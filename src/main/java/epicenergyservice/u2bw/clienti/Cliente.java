package epicenergyservice.u2bw.clienti;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import epicenergyservice.u2bw.fatture.Fattura;
import epicenergyservice.u2bw.indirizzi.Indirizzo;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;


import java.util.List;
import java.util.UUID;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DialectOverride;
import org.hibernate.annotations.Formula;

@Entity
@Table(name="clienti")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Cliente {
    @Id
    private UUID id = UUID.randomUUID();
    @Column(nullable = false, length = 255)
    private String nomeContatto;
    @Column(length = 255)
    private String cognomeContatto;
    @Column(length = 255)
    private String emailContatto;
    @Column(nullable = false,unique = true, columnDefinition = "timestamp without time zone")
    private String dataInserimento;
    @Column(columnDefinition = "timestamp without time zone")
    private String dataUltimoContatto;
    @Column(length = 255)
    private String email;
    // TODO campo calcolato? Fare sub query
    @Formula("")
    @Column(columnDefinition = "numeric(19,2)")
    private String fatturatoAnnuale;
    @Column(nullable = false)
    private String partitaIva;
    @Column(nullable = false)
    private String pec;
    @Column(length = 255)
    private String ragioneSociale;
    @Column(length = 255)
    private String telefono;
    @Column(length = 255)
    private String telefonoContatto;

    @ManyToOne
    @JoinColumn(name = "tipo_cliente_id")
    private TipoCliente tipoCliente;

    @OneToMany(mappedBy = "cliente", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonIgnoreProperties({"cliente"})
    private List<Fattura> fatture;

    @ManyToOne
    @JoinColumn(name = "indirizzo_sede_legale_id")
    private Indirizzo indirizzoSedeLegale;

    @ManyToOne
    @JoinColumn(name = "indirizzo_sede_operativa_id")
    private Indirizzo indirizzoSedeOperativa;
}













