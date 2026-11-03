package modelo.mascotas;

import modelo.personas.Duenio;

public abstract class Mascota {
    private String nombre;
    private int edad;
    private double peso;
    private Duenio duenio;
    private HistorialClinico historialClinico;

    public Mascota(String nombre, int edad, double peso, Duenio duenio) {
        setNombre(nombre);
        setEdad(edad);
        setPeso(peso);
        this.duenio = duenio;
        this.historialClinico = new HistorialClinico(); // Composición estricta
    }

    public abstract void hacerSonido();

    public void setEdad(int edad) {
        if (edad <= 0) {
            throw new IllegalArgumentException("La edad no puede ser cero ni negativa.");
        }
        this.edad = edad;
    }

    public void setPeso(double peso) {
        if (peso <= 0) {
            throw new IllegalArgumentException("El peso no puede ser cero ni negativo.");
        }
        this.peso = peso;
    }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public int getEdad() { return edad; }
    public double getPeso() { return peso; }
    public Duenio getDuenio() { return duenio; }
    public HistorialClinico getHistorialClinico() { return historialClinico; }

    @Override
    public String toString() {
        return "Mascota: " + nombre + " (Edad: " + edad + " años, Peso: " + peso + " kg)";
    }
}