package epicenergyservice.u2bw;

import epicenergyservice.u2bw.exceptions.NotFoundException;
import epicenergyservice.u2bw.indirizzi.Comune;
import epicenergyservice.u2bw.indirizzi.Provincia;
import epicenergyservice.u2bw.indirizzi.repositories.ProvinceRepository;
import epicenergyservice.u2bw.indirizzi.services.ComuniService;
import epicenergyservice.u2bw.indirizzi.services.ProvinceService;
import epicenergyservice.u2bw.utenti.Ruolo;
import epicenergyservice.u2bw.utenti.Utente;
import epicenergyservice.u2bw.utenti.repositories.UtenteRepository;
import epicenergyservice.u2bw.utenti.services.RuoloService;
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
import java.util.Arrays;
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
    @Autowired
    RuoloService ruoloService;
    @Autowired
    ComuniService comuniService;
    @Autowired
    ProvinceRepository provinceRepository;

    @Autowired
    UtenteRepository utenteRepository;

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
            loadCsv();
            firstUserCreate();
        }
    }

    public void loadCsv() throws IOException {
        Reader readerComuni = new InputStreamReader(new ClassPathResource("comuni-italiani.csv").getInputStream());
        CSVParser comuni = new CSVParser(readerComuni, CSVFormat.DEFAULT.withDelimiter(';').withFirstRecordAsHeader());

        Reader readerProvince = new InputStreamReader(new ClassPathResource("province-italiane.csv").getInputStream());
        CSVParser province = new CSVParser(readerComuni, CSVFormat.DEFAULT.withDelimiter(';').withFirstRecordAsHeader());

        provinceRepository.deleteAll();
        for (CSVRecord provincia : province) {
            Optional<CSVRecord> pc = comuni.stream().filter(c -> c.get(3).contains(provincia.get(2))).findFirst();
            pc.orElseThrow(() -> new NotFoundException("Provincia non trovato!!"));
            log.info(provincia.get(2));
            log.info(pc.toString());
            //Arrays.asList(comuni).contains(provincia.get(2));
            //Provincia p = new Provincia(provincia.get(1), provincia.get(0), provincia.get(2));
            //provinceRepository.save(p);
        }

//        comuniRepository.deleteAll();
//        for (CSVRecord record : comuni) {
//            Comune c = new Comune(record.get(0),record.get(1),record.get(2), provincia);
//            comuniRepository.save(c);
//        }
//
//
//        provinceRepository.deleteAll();
//        for (CSVRecord record : csvParser) {
//            Provincia p = new Provincia(record.get(1), record.get(0), record.get(2));
//            provinceRepository.save(p);
//        }
    }
    public void firstUserCreate(){
        for (String ruolo : ruoliDefault) {
            ruoloService.create(ruolo);
        }

        Utente admin = new Utente(cognome,email,nome,bcrypt.encode(password),username);
        Ruolo ruolo = ruoloService.findNome("ADMIN");
        admin.getRuoli().add(ruolo);
        utenteRepository.save(admin);
    }
}
