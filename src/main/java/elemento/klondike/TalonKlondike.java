package elemento.klondike;

import elemento.Carta;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TalonKlondike implements Serializable {
    private final List<Carta> talon;

    public TalonKlondike() {
        talon = new ArrayList<>();
    }

    public TalonKlondike(List<Carta> cartas) {
        this.talon = cartas;
    }

    // ----- INTERACCION CON DESCARTE -----
    public boolean pasarCartaADescarte(Descarte descarte) {
        if (!puedoSacar()) {
            return false;
        }

        descarte.agregarCarta(talon.removeLast());
        return true;
    }

    public void recibirCartas(Descarte descarte) {
        if (talon.isEmpty()) {
            List<Carta> devueltas = descarte.devolverCartas();
            talon.addAll(devueltas);
        }
    }

    // ----- PARA TESTING -----
    private boolean puedoSacar() {
        return !talon.isEmpty();
    }

    public int cantidadDeCartas() {
        return talon.size();
    }

    //para mostrarlas en klondikeui
    public List<Carta> getCartas (){
        return talon;
    }
}
