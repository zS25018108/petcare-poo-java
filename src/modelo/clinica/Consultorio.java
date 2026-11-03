package modelo.clinica;

public class Consultorio {
    private int numero;
    private boolean disponible;

    public Consultorio(int numero) {
        this.numero = numero;
        this.disponible = true;
    }

    public int getNumero() { return numero; }
    public boolean isDisponible() { return disponible; }
    public void setDisponible(boolean disponible) { this.disponible = disponible; }
}