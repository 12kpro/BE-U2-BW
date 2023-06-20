package epicenergyservice.u2bw.utenti.services;

import epicenergyservice.u2bw.exceptions.BadRequestException;
import epicenergyservice.u2bw.utenti.Ruolo;
import epicenergyservice.u2bw.utenti.Utente;
import epicenergyservice.u2bw.utenti.payloads.UtenteCreatePayload;
import epicenergyservice.u2bw.utenti.repositories.RuoloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
