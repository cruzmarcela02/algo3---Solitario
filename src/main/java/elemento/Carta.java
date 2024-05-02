package elemento;

import java.io.Serializable;
import java.util.Objects;

public class Carta implements Serializable {
    private final int numero;
    private final Color color;
    private final Palo palo;
    private boolean visible;

    public Carta(int numero, Palo palo) {
        this.numero = numero;
        this.color = palo.getColor();
        this.palo = palo;
        this.visible = false;
    }

    public Carta(int numero, Palo palo, boolean visible) {
        this.numero = numero;
        this.color = palo.getColor();
        this.palo = palo;
        this.visible = visible;
    }

    public void darVuelta() {
        visible = !visible;
    }

    public boolean esVisible() {
        return visible;
    }

    public Color getColor() {
        return color;
    }

    public int getNumero() {
        return numero;
    }

    public Palo getPalo() {
        return palo;
    }

    public boolean esLaSiguiente(Carta otraCarta) {
        return (palo.equals(otraCarta.getPalo())) && (numero == otraCarta.getNumero() + 1);
    }

    public boolean esUnNumeroMenorYColorDistinto(Carta otraCarta) {
        return numero == otraCarta.getNumero() - 1 && color != otraCarta.getColor();
    }

    public boolean esUnNumeroMayor(Carta otraCarta){
        return numero == otraCarta.getNumero() + 1;
    }

    public boolean esUnRey() {
        return numero == 13;
    }

    public boolean esUnAs() {
        return numero == 1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Carta carta = (Carta) o;
        return numero == carta.numero && visible == carta.visible && color == carta.color && palo == carta.palo;
    }

    @Override
    public int hashCode() {
        return Objects.hash(numero, color, palo, visible);
    }

    @Override
    public String toString(){
        return palo + " " + numero + " " + color + " " + visible;
    }
}
