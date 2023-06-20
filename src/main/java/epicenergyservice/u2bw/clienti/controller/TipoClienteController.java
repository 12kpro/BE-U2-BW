package epicenergyservice.u2bw.clienti.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import epicenergyservice.u2bw.clienti.TipoCliente;
import epicenergyservice.u2bw.clienti.repositories.TipoClienteRepository;
import epicenergyservice.u2bw.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/tipocliente")
public class TipoClienteController {

    @Autowired
    private TipoClienteRepository tipoClienteRepository;

    @GetMapping
    public ResponseEntity<Page<TipoCliente>> getAllTipiCliente(Pageable pageable) {
        Page<TipoCliente> tipiCliente = tipoClienteRepository.findAll(pageable);
        return ResponseEntity.ok(tipiCliente);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoCliente> getTipoClienteById(@PathVariable UUID id) {
        TipoCliente tipoCliente = tipoClienteRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Tipo cliente non trovato con ID: " + id));
        return ResponseEntity.ok(tipoCliente);
    }

    @PostMapping
    public ResponseEntity<TipoCliente> createTipoCliente(@RequestBody TipoCliente tipoCliente) {
        TipoCliente nuovoTipoCliente = tipoClienteRepository.save(tipoCliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuovoTipoCliente);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TipoCliente> updateTipoCliente(@PathVariable UUID id, @RequestBody TipoCliente tipoCliente) {
        TipoCliente tipoClienteEsistente = tipoClienteRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Tipo cliente non trovato con ID: " + id));
        tipoCliente.setId(id);
        TipoCliente tipoClienteAggiornato = tipoClienteRepository.save(tipoCliente);
        return ResponseEntity.ok(tipoClienteAggiornato);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTipoCliente(@PathVariable UUID id) {
        TipoCliente tipoClienteEsistente = tipoClienteRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Tipo cliente non trovato con ID: " + id));
        tipoClienteRepository.delete(tipoClienteEsistente);
        return ResponseEntity.noContent().build();
    }
}