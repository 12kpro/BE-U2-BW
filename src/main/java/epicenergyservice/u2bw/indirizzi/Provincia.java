package epicenergyservice.u2bw.indirizzi;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name="province")
@NoArgsConstructor
public class Provincia {
    @Id
    private UUID id = UUID.randomUUID();
    @Column(length = 255)
    private String nome;
    @Column(length = 255)
    private String sigla;
    @Column(length = 255)
    private String regione;

    public Provincia( String nome, String sigla, String regione) {
        this.nome = nome;
        this.sigla = sigla;
        this.regione = regione;
    }
}
