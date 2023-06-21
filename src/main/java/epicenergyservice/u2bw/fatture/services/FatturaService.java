package epicenergyservice.u2bw.fatture.services;

import epicenergyservice.u2bw.clienti.Cliente;
import epicenergyservice.u2bw.exceptions.BadRequestException;
import epicenergyservice.u2bw.exceptions.NotFoundException;
import epicenergyservice.u2bw.fatture.Fattura;
import epicenergyservice.u2bw.fatture.repositories.FatturaRepository;
import epicenergyservice.u2bw.utenti.Ruolo;
import epicenergyservice.u2bw.utenti.Utente;
import epicenergyservice.u2bw.utenti.payloads.UtenteCreatePayload;
import epicenergyservice.u2bw.utenti.repositories.RuoloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class FatturaService {


    @Autowired
    private FatturaRepository fatturaRepository;

    public Fattura create(FatturaCreatePayload f) {
//        fatturaRepository.findByEmail(u.getEmail()).ifPresent(user -> {
//            throw new BadRequestException("Email " + user.getEmail() + " already in use!");
//        });

//        Ruolo ruoloDefault = fatturaRepository.findByNome("USER").orElseThrow(() -> new NotFoundException("Ruolo USER non esiste!!"));
//        Utente newUser = new Utente(u.getCognome(),u.getEmail(),u.getNome(),u.getPassword(), u.getUsername());
//        newUser.getRuoli().add(ruoloDefault);

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


    public Fattura findById(UUID id) throws NotFoundException {
        return fatturaRepository.findById(id).orElseThrow(() -> new NotFoundException("Utete con Id:" + id + "non trovato!!"));
    }


    public Fattura findByIdAndUpdate(UUID id, Fattura f) throws NotFoundException {
        Fattura found = this.findById(id);

//        found.setId(id);
//        found.setNome(u.getNome());
//        found.setCognome(u.getCognome());
//        found.setEmail(u.getEmail());

        return fatturaRepository.save(found);
    }

    public void findByIdAndDelete(UUID id) throws NotFoundException {
        Fattura found = this.findById(id);
        fatturaRepository.delete(found);
    }


    /// EXTRA
    public Page<Fattura> findByClienteId(UUID id, int page, int size, String sortBy) {
        if (size < 0)
            size = 10;
        if (size > 100)
            size = 100;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));

        return fatturaRepository.findByCliente_Id(id,pageable);
    }

    public Page<Fattura> findByStatoId(UUID id, int page, int size, String sortBy) {
        if (size < 0)
            size = 10;
        if (size > 100)
            size = 100;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));

        return fatturaRepository.findByStatoFattura_Id(id,pageable);
    }

}
