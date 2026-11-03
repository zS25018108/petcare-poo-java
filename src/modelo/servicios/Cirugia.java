package modelo.servicios;

public class Cirugia implements Facturable {
    private String complejidad;
    private double horasQuirofano;
    private static final double COSTO_BASE_HORA = 1200.0;

    public Cirugia(String complejidad, double horasQuirofano) {
        this.complejidad = complejidad;
        setHorasQuirofano(horasQuirofano);
    }

    public void setHorasQuirofano(double horasQuirofano) {
        if (horasQuirofano <= 0) {
            throw new IllegalArgumentException("Las horas de quirófano deben ser mayores a cero.");
        }
        this.horasQuirofano = horasQuirofano;
    }

    @Override
    public double calcularCosto() {
        double multiplicador = complejidad.equalsIgnoreCase("alta") ? 1.5 : 1.0;
        return (horasQuirofano * COSTO_BASE_HORA) * multiplicador;
    }
}