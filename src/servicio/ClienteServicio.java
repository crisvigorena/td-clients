package servicio;

//Se importan los archivos necesarios
import modelo.CategoriaEnum;
import modelo.Cliente;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


//Se declaran la clase ClienteServicio
public class ClienteServicio {

    /**Se declara la lista Cliente
     * con una variable listaClientes
     */
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

        //Iterar sobre la lista cliente y para guardar los datos editados
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
