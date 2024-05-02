package lector;

import lector.klondike.LectorDescarte;
import lector.klondike.LectorFundacion;
import lector.spider.LectorPilaCompleta;
import mododejuego.TableroConfig;

public class LectorCabecera extends Lector {
    private Lector lector;

    @Override
    public void leer(String linea) {
        if (lecturaFinalizada) {
            throw new RuntimeException("Se intento usar un lector que ya ha finalizado");
        }

        this.lector = switch (linea) {
            case "Fundacion" -> new LectorFundacion();
            case "Descarte" -> new LectorDescarte();
            case "Pila" -> new LectorPila();
            case "PilaCompleta" -> new LectorPilaCompleta();
            case "Talon" -> new LectorTalon();
            default -> new LectorNoOP();
        };

        lecturaFinalizada = true;
    }

    @Override
    public Lector obtenerLector() {
        return lector;
    }

    @Override
    public void obtenerValor(TableroConfig config) {

    }
}
