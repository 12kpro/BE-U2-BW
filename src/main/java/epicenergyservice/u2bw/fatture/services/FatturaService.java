package epicenergyservice.u2bw.fatture.services;

import epicenergyservice.u2bw.fatture.Fattura;
import epicenergyservice.u2bw.fatture.repositories.FatturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class FatturaService {
    private final FatturaRepository fatturaRepository;

    @Autowired
    public FatturaService(FatturaRepository fatturaRepository) {
        this.fatturaRepository = fatturaRepository;
    }

    public List<Fattura> getAllFattura() {
        return fatturaRepository.findAll();
    }

    public Optional<Fattura> getFatturaById(UUID id) {
        return fatturaRepository.findById(id);
    }

    public Fattura saveFattura(Fattura fattura) {
        return fatturaRepository.save(fattura);
    }

    public void deleteFattura(UUID id) {
        fatturaRepository.deleteById(id);
    }
}
