package modelo.servicios;

public class ConsultaGeneral implements Facturable {
    private double tarifaBase;

    public ConsultaGeneral(double tarifaBase) {
        setTarifaBase(tarifaBase);
    }

    public void setTarifaBase(double tarifaBase) {
        if (tarifaBase < 0) {
            throw new IllegalArgumentException("La tarifa base no puede ser negativa.");
        }
        this.tarifaBase = tarifaBase;
    }

    @Override
    public double calcularCosto() {
        return tarifaBase;
    }
}