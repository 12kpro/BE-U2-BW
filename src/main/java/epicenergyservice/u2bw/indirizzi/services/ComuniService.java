package epicenergyservice.u2bw.indirizzi.services;


import epicenergyservice.u2bw.exceptions.BadRequestException;
import epicenergyservice.u2bw.exceptions.NotFoundException;
import epicenergyservice.u2bw.indirizzi.Comune;
import epicenergyservice.u2bw.indirizzi.Provincia;
import epicenergyservice.u2bw.indirizzi.payloads.ComuneCreatePayload;
import epicenergyservice.u2bw.indirizzi.repositories.ComuniRepository;
import epicenergyservice.u2bw.utenti.Ruolo;
import epicenergyservice.u2bw.utenti.services.RuoloService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@Slf4j
@Service
public class ComuniService {
     @Autowired
     ComuniRepository comuniRepository;
    @Autowired
    ProvinceService provinceService;
    public Comune create(ComuneCreatePayload c) {
        comuniRepository.findByCodComuneAndNomeAndProvincia_Id(c.getCodComune(),c.getNome(),c.getProvincia()).ifPresent(comune -> {
            throw new BadRequestException("Comune " + comune.getNome() + " esistente!");
        });
        Provincia provincia = provinceService.findById(c.getProvincia());
        Comune newComune = new Comune(c.getCodComune(),c.getNome(),provincia);
        return comuniRepository.save(newComune);
    }
    public Page<Comune> find(int page, int size, String sortBy) {
        if (size < 0)
            size = 10;
        if (size > 100)
            size = 100;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));

        return comuniRepository.findAll(pageable);
    }
    public Comune findById(UUID id) throws NotFoundException {
        return comuniRepository.findById(id).orElseThrow(() -> new NotFoundException("Comune con Id:" + id + "non trovato!!"));
    }

    public Comune update( Comune c) throws NotFoundException {
        this.findById(c.getId());

        return comuniRepository.save(c);
    }
    public void findByIdAndDelete(UUID id) throws NotFoundException {
        Comune found = this.findById(id);
        comuniRepository.delete(found);
    }

    public void deleteAll()  {
        comuniRepository.deleteAll();
    }
}
