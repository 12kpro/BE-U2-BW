package epicenergyservice.u2bw.indirizzi.services;

import epicenergyservice.u2bw.exceptions.BadRequestException;
import epicenergyservice.u2bw.exceptions.NotFoundException;
import epicenergyservice.u2bw.indirizzi.Comune;
import epicenergyservice.u2bw.indirizzi.Provincia;
import epicenergyservice.u2bw.indirizzi.payloads.ComuneCreatePayload;
import epicenergyservice.u2bw.indirizzi.payloads.ProvinciaCreatePayload;
import epicenergyservice.u2bw.indirizzi.repositories.ProvinceRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class ProvinceService {

    @Autowired
    private ProvinceRepository provinceRepository;

    public Provincia create(ProvinciaCreatePayload p) {
        provinceRepository.findByNome(p.getNome()).ifPresent(provincia -> {
            throw new BadRequestException("Provincia " + provincia.getNome() + " esistente!");
        });
        Integer idx = 1;
        List<Integer> usedId = provinceRepository.findAllIds();
        while (usedId.contains(idx)){
            idx++;
        }
        Provincia newProvincia = new Provincia(idx, p.getNome(),p.getSigla(),p.getRegione());
        return provinceRepository.save(newProvincia);
    }
    public Page<Provincia> find(int page, int size, String sortBy) {
        if (size < 0)
            size = 10;
        if (size > 100)
            size = 100;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));

        return provinceRepository.findAll(pageable);
    }
    public Provincia findById(Integer id) throws NotFoundException {
        return provinceRepository.findById(id).orElseThrow(() -> new NotFoundException("Provincia con Id:" + id + "non trovato!!"));
    }

    public Provincia update(Provincia p) throws NotFoundException {
        Provincia found = this.findById(p.getId());
        return provinceRepository.save(p);
    }
    public void findByIdAndDelete(Integer id) throws NotFoundException {
        Provincia found = this.findById(id);
        provinceRepository.delete(found);
    }

    public void deleteAll()  {
        provinceRepository.deleteAll();
    }
}
