package mododejuego.spider;

import elemento.spider.PilaSpider;
import elemento.spider.TalonSpider;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TableroSpider implements Serializable {
    private final List<PilaSpider> pilas;
    private final List<PilaSpider> pilasCompletas;
    private TalonSpider talon;

    public TableroSpider() {
        this.pilas = new ArrayList<>();
        this.talon = new TalonSpider();
        this.pilasCompletas = new ArrayList<>();
    }

    public void agregarPilasCompletas(PilaSpider completa) {
        pilasCompletas.add(completa);
    }

    public void agregarPilas(PilaSpider pila) {
        pilas.add(pila);
    }

    public void agregarTalon(TalonSpider talon) {
        this.talon = talon;
    }

    public PilaSpider seleccionarPila(int posicion) {
        return pilas.get(posicion - 1);
    }

    public List<PilaSpider> getPilas() {
        return pilas;
    }

    public int cantidadDePilasCompletas() {
        return pilasCompletas.size();
    }

    public TalonSpider getTalon() {
        return talon;
    }
}
