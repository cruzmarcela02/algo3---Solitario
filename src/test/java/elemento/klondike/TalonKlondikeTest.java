package elemento.klondike;


import elemento.Carta;
import elemento.Palo;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import static org.junit.Assert.*;

public class TalonKlondikeTest {

    // ----- METODOS AUXILIARES PARA TEST -----
    private Method getPuedoSacarMethod() throws NoSuchMethodException {
        Method method = TalonKlondike.class.getDeclaredMethod("puedoSacar");
        method.setAccessible(true);
        return method;
    }

    @Test
    public void testTalonVacio() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        TalonKlondike talon = new TalonKlondike();
        assertFalse((Boolean) getPuedoSacarMethod().invoke(talon));
        assertEquals(0,talon.cantidadDeCartas());

    }

    @Test
    public void testNoPuedoSacarTalonVacio() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        TalonKlondike talon = new TalonKlondike();
        assertFalse((Boolean) getPuedoSacarMethod().invoke(talon));
    }

    @Test
    public void testPuedoSacarTalonNoVacio() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        List<Carta> cartas = new ArrayList<>();
        cartas.add(new Carta(5, Palo.PICA, false));
        cartas.add(new Carta(8, Palo.CORAZON, false));
        TalonKlondike talon = new TalonKlondike(cartas);
        assertTrue((Boolean) getPuedoSacarMethod().invoke(talon));
    }

    @Test
    public void testPasarCartaADescarte() {
        //GIVEN
        List<Carta> cartas = new ArrayList<>();
        cartas.add(new Carta(5, Palo.PICA, false));
        cartas.add(new Carta(6, Palo.CORAZON, false));
        cartas.add(new Carta(11, Palo.TREBOL, false));
        TalonKlondike talon = new TalonKlondike(cartas);
        Descarte descarte = new Descarte();

        //WHEN
        var fueEnviada = talon.pasarCartaADescarte(descarte);
        var fueEnviada2 = talon.pasarCartaADescarte(descarte);

        //THEN
        assertTrue(fueEnviada);
        assertTrue(fueEnviada2);
        assertEquals(1, talon.cantidadDeCartas());
        assertFalse(descarte.estaVacia());
    }

    @Test
    public void testPasarCartaADescarteTalonVacio() {
        TalonKlondike talon = new TalonKlondike();
        Descarte descarte = new Descarte();
        assertEquals(0, talon.cantidadDeCartas());
        assertFalse(talon.pasarCartaADescarte(descarte));
    }

    @Test
    public void testRecibirCartasDeDescarteValido() {
        //GIVEN
        Stack<Carta> cartasDescarte = new Stack<>();
        cartasDescarte.push(new Carta(12, Palo.CORAZON, true));
        cartasDescarte.push(new Carta(11, Palo.TREBOL, true));
        cartasDescarte.push(new Carta(10, Palo.DIAMANTE, true));
        Descarte descarte = new Descarte(cartasDescarte);
        TalonKlondike talon = new TalonKlondike();

        //WHEN
        talon.recibirCartas(descarte);

        //THEN
        assertEquals(3, talon.cantidadDeCartas());
    }

    @Test
    public void testNoAgregarCartasATalonConCartas() {
        //GIVEN
        Stack<Carta> cartasDescarte = new Stack<>();
        cartasDescarte.add(new Carta(12, Palo.CORAZON,true));
        cartasDescarte.add(new Carta(11, Palo.TREBOL,true));
        cartasDescarte.add(new Carta(10, Palo.DIAMANTE, true));
        Descarte descarte = new Descarte(cartasDescarte);
        List<Carta> cartasTalon = new ArrayList<>();
        cartasTalon.add(new Carta(5, Palo.TREBOL, false));
        cartasTalon.add(new Carta(4, Palo.PICA, false));
        cartasTalon.add(new Carta(9, Palo.PICA, false));
        TalonKlondike talon = new TalonKlondike(cartasTalon);

        //WHEN
        talon.recibirCartas(descarte);

        //THEN
        assertEquals(3, talon.cantidadDeCartas());
        assertFalse(descarte.estaVacia());
    }
}
