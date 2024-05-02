package mododejuego.spider;

import elemento.Carta;
import elemento.spider.PilaSpider;
import elemento.spider.TalonSpider;
import mododejuego.TableroConfig;

import java.util.List;

public class TableroSpiderConfig implements TableroConfig {
    private final List<PilaSpider> pilas;
    private final List<PilaSpider> pilasCompletas;
    private TalonSpider talon;

    public TableroSpiderConfig(List<PilaSpider> pilas, List<PilaSpider> pilasCompletas, TalonSpider talon) {
        this.pilas = pilas;
        this.pilasCompletas = pilasCompletas;
        this.talon = talon;
    }

    public List<PilaSpider> getPilasCompletas() {
        return pilasCompletas;
    }

    public List<PilaSpider> getPilas() {
        return pilas;
    }

    public TalonSpider getTalon() {
        return talon;
    }

    public void setTalon(TalonSpider talon) {
        this.talon = talon;
    }

    @Override
    public void agregarPila(List<Carta> cartasRecibidas) {
        pilas.add(new PilaSpider(cartasRecibidas));
    }

    @Override
    public void agregarTalon(List<Carta> cartasRecibidas) {
        talon = new TalonSpider(cartasRecibidas);
    }
}
