package epicenergyservice.u2bw.utenti.repositories;

import epicenergyservice.u2bw.utenti.Ruolo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
@Repository
public interface RuoloRepository extends JpaRepository<Ruolo, UUID> {
    Optional<Ruolo> findByNome(String nome);

}
