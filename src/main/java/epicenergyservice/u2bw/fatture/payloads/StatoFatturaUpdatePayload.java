package epicenergyservice.u2bw.fatture.payloads;

import lombok.Getter;
import lombok.Setter;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Getter
@Setter
public class StatoFatturaUpdatePayload {
    @NotNull(message = "L'ID è obbligatorio")
    private Long id;

    @NotNull(message = "Il codice è obbligatorio")
    @NotBlank(message = "Il codice non può essere vuoto")
    @Size(max = 10, message = "Il codice può contenere al massimo 10 caratteri")
    private String codice;

    @NotNull(message = "La descrizione è obbligatoria")
    @NotBlank(message = "La descrizione non può essere vuota")
    private String descrizione;

    public StatoFatturaUpdatePayload() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodice() {
        return codice;
    }

    public void setCodice(String codice) {
        this.codice = codice;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }
}
