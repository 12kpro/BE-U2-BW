package epicenergyservice.u2bw.fatture;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "stato_fatture")
@Getter
@Setter
public class StatoFattura {
    @Id
    private UUID id = UUID.randomUUID();
    private String stato;

    @OneToOne(mappedBy = "statoFattura")  // Corretta mappatura bidirezionale
    private Fattura fattura;

}
