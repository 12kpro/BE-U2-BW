package epicenergyservice.u2bw.indirizzi.services;

import epicenergyservice.u2bw.indirizzi.Comune;
import epicenergyservice.u2bw.indirizzi.Provincia;
import epicenergyservice.u2bw.indirizzi.repositories.ComuniRepository;
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
public class ComuniService {
    @Autowired
    private ComuniRepository comuniRepository;
    @Autowired
    private ProvinceService provinceService;
    public void importCsv(Boolean refresh) throws IOException {
            try ( Reader reader = new InputStreamReader(new ClassPathResource("comuni-italiani.csv").getInputStream())) {
                CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withDelimiter(';').withFirstRecordAsHeader());
                if(refresh || comuniRepository.count() == 0) {
                    for (CSVRecord record : csvParser) {
                        Provincia provincia = provinceService.findByNome(record.get(3));
                        Comune c = new Comune(record.get(2), provincia);
                        comuniRepository.save(c);
                    }
                }
            }
    }

}
