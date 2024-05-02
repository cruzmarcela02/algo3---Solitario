package elemento.klondike;

import elemento.Carta;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Stack;

public class Descarte implements Serializable {
    private final Stack<Carta> descarte;

    public Descarte() {
        descarte = new Stack<>();
    }

    public Descarte(Stack<Carta> cartas) {
        descarte = cartas;
    }

    // ----- INTERACCION CON TALON -----
    public void agregarCarta(Carta carta) {
        carta.darVuelta();
        descarte.push(carta);
    }

    public List<Carta> devolverCartas() {
        List<Carta> devolucion = new ArrayList<>();
        while (!estaVacia()) {
            var carta = descarte.pop();
            carta.darVuelta();
            devolucion.add(carta);
        }
        return devolucion;
    }

    // ----- MOVIMIENTOS CON FUNDACION Y PILA -----
    public Optional<Carta> sacarCarta() {
        if (estaVacia()) {
            return Optional.empty();
        }

        return Optional.of(descarte.pop());
    }

    public void reponerCarta(Carta carta) {
        descarte.push(carta);
    }

    public boolean estaVacia() {
        return descarte.empty();
    }

    public Optional<Carta> verCartaTope() {
        if (estaVacia()) {
            return Optional.empty();
        }

        return Optional.of(descarte.peek());
    }

    // UI
    public List<Carta> getCartas(){
        return descarte;
    }
}
