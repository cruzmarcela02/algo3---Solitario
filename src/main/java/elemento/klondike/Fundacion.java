package elemento.klondike;

import elemento.Carta;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.Stack;

public class Fundacion implements Serializable {
    private final Stack<Carta> fundacion;

    public Fundacion() {
        fundacion = new Stack<>();
    }

    public Fundacion(Stack<Carta> cartas) {
        fundacion = cartas;
    }

    // ----- MOVIMIENTOS CON PILA Y DESCARTE -----
    public boolean agregarCarta(Carta carta) {
        if (!puedoAgregar(carta)) {
            return false;
        }

        fundacion.push(carta);
        return true;
    }

    public Optional<Carta> sacarCarta() {
        if (puedoSacar()) {
            return Optional.of(fundacion.pop());

        }

        return Optional.empty();
    }

    public Optional<Carta> verCartaTope() {
        if (estaVacia()) {
            return Optional.empty();
        }

        return Optional.of(fundacion.peek());
    }

    public boolean estaCompleta() {
        return fundacion.size() == 13;
    }

    public boolean estaVacia() {
        return fundacion.empty();
    }

    // ----- PARA TESTING -----
    private boolean puedoAgregar(Carta carta) {
        if (!puedoSacar()) {
            return carta.esUnAs();
        }

        return carta.esLaSiguiente(fundacion.peek());
    }

    private boolean puedoSacar() {
        return !fundacion.empty();
    }

    //para mostrarlas en klondikeui
    public List<Carta> getCartas (){
        return fundacion;
    }
}
