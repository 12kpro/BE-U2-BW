package epicenergyservice.u2bw.clienti.services;

import epicenergyservice.u2bw.clienti.Cliente;
import epicenergyservice.u2bw.clienti.TipoCliente;
import epicenergyservice.u2bw.clienti.payloads.ClienteGetPayload;
import epicenergyservice.u2bw.clienti.payloads.ClientiCreatePayloads;
import epicenergyservice.u2bw.clienti.repositories.ClienteRepository;
import epicenergyservice.u2bw.exceptions.BadRequestException;
import epicenergyservice.u2bw.exceptions.NotFoundException;
import epicenergyservice.u2bw.indirizzi.Indirizzo;
import epicenergyservice.u2bw.indirizzi.Provincia;
import epicenergyservice.u2bw.indirizzi.services.IndirizzoService;
import epicenergyservice.u2bw.indirizzi.services.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.UUID;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private ProvinceService provinceService;
    @Autowired
    private IndirizzoService indirizzoService;
    @Autowired
    TipoClienteService tipoClienteService;
    public Cliente create(ClientiCreatePayloads c) {
        clienteRepository.findByEmail(c.getEmail()).ifPresent(user -> {
            throw new BadRequestException("Cliente " + c.getEmail() + " already in use!");
        });
        //TODO creare oggetto cliente da payload
        //TODO creare costruttori per campi null
        Indirizzo il = indirizzoService.findById(c.getIndirizzoSedeLegale());
        Indirizzo io = indirizzoService.findById(c.getIndirizzoSedeOperativa());
        TipoCliente tc = tipoClienteService.findById(c.getTipoCliente());

        Cliente newCliente = new Cliente();
        return clienteRepository.save(newCliente);
    }

    public Page<Cliente> find(int page, int size, String sortBy) {
        if (size < 0)
            size = 10;
        if (size > 100)
            size = 100;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));

        return clienteRepository.findAll(pageable);
    }
    public Page<Cliente> findQuery(ClienteGetPayload body, int page, int size, String sortBy) {
        Provincia provincia = null;
        if (size < 0)
            size = 10;
        if (size > 100)
            size = 100;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        if(body.getProvinciaSedeLegaleId() != null){
           provincia = provinceService.findById(body.getProvinciaSedeLegaleId());
        }
        return clienteRepository.findByDataInserimentoAndRagioneSocialeIgnoreCaseAndFatturatoAnnualeAndDataUltimoContattoAndIndirizzoSedeLegale_Comune_Provincia(body.getDataInserimento(), body.getRagioneSociale(), body.getFatturatoAnnuale(),body.getDataUltimoContatto(), provincia,pageable);
    }
//    public Page<Cliente> findByFatturatoAnnuale(Double fatturato, int page, int size, String sortBy) {
//        if (size < 0)
//            size = 10;
//        if (size > 100)
//            size = 100;
//        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
//
//        return clienteRepository.findByFatturatoAnnuale(fatturato,pageable);
//    }
//    public Page<Cliente> findByDataInserimento(LocalDate dataInserimento, int page, int size, String sortBy) {
//        if (size < 0)
//            size = 10;
//        if (size > 100)
//            size = 100;
//        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
//
//        return clienteRepository.findByDataInserimento(dataInserimento,pageable);
//    }
//    public Page<Cliente> findByRagioneSocialeContainsIgnoreCase(String nome, int page, int size, String sortBy) {
//        if (size < 0)
//            size = 10;
//        if (size > 100)
//            size = 100;
//        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
//
//        return clienteRepository.findByRagioneSocialeContainsIgnoreCase(nome,pageable);
//    }
//    public Page<Cliente> findByDataUltimoContatto(LocalDate dataContatto,int page, int size, String sortBy) {
//        if (size < 0)
//            size = 10;
//        if (size > 100)
//            size = 100;
//        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
//
//        return clienteRepository.findByDataUltimoContatto(dataContatto,pageable);
//    }
//    public Page<Cliente> findByIndirizzoSedeLegale_Comune_Provincia(Integer provinciaId,int page, int size, String sortBy) {
//        if (size < 0)
//            size = 10;
//        if (size > 100)
//            size = 100;
//        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
//        Provincia p = provinceService.findById(provinciaId);
//        return clienteRepository.findByIndirizzoSedeLegale_Comune_Provincia(p,pageable);
//    }
    public Cliente findById(UUID id) throws NotFoundException {
        return clienteRepository.findById(id).orElseThrow(() -> new NotFoundException("Utete con Id:" + id + "non trovato!!"));
    }

    public Cliente findByEmail(String email) throws NotFoundException {
        return clienteRepository.findByEmail(email).orElseThrow(() -> new NotFoundException("Utete con email:" + email + "non trovato!!"));
    }

    public Cliente findByIdAndUpdate(UUID id, Cliente c) throws NotFoundException {
        Cliente found = this.findById(id);
        //TODO implementare i setters
        found.setId(id);
        found.setEmail(c.getEmail());

        return clienteRepository.save(found);
    }

    public void findByIdAndDelete(UUID id) throws NotFoundException {
        Cliente found = this.findById(id);
        clienteRepository.delete(found);
    }
    
    
    
    
    
//    @Autowired
//    private final ClienteRepository clienteRepository;
//    @Autowired
//    ProvinceService provinceService;
//    public clienteRepository(ClienteRepository clienteRepository) {
//        this.clienteRepository = clienteRepository;
//    }
//
//    public Cliente findById(UUID id) {
//        return clienteRepository.findById(id).orElseThrow(() -> new NotFoundException("Cliente con Id:" + id + "non trovato!!"));
//    }
//    public Page<Cliente> findByNomeContatto(String nomeContatto, int pageNumber, int pageSize) {
//        Pageable pageable = PageRequest.of(pageNumber, pageSize);
//        return clienteRepository.findByNomeContatto(nomeContatto, pageable);
//    }
//
//    public Page<Cliente> findByEmail(String email, int pageNumber, int pageSize) {
//        Pageable pageable = PageRequest.of(pageNumber, pageSize);
//        return clienteRepository.findByEmail(email, pageable);
//    }
//
//    public Page<Cliente> filtraClientiPerFatturatoAnnuo( Double fatturato, int pageNumber, int pageSize) {
//        Pageable pageable = PageRequest.of(pageNumber, pageSize);
//        return clienteRepository.findByFatturatoAnnuale(fatturato, pageable);
//    }
//
//    public Page<Cliente> findByInserimento(LocalDate dataInserimento, int pageNumber, int pageSize) {
//        Pageable pageable = PageRequest.of(pageNumber, pageSize);
//        return clienteRepository.findByDataInserimento(dataInserimento, pageable);
//    }
//
//    public Page<Cliente> searchByPartName(String partName, int pageNumber, int pageSize) {
//        Pageable pageable = PageRequest.of(pageNumber, pageSize);
//        return clienteRepository.findByNomeContattoContainsIgnoreCase(partName, pageable);
//    }
//
//    public Cliente findByUltimocontatto(LocalDate ultimoContatto ) {
//        return clienteRepository.findByDataUltimoContatto(ultimoContatto).orElseThrow(() -> new NotFoundException("Cliente non trovato!!"));
//    }
//
//    public Page<Cliente> searchByProvincia(Integer provinciaId, int pageNumber, int pageSize) {
//        Pageable pageable = PageRequest.of(pageNumber, pageSize);
//        Provincia p = provinceService.findById(provinciaId);
//        return clienteRepository.findByIndirizzoSedeLegale_Comune_Provincia(p, pageable);
//    }

}
