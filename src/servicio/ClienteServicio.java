package servicio;

import modelo.CategoriaEnum;
import modelo.Cliente;

import java.util.ArrayList;
import java.util.List;

public class ClienteServicio {

    // Crear las variables de trabajo
    List<Cliente> listaClientes;


    // Crear el constructor
    public ClienteServicio(List<Cliente> listaClientes) {
        this.listaClientes = new ArrayList<>(listaClientes);
    }

    // Crear un método para listar todos los clientes
    public void listarClientes() {

    }

    // Crear un método para agregar nuevos clientes
    public void agregarCliente(String runCliente, String nombreCliente, String apellidoCliente,
                   @SuppressWarnings("SpellCheckingInspection") String aniosCliente,
                   @SuppressWarnings("SpellCheckingInspection") CategoriaEnum nombreCategoria) {

    }

    // Crear un método para editar los datos de un cliente
    public void editarCliente(String runCliente, String nombreCliente, String apellidoCliente,
                   @SuppressWarnings("SpellCheckingInspection") String aniosCliente,
                   @SuppressWarnings("SpellCheckingInspection") CategoriaEnum nombreCategoria) {

    }

    // Generar un accesador
    public List<Cliente> getListaClientes() {
        return listaClientes;
    }
}
