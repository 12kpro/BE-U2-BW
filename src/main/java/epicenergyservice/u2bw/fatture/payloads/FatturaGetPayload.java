package epicenergyservice.u2bw.fatture.payloads;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.DecimalMin;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import java.util.UUID;

@ToString
@Getter
@Setter
public class FatturaGetPayload {
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    //TODO ffix format for local date time
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime data;

    private Integer anno;
    @DecimalMin("0.0")
    private Double minImporto;
    @DecimalMin("0.0")
    private Double maxImporto;

    private UUID clienteId;
    private UUID statoId;

    public FatturaGetPayload(LocalDateTime data, Integer anno, Double minImporto, Double maxImporto, UUID clienteId, UUID statoId) {
        this.data = data;
        this.anno = anno;
        this.minImporto = minImporto;
        this.maxImporto = maxImporto;
        this.clienteId = clienteId;
        this.statoId = statoId;
    }

    //TODO aggiungere veriffica per min < max
    @AssertTrue(message = "If minImporto is not null, maxImporto must also not be null")
    private boolean isMaxImportoValid() {
        return minImporto == null || maxImporto != null;
    }
}