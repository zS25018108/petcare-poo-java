package modelo.mascotas;

import modelo.personas.Duenio;

public class Perro extends Mascota {
    private String raza;
    private String nivelEnergia;

    public Perro(String nombre, int edad, double peso, Duenio duenio, String raza, String nivelEnergia) {
        super(nombre, edad, peso, duenio);
        this.raza = raza;
        this.nivelEnergia = nivelEnergia;
    }

    @Override
    public void hacerSonido() {
        System.out.println(getNombre() + " (Perro) dice: ¡Guau, guau!");
    }

    public String getRaza() { return raza; }
    public String getNivelEnergia() { return nivelEnergia; }
}