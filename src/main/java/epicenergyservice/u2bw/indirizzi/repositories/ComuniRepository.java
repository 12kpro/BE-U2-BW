package epicenergyservice.u2bw.indirizzi.repositories;

import epicenergyservice.u2bw.indirizzi.Comune;
import epicenergyservice.u2bw.indirizzi.Provincia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ComuniRepository extends JpaRepository<Comune, UUID> {
    Optional<Comune> findByCodComuneAndNomeAndProvincia_Id(String codComune, String nome, Integer id);

    Optional<Comune> findByCodComuneAndProvincia(String codComune, Provincia provincia);

}
