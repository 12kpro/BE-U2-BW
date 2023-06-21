package epicenergyservice.u2bw.clienti.payloads;



import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientiCreatePayloads {
    @NotNull(message = "Il nome del contatto è obbligatorio")
    @Size(min = 3, max = 30, message = "Nome min 3 caratteri, massimo 30")
    private String nomeContatto;

    @Size(min = 3, max = 30, message = "Nome min 3 caratteri, massimo 30")
    private String cognomeContatto;

    @Size(max = 255, message = "L'email del contatto può contenere al massimo 255 caratteri")
    @Email(message = "Non hai inserito un indirizzo email valido per il contatto")
    private String emailContatto;

    @NotNull(message = "La data di inserimento è obbligatoria")
    private String dataInserimento;

    private String dataUltimoContatto;

    @NotNull(message = "L'email è obbligatoria")
    @Email(message = "Non hai inserito un indirizzo email valido")
    private String email;

    private String fatturatoAnnuale;

    @NotNull(message = "La partita IVA è obbligatoria")
    private String partitaIva;

    @NotNull(message = "La PEC è obbligatoria")
    @Email(message = "Non hai inserito un indirizzo PEC valido")
    private String pec;

    @Size(max = 15, message = "La ragione sociale può contenere al massimo 255 caratteri")
    private String ragioneSociale;

    @Size(max = 20, message = "Il numero di telefono può contenere al massimo 255 caratteri")
    private String telefono;






    public ClientiCreatePayloads() {
    }

    public ClientiCreatePayloads(
            @NotNull(message = "Il nome del contatto è obbligatorio") @Size(min = 3, max = 30, message = "Nome min 3 caratteri, massimo 30") String nomeContatto,
            @Size(min = 3, max = 30, message = "Cognome min 3 caratteri, massimo 30") String cognomeContatto,
            @Size(min = 3, max = 30, message = "email min 3 caratteri, massimo 30") @Email(message = "Non hai inserito un indirizzo email valido per il contatto") String emailContatto,
            @NotNull(message = "La data di inserimento è obbligatoria") String dataInserimento,
            String dataUltimoContatto,
            @NotNull(message = "L'email è obbligatoria") @Email(message = "Non hai inserito un indirizzo email valido") String email,
            String fatturatoAnnuale,
            @NotNull(message = "La partita IVA è obbligatoria") String partitaIva,
            @NotNull(message = "La PEC è obbligatoria") @Email(message = "Non hai inserito un indirizzo PEC valido") String pec,
            @Size(max = 15, message = "La ragione sociale può contenere al massimo 15 caratteri") String ragioneSociale,
            @Size(max = 20, message = "Il numero di telefono può contenere al massimo 255 caratteri") String telefono


    ) {
        this.nomeContatto = nomeContatto;
        this.cognomeContatto = cognomeContatto;
        this.emailContatto = emailContatto;
        this.dataInserimento = dataInserimento;
        this.dataUltimoContatto = dataUltimoContatto;
        this.email = email;
        this.fatturatoAnnuale = fatturatoAnnuale;
        this.partitaIva = partitaIva;
        this.pec = pec;
        this.ragioneSociale = ragioneSociale;
        this.telefono = telefono;

    }
}

