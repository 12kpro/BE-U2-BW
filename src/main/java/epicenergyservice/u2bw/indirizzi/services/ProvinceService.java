package epicenergyservice.u2bw.indirizzi.services;

import epicenergyservice.u2bw.exceptions.NotFoundException;
import epicenergyservice.u2bw.indirizzi.Provincia;
import epicenergyservice.u2bw.indirizzi.repositories.ProvinceRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
@Slf4j
@Service
public class ProvinceService {

    @Autowired
    private ProvinceRepository provinceRepository;
    public Provincia findByNome(String n) throws NotFoundException {
        return provinceRepository.findByNome(n).orElseThrow(() -> new NotFoundException("Provincia:" + n + "non trovata!!"));
    }
    public void importCsv(Boolean refresh ) throws IOException {
        try (Reader reader = new InputStreamReader(new ClassPathResource("province-italiane.csv").getInputStream())) {
            CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withDelimiter(';').withFirstRecordAsHeader());
            if( refresh || provinceRepository.count() == 0) {
                for (CSVRecord record : csvParser) {
                    Provincia p = new Provincia(record.get(1), record.get(0), record.get(2));
                    provinceRepository.save(p);
                }
            }
        }
    }

}
