package epicenergyservice.u2bw.indirizzi.repositories;

import epicenergyservice.u2bw.indirizzi.Provincia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProvinceRepository extends JpaRepository<Provincia, UUID> {
    Optional<Provincia> findByNome(String nome);

}
