package epicenergyservice.u2bw.clienti.repositories;

import epicenergyservice.u2bw.clienti.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import java.util.UUID;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, UUID> {

    @Query("SELECT c FROM Cliente c")
    List<Cliente> findAll();

    @Query("SELECT c FROM Cliente c WHERE c.id = :id")
    Optional<Cliente> findById(@Param("id") UUID id);

    @Query("SELECT c FROM Cliente c WHERE c.nomeContatto = :nomeContatto")
    List<Cliente> findByNomeContatto(@Param("nomeContatto") String nomeContatto);

    @Query("SELECT c FROM Cliente c WHERE c.email = :email")
    List<Cliente> findByEmail(@Param("email") String email);
}




