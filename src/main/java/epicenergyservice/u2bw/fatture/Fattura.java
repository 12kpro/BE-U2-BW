package epicenergyservice.u2bw.fatture;

import epicenergyservice.u2bw.clienti.Cliente;
import jakarta.persistence.Id;

import java.time.LocalDateTime;
import java.util.UUID;

public class Fattura {
    @Id
    private UUID id = UUID.randomUUID();

    private int anno;
    private LocalDateTime data;
    private  Double importo;
    private int numero;


    // relazioni ////

    private Cliente cliente;
    private StatoFattura statoFattura;
}
