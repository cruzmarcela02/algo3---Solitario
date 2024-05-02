package transaccion;

import elemento.klondike.Fundacion;
import elemento.klondike.PilaKlondike;

public class TransaccionPilaFundacion implements Transaccion {
    private final PilaKlondike pila;
    private final Fundacion fundacion;

    public TransaccionPilaFundacion(PilaKlondike pila, Fundacion fundacion) {
        this.pila = pila;
        this.fundacion = fundacion;
    }

    public void realizar() {
        var carta = pila.sacarCarta();

        if (carta.isEmpty()){
            return;
        }

        if (fundacion.agregarCarta(carta.get())) {
            pila.actualizarEstado();
            return;
        }

        pila.reponerCarta(carta.get());
    }
}
