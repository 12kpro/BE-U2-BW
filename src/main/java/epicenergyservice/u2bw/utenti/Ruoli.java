package epicenergyservice.u2bw.utenti;

import jakarta.persistence.Id;

import java.util.UUID;

public class Ruoli {
    @Id
    private UUID id = UUID.randomUUID();
    private  String ruolo;

    // relazioni //
    private Utente utente;
}
