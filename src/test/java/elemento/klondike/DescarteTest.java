package elemento.klondike;


import elemento.Carta;
import elemento.Palo;
import org.junit.Test;
import static org.junit.Assert.*;

public class DescarteTest {

    @Test
    public void testSacarCartaDescarteVacio() {
        // GIVEN
        Descarte descarte = new Descarte();
        Carta asTrebol = new Carta(1, Palo.TREBOL, false);

        // WHEN
        descarte.agregarCarta(asTrebol);
        descarte.sacarCarta();

        //THEN
        assertTrue(descarte.estaVacia());
    }

    @Test
    public void testSacarCartaDescarteNoVacio() {
        // GIVEN
        Descarte descarte = new Descarte();
        Carta asTrebol = new Carta(1, Palo.TREBOL, false);
        Carta cincoCorazon = new Carta(5, Palo.CORAZON, false);

        // WHEN
        descarte.agregarCarta(asTrebol);
        descarte.agregarCarta(cincoCorazon);
        descarte.sacarCarta();

        // THEN
        var cartaTope = descarte.verCartaTope();
        assertTrue(cartaTope.isPresent());
        assertEquals(asTrebol, cartaTope.get());
    }

    @Test
    public void testAgregarUnaCarta() {
        // GIVEN
        Descarte descarte = new Descarte();
        Carta asTrebol = new Carta(1, Palo.TREBOL, false);

        // WHEN
        descarte.agregarCarta(asTrebol);

        // THEN
        var cartaTope = descarte.verCartaTope();
        assertTrue(cartaTope.isPresent());
        assertEquals(asTrebol, cartaTope.get());
    }

    @Test
    public void testAgregarVariasCartas() {
        // GIVEN
        Descarte descarte = new Descarte();
        Carta asTrebol = new Carta(1, Palo.TREBOL, false);
        Carta sieteDiamante = new Carta(7, Palo.DIAMANTE, false);
        Carta seisTrebol = new Carta(6, Palo.TREBOL, false);

        // WHEN
        descarte.agregarCarta(asTrebol);
        descarte.agregarCarta(sieteDiamante);
        descarte.agregarCarta(seisTrebol);

        // THEN
        var cartaTope = descarte.verCartaTope();
        assertTrue(cartaTope.isPresent());
        assertEquals(seisTrebol, cartaTope.get());
    }

    @Test
    public void testNoPuedoSacarUnaCartaDeDescarteVacio() {
        Descarte descarte = new Descarte();
        var cartaSacada = descarte.sacarCarta();
        assertFalse(cartaSacada.isPresent());
    }

    @Test
    public void testSacoCartaYDescarteQuedaVacio() {
        // GIVEN
        Descarte descarte = new Descarte();
        Carta asTrebol = new Carta(1, Palo.TREBOL, false);

        // WHEN
        descarte.agregarCarta(asTrebol);
        var cartaSacada = descarte.sacarCarta();

        // THEN
        assertTrue(cartaSacada.isPresent());
        assertEquals(asTrebol, cartaSacada.get());
        assertTrue(descarte.estaVacia());
    }

    @Test
    public void testCartaEsVisibleLuegoDeAgregar() {
        // GIVEN
        Descarte descarte = new Descarte();
        Carta asTrebolNoVisible = new Carta(1, Palo.TREBOL, false);
        Carta asTrebolVisible = new Carta(1, Palo.TREBOL, true);

        // WHEN
        descarte.agregarCarta(asTrebolNoVisible);

        // THEN
        var cartaTope = descarte.verCartaTope();
        assertTrue(cartaTope.isPresent());
        assertEquals(asTrebolVisible, cartaTope.get());
    }
}