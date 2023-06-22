package epicenergyservice.u2bw.clienti.services;

import epicenergyservice.u2bw.clienti.Cliente;
import epicenergyservice.u2bw.clienti.repositories.ClienteRepository;
import epicenergyservice.u2bw.exceptions.NotFoundException;
import epicenergyservice.u2bw.indirizzi.Provincia;
import epicenergyservice.u2bw.indirizzi.services.ProvinceService;
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
    @Autowired
    private final ClienteRepository clienteRepository;
    @Autowired
    ProvinceService provinceService;
    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public Cliente findById(UUID id) {
        return clienteRepository.findById(id).orElseThrow(() -> new NotFoundException("Cliente con Id:" + id + "non trovato!!"));
    }
    public Page<Cliente> findByNomeContatto(String nomeContatto, int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return clienteRepository.findByNomeContatto(nomeContatto, pageable);
    }

    public Page<Cliente> findByEmail(String email, int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return clienteRepository.findByEmail(email, pageable);
    }

    public Page<Cliente> filtraClientiPerFatturatoAnnuo( Double fatturato, int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return clienteRepository.findByFatturatoAnnuale(fatturato, pageable);
    }

    public Page<Cliente> findByInserimento(LocalDate dataInserimento, int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return clienteRepository.findByDataInserimento(dataInserimento, pageable);
    }

    public Page<Cliente> searchByPartName(String partName, int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return clienteRepository.findByNomeContattoContainsIgnoreCase(partName, pageable);
    }

    public Cliente findByUltimocontatto(LocalDate ultimoContatto ) {
        return clienteRepository.findByDataUltimoContatto(ultimoContatto).orElseThrow(() -> new NotFoundException("Cliente non trovato!!"));
    }

    public Page<Cliente> searchByProvincia(Integer provinciaId, int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Provincia p = provinceService.findById(provinciaId);
        return clienteRepository.findByIndirizzoSedeLegale_Comune_Provincia(p, pageable);
    }

}
