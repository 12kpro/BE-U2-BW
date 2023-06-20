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
    @Column(length = 255)
    private String nome;

    @ManyToOne
    private Provincia provincia;

    public Comune(String nome, Provincia provincia) {
        this.nome = nome;
        this.provincia = provincia;
    }
}
