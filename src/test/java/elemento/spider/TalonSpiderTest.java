package elemento.spider;

import elemento.Carta;
import elemento.Palo;
import org.junit.Test;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class TalonSpiderTest {
    // ----- METODOS AUXILIARES PARA TEST -----
    private Method getPuedoRepartirMethod() throws NoSuchMethodException {
        Method method = TalonSpider.class.getDeclaredMethod("puedoRepartir", List.class);
        method.setAccessible(true);
        return method;
    }

    @Test
    public void testTalonVacio()  {
        TalonSpider talon = new TalonSpider();
        assertTrue(talon.estaVacio());
        assertEquals(0,talon.cantidadDeCartas());
    }

    @Test
    public void testNoPuedoRepartirTalonVacio() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        TalonSpider talon = new TalonSpider();
        List<PilaSpider> pilas = new ArrayList<>();
        for (int i = 1; i < 11; i++) {
            PilaSpider pilaAgregar = new PilaSpider();
            pilaAgregar.recibirCarta(new Carta(i, Palo.DIAMANTE, false));
            pilas.add(pilaAgregar);
        }
        assertFalse((Boolean) getPuedoRepartirMethod().invoke(talon, pilas));
        assertTrue(talon.estaVacio());
    }

    @Test
    public void testNoPuedoRepartirPilaVacia() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        List<PilaSpider> pilas = new ArrayList<>();
        List<Carta> cartasParaTalon = new ArrayList<>();
        for (int i = 1; i < 11; i++) {
            PilaSpider pilaParaAgregar = new PilaSpider();
            cartasParaTalon.add(new Carta(i, Palo.DIAMANTE, false));
            if (i != 1) {
                pilaParaAgregar.recibirCarta(new Carta(i, Palo.DIAMANTE, false));
            }
            pilas.add(pilaParaAgregar);
        }
        TalonSpider talon = new TalonSpider(cartasParaTalon);
        assertFalse((Boolean) getPuedoRepartirMethod().invoke(talon, pilas));
        assertFalse(talon.estaVacio());
    }

    @Test
    public void testRepartir() {
        // GIVEN
        List<PilaSpider> pilas = new ArrayList<>();
        List<Carta> cartasParaTalon = new ArrayList<>();
        for (int i = 1; i < 11; i++) {
            PilaSpider pilaParaAgregar = new PilaSpider();
            cartasParaTalon.add(new Carta(i, Palo.DIAMANTE, false));
            pilaParaAgregar.recibirCarta(new Carta(i, Palo.DIAMANTE, false));
            pilas.add(pilaParaAgregar);
        }
        TalonSpider talon = new TalonSpider(cartasParaTalon);

        // WHEN
        talon.repartir(pilas);

        // THEN
        for (PilaSpider pila: pilas) {
            assertEquals(2, pila.cantidadDeCartas());
        }
        assertTrue(talon.estaVacio());
    }
}