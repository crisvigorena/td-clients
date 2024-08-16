package servicio;

import modelo.Cliente;

import java.util.List;

public abstract class Exportador {

    // Definir la firma de un método abstracto para exportar datos
    public abstract void exportar(String fileName, List<Cliente> listaClientes);
}
