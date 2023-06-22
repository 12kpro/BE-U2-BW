package epicenergyservice.u2bw.fatture.repositories;

import epicenergyservice.u2bw.fatture.Fattura;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Repository
public interface FatturaRepository extends JpaRepository<Fattura, UUID> {
    Page<Fattura> findByCliente_Id(UUID id, Pageable pageable);

    Page<Fattura> findByStatoFattura_Id(UUID id, Pageable pageable);

    Page<Fattura> findByAnno(int anno, Pageable pageable);

    Page<Fattura> findByData(LocalDateTime data, Pageable pageable);

    Page<Fattura> findByImportoBetween(BigDecimal importoStart, BigDecimal importoEnd, Pageable pageable);

    Page<Fattura> findByDataAndAnnoAndImportoBetween(LocalDateTime data, int anno, BigDecimal importoStart, BigDecimal importoEnd, Pageable pageable);
}
