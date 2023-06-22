package epicenergyservice.u2bw.clienti.controller;



import epicenergyservice.u2bw.clienti.Cliente;
import epicenergyservice.u2bw.clienti.TipoCliente;
import epicenergyservice.u2bw.clienti.services.ClienteService;
import epicenergyservice.u2bw.clienti.services.TipoClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/tipo-cliente")
public class TipoClienteController {
    private final TipoClienteService tipoClienteService;

    @Autowired
    public TipoClienteController(TipoClienteService tipoClienteService) {
        this.tipoClienteService = tipoClienteService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoCliente> getTipoClienteById(@PathVariable UUID id) {
        Optional<TipoCliente> tipoCliente = tipoClienteService.getTipoClienteById(id);
        return tipoCliente.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<TipoCliente> getTipoClienteByNome(@PathVariable String nome) {
        Optional<TipoCliente> tipoCliente = tipoClienteService.getTipoClienteByNome(nome);
        return tipoCliente.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }



}
