package vista;

import modelo.CategoriaEnum;
import modelo.Cliente;
import servicio.ArchivoServicio;
import servicio.ClienteServicio;
import servicio.ExportadorCsv;
import servicio.ExportadorTxt;
import utilidades.Utilidad;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Scanner;

public class Menu {

    // Crear las variables de clase
    ArrayList<Cliente> listaClientes;
    String defaultDirectory = "static";
    String fileName = "Clientes";
    String fileName1 = "DBClientes.csv";
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

        // Retornar al Menú Principal cuando el usuario lo pida
        retornarMenuPrincipal();
    }

    // Crear un método para editar los datos de un cliente
    public void editarCliente() {

        // Crear las variables de trabajo
        String choice_edit;
        String choice_run;
        String choice_name;
        String choice_surname;
        String choice_years;
        String choice_edit_status;
        String choice_edit_data;
        String choice_edit_run;
        String choice_edit_name;
        String choice_edit_surname;
        String choice_edit_years;
        CategoriaEnum current_state;
        CategoriaEnum opposite_state;
        Cliente client_data;
        CategoriaEnum client_category;
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
                        client_data.setRunCliente(choice_edit_name);
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
                        client_data.setRunCliente(choice_edit_surname);
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
                        client_data.setRunCliente(choice_edit_years);
                    }
                }
            }
        }

        // Ahora que ya hemos actualizado los datos del cliente, debemos reemplazar el cliente
        // Para eso, primero lo eliminamos de la lista de clientes
        client_removed = clienteServicio.eliminarCliente(choice_run);

        // Señalar el mensaje final que debiera ser mostrado por defecto (error en la operación)
        final_usr_msg_state = false;

        // Determinar si el cliente fue eliminado exitosamente
        if (client_removed) {

            // Señalar que el mensaje final que debiera ser mostrado es de éxito
            final_usr_msg_state = true;

            // Si el cliente fue eliminado exitosamente, entonces insertamos el objeto Cliente,
            // con los datos actualizados, en la Lista de Clientes
            client_added = clienteServicio.importarUnCliente(client_data);

            // Determinar si el cliente fue agregado exitosamente
            if (!client_added) {

                // Señalar que el mensaje final que debiera ser mostrado es de érror
                final_usr_msg_state = false;
            }
        }

        // Crear el mensaje final al usuario con el resultado de la operación
        final_usr_msg = final_usr_msg_state ? Utilidad.crearMensaje("edit_menu_success", "") :
                        Utilidad.crearMensaje("edit_menu_fail", "");

        // Imprimir el mensaje final
        System.out.println(final_usr_msg);

        // Finalmente, retornar al Menú Principal cuando el usuario lo pida
        retornarMenuPrincipal();
    }

    // Crear un método para importar datos de múltiples clientes desde un archivo CSV
    public void importarDatos() {

        // Retornar resultado y mensajes al usuario
        return new String[]{""};
    }

    // Crear un método para exportar los datos de todos los clientes a un archivo CSV o TXT
    public void exportarDatos() {

        // Retornar resultado y mensajes al usuario
        return new String[]{""};
    }

    // Crear un método para finalizar la ejecución del programa
    public void terminarPrograma() {

        // Presentar el mensaje de despedida
        System.out.println(Utilidad.crearMensaje("end_program", ""));

        // Terminar la ejecución del programa
        System.exit(0);
    }
}
