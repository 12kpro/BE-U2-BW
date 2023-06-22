package epicenergyservice.u2bw.clienti.services;

import epicenergyservice.u2bw.clienti.Cliente;
import epicenergyservice.u2bw.clienti.TipoCliente;
import epicenergyservice.u2bw.clienti.repositories.TipoClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TipoClienteService {
    private final TipoClienteRepository tipoClienteRepository;
    @Autowired
    public TipoClienteService(TipoClienteRepository tipoClienteRepository) {
        this.tipoClienteRepository = tipoClienteRepository;
    }

    public Optional<TipoCliente> getTipoClienteById(UUID id) {
        return tipoClienteRepository.findByClienti_TipoCliente_Id(id);
    }
    public Optional<TipoCliente> getTipoClienteByNome(String nome) {
        return tipoClienteRepository.findByClienti_TipoCliente_Nome(nome);
    }

    public void deleteTipoCliente(UUID id) {
        tipoClienteRepository.deleteById(id);
    }



}