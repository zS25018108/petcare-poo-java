package modelo.clinica;

import modelo.personas.Veterinario;
import java.util.ArrayList;
import java.util.List;

public class Clinica {
    private String nombre;
    private List<Veterinario> veterinarios;
    private List<Consultorio> consultorios;
    private List<Cita> citas;

    public Clinica(String nombre) {
        this.nombre = nombre;
        this.veterinarios = new ArrayList<>();
        this.consultorios = new ArrayList<>();
        this.citas = new ArrayList<>();
        
        // Composición de consultorios (pertenecen a la clínica)
        consultorios.add(new Consultorio(1));
        consultorios.add(new Consultorio(2));
    }

    public void agregarVeterinario(Veterinario v) {
        veterinarios.add(v);
    }

    public void agregarCita(Cita c) {
        citas.add(c);
    }

    public List<Veterinario> getVeterinarios() { return veterinarios; }
    public List<Consultorio> getConsultorios() { return consultorios; }
    public List<Cita> getCitas() { return citas; }
}