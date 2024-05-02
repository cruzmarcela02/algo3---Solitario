package elemento.spider;

import elemento.Carta;
import elemento.Palo;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Stack;

import static org.junit.Assert.*;

public class PilaSpiderTest {

    @Test
    public void testPilaVacia() {
        var pila = new PilaSpider();
        assertEquals(0, pila.cantidadDeCartas());
    }

    @Test
    public void testRecibirUnaCarta() {
        var pila = new PilaSpider();
        var cuatroPica = new Carta(4, Palo.PICA);
        pila.recibirCarta(cuatroPica);
        assertEquals(1, pila.cantidadDeCartas());
    }

    @Test
    public void testRecibirVariasCartas() {
        var pila = new PilaSpider();
        var cuatroPica = new Carta(4, Palo.PICA);
        var sietePica = new Carta(7, Palo.PICA);
        var dosPica = new Carta(2, Palo.PICA);
        var nuevePica = new Carta(9, Palo.PICA);

        pila.recibirCarta(cuatroPica);
        pila.recibirCarta(sietePica);
        pila.recibirCarta(dosPica);
        pila.recibirCarta(nuevePica);

        assertEquals(4, pila.cantidadDeCartas());
    }

    @Test
    public void testAgregarCeroCartas() {
        var pila = new PilaSpider();
        var optional = pila.sacarCartas(1);
        assertTrue(optional.isEmpty());
    }

    // ----- SACAR -----
    @Test
    public void testSacaUnaCarta() {
        var pila = new PilaSpider();
        var cuatroPica = new Carta(4, Palo.PICA);
        pila.recibirCarta(cuatroPica);

        pila.sacarCartas(1);
        assertEquals(0, pila.cantidadDeCartas());
    }

    @Test
    public void testSacaPorqueHaceEscalera() {
        var cartas = new ArrayList<Carta>();
        cartas.add(new Carta(5, Palo.PICA,true));
        cartas.add(new Carta(4, Palo.PICA,true));

        var pila = new PilaSpider(cartas);
        pila.sacarCartas(2);
        assertEquals(0,pila.cantidadDeCartas());
        assertTrue(pila.estaVacia());
    }

    @Test
    public void testPuedoSacarEscaleraDeTresCartas() {
        var cartas = new ArrayList<Carta>();
        cartas.add(new Carta(5, Palo.PICA,true));
        cartas.add(new Carta(4, Palo.PICA,true));
        cartas.add(new Carta(3, Palo.PICA,true));
        cartas.add(new Carta(2, Palo.PICA,true));
        cartas.add(new Carta(1, Palo.PICA,true));

        var pila = new PilaSpider(cartas);
        pila.sacarCartas(3);
        assertEquals(2, pila.cantidadDeCartas());
    }

    // ----- NO PUEDO SACAR -----
    @Test
    public void testNoPuedoSacarCartaPorqueNoHaceEscalera() {
        var cartas = new ArrayList<Carta>();
        cartas.add(new Carta(5, Palo.PICA,true));
        cartas.add(new Carta(3, Palo.PICA,true));

        var pila = new PilaSpider(cartas);

        pila.sacarCartas(2);
        assertEquals(2,pila.cantidadDeCartas());
        assertFalse(pila.estaVacia());
    }

    @Test
    public void testNoPuedoSacarCartasPorqueLaEscaleraNoLlegaAlFinal() {
        var cartas = new ArrayList<Carta>();
        cartas.add(new Carta(5, Palo.PICA,true));
        cartas.add(new Carta(4, Palo.PICA,true));
        cartas.add(new Carta(2, Palo.PICA,true));

        var pila = new PilaSpider(cartas);

        pila.sacarCartas(3);
        assertEquals(3,pila.cantidadDeCartas());
    }

    @Test
    public void testNoPuedoSacarCartaPorVisibilidad() {
        var pila = new PilaSpider();
        var cuatroPica = new Carta(4, Palo.PICA);
        var cincoPica = new Carta(5, Palo.PICA,true);

        pila.recibirCarta(cuatroPica);
        pila.recibirCarta(cincoPica);

        pila.sacarCartas(2);
        assertEquals(2,pila.cantidadDeCartas());
    }

    // ----- HAY ESCALERA COMPLETA EN LA PILA -----
    @Test
    public void testPilaTieneEscaleraCompleta() {
        var cartas = new ArrayList<Carta>();
        cartas.add(new Carta(13,Palo.PICA,true));
        cartas.add(new Carta(12,Palo.PICA,true));
        cartas.add(new Carta(11,Palo.PICA,true));
        cartas.add(new Carta(10,Palo.PICA,true));
        cartas.add(new Carta(9,Palo.PICA,true));
        cartas.add(new Carta(8,Palo.PICA,true));
        cartas.add(new Carta(7,Palo.PICA,true));
        cartas.add(new Carta(6,Palo.PICA,true));
        cartas.add(new Carta(5,Palo.PICA,true));
        cartas.add(new Carta(4,Palo.PICA,true));
        cartas.add(new Carta(3,Palo.PICA,true));
        cartas.add(new Carta(2,Palo.PICA,true));
        cartas.add(new Carta(1,Palo.PICA,true));

        var pila = new PilaSpider(cartas);

        assertTrue(pila.hayEscaleraCompleta());
    }

    @Test
    public void testPilaTieneEscaleraIncompleta(){
        var cartas = new ArrayList<Carta>();
        cartas.add(new Carta(8,Palo.PICA,true));
        cartas.add(new Carta(12,Palo.PICA,true));
        cartas.add(new Carta(11,Palo.PICA,true));
        cartas.add(new Carta(10,Palo.PICA,true));
        cartas.add(new Carta(9,Palo.PICA,true));
        cartas.add(new Carta(8,Palo.PICA,true));
        cartas.add(new Carta(7,Palo.PICA,true));
        cartas.add(new Carta(6,Palo.PICA,true));
        cartas.add(new Carta(5,Palo.PICA,true));
        cartas.add(new Carta(4,Palo.PICA,true));
        cartas.add(new Carta(3,Palo.PICA,true));
        cartas.add(new Carta(2,Palo.PICA,true));
        cartas.add(new Carta(1,Palo.PICA,true));

        var pila = new PilaSpider(cartas);

        assertFalse(pila.hayEscaleraCompleta());
    }

    @Test
    public void testSacarCartasPilaVacia() {
        var pila = new PilaSpider();
        var optional = pila.sacarCartas(1);
        assertTrue(optional.isEmpty());
    }

    @Test
    public void testNoAgregarAPilaCartasQueNoFormanEscaleraConPila() {
        var cartas = new ArrayList<Carta>();
        cartas.add(new Carta(1, Palo.PICA, false));
        cartas.add(new Carta(6, Palo.PICA, false));
        cartas.add(new Carta(7, Palo.PICA, false));
        cartas.add(new Carta(9, Palo.PICA, true));
        PilaSpider pila = new PilaSpider(cartas);

        var nuevas = new Stack<Carta>();
        nuevas.push(new Carta(3, Palo.PICA, true));
        nuevas.push(new Carta(2, Palo.PICA, true));
        pila.agregarCartas(nuevas);

        assertEquals(4, pila.cantidadDeCartas());
    }

    @Test
    public void testAgregarAPilaConCartasUnaPilaQueHaceEscalera() {
        var cartas = new ArrayList<Carta>();
        var nuevas = new Stack<Carta>();
        cartas.add(new Carta(1, Palo.PICA, false));
        cartas.add(new Carta(6, Palo.PICA, false));
        cartas.add(new Carta(7, Palo.PICA, false));
        cartas.add(new Carta(9, Palo.PICA, true));
        var pila = new PilaSpider(cartas);

        nuevas.push(new Carta(7, Palo.PICA, true));
        nuevas.push(new Carta(8, Palo.PICA, true));

        pila.agregarCartas(nuevas);
        assertEquals(6, pila.cantidadDeCartas());
    }
}
