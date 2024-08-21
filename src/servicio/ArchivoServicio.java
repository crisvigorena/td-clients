package servicio;

import modelo.Cliente;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class ArchivoServicio extends Exportador {

    // Crear variables de clase
    static String user_directory = System.getProperty("user.dir");
    static String separator = File.separator;
    static String src_directory = "%s%ssrc%s".formatted(user_directory, separator, separator);
    static String new_line = System.lineSeparator();
    static String exc_cause;

    public ArchivoServicio(String directory) {
        super(directory);
    }


    private static String[] readFile(String directory, String file_name) {
        // Definir variables de trabajo y respuesta
        String folder_path = "%s%s".formatted(src_directory, directory);
        String file_path = "%s%s%s".formatted(folder_path, separator, file_name);
        File current_file = new File(file_path);
        FileReader file_reader = null;
        BufferedReader file_buffer = null;
        String new_datum = "";
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
                answer[2] = "%s%s%s".formatted(answer[2], separator, new_datum);
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
        ArrayList<String> answer;

        // Separar cada elemento usando el salto de línea y guardarlos en 'answer'
        answer =  new ArrayList<String>(Arrays.asList(content.split(separator)));

        // Retornar la respuesta
        return answer;
    }

    // Crear un método para cargar datos por lotes en el sistema
    public void cargarDatos(String fileName) {

    }

    // Implementar el método para exportar datos
    /**
     * @param fileName
     * @param listaClientes
     */
    @Override
    public void exportar(String fileName, List<Cliente> listaClientes) {

    }
}
