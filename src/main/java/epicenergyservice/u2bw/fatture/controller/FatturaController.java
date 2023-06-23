package epicenergyservice.u2bw.fatture.controller;

import epicenergyservice.u2bw.fatture.payloads.FatturaGetPayload;
import epicenergyservice.u2bw.fatture.payloads.FatturaUpdatePayload;
import org.springframework.data.domain.Page;
import epicenergyservice.u2bw.exceptions.NotFoundException;
import epicenergyservice.u2bw.fatture.Fattura;
import epicenergyservice.u2bw.fatture.payloads.FatturaCreatePayload;
import epicenergyservice.u2bw.fatture.services.FatturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

@RestController
@RequestMapping("/fatture")
@PreAuthorize("hasAuthority('ADMIN') || hasAuthority('USER')")
public class FatturaController {

    private final FatturaService fatturaService;

    @Autowired
    public FatturaController(FatturaService fatturaService) {
        this.fatturaService = fatturaService;
    }
    @GetMapping("")
    public Page<Fattura> getFattura(@RequestBody(required = false) @Validated FatturaGetPayload body, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size,
                                    @RequestParam(defaultValue = "id") String sortBy) {
        Page<Fattura> res = null;
        if (body == null){
            res = fatturaService.find(page, size, sortBy);
        }else{
            res = fatturaService.findByParams(body, page, size, sortBy);
        }
        return res;
    }


    @GetMapping("/{fatturaId}")
    public Fattura getFattura(@PathVariable UUID fatturaId) throws NotFoundException {
        return fatturaService.findById(fatturaId);
    }

    @PostMapping("")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    public Fattura saveFattura(@RequestBody @Validated FatturaCreatePayload body) {
        return fatturaService.create(body);
    }

    @PutMapping("/{fatturaId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Fattura updateFattura(@PathVariable UUID fatturaId, @RequestBody @Validated FatturaUpdatePayload body) throws NotFoundException {
        return fatturaService.findByIdAndUpdate(fatturaId, body);
    }

    @DeleteMapping("/{fatturaId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteFattura(@PathVariable UUID fatturaId) throws NotFoundException {
        fatturaService.findByIdAndDelete(fatturaId);
    }
}
