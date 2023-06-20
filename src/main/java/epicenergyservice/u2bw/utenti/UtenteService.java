package epicenergyservice.u2bw.utenti;


import epicenergyservice.u2bw.exceptions.BadRequestException;
import epicenergyservice.u2bw.exceptions.NotFoundException;
import epicenergyservice.u2bw.utenti.payloads.UtenteCreatePayload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UtenteService {
    @Autowired
    private UtenteRepository utenteRepo;

    public Utente create(UtenteCreatePayload u) {
        utenteRepo.findByEmail(u.getEmail()).ifPresent(user -> {
            throw new BadRequestException("Email " + user.getEmail() + " already in use!");
        });
        Utente newUser = new Utente(u.getName(), u.getSurname(), u.getUserName(),u.getEmail(), u.getPassword());
        return utenteRepo.save(newUser);
    }

    public Page<Utente> find(int page, int size, String sortBy) {
        if (size < 0)
            size = 10;
        if (size > 100)
            size = 100;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));

        return utenteRepo.findAll(pageable);
    }

    public Utente findById(UUID id) throws NotFoundException {
        return utenteRepo.findById(id).orElseThrow(() -> new NotFoundException("Utete con Id:" + id + "non trovato!!"));
    }

    public Utente findByEmail(String email) throws NotFoundException {
        return utenteRepo.findByEmail(email).orElseThrow(() -> new NotFoundException("Utete con email:" + email + "non trovato!!"));
    }
    public Utente findByUserName(String username) throws NotFoundException {
        return utenteRepo.findByUsername(username).orElseThrow(() -> new NotFoundException("Utete:" + username + "non trovato!!"));
    }
    public Utente findByIdAndUpdate(UUID id, Utente u) throws NotFoundException {
        Utente found = this.findById(id);

        found.setId(id);
        found.setNome(u.getNome());
        found.setCognome(u.getCognome());
        found.setEmail(u.getEmail());

        return utenteRepo.save(found);
    }

    public void findByIdAndDelete(UUID id) throws NotFoundException {
        Utente found = this.findById(id);
        utenteRepo.delete(found);
    }

}
