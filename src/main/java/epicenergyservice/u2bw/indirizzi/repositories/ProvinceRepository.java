package epicenergyservice.u2bw.indirizzi.repositories;

import epicenergyservice.u2bw.indirizzi.Provincia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProvinceRepository extends JpaRepository<Provincia, Integer> {
    Optional<Provincia> findByNome(String nome);

    Optional<Provincia> findByNomeIgnoreCase(String nome);



    @Query("SELECT p.id FROM Provincia p")
    List<Integer> findAllIds();
}
