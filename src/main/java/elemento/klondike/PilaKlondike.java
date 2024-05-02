package elemento.klondike;

import elemento.Carta;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Stack;

public class PilaKlondike extends elemento.Pila implements Serializable {

    public PilaKlondike() {
        pila = new ArrayList<>();
    }

    public PilaKlondike(List<Carta> cartas) {
        pila = cartas;
    }

    private boolean puedoAgregar(Stack<Carta> recibidas) {
        if (recibidas.isEmpty()) {
            return false;
        }

        Carta primeraCarta = recibidas.peek();
        if (!primeraCarta.esVisible()) {
            return false;
        }

        if (pila.isEmpty()) {
            return primeraCarta.esUnRey();
        }

        Carta ultimaCarta = pila.get(pila.size() - 1);
        return primeraCarta.esUnNumeroMenorYColorDistinto(ultimaCarta);
    }

    // ----- MOVIMIENTOS ENTRE PILAS -----

    public boolean agregarCartas(Stack<Carta> recibidas) {
        if (!puedoAgregar(recibidas)) {
            return false;
        }

        while (!recibidas.isEmpty()) {
            pila.add(recibidas.pop());
        }

        return true;
    }

    // ----- MOVIMIENTOS CON FUNDACION Y DESCARTE -----
    public Optional<Carta> sacarCarta() {
        if (!puedoSacar(1)) {
            return Optional.empty();
        }

        return Optional.of(pila.removeLast());
    }

    public boolean agregarCarta(Carta carta) {
        if (!puedoAgregar(carta)) {
            return false;
        }

        pila.add(carta);
        return true;
    }

    public void reponerCarta(Carta devuelta) {
        pila.add(devuelta);
    }

    // ----- PARA TESTING -----
    private boolean puedoAgregar(Carta carta) {
        if (carta == null) {
            return false;
        }

        if (!carta.esVisible()) {
            return false;
        }

        if (pila.isEmpty()) {
            return carta.esUnRey();
        }

        Carta ultimaCarta = pila.get(pila.size() - 1);
        return carta.esUnNumeroMenorYColorDistinto(ultimaCarta);
    }
    @Override
    protected boolean puedoSacar(int cantidad) {
        if (cantidad > pila.size()) {
            return false;
        }

        int pos = pila.size() - cantidad;
        Carta c = pila.get(pos);
        return c.esVisible();
    }
}
