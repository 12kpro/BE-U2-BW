package epicenergyservice.u2bw.utenti.controllers;


import epicenergyservice.u2bw.exceptions.NotFoundException;
import epicenergyservice.u2bw.utenti.Utente;
import epicenergyservice.u2bw.utenti.services.UtenteService;
import epicenergyservice.u2bw.utenti.payloads.UtenteCreatePayload;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/utenti")
@PreAuthorize("hasAuthority('ADMIN') || hasAuthority('USER')")

// TODO Verificare il funzionamento per utenti con più ruoli
public class UtenteController {
    @Autowired
    private UtenteService utenteService;

    @GetMapping("")
    public Page<Utente> getUsers(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size,
                                 @RequestParam(defaultValue = "id") String sortBy) {
        return utenteService.find(page, size, sortBy);
    }
    @GetMapping("/{userId}")
    public Utente getUser(@PathVariable UUID userId) throws Exception {
        return utenteService.findById(userId);
    }

    @PostMapping("")
    @PostAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    public Utente saveUser(@RequestBody @Validated UtenteCreatePayload body) {
        return utenteService.create(body);
    }

    @PutMapping("/{userId}")
    @PostAuthorize("hasAuthority('ADMIN')")
    public Utente updateUser(@PathVariable UUID userId, @RequestBody Utente body) throws Exception {
        return utenteService.findByIdAndUpdate(userId, body);
    }

    @DeleteMapping("/{userId}")
    @PostAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable UUID userId) throws NotFoundException {
        utenteService.findByIdAndDelete(userId);
    }

}
