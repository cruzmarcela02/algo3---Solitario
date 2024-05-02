package interfaz;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Menu;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuUI {
    private static final String KLONDIKE = "KLONDIKE";
    private static final String SPIDER = "SPIDER";
    private static final int KLONDIKE_POSICION = 0;
    private static final int SPIDER_POSICION = 1;

    public static void seteoJuegoNuevoItem(Stage stage, Menu menu) {
        var itemKlondike = menu.getItems().get(KLONDIKE_POSICION);
        var itemSpider = menu.getItems().get(SPIDER_POSICION);

        itemKlondike.setOnAction(crearEventoMenuItem(stage, KLONDIKE));
        itemSpider.setOnAction(crearEventoMenuItem(stage, SPIDER));
    }

    private static EventHandler<ActionEvent> crearEventoMenuItem(Stage stage, String modoSeleccionado) {
        return event -> {
            try {
                SeleccionModo.cargarModos();
                SeleccionModo.abrirVentana(stage, modoSeleccionado, false);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        };
    }
}
