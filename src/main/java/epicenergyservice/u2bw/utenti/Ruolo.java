package epicenergyservice.u2bw.utenti;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name="ruoli")
@Data
@NoArgsConstructor
@Getter
public class Ruolo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String nome;

    @ManyToMany
    @JoinTable(name = "utenti_ruoli",
            joinColumns = @JoinColumn(name = "ruolo_id"),
            inverseJoinColumns = @JoinColumn(name = "utente_id"))
    private Set<Utente> utenti = new LinkedHashSet<>();

    public Ruolo(String nome) {
        this.nome = nome;
    }

}
