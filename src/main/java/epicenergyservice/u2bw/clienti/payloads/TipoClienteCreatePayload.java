package epicenergyservice.u2bw.clienti.payloads;


import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TipoClienteCreatePayload {
    @NotNull(message = "Il nome del tipo cliente è obbligatorio")
    private String nome;
    @NotNull(message = "La descrizione del tipo cliente è obbligatoria")
    private String descrizione;
}
