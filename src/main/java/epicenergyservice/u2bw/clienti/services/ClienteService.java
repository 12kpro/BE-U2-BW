package epicenergyservice.u2bw.clienti.services;

import epicenergyservice.u2bw.clienti.Cliente;
import epicenergyservice.u2bw.clienti.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ClienteService {
    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

   /* public Cliente findById(UUID id) {
        return clienteRepository.findById(id).orElse(null);
    }*/
    public Page<Cliente> findByNomeContatto(String nomeContatto, int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return clienteRepository.findByNomeContatto(nomeContatto, pageable);
    }

    public Page<Cliente> findByEmail(String email, int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return clienteRepository.findByEmail(email, pageable);
    }

    public Page<Cliente> filtraClientiPerFatturatoAnnuo(double minFatturato, double maxFatturato, int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return clienteRepository.filtraClientiPerFatturatoAnnuo(minFatturato, maxFatturato, pageable);
    }

    public Page<Cliente> findByInserimento(LocalDate dataInserimento, int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return clienteRepository.findByInserimento(dataInserimento, pageable);
    }

    public Page<Cliente> searchByPartName(String partName, int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return clienteRepository.searchByPartName(partName, pageable);
    }

    public Page<Cliente> findByUltimocontatto(LocalDate ultimoContatto, int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return clienteRepository.findByUltimocontatto(ultimoContatto, pageable);
    }

    public Page<Cliente> searchByProvincia(String provincia, int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return clienteRepository.searchByProvincia(provincia, pageable);
    }

}
