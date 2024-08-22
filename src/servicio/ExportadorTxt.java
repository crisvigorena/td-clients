package servicio;

import modelo.Cliente;
import utilidades.Utilidad;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**Se instancia la clase ExportadorTxt
 * que hereda de la clase Exportador
 */
public class ExportadorTxt extends Exportador {



    /**Se instancia el metodo Exportador txt.
     * */
    public ExportadorTxt(String directory) {
        super(directory);
        delimiter = "\t";
    }

    // Implementar el método para exportar datos
    /**
     * @param fileName
     * @param listaClientes
     */
    @Override
    public void exportar(String fileName, List<Cliente> listaClientes) {

        // Declarar variables de trabajo
        String[] create_file_result;
        String[] write_file_result;
        String file_contents;

        // Definir el mensaje por defecto que mostrar al usuario
        String user_msg = Utilidad.crearMensaje("export_menu_fail_txt", "");

        // Crear el directorio y archivo
        create_file_result = crearArchivo(fileName);

        // Comprobar el resultado de la operación de crear archivo. Si fue exitosa, continuar
        if (Objects.equals(create_file_result[0], "true")) {

            // Construir el contenido del archivo
            file_contents = buildTextData((ArrayList<Cliente>) listaClientes);

            // Grabar el contenido en el archivo
            write_file_result = writeBuffer(fileName, file_contents);

            // Comprobar el resultado de la operación de grabar el contenido del archivo
            if (Objects.equals(write_file_result[0], "true")) {

                // Recuperar el mensaje de éxito
                user_msg = Utilidad.crearMensaje("export_menu_success_txt", "");

            }
        }

        // Mostrar el mensaje al usuario
        System.out.println(user_msg);
    }
}
