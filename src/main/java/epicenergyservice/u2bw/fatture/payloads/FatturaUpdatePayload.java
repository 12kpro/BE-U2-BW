package epicenergyservice.u2bw.fatture.payloads;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class FatturaUpdatePayload {
    @NotBlank(message = "Il numero è obbligatorio")
    private int numero;

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
    @NotNull(message = "Lo stato è obbligatorio")
    private UUID statoFattura;

    public FatturaUpdatePayload(int numero, @NotNull(message = "La data è obbligatoria") LocalDateTime data, @NotNull(message = "L'importo è obbligatorio") Double importo, @NotNull(message = "Il cliente è obbligatorio") UUID cliente, @NotNull(message = "Lo stato è obbligatorio") UUID statoFattura) {
        this.numero = numero;
        this.data = data;
        this.importo = importo;
        this.cliente = cliente;
        this.statoFattura = statoFattura;
    }
//    public FatturaCreatePayload() {
//    }
//
//    public int getNumero() {
//        return numero;
//    }
//
//    public void setNumero(int numero) {
//        this.numero = numero;
//    }
//
//    public LocalDateTime getData() {
//        return data;
//    }
//
//    public void setData(LocalDateTime data) {
//        this.data = data;
//    }
//
//    public BigDecimal getImporto() {
//        return importo;
//    }
//
//    public void setImporto(BigDecimal importo) {
//        this.importo = importo;
//    }
//
//    public Cliente getCliente() {
//        return cliente;
//    }
//
//    public void setCliente(Cliente cliente) {
//        this.cliente = cliente;
//    }
//
//    private String stato;
//
//    public String getStato() {
//        return stato;
//    }
//
//    public void setStato(String stato) {
//        this.stato = stato;
//    }

}
