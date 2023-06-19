package epicenergyservice.u2bw.clienti;

import epicenergyservice.u2bw.indirizzi.Indirizzo;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.UUID;
@Entity
@Table(name="clienti")

public class Cliente {
    @Id
    private UUID id = UUID.randomUUID();
    private String cognomeContatto;
    private String dataInserimento;
    private String dataUltimoContatto;
    private String email;
    private String emailContatto;
    private String fatturatoAnnuale; //-> Calcolato?
    private String nomeContatto;
    private String partitaIva;  //-> numeri?
    private String pec;
    private String ragioneSociale;
    private String telefono;
    private String telefonoContatto;

    ///////// relazioni /////
    private TipoCliente tipoCliente;
    private Indirizzo indirizzoSedeLegale;
    private Indirizzo indirizzoSedeOperativa;
}
