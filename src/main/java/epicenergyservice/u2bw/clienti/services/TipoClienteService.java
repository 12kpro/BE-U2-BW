package epicenergyservice.u2bw.clienti.services;


import epicenergyservice.u2bw.clienti.TipoCliente;
import epicenergyservice.u2bw.clienti.payloads.TipoClienteCreatePayload;
import epicenergyservice.u2bw.clienti.repositories.TipoClienteRepository;
import epicenergyservice.u2bw.exceptions.BadRequestException;
import epicenergyservice.u2bw.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
public class TipoClienteService {


    @Autowired
    private TipoClienteRepository TipoClienteRepository;


    public TipoCliente create(TipoClienteCreatePayload t) {
        TipoClienteRepository.findByNome(t.getNome()).ifPresent(user -> {
            throw new BadRequestException("Tipo cliente: " + t.getNome() + " already in use!");
        });

        TipoCliente newTipo = new TipoCliente(t.getNome());
        return TipoClienteRepository.save(newTipo);
    }

    public Page<TipoCliente> find(int page, int size, String sortBy) {
        if (size < 0)
            size = 10;
        if (size > 100)
            size = 100;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));

        return TipoClienteRepository.findAll(pageable);
    }

    public TipoCliente findById(UUID id) throws NotFoundException {
        return TipoClienteRepository.findById(id).orElseThrow(() -> new NotFoundException("Tipo cliente con Id:" + id + "non trovato!!"));
    }

    public TipoCliente findByNome(String nome) throws NotFoundException {
        return TipoClienteRepository.findByNome(nome).orElseThrow(() -> new NotFoundException("Tipo cliente:" + nome + "non trovato!!"));
    }

    public TipoCliente findByIdAndUpdate(UUID id, TipoCliente t) throws NotFoundException {
        TipoCliente found = this.findById(id);
        found.setId(id);
        found.setNome(t.getNome());
        return TipoClienteRepository.save(found);
    }

    public void findByIdAndDelete(UUID id) throws NotFoundException {
        TipoCliente found = this.findById(id);
        TipoClienteRepository.delete(found);
    }


//    private final TipoClienteRepository TipoClienteRepository;
//    @Autowired
//    public TipoClienteService(TipoClienteRepository TipoClienteRepository) {
//        this.TipoClienteRepository = TipoClienteRepository;
//    }
//
//    public Optional<TipoCliente> getTipoClienteById(UUID id) {
//        return TipoClienteRepository.findByClienti_TipoCliente_Id(id);
//    }
//    public Optional<TipoCliente> getTipoClienteByNome(String nome) {
//        return TipoClienteRepository.findByClienti_TipoCliente_Nome(nome);
//    }
//
//    public void deleteTipoCliente(UUID id) {
//        TipoClienteRepository.deleteById(id);
//    }



}