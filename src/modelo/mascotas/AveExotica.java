package modelo.mascotas;

import modelo.personas.Duenio;

public class AveExotica extends Mascota {
    private double longitudAlas;

    public AveExotica(String nombre, int edad, double peso, Duenio duenio, double longitudAlas) {
        super(nombre, edad, peso, duenio);
        this.longitudAlas = longitudAlas;
    }

    @Override
    public void hacerSonido() {
        System.out.println(getNombre() + " (Ave Exótica) dice: ¡Pío, pío!");
    }

    public double getLongitudAlas() { return longitudAlas; }
    public void setLongitudAlas(double longitudAlas) { this.longitudAlas = longitudAlas; }
}