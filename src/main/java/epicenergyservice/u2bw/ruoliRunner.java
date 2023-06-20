package epicenergyservice.u2bw;

import epicenergyservice.u2bw.indirizzi.services.ComuniService;
import epicenergyservice.u2bw.indirizzi.services.ProvinceService;
import epicenergyservice.u2bw.utenti.services.RuoloService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class ruoliRunner implements CommandLineRunner {

    @Autowired
    RuoloService ruoloService;

    @Override
    public void run(String... args) throws Exception {
        String[] ruoliDefault = new String[]{"USER","ADMIN"};
        for (String ruolo : ruoliDefault) {
            ruoloService.create(ruolo);
        }

    }
}
