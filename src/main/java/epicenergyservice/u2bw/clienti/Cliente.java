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

@Entity
@Table(name="clienti")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Cliente {
    @Id
    private UUID id = UUID.randomUUID();
    @Column(nullable = false)
    private String nomeContatto;
    private String cognomeContatto;
    private String emailContatto;
    @Column(nullable = false,unique = true)
    private String dataInserimento;
    private String dataUltimoContatto;
    private String email;

    private String fatturatoAnnuale;
    @Column(nullable = false)
    private String partitaIva;
    @Column(nullable = false)
    private String pec;
    private String ragioneSociale;
    private String telefono;
    private String telefonoContatto;

    @OneToOne
    @JoinColumn(name = "tipo_cliente_id")
    private TipoCliente tipoCliente;
    @OneToMany(mappedBy = "cliente", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonIgnoreProperties({"cliente"})
    private List<Indirizzo> indirizzi;

    @OneToMany(mappedBy = "cliente", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonIgnoreProperties({"cliente"})
    private List<Fattura> fatture;
}













