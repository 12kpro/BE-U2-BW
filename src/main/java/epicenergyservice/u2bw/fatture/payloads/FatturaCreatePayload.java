package epicenergyservice.u2bw.fatture.payloads;

import com.fasterxml.jackson.annotation.JsonFormat;
import epicenergyservice.u2bw.clienti.Cliente;
import lombok.Getter;
import lombok.Setter;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class FatturaCreatePayload {
    @NotNull(message = "Il numero è obbligatorio")
    private Integer numero;

    @NotNull(message = "La data è obbligatoria")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    //TODO ffix format for local date time
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime data;

    @NotNull(message = "L'importo è obbligatorio")
    @Positive(message = "L'importo deve essere un valore positivo")
    private Double importo;

    @NotNull(message = "Il cliente è obbligatorio")
    private UUID cliente;


    public FatturaCreatePayload(@NotNull(message = "Il numero è obbligatorio") Integer numero, @NotNull(message = "La data è obbligatoria") LocalDateTime data, @NotNull(message = "L'importo è obbligatorio") Double importo, @NotNull(message = "Il cliente è obbligatorio") UUID cliente) {
        this.numero = numero;
        this.data = data;
        this.importo = importo;
        this.cliente = cliente;
    }
}
