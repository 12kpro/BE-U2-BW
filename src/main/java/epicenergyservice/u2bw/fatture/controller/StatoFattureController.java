package epicenergyservice.u2bw.fatture.controller;


import epicenergyservice.u2bw.exceptions.NotFoundException;
import epicenergyservice.u2bw.fatture.payloads.StatoFatturaCreatePayload;
import org.springframework.data.domain.Page;
import epicenergyservice.u2bw.fatture.StatoFattura;
import epicenergyservice.u2bw.fatture.services.StatoFatturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

@RestController
@RequestMapping("/statofatture")
@PreAuthorize("hasAuthority('ADMIN')")
public class StatoFattureController {

    @Autowired
    private StatoFatturaService statoFatturaService;

    @GetMapping("")
    public Page<StatoFattura> getStatoFattura(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size,
                                            @RequestParam(defaultValue = "id") String sortBy) {
        return statoFatturaService.find(page, size, sortBy);
    }
    @GetMapping("/{statoFatturaId}")
    public StatoFattura getStatoFattura(@PathVariable UUID statoFatturaId) throws Exception {
        return statoFatturaService.findById(statoFatturaId);
    }
    @GetMapping("/{nome}")
    public StatoFattura getStatoFatturaByNome(@PathVariable String nome) {
        return statoFatturaService.findByNome(nome);
    }
    @PostMapping("")
    @PostAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    public StatoFattura saveStatoFattura(@RequestBody @Validated StatoFatturaCreatePayload body) {
        return statoFatturaService.create(body);
    }

    @PutMapping("/{statoFatturaId}")
    @PostAuthorize("hasAuthority('ADMIN')")
    public StatoFattura updateStatoFattura(@PathVariable UUID statoFatturaId, @RequestBody StatoFattura body) throws Exception {
        return statoFatturaService.findByIdAndUpdate(statoFatturaId, body);
    }

    @DeleteMapping("/{statoFatturaId}")
    @PostAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteStatoFattura(@PathVariable UUID statoFatturaId) throws NotFoundException {
        statoFatturaService.findByIdAndDelete(statoFatturaId);
    }
    
    

//    private final StatoFatturaService statoFatturaService;
//
//    @Autowired
//    public StatoFattureController(StatoFatturaService statoFatturaService) {
//        this.statoFatturaService = statoFatturaService;
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<StatoFattura> getStatoFatturaById(@PathVariable("id") UUID id) {
//        Optional<StatoFattura> statoFattura = statoFatturaService.getStatoFatturaById(id);
//        return statoFattura.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
//                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
//    }
//
//    @PostMapping("")
//    public ResponseEntity<StatoFattura> createStatoFattura(@RequestBody @Validated StatoFatturaUpdatePayload statoFattura) {
//        StatoFattura createdStatoFattura = statoFatturaService.createStatoFattura(statoFattura);
//        return new ResponseEntity<>(createdStatoFattura, HttpStatus.CREATED);
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<StatoFattura> updateStatoFattura(@PathVariable("id") UUID id, @RequestBody @Validated StatoFatturaUpdatePayload statoFattura) {
//        Optional<StatoFattura> existingStatoFattura = statoFatturaService.getStatoFatturaById(id);
//        if (existingStatoFattura.isPresent()) {
//            statoFattura.setId(id);
//            StatoFattura updatedStatoFattura = statoFatturaService.updateStatoFattura(statoFattura);
//            return new ResponseEntity<>(updatedStatoFattura, HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteStatoFattura(@PathVariable("id") UUID id) {
//        Optional<StatoFattura> existingStatoFattura = statoFatturaService.getStatoFatturaById(id);
//        if (existingStatoFattura.isPresent()) {
//            statoFatturaService.deleteStatoFattura(id);
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        } else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }
}
