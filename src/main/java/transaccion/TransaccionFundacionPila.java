package transaccion;

import elemento.klondike.Fundacion;
import elemento.klondike.PilaKlondike;

public class TransaccionFundacionPila implements Transaccion {
    private final Fundacion fundacion;
    private final PilaKlondike pila;

    public TransaccionFundacionPila(Fundacion fundacion, PilaKlondike pila) {
        this.fundacion = fundacion;
        this.pila = pila;
    }

    public void realizar() {
        var carta = fundacion.sacarCarta();

        if (carta.isEmpty()) {
            return;
        }

        if (!pila.agregarCarta(carta.get())) {
            fundacion.agregarCarta(carta.get());
        }
    }
}
