package epicenergyservice.u2bw;

import epicenergyservice.u2bw.indirizzi.services.ComuniService;
import epicenergyservice.u2bw.indirizzi.services.ProvinceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class importRunner implements CommandLineRunner {

    @Autowired
    ComuniService comuniService;
    @Autowired
    ProvinceService provinceService;
    @Override
    public void run(String... args) throws Exception {
        provinceService.importCsv(false);
        comuniService.importCsv(false);
    }
}
