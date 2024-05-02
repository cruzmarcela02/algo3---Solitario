package mododejuego.klondike;

import elemento.klondike.Descarte;
import elemento.klondike.Fundacion;
import elemento.klondike.PilaKlondike;
import elemento.klondike.TalonKlondike;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TableroKlondike implements Serializable {
    private final List<PilaKlondike> pilas;
    private final List<Fundacion> fundaciones;
    private Descarte descarte;
    private TalonKlondike talon;

    public TableroKlondike() {
        this.pilas = new ArrayList<>();
        this.fundaciones = new ArrayList<>();
        this.descarte = new Descarte();
        this.talon = new TalonKlondike();
    }

    public void agregarPilas(PilaKlondike pila) {
        pilas.add(pila);
    }

    public void agregarFundaciones(Fundacion fundacion) {
        fundaciones.add(fundacion);
    }

    public void agregarTalon(TalonKlondike talon) {
        this.talon = talon;
    }

    public void agregarDescarte(Descarte descarte) {
        this.descarte = descarte;
    }

    public List<Fundacion> getFundaciones() {
        return fundaciones;
    }

    public PilaKlondike seleccionarPila(int posicion) {
        return pilas.get(posicion - 1);
    }

    public Fundacion seleccionarFundacion(int posicion) {
        return fundaciones.get(posicion - 1);
    }

    public Descarte getDescarte(){
        return descarte;
    }

    public TalonKlondike getTalon() {
        return talon;
    }

    public List<PilaKlondike> getPilas() {
        return pilas;
    }
}
