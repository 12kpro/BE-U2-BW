package epicenergyservice.u2bw.clienti.repositories;

import epicenergyservice.u2bw.clienti.Cliente;
import epicenergyservice.u2bw.clienti.TipoCliente;
import epicenergyservice.u2bw.indirizzi.Provincia;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, UUID> {

    Page<Cliente> findByNomeContatto(String nomeContatto, Pageable pageable);
    Optional<Cliente> findByEmail(String email);
    Page<Cliente> findByFatturatoAnnuale(Double fatturatoAnnuale, Pageable pageable);
    Page<Cliente> findByDataInserimento(LocalDate dataInserimento, Pageable pageable);
    Page<Cliente> findByRagioneSocialeContainsIgnoreCase(String ragioneSociale, Pageable pageable);
    Page<Cliente> findByDataUltimoContatto(LocalDate dataUltimoContatto, Pageable pageable);
    Page<Cliente> findByIndirizzoSedeLegale_Comune_Provincia(Provincia provincia, Pageable pageable);

    Page<Cliente> findByDataInserimentoAndRagioneSocialeIgnoreCaseAndFatturatoAnnualeAndDataUltimoContattoAndIndirizzoSedeLegale_Comune_Provincia(@Nullable LocalDate dataInserimento, @Nullable String ragioneSociale, @Nullable Double fatturatoAnnuale, @Nullable LocalDate dataUltimoContatto, @Nullable Provincia provincia, Pageable pageable);

    //Page<Cliente> filtraClientiPerFatturatoAnnuo(double minFatturato, double maxFatturato, Pageable pageable);
    //Page<Cliente> findByNomeContattoContainsIgnoreCase(String nomeContatto, Pageable pageable);

    //Optional<Cliente> findByDataUltimoContatto(LocalDate dataUltimoContatto);

//    Page<Cliente> findByEmail(String email, Pageable pageable);


//    Page<Cliente> searchByPartName(String partName, Pageable pageable);

//    Page<Cliente> findByUltimocontatto(LocalDate ultimoContatto, Pageable pageable);

//    Page<Cliente> searchByProvincia(String provincia, Pageable pageable);
}




