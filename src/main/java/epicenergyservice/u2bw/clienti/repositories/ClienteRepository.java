package epicenergyservice.u2bw.clienti.repositories;

import epicenergyservice.u2bw.clienti.Cliente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ClienteRepository extends JpaRepository {
    Page<Cliente> findById(UUID id);

    Page<Cliente> findByNomeContatto(String nomeContatto, Pageable pageable);

    Page<Cliente> findByEmail(String email, Pageable pageable);

    Page<Cliente> filtraClientiPerFatturatoAnnuo(double minFatturato, double maxFatturato, Pageable pageable);

    Page<Cliente> findByInserimento(LocalDate dataInserimento, Pageable pageable);

    Page<Cliente> searchByPartName(String partName, Pageable pageable);

    Page<Cliente> findByUltimocontatto(LocalDate ultimoContatto, Pageable pageable);

    Page<Cliente> searchByProvincia(String provincia, Pageable pageable);
}




