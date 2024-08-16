package modelo;

public class Cliente {

    // Declarar las variables de clase
    String runCliente;
    String nombreCliente;
    String apellidoCliente;
    @SuppressWarnings("SpellCheckingInspection")
    String aniosCliente;
    @SuppressWarnings("SpellCheckingInspection")
    CategoriaEnum nombreCategoria;

    // Crear un constructor con parámetros
    public Cliente(String runCliente, String nombreCliente, String apellidoCliente,
                   @SuppressWarnings("SpellCheckingInspection") String aniosCliente,
                   @SuppressWarnings("SpellCheckingInspection") CategoriaEnum nombreCategoria) {
        this.runCliente = runCliente;
        this.nombreCliente = nombreCliente;
        this.apellidoCliente = apellidoCliente;
        this.aniosCliente = aniosCliente;
        this.nombreCategoria = nombreCategoria;
    }

    //Crear Accesadores
    public String getRunCliente() {
        return runCliente;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public String getApellidoCliente() {
        return apellidoCliente;
    }

    @SuppressWarnings("SpellCheckingInspection")
    public String getAniosCliente() {
        return aniosCliente;
    }

    @SuppressWarnings("SpellCheckingInspection")
    public CategoriaEnum getNombreCategoria() {
        return nombreCategoria;
    }

    // Crear Mutadores
    public void setRunCliente(String runCliente) {
        this.runCliente = runCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public void setApellidoCliente(String apellidoCliente) {
        this.apellidoCliente = apellidoCliente;
    }

    @SuppressWarnings("SpellCheckingInspection")
    public void setAniosCliente(String aniosCliente) {
        this.aniosCliente = aniosCliente;
    }

    @SuppressWarnings("SpellCheckingInspection")
    public void setNombreCategoria(CategoriaEnum nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }

    // Crear método toString

    @Override
    public String toString() {
        // Definir variables de trabajo
        String newLine = System.lineSeparator();
        String description;

        // Crear mensaje al usuario
        description = ("Datos del Cliente%s " +
                "------------------" +
                "RUN: %s,%s" +
                "Nombre: %s,%s" +
                "Apellido: %s,%s" +
                "Años como cliente: %s, %s" +
                "Categoría: %s%s").formatted(newLine,
                                            runCliente, newLine,
                                            nombreCliente, newLine,
                                            apellidoCliente, newLine,
                                            aniosCliente, newLine,
                                            nombreCategoria, newLine);

        // Retornar descripción del cliente
        return description;
    }
}
