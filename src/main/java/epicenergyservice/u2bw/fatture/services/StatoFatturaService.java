package epicenergyservice.u2bw.fatture.services;

import epicenergyservice.u2bw.fatture.Fattura;
import epicenergyservice.u2bw.fatture.StatoFattura;
import epicenergyservice.u2bw.fatture.payloads.StatoFatturaUpdatePayload;
import epicenergyservice.u2bw.fatture.repositories.StatoFatturaRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Getter
@Setter
public class StatoFatturaService {
    private final StatoFatturaRepository statoFatturaRepository;

    @Autowired
    public StatoFatturaService(StatoFatturaRepository statoFatturaRepository) {
        this.statoFatturaRepository = statoFatturaRepository;
    }

    public List<StatoFattura> getAllStatoFattura() {
        return statoFatturaRepository.findAll();
    }

    public Optional<StatoFattura> getStatoFatturaById(Long id) {
        return statoFatturaRepository.findById(id);
    }

    public StatoFattura saveStatoFattura(StatoFattura statoFattura) {
        return statoFatturaRepository.save(statoFattura);
    }

    public void deleteStatoFattura(Long id) {
        statoFatturaRepository.deleteById(id);
    }

    public StatoFattura createStatoFattura(StatoFatturaUpdatePayload statoFatturaPayload) {
        if (statoFatturaPayload.getFattura() != null) {
            Fattura fattura = statoFatturaPayload.getFattura();
            Optional<StatoFattura> existingStato = statoFatturaRepository.findByFattura(fattura);
            if (existingStato.isPresent()) {
                throw new IllegalArgumentException("Uno stato fattura esiste già per la fattura specificata.");
            }
        }

        StatoFattura statoFattura = new StatoFattura();
        statoFattura.setCodice(statoFatturaPayload.getCodice());
        statoFattura.setDescrizione(statoFatturaPayload.getDescrizione());
        statoFattura.setFattura(statoFatturaPayload.getFattura());

        return statoFatturaRepository.save(statoFattura);
    }

    public StatoFattura updateStatoFattura(StatoFatturaUpdatePayload statoFatturaPayload) {
        Long id = statoFatturaPayload.getId();
        Optional<StatoFattura> existingStato = statoFatturaRepository.findById(id);
        if (existingStato.isEmpty()) {
            throw new IllegalArgumentException("Stato fattura non trovato con ID: " + id);
        }

        StatoFattura statoFattura = existingStato.get();
        statoFattura.setCodice(statoFatturaPayload.getCodice());
        statoFattura.setDescrizione(statoFatturaPayload.getDescrizione());
        statoFattura.setFattura(statoFatturaPayload.getFattura());

        return statoFatturaRepository.save(statoFattura);
    }
}
