package epicenergyservice.u2bw.indirizzi.repositories;


import epicenergyservice.u2bw.indirizzi.Indirizzo;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.UUID;

public interface IndirizzoRepository extends JpaRepository <Indirizzo, UUID> {

}
