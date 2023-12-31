package epicenergyservice.u2bw.clienti.payloads;



import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
public class ClientiCreatePayloads {
    @NotNull(message = "Il nome del contatto è obbligatorio")
    @Size(min = 3, max = 30, message = "Nome min 3 caratteri, massimo 30")
    private String nomeContatto;

    @Size(min = 3, max = 30, message = "Nome min 3 caratteri, massimo 30")
    private String cognomeContatto;

    @Size(max = 30, message = "L'email del contatto può contenere al massimo 30 caratteri")
    @Email(message = "Non hai inserito un indirizzo email valido per il contatto")
    private String emailContatto;

    @NotNull(message = "La data di inserimento è obbligatoria")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataInserimento;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataUltimoContatto;

    @NotNull(message = "L'email è obbligatoria")
    @Email(message = "Non hai inserito un indirizzo email valido")
    private String email;

    @NotNull(message = "La partita IVA è obbligatoria")
    private String partitaIva;

    @NotNull(message = "La PEC è obbligatoria")
    @Email(message = "Non hai inserito un indirizzo PEC valido")
    private String pec;

    @Size(max = 15, message = "La ragione sociale può contenere al massimo 15 caratteri")
    private String ragioneSociale;

    @Size(max = 20, message = "Il numero di telefono può contenere al massimo 20 caratteri")
    private String telefono;

    @Size(max = 20, message = "Il numero di telefono può contenere al massimo 20 caratteri")
    private String telefonoContatto;

    @NotNull(message = "Il tipo cliente è obbligatorio")
    private UUID tipoCliente;

    @NotNull(message = "La sede legale è obbligatoria")
    private UUID indirizzoSedeLegale;


    private UUID indirizzoSedeOperativa;

    public ClientiCreatePayloads(@NotNull(message = "Il nome del contatto è obbligatorio") String nomeContatto, String cognomeContatto, String emailContatto, @NotNull(message = "La data di inserimento è obbligatoria") LocalDate dataInserimento, LocalDate dataUltimoContatto, @NotNull(message = "L'email è obbligatoria") String email, @NotNull(message = "La partita IVA è obbligatoria") String partitaIva, @NotNull(message = "La PEC è obbligatoria") String pec, String ragioneSociale, String telefono, String telefonoContatto, @NotNull(message = "Il tipo cliente è obbligatorio") UUID tipoCliente, @NotNull(message = "La sede legale è obbligatoria") UUID indirizzoSedeLegale, UUID indirizzoSedeOperativa) {
        this.nomeContatto = nomeContatto;
        this.cognomeContatto = cognomeContatto;
        this.emailContatto = emailContatto;
        this.dataInserimento = dataInserimento;
        this.dataUltimoContatto = dataUltimoContatto;
        this.email = email;
        this.partitaIva = partitaIva;
        this.pec = pec;
        this.ragioneSociale = ragioneSociale;
        this.telefono = telefono;
        this.telefonoContatto = telefonoContatto;
        this.tipoCliente = tipoCliente;
        this.indirizzoSedeLegale = indirizzoSedeLegale;
        this.indirizzoSedeOperativa = indirizzoSedeOperativa;
    }
}

