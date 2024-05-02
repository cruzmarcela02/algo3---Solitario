package transaccion;

import elemento.Pila;

public class TransaccionPilaPila implements Transaccion {
    private final Pila pilaOrigen;
    private final Pila pilaDestino;
    private final int cantidad;

    public TransaccionPilaPila(Pila origen, Pila destino, int cantidad) {
        this.pilaOrigen = origen;
        this.pilaDestino = destino;
        this.cantidad = cantidad;
    }

    public void realizar() {
        var cartas = pilaOrigen.sacarCartas(cantidad);

        if (cartas.isEmpty()) {
            return;
        }

        if (pilaDestino.agregarCartas(cartas.get())) {
            pilaOrigen.actualizarEstado();
            return;
        }

        pilaOrigen.reponerCartas(cartas.get());
    }
}
