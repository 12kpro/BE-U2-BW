package epicenergyservice.u2bw.utenti;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
public class Ruolo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Enumerated(EnumType.STRING)
    private Ruolo nome;

    // Relazione many-to-many con Utente
    @ManyToMany(mappedBy = "ruoli")
    private Set<Utente> utenti = new HashSet<>();

    public Ruolo(Ruolo nome) {
        this.nome = nome;
    }
}
