package elemento.klondike;

import elemento.Carta;
import elemento.Palo;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Stack;

import static org.junit.Assert.*;

public class PilaKlondikeTest {

    // ----- METODOS AUXILIARES PARA TEST -----
    private Method getPuedoAgregarConCartaMethod() throws NoSuchMethodException {
        Method method = PilaKlondike.class.getDeclaredMethod("puedoAgregar", Carta.class);
        method.setAccessible(true);
        return method;
    }

    private Method getPuedoAgregarConEscaleraMethod() throws NoSuchMethodException {
        Method method = PilaKlondike.class.getDeclaredMethod("puedoAgregar", Stack.class);
        method.setAccessible(true);
        return method;
    }

    @Test
    public void testPilaVacia() {
        var pila = new PilaKlondike();
        assertEquals(0, pila.cantidadDeCartas());
        assertFalse(pila.puedoSacar(1));
    }

    // ----- TEST PUEDO AGREGAR -----
    @Test
    public void testPuedoAgregarCartaReyConPilaVacia() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        var pila = new PilaKlondike();
        var carta = new Carta(13, Palo.PICA, true);
        assertTrue((Boolean) getPuedoAgregarConCartaMethod().invoke(pila, carta));
    }

    @Test
    public void testPuedoAgregarEscaleraConReyEnPilaVacia() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        var pila = new PilaKlondike();
        var escalera = new Stack<Carta>();
        escalera.push(new Carta(10, Palo.DIAMANTE, true));
        escalera.push(new Carta(11, Palo.TREBOL, true));
        escalera.push(new Carta(12, Palo.CORAZON, true));
        escalera.push(new Carta(13, Palo.PICA, true));
        assertTrue((Boolean) getPuedoAgregarConEscaleraMethod().invoke(pila, escalera));
    }

    @Test
    public void testNoPuedoAgregarEscaleraSinReyEnPilaVacia() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        var pila = new PilaKlondike();
        var escalera = new Stack<Carta>();
        escalera.push(new Carta(12, Palo.CORAZON, true));
        escalera.push(new Carta(11, Palo.TREBOL, true));
        escalera.push(new Carta(10, Palo.DIAMANTE, true));
        assertFalse((Boolean) getPuedoAgregarConEscaleraMethod().invoke(pila, escalera));
    }

    @Test
    public void testNoPuedoAgregarEnPilaConCartasSiCartaNoFormaEscalera() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        var cartas = new ArrayList<Carta>();
        cartas.add(new Carta(12, Palo.CORAZON));
        cartas.add(new Carta(11, Palo.TREBOL));
        cartas.add(new Carta(10, Palo.DIAMANTE));
        cartas.add(new Carta(6, Palo.DIAMANTE, true));
        var pila = new PilaKlondike(cartas);

        var cartasARecibir = new Stack<Carta>();
        cartasARecibir.push(new Carta(8, Palo.CORAZON, true));

        assertFalse((Boolean) getPuedoAgregarConEscaleraMethod().invoke(pila, cartasARecibir));
    }

    @Test
    public void testPuedoAgregarEnPilaConCartasSiCartaFormaEscalera() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        var cartas = new ArrayList<Carta>();
        cartas.add(new Carta(12, Palo.CORAZON));
        cartas.add(new Carta(11, Palo.TREBOL));
        cartas.add(new Carta(10, Palo.DIAMANTE));
        cartas.add(new Carta(6, Palo.DIAMANTE, true));
        var pila = new PilaKlondike(cartas);

        var cartasAMover = new Stack<Carta>();
        cartasAMover.push(new Carta(5, Palo.TREBOL, true));

        assertTrue((Boolean) getPuedoAgregarConEscaleraMethod().invoke(pila, cartasAMover));
    }

    @Test
    public void testNoPuedoAgregarEnPilaConCartasSiCartaFormaEscaleraPeroTieneIgualColor() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        var cartas = new ArrayList<Carta>();
        cartas.add(new Carta(12, Palo.CORAZON));
        cartas.add(new Carta(11, Palo.TREBOL));
        cartas.add(new Carta(10, Palo.DIAMANTE));
        cartas.add(new Carta(6, Palo.DIAMANTE, true));

        var pila = new PilaKlondike(cartas);
        var cartaAMover = new Stack<Carta>();
        cartaAMover.push(new Carta(5, Palo.DIAMANTE, true));

        assertFalse((Boolean) getPuedoAgregarConEscaleraMethod().invoke(pila, cartaAMover));
    }

    @Test
    public void testNoPuedoAgregarEscaleraAPila() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        var cartas = new ArrayList<Carta>();
        cartas.add(new Carta(12, Palo.CORAZON));
        cartas.add(new Carta(11, Palo.TREBOL));
        cartas.add(new Carta(10, Palo.DIAMANTE));
        cartas.add(new Carta(6, Palo.DIAMANTE,true));
        var pila = new PilaKlondike(cartas);

        var cartasMover = new Stack<Carta>();
        cartasMover.push(new Carta(6, Palo.TREBOL,true));
        cartasMover.push(new Carta(7, Palo.CORAZON,true));
        cartasMover.push(new Carta(8, Palo.TREBOL,true));

        assertFalse((Boolean) getPuedoAgregarConEscaleraMethod().invoke(pila, cartasMover));
    }

    @Test
    public void testMovimientoValidoConEscaleraPilaReturnsTrueTest_ColoresDistintos() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        var cartas = new ArrayList<Carta>();
        cartas.add(new Carta(12, Palo.CORAZON));
        cartas.add(new Carta(11, Palo.TREBOL));
        cartas.add(new Carta(10, Palo.DIAMANTE));
        cartas.add(new Carta(9, Palo.DIAMANTE,true));
        var pila = new PilaKlondike(cartas);

        var cartasMover = new Stack<Carta>();
        cartasMover.push(new Carta(6, Palo.TREBOL,true));
        cartasMover.push(new Carta(7, Palo.CORAZON,true));
        cartasMover.push(new Carta(8, Palo.TREBOL,true));

        assertTrue((Boolean) getPuedoAgregarConEscaleraMethod().invoke(pila, cartasMover));
    }

    // ----- PUEDO SACAR -----
    @Test
    public void testNoPuedoSacarConPilaVacia() {
        PilaKlondike pila = new PilaKlondike();
        assertFalse(pila.puedoSacar(4));
    }

    // ----- PUEDO SACAR CON ESCALERA -----
    @Test
    public void testPuedoSacarConPilaReturnsTrueEscalera() {
        var cartas = new ArrayList<Carta>();

        cartas.add(new Carta(13, Palo.CORAZON, true));
        cartas.add(new Carta(12, Palo.TREBOL, true));
        cartas.add(new Carta(11, Palo.DIAMANTE, true));
        var pila = new PilaKlondike(cartas);

        assertTrue(pila.puedoSacar(3));
    }

    // ----- PUEDO SACAR SEMI ESCALERA INTERMEDIA -----
    @Test
    public void testPuedoSacarConPilaReturnsFalseSemiEscalera() {
        var cartas = new ArrayList<Carta>();
        cartas.add(new Carta(13, Palo.CORAZON,true));
        cartas.add(new Carta(12, Palo.TREBOL, true));
        cartas.add(new Carta(11, Palo.DIAMANTE,true));
        var pila = new PilaKlondike(cartas);
        assertTrue(pila.puedoSacar(2));
    }

    @Test
    public void testNoPuedoSacarSiLaCartaCantidadDeCartaQueQuieroNoEsVisible() {
        var cartas = new ArrayList<Carta>();
        cartas.add(new Carta(13, Palo.CORAZON));
        cartas.add(new Carta(12, Palo.TREBOL));
        cartas.add(new Carta(11, Palo.DIAMANTE));
        cartas.add(new Carta(4, Palo.PICA, true));
        PilaKlondike pila = new PilaKlondike(cartas);
        assertFalse(pila.puedoSacar(4));
    }

    @Test
    public void testPuedoSacarUnaCartaVisibleConPilaNoVacia() {
        var cartas = new ArrayList<Carta>();
        cartas.add(new Carta(1, Palo.CORAZON));
        cartas.add(new Carta(6, Palo.TREBOL));
        cartas.add(new Carta(7, Palo.DIAMANTE));
        cartas.add(new Carta(9, Palo.DIAMANTE,true));
        PilaKlondike pila = new PilaKlondike(cartas);
        assertTrue(pila.puedoSacar(1));
    }

    // ----- AGREGAR CARTA -----
    @Test
    public void testNoAgregarKingEnPilaVacia() {
        var pila = new PilaKlondike();
        pila.agregarCarta(new Carta(1, Palo.CORAZON));

        assertEquals(0,pila.cantidadDeCartas());
    }

    @Test
    public void testAgregarKingEnPilaVacia() {
        var cartas = new ArrayList<Carta>();
        cartas.add(new Carta(13, Palo.CORAZON,true));
        PilaKlondike pila = new PilaKlondike(cartas);

        assertEquals(1, pila.cantidadDeCartas());
    }

    @Test
    public void testAgregarEscaleraConKingEnPilaVacia() {
        var cartas = new Stack<Carta>();
        cartas.push(new Carta(11, Palo.DIAMANTE, true));
        cartas.push(new Carta(12, Palo.TREBOL, true));
        cartas.push(new Carta(13, Palo.CORAZON, true));
        var pila = new PilaKlondike();

        pila.agregarCartas(cartas);

        assertEquals(3, pila.cantidadDeCartas());
    }

    @Test
    public void testNoAgregarEscaleraSinKingEnPilaVacia() {
        var cartas = new Stack<Carta>();
        cartas.push(new Carta(12, Palo.TREBOL,true));
        cartas.push(new Carta(11, Palo.DIAMANTE, true));
        cartas.push(new Carta(10, Palo.TREBOL,true));

        var pila = new PilaKlondike();
        pila.agregarCartas(cartas);
        assertEquals(0, pila.cantidadDeCartas());
    }

    @Test
    public void testNoAgregarAPilaCartasQueNoFormanEscaleraConPila() {
        var cartas = new ArrayList<Carta>();
        cartas.add(new Carta(1, Palo.CORAZON, false));
        cartas.add(new Carta(6, Palo.TREBOL, false));
        cartas.add(new Carta(7, Palo.DIAMANTE, false));
        cartas.add(new Carta(9, Palo.DIAMANTE, true));
        PilaKlondike pila = new PilaKlondike(cartas);

        var nuevas = new Stack<Carta>();
        nuevas.push(new Carta(3, Palo.DIAMANTE, true));
        nuevas.push(new Carta(2, Palo.TREBOL, true));
        pila.agregarCartas(nuevas);

        assertEquals(4, pila.cantidadDeCartas());
    }

    @Test
    public void testAgregarAPilaConCartasUnaPilaQueHaceEscalera() {
        var cartas = new ArrayList<Carta>();
        var nuevas = new Stack<Carta>();
        cartas.add(new Carta(1, Palo.CORAZON, false));
        cartas.add(new Carta(6, Palo.TREBOL, false));
        cartas.add(new Carta(7, Palo.DIAMANTE, false));
        cartas.add(new Carta(9, Palo.DIAMANTE, true));
        var pila = new PilaKlondike(cartas);

        nuevas.push(new Carta(7, Palo.CORAZON, true));
        nuevas.push(new Carta(8, Palo.PICA, true));

        pila.agregarCartas(nuevas);
        assertEquals(6, pila.cantidadDeCartas());
    }

    @Test
    public void testNoAgregarEnPilaCartasQueFormanEscaleraPeroTieneIgualColorLaUltimaDePila() {
        var cartas = new ArrayList<Carta>();
        var nuevas = new Stack<Carta>();
        cartas.add(new Carta(1, Palo.CORAZON));
        cartas.add(new Carta(6, Palo.TREBOL));
        cartas.add(new Carta(7, Palo.DIAMANTE));
        cartas.add(new Carta(9, Palo.DIAMANTE, true));

        nuevas.push(new Carta(8, Palo.CORAZON, true));
        nuevas.push(new Carta(7, Palo.TREBOL, true));

        var pila = new PilaKlondike(cartas);
        pila.agregarCartas(nuevas);

        assertEquals(4, pila.cantidadDeCartas());
    }

    // ----- AGREGAR ----
    @Test
    public void testAgregarReyAPilaVacia() {
        var pila = new PilaKlondike();

        assertTrue(pila.agregarCarta(new Carta(13, Palo.PICA, true)));
        assertEquals(1, pila.cantidadDeCartas());
    }

    @Test
    public void testAgregarCartaDistintaAReyAPilaVacia() {
        var pila = new PilaKlondike();

        assertFalse(pila.agregarCarta(new Carta(1, Palo.PICA, true)));
        assertEquals(0, pila.cantidadDeCartas());
    }

    @Test
    public void testAgregarEscaleraAPilaVacia() {
        var pila = new PilaKlondike();
        var escalera = new Stack<Carta>();

        escalera.push(new Carta(9, Palo.CORAZON, true));
        escalera.push(new Carta(10, Palo.TREBOL, true));
        escalera.push(new Carta(11, Palo.CORAZON, true));
        escalera.push(new Carta(12, Palo.TREBOL, true));
        escalera.push(new Carta(13, Palo.CORAZON, true));

        assertTrue(pila.agregarCartas(escalera));
        assertEquals(5, pila.cantidadDeCartas());
    }


    // ----- SACAR CARTA -----
    @Test
    public void testSacarCartasPilaVacia() {
        var pila = new PilaKlondike();
        var optional = pila.sacarCartas(1);
        assertTrue(optional.isEmpty());
    }

    @Test
    public void testNoSacaCartasCuandoNoLaCartaNoEsVisibles() {
        var cartas = new ArrayList<Carta>();
        cartas.add(new Carta(1, Palo.CORAZON));
        cartas.add(new Carta(6, Palo.TREBOL));
        cartas.add(new Carta(10, Palo.DIAMANTE));
        cartas.add(new Carta(9, Palo.PICA, true));
        cartas.add(new Carta(8, Palo.CORAZON, true));

        PilaKlondike pila = new PilaKlondike(cartas);
        var optional = pila.sacarCartas(5);

        assertTrue(optional.isEmpty());
        assertEquals(5, pila.cantidadDeCartas());
    }

    @Test
    public void TestSacarUnaCartaVisiblePilaConCartas() {
        var cartas = new ArrayList<Carta>();
        cartas.add(new Carta(1, Palo.CORAZON));
        cartas.add(new Carta(6, Palo.TREBOL));
        cartas.add(new Carta(10, Palo.DIAMANTE, true));
        cartas.add(new Carta(9, Palo.PICA, true));

        var esperadas = new ArrayList<Carta>();

        esperadas.add(new Carta(1, Palo.CORAZON));
        esperadas.add(new Carta(6, Palo.TREBOL));
        esperadas.add(new Carta(10, Palo.DIAMANTE, true));

        var pila = new PilaKlondike(cartas);
        var carta = pila.sacarCartas(1);

        assertEquals(esperadas, pila.getCartas());
        assertTrue(carta.isPresent());
        assertEquals(new Carta(9, Palo.PICA, true), carta.get().peek());
    }

    @Test
    public void testSacarCartasEscaleraVisiblePilaConCartas() {
        var esperadas = new ArrayList<Carta>();
        var cartas = new ArrayList<Carta>();

        cartas.add(new Carta(1, Palo.CORAZON));
        cartas.add(new Carta(6, Palo.TREBOL));
        cartas.add(new Carta(10, Palo.DIAMANTE, true));
        cartas.add(new Carta(9, Palo.PICA, true));
        cartas.add(new Carta(8, Palo.CORAZON, true));

        esperadas.add(new Carta(1, Palo.CORAZON));
        esperadas.add(new Carta(6, Palo.TREBOL));

        PilaKlondike pila = new PilaKlondike(cartas);
        pila.sacarCartas(3);

        assertEquals(esperadas, pila.getCartas());
        assertEquals(2, pila.cantidadDeCartas());
    }
}
