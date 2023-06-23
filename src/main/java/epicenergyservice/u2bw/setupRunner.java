package epicenergyservice.u2bw;

import epicenergyservice.u2bw.clienti.TipoCliente;
import epicenergyservice.u2bw.clienti.repositories.TipoClienteRepository;
import epicenergyservice.u2bw.fatture.StatoFattura;
import epicenergyservice.u2bw.fatture.repositories.StatoFatturaRepository;
import epicenergyservice.u2bw.indirizzi.Comune;
import epicenergyservice.u2bw.indirizzi.Provincia;
import epicenergyservice.u2bw.indirizzi.repositories.ComuniRepository;
import epicenergyservice.u2bw.indirizzi.repositories.ProvinceRepository;
import epicenergyservice.u2bw.utenti.Ruolo;
import epicenergyservice.u2bw.utenti.Utente;
import epicenergyservice.u2bw.utenti.repositories.RuoloRepository;
import epicenergyservice.u2bw.utenti.repositories.UtenteRepository;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Component
public class setupRunner implements CommandLineRunner {
    private Boolean firstRun;
    private String nome;
    private String cognome;
    private String username;
    private String email;
    private String password;

    private String[] ruoliDefault = new String[]{"USER","ADMIN"};
    private String[] tipoClienteDefault = new String[]{"SAS","SRL","SPA","SS","PA"};
    private String[] statoFattureDefault = new String[]{"PAGATO","NON_PAGATO"};
    @Autowired
    RuoloRepository ruoloRepository;
    @Autowired
    ComuniRepository comuniRepository;
    @Autowired
    ProvinceRepository provinceRepository;

    @Autowired
    UtenteRepository utenteRepository;
    @Autowired
    TipoClienteRepository tipoClienteRepository;
    @Autowired
    StatoFatturaRepository statoFatturaRepository;
    @Autowired
    private PasswordEncoder bcrypt;
    @Value("${firstrun}")
    public void setFirstrun(Boolean firstrun) {
        this.firstRun = firstrun;
    }
    @Value("${admin.nome}")
    public void setAdminNome(String s) {
        this.nome = s;
    }
    @Value("${admin.cognome}")
    public void setAdminCognome(String s) {
        this.cognome = s;
    }
    @Value("${admin.username}")
    public void setAdminUserName(String s) {
        this.username = s;
    }
    @Value("${admin.email}")
    public void setAdminEmail(String s) {
        this.email = s;
    }
    @Value("${admin.password}")
    public void setAdminPassword(String s) {
        this.password = s;
    }
    @Override
    public void run(String... args) throws Exception {
        if (firstRun) {
            loadDefault();
            loadCsv();
            firstUserCreate();
        }
    }

    public void loadCsv() throws IOException {
        List<String> provinceNotFound = new ArrayList<>();
        List<String> comuniNotFound = new ArrayList<>();
        List<String> codiciComuniNotValid = new ArrayList<>();
        Reader readerComuni = new InputStreamReader(new ClassPathResource("comuni-italiani.csv").getInputStream());
        CSVParser comuni = new CSVParser(readerComuni, CSVFormat.DEFAULT.withDelimiter(';').withFirstRecordAsHeader());

        for (CSVRecord comune : comuni) {

            Reader readerProvince = new InputStreamReader(new ClassPathResource("province-italiane.csv").getInputStream());
            CSVParser province = new CSVParser(readerProvince, CSVFormat.DEFAULT.withDelimiter(';').withFirstRecordAsHeader());

            Optional<Provincia> provincia = Optional.ofNullable(provinceRepository.findByNome(comune.get(3))
                    .orElseGet(() -> {
                Optional<CSVRecord> result = province.stream()
                        .filter(p -> p.get(1).equals(comune.get(3)))
                        .findFirst();
                //log.info("result" + result.toString());
                if (result.isPresent()) {
                    CSVRecord r = result.get();
                    Provincia p = new Provincia(Integer.parseInt(comune.get(0)), r.get(1), r.get(0), r.get(2));
                    return provinceRepository.save(p);
                } else {
                    return null;
                }
            })
            );

            if(provincia.isPresent()){
                if(comune.get(1).contains("#")){
                    if(!codiciComuniNotValid.contains(comune.get(2))){
                        codiciComuniNotValid.add(comune.get(2));
                    }
                }else{
                    Optional<Comune> comuneDb = comuniRepository.findByCodComuneAndProvincia(comune.get(1),provincia.get());
                    if(!comuneDb.isPresent()){
                        comuniRepository.save(new Comune( comune.get(1), comune.get(2), provincia.get()));
                    }
                }
            }else{
                if(!provinceNotFound.contains(comune.get(3))){
                    provinceNotFound.add(comune.get(3));
                }
                if(!comuniNotFound.contains(comune.get(2))){
                    comuniNotFound.add(comune.get(2));
                }
            }
        }
        log.info("Lista Province non trovate in province-italiane.csv");
        log.info(provinceNotFound.toString());
        log.info("Lista Comuni non inseriti verificare corrispondenza province in province-italiane.csv");
        log.info(comuniNotFound.toString());
        log.info("Lista comuni con codice comune non valido");
        log.info(codiciComuniNotValid.toString());
    }
    public void firstUserCreate(){
        //TODO nick prende un valore inesistente nei file properties e env
        Optional<Utente> found = utenteRepository.findByEmail(email);
        Utente admin = new Utente(cognome,email,nome,bcrypt.encode(password),username);
        log.info(username);
        Optional<Ruolo> ruolo = ruoloRepository.findByNome("ADMIN");
        if (ruolo.isPresent() && !found.isPresent()){
            log.info(ruolo.get().toString());

            admin.getRuoli().add(ruolo.get());
            log.info(admin.toString());
            utenteRepository.save(admin);
        }
    }

    public void loadDefault(){
        if(ruoloRepository.count() == 0) {
            for (String ruolo : ruoliDefault) {
                ruoloRepository.save(new Ruolo(ruolo));
            }
        }
        if(statoFatturaRepository.count() == 0) {
            for (String stato : statoFattureDefault) {
                statoFatturaRepository.save(new StatoFattura(stato));
            }
        }
        if(tipoClienteRepository.count() == 0) {
            for (String tipo : tipoClienteDefault) {
                tipoClienteRepository.save(new TipoCliente(tipo));
            }
        }
    }
}
