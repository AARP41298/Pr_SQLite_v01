package logica;

import android.content.Context;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import accesodatos.AccesoAdatos;
import entidades.Cliente;

public class AdminClientes {
    //Instancia de la clase AccesoDatos
    private AccesoAdatos accesodatos;



    public AdminClientes(Context context) {
        accesodatos = new AccesoAdatos(context);
    }

    public List<Cliente> ObtenerLista() throws SQLException {
        List<Cliente> lista = accesodatos.ListaClientes();
        return lista;
    }

    public Cliente ConsultaCliente(int idCliente) throws SQLException {
        return accesodatos.QueryCliente(idCliente);
    }

    public long AgregarCliente(Cliente cliente) throws SQLException {
        return accesodatos.AddClientes(cliente);
    }

    public int EditarCliente(Cliente cliente) throws SQLException {
        return accesodatos.UpdateClientes(cliente);
    }

    public int EliminarCliente(Cliente cliente) throws SQLException {
        return accesodatos.DeleteClientes(cliente);
    }
}
