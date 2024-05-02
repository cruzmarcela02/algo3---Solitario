package transaccion;

import elemento.klondike.Descarte;
import elemento.klondike.TalonKlondike;

public class TransaccionTalonDescarte implements Transaccion {
    private final TalonKlondike talon;
    private final Descarte descarte;

    public TransaccionTalonDescarte(TalonKlondike talon, Descarte descarte) {
        this.talon = talon;
        this.descarte = descarte;
    }

    public void realizar() {
        if (!talon.pasarCartaADescarte(descarte)) {
            talon.recibirCartas(descarte);
        }
    }
}
