package epicenergyservice.u2bw.utenti;

import jakarta.persistence.*;
import lombok.Data;

import lombok.NoArgsConstructor;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@Entity
@Data
@NoArgsConstructor
@Table(name = "utenti")
public class Utente implements UserDetails {
    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id = UUID.randomUUID();

    private String cognome;
    private String email;
    private String nome;
    private String password;
    private String username;

    @ManyToMany
    @JoinTable(name = "utenti_ruoli",
            joinColumns = @JoinColumn(name = "utente_id"),
            inverseJoinColumns = @JoinColumn(name = "ruolo_id"))
    private Set<Ruolo> ruoli = new LinkedHashSet<>();

//    // Relazione many-to-many con Ruolo
//    @ManyToMany(fetch = FetchType.EAGER)
//    @JoinTable(name = "utente_ruolo",
//            joinColumns = @JoinColumn(name = "utente_id"),
//            inverseJoinColumns = @JoinColumn(name = "ruolo_id"))
//    private Set<Ruolo> ruoli = new HashSet<>();

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
        for (Ruolo ruolo : ruoli) {
            authorities.add(new SimpleGrantedAuthority(ruolo.getNome()));
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
