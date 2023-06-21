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

}
