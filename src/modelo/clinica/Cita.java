package modelo.clinica;

import modelo.mascotas.Mascota;
import modelo.personas.Veterinario;
import modelo.servicios.Facturable;

public class Cita {
    private String fechaHora;
    private String estado; // PROGRAMADA, ATENDIDA, CANCELADA
    private Mascota mascota;
    private Veterinario veterinario;
    private Consultorio consultorio;
    private Facturable servicio;

    public Cita(String fechaHora, Mascota mascota, Veterinario veterinario, Consultorio consultorio, Facturable servicio) {
        this.fechaHora = fechaHora;
        this.estado = "PROGRAMADA";
        this.mascota = mascota;
        this.veterinario = veterinario;
        this.consultorio = consultorio;
        this.servicio = servicio;
    }

    public void setEstado(String nuevoEstado) {
        if (this.estado.equals("CANCELADA") && nuevoEstado.equals("ATENDIDA")) {
            throw new IllegalArgumentException("Una cita cancelada no puede marcarse como atendida.");
        }
        this.estado = nuevoEstado;
    }

    public String getFechaHora() { return fechaHora; }
    public String getEstado() { return estado; }
    public Mascota getMascota() { return mascota; }
    public Veterinario getVeterinario() { return veterinario; }
    public Consultorio getConsultorio() { return consultorio; }
    public Facturable getServicio() { return servicio; }
}