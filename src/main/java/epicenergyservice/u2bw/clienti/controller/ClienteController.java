package epicenergyservice.u2bw.clienti.controller;

import epicenergyservice.u2bw.clienti.Cliente;
import epicenergyservice.u2bw.clienti.payloads.ClientiCreatePayloads;
import epicenergyservice.u2bw.clienti.services.ClienteService;
import epicenergyservice.u2bw.exceptions.NotFoundException;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.UUID;
@Slf4j
@RestController
@RequestMapping("/clienti")
@PreAuthorize("hasAuthority('ADMIN') || hasAuthority('USER')")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping("")
    public Page<Cliente> getClienti(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInserimento,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataUltimoContatto,
            @RequestParam @DecimalMin("0.0") Double fatturatoAnnuale,
            @RequestParam String ragioneSociale,
            @RequestParam @Min(0) Integer provinciaSedeLegaleId
    ) {

            return clienteService.findByParams(
                    page,
                    size,
                    sortBy,
                    dataInserimento,
                    dataUltimoContatto,
                    fatturatoAnnuale,
                    ragioneSociale,
                    provinciaSedeLegaleId
                    );

    }

    @GetMapping("/{clienteId}")
    public Cliente getcliente(@PathVariable UUID clienteId) throws Exception {
        return clienteService.findById(clienteId);
    }

    @PostMapping("")
    @PostAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente savecliente(@RequestBody @Validated ClientiCreatePayloads body) {
        return clienteService.create(body);
    }

    @PutMapping("/{clienteId}")
    @PostAuthorize("hasAuthority('ADMIN')")
    public Cliente updatecliente(@PathVariable UUID clienteId, @RequestBody ClientiCreatePayloads body) throws Exception {
        return clienteService.findByIdAndUpdate(clienteId, body);
    }

    @DeleteMapping("/{clienteId}")
    @PostAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletecliente(@PathVariable UUID clienteId) throws NotFoundException {
        clienteService.findByIdAndDelete(clienteId);
    }


}
