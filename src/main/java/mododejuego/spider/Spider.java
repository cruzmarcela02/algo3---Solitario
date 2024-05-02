package mododejuego.spider;

import elemento.Carta;
import elemento.Mazo;
import elemento.Palo;
import elemento.spider.PilaSpider;
import elemento.spider.TalonSpider;

import lector.Lector;
import logica.Solitario;
import mododejuego.TableroConfig;
import serializacion.SerializacionDeTableros;
import transaccion.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Spider extends Solitario {
    private static final int CANT_MAZOS = 8;
    private TableroSpider tablero;

    public Spider() {
        super(Palo.PICA, CANT_MAZOS);
        tablero = new TableroSpider();
    }

    public Spider(SerializacionDeTableros serializador) {
        super(Palo.PICA, CANT_MAZOS, serializador);
        tablero = new TableroSpider();
    }

    public Spider(Mazo mazo) {
        this();
        this.mazo = mazo;
    }

    public Spider(Lector lector) {
        super(Palo.PICA, CANT_MAZOS, lector);
        tablero = new TableroSpider();
    }

    public Spider(SerializacionDeTableros serializador, Lector lector) {
        super(Palo.PICA, CANT_MAZOS, serializador, lector);
        tablero = new TableroSpider();
    }

    public TableroSpider getTablero() {
        return tablero;
    }

    public boolean guardarPartida() {
        return this.guardarPartida(tablero);
    }

    public boolean recuperarPartida() {
        var tableroDeserializado = recuperarTablero();

        if (tableroDeserializado.isEmpty()) {
            return false;
        }

        try {
            tablero = (TableroSpider) tableroDeserializado.get();
            return true;
        } catch (RuntimeException e) {
            return false;
        }
    }

    @Override
    public boolean juegoGanado() {
        for (PilaSpider pila: tablero.getPilas()) {
            if (!pila.estaVacia()) {
                return false;
            }
        }
        TalonSpider talon = tablero.getTalon();
        return talon.estaVacio();
    }

    public TableroSpider armarTablero() {
        crearPilas();
        crearTalon();
        return tablero;
    }

    private void crearPilas() {
        var mazo = getMazoDeCartas();

        var pilas = new ArrayList<List<Carta>>();
        for (int i = 0; i < 10; i++) {
            pilas.add(new ArrayList<>());
        }

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 10; j++) {
                var cartas = mazo.getCartas();
                var carta = cartas.getLast();

                if (i == 5 &&  j >= 4) {
                    continue;
                }

                if (i == 5 || i == 4 && j >= 4) {
                    carta.darVuelta();
                }

                var actual = pilas.get(j);
                actual.add(carta);
                cartas.removeLast();
            }
        }

        for (int i = 0; i < 10; i++) {
            tablero.agregarPilas(new PilaSpider(pilas.get(i)));
        }
    }

    private void crearTalon() {
        Mazo mazo = getMazoDeCartas();
        List<Carta> cartas = mazo.getCartas();
        tablero.agregarTalon(new TalonSpider(cartas));
    }

    // ----- TABLERO ESTADO PARTICULAR -----
    public void cargarEstado(String ruta) {
        var tableroConfig = new TableroSpiderConfig(new ArrayList<>(), new ArrayList<>(), null);
        this.cargarEstado(ruta, tableroConfig);
    }

    protected void armarTablero(TableroConfig tableroConfig) {
        TableroSpiderConfig tsc = (TableroSpiderConfig) tableroConfig;
        for (PilaSpider pila: tsc.getPilas()) {
            tablero.agregarPilas(pila);
        }

        for (PilaSpider pila: tsc.getPilasCompletas()) {
            tablero.agregarPilasCompletas(pila);
        }

        tablero.agregarTalon(tsc.getTalon());
    }

    // ----- MOVIMIENTOS -----
    public void moverPilaPila(int origen, int destino, int cantidad) {
        var pilaOrigen = tablero.seleccionarPila(origen);
        var pilaDestino = tablero.seleccionarPila(destino);
        new TransaccionPilaPila(pilaOrigen, pilaDestino, cantidad).realizar();

        if (pilaDestino.hayEscaleraCompleta()){
            var completa = pilaDestino.obtenerEscalera();
            tablero.agregarPilasCompletas(completa);
            tablero.seleccionarPila(destino).actualizarEstado();
        }
    }

    public void repartirCartas() {
        var talon = tablero.getTalon();
        var pilas = tablero.getPilas();
        talon.repartir(pilas);
    }

    public Optional<Carta> mostrarCartaPila(int nroPila) {
        var cartas = tablero.seleccionarPila(nroPila);
        var cartasParaVer = cartas.verCartas(1);
        if (cartasParaVer.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(cartasParaVer.getFirst());
    }
}
