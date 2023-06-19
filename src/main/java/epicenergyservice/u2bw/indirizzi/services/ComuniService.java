package epicenergyservice.u2bw.indirizzi.services;

import epicenergyservice.u2bw.indirizzi.Comune;
import epicenergyservice.u2bw.indirizzi.repositories.ComuniRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
@Slf4j
@Service
public class ComuniService {

    @Autowired
    private ComuniRepository comuniRepository;

    public void importCsv(String file) throws IOException {
        if (comuniRepository.count() == 0 ){
            try (Reader reader = Files.newBufferedReader(Paths.get(file))) {
                CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader());
                for (CSVRecord record : csvParser) {
                    log.info(record.toString());
                    Comune c = new Comune();
//                User user = new User();
//                user.setUsername(record.get("username"));
//                user.setPhoneNumber(record.get("phonenumber"));
//                user.setAddress(record.get("address"));
//                userRepository.save(user);
                }
            }
        }
    }

}
