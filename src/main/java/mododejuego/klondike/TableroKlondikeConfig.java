package mododejuego.klondike;

import elemento.Carta;
import elemento.klondike.TalonKlondike;
import elemento.klondike.Descarte;
import elemento.klondike.Fundacion;
import elemento.klondike.PilaKlondike;
import mododejuego.TableroConfig;

import java.util.List;

public class TableroKlondikeConfig implements TableroConfig {
    private final List<Fundacion> fundaciones;
    private final List<PilaKlondike> pilas;
    private TalonKlondike talon;
    private Descarte descarte;

    public TableroKlondikeConfig(List<Fundacion> fundaciones, List<PilaKlondike> pilas, TalonKlondike talon, Descarte descarte) {
        this.fundaciones = fundaciones;
        this.pilas = pilas;
        this.talon = talon;
        this.descarte = descarte;
    }

    public List<Fundacion> getFundaciones() {
        return fundaciones;
    }

    public List<PilaKlondike> getPilas() {
        return pilas;
    }

    public TalonKlondike getTalon() {
        return talon;
    }

    public void setTalon(TalonKlondike talon) {
        this.talon = talon;
    }

    public Descarte getDescarte() {
        return descarte;
    }

    public void setDescarte(Descarte descarte) {
        this.descarte = descarte;
    }

    @Override
    public void agregarPila(List<Carta> cartasRecibidas) {
        pilas.add(new PilaKlondike(cartasRecibidas));
    }

    @Override
    public void agregarTalon(List<Carta> cartasRecibidas) {
        talon = new TalonKlondike(cartasRecibidas);
    }
}
