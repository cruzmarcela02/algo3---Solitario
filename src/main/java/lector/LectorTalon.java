package lector;

import elemento.Carta;
import mododejuego.TableroConfig;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LectorTalon extends Lector {
    private final List<Carta> talon;

    public LectorTalon() {
        talon = new ArrayList<>();
        lecturaFinalizada = false;
    }

    @Override
    public void leer(String carta) {
        Optional<Carta> cartaLeida = obtenerCarta(carta);
        cartaLeida.ifPresent(talon::add);
    }

    @Override
    public void obtenerValor(TableroConfig config) {
        config.agregarTalon(talon);
    }
}
