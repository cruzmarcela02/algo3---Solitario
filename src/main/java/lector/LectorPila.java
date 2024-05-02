package lector;

import elemento.Carta;
import mododejuego.TableroConfig;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LectorPila extends Lector {
    private final List<Carta> pila;

    public LectorPila() {
        pila = new ArrayList<>();
        lecturaFinalizada = false;
    }

    @Override
    public void leer(String carta) {
        Optional<Carta> cartaLeida = obtenerCarta(carta);
        cartaLeida.ifPresent(pila::add);
    }

    @Override
    public void obtenerValor(TableroConfig config) {
        config.agregarPila(pila);
    }
}
