package lector.klondike;

import elemento.Carta;
import elemento.klondike.Fundacion;
import lector.Lector;
import mododejuego.TableroConfig;
import mododejuego.klondike.TableroKlondikeConfig;

import java.util.Optional;
import java.util.Stack;

public class LectorFundacion extends Lector {
    private final Stack<Carta> fundacion;

    public LectorFundacion() {
        fundacion = new Stack<>();
        lecturaFinalizada = false;
    }

    @Override
    public void leer(String carta) {
        Optional<Carta> cartaLeida = obtenerCarta(carta);
        cartaLeida.ifPresent(fundacion::add);
    }

    @Override
    public void obtenerValor(TableroConfig config) {
        TableroKlondikeConfig tkc = (TableroKlondikeConfig) config;
        tkc.getFundaciones().add(new Fundacion(fundacion));
    }
}
