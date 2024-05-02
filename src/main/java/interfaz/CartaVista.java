package interfaz;

import elemento.Carta;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;

public class CartaVista extends ImageView {
    private static final String DIAMANTE = "Diamond ";
    private static final String CORAZON = "Hearts ";
    private static final String TREBOL = "Clubs ";
    private static final String PICA = "Spades ";
    private static final String BACK_RED = "Back Red 1";
    private final Carta carta;
    private final ColorAdjust colorAdjust;

    public CartaVista(Carta carta) {
        super();
        this.carta = carta;
        this.setImage(crearCartaImagen(carta));
        this.colorAdjust = new ColorAdjust();
        this.setEffect(colorAdjust);
    }

    public Carta getCarta() {
        return carta;
    }

    public void seleccionarCarta() {
        colorAdjust.setBrightness(-0.5);
    }

    public void deseleccionarCarta() {
        colorAdjust.setBrightness(0.0);
    }

    public boolean estaSeleccionada() {
        return colorAdjust.getBrightness() >= 0;
    }

    public boolean sePuedeSeleccionar() {
        return estaSeleccionada() && carta.esVisible();
    }

    private String obtenerRutaCarta(Carta carta) {
        if (!carta.esVisible()) {
            return "/images/cards/" + BACK_RED + ".png";
        }

        return switch (carta.getPalo().name()) {
            case "DIAMANTE" -> "/images/cards/" + DIAMANTE + carta.getNumero() + ".png";
            case "CORAZON" -> "/images/cards/" + CORAZON + carta.getNumero() + ".png";
            case "TREBOL" -> "/images/cards/" + TREBOL + carta.getNumero() + ".png";
            case "PICA" -> "/images/cards/" + PICA + carta.getNumero() + ".png";
            default -> "/images/cards/" + BACK_RED + ".png";
        };
    }

    private Image crearCartaImagen(Carta carta){
        var ruta = obtenerRutaCarta(carta);
        return new Image(Objects.requireNonNull(getClass().getResourceAsStream(ruta)));
    }
}
