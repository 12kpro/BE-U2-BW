package epicenergyservice.u2bw.fatture.controller;

import org.springframework.data.domain.Page;
import epicenergyservice.u2bw.exceptions.NotFoundException;
import epicenergyservice.u2bw.fatture.Fattura;
import epicenergyservice.u2bw.fatture.payloads.FatturaCreatePayload;
import epicenergyservice.u2bw.fatture.services.FatturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
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
    public Page<Fattura> getFatture(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(required = false) LocalDateTime data,
            @RequestParam(required = false) Integer anno,
            @RequestParam(required = false) BigDecimal minImporto,
            @RequestParam(required = false) BigDecimal maxImporto
    ) {
        return fatturaService.getFatture(page, size, sortBy, data, anno, minImporto, maxImporto);
    }

    @GetMapping("/{fatturaId}")
    public Fattura getFattura(@PathVariable UUID fatturaId) throws NotFoundException {
        return fatturaService.getFattura(fatturaId);
    }

    @PostMapping("")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    public Fattura saveFattura(@RequestBody @Validated FatturaCreatePayload body) {
        return fatturaService.createFattura(body);
    }

    @PutMapping("/{fatturaId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Fattura updateFattura(@PathVariable UUID fatturaId, @RequestBody @Validated FatturaCreatePayload body) throws NotFoundException {
        return fatturaService.updateFattura(fatturaId, body);
    }

    @DeleteMapping("/{fatturaId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteFattura(@PathVariable UUID fatturaId) throws NotFoundException {
        fatturaService.deleteFattura(fatturaId);
    }

    @GetMapping("/cliente/{id}")
    public Page<Fattura> getFattureByClienteId(
            @PathVariable UUID id,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy
    ) {
        return fatturaService.findByClienteId(id, page, size, sortBy);
    }

    @GetMapping("/stato/{id}")
    public Page<Fattura> getFattureByStatoId(
            @PathVariable UUID id,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy
    ) {
        return fatturaService.findByStatoId(id, page, size, sortBy);
    }
}
