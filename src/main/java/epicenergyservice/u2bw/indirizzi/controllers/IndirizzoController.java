package epicenergyservice.u2bw.indirizzi.controllers;

import epicenergyservice.u2bw.exceptions.NotFoundException;
import epicenergyservice.u2bw.indirizzi.Indirizzo;
import epicenergyservice.u2bw.indirizzi.payloads.IndirizzoCreatePayload;
import epicenergyservice.u2bw.indirizzi.services.IndirizzoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
@RestController
@RequestMapping("/indirizzo")
@PreAuthorize("hasAuthority('ADMIN') || hasAuthority('USER')")
public class IndirizzoController {

    @Autowired
    private IndirizzoService indirizzoService;

    @GetMapping("")
    public Page<Indirizzo> getIndirizzo(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size,
                                      @RequestParam(defaultValue = "id") String sortBy) {
        return indirizzoService.find(page, size, sortBy);
    }

    @GetMapping("/{indirizzoId}")
    public Indirizzo getIndirizzo(@PathVariable UUID indirizzoId) throws Exception {
        return indirizzoService.findById(indirizzoId);
    }

    @PostMapping("")
    @PostAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    public Indirizzo saveIndirizzo(@RequestBody @Validated IndirizzoCreatePayload body) {
        return indirizzoService.create(body);
    }

    //TODO Da Verificare
    @PutMapping("/{indirizzoId}")
    @PostAuthorize("hasAuthority('ADMIN')")
    public Indirizzo updateIndirizzo(@PathVariable UUID indirizzoId, @RequestBody IndirizzoCreatePayload body) throws Exception {
        return indirizzoService.findByIdAndUpdate(indirizzoId, body);
    }

    @DeleteMapping("/{indirizzoId}")
    @PostAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteIndirizzo(@PathVariable UUID indirizzoId) throws NotFoundException {
        indirizzoService.findByIdAndDelete(indirizzoId);
    }


}
