package epicenergyservice.u2bw.fatture.controller;

import org.springframework.data.domain.Page;
import epicenergyservice.u2bw.exceptions.NotFoundException;
import epicenergyservice.u2bw.fatture.Fattura;
import epicenergyservice.u2bw.fatture.services.FatturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

//@RestController
@RequestMapping("/fatture")
@PreAuthorize("hasRole('ADMIN') or hasRole('Fattura')")
public class FatturaController {

//    Deve essere possibile filtrare le fatture per
// TODO   Data
// TODO   Anno
// TODO   Range di importi

    @Autowired
    private FatturaService fatturaService;

    @GetMapping("")
    public Page<Fattura> getFattura(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size,
                                 @RequestParam(defaultValue = "id") String sortBy) {
        return fatturaService.find(page, size, sortBy);
    }

    @GetMapping("/{fatturaId}")
    public Fattura getFattura(@PathVariable UUID fatturaId) throws Exception {
        return fatturaService.findById(fatturaId);
    }

    @PostMapping("")
    @PostAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    public Fattura saveFattura(@RequestBody @Validated FatturaCreatePayload body) {
        return fatturaService.create(body);
    }

    @PutMapping("/{fatturaId}")
    @PostAuthorize("hasRole('ADMIN')")
    public Fattura updateFattura(@PathVariable UUID fatturaId, @RequestBody Fattura body) throws Exception {
        return fatturaService.findByIdAndUpdate(fatturaId, body);
    }

    @DeleteMapping("/{fatturaId}")
    @PostAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteFattura(@PathVariable UUID fatturaId) throws NotFoundException {
        fatturaService.findByIdAndDelete(fatturaId);
    }



    ///EXTRA
    @GetMapping("/cliente/{id}")
    public Page<Fattura> getFatturaByClienteId(@PathVariable UUID id, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size,
                                               @RequestParam(defaultValue = "id") String sortBy) {
        return fatturaService.findByClienteId(id, page, size, sortBy);
    }

    @GetMapping("/stato/{id}")
    public Page<Fattura> getFatturaByStatoId(@PathVariable UUID id, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size,
                                             @RequestParam(defaultValue = "id") String sortBy) {
        return fatturaService.findByStatoId(id, page, size, sortBy);
    }

}

