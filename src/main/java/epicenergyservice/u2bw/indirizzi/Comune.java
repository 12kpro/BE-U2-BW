package epicenergyservice.u2bw.indirizzi;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="comuni")
@NoArgsConstructor
public class Comune {
    @Id
    private Integer id;
    @Column(length = 255)
    private String nome;

    // relazione //

    @ManyToOne
    @JoinColumn(name = "provincia_id")
    private Provincia provincia;

    public Comune(Integer id, String nome, Provincia provincia) {
        this.id = id;
        this.nome = nome;
        this.provincia = provincia;
    }
}
