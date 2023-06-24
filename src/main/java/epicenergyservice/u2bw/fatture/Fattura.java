package epicenergyservice.u2bw.fatture;

import epicenergyservice.u2bw.clienti.Cliente;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Formula;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "fatture")
@Getter
@Setter
public class Fattura {
    @Id
    private UUID id = UUID.randomUUID();
    //TODO verificare
    @Formula("EXTRACT(YEAR FROM data)")
    private Integer anno;
    @Column(columnDefinition = "timestamp without time zone")
    private LocalDateTime data;
    @Column(columnDefinition = "numeric(19,2)")
    private Double importo;
    private Integer numero;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "stato_fattura_id")
    private StatoFattura statoFattura;

}
