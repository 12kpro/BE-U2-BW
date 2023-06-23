package epicenergyservice.u2bw.fatture.payloads;

import epicenergyservice.u2bw.clienti.Cliente;
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
    @NotNull(message = "Il numero è obbligatorio")
    private Integer numero;

    @NotNull(message = "La data è obbligatoria")
    private LocalDateTime data;

    @NotNull(message = "L'importo è obbligatorio")
    @Positive(message = "L'importo deve essere un valore positivo")
    private BigDecimal importo;

    @NotNull(message = "Il cliente è obbligatorio")
    private Cliente cliente;

    public FatturaCreatePayload() {
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
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

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    private String stato;

    public String getStato() {
        return stato;
    }

    public void setStato(String stato) {
        this.stato = stato;
    }

}
