package epicenergyservice.u2bw.indirizzi.services;

import epicenergyservice.u2bw.exceptions.BadRequestException;
import epicenergyservice.u2bw.exceptions.NotFoundException;

import epicenergyservice.u2bw.indirizzi.Comune;
import epicenergyservice.u2bw.indirizzi.Indirizzo;
import epicenergyservice.u2bw.indirizzi.payloads.IndirizzoCreatePayload;
import epicenergyservice.u2bw.indirizzi.repositories.IndirizzoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class IndirizzoService {

    @Autowired
    private IndirizzoRepository indirizzoRepository;

    @Autowired
    ComuniService comuniService;

    public Indirizzo create(IndirizzoCreatePayload i) {
        indirizzoRepository.findByCapAndCivicoAndLocalitaAndViaAndComune_Id(i.getCap(),i.getCivico(),i.getLocalità(),i.getVia(),i.getComune()).ifPresent(indirizzo -> {
            throw new BadRequestException("indirizzo already in use!");
        });
      Comune c = comuniService.findById(i.getComune()) ;

        Indirizzo indirizzo= new Indirizzo(i.getVia(), i.getCivico(), i.getLocalità(), i.getCap(), c);
        return indirizzoRepository.save( indirizzo);
    }

    public Page<Indirizzo> find(int page, int size, String sortBy) {
        if (size < 0)
            size = 10;
        if (size > 100)
            size = 100;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));

        return indirizzoRepository.findAll(pageable);
    }


    public Indirizzo findById(UUID id) throws NotFoundException {
        return indirizzoRepository.findById(id).orElseThrow(() -> new NotFoundException("Indirizzo con Id:" + id + "non trovato!!"));
    }


    public Indirizzo findByIdAndUpdate(UUID id, Indirizzo i) throws NotFoundException {
        Indirizzo found = this.findById(id);

       found.setId(id);
       found.setVia(i.getVia());
       found.setCivico(i.getCivico());
        found.setLocalita(i.getLocalita());
        found.setCap(i.getCap());
        found.setComune(i.getComune());

        return indirizzoRepository.save(found);
    }

    public void findByIdAndDelete(UUID id) throws NotFoundException {
        Indirizzo found = this.findById(id);
        indirizzoRepository.delete(found);
    }
}
