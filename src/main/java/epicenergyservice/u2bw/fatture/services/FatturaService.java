package epicenergyservice.u2bw.fatture.services;

import epicenergyservice.u2bw.exceptions.NotFoundException;
import epicenergyservice.u2bw.fatture.Fattura;
import epicenergyservice.u2bw.fatture.StatoFattura;
import epicenergyservice.u2bw.fatture.payloads.FatturaCreatePayload;
import epicenergyservice.u2bw.fatture.repositories.FatturaRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
@Getter
@Setter
public class FatturaService {
    private final FatturaRepository fatturaRepository;

    @Autowired
    public FatturaService(FatturaRepository fatturaRepository) {
        this.fatturaRepository = fatturaRepository;
    }

    public Page<Fattura> getFatture(
            int page,
            int size,
            String sortBy,
            LocalDateTime data,
            Integer anno,
            BigDecimal minImporto,
            BigDecimal maxImporto
    ) {
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by(sortBy));
        return fatturaRepository.find(pageRequest, data, anno, minImporto, maxImporto);
    }

    public Fattura getFattura(UUID fatturaId) throws NotFoundException {
        return fatturaRepository.findById(fatturaId)
                .orElseThrow(() -> new NotFoundException("Fattura non trovata"));
    }

    //TODO Recuperare cliente da id
    //TODO Recuperare stato fattura da stato default (non pagata)
    public Fattura createFattura(FatturaCreatePayload payload) {
        Fattura fattura = new Fattura();
        fattura.setData(payload.getData());
        fattura.setImporto(payload.getImporto().doubleValue());
        fattura.setNumero(payload.getNumero());
        fattura.setCliente(payload.getCliente());

        return fatturaRepository.save(fattura);
    }


    //TODO creare un payload per fattura update e passare data e stato
    public Fattura updateFattura(UUID fatturaId, FatturaCreatePayload payload) throws NotFoundException {
        Fattura fattura = fatturaRepository.findById(fatturaId)
                .orElseThrow(() -> new NotFoundException("Fattura non trovata"));

        StatoFattura statoFattura = new StatoFattura();
        statoFattura.setStato(payload.getStato());

        fattura.setStatoFattura(statoFattura);

        return fatturaRepository.save(fattura);
    }


    public void deleteFattura(UUID fatturaId) throws NotFoundException {
        if (!fatturaRepository.existsById(fatturaId)) {
            throw new NotFoundException("Fattura non trovata");
        }

        fatturaRepository.deleteById(fatturaId);
    }

    public Page<Fattura> findByClienteId(UUID clienteId, int page, int size, String sortBy) {
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by(sortBy));
        return fatturaRepository.findByCliente_Id(clienteId, pageRequest);
    }

    public Page<Fattura> findByStatoId(UUID statoId, int page, int size, String sortBy) {
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by(sortBy));
        return fatturaRepository.findByStatoFattura_Id(statoId, pageRequest);
    }
}
