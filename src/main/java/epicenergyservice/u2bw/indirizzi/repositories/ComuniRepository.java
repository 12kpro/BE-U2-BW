package epicenergyservice.u2bw.indirizzi.repositories;

import epicenergyservice.u2bw.indirizzi.Comune;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.UUID;

@Repository
public interface ComuniRepository extends JpaRepository<Comune, UUID> {


}
