package epicenergyservice.u2bw.indirizzi;

import jakarta.persistence.Id;

import java.util.UUID;

public class Indirizzo {
    @Id
    private UUID id = UUID.randomUUID();

    private String cap;
    private String civico;
    private String localita;
    private String via;

    /// relazione ////
    private Comune comune;
}
