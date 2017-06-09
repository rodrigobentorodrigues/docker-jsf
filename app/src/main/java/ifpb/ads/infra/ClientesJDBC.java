package ifpb.ads.infra;

import ifpb.ads.cliente.Cliente;
import ifpb.ads.cliente.Clientes;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ClientesJDBC implements Clientes {

    private static int id = 0;
    private String url;
    private String user;
    private String password;
    private Connection con;
    
    public ClientesJDBC(){
        try {
            Class.forName("org.postgresql.Driver");
            this.url = "jdbc:postgresql://host-banco:5432/cliente";
            this.user = "postgres";
            this.password = "12345";
            this.con = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    @Override
    public void salvar(Cliente cliente) {
        try {
            String sql = "INSERT INTO cliente(id, nome) VALUES(?,?)";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, ++id);
            stmt.setString(2, cliente.getNome());
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
    }

    @Override
    public void remover(Cliente cliente) {
        try {
            String sql = "DELETE FROM cliente WHERE id = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, cliente.getId());
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void atualizar(Cliente cliente) {
        String sql = "UPDATE cliente SET nome = ? WHERE id = ?";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, cliente.getNome());
            stmt.setInt(2, cliente.getId());
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public List<Cliente> todosOsClientes() {
        String sql = "SELECT * FROM cliente";
        List<Cliente> lista = new ArrayList<>();
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                Cliente c = new Cliente();
                c.setId(rs.getInt("id"));
                c.setNome(rs.getString("nome"));
                lista.add(c);
            }
            return lista;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return Collections.EMPTY_LIST;
    }

}
