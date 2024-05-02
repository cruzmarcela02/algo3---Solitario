package elemento;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Mazo {
    private final ArrayList<Carta> cartas = new ArrayList<>();
    private final int ALEATORIO = -1;
    private int seed;

    public Mazo() {
        for (Palo palo: Palo.values()) {
            for (int numero = 1; numero <= 13; numero++) {
                Carta nuevaCarta = new Carta(numero,palo);
                cartas.add(nuevaCarta);
            }
        }
        this.seed = ALEATORIO;
    }

    public Mazo(Palo palo, int cantidad) {
        for (int i = 0; i < cantidad; i++) {
            for (int numero = 1; numero <= 13; numero++) {
                Carta nuevaCarta = new Carta(numero,palo);
                cartas.add(nuevaCarta);
            }
        }
        this.seed = ALEATORIO;
    }

    public Mazo(int seed){
        this();
        this.seed = seed;
    }

    public Mazo(Palo palo, int cantidad, int seed) {
        this(palo, cantidad);
        this.seed = seed;
    }

    public void mezclar() {
        if (seed == ALEATORIO) {
            Collections.shuffle(cartas);
            return;
        }
        Collections.shuffle(cartas, new Random(seed));
    }

    public ArrayList<Carta> getCartas() {
        return cartas;
    }
}