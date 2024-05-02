package interfaz;

import interfaz.klondike.KlondikeUI;
import interfaz.spider.SpiderUI;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import mododejuego.klondike.Klondike;
import mododejuego.spider.Spider;
import serializacion.SerializacionDeTableros;

import java.io.IOException;

public class SeleccionModo {
    private static final String KLONDIKE = "KLONDIKE";
    private static final String SPIDER = "SPIDER";
    private static final SerializacionDeTableros serializador = new SerializacionDeTableros();
    private static String opcionElegida = "";
    private static Spider spider;
    private static Klondike klondike;

    public static void cargarModos() {
        spider = new Spider(serializador);
        klondike = new Klondike(serializador);
    }

    public static void mostrarVentana(Stage stage) {
        stage.setTitle("Solitario");
        Label titleLabel = new Label("Selecciona el solitario");
        ChoiceBox<String> modosChoiceBox = new ChoiceBox<>();
        modosChoiceBox.getItems().addAll(KLONDIKE, SPIDER);
        modosChoiceBox.setValue(KLONDIKE);

        Button jugarButton = new Button("Jugar");

        jugarButton.setOnAction(e -> {
            try {
                abrirVentana(stage, modosChoiceBox.getValue(), false);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        Button cancelButton = new Button("Cancelar");
        cancelButton.setOnAction(e -> stage.close());

        HBox buttonsLayout = new HBox(10);
        buttonsLayout.getChildren().addAll(jugarButton, cancelButton);
        buttonsLayout.setAlignment(Pos.CENTER);

        BorderPane layout = new BorderPane();
        layout.setPadding(new Insets(10));
        layout.setTop(titleLabel);
        BorderPane.setAlignment(titleLabel, Pos.CENTER);
        layout.setCenter(modosChoiceBox);
        BorderPane.setAlignment(modosChoiceBox, Pos.CENTER);
        layout.setBottom(buttonsLayout);
        BorderPane.setAlignment(buttonsLayout, Pos.CENTER);

        Scene scene = new Scene(layout, 250, 200);
        stage.setScene(scene);
        stage.show();
    }

    public static void abrirVentana(Stage stage, String modoSeleccionado, boolean juegoRecuperado) throws IOException {
        if (SPIDER.equals(modoSeleccionado)) {
            opcionElegida = SPIDER;
            SpiderUI spiderUI = new SpiderUI(spider, juegoRecuperado);
            spiderUI.iniciar(stage);
        } else if (KLONDIKE.equals(modoSeleccionado)) {
            opcionElegida = KLONDIKE;
            KlondikeUI klondikeUI = new KlondikeUI(klondike, juegoRecuperado);
            klondikeUI.iniciar(stage);
        }
    }

    public static void guardarPartidaActual() {
        if (opcionElegida.equals(SPIDER)) {
            spider.guardarPartida();
        } else if (opcionElegida.equals(KLONDIKE)) {
            klondike.guardarPartida();
        }
    }

    public static boolean recuperarPartida(Stage stage) throws IOException {
        var recuperarPartidaSpider = spider.recuperarPartida();
        var recuperarPartidaKlondike = klondike.recuperarPartida();

        if (recuperarPartidaSpider) {
            if (spider.juegoGanado()) {
                cargarModos();
                return false;
            } else {
                SeleccionModo.abrirVentana(stage, SPIDER, true);
            }
            return true;
        }

        if (recuperarPartidaKlondike) {
            if (klondike.juegoGanado()) {
                cargarModos();
                return false;
            } else {
                SeleccionModo.abrirVentana(stage, KLONDIKE, true);
            }
            return true;
        }

        return false;
    }
}