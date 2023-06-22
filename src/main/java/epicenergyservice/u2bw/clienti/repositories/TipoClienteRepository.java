package epicenergyservice.u2bw.clienti.repositories;
import epicenergyservice.u2bw.clienti.Cliente;
import epicenergyservice.u2bw.clienti.TipoCliente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface TipoClienteRepository extends JpaRepository<TipoCliente, UUID> {
//    Optional<TipoCliente> findByClienti_TipoCliente_Id(UUID id);
//
//    Optional<TipoCliente> findByClienti_TipoCliente_Nome(String nome);

    Optional<TipoCliente> findByNome(String nome);







}
