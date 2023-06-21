package epicenergyservice.u2bw.fatture.payloads;

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
public class FatturaCreatePayload {
    @NotBlank(message = "Il numero è obbligatorio")
    private String numero;

    @NotNull(message = "La data è obbligatoria")
    private LocalDateTime data;

    @NotNull(message = "L'importo è obbligatorio")
    @Positive(message = "L'importo deve essere un valore positivo")
    private BigDecimal importo;

    @NotNull(message = "Lo stato è obbligatorio")
    private String stato;

    @NotNull(message = "Il cliente è obbligatorio")
    private UUID cliente;

    public FatturaCreatePayload() {
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public BigDecimal getImporto() {
        return importo;
    }

    public void setImporto(BigDecimal importo) {
        this.importo = importo;
    }

    public String getStato() {
        return stato;
    }

    public void setStato(String stato) {
        this.stato = stato;
    }

    public UUID getCliente() {
        return cliente;
    }

    public void setCliente(UUID cliente) {
        this.cliente = cliente;
    }
}
