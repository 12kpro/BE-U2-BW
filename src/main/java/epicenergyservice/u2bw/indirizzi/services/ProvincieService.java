package epicenergyservice.u2bw.indirizzi.services;

import epicenergyservice.u2bw.indirizzi.Provincia;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
@Slf4j
@Service
public class ProvincieService {

    @Autowired
    private ProvincieService provincieService;

    public void importCsv(MultipartFile file) throws IOException {
        try (Reader reader = new InputStreamReader(file.getInputStream())) {
            CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader());
            for (CSVRecord record : csvParser) {
                log.info(record.toString());
                Provincia p = new Provincia();
            }
        }
    }

}
