package epicenergyservice.u2bw.clienti.payloads;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
@ToString
@Getter
@Setter
public class ClienteGetPayload {
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataInserimento;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataUltimoContatto;
    @DecimalMin("0.0")
    private Double fatturatoAnnuale;
    private String ragioneSociale;
    @Min(value = 0)
    private Integer provinciaSedeLegaleId;

    public ClienteGetPayload(LocalDate dataInserimento, LocalDate dataUltimoContatto, Double fatturatoAnnuale, String ragioneSociale, Integer provinciaSedeLegaleId) {
        this.dataInserimento = dataInserimento;
        this.dataUltimoContatto = dataUltimoContatto;
        this.fatturatoAnnuale = fatturatoAnnuale;
        this.ragioneSociale = ragioneSociale;
        this.provinciaSedeLegaleId = provinciaSedeLegaleId;
    }
}
//Nome
//Fatturato annuale
//Data di inserimento
//Data di ultimo contatto
//Provincia della sede legale.