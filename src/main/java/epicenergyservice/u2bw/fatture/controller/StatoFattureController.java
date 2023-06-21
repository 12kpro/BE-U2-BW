package epicenergyservice.u2bw.fatture.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import epicenergyservice.u2bw.fatture.StatoFattura;
import epicenergyservice.u2bw.fatture.services.StatoFatturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

//@RestController
@RequestMapping("/statofatture")
public class StatoFattureController {
    private final StatoFatturaService tipoFatturaService;
    private final Pageable pageable;

    @Autowired
    public StatoFattureController(StatoFatturaService statoFatturaService, Pageable pageable) {
        this.tipoFatturaService = statoFatturaService;
        this.pageable = pageable;
    }

   /* @GetMapping
    public ResponseEntity<Page<StatoFattura>> getAllStatiFatture() {
        Page<StatoFattura> statoFatture = tipoFatturaService.getAllStatiFattura(pageable);
        return ResponseEntity.ok(statoFatture);
    }*/

    @GetMapping("/{id}")
    public ResponseEntity<StatoFattura> getStatoFatturaById(@PathVariable("id") Long id) {
        Optional<StatoFattura> statoFattura = tipoFatturaService.getStatoFatturaById(id);
        return statoFattura.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<StatoFattura> createStatoFattura(@RequestBody StatoFattura statoFattura) {
        StatoFattura createdStatoFattura = tipoFatturaService.createStatoFattura(statoFattura);
        return new ResponseEntity<>(createdStatoFattura, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StatoFattura> updateStatoFattura(@PathVariable("id") Long id, @RequestBody StatoFattura statoFattura) {
        Optional<StatoFattura> existingStatoFattura = tipoFatturaService.getStatoFatturaById(id);
        if (existingStatoFattura.isPresent()) {
            statoFattura.setId(id);
            StatoFattura updatedStatoFattura = tipoFatturaService.updateStatoFattura(statoFattura);
            return new ResponseEntity<>(updatedStatoFattura, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStatoFattura(@PathVariable("id") Long id) {
        Optional<StatoFattura> existingStatoFattura = tipoFatturaService.getStatoFatturaById(id);
        if (existingStatoFattura.isPresent()) {
            tipoFatturaService.deleteStatoFattura(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
