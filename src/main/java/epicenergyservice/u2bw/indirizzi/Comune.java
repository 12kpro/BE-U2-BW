package epicenergyservice.u2bw.indirizzi;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name="comuni")
@NoArgsConstructor
public class Comune {
    @Id
    private UUID id = UUID.randomUUID();
    private String codComune;
    @Column(length = 255)
    private String nome;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn
    private Provincia provincia;

    public Comune(String codComune, String nome, Provincia provincia) {
        this.codComune = codComune;
        this.nome = nome;
        this.provincia = provincia;
    }
}
