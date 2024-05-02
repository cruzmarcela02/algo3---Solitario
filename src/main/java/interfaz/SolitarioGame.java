package interfaz;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.IOException;

public class SolitarioGame extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        stage.setResizable(false);
        stage.getIcons().add(new Image("/images/icon/iconCarta.png"));

        SeleccionModo.cargarModos();

        if (SeleccionModo.recuperarPartida(stage)) {
            return;
        }

        SeleccionModo.mostrarVentana(stage);
    }

    @Override
    public void stop() {
        SeleccionModo.guardarPartidaActual();
    }

    public static void main(String[] args) {
        launch();
    }
}
