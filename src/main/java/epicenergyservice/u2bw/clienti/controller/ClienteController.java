package epicenergyservice.u2bw.clienti.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import epicenergyservice.u2bw.clienti.Cliente;
import epicenergyservice.u2bw.clienti.repositories.ClienteRepository;
import epicenergyservice.u2bw.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/clienti")
public class ClienteController {
    @Autowired
    private ClienteRepository clienteRepository;

    @GetMapping
    public ResponseEntity<Page<Cliente>> getAllClienti(Pageable pageable) {
        Page<Cliente> clienti = clienteRepository.findAll(pageable);
        return ResponseEntity.ok(clienti);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> getClienteById(@PathVariable UUID id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Cliente non trovato con ID: " + id));
        return ResponseEntity.ok(cliente);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Cliente> createCliente(@RequestBody Cliente cliente) {
        Cliente nuovoCliente = clienteRepository.save(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuovoCliente);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Cliente> updateCliente(@PathVariable UUID id, @RequestBody Cliente cliente) {
        Cliente clienteEsistente = clienteRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Cliente non trovato con ID: " + id));
        cliente.setId(id);
        Cliente clienteAggiornato = clienteRepository.save(cliente);
        return ResponseEntity.ok(clienteAggiornato);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteCliente(@PathVariable UUID id) {
        Cliente clienteEsistente = clienteRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Cliente non trovato con ID: " + id));
        clienteRepository.delete(clienteEsistente);
        return ResponseEntity.noContent().build();
    }
}
