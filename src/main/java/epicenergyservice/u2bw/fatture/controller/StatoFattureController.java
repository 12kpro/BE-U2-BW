package epicenergyservice.u2bw.fatture.controller;

import org.springframework.data.domain.Page;
import epicenergyservice.u2bw.fatture.StatoFattura;
import epicenergyservice.u2bw.fatture.payloads.StatoFatturaUpdatePayload;
import epicenergyservice.u2bw.fatture.services.StatoFatturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/statofatture")
public class StatoFattureController {

    private final StatoFatturaService statoFatturaService;

    @Autowired
    public StatoFattureController(StatoFatturaService statoFatturaService) {
        this.statoFatturaService = statoFatturaService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<StatoFattura> getStatoFatturaById(@PathVariable("id") Long id) {
        Optional<StatoFattura> statoFattura = statoFatturaService.getStatoFatturaById(id);
        return statoFattura.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("")
    public ResponseEntity<StatoFattura> createStatoFattura(@RequestBody @Validated StatoFatturaUpdatePayload statoFattura) {
        StatoFattura createdStatoFattura = statoFatturaService.createStatoFattura(statoFattura);
        return new ResponseEntity<>(createdStatoFattura, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StatoFattura> updateStatoFattura(@PathVariable("id") Long id, @RequestBody @Validated StatoFatturaUpdatePayload statoFattura) {
        Optional<StatoFattura> existingStatoFattura = statoFatturaService.getStatoFatturaById(id);
        if (existingStatoFattura.isPresent()) {
            statoFattura.setId(id);
            StatoFattura updatedStatoFattura = statoFatturaService.updateStatoFattura(statoFattura);
            return new ResponseEntity<>(updatedStatoFattura, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStatoFattura(@PathVariable("id") Long id) {
        Optional<StatoFattura> existingStatoFattura = statoFatturaService.getStatoFatturaById(id);
        if (existingStatoFattura.isPresent()) {
            statoFatturaService.deleteStatoFattura(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
