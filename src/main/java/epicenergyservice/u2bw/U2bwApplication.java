package epicenergyservice.u2bw;

import epicenergyservice.u2bw.indirizzi.services.ComuniService;
import epicenergyservice.u2bw.indirizzi.services.ProvincieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.io.IOException;

@SpringBootApplication
public class U2bwApplication {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(U2bwApplication.class, args);
//
//		try {
//			comuniService.importCsv("comuni-italiani.csv");
//		} catch (IOException e) {
//			throw new RuntimeException(e);
//		}


	}

}
