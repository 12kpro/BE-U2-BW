package epicenergyservice.u2bw.fatture.services;

import epicenergyservice.u2bw.exceptions.BadRequestException;
import epicenergyservice.u2bw.exceptions.NotFoundException;
import epicenergyservice.u2bw.fatture.StatoFattura;
import epicenergyservice.u2bw.fatture.payloads.StatoFatturaCreatePayload;
import epicenergyservice.u2bw.fatture.repositories.StatoFatturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
public class StatoFatturaService {

    @Autowired
    private StatoFatturaRepository statoFatturaRepository;


    public StatoFattura create(StatoFatturaCreatePayload st) {
        statoFatturaRepository.findByStatoIgnoreCase(st.getStato()).ifPresent(user -> {
            throw new BadRequestException("Stato fattura: " + st.getStato() + " already in use!");
        });

        StatoFattura newStatoFattura = new StatoFattura(st.getStato());
        return statoFatturaRepository.save(newStatoFattura);
    }

    public Page<StatoFattura> find(int page, int size, String sortBy) {
        if (size < 0)
            size = 10;
        if (size > 100)
            size = 100;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));

        return statoFatturaRepository.findAll(pageable);
    }

    public StatoFattura findById(UUID id) throws NotFoundException {
        return statoFatturaRepository.findById(id).orElseThrow(() -> new NotFoundException("Stato fattura con Id:" + id + "non trovato!!"));
    }

    public StatoFattura findByNome(String nome) throws NotFoundException {
        return statoFatturaRepository.findByStatoIgnoreCase(nome).orElseThrow(() -> new NotFoundException("Stato fattura:" + nome + "non trovato!!"));
    }

    public StatoFattura findByIdAndUpdate(UUID id, StatoFattura st) throws NotFoundException {
        StatoFattura found = this.findById(id);
        found.setId(id);
        found.setStato(st.getStato());
        return statoFatturaRepository.save(found);
    }

    public void findByIdAndDelete(UUID id) throws NotFoundException {
        StatoFattura found = this.findById(id);
        statoFatturaRepository.delete(found);
    }
    
    
    
    
//    private final StatoFatturaRepository statoFatturaRepository;
//
//    @Autowired
//    public StatoFatturaService(StatoFatturaRepository statoFatturaRepository) {
//        this.statoFatturaRepository = statoFatturaRepository;
//    }
//
//    public List<StatoFattura> getAllStatoFattura() {
//        return statoFatturaRepository.findAll();
//    }
//
//    public Optional<StatoFattura> getStatoFatturaById(UUID id) {
//        return statoFatturaRepository.findById(id);
//    }
//
//    public StatoFattura saveStatoFattura(StatoFattura statoFattura) {
//        return statoFatturaRepository.save(statoFattura);
//    }
//
//    public void deleteStatoFattura(UUID id) {
//        statoFatturaRepository.deleteById(id);
//    }
//
//    public StatoFattura createStatoFattura(StatoFatturaUpdatePayload statoFatturaPayload) {
//        if (statoFatturaPayload.getFattura() != null) {
//            Fattura fattura = statoFatturaPayload.getFattura();
//            Optional<StatoFattura> existingStato = statoFatturaRepository.findByFattura(fattura);
//            if (existingStato.isPresent()) {
//                throw new IllegalArgumentException("Uno stato fattura esiste già per la fattura specificata.");
//            }
//        }
//
//        StatoFattura statoFattura = new StatoFattura();
//        statoFattura.setStato(statoFatturaPayload.getStato());
//        statoFattura.setFattura(statoFatturaPayload.getFattura());
//
//        return statoFatturaRepository.save(statoFattura);
//    }
//
//    public StatoFattura updateStatoFattura(StatoFatturaUpdatePayload statoFatturaPayload) {
//        UUID id = statoFatturaPayload.getId();
//        Optional<StatoFattura> existingStato = statoFatturaRepository.findById(id);
//        if (existingStato.isEmpty()) {
//            throw new IllegalArgumentException("Stato fattura non trovato con ID: " + id);
//        }
//
//        StatoFattura statoFattura = existingStato.get();
//        statoFattura.setStato(statoFatturaPayload.getStato());
//        statoFattura.setFattura(statoFatturaPayload.getFattura());
//
//        return statoFatturaRepository.save(statoFattura);
//    }
}
