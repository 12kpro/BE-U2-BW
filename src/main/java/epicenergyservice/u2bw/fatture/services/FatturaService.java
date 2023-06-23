package epicenergyservice.u2bw.fatture.services;
import epicenergyservice.u2bw.clienti.Cliente;
import epicenergyservice.u2bw.clienti.services.ClienteService;
import epicenergyservice.u2bw.exceptions.BadRequestException;
import epicenergyservice.u2bw.exceptions.NotFoundException;
import epicenergyservice.u2bw.fatture.Fattura;
import epicenergyservice.u2bw.fatture.StatoFattura;
import epicenergyservice.u2bw.fatture.payloads.FatturaCreatePayload;
import epicenergyservice.u2bw.fatture.payloads.FatturaGetPayload;
import epicenergyservice.u2bw.fatture.payloads.FatturaUpdatePayload;
import epicenergyservice.u2bw.fatture.repositories.FatturaRepository;
import epicenergyservice.u2bw.indirizzi.Provincia;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
@Getter
@Setter
public class FatturaService {

    @Autowired
    private FatturaRepository fatturaRepository;

    @Autowired
    private StatoFatturaService statoFatturaService;

    @Autowired
    private ClienteService clienteService;


    public Fattura create(FatturaCreatePayload f) {
        fatturaRepository.findByNumeroAndCliente_Id(f.getNumero(),f.getCliente()).ifPresent(user -> {
            throw new BadRequestException("Fattura " + f.getNumero() + " already in use!");
        });

        Cliente c = clienteService.findById(f.getCliente());
        StatoFattura st = statoFatturaService.findByNome("NON_PAGATO");

        Fattura newFattura = new Fattura();
        newFattura.setCliente(c);
        newFattura.setStatoFattura(st);
        newFattura.setData(f.getData());
        newFattura.setNumero(f.getNumero());
        newFattura.setImporto(f.getImporto());

        return fatturaRepository.save(newFattura);
    }

    public Page<Fattura> find(int page, int size, String sortBy) {
        if (size < 0)
            size = 10;
        if (size > 100)
            size = 100;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));

        return fatturaRepository.findAll(pageable);
    }
    public Page<Fattura> findByParams(FatturaGetPayload body, int page, int size, String sortBy) {
        Provincia provincia = null;
        if (size < 0)
            size = 10;
        if (size > 100)
            size = 100;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));

        return fatturaRepository.findByDataAndAnnoAndImportoBetweenAndCliente_IdAndStatoFattura_Id(
                body.getData(),
                body.getAnno(),
                body.getMinImporto(),
                body.getMaxImporto(),
                body.getClienteId(),
                body.getStatoId(),
                pageable

        );
    }
    public Fattura findById(UUID id) throws NotFoundException {
        return fatturaRepository.findById(id).orElseThrow(() -> new NotFoundException("Fattura con Id:" + id + "non trovato!!"));
    }

    public Fattura findByNumeroAndCliente_Id(Integer numero, UUID clienteId) throws NotFoundException {
        return fatturaRepository.findByNumeroAndCliente_Id(numero,clienteId).orElseThrow(() -> new NotFoundException("Fattura con numero:" + numero + "non trovato!!"));
    }

    public Fattura findByIdAndUpdate(UUID id, FatturaUpdatePayload f) throws NotFoundException {
        Fattura found = this.findById(id);


        Cliente c = clienteService.findById(f.getCliente());
        StatoFattura st = statoFatturaService.findById(f.getStatoFattura());

        found.setId(id);
        found.setCliente(c);
        found.setStatoFattura(st);
        found.setData(f.getData());
        found.setNumero(f.getNumero());
        found.setImporto(f.getImporto());

        return fatturaRepository.save(found);
    }

    public void findByIdAndDelete(UUID id) throws NotFoundException {
        Fattura found = this.findById(id);
        fatturaRepository.delete(found);
    }
}
