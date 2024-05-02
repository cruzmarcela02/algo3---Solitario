package interfaz;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class MensajeGanadorUI {

    public static void mostrarAlertJuegoGanado(Stage stage, String modoSeleccionado) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(null);
        alert.setTitle("¡Felicitaciones!");
        alert.setContentText("Usted ha ganado el juego. ¿Desea iniciar una nueva partida?");
        Optional<ButtonType> action = alert.showAndWait();

        if (action.isPresent() && action.get() == ButtonType.OK) {
            try {
                SeleccionModo.cargarModos();
                SeleccionModo.abrirVentana(stage, modoSeleccionado, false);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            stage.close();
        }
    }
}
