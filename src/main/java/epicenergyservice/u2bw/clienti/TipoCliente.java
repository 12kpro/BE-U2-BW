package epicenergyservice.u2bw.clienti;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tipi_cliente")
public class TipoCliente {
    @Id
    private UUID id = UUID.randomUUID();
    private String nome;

    @OneToMany(mappedBy = "tipoCliente")
    private List<Cliente> clienti = new ArrayList<>();



    public TipoCliente() {
    }

    public TipoCliente(String nome) {
//        this.id = id;
        this.nome = nome;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Cliente> getClienti() {
        return clienti;
    }

    public void setClienti(List<Cliente> clienti) {
        this.clienti = clienti;
    }
}
