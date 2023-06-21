package epicenergyservice.u2bw.indirizzi.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;


//Un indirizzo Ã¨ composto da





@Getter
@Setter
public class IndirizzoCreatePayload {
    @NotNull(message = "La via è obbligatoria")
    @Size(min = 3, max = 30, message = "Nome min 3 caratteri, massimo 30")
    String via;
    @NotNull(message = "Il civico è obbligatorio")
    String civico;
    @NotNull(message = "La località è obbligatoria")
    @Size(min = 3, max = 30, message = "Nome min 3 caratteri, massimo 30")
    String località;
    @NotNull(message = "il cap è obbligatorio")
    @Size(min = 3, max = 30, message = "Nome min 3 caratteri, massimo 30")
    String cap;
    @NotNull(message = "il comune è obbligatorio")
    @Size(min = 3, max = 30, message = "Nome min 3 caratteri, massimo 30")
    UUID comune;




    public IndirizzoCreatePayload(@NotNull(message = "La via è obbligatoria") String via, @NotNull(message = "Il civico è obbligatorio") String civico, @NotNull(message = "La località è obbligatoria") String località, @NotNull(message = "Il cap è obbligatorio") String cap, @NotNull(message = "il comune è obbligatorio") String comune) {
        this.via = via;
        this.civico = civico;
        this.località = località;
        this.cap = cap;
        this.comune = UUID.fromString(comune);
    }

}
