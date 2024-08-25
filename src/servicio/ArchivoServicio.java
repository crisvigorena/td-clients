package servicio;

import modelo.CategoriaEnum;
import modelo.Cliente;
import utilidades.Utilidad;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class ArchivoServicio extends Exportador {

    // Crear una variable de clase para contener la lista de clientes leída desde el archivo CSV
    ArrayList<Cliente> clientsList = new ArrayList<>();

    public ArchivoServicio(String directory) {
        super(directory);
        delimiter = ",";
    }


    private String[] readFile(String file_name) {
        // Definir variables de trabajo y respuesta
        String directory = dir_path;
        file_path = file_name;
        File current_file = new File(file_path);
        FileReader file_reader;
        BufferedReader file_buffer;
        String new_datum;
        String[] answer = new String[3];

        // Crear mensajes para el usuario
        String success_msg = "El archivo fue leído exitosamente.";
        String read_error_msg = "Error al intentar leer el archivo.";

        // Crear respuesta por defecto
        answer[0] = "true";
        answer[1] = "";
        answer[2] = "";

        // Intentar leer el archivo
        try {
            // Abrir el archivo
            file_reader = new FileReader(current_file, StandardCharsets.UTF_8);
            file_buffer = new BufferedReader(file_reader);

            // Leer línea por línea
            new_datum = file_buffer.readLine();
            while (new_datum != null) {
                if (answer[2].isEmpty()) {
                    answer[2] = new_datum;
                } else {
                    answer[2] = "%s%s%s".formatted(answer[2], "|", new_datum);
                }
                new_datum = file_buffer.readLine();
            };

            // Cerrar el archivo
            file_buffer.close();
            file_reader.close();

        } catch (IOException io_exc) {
            // Si se produce una excepción, preparar un mensaje de error
            answer[0] = "false";
            exc_cause = String.valueOf(io_exc.getCause());
            answer[1] = "%s.%s%s".formatted(read_error_msg, new_line, exc_cause);
        }

        // Preparar mensaje de éxito para el usuario
        answer[1] = answer[0].equals("true") ? success_msg : answer[1];

        // Retornar respuesta
        return answer;
    }

    private ArrayList<String> stringToArray(String content) {
        // Definir variables de trabajo y respuesta
        ArrayList<String> all_lines;
        ArrayList<ArrayList<String>> answer = new ArrayList<>();

        // Separar cada elemento usando el caracter "|" y guardarlos en 'all_lines'
        all_lines =  new ArrayList<String>(Arrays.asList(content.split("\\|")));

        // Iterar sobre cada uno de los elementos de "all_lines". Cada elemento corresponde a un cliente
        for (String item: all_lines) {
            answer.add(new ArrayList<>(Arrays.asList(item.split(","))));
        }

        // Retornar la respuesta
        return all_lines;
    }

    // Crear un método para cargar datos por lotes en el sistema, leyendo desde un archivo CSV
    private ArrayList<String> cargarDatos(String fileName) {

        // Definir variables de trabajo y respuesta
        String[] operation_result;
        ArrayList<String> answer = new ArrayList<>();

        // Leer los datos desde el archivo CSV
        operation_result = readFile(fileName);

        // Agregar el resultado de la operación en la respuesta
        answer.add(operation_result[0]);

        // Comprobar el resultado de la operación. Si fue exitosa, continuar procesando los datos
        if (Objects.equals(operation_result[0], "true")) {

            // Convertir los datos leídos en un ArrayList que contenga los datos separados por tipo y por cliente,
            // y agregarlos a la respuesta
            answer.addAll(stringToArray(operation_result[2]));
        }

        // Retornar los datos listos para ser importados en el sistema
        return answer;
    }

    // Implementar el método para exportar datos
    /**
     * @param fileName
     * @param listaClientes
     */
    @Override
    public void exportar(String fileName, List<Cliente> listaClientes) {

        // Definir variables de trabajo
        String user_msg;
        String operation_result;
        ArrayList<String> loaded_data;
        String[] line_data;
        Cliente temp_client;

        // Definir el mensaje por defecto a mostrar al usuario (mensaje de error)
        user_msg = Utilidad.crearMensaje("load_menu_fail", "");

        // Cargar los datos desde el archivo CSV
        loaded_data = cargarDatos(fileName);

        // Extraer el primer elemento de 'loaded_data' ya que contiene el resultado de la operación de lectura
        operation_result = loaded_data.removeFirst();

        // Comprobar el resultado de la operación
        if (Objects.equals(operation_result, "true")) {

            // Si la operación fue exitosa, continuar procesando los datos.
            // Iterar sobre los datos leídos desde el archivo (por cada Cliente)
            for (String item : loaded_data) {

                // Guardar los datos del cliente en un objeto Cliente
                line_data = item.split(",");
                temp_client = new Cliente(line_data[0], line_data[1], line_data[2],
                        line_data[3], CategoriaEnum.valueOf(line_data[4]));

                // Agregar el Cliente a la lista de clientes
                clientsList.add(temp_client);
            }

            // Luego, recuperar el mensaje de éxito para mostrar al usuario
            user_msg = Utilidad.crearMensaje("load_menu_success", "");
        }

        // Finalmente, imprimir el mensaje al usuario
        System.out.println(user_msg);
    }

    // Crear un método para enmascarar ese horrible y poco intuitivo método 'exportar' en una clase de importación
    public void importar(String filepath, List<Cliente> listaClientes) {

        // Pasar los argumentos al método 'exportar'
        exportar(filepath, listaClientes);
    }

    public ArrayList<Cliente> getClientsList() {
        return clientsList;
    }
}
