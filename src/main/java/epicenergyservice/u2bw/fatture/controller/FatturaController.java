package epicenergyservice.u2bw.fatture.controller;

import epicenergyservice.u2bw.fatture.payloads.FatturaUpdatePayload;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.DecimalMin;
import org.springframework.data.domain.Page;
import epicenergyservice.u2bw.exceptions.NotFoundException;
import epicenergyservice.u2bw.fatture.Fattura;
import epicenergyservice.u2bw.fatture.payloads.FatturaCreatePayload;
import epicenergyservice.u2bw.fatture.services.FatturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
    public Page<Fattura> getFattura(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDateTime data, //TODO fix format for local date time
            @RequestParam Integer anno,
            @RequestParam @DecimalMin("0.0") Double minImporto,
            @RequestParam @DecimalMin("0.0") Double maxImporto,
            @RequestParam UUID clienteId,
            @RequestParam UUID statoId
    ) {

        return fatturaService.findByParams(page,
        size,
        sortBy,
        data,
        anno,
        minImporto,
        maxImporto,
        clienteId,
        statoId);
    }
    @AssertTrue(message = "If minImporto is not null, maxImporto must also not be null")
    private boolean isMaxImportoValid(Double minImporto, Double maxImporto) {
        return minImporto == null || maxImporto != null && maxImporto > minImporto;
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
