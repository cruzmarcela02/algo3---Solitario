package mododejuego.klondike;

import elemento.*;
import elemento.klondike.Descarte;
import elemento.klondike.Fundacion;
import elemento.klondike.PilaKlondike;
import elemento.klondike.TalonKlondike;
import lector.Lector;
import logica.*;
import elemento.Mazo;
import mododejuego.TableroConfig;
import serializacion.SerializacionDeTableros;
import transaccion.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Klondike extends Solitario {
    private TableroKlondike tablero;

    public Klondike() {
        tablero = new TableroKlondike();
    }

    public Klondike(Mazo mazo) {
        this();
        this.mazo = mazo;
    }

    public Klondike(Mazo mazo, SerializacionDeTableros serializador) {
        super(serializador);
        this.mazo = mazo;
        tablero = new TableroKlondike();
    }

    public Klondike(SerializacionDeTableros serializador) {
        super(serializador);
        tablero = new TableroKlondike();
    }

    public Klondike(Lector lector) {
        super(lector);
        tablero = new TableroKlondike();
    }

    public TableroKlondike getTablero() {
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
            tablero = (TableroKlondike) tableroDeserializado.get();
            return true;
        } catch (RuntimeException e) {
            return false;
        }
    }

    @Override
    public boolean juegoGanado() {
        for (Fundacion fundacion: tablero.getFundaciones()) {
            if (!fundacion.estaCompleta()) {
                return false;
            }
        }

        return true;
    }

    public TableroKlondike armarTablero() {
        crearPilas();
        crearFundaciones();
        crearTalon();
        crearDescarte();
        return tablero;
    }

    private void crearPilas() {
        var mazo = getMazoDeCartas();

        for(int i = 0; i < 7; i++) {
            var cartasApilar = new ArrayList<Carta>();
            for(int j = 0; j < i + 1; j++) {
                var cartas = mazo.getCartas();
                int indice = cartas.size() - 1;
                var carta = cartas.get(indice);

                if (i == j) {
                    carta.darVuelta();
                }

                cartasApilar.add(carta);
                cartas.remove(indice);
            }

            tablero.agregarPilas(new PilaKlondike(cartasApilar));
        }
    }

    private void crearFundaciones() {
        for(int i = 0; i < 4; i++) {
            tablero.agregarFundaciones(new Fundacion());
        }
    }

    private void crearTalon() {
        Mazo mazo = getMazoDeCartas();
        List<Carta> cartas = mazo.getCartas();
        TalonKlondike talon = new TalonKlondike(cartas);
        tablero.agregarTalon(talon);
    }

    private void crearDescarte(){
        tablero.agregarDescarte(new Descarte());
    }

    // ----- TABLERO ESTADO PARTICULAR -----
    public void cargarEstado(String ruta) {
        var tableroConfig = new TableroKlondikeConfig(new ArrayList<>(), new ArrayList<>(), null, null);
        this.cargarEstado(ruta, tableroConfig);
    }

    protected void armarTablero(TableroConfig tableroConfig) {
        TableroKlondikeConfig tkc = (TableroKlondikeConfig) tableroConfig;
        for (PilaKlondike pila: tkc.getPilas()) {
            tablero.agregarPilas(pila);
        }

        for (Fundacion fundacion: tkc.getFundaciones()) {
            tablero.agregarFundaciones(fundacion);
        }

        tablero.agregarDescarte(tkc.getDescarte());
        tablero.agregarTalon(tkc.getTalon());

    }

    // ----- MOVIMIENTOS -----
    public void moverPilaPila(int origen, int destino, int cantidad) {
        var pilaOrigen = tablero.seleccionarPila(origen);
        var pilaDestino = tablero.seleccionarPila(destino);
        new TransaccionPilaPila(pilaOrigen, pilaDestino, cantidad).realizar();
    }

    public void moverDescartePila(int destino) {
        var descarte = tablero.getDescarte();
        var pila = tablero.seleccionarPila(destino);
        new TransaccionDescartePila(descarte, pila).realizar();
    }

    public void moverDescarteFundacion(int destino){
        var descarte = tablero.getDescarte();
        var fundacion = tablero.seleccionarFundacion(destino);
        new TransaccionDescarteFundacion(descarte, fundacion).realizar();
    }

    public void moverFundacionPila(int origen, int destino) {
        var fundacion = tablero.seleccionarFundacion(origen);
        var pila = tablero.seleccionarPila(destino);
        new TransaccionFundacionPila(fundacion,pila).realizar();
    }

    public void moverPilaFundacion(int origen, int destino) {
        var pila = tablero.seleccionarPila(origen);
        var fundacion = tablero.seleccionarFundacion(destino);
        new TransaccionPilaFundacion(pila, fundacion).realizar();
    }

    public void moverTalonDescarte(){
        var talon = tablero.getTalon();
        var descarte = tablero.getDescarte();
        new TransaccionTalonDescarte(talon, descarte).realizar();
    }

    public Optional<Carta> mostrarCartaFundacion(int nroFundacion) {
        var cartas = tablero.seleccionarFundacion(nroFundacion);
        return cartas.verCartaTope();
    }

    public Optional<Carta> mostrarCartaPila(int nroPila) {
        var cartas = tablero.seleccionarPila(nroPila);
        var cartasParaVer = cartas.verCartas(1);
        if (cartasParaVer.isEmpty()){
            return Optional.empty();
        }
        return  Optional.of(cartasParaVer.getFirst());
    }

    public Optional<Carta> mostrarCartaDescarte() {
        var descarte = tablero.getDescarte();
        return descarte.verCartaTope();
    }
}
