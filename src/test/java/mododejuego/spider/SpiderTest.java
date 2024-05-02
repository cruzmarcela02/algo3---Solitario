package mododejuego.spider;

import elemento.Carta;
import elemento.Mazo;
import elemento.Palo;
import lector.LectorCabecera;
import org.junit.Test;
import serializacion.SerializacionDeTableros;

import java.util.Objects;

import static org.junit.Assert.*;

public class SpiderTest {
    private static final int SEED_8 = 8;
    private static final int CANT_MAZOS = 8;

    @Test
    public void testJuegoEstadoInicial() {
        // GIVEN
        var mazo = new Mazo(Palo.PICA, CANT_MAZOS, SEED_8);
        mazo.mezclar();
        var spider = new Spider(mazo);

        // WHEN
        var tablero = spider.armarTablero();

        var ultimaCartaPilaUno = spider.mostrarCartaPila(1);
        var ultimaCartaPilaDos = spider.mostrarCartaPila(2);
        var ultimaCartaPilaTres = spider.mostrarCartaPila(3);
        var ultimaCartaPilaCuatro = spider.mostrarCartaPila(4);
        var ultimaCartaPilaCinco = spider.mostrarCartaPila(5);
        var ultimaCartaPilaSeis = spider.mostrarCartaPila(6);
        var ultimaCartaPilaSiete = spider.mostrarCartaPila(7);
        var ultimaCartaPilaOcho = spider.mostrarCartaPila(8);
        var ultimaCartaPilaNueve = spider.mostrarCartaPila(9);
        var ultimaCartaPilaDiez = spider.mostrarCartaPila(10);

        var talon = tablero.getTalon();

        // THEN
        assertTrue(ultimaCartaPilaUno.isPresent());
        assertTrue(ultimaCartaPilaDos.isPresent());
        assertTrue(ultimaCartaPilaTres.isPresent());
        assertTrue(ultimaCartaPilaCuatro.isPresent());
        assertTrue(ultimaCartaPilaCinco.isPresent());
        assertTrue(ultimaCartaPilaSeis.isPresent());
        assertTrue(ultimaCartaPilaSiete.isPresent());
        assertTrue(ultimaCartaPilaOcho.isPresent());
        assertTrue(ultimaCartaPilaNueve.isPresent());
        assertTrue(ultimaCartaPilaDiez.isPresent());

        assertEquals(new Carta(13, Palo.PICA, true), ultimaCartaPilaUno.get());
        assertEquals(new Carta(5, Palo.PICA,true), ultimaCartaPilaDos.get());
        assertEquals(new Carta(8, Palo.PICA, true), ultimaCartaPilaTres.get());
        assertEquals(new Carta(10, Palo.PICA,true), ultimaCartaPilaCuatro.get());
        assertEquals(new Carta(7,Palo.PICA, true), ultimaCartaPilaCinco.get());
        assertEquals(new Carta(10,Palo.PICA, true), ultimaCartaPilaSeis.get());
        assertEquals(new Carta(2, Palo.PICA,true), ultimaCartaPilaSiete.get());
        assertEquals(new Carta(13, Palo.PICA,true), ultimaCartaPilaOcho.get());
        assertEquals(new Carta(13, Palo.PICA,true), ultimaCartaPilaNueve.get());
        assertEquals(new Carta(13, Palo.PICA,true), ultimaCartaPilaDiez.get());
        assertEquals(50, talon.cantidadDeCartas());
        assertFalse(spider.juegoGanado());
    }

    @Test
    public void testMoverUnaCartaEntrePilas() {
        // GIVEN
        var mazo = new Mazo(Palo.PICA, CANT_MAZOS, SEED_8);
        mazo.mezclar();
        var spider = new Spider(mazo);
        var tablero = spider.armarTablero();

        // WHEN
        spider.moverPilaPila(5,3,1);
        var ultimaCartaPilaUno = spider.mostrarCartaPila(1);
        var ultimaCartaPilaDos = spider.mostrarCartaPila(2);
        var ultimaCartaPilaTres = spider.mostrarCartaPila(3);
        var ultimaCartaPilaCuatro = spider.mostrarCartaPila(4);
        var ultimaCartaPilaCinco = spider.mostrarCartaPila(5);
        var ultimaCartaPilaSeis = spider.mostrarCartaPila(6);
        var ultimaCartaPilaSiete = spider.mostrarCartaPila(7);
        var ultimaCartaPilaOcho = spider.mostrarCartaPila(8);
        var ultimaCartaPilaNueve = spider.mostrarCartaPila(9);
        var ultimaCartaPilaDiez = spider.mostrarCartaPila(10);

        var talon = tablero.getTalon();

        // THEN
        assertTrue(ultimaCartaPilaUno.isPresent());
        assertTrue(ultimaCartaPilaDos.isPresent());
        assertTrue(ultimaCartaPilaTres.isPresent());
        assertTrue(ultimaCartaPilaCuatro.isPresent());
        assertTrue(ultimaCartaPilaCinco.isPresent());
        assertTrue(ultimaCartaPilaSeis.isPresent());
        assertTrue(ultimaCartaPilaSiete.isPresent());
        assertTrue(ultimaCartaPilaOcho.isPresent());
        assertTrue(ultimaCartaPilaNueve.isPresent());
        assertTrue(ultimaCartaPilaDiez.isPresent());

        assertEquals(new Carta(13, Palo.PICA, true), ultimaCartaPilaUno.get());
        assertEquals(new Carta(5, Palo.PICA,true), ultimaCartaPilaDos.get());
        assertEquals(new Carta(7, Palo.PICA, true), ultimaCartaPilaTres.get());
        assertEquals(new Carta(10, Palo.PICA,true), ultimaCartaPilaCuatro.get());
        assertEquals(new Carta(6,Palo.PICA, true), ultimaCartaPilaCinco.get());
        assertEquals(new Carta(10,Palo.PICA, true), ultimaCartaPilaSeis.get());
        assertEquals(new Carta(2, Palo.PICA,true), ultimaCartaPilaSiete.get());
        assertEquals(new Carta(13, Palo.PICA,true), ultimaCartaPilaOcho.get());
        assertEquals(new Carta(13, Palo.PICA,true), ultimaCartaPilaNueve.get());
        assertEquals(new Carta(13, Palo.PICA,true), ultimaCartaPilaDiez.get());
        assertEquals(50,talon.cantidadDeCartas());
    }

    @Test
    public void testRealizarVariosMovimientosEntrePilas() {
        // GIVEN
        var mazo = new Mazo(Palo.PICA, CANT_MAZOS, SEED_8);
        mazo.mezclar();
        var spider = new Spider(mazo);
        var tablero = spider.armarTablero();

        // WHEN
        spider.moverPilaPila(5,3,1);
        spider.moverPilaPila(5,3,1);
        spider.moverPilaPila(2,3,1);
        spider.moverPilaPila(4,2,1);
        spider.moverPilaPila(4,2,1);
        spider.moverPilaPila(3,2,4);

        var ultimaCartaPilaUno = spider.mostrarCartaPila(1);
        var ultimaCartaPilaDos = spider.mostrarCartaPila(2);
        var ultimaCartaPilaTres = spider.mostrarCartaPila(3);
        var ultimaCartaPilaCuatro = spider.mostrarCartaPila(4);
        var ultimaCartaPilaCinco = spider.mostrarCartaPila(5);
        var ultimaCartaPilaSeis = spider.mostrarCartaPila(6);
        var ultimaCartaPilaSiete = spider.mostrarCartaPila(7);
        var ultimaCartaPilaOcho = spider.mostrarCartaPila(8);
        var ultimaCartaPilaNueve = spider.mostrarCartaPila(9);
        var ultimaCartaPilaDiez = spider.mostrarCartaPila(10);

        var talon = tablero.getTalon();

        // THEN
        assertTrue(ultimaCartaPilaUno.isPresent());
        assertTrue(ultimaCartaPilaDos.isPresent());
        assertTrue(ultimaCartaPilaTres.isPresent());
        assertTrue(ultimaCartaPilaCuatro.isPresent());
        assertTrue(ultimaCartaPilaCinco.isPresent());
        assertTrue(ultimaCartaPilaSeis.isPresent());
        assertTrue(ultimaCartaPilaSiete.isPresent());
        assertTrue(ultimaCartaPilaOcho.isPresent());
        assertTrue(ultimaCartaPilaNueve.isPresent());
        assertTrue(ultimaCartaPilaDiez.isPresent());

        assertEquals(new Carta(13, Palo.PICA, true), ultimaCartaPilaUno.get());
        assertEquals(new Carta(5, Palo.PICA,true), ultimaCartaPilaDos.get());
        assertEquals(new Carta(3, Palo.PICA, true), ultimaCartaPilaTres.get());
        assertEquals(new Carta(6, Palo.PICA,true), ultimaCartaPilaCuatro.get());
        assertEquals(new Carta(8,Palo.PICA, true), ultimaCartaPilaCinco.get());
        assertEquals(new Carta(10,Palo.PICA, true), ultimaCartaPilaSeis.get());
        assertEquals(new Carta(2, Palo.PICA,true), ultimaCartaPilaSiete.get());
        assertEquals(new Carta(13, Palo.PICA,true), ultimaCartaPilaOcho.get());
        assertEquals(new Carta(13, Palo.PICA,true), ultimaCartaPilaNueve.get());
        assertEquals(new Carta(13, Palo.PICA,true), ultimaCartaPilaDiez.get());
        assertEquals(50,talon.cantidadDeCartas());
    }

    @Test
    public void testNoMoverUnaCartaEntrePilas() {
        // GIVEN
        var mazo = new Mazo(Palo.PICA, CANT_MAZOS, SEED_8);
        mazo.mezclar();
        var spider = new Spider(mazo);
        spider.armarTablero();

        // WHEN
        spider.moverPilaPila(4,1,1);

        var ultimaCartaPilaUno = spider.mostrarCartaPila(1);
        var ultimaCartaPilaDos = spider.mostrarCartaPila(2);
        var ultimaCartaPilaTres = spider.mostrarCartaPila(3);
        var ultimaCartaPilaCuatro = spider.mostrarCartaPila(4);
        var ultimaCartaPilaCinco = spider.mostrarCartaPila(5);
        var ultimaCartaPilaSeis = spider.mostrarCartaPila(6);
        var ultimaCartaPilaSiete = spider.mostrarCartaPila(7);
        var ultimaCartaPilaOcho = spider.mostrarCartaPila(8);
        var ultimaCartaPilaNueve = spider.mostrarCartaPila(9);
        var ultimaCartaPilaDiez = spider.mostrarCartaPila(10);

        // THEN
        assertTrue(ultimaCartaPilaUno.isPresent());
        assertTrue(ultimaCartaPilaDos.isPresent());
        assertTrue(ultimaCartaPilaTres.isPresent());
        assertTrue(ultimaCartaPilaCuatro.isPresent());
        assertTrue(ultimaCartaPilaCinco.isPresent());
        assertTrue(ultimaCartaPilaSeis.isPresent());
        assertTrue(ultimaCartaPilaSiete.isPresent());
        assertTrue(ultimaCartaPilaOcho.isPresent());
        assertTrue(ultimaCartaPilaNueve.isPresent());
        assertTrue(ultimaCartaPilaDiez.isPresent());

        assertEquals(new Carta(13, Palo.PICA, true), ultimaCartaPilaUno.get());
        assertEquals(new Carta(5, Palo.PICA,true), ultimaCartaPilaDos.get());
        assertEquals(new Carta(8, Palo.PICA, true), ultimaCartaPilaTres.get());
        assertEquals(new Carta(10, Palo.PICA,true), ultimaCartaPilaCuatro.get());
        assertEquals(new Carta(7,Palo.PICA, true), ultimaCartaPilaCinco.get());
        assertEquals(new Carta(10,Palo.PICA, true), ultimaCartaPilaSeis.get());
        assertEquals(new Carta(2, Palo.PICA,true), ultimaCartaPilaSiete.get());
        assertEquals(new Carta(13, Palo.PICA,true), ultimaCartaPilaOcho.get());
        assertEquals(new Carta(13, Palo.PICA,true), ultimaCartaPilaNueve.get());
        assertEquals(new Carta(13, Palo.PICA,true), ultimaCartaPilaDiez.get());
    }

    @Test
    public void testRepartirCartasConPilasNoVacias() {
        // GIVEN
        var mazo = new Mazo(Palo.PICA, CANT_MAZOS, SEED_8);
        mazo.mezclar();
        var spider = new Spider(mazo);
        spider.armarTablero();

        // WHEN
        spider.repartirCartas();

        var ultimaCartaPilaUno = spider.mostrarCartaPila(1);
        var ultimaCartaPilaDos = spider.mostrarCartaPila(2);
        var ultimaCartaPilaTres = spider.mostrarCartaPila(3);
        var ultimaCartaPilaCuatro = spider.mostrarCartaPila(4);
        var ultimaCartaPilaCinco = spider.mostrarCartaPila(5);
        var ultimaCartaPilaSeis = spider.mostrarCartaPila(6);
        var ultimaCartaPilaSiete = spider.mostrarCartaPila(7);
        var ultimaCartaPilaOcho = spider.mostrarCartaPila(8);
        var ultimaCartaPilaNueve = spider.mostrarCartaPila(9);
        var ultimaCartaPilaDiez = spider.mostrarCartaPila(10);

        // THEN
        assertTrue(ultimaCartaPilaUno.isPresent());
        assertTrue(ultimaCartaPilaDos.isPresent());
        assertTrue(ultimaCartaPilaTres.isPresent());
        assertTrue(ultimaCartaPilaCuatro.isPresent());
        assertTrue(ultimaCartaPilaCinco.isPresent());
        assertTrue(ultimaCartaPilaSeis.isPresent());
        assertTrue(ultimaCartaPilaSiete.isPresent());
        assertTrue(ultimaCartaPilaOcho.isPresent());
        assertTrue(ultimaCartaPilaNueve.isPresent());
        assertTrue(ultimaCartaPilaDiez.isPresent());

        assertEquals(new Carta(2, Palo.PICA, true), ultimaCartaPilaUno.get());
        assertEquals(new Carta(5, Palo.PICA,true), ultimaCartaPilaDos.get());
        assertEquals(new Carta(7, Palo.PICA, true), ultimaCartaPilaTres.get());
        assertEquals(new Carta(1, Palo.PICA,true), ultimaCartaPilaCuatro.get());
        assertEquals(new Carta(5,Palo.PICA, true), ultimaCartaPilaCinco.get());
        assertEquals(new Carta(10,Palo.PICA, true), ultimaCartaPilaSeis.get());
        assertEquals(new Carta(5, Palo.PICA,true), ultimaCartaPilaSiete.get());
        assertEquals(new Carta(9, Palo.PICA,true), ultimaCartaPilaOcho.get());
        assertEquals(new Carta(3, Palo.PICA,true), ultimaCartaPilaNueve.get());
        assertEquals(new Carta(4, Palo.PICA,true), ultimaCartaPilaDiez.get());
    }

    @Test
    public void testNoRepartirCartasHayPilaVacia(){
        // GIVEN
        var lector = new LectorCabecera();
        var spider = new Spider(lector);
        var ruta = Objects.requireNonNull(this.getClass().getResource("/repartirCartasPilaVaciaSpider.txt")).getFile();
        spider.cargarEstado(ruta);

        // WHEN
        spider.repartirCartas();

        var ultimaCartaPilaUno = spider.mostrarCartaPila(1);
        var ultimaCartaPilaDos = spider.mostrarCartaPila(2);
        var ultimaCartaPilaTres = spider.mostrarCartaPila(3);
        var ultimaCartaPilaCuatro = spider.mostrarCartaPila(4);
        var ultimaCartaPilaCinco = spider.mostrarCartaPila(5);
        var ultimaCartaPilaSeis = spider.mostrarCartaPila(6);
        var ultimaCartaPilaSiete = spider.mostrarCartaPila(7);
        var ultimaCartaPilaOcho = spider.mostrarCartaPila(8);
        var ultimaCartaPilaNueve = spider.mostrarCartaPila(9);
        var ultimaCartaPilaDiez = spider.mostrarCartaPila(10);
        var tablero = spider.getTablero();
        var talon = tablero.getTalon();

        // THEN
        assertTrue(ultimaCartaPilaUno.isPresent());
        assertFalse(ultimaCartaPilaDos.isPresent());
        assertFalse(ultimaCartaPilaTres.isPresent());
        assertFalse(ultimaCartaPilaCuatro.isPresent());
        assertFalse(ultimaCartaPilaCinco.isPresent());
        assertFalse(ultimaCartaPilaSeis.isPresent());
        assertFalse(ultimaCartaPilaSiete.isPresent());
        assertFalse(ultimaCartaPilaOcho.isPresent());
        assertFalse(ultimaCartaPilaNueve.isPresent());
        assertFalse(ultimaCartaPilaDiez.isPresent());

        assertEquals(new Carta(10, Palo.PICA,true), ultimaCartaPilaUno.get());
        assertEquals(10, talon.cantidadDeCartas());
    }

    @Test
    public void testPilaEscaleraCompleta() {
        // GIVEN
        var lector = new LectorCabecera();
        var spider = new Spider(lector);
        var ruta = Objects.requireNonNull(this.getClass().getResource("/armarEscalerasCompletas.txt")).getFile();
        spider.cargarEstado(ruta);

        // WHEN
        spider.moverPilaPila(3, 1, 6);
        spider.moverPilaPila(1, 2, 12);

        var ultimaCartaPilaUno = spider.mostrarCartaPila(1);
        var ultimaCartaPilaDos = spider.mostrarCartaPila(2);
        var ultimaCartaPilaTres = spider.mostrarCartaPila(3);
        var tablero = spider.getTablero();

        // THEN
        assertTrue(ultimaCartaPilaUno.isPresent());
        assertFalse(ultimaCartaPilaDos.isPresent());
        assertFalse(ultimaCartaPilaTres.isPresent());
        assertEquals(new Carta(13, Palo.PICA,true), ultimaCartaPilaUno.get());
        assertEquals(tablero.cantidadDePilasCompletas(), 7);
    }

    @Test
    public void testArmamosLasDosUltimasPilasParaGanar() {
        // GIVEN
        var lector = new LectorCabecera();
        var spider = new Spider(lector);
        var ruta = Objects.requireNonNull(this.getClass().getResource("/armarEscalerasCompletas.txt")).getFile();
        spider.cargarEstado(ruta);

        // WHEN
        spider.moverPilaPila(3, 1, 6);
        spider.moverPilaPila(1, 2, 12);
        spider.moverPilaPila(4, 1, 12);

        var ultimaCartaPilaCuatro = spider.mostrarCartaPila(4);
        var ultimaCartaPilaDos = spider.mostrarCartaPila(2);
        var tablero = spider.getTablero();

        // THEN
        assertFalse(ultimaCartaPilaDos.isPresent());
        assertFalse(ultimaCartaPilaCuatro.isPresent());
        assertEquals(8, tablero.cantidadDePilasCompletas());
        assertTrue(spider.juegoGanado());
    }

    @Test(expected = RuntimeException.class)
    public void testCargarEstadoArchivoSinFormato(){
        // GIVEN
        var lector = new LectorCabecera();
        var spider = new Spider(lector);
        var ruta = Objects.requireNonNull(this.getClass().getResource("/archivoErroneo.txt")).getFile();
        spider.cargarEstado(ruta);
    }


    @Test
    public void testJuegoGanado() {
        var lector = new LectorCabecera();
        var spider = new Spider(lector);
        var ruta = Objects.requireNonNull(this.getClass().getResource("/juegoGanadoSpider.txt")).getFile();
        spider.cargarEstado(ruta);
        assertTrue(spider.juegoGanado());
    }

    // ----- Spider con persistencia -----
    @Test
    public void testJuegoEstadoConPersistencia() {
        // GIVEN
        var lector = new LectorCabecera();
        var serializador = new SerializacionDeTableros();
        var spider = new Spider(serializador, lector);
        var ruta = Objects.requireNonNull(this.getClass().getResource("/armarEscalerasCompletas.txt")).getFile();
        spider.cargarEstado(ruta);

        // WHEN
        spider.moverPilaPila(3, 1, 6);
        var guardado = spider.guardarPartida();

        spider.moverPilaPila(1, 2, 12);

        var spiderJuegoRecuperado = new Spider(serializador);
        var recuperado = spiderJuegoRecuperado.recuperarPartida();
        var tableroRecuperado = spiderJuegoRecuperado.getTablero();

        var ultimaCartaPilaUno = spiderJuegoRecuperado.mostrarCartaPila(1);
        var ultimaCartaPilaDos = spiderJuegoRecuperado.mostrarCartaPila(2);
        var ultimaCartaPilaTres = spiderJuegoRecuperado.mostrarCartaPila(3);
        var ultimaCartaPilaCuatro = spiderJuegoRecuperado.mostrarCartaPila(4);
        var ultimaCartaPilaCinco = spiderJuegoRecuperado.mostrarCartaPila(5);
        var ultimaCartaPilaSeis = spiderJuegoRecuperado.mostrarCartaPila(6);
        var ultimaCartaPilaSiete = spiderJuegoRecuperado.mostrarCartaPila(7);
        var ultimaCartaPilaOcho = spiderJuegoRecuperado.mostrarCartaPila(8);
        var ultimaCartaPilaNueve = spiderJuegoRecuperado.mostrarCartaPila(9);
        var ultimaCartaPilaDiez = spiderJuegoRecuperado.mostrarCartaPila(10);
        var talon = tableroRecuperado.getTalon();

        // THEN
        assertTrue(guardado);
        assertTrue(recuperado);
        assertTrue(ultimaCartaPilaUno.isPresent());
        assertTrue(ultimaCartaPilaDos.isPresent());
        assertFalse(ultimaCartaPilaTres.isPresent());
        assertTrue(ultimaCartaPilaCuatro.isPresent());
        assertFalse(ultimaCartaPilaCinco.isPresent());
        assertFalse(ultimaCartaPilaSeis.isPresent());
        assertFalse(ultimaCartaPilaSiete.isPresent());
        assertFalse(ultimaCartaPilaOcho.isPresent());
        assertFalse(ultimaCartaPilaNueve.isPresent());
        assertFalse(ultimaCartaPilaDiez.isPresent());

        assertEquals(new Carta(1, Palo.PICA, true), ultimaCartaPilaUno.get());
        assertEquals(new Carta(13, Palo.PICA,true), ultimaCartaPilaDos.get());
        assertEquals(new Carta(1, Palo.PICA,true), ultimaCartaPilaCuatro.get());
        assertEquals(0, talon.cantidadDeCartas());
        assertEquals(tableroRecuperado.cantidadDePilasCompletas(), 6);
    }
}
