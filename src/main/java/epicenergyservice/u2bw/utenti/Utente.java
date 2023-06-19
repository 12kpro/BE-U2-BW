package epicenergyservice.u2bw.utenti;

import jakarta.persistence.Id;

import java.util.UUID;

public class Utente {
    @Id
    private UUID id = UUID.randomUUID();

    private String cognome;
    private String email;
    private String nome;
    private String password;
    private String username;

    // relazioni //

    private Ruoli ruolo;

}
