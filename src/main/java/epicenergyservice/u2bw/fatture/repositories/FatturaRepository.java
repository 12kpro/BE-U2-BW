package epicenergyservice.u2bw.fatture.repositories;

import epicenergyservice.u2bw.fatture.Fattura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface FatturaRepository extends JpaRepository<Fattura, UUID> {
}
