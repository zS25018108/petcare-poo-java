package modelo.personas;

public class Recepcionista extends Persona {
    private String turno;

    public Recepcionista(String nombre, String apellido, String telefono, String correo, String turno) {
        super(nombre, apellido, telefono, correo);
        this.turno = turno;
    }

    public String getTurno() { return turno; }
    public void setTurno(String turno) { this.turno = turno; }

    @Override
    public String toString() {
        return "Recepcionista: " + super.toString() + " [Turno: " + turno + "]";
    }
}