package epicenergyservice.u2bw.indirizzi.controllers;

import epicenergyservice.u2bw.exceptions.NotFoundException;
import epicenergyservice.u2bw.indirizzi.Comune;
import epicenergyservice.u2bw.indirizzi.Provincia;
import epicenergyservice.u2bw.indirizzi.payloads.ComuneCreatePayload;
import epicenergyservice.u2bw.indirizzi.payloads.ProvinciaCreatePayload;
import epicenergyservice.u2bw.indirizzi.services.ComuniService;
import epicenergyservice.u2bw.indirizzi.services.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/province")
@PreAuthorize("hasRole('ADMIN')")
public class ProvinceController {

    @Autowired
    ProvinceService provinceService;
    @GetMapping("")
    public Page<Provincia> getProvince(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size,
                                     @RequestParam(defaultValue = "id") String sortBy) {
        return provinceService.find(page, size, sortBy);
    }
    @GetMapping("/{provinciaId}")
    public Provincia getProvincia(@PathVariable Integer provinciaId) throws Exception {
        return provinceService.findById(provinciaId);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public Provincia saveProvincia(@RequestBody ProvinciaCreatePayload body) {
        return provinceService.create(body);
    }

    @PutMapping("")
    public Provincia updateProvincia( @RequestBody Provincia body) throws Exception {
        return provinceService.update( body);
    }

    @DeleteMapping("/{provinciaId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProvincia(@PathVariable Integer provinciaId) throws NotFoundException {
        provinceService.findByIdAndDelete(provinciaId);
    }
}
