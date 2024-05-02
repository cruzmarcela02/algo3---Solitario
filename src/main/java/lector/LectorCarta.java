package lector;

import elemento.Carta;
import elemento.Palo;

import java.util.Optional;

public class LectorCarta {

    public Optional<Carta> leerCarta(String carta) {
        if (carta.isEmpty()) {
            return Optional.empty();
        }

        var datos = carta.split(" ");

        if (!datos[0].equals("Carta") || datos.length != 4) {
            return Optional.empty();
        }

        var nro = Integer.parseInt(datos[1]);
        var palo = Palo.valueOf(datos[2]);
        var esVisible = Boolean.parseBoolean(datos[3]);
        return Optional.of(new Carta(nro,palo,esVisible));
    }
}
