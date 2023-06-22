package epicenergyservice.u2bw.indirizzi.controllers;

import epicenergyservice.u2bw.exceptions.NotFoundException;
import epicenergyservice.u2bw.indirizzi.Comune;
import epicenergyservice.u2bw.indirizzi.payloads.ComuneCreatePayload;
import epicenergyservice.u2bw.indirizzi.services.ComuniService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
@RestController
@RequestMapping("/comuni")
@PreAuthorize("hasAuthority('ADMIN')")
public class ComuniController {

    @Autowired
    ComuniService comuniService;

    @GetMapping("")
    public Page<Comune> getComuni(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size,
                                  @RequestParam(defaultValue = "id") String sortBy) {
        return comuniService.find(page, size, sortBy);
    }
    @GetMapping("/{comuneId}")
    public Comune getComuni(@PathVariable UUID comuneId) throws Exception {
        return comuniService.findById(comuneId);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public Comune saveComuni(@RequestBody ComuneCreatePayload body) {
        return comuniService.create(body);
    }

    @PutMapping("")
    public Comune updateComuni(@RequestBody Comune body) throws Exception {
        return comuniService.update(body);
    }

    @DeleteMapping("/{comuneId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteComuni(@PathVariable UUID comuneId) throws NotFoundException {
        comuniService.findByIdAndDelete(comuneId);
    }
}
