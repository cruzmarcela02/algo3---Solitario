package logica;

import elemento.Mazo;
import lector.Lector;
import mododejuego.TableroConfig;
import serializacion.SerializacionDeTableros;
import elemento.Palo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.Optional;

public abstract class Solitario {
    private static final String RUTA = "juego.bin";
    protected Mazo mazo;
    protected SerializacionDeTableros serializador;
    protected Lector lectorCabecera;

    // ----- Constructores para Klondike -----
    public Solitario() {
        this.mazo = new Mazo();
        mazo.mezclar();
    }

    public Solitario(SerializacionDeTableros serializador) {
        this();
        this.serializador = serializador;
    }

    public Solitario(Lector lector) {
        this();
        this.lectorCabecera = lector;
    }

    // ----- Constructores para Spider -----
    public Solitario(Palo palo, int cantidad) {
        this.mazo = new Mazo(palo, cantidad);
        mazo.mezclar();
    }

    public Solitario(Palo palo, int cantidad, Lector lector) {
        this(palo, cantidad);
        this.lectorCabecera = lector;
    }

    public Solitario(Palo palo, int cantidad, SerializacionDeTableros serializador) {
        this(palo, cantidad);
        this.serializador = serializador;
    }

    public Solitario(Palo palo, int cantidad, SerializacionDeTableros serializador, Lector lector) {
        this(palo, cantidad, serializador);
        this.lectorCabecera = lector;
    }

    public abstract boolean juegoGanado();

    public abstract boolean recuperarPartida();

    public abstract boolean guardarPartida();

    protected abstract void armarTablero(TableroConfig tableroConfig);

    public Mazo getMazoDeCartas() {
        return mazo;
    }

    protected void cargarEstado(String ruta, TableroConfig tableroConfig) {
        try (var lector = new BufferedReader(new FileReader(ruta))) {
            String linea = lector.readLine();
            while (linea != null) {
                lectorCabecera.leer(linea);

                if (lectorCabecera.finalizo()) {
                    lectorCabecera.obtenerValor(tableroConfig);
                }

                lectorCabecera = lectorCabecera.obtenerLector();
                linea = lector.readLine();
            }
            lectorCabecera.obtenerValor(tableroConfig);
            armarTablero(tableroConfig);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    protected boolean guardarPartida(Serializable tablero) {
        boolean guardado = true;

        try {
            serializador.serializar(RUTA, tablero);
        } catch (IOException e) {
            guardado = false;
        }

        return guardado;
    }

    protected Optional<Serializable> recuperarTablero() {
        try {
            return Optional.of(serializador.deserializar(RUTA));
        } catch  (IOException | ClassNotFoundException e) {
            return Optional.empty();
        }
    }
}
