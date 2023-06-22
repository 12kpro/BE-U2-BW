package epicenergyservice.u2bw.clienti.controller;

import epicenergyservice.u2bw.clienti.TipoCliente;
import epicenergyservice.u2bw.clienti.services.TipoClienteService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/tipoclienti")
public class TipoClienteController {
    private final TipoClienteService tipoClienteService;

    public TipoClienteController(TipoClienteService tipoClienteService) {
        this.tipoClienteService = tipoClienteService;
    }

    @GetMapping
    public ResponseEntity<Page<TipoCliente>> getAllTipoClienti(
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "10") int pageSize
    ) {
        Page<TipoCliente> tipoClienti = tipoClienteService.getAllTipoClienti(pageNumber, pageSize);
        return ResponseEntity.ok(tipoClienti);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Page<TipoCliente>> getTipoClienteById(
            @PathVariable UUID id,
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "10") int pageSize
    ) {
        Page<TipoCliente> tipoCliente = tipoClienteService.getTipoClienteById(id, pageNumber, pageSize);
        return ResponseEntity.ok(tipoCliente);
    }

    @PostMapping
    public ResponseEntity<TipoCliente> createTipoCliente(@RequestBody TipoCliente tipoCliente) {
        TipoCliente createdTipoCliente = tipoClienteService.createTipoCliente(tipoCliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTipoCliente);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TipoCliente> updateTipoCliente(
            @PathVariable UUID id,
            @RequestBody TipoCliente tipoCliente
    ) {
        tipoCliente.setId(id);
        TipoCliente updatedTipoCliente = tipoClienteService.updateTipoCliente(tipoCliente);
        return ResponseEntity.ok(updatedTipoCliente);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTipoCliente(@PathVariable UUID id) {
        tipoClienteService.deleteTipoCliente(id);
        return ResponseEntity.noContent().build();
    }
}