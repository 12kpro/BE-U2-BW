package epicenergyservice.u2bw.fatture.repositories;

import epicenergyservice.u2bw.fatture.StatoFattura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatoFatturaRepository extends JpaRepository<StatoFattura, Long> {
    }
