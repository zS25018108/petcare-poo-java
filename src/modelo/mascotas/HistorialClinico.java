package modelo.mascotas;

import java.util.ArrayList;
import java.util.List;

public class HistorialClinico {
    private List<String> consultas;

    public HistorialClinico() {
        this.consultas = new ArrayList<>();
    }

    public void agregarEntrada(String descripcion) {
        consultas.add(descripcion);
    }

    public List<String> getConsultas() {
        return consultas;
    }
}