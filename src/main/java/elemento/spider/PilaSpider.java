package elemento.spider;

import elemento.Carta;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PilaSpider extends elemento.Pila implements Serializable {
    private static final int TOTAL = 13;

    public PilaSpider() {
        pila = new ArrayList<>();
    }

    public PilaSpider(List<Carta> cartas) {
        pila = cartas;
    }

    public boolean estaVacia() {
        return pila.isEmpty();
    }

    @Override
    protected boolean puedoSacar(int cantidad) {
        if (cantidad > pila.size() || pila.isEmpty()) {
            return false;
        }

        if (cantidad == 1) {
            return true;
        }
        return hayEscalera(cantidad);
    }

    public boolean puedoAgregar(Stack<Carta> recibidas) {
        if (recibidas.isEmpty()) {
            return false;
        }

        if (estaVacia()) {
            return true;
        }

        Carta ultimaCarta = pila.getLast();
        Carta primeraCarta = recibidas.peek();
        return ultimaCarta.esUnNumeroMayor(primeraCarta);
    }

    private boolean hayEscalera(int cantidad) {
        if (cantidad > pila.size()) {
            return false;
        }

        int posicion = pila.size() - cantidad;
        Carta carta = pila.get(posicion);

        if (!carta.esVisible()) {
            return false;
        }

        for (int siguiente = posicion + 1;  siguiente < pila.size(); siguiente++ ) {
            Carta abajo = pila.get(siguiente);
            if (!carta.esUnNumeroMayor(abajo)) {
                return false;
            }
            carta = abajo;
        }

        return true;
    }

    public boolean hayEscaleraCompleta() {
        return hayEscalera(TOTAL);
    }

    // ----- RECIBIR DEL TALON -----
    public void recibirCarta(Carta carta){
        carta.darVuelta();
        pila.add(carta);
    }


    // ----- AGREGAR -----
    @Override
    public boolean agregarCartas(Stack<Carta> recibidas) {
        if (!puedoAgregar(recibidas)) {
            return false;
        }

        while (!recibidas.isEmpty()) {
            pila.add(recibidas.pop());
        }
        return true;
    }

    public PilaSpider obtenerEscalera() {
        List<Carta> escalera = new ArrayList<>();
        for (int i = 0; i < TOTAL; i++){
            escalera.addFirst(pila.removeLast());
        }
        return new PilaSpider(escalera);
    }
}
