package lector.spider;

import elemento.Carta;
import elemento.spider.PilaSpider;
import lector.Lector;
import mododejuego.TableroConfig;
import mododejuego.spider.TableroSpiderConfig;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LectorPilaCompleta extends Lector {
    private final List<Carta> pilaCompleta;

    public LectorPilaCompleta() {
        pilaCompleta = new ArrayList<>();
        lecturaFinalizada = false;
    }

    @Override
    public void leer(String carta){
        Optional<Carta> cartaLeida = obtenerCarta(carta);
        cartaLeida.ifPresent(pilaCompleta::add);
    }

    @Override
    public void obtenerValor(TableroConfig config) {
        TableroSpiderConfig tsc = (TableroSpiderConfig) config;
        tsc.getPilasCompletas().add(new PilaSpider(pilaCompleta));
    }
}
