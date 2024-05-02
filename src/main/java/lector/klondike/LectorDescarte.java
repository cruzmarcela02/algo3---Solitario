package lector.klondike;

import lector.Lector;
import mododejuego.TableroConfig;
import mododejuego.klondike.TableroKlondikeConfig;
import elemento.Carta;
import elemento.klondike.Descarte;

import java.util.Optional;
import java.util.Stack;

public class LectorDescarte extends Lector {

    private final Stack<Carta> descarte;

    public LectorDescarte() {
        descarte = new Stack<>();
        lecturaFinalizada = false;
    }

    @Override
    public void leer(String carta) {
        Optional<Carta> cartaLeida = obtenerCarta(carta);
        cartaLeida.ifPresent(descarte::add);
    }

    @Override
    public void obtenerValor(TableroConfig config) {
        TableroKlondikeConfig tkc = (TableroKlondikeConfig) config;
        tkc.setDescarte(new Descarte(descarte));
    }

}