package epicenergyservice.u2bw.fatture.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import epicenergyservice.u2bw.exceptions.NotFoundException;
import epicenergyservice.u2bw.fatture.Fattura;
import epicenergyservice.u2bw.fatture.services.FatturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/fatture")
public class FatturaController {

    private final FatturaService fatturaService;
    private final Pageable pageable;

    @Autowired
    public FatturaController(FatturaService fatturaService, Pageable pageable) {
        this.fatturaService = fatturaService;
        this.pageable = pageable;
    }


    /*@GetMapping
    public ResponseEntity<Page<Fattura>> getAllFatture(Pageable pageable) {
        Page<Fattura> fatture = fatturaService.getAllFattura(pageable);
        return ResponseEntity.ok(fatture);
    }*/


    @GetMapping("/{id}")
    public ResponseEntity<Optional<Fattura>> getFatturaById(@PathVariable UUID id) {
        Optional<Fattura> fattura = fatturaService.getFatturaById(id);
        if (fattura != null) {
            return ResponseEntity.ok(fattura);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /*@PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Fattura> createFattura(@RequestBody Fattura fattura) {
        Fattura createdFattura = fatturaService.createFattura(fattura);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdFattura);
    }*/

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Fattura> updateFattura(@PathVariable UUID id, @RequestBody Fattura fattura) {
        Fattura updatedFattura = fatturaService.updateFattura(id, fattura);
        if (updatedFattura != null) {
            return ResponseEntity.ok(updatedFattura);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteFattura(@PathVariable UUID id) {
        boolean deleted = fatturaService.deleteFattura(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/numero/{numeroFattura}")
    public ResponseEntity<Fattura> getFatturaPerNumero(@PathVariable int numeroFattura) {
        Fattura fattura = fatturaService.getFatturaPerNumero(numeroFattura)
                .orElseThrow(() -> new NotFoundException("Fattura non trovata"));

        return new ResponseEntity<>(fattura, HttpStatus.OK);
    }
}

