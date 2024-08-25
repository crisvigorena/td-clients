package servicio;

import modelo.CategoriaEnum;
import modelo.Cliente;
import utilidades.Utilidad;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ClienteServicio {
    // Crear las variables de clase
    List<Cliente> listaClientes;


    // Crear el constructor
    public ClienteServicio(List<Cliente> listaClientes) {

        // Cargar la lista de clientes en el sistema
        this.listaClientes = new ArrayList<>(listaClientes);
    }

    // Crear un método para listar todos los clientes
    public void listarClientes() {

        // Primero revisar que la lista de Clientes no está vacía
        if (listaClientes.isEmpty()) {

            // Imprimir mensaje informando al usuario de que la lista está vacía
            System.out.println(Utilidad.crearMensaje("empty_list", ""));
        } else {

            // Caso contrario, listar todos los clientes con sus datos
            for (Cliente record : listaClientes) {
                System.out.println(record.toString());
            }
        }
    }

    // Crear un método para agregar nuevos clientes
    public void agregarCliente(String runCliente, String nombreCliente, String apellidoCliente,
                               @SuppressWarnings("SpellCheckingInspection") String aniosCliente,
                               @SuppressWarnings("SpellCheckingInspection") CategoriaEnum nombreCategoria) {

        // Crear variable de trabajo
        boolean success = true;
        String usr_msg = Utilidad.crearMensaje("add_menu_success", ""); // Éxito por defecto

        // Crear un objeto Cliente y agregarlo a la lista de Clientes
        try {
            success = listaClientes.add(new Cliente(runCliente, nombreCliente, apellidoCliente, aniosCliente, nombreCategoria));
        } catch (Exception exc_cause) {
            success = false;
        }

        // Elegir el mensaje a mostrar al usuario
        if (!success) {

            // Si el cliente NO fue agregado exitosamente, elegir el mensaje de error
            usr_msg = Utilidad.crearMensaje("add_menu_fail", "");
        }

        // Imprimir el mensaje al usuario
        System.out.println(usr_msg);
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

        // Crear variables de trabajo
        int item_index = -1;
        boolean answer = true;

        // Iterar en la lista de clientes
        for (Cliente record : listaClientes) {

            // Leer el RUN de cada elemento y compararlo con el RUN buscado
            if (Objects.equals(record.getRunCliente(), runCliente)) {

                // Si coincide, obtener el número de índice en el array
                try {
                    item_index = listaClientes.indexOf(record);
                } catch (Exception exc_cause) {
                    answer = false;
                }
            }
        }

        // Intentar eliminar el cliente usando el número de índice
        try {
            listaClientes.remove(item_index);
        } catch (Exception exc_cause) {
            answer = false;
        }

        // Retornar la respuesta
        return answer;
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
