package epicenergyservice.u2bw.clienti.controller;




import epicenergyservice.u2bw.clienti.TipoCliente;
import epicenergyservice.u2bw.clienti.payloads.TipoClienteCreatePayload;
import epicenergyservice.u2bw.clienti.services.TipoClienteService;
import epicenergyservice.u2bw.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import java.util.UUID;

@RestController
@RequestMapping("/tipo-cliente")
@PreAuthorize("hasAuthority('ADMIN')")
public class TipoClienteController {

    @Autowired
    private TipoClienteService tipoClienteService;

    @GetMapping("")
    public Page<TipoCliente> getTipoCliente(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size,
                                 @RequestParam(defaultValue = "id") String sortBy) {
        return tipoClienteService.find(page, size, sortBy);
    }
    @GetMapping("/{tipoId}")
    public TipoCliente getUser(@PathVariable UUID tipoId) throws Exception {
        return tipoClienteService.findById(tipoId);
    }
    @GetMapping("/{nome}")
    public TipoCliente getTipoClienteByNome(@PathVariable String nome) {
        return tipoClienteService.findByNome(nome);
    }
    @PostMapping("")
    @PostAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    public TipoCliente saveUser(@RequestBody @Validated TipoClienteCreatePayload body) {
        return tipoClienteService.create(body);
    }

    @PutMapping("/{tipoId}")
    @PostAuthorize("hasAuthority('ADMIN')")
    public TipoCliente updateUser(@PathVariable UUID tipoId, @RequestBody TipoCliente body) throws Exception {
        return tipoClienteService.findByIdAndUpdate(tipoId, body);
    }

    @DeleteMapping("/{tipoId}")
    @PostAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable UUID tipoId) throws NotFoundException {
        tipoClienteService.findByIdAndDelete(tipoId);
    }

//    private final TipoClienteService tipoClienteService;
//    private final TipoClienteRepository tipoClienteRepository;
//
//    @Autowired
//    public TipoClienteController(TipoClienteService tipoClienteService,
//                                 TipoClienteRepository tipoClienteRepository) {
//        this.tipoClienteService = tipoClienteService;
//        this.tipoClienteRepository = tipoClienteRepository;
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<TipoCliente> getTipoClienteById(@PathVariable UUID id) {
//        Optional<TipoCliente> tipoCliente = tipoClienteService.getTipoClienteById(id);
//        return tipoCliente.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
//    }
//
//    @GetMapping("/nome/{nome}")
//    public ResponseEntity<TipoCliente> getTipoClienteByNome(@PathVariable String nome) {
//        Optional<TipoCliente> tipoCliente = tipoClienteService.getTipoClienteByNome(nome);
//        return tipoCliente.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteTipoCliente(@PathVariable("id") UUID id) {
//        Optional<TipoCliente> existingTipoCliente = tipoClienteService.getTipoClienteById(id);
//        if (existingTipoCliente.isPresent()) {
//            existingTipoCliente.ifPresent(tipoClienteRepository::delete);
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        } else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }

}
