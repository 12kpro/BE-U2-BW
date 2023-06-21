package epicenergyservice.u2bw.clienti.repositories;

import epicenergyservice.u2bw.clienti.TipoCliente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TipoClienteRepository extends JpaRepository<TipoCliente, UUID> {
    Page<TipoCliente> findAll(Pageable pageable);

    Page<TipoCliente> findById(UUID id, Pageable pageable);

    TipoCliente save(TipoCliente tipoCliente);

    TipoCliente updateTipoCliente(TipoCliente tipoCliente);

    void deleteById(UUID id);
}
