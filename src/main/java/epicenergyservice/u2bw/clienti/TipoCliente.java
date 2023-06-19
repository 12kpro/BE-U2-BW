package epicenergyservice.u2bw.clienti;

import jakarta.persistence.Id;

import java.util.UUID;

public class TipoCliente {
    @Id
    private UUID id = UUID.randomUUID();
    private String nome;
}
