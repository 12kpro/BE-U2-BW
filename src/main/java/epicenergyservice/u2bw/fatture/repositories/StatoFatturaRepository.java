package epicenergyservice.u2bw.fatture.repositories;

import epicenergyservice.u2bw.fatture.StatoFattura;
import epicenergyservice.u2bw.fatture.Fattura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StatoFatturaRepository extends JpaRepository<StatoFattura, Long> {
    Optional<StatoFattura> findByFattura(Fattura fattura);
}
