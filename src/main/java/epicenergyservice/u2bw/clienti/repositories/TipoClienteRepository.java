package epicenergyservice.u2bw.clienti.repositories;
import epicenergyservice.u2bw.clienti.TipoCliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface TipoClienteRepository extends JpaRepository<TipoCliente, UUID> {
    Optional<TipoCliente> findByNome(String nome);
}
