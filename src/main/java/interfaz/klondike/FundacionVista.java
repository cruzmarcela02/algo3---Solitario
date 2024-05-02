package interfaz.klondike;

import interfaz.CartaVista;
import javafx.scene.layout.StackPane;

import java.util.List;

public class FundacionVista {
    private final StackPane fundacionStack;
    private final int numeroFundacion;

    public FundacionVista(StackPane fundacionStack, List<CartaVista> cartasVista, int numeroFundacion) {
        this.fundacionStack = fundacionStack;
        this.numeroFundacion = numeroFundacion;
        agregarCartasVista(cartasVista);
    }

    private void agregarCartasVista(List<CartaVista> cartasVista) {
        for (CartaVista cartaVista: cartasVista) {
            fundacionStack.getChildren().add(cartaVista);
        }
    }

    public int getNroFundacion() {
        return numeroFundacion;
    }

    public void agregarCartaVista(CartaVista carta) {
        fundacionStack.getChildren().add(carta);
    }
}
