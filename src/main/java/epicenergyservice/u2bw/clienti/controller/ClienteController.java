package epicenergyservice.u2bw.clienti.controller;

import epicenergyservice.u2bw.clienti.Cliente;
import epicenergyservice.u2bw.clienti.services.ClienteService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/clienti")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

   /* @GetMapping("/{id}")
    public ResponseEntity<Cliente> getClienteById(@PathVariable UUID id) {
        Optional<Cliente> cliente = clienteService.findById(id);
        return cliente.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }*/

    @GetMapping("/nomecontatto")
    public ResponseEntity<Page<Cliente>> getClienteByNomeContatto(
            @RequestParam String nomeContatto,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Page<Cliente> result = clienteService.findByNomeContatto(nomeContatto, page, size);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/email")
    public ResponseEntity<Page<Cliente>> getClienteByEmail(
            @RequestParam String email,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Page<Cliente> result = clienteService.findByEmail(email, page, size);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/fatturato")
    public ResponseEntity<Page<Cliente>> filtraClientiPerFatturatoAnnuo(
            @RequestParam double minFatturato,
            @RequestParam double maxFatturato,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Page<Cliente> result = clienteService.filtraClientiPerFatturatoAnnuo(minFatturato, maxFatturato, page, size);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/inserimento")
    public ResponseEntity<Page<Cliente>> findByInserimento(
            @RequestParam String dataInserimento,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        LocalDate parsedDate = LocalDate.parse(dataInserimento);
        Page<Cliente> result = clienteService.findByInserimento(parsedDate, page, size);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/partname")
    public ResponseEntity<Page<Cliente>> searchByPartName(
            @RequestParam String partName,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Page<Cliente> result = clienteService.searchByPartName(partName, page, size);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/ultimocontatto")
    public ResponseEntity<Page<Cliente>> findByUltimocontatto(
            @RequestParam String ultimoContatto,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        LocalDate parsedDate = LocalDate.parse(ultimoContatto);
        Page<Cliente> result = clienteService.findByUltimocontatto(parsedDate, page, size);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/provincia")
    public ResponseEntity<Page<Cliente>> searchByProvincia(
            @RequestParam String provincia,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Page<Cliente> result = clienteService.searchByProvincia(provincia, page, size);
        return ResponseEntity.ok(result);
    }
}
