package epicenergyservice.u2bw.utenti.services;

import epicenergyservice.u2bw.exceptions.BadRequestException;
import epicenergyservice.u2bw.exceptions.NotFoundException;
import epicenergyservice.u2bw.utenti.Ruolo;
import epicenergyservice.u2bw.utenti.Utente;
import epicenergyservice.u2bw.utenti.repositories.RuoloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class RuoloService {
    @Autowired
    RuoloRepository ruoloRepository;
    public Ruolo create(String r) {
        ruoloRepository.findByNome(r).ifPresent(user -> {
            throw new BadRequestException("Ruolo " + r + " già in uso!");
        });
        Ruolo newRuolo = new Ruolo(r);
        return ruoloRepository.save(newRuolo);
    }
    public Page<Ruolo> find(int page, int size, String sortBy) {
        if (size < 0)
            size = 10;
        if (size > 100)
            size = 100;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));

        return ruoloRepository.findAll(pageable);
    }
    public Ruolo findById(UUID id) throws NotFoundException {
        return ruoloRepository.findById(id).orElseThrow(() -> new NotFoundException("Ruolo con Id:" + id + "non trovato!!"));
    }
    public Ruolo findNome(String r) throws NotFoundException {
        return ruoloRepository.findByNome(r).orElseThrow(() -> new NotFoundException("Ruolo con nome:" + r + "non trovato!!"));
    }
    public Ruolo findByIdAndUpdate(UUID id, Ruolo r) throws NotFoundException {
        Ruolo found = this.findById(id);
        found.setId(id);
        found.setNome(r.getNome());
        return ruoloRepository.save(found);
    }
    public void findByIdAndDelete(UUID id) throws NotFoundException {
        Ruolo found = this.findById(id);
        ruoloRepository.delete(found);
    }
}
