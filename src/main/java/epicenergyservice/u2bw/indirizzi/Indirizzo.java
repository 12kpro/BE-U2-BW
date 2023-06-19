package epicenergyservice.u2bw.indirizzi;

import jakarta.persistence.*;

import java.util.UUID;
@Entity
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
}
