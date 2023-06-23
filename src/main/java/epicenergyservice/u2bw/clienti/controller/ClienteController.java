package epicenergyservice.u2bw.clienti.controller;

import epicenergyservice.u2bw.clienti.Cliente;
import epicenergyservice.u2bw.clienti.payloads.ClienteGetPayload;
import epicenergyservice.u2bw.clienti.payloads.ClientiCreatePayloads;
import epicenergyservice.u2bw.clienti.services.ClienteService;
import epicenergyservice.u2bw.exceptions.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
@Slf4j
@RestController
@RequestMapping("/clienti")
@PreAuthorize("hasAuthority('ADMIN') || hasAuthority('USER')")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping("")
    public Page<Cliente> getClienti(@RequestBody(required = false) @Validated ClienteGetPayload body, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size,
                                    @RequestParam(defaultValue = "id") String sortBy) {
        Page<Cliente> res = null;
        if (body == null){
            res = clienteService.find(page, size, sortBy);
        }else{
            log.info(body.toString());
            res = clienteService.findQuery(body, page, size, sortBy);
            //TODO implementare if per query custom
//            findByFatturatoAnnuale
//            findByDataInserimento
//            findByRagioneSocialeContainsIgnoreCase
//            findByDataUltimoContatto
//            findByIndirizzoSedeLegale_Comune_Provincia
        }
        return res;
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
    public Cliente updatecliente(@PathVariable UUID clienteId, @RequestBody Cliente body) throws Exception {
        return clienteService.findByIdAndUpdate(clienteId, body);
    }

    @DeleteMapping("/{clienteId}")
    @PostAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletecliente(@PathVariable UUID clienteId) throws NotFoundException {
        clienteService.findByIdAndDelete(clienteId);
    }








//    private final ClienteService clienteService;
//
//    public ClienteController(ClienteService clienteService) {
//        this.clienteService = clienteService;
//    }
//
//   /* @GetMapping("/{id}")
//    public ResponseEntity<Cliente> getClienteById(@PathVariable UUID id) {
//        Optional<Cliente> cliente = clienteService.findById(id);
//        return cliente.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
//    }*/
//
//    @GetMapping("/nomecontatto")
//    public ResponseEntity<Page<Cliente>> getClienteByNomeContatto(
//            @RequestParam String nomeContatto,
//            @RequestParam(defaultValue = "0") int page,
//            @RequestParam(defaultValue = "10") int size
//    ) {
//        Page<Cliente> result = clienteService.findByNomeContatto(nomeContatto, page, size);
//        return ResponseEntity.ok(result);
//    }
//
//    @GetMapping("/email")
//    public ResponseEntity<Page<Cliente>> getClienteByEmail(
//            @RequestParam String email,
//            @RequestParam(defaultValue = "0") int page,
//            @RequestParam(defaultValue = "10") int size
//    ) {
//        Page<Cliente> result = clienteService.findByEmail(email, page, size);
//        return ResponseEntity.ok(result);
//    }
//
//    @GetMapping("/fatturato")
//    public ResponseEntity<Page<Cliente>> filtraClientiPerFatturatoAnnuo(
//            @RequestParam Double fatturato,
//
//            @RequestParam(defaultValue = "0") int page,
//            @RequestParam(defaultValue = "10") int size
//    ) {
//        Page<Cliente> result = clienteService.filtraClientiPerFatturatoAnnuo(fatturato, page, size);
//        return ResponseEntity.ok(result);
//    }
//
//    @GetMapping("/inserimento")
//    public ResponseEntity<Page<Cliente>> findByInserimento(
//            @RequestParam String dataInserimento,
//            @RequestParam(defaultValue = "0") int page,
//            @RequestParam(defaultValue = "10") int size
//    ) {
//        LocalDate parsedDate = LocalDate.parse(dataInserimento);
//        Page<Cliente> result = clienteService.findByInserimento(parsedDate, page, size);
//        return ResponseEntity.ok(result);
//    }
//
//    @GetMapping("/partname")
//    public ResponseEntity<Page<Cliente>> searchByPartName(
//            @RequestParam String partName,
//            @RequestParam(defaultValue = "0") int page,
//            @RequestParam(defaultValue = "10") int size
//    ) {
//        Page<Cliente> result = clienteService.searchByPartName(partName, page, size);
//        return ResponseEntity.ok(result);
//    }
//
//    @GetMapping("/ultimocontatto")
//    public ResponseEntity<Cliente> findByUltimocontatto(
//            @RequestParam String ultimoContatto,
//            @RequestParam(defaultValue = "0") int page,
//            @RequestParam(defaultValue = "10") int size
//    ) {
//        LocalDate parsedDate = LocalDate.parse(ultimoContatto);
//        Cliente result = clienteService.findByUltimocontatto(parsedDate);
//        return ResponseEntity.ok(result);
//    }
//
//    @GetMapping("/provincia")
//    public ResponseEntity<Page<Cliente>> searchByProvincia(
//            @RequestParam Integer provincia,
//            @RequestParam(defaultValue = "0") int page,
//            @RequestParam(defaultValue = "10") int size
//    ) {
//        Page<Cliente> result = clienteService.searchByProvincia(provincia, page, size);
//        return ResponseEntity.ok(result);
//    }
}
