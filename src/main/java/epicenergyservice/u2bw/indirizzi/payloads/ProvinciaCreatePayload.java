package epicenergyservice.u2bw.indirizzi.payloads;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class ProvinciaCreatePayload {
    @NotNull(message = "Il nome è obbligatorio")
    @Size(min = 3, max = 30, message = "Nome min 3 caratteri, massimo 30")
    String nome;
    @NotNull(message = "La Sigla è obbligatoria")
    @Size(min = 2, max = 2, message = "La sigla deve avere 2 caratteri")
    String sigla;
    @NotNull(message = "La Regione è obbligatorio")
    String regione;

    public ProvinciaCreatePayload(@NotNull(message = "Il nome è obbligatorio") String nome, @NotNull(message = "La Sigla è obbligatoria") String sigla, @NotNull(message = "La Regione è obbligatorio") String regione) {
        this.nome = nome;
        this.sigla = sigla;
        this.regione = regione;
    }
}
