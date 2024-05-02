package transaccion;

import elemento.klondike.Descarte;
import elemento.klondike.PilaKlondike;

public class TransaccionDescartePila implements Transaccion {
    private final Descarte descarte;
    private final PilaKlondike pila;

    public TransaccionDescartePila(Descarte descarte, PilaKlondike pila){
        this.descarte = descarte;
        this.pila = pila;
    }

    public void realizar(){
        var carta = descarte.sacarCarta();

        if (carta.isEmpty()) {
            return;
        }

        if (!pila.agregarCarta(carta.get())) {
            descarte.reponerCarta(carta.get());
        }
    }
}
