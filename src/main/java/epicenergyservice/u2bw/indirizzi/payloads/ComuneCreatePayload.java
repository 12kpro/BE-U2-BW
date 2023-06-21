package epicenergyservice.u2bw.indirizzi.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class ComuneCreatePayload {
    @NotNull(message = "Il nome è obbligatorio")
    @Size(min= 3, max = 3, message = "Codice comune deve avere  3 caratteri")
    String codComune;
    @NotNull(message = "Il nome è obbligatorio")
    @Size(min = 3, max = 30, message = "Nome min 3 caratteri, massimo 30")
    String nome;
    @NotNull(message = "L'ID Provincia è obbligatorio")
    Integer provincia;

    public ComuneCreatePayload(@NotNull(message = "Il nome è obbligatorio") String codComune, @NotNull(message = "Il nome è obbligatorio") String nome, @NotNull(message = "L'ID Provincia è obbligatorio") Integer provincia) {
        this.codComune = codComune;
        this.nome = nome;
        this.provincia = provincia;
    }
}
