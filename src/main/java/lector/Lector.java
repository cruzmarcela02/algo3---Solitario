package lector;

import elemento.Carta;
import mododejuego.TableroConfig;

import java.util.Optional;

public abstract class Lector {
    protected boolean lecturaFinalizada;
    protected LectorCarta lectorCarta = new LectorCarta();

    protected Optional<Carta> obtenerCarta(String carta) {
        var cartaObtenida = lectorCarta.leerCarta(carta);

        if (cartaObtenida.isEmpty()) {
            this.lecturaFinalizada = true;
        }

        return cartaObtenida;
    }

    public abstract void leer(String linea);

    public Lector obtenerLector() {
        if (lecturaFinalizada) {
            return new LectorCabecera();
        }
        return this;
    }

    public abstract void obtenerValor(TableroConfig config);

    public boolean finalizo() {
        return lecturaFinalizada;
    }

}
