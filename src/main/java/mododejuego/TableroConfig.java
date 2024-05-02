package mododejuego;

import elemento.Carta;

import java.util.List;

public interface TableroConfig {

    void agregarPila(List<Carta> cartasRecibidas);

    void agregarTalon(List<Carta> cartasRecibidas);
}
