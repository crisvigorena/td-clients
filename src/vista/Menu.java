package vista;

import servicio.ArchivoServicio;
import servicio.ClienteServicio;
import servicio.ExportadorCsv;
import servicio.ExportadorTxt;

import java.util.Scanner;

public class Menu {

    // Crear las variables de clase
    ClienteServicio clienteServicio = new ClienteServicio();
    ArchivoServicio archivoServicio = new ArchivoServicio();
    ExportadorCsv exportadorCsv = new ExportadorCsv();
    ExportadorTxt exportadorTxt = new ExportadorTxt();
    String fileName = "Clientes";
    String fileName1 = "DBClientes.csv";
    Scanner scanner = new Scanner(System.in);

    // Crear método para desplegar el menú
    public void iniciarMenu() {

    }

    // Crear un método para listar los clientes
    public String listarClientes() {

        // Retornar listado
        return "";
    }

    // Crear un método para agregar un nuevo cliente
    public String[] agregarCliente() {

        // Retornar resultado y mensajes al usuario
        return new String[] {""};
    }

    // Crear un método para editar los datos de un cliente
    public String[] editarCliente() {

        // Retornar resultado y mensajes al usuario
        return new String[] {""};
    }

    // Crear un método para editar los datos de un cliente
    public String[] importarDatos() {

        // Retornar resultado y mensajes al usuario
        return new String[] {""};
    }

    // Crear un método para editar los datos de un cliente
    public String[] exportarDatos() {

        // Retornar resultado y mensajes al usuario
        return new String[] {""};
    }

    // Crear un método para editar los datos de un cliente
    public String[] terminarPrograma() {

        // Retornar resultado y mensajes al usuario
        return new String[] {""};
    }
}
