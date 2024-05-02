package lector;

import mododejuego.TableroConfig;

public class LectorNoOP extends Lector {
    @Override
    public void leer(String linea) {
        if (linea.isEmpty()) {
            lecturaFinalizada = true;
        }
    }

    @Override
    public void obtenerValor(TableroConfig config) {
        throw new RuntimeException("No respeta el formato");
    }
}
