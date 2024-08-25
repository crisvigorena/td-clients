package utilidades;


//Se instamcia la clase Utilidad
public class Utilidad {

    // Crear variables de clase
    static String new_line = System.lineSeparator();

    /**
     *  Como la consola de IntelliJ no responde a la instrucción de limpiar pantalla de un shell normal, es necesario
     *  simularlo insertando saltos de línea.
     */
// Crear un método que limpie la pantalla
    public static void limpiarConsolaIntellij() {

        // Definir la variable de trabajo
        StringBuilder newLine = new StringBuilder();

        // Imprimir 50 saltos de línea
        System.out.println(newLine.repeat(System.lineSeparator(), 50).toString());
    }

    // Crear un método para mostrar mensajes al usuario
    public static String crearMensaje(String message, String argument) throws IllegalStateException {

        // Definir variable de respuesta
        String answer;

        // Crear mensajes del Menú Principal
        String main_menu_1 = "%s%s1. Listar Clientes%s".formatted(new_line, new_line, new_line);
        String main_menu_2 = "2. Agregar Cliente%s".formatted(new_line);
        String main_menu_3 = "3. Editar Cliente%s".formatted(new_line);
        String main_menu_4 = "4. Cargar Datos%s".formatted(new_line);
        String main_menu_5 = "5. Exportar Datos%s".formatted(new_line);
        String main_menu_6 = "6. Salir%s%s".formatted(new_line, new_line);
        String main_menu_7 = "Ingrese una opción: ";
        String main_menu_8 = "Opción inválida. Ingrese una opción disponible en el menú (1 al 6): ";
        String main_menu = "%s%s%s%s%s%s%s".formatted(main_menu_1, main_menu_2, main_menu_3, main_menu_4, main_menu_5,
                main_menu_6, main_menu_7);

        // Crear mensajes del Menú Agregar Cliente
        String add_menu_title = "%s%s-------------Agregar Cliente-------------%s%s".formatted(new_line, new_line,
                new_line, new_line);
        String add_menu_run = "%sIngresa RUN del Cliente (formato: 12345678-9): ".formatted(new_line);
        String add_menu_name = "%sIngresa Nombre del Cliente: ".formatted(new_line);
        String add_menu_surname = "%sIngresa Apellido del Cliente: ".formatted(new_line);
        String add_menu_years = "%sIngresa Años Como Cliente: ".formatted(new_line);
        String add_menu_header = "%s%s".formatted(add_menu_title, add_menu_name);

        // Crear mensajes del Menú Editar Cliente
        String edit_menu_1 = "%s%s-------------Editar Cliente-------------%s%s".formatted(new_line, new_line,
                new_line, new_line);
        String edit_menu_2 = "Seleccione qué desea hacer:%s".formatted(new_line);
        String edit_menu_3 = "1.-Cambiar el estado del Cliente%s".formatted(new_line);
        String edit_menu_4 = "2.-Editar los datos ingresados del Cliente%s".formatted(new_line);
        String edit_menu_5 = main_menu_7;
        String edit_menu = "%s%s%s%s%s".formatted(edit_menu_1, edit_menu_2, edit_menu_3, edit_menu_4, edit_menu_5);
        String edit_menu_6 = "%s%s----------------------------------------%s%s".formatted(new_line, new_line,
                new_line, new_line);
        String edit_menu_7 = "Ingrese RUN del Cliente a editar (formato: 12345678-9): ";
        String edit_menu_get_run = "%s%s".formatted(edit_menu_6, edit_menu_7);
        String edit_menu_8 = "%s%s-----Actualizando estado del Cliente----%s";
        String edit_menu_9 = "El estado actual es: %s%s".formatted(argument, new_line);
        String edit_menu_10 = "1.-Si desea cambiar el estado del Cliente a %s%s".formatted(argument, new_line);
        String edit_menu_11 = "2.-Si desea mantener el estado del cliente (%s)%s".formatted(argument, new_line);
        String edit_menu_status_header = "%s%s".formatted(edit_menu_8, edit_menu_9);
        String edit_menu_status_choose = main_menu_7;
        String edit_menu_12 = "%s%s----Actualizando datos del Cliente-----%s%s".formatted(new_line, new_line,
                new_line, new_line);
        String edit_menu_13 = "1.-El RUN del Cliente es: %s%s".formatted(argument, new_line);
        String edit_menu_14 = "2.-El Nombre del Cliente es: %s%s".formatted(argument, new_line);
        String edit_menu_15 = "3.-El Apellido del Cliente es: %s%s".formatted(argument, new_line);
        String edit_menu_16 = "4.-Los años como Cliente son: %s%s".formatted(argument, new_line);
        String edit_menu_17 = "%sIngrese opción a editar de los datos del cliente: ".formatted(new_line);
        String edit_menu_18 = "%s1.-Ingrese nuevo RUN del Cliente (formato: 12345678-9): ".formatted(new_line);
        String edit_menu_19 = "%s2.-Ingrese nuevo Nombre del Cliente: ".formatted(new_line);
        String edit_menu_20 = "%s3.-Ingrese nuevo Apellido del Cliente: ".formatted(new_line);
        String edit_menu_21 = "%s4.-Ingrese nueva Cantidad de Años como Cliente: ".formatted(new_line);
        String edit_menu_22 = "%sDatos cambiados con éxito.%s".formatted(new_line, new_line);
        String edit_menu_23 = "%sHubo un error al intentar cambiar los datos.%s".formatted(new_line, new_line);

        // Crear mensajes del Menú Cargar Datos
        String load_menu_1 = "%s---------Cargar Datos-----------%s".formatted(new_line, new_line);
        String load_menu_2 = "%sIngresa la ruta en donde se encuentra el archivo DBClientes.csv%s".formatted(new_line, new_line);
        String load_menu_header = "%s%s".formatted(load_menu_1, load_menu_2);
        String load_menu_3 = "Ruta por defecto es [%s]. Ingresa la ruta deseada: ".formatted(argument);
        String load_menu_4 = edit_menu_6;
        String load_menu_5 = "%sDatos cargados correctamente en la lista.%s".formatted(new_line, new_line);
        String load_menu_6 = "%sHubo un error al intentar cargar los datos en la lista.%s".formatted(new_line, new_line);
        String load_menu_success = "%s%s".formatted(load_menu_4, load_menu_5);
        String load_menu_fail = "%s%s".formatted(load_menu_4, load_menu_6);

        // Crear mensajes del Menú Exportar Datos
        String export_menu_1 = "%s---------Exportar Datos-----------%s".formatted(new_line, new_line);
        String export_menu_2 = "Seleccione el formato a exportar:%s".formatted(new_line);
        String export_menu_3 = "1.-Formato csv%s".formatted(new_line);
        String export_menu_4 = "2.-Formato txt%s".formatted(new_line);
        String export_menu_5 = edit_menu_6;
        String export_menu_6 = main_menu_7;
        String export_menu_header = "%s%s%s%s%s%s".formatted(export_menu_1, export_menu_2, export_menu_3, export_menu_4, export_menu_5, export_menu_6);
        String export_menu_7 = "%s---------Exportar Datos-----------%s".formatted(new_line, new_line);
        String export_menu_8 = "%sIngresa la ruta en donde desea exportar el archivo clientes.csv: ".formatted(new_line);
        String export_menu_9 = "%sIngresa la ruta en donde desea exportar el archivo clientes.txt: ".formatted(new_line);
        String export_menu_10 = load_menu_3;
        String export_menu_csv = "%s%s%s".formatted(export_menu_7, export_menu_8, export_menu_10);
        String export_menu_txt = "%s%s%s".formatted(export_menu_7, export_menu_9, export_menu_10);
        String export_menu_11 = edit_menu_6;
        String export_menu_12 = "%sDatos de clientes exportados correctamente en formato csv.%s".formatted(new_line, new_line);
        String export_menu_13 = "%sHubo un error al intentar exportar los datos de los clientes en formato csv.%s".formatted(new_line, new_line);
        String export_menu_success_csv = "%s%s".formatted(export_menu_11, export_menu_12);
        String export_menu_fail_csv = "%s%s".formatted(export_menu_11, export_menu_13);
        String export_menu_14 = "%sDatos de clientes exportados correctamente en formato txt.%s".formatted(new_line, new_line);
        String export_menu_15 = "%sHubo un error al intentar exportar los datos de los clientes en formato txt.%s".formatted(new_line, new_line);
        String export_menu_success_txt = "%s%s".formatted(export_menu_11, export_menu_14);
        String export_menu_fail_txt = "%s%s".formatted(export_menu_11, export_menu_15);
        String end_program = "%s---------El programa ha terminado. Adios.-----------%s".formatted(new_line, new_line);

        // Construir el mensaje requerido
        answer = switch (message) {
            case "main_menu" -> main_menu;
            case "main_menu_fail" -> main_menu_8;
            case "add_menu_header" -> add_menu_header;
            case "add_menu_name" -> add_menu_name;
            case "add_menu_surname" -> add_menu_surname;
            case "add_menu_years" -> add_menu_years;
            case "edit_menu" -> edit_menu;
            case "edit_menu_get_run" -> edit_menu_get_run;
            case "edit_menu_status_header" -> edit_menu_status_header;
            case "edit_menu_status_change" -> edit_menu_10;
            case "edit_menu_status_keep" -> edit_menu_11;
            case "edit_menu_status_choose" -> edit_menu_status_choose;
            case "edit_menu_data_header" -> edit_menu_12;
            case "edit_menu_data_run" -> edit_menu_13;
            case "edit_menu_data_name" -> edit_menu_14;
            case "edit_menu_data_surname" -> edit_menu_15;
            case "edit_menu_data_years" -> edit_menu_16;
            case "edit_menu_data_choice" -> edit_menu_17;
            case "edit_menu_new_run" -> edit_menu_18;
            case "edit_menu_new_name" -> edit_menu_19;
            case "edit_menu_new_surname" -> edit_menu_20;
            case "edit_menu_new_years" -> edit_menu_21;
            case "edit_menu_success" -> edit_menu_22;
            case "edit_menu_fail" -> edit_menu_23;
            case "load_menu_header" -> load_menu_header;
            case "load_menu_input" -> load_menu_3;
            case "load_menu_success" -> load_menu_success;
            case "load_menu_fail" -> load_menu_fail;
            case "export_menu_header" -> export_menu_header;
            case "export_menu_csv" -> export_menu_csv;
            case "export_menu_txt" -> export_menu_txt;
            case "export_menu_success_csv" -> export_menu_success_csv;
            case "export_menu_success_txt" -> export_menu_success_txt;
            case "export_menu_fail_csv" -> export_menu_fail_csv;
            case "export_menu_fail_txt" -> export_menu_fail_txt;
            case "end_program" -> end_program;
            default -> "Mensaje no encontrado para: %s".formatted(message);
        };

        // Retornar la respuesta
        return answer;
    }
}
