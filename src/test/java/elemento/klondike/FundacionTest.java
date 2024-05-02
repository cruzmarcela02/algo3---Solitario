package elemento.klondike;

import elemento.Carta;
import elemento.Palo;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.Assert.*;

public class FundacionTest {

    // ----- METODOS AUXILIARES PARA TEST -----
    private Method getPuedoAgregarMethod() throws NoSuchMethodException {
        Method method = Fundacion.class.getDeclaredMethod("puedoAgregar", Carta.class);
        method.setAccessible(true);
        return method;
    }

    private Method getPuedoSacarMethod() throws NoSuchMethodException {
        Method method = Fundacion.class.getDeclaredMethod("puedoSacar");
        method.setAccessible(true);
        return method;
    }

    // ----- TEST PUEDO SACAR -----
    @Test
    public void testFundacionVacia() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Fundacion fundacion = new Fundacion();
        assertFalse((Boolean) getPuedoSacarMethod().invoke(fundacion));
    }

    // ----- TEST PUEDO AGREGAR -----
    @Test
    public void testPuedoAgregarAsEnFundacionVacia() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Fundacion fundacion = new Fundacion();
        Carta asCorazon = new Carta(1, Palo.CORAZON, true);
        assertTrue((Boolean) getPuedoAgregarMethod().invoke(fundacion,asCorazon));
    }

    @Test
    public void testNoPuedoAgregarCartaNoAsEnFundacionVacia() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Fundacion fundacion = new Fundacion();
        Carta dosCorazon = new Carta(2, Palo.CORAZON, true);
        assertFalse((Boolean) getPuedoAgregarMethod().invoke(fundacion,dosCorazon));
    }

    // ----- AGREGAR CARTA -----
    @Test
    public void testAgregarAsAUnaFundacion() {
        // GIVEN
        Fundacion fundacion = new Fundacion();
        Carta asCorazon = new Carta(1, Palo.CORAZON, true);

        // WHEN
        fundacion.agregarCarta(asCorazon);

        // THEN
        var cartaTope = fundacion.verCartaTope();
        assertTrue(cartaTope.isPresent());
        assertEquals(asCorazon, cartaTope.get());
    }

    @Test
    public void testAgregarCartaRandomEnFundacionVacia() {
        // GIVEN
        Fundacion fundacion = new Fundacion();
        Carta dosCorazon = new Carta(2, Palo.CORAZON, true);

        // WHEN
        fundacion.agregarCarta(dosCorazon);

        // THEN
        var cartaTope = fundacion.verCartaTope();
        assertFalse(cartaTope.isPresent());
    }

    @Test
    public void testAgregarDosCartasValidasEnFundacionVacia() {
        // GIVEN
        Fundacion fundacion = new Fundacion();
        Carta asCorazon = new Carta(1, Palo.CORAZON, true);
        Carta dosCorazon = new Carta(2, Palo.CORAZON, true);

        // WHEN
        fundacion.agregarCarta(asCorazon);
        fundacion.agregarCarta(dosCorazon);

        // THEN
        var cartaTope = fundacion.verCartaTope();
        assertTrue(cartaTope.isPresent());
        assertEquals(dosCorazon, cartaTope.get());
    }

    // ----- SACAR CARTA -----
    @Test
    public void testSacarFundacionNoVacia(){
        // GIVEN
        Fundacion fundacion = new Fundacion();
        Carta asCorazon = new Carta(1,Palo.CORAZON, true);
        Carta dosCorazon = new Carta(2,Palo.CORAZON, true);

        // WHEN
        fundacion.agregarCarta(asCorazon);
        fundacion.agregarCarta(dosCorazon);
        var cartaSacada = fundacion.sacarCarta();

        // THEN
        assertTrue(cartaSacada.isPresent());
        assertEquals(dosCorazon, cartaSacada.get());
    }

    @Test
    public void testSacarHastaVaciarFundacion(){
        // GIVEN
        Fundacion fundacion = new Fundacion();
        Carta asCorazon = new Carta(1,Palo.CORAZON, true);
        Carta dosCorazon = new Carta(2,Palo.CORAZON, true);

        // WHEN
        fundacion.agregarCarta(asCorazon);
        fundacion.agregarCarta(dosCorazon);
        fundacion.sacarCarta();
        fundacion.sacarCarta();

        // THEN
        assertTrue(fundacion.estaVacia());
    }
}
