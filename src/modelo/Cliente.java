package modelo;

/**
 * Se contruye la clase Cliente
 */
public class Cliente {

    // Se declaran las variables de clase
    String runCliente;
    String nombreCliente;
    String apellidoCliente;
    @SuppressWarnings("SpellCheckingInspection")
    String aniosCliente;
    @SuppressWarnings("SpellCheckingInspection")
    CategoriaEnum nombreCategoria;

    // Crear un constructor con los parámetros a manipular
    public Cliente(String runCliente, String nombreCliente, String apellidoCliente,
                   @SuppressWarnings("SpellCheckingInspection") String aniosCliente,
                   @SuppressWarnings("SpellCheckingInspection") CategoriaEnum nombreCategoria) {
        this.runCliente = runCliente;
        this.nombreCliente = nombreCliente;
        this.apellidoCliente = apellidoCliente;
        this.aniosCliente = aniosCliente;
        this.nombreCategoria = nombreCategoria;
    }

    //Se crea el accesador RunCliente que retorna un String
    public String getRunCliente() {
        return runCliente;
    }
    //se crea el accesador nombreCliente que retorna un String
    public String getNombreCliente() {
        return nombreCliente;
    }
    //Se crea el accesador ApellidoCliente que retorna un String
    public String getApellidoCliente() {
        return apellidoCliente;
    }

    //Se crea el accesador AniosCliente que retorna un String
    @SuppressWarnings("SpellCheckingInspection")
    public String getAniosCliente() {
        return aniosCliente;
    }

    //Se crea el accesador NombreCategoria que retorna un String
    @SuppressWarnings("SpellCheckingInspection")
    public CategoriaEnum getNombreCategoria() {
        return nombreCategoria;
    }

    // Se implementa el setter RunCliente
    public void setRunCliente(String runCliente) {
        this.runCliente = runCliente;
    }

    //Se implementa el setter NombreCliente
    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }
    //Se implementa el setter ApellidoCliente
    public void setApellidoCliente(String apellidoCliente) {
        this.apellidoCliente = apellidoCliente;
    }
    //Se implementa el setter AniosCliente
    @SuppressWarnings("SpellCheckingInspection")
    public void setAniosCliente(String aniosCliente) {
        this.aniosCliente = aniosCliente;
    }
    //Se implementa el setter NombreCategoria
    @SuppressWarnings("SpellCheckingInspection")
    public void setNombreCategoria(CategoriaEnum nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }

    // Crear método toString

    @Override


//Se instancia un metodo para sobreescribir Variables
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
