package epicenergyservice.u2bw.fatture.payloads;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class StatoFatturaCreatePayload {
    @NotNull(message = "Il nome è obbligatorio")
    private String stato;

    public StatoFatturaCreatePayload(@NotNull(message = "Il nome è obbligatorio") String stato) {
        this.stato = stato;
    }
}
