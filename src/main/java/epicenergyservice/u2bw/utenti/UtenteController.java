package epicenergyservice.u2bw.utenti;


import epicenergyservice.u2bw.exceptions.NotFoundException;
import epicenergyservice.u2bw.utenti.payloads.UtenteCreatePayload;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UtenteController {
    @Autowired
    private UtenteService utenteService;
    // testata OK
    @GetMapping("")
    public Page<Utente> getUsers(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size,
                                 @RequestParam(defaultValue = "id") String sortBy) {
        return utenteService.find(page, size, sortBy);
    }
    // testata OK
    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public Utente saveUser(@RequestBody @Validated UtenteCreatePayload body) {
        return utenteService.create(body);
    }
    // testata OK
    @GetMapping("/{userId}")
    public Utente getUser(@PathVariable UUID userId) throws Exception {
        return utenteService.findById(userId);
    }

    //Request method 'PUT' is not supported  --> testata
    @PutMapping("/{userId}")
    public Utente updateUser(@PathVariable UUID userId, @RequestBody Utente body) throws Exception {
        return utenteService.findByIdAndUpdate(userId, body);
    }
    // testata OK
    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable UUID userId) throws NotFoundException {
        utenteService.findByIdAndDelete(userId);
    }

}
