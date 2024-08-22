package servicio;

import modelo.Cliente;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import utilidades.Utilidad;

/**Se crea la clase abstracta Exportador.
 */
public abstract class Exportador {

    // Crear variables de clase
    String separator = File.separator;
    String new_line = System.lineSeparator();
    static String dir_path;
    String file_path;
    String delimiter;
    ArrayList<Cliente> clientsList;
    String exc_cause;

    //Se crea el metodo exportador
    public Exportador(String directory) {
        dir_path = directory;
    }


    /**Se declara el metodo que setea el directorio para posteriormente
     * exportar la informacion.
     */
    public static void setDir_path(String dir_path) {
        Exportador.dir_path = dir_path;
    }

    /**
     * Se declara el metodo setter para la
     * lista de clientes
     */
    public void setClientsList(ArrayList<Cliente> clientsList) {
        this.clientsList = clientsList;
    }

    /**
     * Build text data string.
     *
     * @param clients_list the client list
     * @return the string
     */
// Crear un método que prepare la información del cliente que será exportada
    public String buildTextData(ArrayList<Cliente> clients_list) {

        // Definir las variables de trabajo y respuesta
        Cliente item;
        String line;
        setClientsList(clients_list);
        Iterator<Cliente> exportData = clientsList.iterator();
        StringBuilder answer = new StringBuilder();

        // Iterar sobre la lista de clientes para extraer la información
        while (exportData.hasNext()) {
            item = exportData.next();
            line = "%s%s%s%s%s%s%s%s%s%s".formatted(item.getRunCliente(), delimiter,
                    item.getNombreCliente(), delimiter,
                    item.getApellidoCliente(), delimiter,
                    item.getAniosCliente(), delimiter,
                    item.getNombreCategoria(), new_line);
            answer.append(line);
        }

        // Retornar el texto formateado
        return answer.toString();
    }

    /**
     * Crear archivo string [ ].
     *
     * @param fichero    the fichero
     * @return the string [ ]
     */
    public String[] crearArchivo(String fichero) {
        // Definir variables de trabajo y respuesta
        String directory = dir_path;
        file_path = "%s%s%s".formatted(directory, separator, fichero);
        File folder = new File(directory);
        File file = new File(file_path);
        boolean folder_created = false;
        boolean file_created = false;
        String[] answer = new String[2];

        // Crear mensajes al usuario
        String base_success_msg = "\nEl %s ha sido creado exitosamente.\n";
        String base_error_msg = "\nError al crear el %s.\n";
        String base_exists_error_msg = "\nEl %s ya existe.\n";
        String folder_str = "directorio";
        String file_str = "archivo";
        String folder_success_msg = base_success_msg.formatted(folder_str);
        String folder_error_msg = base_error_msg.formatted(folder_str);
        String folder_exists_error_msg = base_exists_error_msg.formatted(folder_str);
        String file_success_msg = base_success_msg.formatted(file_str);
        String file_error_msg = base_error_msg.formatted(file_str);
        String file_exists_error_msg = base_exists_error_msg.formatted(file_str);

        // Crear valores por defecto para la respuesta
        answer[0] = "true";
        answer[1] = "";

        // Comprobar si ya existe la carpeta. Si existe, saltar este bloque y guardar un mensaje de error para el
        // usuario. Caso contrario, crearlo
        if (!folder.exists()) {
            try {
                folder_created = folder.mkdirs();
            } catch (SecurityException folder_exc) {
                // Incluir en la respuesta si es que hubo un mensaje de error
                exc_cause = String.valueOf(folder_exc.getCause());
                answer[1] = "%s.%s%s".formatted(folder_error_msg, new_line, exc_cause);
            }
        } else answer[1] = folder_exists_error_msg;

        // Comprobar si ya existe el archivo. Si existe, saltar este bloque y guardar un mensaje de error para el
        // usuario. Caso contrario, crearlo
        if (!file.exists()) {
            try {
                file_created = file.createNewFile();
            } catch (SecurityException | IOException file_exc) {
                // Incluir en la respuesta si es que hubo un mensaje de error
                exc_cause = String.valueOf(file_exc.getCause());
                answer[1] = "%s.%s%s%s%s".formatted(answer[1], new_line, file_error_msg, new_line, exc_cause);
            }
        } else answer[1] = "%s.%s%s".formatted(answer[1], new_line, file_exists_error_msg);

        // Construir la respuesta. Esta debe incluir si la operación fue exitosa en el primer elemento del array.
        // Si hubo algún mensaje de error, este va en el segundo elemento del array
        answer[0] = "%s".formatted((folder_created && file_created));
        answer[1] = answer[0].equals("true") ? "%s%s%s".formatted(folder_success_msg, new_line, file_success_msg) : answer[1];

        // Retornar la respuesta
        return answer;
    }

    /**
     * Write buffer string [ ].
     *
     * @param file_name the file name
     * @param content   the content
     * @return the string [ ]
     */
    public String[] writeBuffer(String file_name, String content) {

        // Definir variables de trabajo y respuesta
        String directory = dir_path;
        file_path = "%s%s%s".formatted(directory, separator, file_name);
        File current_file = new File(file_path);
        FileWriter file_writer;
        BufferedWriter file_buffer;
        String[] answer = new String[2];

        // Crear mensajes para el usuario
        String success_msg = "El archivo fue escrito exitosamente.";
        String write_error_msg = "Error al intentar escribir el archivo.";

        // Crear respuesta por defecto
        answer[0] = "true";
        answer[1] = "";

        // Intentar escribir el archivo
        try {
            // Abrir el archivo para escritura
            file_writer = new FileWriter(current_file, StandardCharsets.UTF_8);
            file_buffer = new BufferedWriter(file_writer);

            // Escribir los datos e insertar un salto de línea
            file_buffer.write(content);

            // Cerrar el archivo
            file_buffer.close();

        }
        // Si se produce una excepción, preparar un mensaje de error
        catch (IOException io_exc) {

            answer[0] = "false";
            exc_cause = String.valueOf(io_exc.getCause());

            // Guardar mensaje de error en el segundo elemento de la respuesta
            answer[1] = "%s%s%s".formatted(write_error_msg, new_line, exc_cause);
        }

        // Preparar mensaje de éxito para el usuario
        answer[1] = answer[0].equals("true") ? success_msg : answer[1];

        // Retornar respuesta
        return answer;
    }

    /**
     * Exportar.
     *
     * @param fileName      the file name
     * @param listaClientes the lista clientes
     */
// Definir la firma de un método abstracto para exportar datos
    public abstract void exportar(String fileName, List<Cliente> listaClientes);
}

