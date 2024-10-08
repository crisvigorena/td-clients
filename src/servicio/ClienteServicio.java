package servicio;

import modelo.CategoriaEnum;
import modelo.Cliente;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ClienteServicio {

    // Crear las variables de trabajo
    List<Cliente> listaClientes;


    // Crear el constructor
    public ClienteServicio(List<Cliente> listaClientes) {
        this.listaClientes = new ArrayList<>(listaClientes);
    }

    // Crear un método para listar todos los clientes
    public void listarClientes() {
        for (Cliente record: listaClientes) {
            System.out.println(record.toString());
        }
    }

    // Crear un método para agregar nuevos clientes
    public void agregarCliente(String runCliente, String nombreCliente, String apellidoCliente,
                   @SuppressWarnings("SpellCheckingInspection") String aniosCliente,
                   @SuppressWarnings("SpellCheckingInspection") CategoriaEnum nombreCategoria) {

        // Crear un objeto Cliente y agregarlo a la lista de Clientes
        listaClientes.add(new Cliente(runCliente, nombreCliente, apellidoCliente, aniosCliente, nombreCategoria));
    }

    // Crear un método para editar los datos de un cliente
    public void editarCliente(String runCliente, String nombreCliente, String apellidoCliente,
                   @SuppressWarnings("SpellCheckingInspection") String aniosCliente,
                   @SuppressWarnings("SpellCheckingInspection") CategoriaEnum nombreCategoria) {
        for (Cliente record: listaClientes) {
            if (Objects.equals(record.getRunCliente(), runCliente)) {
                record.setNombreCliente(nombreCliente);
                record.setApellidoCliente(apellidoCliente);
                record.setAniosCliente(aniosCliente);
                record.setNombreCategoria(nombreCategoria);
            }
        }
    }

    // Generar un accesador
    public List<Cliente> getListaClientes() {
        return listaClientes;
    }
}
