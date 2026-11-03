package modelo.servicios;

public class Vacunacion implements Facturable {
    private double costoVacuna;

    public Vacunacion(double costoVacuna) {
        setCostoVacuna(costoVacuna);
    }

    public void setCostoVacuna(double costoVacuna) {
        if (costoVacuna < 0) {
            throw new IllegalArgumentException("El costo de la vacuna no puede ser negativo.");
        }
        this.costoVacuna = costoVacuna;
    }

    @Override
    public double calcularCosto() {
        return costoVacuna;
    }
}