package epicenergyservice.u2bw.indirizzi.controllers;

import epicenergyservice.u2bw.exceptions.NotFoundException;
import epicenergyservice.u2bw.indirizzi.payloads.ImportSuccessPayload;
import epicenergyservice.u2bw.indirizzi.services.ComuniService;
import epicenergyservice.u2bw.indirizzi.services.ProvinceService;
import epicenergyservice.u2bw.utenti.Utente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/import")
//@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
public class CsvImportController {
    @Autowired
    ComuniService comuniService;
	@Autowired
    ProvinceService provincieService;
    @GetMapping("/comuni")
    public ResponseEntity<ImportSuccessPayload> importComuni() throws Exception {
        //TODO sostituire parametro false con queryparam
        comuniService.importCsv(false);
        return new ResponseEntity<>(new ImportSuccessPayload("file caricato con successo"), HttpStatus.OK);
    }
    @GetMapping("/province")
    public ResponseEntity<ImportSuccessPayload> importProvince() throws Exception {
        //TODO sostituire parametro false con queryparam
        provincieService.importCsv(false);
        return new ResponseEntity<>(new ImportSuccessPayload("file caricato con successo"), HttpStatus.OK);
    }
}
