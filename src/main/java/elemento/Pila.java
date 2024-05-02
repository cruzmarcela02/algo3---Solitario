package elemento;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Stack;

public abstract class Pila implements Serializable {

    protected List<Carta> pila = new ArrayList<>();
    public abstract boolean agregarCartas(Stack<Carta> recibidas);
    protected abstract boolean puedoSacar(int cantidad);

    public void actualizarEstado() {
        if (!pila.isEmpty()) {
            int pos = pila.size() - 1;

            if (!pila.get(pos).esVisible()) {
                pila.get(pos).darVuelta();
            }
        }
    }
    public void reponerCartas(Stack<Carta> devueltas) {
        while (!devueltas.isEmpty()) {
            pila.add(devueltas.pop());
        }
    }

    public Optional<Stack<Carta>> sacarCartas(int cantidad) {
        if (!puedoSacar(cantidad)){
            return Optional.empty();
        }

        var removidas = new Stack<Carta>();
        for (int i = 0; i < cantidad; i++) {
            removidas.push(pila.removeLast());
        }

        return Optional.of(removidas);
    }

    public int cantidadDeCartas() {
        return pila.size();
    }

    public List<Carta> verCartas(int cantidad) {
        if (pila.isEmpty()){
            return new ArrayList<>();
        }

        int desde = pila.size() - cantidad;
        return pila.subList(desde, pila.size());
    }

    public Carta verUltimaCarta() {
        return pila.getLast();
    }

    public List<Carta> getCartas() {
        return pila;
    }
}
