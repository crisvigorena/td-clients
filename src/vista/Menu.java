package vista;

import modelo.CategoriaEnum;
import modelo.Cliente;
import servicio.ArchivoServicio;
import servicio.ClienteServicio;
import servicio.ExportadorCsv;
import servicio.ExportadorTxt;
import utilidades.Utilidad;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Menu {

    // Crear las variables de clase
    ArrayList<Cliente> listaClientes = new ArrayList<>();
    String separator = File.separator;
    String defaultDirectory = "%s%s%s".formatted(System.getProperty("user.dir"), separator, "static");
    String fileName = "Clientes";
    String fileName1 = "DBClientes.csv";
    String default_import_file = "%s%s%s".formatted(defaultDirectory, separator, fileName1);
    String default_export_file = "%s%s%s".formatted(defaultDirectory, separator, fileName);
    Scanner scanner = new Scanner(System.in, StandardCharsets.UTF_8);
    ClienteServicio clienteServicio = new ClienteServicio(listaClientes);
    ArchivoServicio archivoServicio = new ArchivoServicio(defaultDirectory);
    ExportadorCsv exportadorCsv = new ExportadorCsv(defaultDirectory);
    ExportadorTxt exportadorTxt = new ExportadorTxt(defaultDirectory);

    public Menu(ArrayList<Cliente> listaClientes) {
        this.listaClientes = listaClientes;
        iniciarMenu();
    }

    // Crear método para desplegar el menú
    public void iniciarMenu() {

        // Crear las variables de trabajo
        String choice_num;
        boolean valid_choice;

        // Desplegar el Menú principal
        System.out.println(Utilidad.crearMensaje("main_menu", ""));

        // Pedir al usuario que elija una opción y validar que está entre las opciones disponibles.
        do {
            // Leer elección del usuario desde el teclado
            choice_num = scanner.next();

            // Comprobar que el usuario solo ingresa un número válido del menú
            valid_choice = choice_num.matches("^[123456]$");

            // Si el usuario no elige una opción válida volver a imprimir el mensaje pidiendo que elija
            if (!valid_choice) {
                System.out.println(Utilidad.crearMensaje("main_menu_fail", ""));
            }

        } while (!valid_choice);

        // Según la selección del usuario, redirigir al método apropiado
        switch (choice_num) {
            case "1" -> listarClientes();
            case "2" -> agregarCliente();
            case "3" -> editarCliente();
            case "4" -> importarDatos();
            case "5" -> exportarDatos();
            case "6" -> terminarPrograma();
        }

    }

    // Crear un método que retorne al Menú Principal cuando el usuario esté listo para volver
    private void retornarMenuPrincipal() {

        // Crear las variables de trabajo
        String choice_str;
        boolean valid_choice;

        // Imprimir mensaje y validar elección
        do {
            // Mostrar mensaje al usuario
            System.out.println(Utilidad.crearMensaje("return_main_menu", ""));

            // Leer elección del usuario desde el teclado
            choice_str = scanner.next();

            // Comprobar que el usuario solo ingresa la opción disponible
            valid_choice = choice_str.matches("^[sS]$");

        } while (!valid_choice);

        // Cuando el usuario ingrese la opción disponible, limpiar la consola
        Utilidad.limpiarConsolaIntellij();

        // Volver a mostrar el Menú Principal
        iniciarMenu();
    }

    // Crear un método para listar los clientes
    public void listarClientes() {

        // Listar los datos de todos los clientes
        clienteServicio.listarClientes();

        // Retornar al Menú Principal cuando el usuario lo pida
        retornarMenuPrincipal();
    }

    // Crear un método para agregar un nuevo cliente
    public void agregarCliente() {

        // Crear las variables de trabajo
        String choice_run;
        String choice_name;
        String choice_surname;
        String choice_years;
        CategoriaEnum client_category;
        boolean valid_choice;
        String regex_run = "^[\\d]{6,10}-\\d$";
        String regex_letters = "^[\\p{L}]+$";
        String regex_years = "^[\\d]+$";

        // Imprimir mensajes y validar elección
        do {
            // Mostrar el encabezado y el mensaje para ingresar RUN
            System.out.println(Utilidad.crearMensaje("add_menu_header", ""));

            // Leer elección del usuario desde el teclado
            choice_run = scanner.next();

            // Comprobar que el usuario solo ingresa la opción disponible
            valid_choice = choice_run.matches(regex_run);

        } while (!valid_choice);


        // Imprimir mensajes y validar elección
        do {
            // Mostrar el mensaje para ingresar el Nombre
            System.out.println(Utilidad.crearMensaje("add_menu_name", ""));

            // Leer elección del usuario desde el teclado
            choice_name = scanner.next();

            // Comprobar que el usuario solo ingresa la opción disponible
            valid_choice = choice_name.matches(regex_letters);

        } while (!valid_choice);


        // Imprimir mensajes y validar elección
        do {
            // Mostrar el mensaje para ingresar el Apellido
            System.out.println(Utilidad.crearMensaje("add_menu_surname", ""));

            // Leer elección del usuario desde el teclado
            choice_surname = scanner.next();

            // Comprobar que el usuario solo ingresa la opción disponible
            valid_choice = choice_surname.matches(regex_letters);

        } while (!valid_choice);


        // Imprimir mensajes y validar elección
        do {
            // Mostrar el mensaje para ingresar los Años como Cliente
            System.out.println(Utilidad.crearMensaje("add_menu_years", ""));

            // Leer elección del usuario desde el teclado
            choice_years = scanner.next();

            // Comprobar que el usuario solo ingresa la opción disponible
            valid_choice = choice_years.matches(regex_years);

        } while (!valid_choice);

        // Todo nuevo cliente es clasificado en la categoría Activo
        client_category = CategoriaEnum.Activo;

        // Crear el nuevo Cliente
        clienteServicio.agregarCliente(choice_run, choice_name, choice_surname, choice_years, client_category);

        // Recuperar la lista de clientes actualizada y cargarla en esta clase
        listaClientes = (ArrayList<Cliente>) clienteServicio.getListaClientes();

        // Retornar al Menú Principal cuando el usuario lo pida
        retornarMenuPrincipal();
    }

    // Crear un método para editar los datos de un cliente
    public void editarCliente() {

        // Crear las variables de trabajo
        String choice_edit;
        String choice_run;
        String choice_edit_status;
        String choice_edit_data;
        String choice_edit_run;
        String choice_edit_name;
        String choice_edit_surname;
        String choice_edit_years;
        CategoriaEnum current_state;
        CategoriaEnum opposite_state;
        Cliente client_data;
        boolean valid_choice;
        boolean client_removed;
        boolean client_added;
        boolean final_usr_msg_state;
        String final_usr_msg;
        String regex_run = "^[\\d]{6,10}-\\d$";
        String regex_letters = "^[\\p{L}]+$";
        String regex_years = "^[\\d]+$";

        // Imprimir mensajes y validar elección
        do {
            // Mostrar el Menú Editar
            System.out.println(Utilidad.crearMensaje("edit_menu", ""));

            // Leer elección del usuario desde el teclado
            choice_edit = scanner.next();

            // Comprobar que el usuario solo ingresa una de las opciones disponibles
            valid_choice = choice_edit.matches("^[12]$");

        } while (!valid_choice);

        // Imprimir mensajes y validar elección
        do {
            // Mostrar el encabezado y el mensaje para ingresar RUN
            System.out.println(Utilidad.crearMensaje("edit_menu_get_run", ""));

            // Leer elección del usuario desde el teclado
            choice_run = scanner.next();

            // Comprobar que el usuario solo ingresa la opción disponible
            valid_choice = choice_run.matches(regex_run);

        } while (!valid_choice);

        // Buscar el cliente
        client_data = clienteServicio.buscarCliente(choice_run);

        // Comprobar si el cliente fue encontrado
        if (client_data != null) {

            // Si lo fue, comprobar elección del usuario.
            if (choice_edit.equals("1")) {

                // Si el cliente desea cambiar el Estado del cliente, recuperar la categoría actual
                current_state = client_data.getNombreCategoria();

                // Determinar la categoría opuesta
                if (current_state == CategoriaEnum.Activo) {

                    // Si está Activo, el opuesto es Inactivo
                    opposite_state = CategoriaEnum.Inactivo;

                  // Caso contrario, fijar en Activo
                } else opposite_state = CategoriaEnum.Activo;

                // Imprimir mensajes y validar elección
                do {
                    // Mostrar al usuario la categoría actual
                    System.out.println(Utilidad.crearMensaje("edit_menu_status_header",
                            String.valueOf(current_state)));

                    // Mostrar al usuario el mensaje de cambiar categoría
                    System.out.println(Utilidad.crearMensaje("edit_menu_status_change",
                            String.valueOf(opposite_state)));

                    // Mostrar al usuario el mensaje de mantener la categoría
                    System.out.println(Utilidad.crearMensaje("edit_menu_status_keep",
                            String.valueOf(current_state)));

                    // Mostrar al usuario el mensaje para pedir que elija una opción
                    System.out.println(Utilidad.crearMensaje("edit_menu_status_choose",""));

                    // Leer elección del usuario desde el teclado
                    choice_edit_status = scanner.next();

                    // Comprobar que el usuario solo ingresa una de las opciones disponibles
                    valid_choice = choice_edit_status.matches("^[12]$");

                } while (!valid_choice);

                // Comprobar si el usuario quiere cambiar el estado y hacerlo
                if (choice_edit_status.equals("1")) {

                    // Cambiar la categoría, fijando la opuesta
                    client_data.setNombreCategoria(opposite_state);

                // Caso contrario, volver al Menú principal
                } else {

                    // Retornar al Menú Principal cuando el usuario lo pida
                    retornarMenuPrincipal();
                }

            // El usuario quiere cambiar otros datos del Cliente, en vez del Estado
            } else {

                // Imprimir mensajes y validar elección
                do {

                    // Presentar la información registrada actualmente para ese cliente
                    System.out.print(Utilidad.crearMensaje("edit_menu_data_header", ""));
                    System.out.print(Utilidad.crearMensaje("edit_menu_data_run", client_data.getRunCliente()));
                    System.out.print(Utilidad.crearMensaje("edit_menu_data_name", client_data.getNombreCliente()));
                    System.out.print(Utilidad.crearMensaje("edit_menu_data_surname", client_data.getApellidoCliente()));
                    System.out.print(Utilidad.crearMensaje("edit_menu_data_years", client_data.getAniosCliente()));
                    System.out.print(Utilidad.crearMensaje("edit_menu_data_choice", ""));

                    // Leer elección del usuario desde el teclado
                    choice_edit_data = scanner.next();

                    // Comprobar que el usuario solo ingresa una de las opciones disponibles
                    valid_choice = choice_edit_data.matches("^[1234]$");

                } while (!valid_choice);

                // Comprobar qué eligió el usuario
                switch (choice_edit_data) {

                    // El usuario quiere cambiar el RUN
                    case "1" -> {
                        do {
                            // Presentar el mensaje
                            System.out.print(Utilidad.crearMensaje("edit_menu_new_run", client_data.getRunCliente()));

                            // Leer elección del usuario desde el teclado
                            choice_edit_run = scanner.next();

                            // Comprobar que el usuario solo ingresa el formato válido
                            valid_choice = choice_edit_run.matches(regex_run);

                        } while (!valid_choice);

                        // Actualizar el RUN del Cliente
                        client_data.setRunCliente(choice_edit_run);

                        // Como el RUT cambió,
                    }

                    // El usuario quiere cambiar el Nombre
                    case "2" -> {
                        do {
                            // Presentar el mensaje
                            System.out.print(Utilidad.crearMensaje("edit_menu_new_name", client_data.getNombreCliente()));

                            // Leer elección del usuario desde el teclado
                            choice_edit_name = scanner.next();

                            // Comprobar que el usuario solo ingresa el formato válido
                            valid_choice = choice_edit_name.matches(regex_letters);

                        } while (!valid_choice);

                        // Actualizar el Nombre del Cliente
                        client_data.setNombreCliente(choice_edit_name);
                    }

                    // El usuario quiere cambiar el Apellido
                    case "3" -> {
                        do {
                            // Presentar el mensaje
                            System.out.print(Utilidad.crearMensaje("edit_menu_new_surname", client_data.getApellidoCliente()));

                            // Leer elección del usuario desde el teclado
                            choice_edit_surname = scanner.next();

                            // Comprobar que el usuario solo ingresa el formato válido
                            valid_choice = choice_edit_surname.matches(regex_letters);

                        } while (!valid_choice);

                        // Actualizar el Apellido del Cliente
                        client_data.setApellidoCliente(choice_edit_surname);
                    }

                    // El usuario quiere cambiar los Años como Cliente
                    case "4" -> {
                        do {
                            // Presentar el mensaje
                            System.out.print(Utilidad.crearMensaje("edit_menu_new_years", client_data.getAniosCliente()));

                            // Leer elección del usuario desde el teclado
                            choice_edit_years = scanner.next();

                            // Comprobar que el usuario solo ingresa el formato válido
                            valid_choice = choice_edit_years.matches(regex_years);

                        } while (!valid_choice);

                        // Actualizar el Apellido del Cliente
                        client_data.setAniosCliente(choice_edit_years);
                    }
                }
            }
        }

        // Como client_data es directamente un elemento en la lista de clientes de clienteServicio, cualquier cambio
        // altera directamente la lista original. Entonces no es necesario eliminar el registro anterior a
        // la modificación de datos

        // Crear el mensaje final al usuario con el resultado de la operación
        final_usr_msg = Utilidad.crearMensaje("edit_menu_success", "");

        // Imprimir el mensaje final
        System.out.println(final_usr_msg);

        // Finalmente, retornar al Menú Principal cuando el usuario lo pida
        retornarMenuPrincipal();
    }

    // Crear un método para importar datos de múltiples clientes desde un archivo CSV
    public void importarDatos() {

        // Crear variables de trabajo
        String choice_path;
        boolean valid_choice;

        // Imprimir mensajes y validar elección
        do {
            // Mostrar el encabezado y pedir al usuario que ingrese la ruta de importación
            System.out.println(Utilidad.crearMensaje("load_menu_header", ""));
            System.out.println(Utilidad.crearMensaje("load_menu_input", default_import_file));

            // Leer elección del usuario desde el teclado
            choice_path = scanner.next();

            // Comprobar si el usuario no escribió nada, entonces interpretarlo como que acepta la ruta por defecto
            if (Objects.equals(choice_path, "")) {
                choice_path = default_import_file;
            }

            // Comprobar que el usuario solo ingresa la opción disponible
            valid_choice = choice_path.matches("^[.\\p{Alnum}\\p{Zs}/\"´:\\\\_-]+$");

        } while (!valid_choice);

        // Leer los datos desde el archivo
        archivoServicio.importar(choice_path, listaClientes);

        // Recuperar la lista de Clientes desde la clase importadora
        listaClientes = archivoServicio.getClientsList();

        // Cargar la lista de clientes en clienteServicio para que el listado funcione
        clienteServicio.cargarListaDeClientes(listaClientes);

        // Finalmente, retornar al Menú Principal cuando el usuario lo pida
        retornarMenuPrincipal();
    }

    // Crear un método para exportar los datos de todos los clientes a un archivo CSV o TXT
    public void exportarDatos() {

        // Crear variables de trabajo
        String new_line = System.lineSeparator();
        String choice_path;
        String chosen_format = "export_menu_csv";
        String file_extension = ".csv";
        boolean valid_choice;
        String filename_regex;
        String[] chosen_directory;

        // Imprimir mensajes y validar elección de formato de archivo
        do {
            // Mostrar el encabezado y pedir al usuario que ingrese la ruta de exportación
            System.out.println(Utilidad.crearMensaje("export_menu_header", ""));

            // Leer elección del usuario desde el teclado
            choice_path = scanner.next();

            // Comprobar que el usuario solo ingresa la opción disponible
            valid_choice = choice_path.matches("^[12]$");

        } while (!valid_choice);

        // Si el usuario eligió formato TXT, reasignar la variable
        if (choice_path.equals("2")) {
            chosen_format = "export_menu_txt";
            file_extension = ".txt";
        }

        // Imprimir mensajes y validar elección de ruta de exportación
        do {
            // Mostrar el encabezado y pedir al usuario que ingrese la ruta de exportación
            default_export_file = "%s%s".formatted(default_export_file, file_extension);
            System.out.println(Utilidad.crearMensaje(chosen_format, default_export_file));

            // Leer elección del usuario desde el teclado
            choice_path = scanner.next();

            // Comprobar si el usuario no escribió nada, entonces interpretarlo como que acepta la ruta por defecto
            if (Objects.equals(choice_path, new_line)) {
                choice_path = default_export_file;
            }

            // Comprobar que el usuario solo ingresa la opción disponible
            valid_choice = choice_path.matches("^[.\\p{Alnum}\\p{Zs}/\"´:\\\\_-]+$");

        } while (!valid_choice);

        // Separar el nombre del archivo de la ruta ingresada por el usuario
        filename_regex = "%s%s%s".formatted(separator, fileName, file_extension);
        chosen_directory = choice_path.split(filename_regex);

        // Exportar los datos al archivo
        if (file_extension.equals(".csv")) {

            // El formato elegido fue CSV. Primero pasar el directorio
            exportadorCsv.setDir_path(chosen_directory[0]);

            // Exportar
            exportadorCsv.exportar(choice_path, listaClientes);

        } else {

            // El formato elegido fue TXT. Primero pasar el directorio
            exportadorTxt.setDir_path(chosen_directory[0]);

            // Exportar
            exportadorTxt.exportar(choice_path, listaClientes);
        }

        // Finalmente, retornar al Menú Principal cuando el usuario lo pida
        retornarMenuPrincipal();
    }

    // Crear un método para finalizar la ejecución del programa
    public void terminarPrograma() {

        // Presentar el mensaje de despedida
        System.out.println(Utilidad.crearMensaje("end_program", ""));

        // Terminar la ejecución del programa
        System.exit(0);
    }
}
