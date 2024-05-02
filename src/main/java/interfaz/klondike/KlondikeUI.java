package interfaz.klondike;

import elemento.Carta;
import elemento.klondike.Descarte;
import elemento.klondike.Fundacion;
import elemento.klondike.PilaKlondike;
import elemento.klondike.TalonKlondike;
import interfaz.*;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import javafx.scene.control.Menu;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import mododejuego.klondike.Klondike;

import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class KlondikeUI implements ModoUI {

    private final List<CartaVista> cartasSeleccionadas = new ArrayList<>();
    private final List<PilaVista> pilaSeleccionada = new ArrayList<>();
    private final List<FundacionVista> fundacionSeleccionada = new ArrayList<>();
    private boolean hayDescarteSeleccionado;
    private boolean hayPilaSeleccionada;
    private boolean hayFundacionSeleccionada;
    private final List<CartaVista> cartasVistaArray = new ArrayList<>();
    private StackPane talonVista;
    private StackPane descarteVista;
    @FXML
    private Menu menuJuegoKlondike;

    public Klondike klondike;
    private final String KLONDIKE = "KLONDIKE";

    public KlondikeUI(Klondike klondike, boolean juegoRecuperado) {
        this.klondike = klondike;
        if (!juegoRecuperado) {
            klondike.armarTablero();
        }
    }

    public void iniciar(Stage stage) throws IOException {
        stage.setTitle("Klondike");
        var loader = new FXMLLoader(this.getClass().getResource("/klondikeLayout.fxml"));
        loader.setController(this);
        Pane ventana = loader.load();

        descarteVista = (StackPane) ventana.lookup("#descarte");
        talonVista = (StackPane) ventana.lookup("#talon");
        var klondikeScene = new Scene(ventana, 717, 550);

        seteoDescarte();
        seteoTalon();
        seteoPilas(ventana);
        seteoFundacion(ventana, stage);

        MenuUI.seteoJuegoNuevoItem(stage, menuJuegoKlondike);

        stage.setScene(klondikeScene);
        stage.show();
    }

    private void seteoTalon() {
        var tablero = klondike.getTablero();
        var descarte = tablero.getDescarte();
        var talon = tablero.getTalon();

        for (Carta carta: talon.getCartas()) {
            var cartaVista = new CartaVista(carta);
            talonVista.getChildren().add(cartaVista);
        }

        EventHandler<MouseEvent> eventoTalon = crearEventoTalon(talon, descarte);
        talonVista.addEventHandler(MouseEvent.MOUSE_CLICKED, eventoTalon);
    }

    private void seteoDescarte() {
        var tablero = klondike.getTablero();
        var descarte = tablero.getDescarte();

        for (Carta carta: descarte.getCartas()){
            CartaVista nuevaCarta = new CartaVista(carta);
            EventHandler<MouseEvent> eventoCarta = crearEventoCarta(nuevaCarta);
            nuevaCarta.addEventHandler(MouseEvent.MOUSE_CLICKED, eventoCarta);
            descarteVista.getChildren().add(nuevaCarta);
        }

        EventHandler<MouseEvent> eventoDescarte = crearEventoDescarte(descarte);
        descarteVista.addEventHandler(MouseEvent.MOUSE_CLICKED, eventoDescarte);
    }

    private void seteoPilas(Pane ventana) {
        var tablero = klondike.getTablero();
        var pilas = tablero.getPilas();
        var nroPila = 1;

        for (PilaKlondike pila : pilas) {
            var pilaVistaCapturada = (VBox) ventana.lookup("#pila" + nroPila);

            for (Carta carta : pila.getCartas()) {
                CartaVista cartaVista = new CartaVista(carta);
                EventHandler<MouseEvent> eventoCarta = crearEventoCarta(cartaVista);
                cartaVista.addEventHandler(MouseEvent.MOUSE_CLICKED, eventoCarta);
                cartasVistaArray.add(cartaVista);
            }

            PilaVista pilaVista = new PilaVista(pilaVistaCapturada, cartasVistaArray, nroPila);
            EventHandler<MouseEvent> eventoPila = crearEventoPila(pilaVista);
            pilaVistaCapturada.addEventHandler(MouseEvent.MOUSE_CLICKED, eventoPila);
            nroPila += 1;
            cartasVistaArray.clear();
        }
    }

    private void seteoFundacion(Pane ventana, Stage stage) {
        var tablero = klondike.getTablero();
        var numeroFundacion = 1;

        for (Fundacion fundacion : tablero.getFundaciones()) {
            var fundacionVistaCapturada = (StackPane) ventana.lookup("#fundacion" + numeroFundacion);

            for (Carta carta : fundacion.getCartas()) {
                CartaVista cartaVista = new CartaVista(carta);
                EventHandler<MouseEvent> eventoCarta = crearEventoCarta(cartaVista);
                cartaVista.addEventHandler(MouseEvent.MOUSE_CLICKED, eventoCarta);
                cartasVistaArray.add(cartaVista);
            }

            FundacionVista fundacionVista = new FundacionVista(fundacionVistaCapturada, cartasVistaArray, numeroFundacion);
            EventHandler<MouseEvent> eventoFundacion = crearEventoFundacion(fundacionVista, stage);
            fundacionVistaCapturada.addEventHandler(MouseEvent.MOUSE_CLICKED, eventoFundacion);
            numeroFundacion++;
            cartasVistaArray.clear();
        }
    }

    private EventHandler<MouseEvent> crearEventoTalon(TalonKlondike talon, Descarte descarte) {
        return event -> {
            if (hayFundacionSeleccionada || hayPilaSeleccionada || hayDescarteSeleccionado) {
                deseleccionarCartas();
                reiniciarEstados();
                return;
            }

            if (talon.pasarCartaADescarte(descarte)) {
                var tope = descarte.getCartas().getLast();
                talonVista.getChildren().removeLast();
                CartaVista nuevaCarta = new CartaVista(tope);
                EventHandler<MouseEvent> eventoCarta = crearEventoCarta(nuevaCarta);
                nuevaCarta.addEventHandler(MouseEvent.MOUSE_CLICKED, eventoCarta);
                descarteVista.getChildren().add(nuevaCarta);

            } else {
                talon.recibirCartas(descarte);
                if (talon.cantidadDeCartas() != 0) {
                    for (Carta carta: talon.getCartas()){
                        descarteVista.getChildren().removeLast();
                        CartaVista nuevaCarta = new CartaVista(carta);
                        talonVista.getChildren().add(nuevaCarta);
                    }
                }
            }
        };
    }

    private EventHandler<MouseEvent> crearEventoDescarte(Descarte descarte) {
        return event -> {
            if (hayDescarteSeleccionado) {
                deseleccionarCartas();
                reiniciarEstados();
                return;
            }

            if (!descarte.estaVacia()) {
                if (hayPilaSeleccionada || hayFundacionSeleccionada) {
                    deseleccionarCartas();
                    reiniciarEstados();
                    return;
                }
                hayDescarteSeleccionado = true;
            }
        };
    }

    private EventHandler<MouseEvent> crearEventoPila(PilaVista pilaVista) {
        return event -> {
            var tablero = klondike.getTablero();
            var nroPila = pilaVista.getNroPila();
            var pila = tablero.seleccionarPila(nroPila);

            if (!hayPilaSeleccionada && !hayFundacionSeleccionada && !hayDescarteSeleccionado) {
                if (pila.cantidadDeCartas() != 0 && cartasSeleccionadas.size() == 1) {
                    hayPilaSeleccionada = true;
                    pilaSeleccionada.add(pilaVista);
                    CartaVista primeraCarta = cartasSeleccionadas.getFirst();
                    pilaVista.seleccionarEscalera(primeraCarta, cartasSeleccionadas);
                    return;

                }
            }

            if (hayDescarteSeleccionado) {
                var ant = pila.cantidadDeCartas();
                klondike.moverDescartePila(nroPila);
                var pos = pila.cantidadDeCartas();

                if (ant < pos) {
                    var carta = cartasSeleccionadas.getFirst();
                    carta.deseleccionarCarta();
                    pilaVista.getPilaVbox().getChildren().add(carta);
                }
            }

            if (hayFundacionSeleccionada){
                var fundacion = fundacionSeleccionada.getFirst();
                var nroFundacion = fundacion.getNroFundacion();
                var ant = pila.cantidadDeCartas();
                klondike.moverFundacionPila(nroFundacion, nroPila);
                var pos = pila.cantidadDeCartas();

                if (ant < pos) {
                    var carta = cartasSeleccionadas.getFirst();
                    carta.deseleccionarCarta();
                    pilaVista.getPilaVbox().getChildren().add(carta);
                }
            }

            if (hayPilaSeleccionada) {
                var pilaVistaOrigen = pilaSeleccionada.getFirst();
                int origen = pilaVistaOrigen.getNroPila();
                var pilaOrigen = tablero.seleccionarPila(origen);
                int destino = pilaVista.getNroPila();
                int cantidad = cartasSeleccionadas.size();

                int ant = tablero.seleccionarPila(destino).cantidadDeCartas();
                klondike.moverPilaPila(origen, destino, cantidad);
                int pos = tablero.seleccionarPila(destino).cantidadDeCartas();

                if (ant < pos) {
                    for (CartaVista carta: cartasSeleccionadas) {
                        carta.deseleccionarCarta();
                        pilaVista.agregarCarta(carta);
                    }

                    var actualizada = pilaVistaOrigen.actualizarVistaPila(pilaOrigen);
                    if (actualizada != null) {
                        EventHandler<MouseEvent> eventoCarta = crearEventoCarta(actualizada);
                        actualizada.addEventHandler(MouseEvent.MOUSE_CLICKED, eventoCarta);
                    }
                }
            }
            deseleccionarCartas();
            reiniciarEstados();
        };
    }

    private EventHandler<MouseEvent> crearEventoFundacion(FundacionVista fundacionVista, Stage stage) {
        return event -> {
            var tablero = klondike.getTablero();
            int nroFundacion = fundacionVista.getNroFundacion();
            var fundacion = tablero.seleccionarFundacion(nroFundacion);

            if (!hayFundacionSeleccionada && !hayPilaSeleccionada && !hayDescarteSeleccionado) {
                if (!fundacion.estaVacia()){
                    hayFundacionSeleccionada = true;
                    fundacionSeleccionada.add(fundacionVista);
                    return;
                }
            }

            if (hayFundacionSeleccionada) {
                deseleccionarCartas();
                reiniciarEstados();
                return;
            }

            if (hayDescarteSeleccionado) {
                var ant = fundacion.getCartas().size();
                klondike.moverDescarteFundacion(nroFundacion);
                var pos = fundacion.getCartas().size();

                if (ant < pos) {
                    var carta = cartasSeleccionadas.getFirst();
                    carta.deseleccionarCarta();
                    fundacionVista.agregarCartaVista(carta);
                }
            }

            if (hayPilaSeleccionada) {
                var pila = pilaSeleccionada.getFirst();
                var nroPila = pila.getNroPila();
                var pilaOrigen = tablero.seleccionarPila(nroPila);

                if (cartasSeleccionadas.size() == 1) {
                    var ant = fundacion.getCartas().size();
                    klondike.moverPilaFundacion(nroPila, nroFundacion);
                    var pos = fundacion.getCartas().size();

                    if (ant < pos) {
                        var carta = cartasSeleccionadas.getFirst();
                        carta.deseleccionarCarta();
                        fundacionVista.agregarCartaVista(carta);
                        var actualizada = pila.actualizarVistaPila(pilaOrigen);
                        if (actualizada != null) {
                            EventHandler<MouseEvent> eventoCarta = crearEventoCarta(actualizada);
                            actualizada.addEventHandler(MouseEvent.MOUSE_CLICKED, eventoCarta);
                        }
                    }
                }
            }

            deseleccionarCartas();
            reiniciarEstados();

            if (klondike.juegoGanado()) {
                MensajeGanadorUI.mostrarAlertJuegoGanado(stage, KLONDIKE);
            }
        };
    }

    private EventHandler<MouseEvent> crearEventoCarta(CartaVista cartaVista) {
        return event -> {
            if (cartaVista.sePuedeSeleccionar() && cartasSeleccionadas.isEmpty()) {
                cartasSeleccionadas.add(cartaVista);
                cartaVista.seleccionarCarta();
            }
        };
    }

    private void deseleccionarCartas() {
        for (CartaVista carta: cartasSeleccionadas) {
            carta.deseleccionarCarta();
        }
        cartasSeleccionadas.clear();
    }

    private void reiniciarEstados() {
        hayPilaSeleccionada = false;
        hayFundacionSeleccionada = false;
        hayDescarteSeleccionado = false;
        pilaSeleccionada.clear();
        fundacionSeleccionada.clear();
    }
}
