package elemento.spider;

import elemento.Carta;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TalonSpider implements Serializable {
    private final List<Carta> talon;

    public TalonSpider() {
        talon = new ArrayList<>();
    }

    public TalonSpider(List<Carta> cartas) {
        this.talon = cartas;
    }

    // ----- INTERACCION CON PILAS -----
    public boolean repartir(List<PilaSpider> pilas) {
        if (!puedoRepartir(pilas)) {
            return false;
        }

        for (PilaSpider pila: pilas) {
            pila.recibirCarta(talon.removeLast());
        }
        return true;
    }

     public boolean puedoRepartir(List<PilaSpider> pilas) {
        for (PilaSpider pila: pilas) {
            if (pila.estaVacia()) {
                return false;
            }
        }
        return !talon.isEmpty();
    }

    public List<Carta> getCartas() {
        return this.talon;
    }

    // ----- PARA TESTING -----
    public boolean estaVacio() {
        return talon.isEmpty();
    }

    public int cantidadDeCartas() {
        return talon.size();
    }
}