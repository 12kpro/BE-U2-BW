package epicenergyservice.u2bw.clienti.payloads;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class ClienteGetPayload {
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(pattern = "MM/dd/yyyy")
    private LocalDate dataInserimento;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(pattern = "MM/dd/yyyy")
    private LocalDate dataUltimoContatto;
    @DecimalMin("0.0")
    private String fatturatoAnnuale;
    private String ragioneSociale;
    @Min(value = 0)
    private Integer provinciaSedeLegaleId;
}
//Nome
//Fatturato annuale
//Data di inserimento
//Data di ultimo contatto
//Provincia della sede legale.