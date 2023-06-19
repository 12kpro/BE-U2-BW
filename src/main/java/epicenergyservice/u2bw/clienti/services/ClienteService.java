package epicenergyservice.u2bw.clienti.services;

import epicenergyservice.u2bw.clienti.Cliente;
import epicenergyservice.u2bw.clienti.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    @Autowired
    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public List<Cliente> getAllClienti() {
        return clienteRepository.findAll();
    }

    public Optional<Cliente> getClienteById(UUID id) {
        return clienteRepository.findById(id);
    }

    public List<Cliente> getClientiByNomeContatto(String nomeContatto) {
        return clienteRepository.findByNomeContatto(nomeContatto);
    }

    public List<Cliente> getClientiByEmail(String email) {
        return clienteRepository.findByEmail(email);
    }
}
