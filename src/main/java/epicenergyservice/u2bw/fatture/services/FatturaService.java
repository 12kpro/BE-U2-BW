package epicenergyservice.u2bw.fatture.services;

import epicenergyservice.u2bw.clienti.Cliente;
import epicenergyservice.u2bw.clienti.repositories.ClienteRepository;
import epicenergyservice.u2bw.exceptions.NotFoundException;
import epicenergyservice.u2bw.fatture.Fattura;
import epicenergyservice.u2bw.fatture.repositories.FatturaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FatturaService {
    private final FatturaRepository fatturaRepository;
    private final ClienteRepository clienteRepository;

    public Page<Fattura> find(int page, int size, String sortBy, LocalDateTime data, Integer anno, Double minImporto, Double maxImporto) {
        PageRequest pageRequest = PageRequest.of(page, size);

        if (data != null) {
            return fatturaRepository.findByData(data, pageRequest);
        } else if (anno != null) {
            return fatturaRepository.findByAnno(anno, pageRequest);
        } else if (minImporto != null && maxImporto != null) {
            return fatturaRepository.findByImportoBetween(minImporto, maxImporto, pageRequest);
        }

        return fatturaRepository.findAll(pageRequest);
    }

    public Fattura findById(UUID fatturaId) throws NotFoundException {
        return fatturaRepository.findById(fatturaId)
                .orElseThrow(() -> new NotFoundException("Fattura non trovata"));
    }

    public Fattura create(FatturaCreatePayload payload) {
        Fattura fattura = new Fattura();
        fattura.setAnno(payload.getAnno());
        fattura.setData(payload.getData());
        fattura.setImporto(payload.getImporto());
        fattura.setNumero(payload.getNumero());

        Cliente cliente = clienteRepository.findById(payload.getClienteId())
                .orElseThrow(() -> new NotFoundException("Cliente non trovato"));

        fattura.setCliente(cliente);

        return fatturaRepository.save(fattura);
    }

    public Fattura findByIdAndUpdate(UUID fatturaId, Fattura updatedFattura) throws NotFoundException {
        Fattura fattura = fatturaRepository.findById(fatturaId)
                .orElseThrow(() -> new NotFoundException("Fattura non trovata"));

        fattura.setAnno(updatedFattura.getAnno());
        fattura.setData(updatedFattura.getData());
        fattura.setImporto(updatedFattura.getImporto());
        fattura.setNumero(updatedFattura.getNumero());

        Cliente cliente = clienteRepository.findById(updatedFattura.getCliente().getId())
                .orElseThrow(() -> new NotFoundException("Cliente non trovato"));

        fattura.setCliente(cliente);

        return fatturaRepository.save(fattura);
    }

    public void findByIdAndDelete(UUID fatturaId) throws NotFoundException {
        if (!fatturaRepository.existsById(fatturaId)) {
            throw new NotFoundException("Fattura non trovata");
        }

        fatturaRepository.deleteById(fatturaId);
    }

    public Page<Fattura> findByClienteId(UUID clienteId, int page, int size, String sortBy) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return fatturaRepository.findByCliente_Id(clienteId, pageRequest);
    }

    public Page<Fattura> findByStatoId(UUID statoId, int page, int size, String sortBy) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return fatturaRepository.findByStatoFattura_Id(statoId, pageRequest);
    }
}
