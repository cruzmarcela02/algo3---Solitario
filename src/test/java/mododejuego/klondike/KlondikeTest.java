package mododejuego.klondike;

import elemento.Carta;
import elemento.Mazo;
import elemento.Palo;
import lector.LectorCabecera;
import org.junit.Test;
import serializacion.SerializacionDeTableros;

import java.util.Objects;

import static org.junit.Assert.*;

public class KlondikeTest {
    private static final int SEED_6 = 6;
    private static final int SEED_8 = 8;

    @Test
    public void testJuegoEstadoInicial() {
        // GIVEN
        var mazo = new Mazo(SEED_8);
        mazo.mezclar();
        var klondike = new Klondike(mazo);

        // WHEN
        var tablero = klondike.armarTablero();

        var ultimaCartaPilaUno = klondike.mostrarCartaPila(1);
        var ultimaCartaPilaDos = klondike.mostrarCartaPila(2);
        var ultimaCartaPilaTres = klondike.mostrarCartaPila(3);
        var ultimaCartaPilaCuatro = klondike.mostrarCartaPila(4);
        var ultimaCartaPilaCinco = klondike.mostrarCartaPila(5);
        var ultimaCartaPilaSeis = klondike.mostrarCartaPila(6);
        var ultimaCartaPilaSiete = klondike.mostrarCartaPila(7);
        var fundacionUno = tablero.seleccionarFundacion(1);
        var fundacionDos = tablero.seleccionarFundacion(2);
        var fundacionTres = tablero.seleccionarFundacion(3);
        var fundacionCuatro = tablero.seleccionarFundacion(4);
        var descarte = tablero.getDescarte();
        var talon = tablero.getTalon();

        // THEN
        assertTrue(ultimaCartaPilaUno.isPresent());
        assertTrue(ultimaCartaPilaDos.isPresent());
        assertTrue(ultimaCartaPilaTres.isPresent());
        assertTrue(ultimaCartaPilaCuatro.isPresent());
        assertTrue(ultimaCartaPilaCinco.isPresent());
        assertTrue(ultimaCartaPilaSeis.isPresent());
        assertTrue(ultimaCartaPilaSiete.isPresent());
        assertEquals(new Carta(2, Palo.DIAMANTE, true), ultimaCartaPilaUno.get());
        assertEquals(new Carta(5, Palo.TREBOL,true), ultimaCartaPilaDos.get());
        assertEquals(new Carta(7, Palo.TREBOL, true), ultimaCartaPilaTres.get());
        assertEquals(new Carta(11, Palo.PICA,true), ultimaCartaPilaCuatro.get());
        assertEquals(new Carta(3,Palo.DIAMANTE, true), ultimaCartaPilaCinco.get());
        assertEquals(new Carta(12,Palo.DIAMANTE, true), ultimaCartaPilaSeis.get());
        assertEquals(new Carta(2, Palo.TREBOL,true), ultimaCartaPilaSiete.get());
        assertTrue(fundacionUno.estaVacia());
        assertTrue(fundacionDos.estaVacia());
        assertTrue(fundacionTres.estaVacia());
        assertTrue(fundacionCuatro.estaVacia());
        assertTrue(descarte.estaVacia());
        assertEquals(talon.cantidadDeCartas(), 24);
        assertFalse(klondike.juegoGanado());
    }

    @Test
    public void testMoverDelDescarteVacioALaPila(){
        // GIVEN
        var mazo = new Mazo(SEED_8);
        mazo.mezclar();
        var klondike = new Klondike(mazo);
        var tablero = klondike.armarTablero();

        // WHEN
        klondike.moverDescartePila(7);

        var ultimaCartaPilaUno = klondike.mostrarCartaPila(1);
        var ultimaCartaPilaDos = klondike.mostrarCartaPila(2);
        var ultimaCartaPilaTres = klondike.mostrarCartaPila(3);
        var ultimaCartaPilaCuatro = klondike.mostrarCartaPila(4);
        var ultimaCartaPilaCinco = klondike.mostrarCartaPila(5);
        var ultimaCartaPilaSeis = klondike.mostrarCartaPila(6);
        var ultimaCartaPilaSiete = klondike.mostrarCartaPila(7);
        var descarte = tablero.getDescarte();

        // THEN
        assertTrue(ultimaCartaPilaUno.isPresent());
        assertTrue(ultimaCartaPilaDos.isPresent());
        assertTrue(ultimaCartaPilaTres.isPresent());
        assertTrue(ultimaCartaPilaCuatro.isPresent());
        assertTrue(ultimaCartaPilaCinco.isPresent());
        assertTrue(ultimaCartaPilaSeis.isPresent());
        assertTrue(ultimaCartaPilaSiete.isPresent());

        assertEquals(new Carta(2,Palo.DIAMANTE, true), ultimaCartaPilaUno.get());
        assertEquals(new Carta(5, Palo.TREBOL,true), ultimaCartaPilaDos.get());
        assertEquals(new Carta(7,Palo.TREBOL, true), ultimaCartaPilaTres.get());
        assertEquals(new Carta(11, Palo.PICA,true), ultimaCartaPilaCuatro.get());
        assertEquals(new Carta(3,Palo.DIAMANTE, true), ultimaCartaPilaCinco.get());
        assertEquals(new Carta(12,Palo.DIAMANTE, true), ultimaCartaPilaSeis.get());
        assertEquals(new Carta(2,Palo.TREBOL,true), ultimaCartaPilaSiete.get());
        assertTrue(descarte.estaVacia());
    }

    @Test
    public void testPasarVariasCartasEntrePilas(){
        // GIVEN
        var lector = new LectorCabecera();
        var klondike = new Klondike(lector);
        var ruta = Objects.requireNonNull(this.getClass().getResource("/pasarVariasCartasEntrePilas.txt")).getFile();
        klondike.cargarEstado(ruta);

        // WHEN
        klondike.moverPilaPila(1,2,1);
        klondike.moverPilaPila(2,4,2);

        var ultimaCartaPilaUno = klondike.mostrarCartaPila(1);
        var ultimaCartaPilaDos = klondike.mostrarCartaPila(2);
        var ultimaCartaPilaTres = klondike.mostrarCartaPila(3);
        var ultimaCartaPilaCuatro = klondike.mostrarCartaPila(4);
        var ultimaCartaPilaCinco = klondike.mostrarCartaPila(5);
        var ultimaCartaPilaSeis = klondike.mostrarCartaPila(6);
        var ultimaCartaPilaSiete = klondike.mostrarCartaPila(7);

        // THEN
        assertTrue(ultimaCartaPilaUno.isPresent());
        assertTrue(ultimaCartaPilaDos.isPresent());
        assertTrue(ultimaCartaPilaTres.isPresent());
        assertTrue(ultimaCartaPilaCuatro.isPresent());
        assertTrue(ultimaCartaPilaCinco.isEmpty());
        assertTrue(ultimaCartaPilaSeis.isEmpty());
        assertTrue(ultimaCartaPilaSiete.isEmpty());

        assertEquals(new Carta(13, Palo.DIAMANTE,true), ultimaCartaPilaUno.get());
        assertEquals(new Carta(9, Palo.PICA,true), ultimaCartaPilaDos.get());
        assertEquals(new Carta(9, Palo.CORAZON,true), ultimaCartaPilaTres.get());
        assertEquals(new Carta(6, Palo.PICA,true), ultimaCartaPilaCuatro.get());
    }

    @Test
    public void testPasarCartaDescarteAPila() {
        // GIVEN
        var lector = new LectorCabecera();
        var klondike = new Klondike(lector);
        var ruta = Objects.requireNonNull(this.getClass().getResource("/fundacionConCartas.txt")).getFile();
        klondike.cargarEstado(ruta);

        // WHEN
        klondike.moverDescartePila(2);

        var ultimaCartaPilaUno = klondike.mostrarCartaPila(1);
        var ultimaCartaPilaDos = klondike.mostrarCartaPila(2);
        var ultimaCartaPilaTres = klondike.mostrarCartaPila(3);
        var ultimaCartaPilaCuatro = klondike.mostrarCartaPila(4);
        var ultimaCartaPilaCinco = klondike.mostrarCartaPila(5);
        var ultimaCartaPilaSeis = klondike.mostrarCartaPila(6);
        var ultimaCartaPilaSiete = klondike.mostrarCartaPila(7);
        var cartaDescarte = klondike.mostrarCartaDescarte();

        // THEN
        assertTrue(ultimaCartaPilaUno.isPresent());
        assertTrue(ultimaCartaPilaDos.isPresent());
        assertTrue(ultimaCartaPilaTres.isPresent());
        assertTrue(ultimaCartaPilaCuatro.isPresent());
        assertTrue(ultimaCartaPilaCinco.isEmpty());
        assertTrue(ultimaCartaPilaSeis.isEmpty());
        assertTrue(ultimaCartaPilaSiete.isEmpty());
        assertTrue(cartaDescarte.isEmpty());
        assertEquals(new Carta(13, Palo.DIAMANTE, true), ultimaCartaPilaUno.get());
        assertEquals(new Carta(8, Palo.DIAMANTE,true), ultimaCartaPilaDos.get());
        assertEquals(new Carta(9, Palo.CORAZON, true), ultimaCartaPilaTres.get());
        assertEquals(new Carta(6, Palo.PICA,true), ultimaCartaPilaCuatro.get());
    }

    @Test
    public void testNoPasarCartaInvalidaDescarteAPilaVacia() {
        // GIVEN
        var lector = new LectorCabecera();
        var klondike = new Klondike(lector);
        var ruta = Objects.requireNonNull(this.getClass().getResource("/fundacionConCartas.txt")).getFile();
        klondike.cargarEstado(ruta);

        // WHEN
        klondike.moverDescartePila(7);

        var ultimaCartaPilaUno = klondike.mostrarCartaPila(1);
        var ultimaCartaPilaDos = klondike.mostrarCartaPila(2);
        var ultimaCartaPilaTres = klondike.mostrarCartaPila(3);
        var ultimaCartaPilaCuatro = klondike.mostrarCartaPila(4);
        var ultimaCartaPilaCinco = klondike.mostrarCartaPila(5);
        var ultimaCartaPilaSeis = klondike.mostrarCartaPila(6);
        var ultimaCartaPilaSiete = klondike.mostrarCartaPila(7);
        var cartaDescarte = klondike.mostrarCartaDescarte();

        //THEN
        assertTrue(ultimaCartaPilaUno.isPresent());
        assertTrue(ultimaCartaPilaDos.isPresent());
        assertTrue(ultimaCartaPilaTres.isPresent());
        assertTrue(ultimaCartaPilaCuatro.isPresent());
        assertTrue(ultimaCartaPilaCinco.isEmpty());
        assertTrue(ultimaCartaPilaSeis.isEmpty());
        assertTrue(ultimaCartaPilaSiete.isEmpty());
        assertTrue(cartaDescarte.isPresent());
        assertEquals(new Carta(13, Palo.DIAMANTE, true), ultimaCartaPilaUno.get());
        assertEquals(new Carta(9, Palo.PICA,true), ultimaCartaPilaDos.get());
        assertEquals(new Carta(9, Palo.CORAZON, true), ultimaCartaPilaTres.get());
        assertEquals(new Carta(6, Palo.PICA,true), ultimaCartaPilaCuatro.get());
        assertEquals(new Carta(8, Palo.DIAMANTE,true), cartaDescarte.get());
    }

    @Test
    public void TestMoverCartaPilaAFundacionVaciaCartaAs() {
        // GIVEN
        Mazo mazo = new Mazo(SEED_6);
        mazo.mezclar();
        Klondike klondike = new Klondike(mazo);
        klondike.armarTablero();

        // WHEN
        klondike.moverPilaFundacion(5,1);

        var ultimaCartaPilaUno = klondike.mostrarCartaPila(1);
        var ultimaCartaPilaDos = klondike.mostrarCartaPila(2);
        var ultimaCartaPilaTres = klondike.mostrarCartaPila(3);
        var ultimaCartaPilaCuatro = klondike.mostrarCartaPila(4);
        var ultimaCartaPilaCinco = klondike.mostrarCartaPila(5);
        var ultimaCartaPilaSeis = klondike.mostrarCartaPila(6);
        var ultimaCartaPilaSiete = klondike.mostrarCartaPila(7);

        var cartaFundacionUno = klondike.mostrarCartaFundacion(1);
        var cartaFundacionDos = klondike.mostrarCartaFundacion(2);
        var cartaFundacionTres = klondike.mostrarCartaFundacion(3);
        var cartaFundacionCuatro = klondike.mostrarCartaFundacion(4);

        // THEN
        assertTrue(ultimaCartaPilaUno.isPresent());
        assertTrue(ultimaCartaPilaDos.isPresent());
        assertTrue(ultimaCartaPilaTres.isPresent());
        assertTrue(ultimaCartaPilaCuatro.isPresent());
        assertTrue(ultimaCartaPilaCinco.isPresent());
        assertTrue(ultimaCartaPilaSeis.isPresent());
        assertTrue(ultimaCartaPilaSiete.isPresent());
        assertTrue(cartaFundacionUno.isPresent());
        assertFalse(cartaFundacionDos.isPresent());
        assertFalse(cartaFundacionTres.isPresent());
        assertFalse(cartaFundacionCuatro.isPresent());

        assertEquals(new Carta(5, Palo.DIAMANTE, true), ultimaCartaPilaUno.get());
        assertEquals(new Carta(4, Palo.PICA,true), ultimaCartaPilaDos.get());
        assertEquals(new Carta(11,Palo.PICA, true), ultimaCartaPilaTres.get());
        assertEquals(new Carta(3, Palo.PICA,true), ultimaCartaPilaCuatro.get());
        assertEquals(new Carta(12,Palo.DIAMANTE, true), ultimaCartaPilaCinco.get());
        assertEquals(new Carta(11,Palo.DIAMANTE, true), ultimaCartaPilaSeis.get());
        assertEquals(new Carta(3, Palo.CORAZON,true), ultimaCartaPilaSiete.get());
        assertEquals(new Carta(1, Palo.PICA,true), cartaFundacionUno.get());
    }

    @Test
    public void TestMoverCartaPilaAFundacionVaciaCartaDistintaDeAs() {
        // GIVEN
        Mazo mazo = new Mazo(SEED_6);
        mazo.mezclar();
        Klondike klondike = new Klondike(mazo);
        klondike.armarTablero();

        // WHEN
        klondike.moverPilaFundacion(1,1);

        var ultimaCartaPilaUno = klondike.mostrarCartaPila(1);
        var ultimaCartaPilaDos = klondike.mostrarCartaPila(2);
        var ultimaCartaPilaTres = klondike.mostrarCartaPila(3);
        var ultimaCartaPilaCuatro = klondike.mostrarCartaPila(4);
        var ultimaCartaPilaCinco = klondike.mostrarCartaPila(5);
        var ultimaCartaPilaSeis = klondike.mostrarCartaPila(6);
        var ultimaCartaPilaSiete = klondike.mostrarCartaPila(7);

        var cartaFundacionUno = klondike.mostrarCartaFundacion(1);
        var cartaFundacionDos = klondike.mostrarCartaFundacion(2);
        var cartaFundacionTres = klondike.mostrarCartaFundacion(3);
        var cartaFundacionCuatro = klondike.mostrarCartaFundacion(4);

        // THEN
        assertTrue(ultimaCartaPilaUno.isPresent());
        assertTrue(ultimaCartaPilaDos.isPresent());
        assertTrue(ultimaCartaPilaTres.isPresent());
        assertTrue(ultimaCartaPilaCuatro.isPresent());
        assertTrue(ultimaCartaPilaCinco.isPresent());
        assertTrue(ultimaCartaPilaSeis.isPresent());
        assertTrue(ultimaCartaPilaSiete.isPresent());
        assertFalse(cartaFundacionUno.isPresent());
        assertFalse(cartaFundacionDos.isPresent());
        assertFalse(cartaFundacionTres.isPresent());
        assertFalse(cartaFundacionCuatro.isPresent());
        assertEquals(new Carta(5, Palo.DIAMANTE, true), ultimaCartaPilaUno.get());
        assertEquals(new Carta(4, Palo.PICA,true), ultimaCartaPilaDos.get());
        assertEquals(new Carta(11,Palo.PICA, true), ultimaCartaPilaTres.get());
        assertEquals(new Carta(3, Palo.PICA,true), ultimaCartaPilaCuatro.get());
        assertEquals(new Carta(1,Palo.PICA, true), ultimaCartaPilaCinco.get());
        assertEquals(new Carta(11,Palo.DIAMANTE, true), ultimaCartaPilaSeis.get());
        assertEquals(new Carta(3, Palo.CORAZON,true), ultimaCartaPilaSiete.get());
    }

    @Test
    public void TestMoverCartaPilaAFundacionConCartasFormaEscalera() {
        // GIVEN
        var lector = new LectorCabecera();
        var klondike = new Klondike(lector);
        var ruta = Objects.requireNonNull(this.getClass().getResource("/fundacionConCartas.txt")).getFile();
        klondike.cargarEstado(ruta);

        // WHEN
        klondike.moverPilaFundacion(3,1);

        var cartaPilaUno = klondike.mostrarCartaPila(1);
        var cartaPilaDos = klondike.mostrarCartaPila(2);
        var cartaPilaTres = klondike.mostrarCartaPila(3);
        var cartaPilaCuatro = klondike.mostrarCartaPila(4);
        var cartaPilaCinco = klondike.mostrarCartaPila(5);
        var cartaPilaSeis = klondike.mostrarCartaPila(6);
        var cartaPilaSiete = klondike.mostrarCartaPila(7);
        var cartaFundacionUno = klondike.mostrarCartaFundacion(1);
        var cartaFundacionDos = klondike.mostrarCartaFundacion(2);
        var cartaFundacionTres = klondike.mostrarCartaFundacion(3);
        var cartaFundacionCuatro = klondike.mostrarCartaFundacion(4);

        // THEN
        assertTrue(cartaPilaUno.isPresent());
        assertTrue(cartaPilaDos.isPresent());
        assertTrue(cartaPilaTres.isPresent());
        assertTrue(cartaPilaCuatro.isPresent());
        assertTrue(cartaPilaCinco.isEmpty());
        assertTrue(cartaPilaSeis.isEmpty());
        assertTrue(cartaPilaSiete.isEmpty());
        assertTrue(cartaFundacionUno.isPresent());
        assertTrue(cartaFundacionDos.isPresent());
        assertTrue(cartaFundacionTres.isPresent());
        assertTrue(cartaFundacionCuatro.isPresent());
        assertEquals(new Carta(13, Palo.DIAMANTE, true), cartaPilaUno.get());
        assertEquals(new Carta(9, Palo.PICA, true), cartaPilaDos.get());
        assertEquals(new Carta(7, Palo.PICA, true), cartaPilaTres.get());
        assertEquals(new Carta(6, Palo.PICA, true), cartaPilaCuatro.get());
        assertEquals(new Carta(9, Palo.CORAZON, true), cartaFundacionUno.get());
        assertEquals(new Carta(5, Palo.PICA, true), cartaFundacionDos.get());
        assertEquals(new Carta(13, Palo.TREBOL, true), cartaFundacionTres.get());
        assertEquals(new Carta(1, Palo.DIAMANTE, true), cartaFundacionCuatro.get());
    }

    @Test
    public void TestMoverCartaPilaAFundacionConCartasQueNoFormanEscalera() {
        // GIVEN
        var lector = new LectorCabecera();
        var klondike = new Klondike(lector);
        var ruta = Objects.requireNonNull(this.getClass().getResource("/fundacionConCartas.txt")).getFile();
        klondike.cargarEstado(ruta);

        // WHEN
        klondike.moverPilaFundacion(4,1);

        var cartaPilaUno = klondike.mostrarCartaPila(1);
        var cartaPilaDos = klondike.mostrarCartaPila(2);
        var cartaPilaTres = klondike.mostrarCartaPila(3);
        var cartaPilaCuatro = klondike.mostrarCartaPila(4);
        var cartaPilaCinco = klondike.mostrarCartaPila(5);
        var cartaPilaSeis = klondike.mostrarCartaPila(6);
        var cartaPilaSiete = klondike.mostrarCartaPila(7);
        var cartaFundacionUno = klondike.mostrarCartaFundacion(1);
        var cartaFundacionDos = klondike.mostrarCartaFundacion(2);
        var cartaFundacionTres = klondike.mostrarCartaFundacion(3);
        var cartaFundacionCuatro = klondike.mostrarCartaFundacion(4);

        // THEN
        assertTrue(cartaPilaUno.isPresent());
        assertTrue(cartaPilaDos.isPresent());
        assertTrue(cartaPilaTres.isPresent());
        assertTrue(cartaPilaCuatro.isPresent());
        assertTrue(cartaPilaCinco.isEmpty());
        assertTrue(cartaPilaSeis.isEmpty());
        assertTrue(cartaPilaSiete.isEmpty());
        assertTrue(cartaFundacionUno.isPresent());
        assertTrue(cartaFundacionDos.isPresent());
        assertTrue(cartaFundacionTres.isPresent());
        assertTrue(cartaFundacionCuatro.isPresent());
        assertEquals(new Carta(13, Palo.DIAMANTE, true), cartaPilaUno.get());
        assertEquals(new Carta(9, Palo.PICA, true), cartaPilaDos.get());
        assertEquals(new Carta(9, Palo.CORAZON, true), cartaPilaTres.get());
        assertEquals(new Carta(6, Palo.PICA, true), cartaPilaCuatro.get());
        assertEquals(new Carta(8, Palo.CORAZON, true), cartaFundacionUno.get());
        assertEquals(new Carta(5, Palo.PICA, true), cartaFundacionDos.get());
        assertEquals(new Carta(13, Palo.TREBOL, true), cartaFundacionTres.get());
        assertEquals(new Carta(1, Palo.DIAMANTE, true), cartaFundacionCuatro.get());
    }

    @Test
    public void TestMoverCartaPilaAFundacionConCartasFormanEscaleraPeroNoEsElMismoPalo() {
        // GIVEN
        var lector = new LectorCabecera();
        var klondike = new Klondike(lector);
        var ruta = Objects.requireNonNull(this.getClass().getResource("/fundacionConCartas.txt")).getFile();
        klondike.cargarEstado(ruta);

        // WHEN
        klondike.moverPilaFundacion(2,1);

        var cartaPilaUno = klondike.mostrarCartaPila(1);
        var cartaPilaDos = klondike.mostrarCartaPila(2);
        var cartaPilaTres = klondike.mostrarCartaPila(3);
        var cartaPilaCuatro = klondike.mostrarCartaPila(4);
        var cartaPilaCinco = klondike.mostrarCartaPila(5);
        var cartaPilaSeis = klondike.mostrarCartaPila(6);
        var cartaPilaSiete = klondike.mostrarCartaPila(7);
        var cartaFundacionUno = klondike.mostrarCartaFundacion(1);
        var cartaFundacionDos = klondike.mostrarCartaFundacion(2);
        var cartaFundacionTres = klondike.mostrarCartaFundacion(3);
        var cartaFundacionCuatro = klondike.mostrarCartaFundacion(4);

        // THEN
        assertTrue(cartaPilaUno.isPresent());
        assertTrue(cartaPilaDos.isPresent());
        assertTrue(cartaPilaTres.isPresent());
        assertTrue(cartaPilaCuatro.isPresent());
        assertTrue(cartaPilaCinco.isEmpty());
        assertTrue(cartaPilaSeis.isEmpty());
        assertTrue(cartaPilaSiete.isEmpty());
        assertTrue(cartaFundacionUno.isPresent());
        assertTrue(cartaFundacionDos.isPresent());
        assertTrue(cartaFundacionTres.isPresent());
        assertTrue(cartaFundacionCuatro.isPresent());
        assertEquals(new Carta(13, Palo.DIAMANTE, true), cartaPilaUno.get());
        assertEquals(new Carta(9, Palo.PICA, true), cartaPilaDos.get());
        assertEquals(new Carta(9, Palo.CORAZON, true), cartaPilaTres.get());
        assertEquals(new Carta(6, Palo.PICA, true), cartaPilaCuatro.get());
        assertEquals(new Carta(8, Palo.CORAZON, true), cartaFundacionUno.get());
        assertEquals(new Carta(5, Palo.PICA, true), cartaFundacionDos.get());
        assertEquals(new Carta(13, Palo.TREBOL, true), cartaFundacionTres.get());
        assertEquals(new Carta(1, Palo.DIAMANTE, true), cartaFundacionCuatro.get());
    }


    @Test
    public void TestDescarteAFundacionFormaEscalera() {
        // GIVEN
        var lector = new LectorCabecera();
        var klondike = new Klondike(lector);
        var ruta = Objects.requireNonNull(this.getClass().getResource("/descarteAFundacion.txt")).getFile();
        klondike.cargarEstado(ruta);

        // WHEN
        klondike.moverDescarteFundacion(3);

        var cartaDescarte = klondike.mostrarCartaDescarte();
        var cartaFundacionUno = klondike.mostrarCartaFundacion(1);
        var cartaFundacionDos = klondike.mostrarCartaFundacion(2);
        var cartaFundacionTres = klondike.mostrarCartaFundacion(3);
        var cartaFundacionCuatro = klondike.mostrarCartaFundacion(4);

        // THEN
        assertTrue(cartaFundacionUno.isPresent());
        assertTrue(cartaFundacionDos.isPresent());
        assertTrue(cartaFundacionTres.isPresent());
        assertTrue(cartaFundacionCuatro.isPresent());
        assertTrue(cartaDescarte.isPresent());
        assertEquals(new Carta(8, Palo.CORAZON, true), cartaFundacionUno.get());
        assertEquals(new Carta(5, Palo.PICA, true), cartaFundacionDos.get());
        assertEquals(new Carta(13, Palo.TREBOL, true), cartaFundacionTres.get());
        assertEquals(new Carta(1, Palo.DIAMANTE, true), cartaFundacionCuatro.get());
        assertEquals(new Carta(8, Palo.DIAMANTE, false), cartaDescarte.get());
    }

    @Test
    public void testMoverTalonADescarte() {
        // GIVEN
        Mazo mazo = new Mazo(SEED_8);
        mazo.mezclar();
        Klondike klondike = new Klondike(mazo);
        klondike.armarTablero();

        // WHEN
        klondike.moverTalonDescarte();
        var cartaDescarteUno = klondike.mostrarCartaDescarte();
        klondike.moverTalonDescarte();
        var cartaDescarteDos = klondike.mostrarCartaDescarte();
        klondike.moverTalonDescarte();
        var cartaDescarteTres = klondike.mostrarCartaDescarte();

        // THEN
        assertTrue(cartaDescarteUno.isPresent());
        assertEquals(new Carta(13, Palo.TREBOL, true), cartaDescarteUno.get());
        assertTrue(cartaDescarteDos.isPresent());
        assertEquals(new Carta(9, Palo.TREBOL, true), cartaDescarteDos.get());
        assertTrue(cartaDescarteTres.isPresent());
        assertEquals(new Carta(5, Palo.DIAMANTE, true), cartaDescarteTres.get());
    }

    @Test
    public void testPasarFundacionAPilaNoVacia() {
        // GIVEN
        var lector = new LectorCabecera();
        var klondike = new Klondike(lector);
        var ruta = Objects.requireNonNull(this.getClass().getResource("/fundacionConCartas.txt")).getFile();
        klondike.cargarEstado(ruta);

        // WHEN
        klondike.moverFundacionPila(1,2);

        var cartaPilaUno = klondike.mostrarCartaPila(1);
        var cartaPilaDos = klondike.mostrarCartaPila(2);
        var cartaPilaTres = klondike.mostrarCartaPila(3);
        var cartaPilaCuatro = klondike.mostrarCartaPila(4);
        var cartaPilaCinco = klondike.mostrarCartaPila(5);
        var cartaPilaSeis = klondike.mostrarCartaPila(6);
        var cartaPilaSiete = klondike.mostrarCartaPila(7);
        var cartaFundacionUno = klondike.mostrarCartaFundacion(1);
        var cartaFundacionDos = klondike.mostrarCartaFundacion(2);
        var cartaFundacionTres = klondike.mostrarCartaFundacion(3);
        var cartaFundacionCuatro = klondike.mostrarCartaFundacion(4);

        // THEN
        assertTrue(cartaPilaUno.isPresent());
        assertTrue(cartaPilaDos.isPresent());
        assertTrue(cartaPilaTres.isPresent());
        assertTrue(cartaPilaCuatro.isPresent());
        assertTrue(cartaPilaCinco.isEmpty());
        assertTrue(cartaPilaSeis.isEmpty());
        assertTrue(cartaPilaSiete.isEmpty());
        assertTrue(cartaFundacionUno.isPresent());
        assertTrue(cartaFundacionDos.isPresent());
        assertTrue(cartaFundacionTres.isPresent());
        assertTrue(cartaFundacionCuatro.isPresent());
        assertEquals(new Carta(13, Palo.DIAMANTE, true), cartaPilaUno.get());
        assertEquals(new Carta(8, Palo.CORAZON, true), cartaPilaDos.get());
        assertEquals(new Carta(9, Palo.CORAZON, true), cartaPilaTres.get());
        assertEquals(new Carta(6, Palo.PICA, true), cartaPilaCuatro.get());
        assertEquals(new Carta(7, Palo.CORAZON, true), cartaFundacionUno.get());
        assertEquals(new Carta(5, Palo.PICA, true), cartaFundacionDos.get());
        assertEquals(new Carta(13, Palo.TREBOL, true), cartaFundacionTres.get());
        assertEquals(new Carta(1, Palo.DIAMANTE, true), cartaFundacionCuatro.get());
    }

    @Test
    public void testNoPasaDeFundacionAPilaNoVacia() {
        // GIVEN
        var lector = new LectorCabecera();
        var klondike = new Klondike(lector);
        var ruta = Objects.requireNonNull(this.getClass().getResource("/fundacionConCartas.txt")).getFile();
        klondike.cargarEstado(ruta);

        // WHEN
        klondike.moverFundacionPila(2,4);

        var cartaPilaUno = klondike.mostrarCartaPila(1);
        var cartaPilaDos = klondike.mostrarCartaPila(2);
        var cartaPilaTres = klondike.mostrarCartaPila(3);
        var cartaPilaCuatro = klondike.mostrarCartaPila(4);
        var cartaPilaCinco = klondike.mostrarCartaPila(5);
        var cartaPilaSeis = klondike.mostrarCartaPila(6);
        var cartaPilaSiete = klondike.mostrarCartaPila(7);
        var cartaFundacionUno = klondike.mostrarCartaFundacion(1);
        var cartaFundacionDos = klondike.mostrarCartaFundacion(2);
        var cartaFundacionTres = klondike.mostrarCartaFundacion(3);
        var cartaFundacionCuatro = klondike.mostrarCartaFundacion(4);

        // THEN
        assertTrue(cartaPilaUno.isPresent());
        assertTrue(cartaPilaDos.isPresent());
        assertTrue(cartaPilaTres.isPresent());
        assertTrue(cartaPilaCuatro.isPresent());
        assertTrue(cartaPilaCinco.isEmpty());
        assertTrue(cartaPilaSeis.isEmpty());
        assertTrue(cartaPilaSiete.isEmpty());
        assertTrue(cartaFundacionUno.isPresent());
        assertTrue(cartaFundacionDos.isPresent());
        assertTrue(cartaFundacionTres.isPresent());
        assertTrue(cartaFundacionCuatro.isPresent());
        assertEquals(new Carta(13, Palo.DIAMANTE, true), cartaPilaUno.get());
        assertEquals(new Carta(9, Palo.PICA, true), cartaPilaDos.get());
        assertEquals(new Carta(9, Palo.CORAZON, true), cartaPilaTres.get());
        assertEquals(new Carta(6, Palo.PICA, true), cartaPilaCuatro.get());
        assertEquals(new Carta(8, Palo.CORAZON, true), cartaFundacionUno.get());
        assertEquals(new Carta(5, Palo.PICA, true), cartaFundacionDos.get());
        assertEquals(new Carta(13, Palo.TREBOL, true), cartaFundacionTres.get());
        assertEquals(new Carta(1, Palo.DIAMANTE, true), cartaFundacionCuatro.get());
    }

    @Test
    public void testPasarDeFundacionCartaKingAPilaVacia() {
        // GIVEN
        var lector = new LectorCabecera();
        var klondike = new Klondike(lector);
        var ruta = Objects.requireNonNull(this.getClass().getResource("/fundacionConCartas.txt")).getFile();
        klondike.cargarEstado(ruta);

        // WHEN
        klondike.moverFundacionPila(3,6);

        var cartaPilaUno = klondike.mostrarCartaPila(1);
        var cartaPilaDos = klondike.mostrarCartaPila(2);
        var cartaPilaTres = klondike.mostrarCartaPila(3);
        var cartaPilaCuatro = klondike.mostrarCartaPila(4);
        var cartaPilaCinco = klondike.mostrarCartaPila(5);
        var cartaPilaSeis = klondike.mostrarCartaPila(6);
        var cartaPilaSiete = klondike.mostrarCartaPila(7);
        var cartaFundacionUno = klondike.mostrarCartaFundacion(1);
        var cartaFundacionDos = klondike.mostrarCartaFundacion(2);
        var cartaFundacionTres = klondike.mostrarCartaFundacion(3);
        var cartaFundacionCuatro = klondike.mostrarCartaFundacion(4);

        // THEN
        assertTrue(cartaPilaUno.isPresent());
        assertTrue(cartaPilaDos.isPresent());
        assertTrue(cartaPilaTres.isPresent());
        assertTrue(cartaPilaCuatro.isPresent());
        assertTrue(cartaPilaCinco.isEmpty());
        assertTrue(cartaPilaSeis.isPresent());
        assertTrue(cartaPilaSiete.isEmpty());
        assertTrue(cartaFundacionUno.isPresent());
        assertTrue(cartaFundacionDos.isPresent());
        assertTrue(cartaFundacionTres.isPresent());
        assertTrue(cartaFundacionCuatro.isPresent());
        assertEquals(new Carta(13, Palo.DIAMANTE, true), cartaPilaUno.get());
        assertEquals(new Carta(9, Palo.PICA, true), cartaPilaDos.get());
        assertEquals(new Carta(9, Palo.CORAZON, true), cartaPilaTres.get());
        assertEquals(new Carta(6, Palo.PICA, true), cartaPilaCuatro.get());
        assertEquals(new Carta(13, Palo.TREBOL, true), cartaPilaSeis.get());
        assertEquals(new Carta(8, Palo.CORAZON, true), cartaFundacionUno.get());
        assertEquals(new Carta(5, Palo.PICA, true), cartaFundacionDos.get());
        assertEquals(new Carta(12, Palo.TREBOL, true), cartaFundacionTres.get());
        assertEquals(new Carta(1, Palo.DIAMANTE, true), cartaFundacionCuatro.get());
    }

    @Test(expected = RuntimeException.class)
    public void testCargarEstadoArchivoSinFormato(){
        var lector = new LectorCabecera();
        var klondike = new Klondike(lector);
        var ruta = Objects.requireNonNull(this.getClass().getResource("/archivoErroneo.txt")).getFile();
        klondike.cargarEstado(ruta);
    }

    @Test
    public void testJuegoGanado() {
        var lector = new LectorCabecera();
        var klondike = new Klondike(lector);
        var ruta = Objects.requireNonNull(this.getClass().getResource("/juegoGanado.txt")).getFile();
        klondike.cargarEstado(ruta);
        assertTrue(klondike.juegoGanado());
    }

    // ----- Klondike con persistencia -----
    @Test
    public void testJuegoEstadoConPersistencia() {
        // GIVEN
        var mazo = new Mazo(SEED_8);
        mazo.mezclar();
        var serializador = new SerializacionDeTableros();
        var klondike = new Klondike(mazo, serializador);
        klondike.armarTablero();

        // WHEN
        klondike.moverPilaPila(7, 5, 1);
        var guardado = klondike.guardarPartida();

        klondike.moverPilaPila(7, 2, 1);
        klondike.moverPilaPila(4, 6, 1);
        klondike.moverPilaPila(2, 4, 2);
        klondike.moverPilaPila(4, 3, 3);
        klondike.moverPilaFundacion(4, 1);
        klondike.moverPilaFundacion(5, 1);
        klondike.moverTalonDescarte();
        klondike.moverTalonDescarte();
        klondike.moverTalonDescarte();

        var klondikeJuegoGuardado = new Klondike(serializador);
        var recuperado = klondikeJuegoGuardado.recuperarPartida();
        var tableroRecuperado = klondikeJuegoGuardado.getTablero();

        var ultimaCartaPilaSiete = klondikeJuegoGuardado.mostrarCartaPila(7);
        var ultimaCartaPilaDos = klondikeJuegoGuardado.mostrarCartaPila(2);
        var ultimaCartaPilaTres = klondikeJuegoGuardado.mostrarCartaPila(3);
        var ultimaCartaPilaCuatro = klondikeJuegoGuardado.mostrarCartaPila(4);
        var ultimaCartaPilaCinco = klondikeJuegoGuardado.mostrarCartaPila(5);
        var ultimaCartaPilaSeis = klondikeJuegoGuardado.mostrarCartaPila(6);
        var ultimaCartaPilaUno = klondikeJuegoGuardado.mostrarCartaPila(1);
        var fundacionUno = tableroRecuperado.seleccionarFundacion(1);
        var fundacionDos = tableroRecuperado.seleccionarFundacion(2);
        var fundacionTres = tableroRecuperado.seleccionarFundacion(3);
        var fundacionCuatro = tableroRecuperado.seleccionarFundacion(4);
        var descarte = tableroRecuperado.getDescarte();
        var talon = tableroRecuperado.getTalon();

        // THEN
        assertTrue(guardado);
        assertTrue(recuperado);
        assertTrue(ultimaCartaPilaUno.isPresent());
        assertTrue(ultimaCartaPilaDos.isPresent());
        assertTrue(ultimaCartaPilaTres.isPresent());
        assertTrue(ultimaCartaPilaCuatro.isPresent());
        assertTrue(ultimaCartaPilaCinco.isPresent());
        assertTrue(ultimaCartaPilaSeis.isPresent());
        assertTrue(ultimaCartaPilaSiete.isPresent());

        assertTrue(fundacionUno.estaVacia());
        assertTrue(fundacionDos.estaVacia());
        assertTrue(fundacionTres.estaVacia());
        assertTrue(fundacionCuatro.estaVacia());
        assertTrue(descarte.estaVacia());
        assertEquals(talon.cantidadDeCartas(), 24);
        assertEquals(new Carta(2, Palo.DIAMANTE, true), ultimaCartaPilaUno.get());
        assertEquals(new Carta(5, Palo.TREBOL,true), ultimaCartaPilaDos.get());
        assertEquals(new Carta(7, Palo.TREBOL, true), ultimaCartaPilaTres.get());
        assertEquals(new Carta(11, Palo.PICA,true), ultimaCartaPilaCuatro.get());
        assertEquals(new Carta(2, Palo.TREBOL, true), ultimaCartaPilaCinco.get());
        assertEquals(new Carta(12,Palo.DIAMANTE, true), ultimaCartaPilaSeis.get());
        assertEquals(new Carta(4, Palo.CORAZON, true), ultimaCartaPilaSiete.get());
    }
}
