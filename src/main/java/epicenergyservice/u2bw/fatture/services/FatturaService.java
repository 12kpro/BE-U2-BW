package epicenergyservice.u2bw.fatture.services;

import epicenergyservice.u2bw.fatture.Fattura;
import epicenergyservice.u2bw.fatture.repositories.FatturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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

    public Fattura createFattura(Fattura fattura) {
        //generiamo un nuovo uuid randomico, poi validiamo la fattura
        UUID fatturaId = UUID.randomUUID();
        fattura.setId(fatturaId);

        if (fattura.getImporto() == null || fattura.getImporto().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("L'importo della fattura deve essere specificato e maggiore di zero.");
        }
        if (fattura.getCliente() == null) {
            throw new IllegalArgumentException("La fattura deve essere associata a un cliente.");
        }
        return fatturaRepository.save(fattura);
    }

    public Fattura updateFattura(UUID fatturaId, Fattura updatedFattura) {

        Fattura existingFattura = fatturaRepository.findById(fatturaId)
                .orElseThrow(() -> new IllegalArgumentException("Fattura non trovata"));
        existingFattura.setAnno(updatedFattura.getAnno());
        existingFattura.setData(updatedFattura.getData());
        existingFattura.setImporto(updatedFattura.getImporto());
        existingFattura.setNumero(updatedFattura.getNumero());

        return fatturaRepository.save(existingFattura);
    }

    public Optional<Fattura> getFatturaPerNumero(int numeroFattura) {
        return fatturaRepository.findByNumero(numeroFattura);
    }
}
