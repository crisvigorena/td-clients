package servicio;
//Se importan las variables a manipular
import modelo.CategoriaEnum;
import modelo.Cliente;
import utilidades.Utilidad;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;


/**
 * Se declara la clase ArchivoServicio que hereda de
 * la clase Exportador
 */
public class ArchivoServicio extends Exportador {

    /**Se implementa el constructor ArchivoServicio que
    entrega el valor del String directory a la clase padre
     */
    public ArchivoServicio(String directory) {
        super(directory);
        delimiter = ",";
    }

    /**Se implementa el metodo readFile que se encarga de leer valores y
    de retornar un String de nombre answer
     */
    private static String[] readFile(String file_name) {
        // Definir variables de trabajo y respuesta
        String directory = dir_path;
        file_path = "%s%s%s".formatted(directory, separator, file_name);
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
            do {
                new_datum = file_buffer.readLine();
                answer[2] = "%s%s%s".formatted(answer[2], "|", new_datum);
            } while (new_datum != null);

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

    private static ArrayList<String> stringToArray(String content) {
        // Definir variables de trabajo y respuesta
        ArrayList<String> all_lines;
        ArrayList<ArrayList<String>> answer = new ArrayList<>();

        // Separar cada elemento usando el caracter "|" y guardarlos en 'all_lines'
        all_lines =  new ArrayList<String>(Arrays.asList(content.split("\\|")));

        // Iterar sobre cada uno de los elementos de "all_lines". Cada elemento corresponde a un cliente
        for (String item: all_lines) {
            answer.add((ArrayList<String>) Arrays.asList(item.split(",")));
        }

        // Retornar la respuesta
        return all_lines;
    }

    // Crear un método para cargar datos por lotes en el sistema, leyendo desde un archivo CSV
    public ArrayList<ArrayList<String>> cargarDatos(String fileName) {

        // Definir variables de trabajo y respuesta
        String[] operation_result;
        ArrayList<ArrayList<String>> answer = new ArrayList<>();

        // Leer los datos desde el archivo CSV
        operation_result = readFile(fileName);

        // Convertir el array en ArrayList y agregarlo como primer elemento en la respuesta
        answer.add((ArrayList<String>) Arrays.asList(operation_result));

        // Comprobar el resultado de la operación. Si fue exitosa, continuar procesando los datos
        if (Objects.equals(operation_result[0], "true")) {

            // Convertir los datos leídos en un ArrayList que contenga los datos separados por tipo y por cliente,
            // y agregarlos a la respuesta
            answer.add(stringToArray(operation_result[2]));
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
        ArrayList<String> operation_result;
        ArrayList<ArrayList<String>> loaded_data;
        Cliente temp_client;
        ArrayList<Cliente> loaded_clients = new ArrayList<>();

        // Definir el mensaje por defecto a mostrar al usuario (mensaje de error)
        user_msg = Utilidad.crearMensaje("load_menu_fail", "");

        // Cargar los datos desde el archivo CSV
        loaded_data = cargarDatos(fileName);

        // Extraer el primer elemento de 'loaded_data' ya que contiene el resultado de la operación de lectura
        operation_result = loaded_data.removeFirst();

        // Comprobar el resultado de la operación
        if (Objects.equals(operation_result.getFirst(), "true")) {

            // Si la operación fue exitosa, continuar procesando los datos.
            // Iterar sobre los datos leídos desde el archivo (por cada Cliente)
            for (ArrayList<String> item : loaded_data) {

                // Guardar los datos del cliente en un objeto Cliente
                temp_client = new Cliente(item.get(0), item.get(1), item.get(2),
                        item.get(3), CategoriaEnum.valueOf(item.get(4)));

                // Agregar el Cliente a la lista de clientes
                loaded_clients.add(temp_client);
            }

            // Luego, recuperar el mensaje de éxito para mostrar al usuario
            user_msg = Utilidad.crearMensaje("load_menu_success", "");

        }

        // Finalmente, imprimir el mensaje al usuario
        System.out.println(user_msg);
    }
}
