package epicenergyservice.u2bw.clienti.services;

import epicenergyservice.u2bw.clienti.TipoCliente;
import epicenergyservice.u2bw.clienti.repositories.TipoClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TipoClienteService {
    private final TipoClienteRepository tipoClienteRepository;

    public TipoClienteService(TipoClienteRepository tipoClienteRepository) {
        this.tipoClienteRepository = tipoClienteRepository;
    }

    public Page<TipoCliente> getAllTipoClienti(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return tipoClienteRepository.findAll(pageable);
    }

    public Page<TipoCliente> getTipoClienteById(UUID id, int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return tipoClienteRepository.findById(id, pageable);
    }

    public TipoCliente createTipoCliente(TipoCliente tipoCliente) {
        return tipoClienteRepository.save(tipoCliente);
    }

    public TipoCliente updateTipoCliente(TipoCliente tipoCliente) {
        return tipoClienteRepository.save(tipoCliente);
    }

    public void deleteTipoCliente(UUID id) {
        tipoClienteRepository.deleteById(id);
    }
}