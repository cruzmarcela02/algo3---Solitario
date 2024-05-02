package transaccion;

import elemento.klondike.Descarte;
import elemento.klondike.Fundacion;

public class TransaccionDescarteFundacion implements Transaccion {
    private final Descarte descarte;
    private final Fundacion fundacion;

    public TransaccionDescarteFundacion(Descarte descarte, Fundacion fundacion){
        this.descarte = descarte;
        this.fundacion = fundacion;
    }

    public void realizar(){
        var carta = descarte.sacarCarta();

        if (carta.isEmpty()) {
            return;
        }

        if (!fundacion.agregarCarta(carta.get())) {
            descarte.reponerCarta(carta.get());
        }
    }
}
