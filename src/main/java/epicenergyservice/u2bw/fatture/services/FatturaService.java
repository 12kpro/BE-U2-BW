package epicenergyservice.u2bw.fatture.services;

import epicenergyservice.u2bw.clienti.Cliente;
import epicenergyservice.u2bw.fatture.Fattura;
import epicenergyservice.u2bw.fatture.repositories.FatturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
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

    public boolean deleteFattura(UUID id) {
        fatturaRepository.deleteById(id);
        return false;
    }

    public Fattura createFattura(int anno, LocalDateTime data, Double importo, int numero, Cliente cliente) {
        Fattura fattura = new Fattura();
        fattura.setAnno(anno);
        fattura.setData(data);
        fattura.setImporto(importo);
        fattura.setNumero(numero);
        fattura.setCliente(cliente);

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
