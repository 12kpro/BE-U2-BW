package epicenergyservice.u2bw.utenti.controllers;

import epicenergyservice.u2bw.exceptions.NotFoundException;
import epicenergyservice.u2bw.utenti.Ruolo;
import epicenergyservice.u2bw.utenti.Utente;
import epicenergyservice.u2bw.utenti.payloads.UtenteCreatePayload;
import epicenergyservice.u2bw.utenti.services.RuoloService;
import epicenergyservice.u2bw.utenti.services.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/ruoli")
@PreAuthorize("hasAuthority('ADMIN')")
public class RuoloController {

    @Autowired
    private RuoloService ruoloService;

    @GetMapping("")
    public Page<Ruolo> getRuoli(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size,
                                @RequestParam(defaultValue = "id") String sortBy) {
        return ruoloService.find(page, size, sortBy);
    }
    @GetMapping("/{ruoloId}")
    public Ruolo getRuolo(@PathVariable UUID userId) throws Exception {
        return ruoloService.findById(userId);
    }

    @PostMapping("/ruoloNome")
    @ResponseStatus(HttpStatus.CREATED)
    public Ruolo saveRuolo(@PathVariable String ruoloNome) {
        return ruoloService.create(ruoloNome);
    }

    @PutMapping("/{ruoloId}")
    public Ruolo updateRuolo(@PathVariable UUID ruoloId, @RequestBody Ruolo body) throws Exception {
        return ruoloService.findByIdAndUpdate(ruoloId, body);
    }

    @DeleteMapping("/{ruoloId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteRuolo(@PathVariable UUID ruoloId) throws NotFoundException {
        ruoloService.findByIdAndDelete(ruoloId);
    }
}
