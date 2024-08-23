package main;

import modelo.Cliente;
import vista.Menu;

import java.util.ArrayList;

public class Main {

    // Crear las variables globales del programa
    static ArrayList<Cliente> listaClientes = new ArrayList<>();
    static Menu appMenu;

    public static void main(String[] args) {

        // Crear una instancia del menú
        appMenu = new Menu(listaClientes);
    }
}
