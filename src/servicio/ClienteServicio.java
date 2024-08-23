package servicio;

import modelo.CategoriaEnum;
import modelo.Cliente;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ClienteServicio {
    // Crear las variables de clase
    List<Cliente> listaClientes;


    // Crear el constructor
    public ClienteServicio(List<Cliente> listaClientes) {
        this.listaClientes = new ArrayList<>(listaClientes);
    }

    // Crear un método para listar todos los clientes
    public void listarClientes() {
        for (Cliente record : listaClientes) {
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

    // Crear un método que importe un solo objeto Cliente
    public boolean importarUnCliente(Cliente nuevoCliente) {

        // Agregar el nuevo cliente directamente en la lista
        try {
            listaClientes.add(nuevoCliente);
        } catch (Exception exc_cause) {

            // Si hubo algún error retornar false
            return false;
        }

        // Caso contrario, retornar true
        return true;
    }

    // Crear un método para editar los datos de un cliente
    public void editarCliente(String runCliente, String nombreCliente, String apellidoCliente,
                              @SuppressWarnings("SpellCheckingInspection") String aniosCliente,
                              @SuppressWarnings("SpellCheckingInspection") CategoriaEnum nombreCategoria) {
        for (Cliente record : listaClientes) {
            if (Objects.equals(record.getRunCliente(), runCliente)) {
                record.setNombreCliente(nombreCliente);
                record.setApellidoCliente(apellidoCliente);
                record.setAniosCliente(aniosCliente);
                record.setNombreCategoria(nombreCategoria);
            }
        }
    }

    // Crear un método para obtener los datos de un cliente
    public Cliente buscarCliente(String runCliente) {

        // Iterar en la lista de clientes
        for (Cliente record : listaClientes) {

            // Leer el RUN de cada elemento y compararlo con el RUN buscado
            if (Objects.equals(record.getRunCliente(), runCliente)) {

                // Si coincide, retornar el cliente
                return record;
            }
        }

        // Si no encuentra el cliente, retornar null
        return null;
    }


    // Crear un método para eliminar un cliente
    public boolean eliminarCliente(String runCliente) {

        // Iterar en la lista de clientes
        for (Cliente record : listaClientes) {

            // Leer el RUN de cada elemento y compararlo con el RUN buscado
            if (Objects.equals(record.getRunCliente(), runCliente)) {

                // Si coincide, remover el cliente de la lista
                try {
                    listaClientes.remove(record);
                } catch (Exception exc_cause) {
                    return false;
                }
            }
        }

        // Si no puede encontrar el cliente, retornar false
        return false;
    }

    // Crear un método para importar una lista de clientes
    public void cargarListaDeClientes(ArrayList<Cliente> listaDeClientes) {

        // Agregar todos los clientes de la lista
        listaClientes.addAll(listaDeClientes);
    }

    // Generar un accesador
    public List<Cliente> getListaClientes() {
        return listaClientes;
    }
}
