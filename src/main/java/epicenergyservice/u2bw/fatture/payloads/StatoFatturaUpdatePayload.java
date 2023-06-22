package epicenergyservice.u2bw.fatture.payloads;

import epicenergyservice.u2bw.fatture.Fattura;
import lombok.Getter;
import lombok.Setter;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class StatoFatturaUpdatePayload {
    @NotNull(message = "L'ID è obbligatorio")
    private UUID id;

    @NotBlank(message = "Lo stato è obbligatorio")
    private String stato;

    private Fattura fattura;

    public StatoFatturaUpdatePayload() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getStato() {
        return stato;
    }

    public void setStato(String stato) {
        this.stato = stato;
    }

    public Fattura getFattura() {
        return fattura;
    }

    public void setFattura(Fattura fattura) {
        this.fattura = fattura;
    }
}
