package epicenergyservice.u2bw.fatture;

import epicenergyservice.u2bw.clienti.Cliente;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;
@Entity
@Getter
@Setter
public class Fattura {
    @Id
    private UUID id = UUID.randomUUID();

    private int anno;
    private LocalDateTime data;
    private BigDecimal importo;
    private int numero;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @OneToOne(mappedBy = "fattura", cascade = CascadeType.ALL)
    private StatoFattura statoFattura;
}
