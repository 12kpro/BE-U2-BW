package epicenergyservice.u2bw.fatture.services;

import epicenergyservice.u2bw.fatture.StatoFattura;
import epicenergyservice.u2bw.fatture.repositories.StatoFatturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StatoFatturaService {
    private final StatoFatturaRepository statoFatturaRepository;

    @Autowired
    public StatoFatturaService(StatoFatturaRepository statoFatturaRepository) {
        this.statoFatturaRepository = statoFatturaRepository;
    }

    public List<StatoFattura> getAllStatiFattura() {
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

    public StatoFattura createStatoFattura(StatoFattura statoFattura) {
        // Esempio di logica di business per la creazione di uno stato fattura
        // Verifica se lo stato fattura esiste già per la fattura specificata
        if (statoFattura.getFattura() != null) {
            Fattura fattura = statoFattura.getFattura();
            Optional<StatoFattura> existingStato = statoFatturaRepository.findByFattura(fattura);
            if (existingStato.isPresent()) {
                throw new IllegalArgumentException("Uno stato fattura esiste già per la fattura specificata.");
            }
        }

        return statoFatturaRepository.save(statoFattura);
    }

    public StatoFattura updateStatoFattura(StatoFattura statoFattura) {
        if (statoFattura.getFattura() != null) {
            Fattura fattura = statoFattura.getFattura();
            Optional<StatoFattura> existingStato = statoFatturaRepository.findByFattura(fattura);
            if (existingStato.isEmpty()) {
                throw new IllegalArgumentException("Non la fattura specificata.");
            }
        }

        return statoFatturaRepository.save(statoFattura);
    }

}
