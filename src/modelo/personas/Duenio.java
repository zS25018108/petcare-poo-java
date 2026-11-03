package modelo.personas;

public class Duenio extends Persona {
    private String direccion;

    public Duenio(String nombre, String apellido, String telefono, String correo, String direccion) {
        super(nombre, apellido, telefono, correo);
        this.direccion = direccion;
    }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }

    @Override
    public String toString() {
        return "Dueño: " + super.toString() + ", Dirección: " + direccion;
    }
}