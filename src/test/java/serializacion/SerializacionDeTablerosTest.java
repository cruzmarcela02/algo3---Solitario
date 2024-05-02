package serializacion;

import mododejuego.klondike.TableroKlondike;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class SerializacionDeTablerosTest {

    private static final String RUTA = "tableroKlondike.bin";
    private static final String RUTA_NO_EXISTE = "noexiste.bin";

    @Test
    public void testPersistenciaConArchivos() throws IOException, ClassNotFoundException {
        var serializador = new SerializacionDeTableros();

        TableroKlondike tableroKlondike = new TableroKlondike();
        serializador.serializar(RUTA, tableroKlondike);

        TableroKlondike tableroKlondikeDeserializado = (TableroKlondike) serializador.deserializar(RUTA);
        assertNotNull(tableroKlondikeDeserializado);
    }

    @Test(expected = IOException.class)
    public void testDeserializarConExcepcion() throws IOException, ClassNotFoundException {
        var serializador = new SerializacionDeTableros();
        TableroKlondike tableroKlondikeDeserializado = (TableroKlondike) serializador.deserializar(RUTA_NO_EXISTE);
        assertNull(tableroKlondikeDeserializado);
    }
}
