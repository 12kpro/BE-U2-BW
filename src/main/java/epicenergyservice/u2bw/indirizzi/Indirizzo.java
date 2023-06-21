package epicenergyservice.u2bw.indirizzi;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;
@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name="indirizzi")
public class Indirizzo {
    @Id
    private UUID id = UUID.randomUUID();
    @Column(length = 255)
    private String cap;
    @Column(length = 255)
    private String civico;
    @Column(length = 255)
    private String localita;
    @Column(length = 255)
    private String via;

    /// relazione ////

    @ManyToOne
    private Comune comune;



    public Indirizzo(String via, String civico, String località, String cap, Comune comune) {
        this.via = via;
        this.civico = civico;
        this.localita = località;
        this.cap = cap;
        this.comune = comune;
    }

}

