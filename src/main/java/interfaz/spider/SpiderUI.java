package interfaz.spider;

import elemento.Carta;

import elemento.spider.PilaSpider;
import elemento.spider.TalonSpider;
import interfaz.*;

import interfaz.PilaVista;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import javafx.scene.control.Menu;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import mododejuego.spider.Spider;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SpiderUI implements ModoUI {
    private final ArrayList<CartaVista> cartasSeleccionadas = new ArrayList<>();
    private final ArrayList<PilaVista> pilasSeleccionadas = new ArrayList<>();
    private boolean hayPilaSeleccionada;
    private HBox talonVista;
    private final Spider spider;
    @FXML
    private Menu menuJuegoSpider;
    private static final String SPIDER = "SPIDER";

    public SpiderUI(Spider spider, boolean juegoRecuperado) {
        this.spider = spider;

        if (!juegoRecuperado) {
            spider.armarTablero();
        }
    }

    public void iniciar(Stage stage) throws IOException {
        stage.setTitle("Spider");
        var loader = new FXMLLoader(this.getClass().getResource("/spiderLayout.fxml"));
        loader.setController(this);
        Pane ventana = loader.load();
        var spiderScene = new Scene(ventana, 879, 660);
        seteoCartasPilas(ventana, stage);
        seteoTalon(ventana);
        MenuUI.seteoJuegoNuevoItem(stage, menuJuegoSpider);
        stage.setScene(spiderScene);
        stage.show();
    }

    private void seteoCartasPilas(Pane ventana, Stage stage) {
        var tablero = spider.getTablero();
        var pilas = tablero.getPilas();

        var nroPila = 1;

        for (PilaSpider pila: pilas) {
            var pilaVistaCapturada = (VBox) ventana.lookup("#pila" + nroPila);

            var cartasPila = pila.getCartas();
            var cartasVistaArray = new ArrayList<CartaVista>();

            for (Carta carta: cartasPila) {
                CartaVista cartaVista = new CartaVista(carta);
                EventHandler<MouseEvent> eventoCarta = crearEventoCarta(cartaVista);
                cartaVista.addEventHandler(MouseEvent.MOUSE_CLICKED, eventoCarta);
                cartasVistaArray.add(cartaVista);
            }

            PilaVista pilaVista = new PilaVista(pilaVistaCapturada, cartasVistaArray, nroPila);
            EventHandler<MouseEvent> eventoPila = crearEventoPila(pilaVista, stage);
            pilaVista.getPilaVbox().addEventHandler(MouseEvent.MOUSE_CLICKED, eventoPila);
            nroPila += 1;
        }
    }

    private void seteoTalon(Pane ventana) {
        var tablero = spider.getTablero();
        var talon = tablero.getTalon();
        var pilas = tablero.getPilas();
        var cartasTalon = talon.getCartas();
        talonVista = (HBox) ventana.lookup("#boxTalones");

        for (int i = 0; i < cantidadDeTalones(); i++) {
            StackPane miniTalon = new StackPane();
            for (int j = 0; j < 10; j++) {
                Carta cartaObtenida = cartasTalon.get(j);
                miniTalon.getChildren().add(new CartaVista(cartaObtenida));
            }
            talonVista.getChildren().add(miniTalon);
        }

        EventHandler<MouseEvent> eventoTalon = crearEventoTalon(ventana, talon, pilas);
        talonVista.addEventHandler(MouseEvent.MOUSE_CLICKED, eventoTalon);
    }

    private EventHandler<MouseEvent> crearEventoCarta(CartaVista cartaVista) {
        return event -> {
            if (cartaVista.estaSeleccionada() && cartaVista.getCarta().esVisible() && cartasSeleccionadas.isEmpty()) {
                cartasSeleccionadas.add(cartaVista);
                cartaVista.seleccionarCarta();
            }
        };
    }

    private EventHandler<MouseEvent> crearEventoPila(PilaVista pilaVista, Stage stage) {
        return event -> {
            var tablero = spider.getTablero();
            if (!hayPilaSeleccionada && !cartasSeleccionadas.isEmpty()) {
                hayPilaSeleccionada = true;
                pilasSeleccionadas.add(pilaVista);
                CartaVista primeraCarta = cartasSeleccionadas.getFirst();
                pilaVista.seleccionarEscalera(primeraCarta, cartasSeleccionadas);
                return;
            }

            if (hayPilaSeleccionada) {
                var pilaVistaOrigen = pilasSeleccionadas.getFirst();
                int origen = pilaVistaOrigen.getNroPila();
                var pilaOrigen = tablero.seleccionarPila(origen);
                int destino = pilaVista.getNroPila();
                var pilaDestino = tablero.seleccionarPila(destino);
                int cantidad = cartasSeleccionadas.size();

                int ant = tablero.seleccionarPila(destino).cantidadDeCartas();
                int antPC = tablero.cantidadDePilasCompletas();
                spider.moverPilaPila(origen, destino, cantidad);
                int posPC = tablero.cantidadDePilasCompletas();
                int pos = tablero.seleccionarPila(destino).cantidadDeCartas();

                if (ant != pos) {
                    for (CartaVista carta : cartasSeleccionadas) {
                        carta.deseleccionarCarta();
                        pilaVista.agregarCarta(carta);
                    }

                    if (antPC < posPC) {
                        for (int i = 0; i < 13; i++) {
                            pilaVista.removerUltimaCarta();
                        }

                        var actualizada = pilaVista.actualizarVistaPila(pilaDestino);
                        if (actualizada != null) {
                            EventHandler<MouseEvent> eventoCarta = crearEventoCarta(actualizada);
                            actualizada.addEventHandler(MouseEvent.MOUSE_CLICKED, eventoCarta);
                        }
                    }

                    var actualizada = pilaVistaOrigen.actualizarVistaPila(pilaOrigen);
                    if (actualizada != null) {
                        EventHandler<MouseEvent> eventoCarta = crearEventoCarta(actualizada);
                        actualizada.addEventHandler(MouseEvent.MOUSE_CLICKED, eventoCarta);
                    }
                }
                reiniciarEstados();
                deseleccionarCartas();
            }

            if (spider.juegoGanado()) {
                MensajeGanadorUI.mostrarAlertJuegoGanado(stage, SPIDER);
            }
        };
    }

    private EventHandler<MouseEvent> crearEventoTalon(Pane ventana, TalonSpider talon, List<PilaSpider> pilas) {
        return event -> {
            if (hayPilaSeleccionada) {
                deseleccionarCartas();
                reiniciarEstados();
                return;
            }

            var tablero = spider.getTablero();
            if (talon.repartir(pilas)) {
                for (int nroPila = 1; nroPila < 11; nroPila++) {
                    var pilaVbox = (VBox) ventana.lookup("#pila" + nroPila);
                    var nuevaUltimaCarta = tablero.seleccionarPila(nroPila).verUltimaCarta();
                    var nuevaUltimaCartaVista = new CartaVista(nuevaUltimaCarta);
                    EventHandler<MouseEvent> eventoCarta = crearEventoCarta(nuevaUltimaCartaVista);
                    nuevaUltimaCartaVista.addEventHandler(MouseEvent.MOUSE_CLICKED, eventoCarta);
                    pilaVbox.getChildren().add(nuevaUltimaCartaVista);
                }
                talonVista.getChildren().removeLast();
            }
        };
    }

    private int cantidadDeTalones() {
        var tablero = spider.getTablero();
        var talon = tablero.getTalon();

        return talon.cantidadDeCartas() / 10;
    }

    private void deseleccionarCartas() {
        for (CartaVista carta: cartasSeleccionadas) {
            carta.deseleccionarCarta();
        }

        cartasSeleccionadas.clear();
    }

    private void reiniciarEstados() {
        hayPilaSeleccionada = false;
        pilasSeleccionadas.clear();
    }
}
