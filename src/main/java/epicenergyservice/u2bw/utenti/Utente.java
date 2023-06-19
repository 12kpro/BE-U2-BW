package epicenergyservice.u2bw.utenti;

import jakarta.persistence.*;
import lombok.Data;

import lombok.NoArgsConstructor;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@Table(name = "utenti")
public class Utente implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String cognome;
    private String email;
    private String nome;
    private String password;
    private String username;

    // Relazione many-to-many con RuoloUtente
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "utente_ruolo",
            joinColumns = @JoinColumn(name = "utente_id"),
            inverseJoinColumns = @JoinColumn(name = "ruolo_id"))
    private Set<RuoloUtente> ruoli = new HashSet<>();

    public Utente(String cognome, String email, String nome, String password, String username) {
        this.cognome = cognome;
        this.email = email;
        this.nome = nome;
        this.password = password;
        this.username = username;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> authorities = new HashSet<>();
        for (RuoloUtente ruoloUtente : ruoli) {
            authorities.add(new SimpleGrantedAuthority(ruoloUtente.getNome().name()));
        }
        return authorities;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
