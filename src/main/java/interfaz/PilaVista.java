package interfaz;

import elemento.Carta;
import elemento.Pila;
import javafx.scene.layout.VBox;

import java.util.List;

public class PilaVista {
    private final VBox pilaVbox;
    private final int nroPila;

    public PilaVista(VBox pilaVbox, List<CartaVista> cartasVista, int nroPila) {
        this.pilaVbox = pilaVbox;
        this.nroPila = nroPila;
        agregarCartasVista(cartasVista);
    }

    private void agregarCartasVista(List<CartaVista> cartasVista) {
        for (CartaVista cartaVista: cartasVista) {
            this.pilaVbox.getChildren().add(cartaVista);
        }
    }

    public VBox getPilaVbox() {
        return this.pilaVbox;
    }

    public int getNroPila() {
        return this.nroPila;
    }

    public int obtenerTamano() {
        return pilaVbox.getChildren().size();
    }

    public void seleccionarEscalera(CartaVista primeraCarta, List<CartaVista> escalera) {
        int posPrimeraCarta = pilaVbox.getChildren().indexOf(primeraCarta);
        for (int i = posPrimeraCarta + 1; i < obtenerTamano(); i++) {
            var carta = (CartaVista) pilaVbox.getChildren().get(i);
            carta.seleccionarCarta();
            escalera.add(carta);
        }
    }

    public void agregarCarta(CartaVista carta) {
        pilaVbox.getChildren().add(carta);
    }

    public void removerUltimaCarta() {
        pilaVbox.getChildren().removeLast();
    }

    public CartaVista actualizarVistaPila(Pila pilaOrigen) {
        if (obtenerTamano() != 0) {
            removerUltimaCarta();
            Carta ultimaCarta = pilaOrigen.verCartas(1).getFirst();
            CartaVista actualizada = new CartaVista(ultimaCarta);
            agregarCarta(actualizada);
            return actualizada;
        }

        return null;
    }
}
