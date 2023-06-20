package epicenergyservice.u2bw.clienti.repositories;

import epicenergyservice.u2bw.clienti.TipoCliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface TipoClienteRepository extends JpaRepository<TipoCliente, UUID> {
    @Query("SELECT t FROM TipoCliente t WHERE t.nome = ?1")
    List<TipoCliente> findByNome(String nome);
    @Query("SELECT t FROM TipoCliente t WHERE lower(t.nome) = lower(?1)")
    TipoCliente findByNomeIgnoreCase(String nome);

}
