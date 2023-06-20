package epicenergyservice.u2bw.fatture;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="stato_fatture")
@Getter
@Setter
public class StatoFattura {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String stato;

    @OneToOne
    @JoinColumn(name = "fattura_id")
    private Fattura fattura;


}