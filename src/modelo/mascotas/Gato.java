package modelo.mascotas;

import modelo.personas.Duenio;

public class Gato extends Mascota {
    private boolean esDomestico;

    public Gato(String nombre, int edad, double peso, Duenio duenio, boolean esDomestico) {
        super(nombre, edad, peso, duenio);
        this.esDomestico = esDomestico;
    }

    @Override
    public void hacerSonido() {
        System.out.println(getNombre() + " (Gato) dice: ¡Miau, miau!");
    }

    public boolean isEsDomestico() { return esDomestico; }
    public void setEsDomestico(boolean esDomestico) { this.esDomestico = esDomestico; }
}