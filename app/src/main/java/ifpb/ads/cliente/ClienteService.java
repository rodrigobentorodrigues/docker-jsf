package ifpb.ads.cliente;

import ifpb.ads.infra.ClientesJDBC;
import java.util.List;

public class ClienteService {

    //private final Clientes clientes = new ClientesEmMemoria();
    private Clientes clientes = new ClientesJDBC();

    public void salvar(Cliente cliente) {
        this.clientes.salvar(cliente);
    }

    public void remover(Cliente cliente) {
        this.clientes.remover(cliente);
    }

    public void atualizar(Cliente cliente) {
        this.clientes.atualizar(cliente);
    }

    public List<Cliente> todosOsClientes() {
        return this.clientes.todosOsClientes();
    }
}
