package epicenergyservice.u2bw.fatture.repositories;

import epicenergyservice.u2bw.fatture.Fattura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface FatturaRepository extends JpaRepository<Fattura, UUID> {

    @Query("SELECT f FROM Fattura f")
    List<Fattura> findAll();

    @Query("SELECT f FROM Fattura f WHERE f.id = :id")
    Optional<Fattura> findById(@Param("id") UUID id);

    @Query("SELECT f FROM Fattura f WHERE f.cliente.id = :clienteId")
    List<Fattura> findByClienteId(@Param("clienteId") UUID clienteId);
}
