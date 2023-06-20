package epicenergyservice.u2bw.clienti.services;

import epicenergyservice.u2bw.clienti.TipoCliente;
import epicenergyservice.u2bw.clienti.repositories.TipoClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class TipoClienteService {
    @Autowired
    private TipoClienteRepository tipoClienteRepository;

    public List<TipoCliente> getAllTipiCliente() {
        return tipoClienteRepository.findAll();
    }
}
