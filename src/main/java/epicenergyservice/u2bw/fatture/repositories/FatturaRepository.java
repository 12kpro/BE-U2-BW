package epicenergyservice.u2bw.fatture.repositories;

import epicenergyservice.u2bw.fatture.Fattura;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface FatturaRepository extends JpaRepository<Fattura, UUID> {
    Optional<Fattura> findByNumeroAndCliente_Id(Integer numero, UUID id);

    Page<Fattura> findByDataAndAnnoAndImportoBetweenAndCliente_IdAndStatoFattura_Id(@Nullable LocalDateTime data, @Nullable Integer anno, @Nullable Double importoStart, @Nullable Double importoEnd, @Nullable UUID id, @Nullable UUID id1, Pageable pageable);
}
