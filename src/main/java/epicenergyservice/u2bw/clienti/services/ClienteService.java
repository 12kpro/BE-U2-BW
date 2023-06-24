package epicenergyservice.u2bw.clienti.services;

import epicenergyservice.u2bw.clienti.Cliente;
import epicenergyservice.u2bw.clienti.TipoCliente;
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
        Indirizzo io = null;
        clienteRepository.findByEmail(c.getEmail()).ifPresent(user -> {
            throw new BadRequestException("Cliente " + c.getEmail() + " already in use!");
        });
        Indirizzo il = indirizzoService.findById(c.getIndirizzoSedeLegale());
        if(c.getIndirizzoSedeOperativa() != null){
            io = indirizzoService.findById(c.getIndirizzoSedeOperativa());
        }
        TipoCliente tc = tipoClienteService.findById(c.getTipoCliente());

        Cliente newCliente = new Cliente(
                c.getNomeContatto(),
                c.getCognomeContatto(),
                c.getEmailContatto(),
                c.getDataInserimento(),
                c.getDataUltimoContatto(),
                c.getEmail(),
                c.getPartitaIva(),
                c.getPec(),
                c.getRagioneSociale(),
                c.getTelefono(),
                c.getTelefonoContatto(),
                tc,
                il,
                io);
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
    public Page<Cliente> findByParams(int page, int size, String sortBy,
               LocalDate dataInserimento,
               LocalDate dataUltimoContatto,
               Double fatturatoAnnuale,
               String ragioneSociale,
               Integer provinciaSedeLegaleId
    ) {
        Provincia provincia = null;
        if (size < 0)
            size = 10;
        if (size > 100)
            size = 100;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        if(provinciaSedeLegaleId != null){
           provincia = provinceService.findById(provinciaSedeLegaleId);
        }
        return clienteRepository.findByDataInserimentoAndRagioneSocialeIgnoreCaseAndFatturatoAnnualeAndDataUltimoContattoAndIndirizzoSedeLegale_Comune_Provincia(
                dataInserimento,
                ragioneSociale,
                fatturatoAnnuale,
                dataUltimoContatto,
                provincia,
                pageable);
    }
    public Cliente findById(UUID id) throws NotFoundException {
        return clienteRepository.findById(id).orElseThrow(() -> new NotFoundException("Cliente con Id:" + id + "non trovato!!"));
    }

    public Cliente findByEmail(String email) throws NotFoundException {
        return clienteRepository.findByEmail(email).orElseThrow(() -> new NotFoundException("Cliente con email:" + email + "non trovato!!"));
    }

    public Cliente findByIdAndUpdate(UUID id, ClientiCreatePayloads c) throws NotFoundException {
        Cliente found = this.findById(id);
        Indirizzo io = null;
        if(c.getIndirizzoSedeOperativa() != null){
            io = indirizzoService.findById(c.getIndirizzoSedeOperativa());
        }
        Indirizzo il = indirizzoService.findById(c.getIndirizzoSedeLegale());
        TipoCliente tc = tipoClienteService.findById(c.getTipoCliente());

        found.setId(id);
        found.setEmail(c.getEmail());
        found.setDataInserimento(c.getDataInserimento());
        found.setCognomeContatto(c.getCognomeContatto());
        found.setPec(c.getPec());
        found.setDataUltimoContatto(c.getDataUltimoContatto());
        found.setTelefono(c.getTelefono());
        found.setTelefonoContatto(c.getTelefonoContatto());
        found.setNomeContatto(c.getNomeContatto());
        found.setEmailContatto(c.getEmailContatto());
        found.setPartitaIva(c.getPartitaIva());
        found.setRagioneSociale(c.getRagioneSociale());
        found.setIndirizzoSedeOperativa(io);
        found.setIndirizzoSedeLegale(il);
        found.setTipoCliente(tc);

        return clienteRepository.save(found);
    }

    public void findByIdAndDelete(UUID id) throws NotFoundException {
        Cliente found = this.findById(id);
        clienteRepository.delete(found);
    }
}
