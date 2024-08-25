package servicio;

import modelo.CategoriaEnum;
import modelo.Cliente;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import javax.accessibility.AccessibleStateSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Test Sistema de Manejo de Clientes - TD Clients")
class ClienteServicioTest {

    // Definir variables de clase
    private static Logger logger = Logger.getLogger("dev.tiktaalik.servicio.ClienteServicioTest");
    protected static List<Cliente> listaClientes = new ArrayList<>();
    private final ClienteServicio clienteServicio = new ClienteServicio(listaClientes);

    @org.junit.jupiter.api.BeforeEach
    void setUpTest() {
        logger.info("----------Comienzo de las pruebas unitarias--------");
    }

    @org.junit.jupiter.api.BeforeEach
    void init() {
        logger.info("------------Configuración inicial antes de ejecutar cada test-----------");
    }

    @org.junit.jupiter.api.AfterEach
    void tearDownTest() {
        logger.info("------------Fin de la ejecución del test-----------");
    }

    @org.junit.jupiter.api.AfterAll
    static void finishim() {
        logger.info("------------Fin de la ejecución de todos los tests unitarios de la clase-----------");
    }

    @org.junit.jupiter.api.Test
    public void agregarClienteTest() {
        logger.info("------------Info test de agregarCliente válido-----------");
        clienteServicio.agregarCliente("0000000-0", "Rodrigo", "Gambra",
                "5", CategoriaEnum.Activo);
        Cliente busqueda = clienteServicio.buscarCliente("0000000-0");
        List<String> expected = new ArrayList<>();
        expected.add("0000000-0");
        List<String> actual = new ArrayList<>();
        actual.add(busqueda.getRunCliente());
        Assertions.assertLinesMatch(expected,actual);
    }

    @org.junit.jupiter.api.Test
    public void agregarClienteNullTest() {
        logger.info("------------Info test de agregarCliente nulo-----------");
        clienteServicio.agregarCliente(null, null, null, null, null);
        Cliente busqueda = clienteServicio.buscarCliente(null);
        Assertions.assertNull(busqueda.getRunCliente());

    }
}