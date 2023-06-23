package epicenergyservice.u2bw.fatture;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "stato_fatture")
@Getter
@Setter
@NoArgsConstructor
public class StatoFattura {
    @Id
    private UUID id = UUID.randomUUID();
    private String stato;

    @OneToOne(mappedBy = "statoFattura")
    private Fattura fattura;

    public StatoFattura(String stato) {
        this.stato = stato;
    }
}
