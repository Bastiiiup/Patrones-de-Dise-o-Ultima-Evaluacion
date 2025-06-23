package model;

import java.time.LocalDateTime;
import java.util.UUID;

public class Evento implements Clonable<Evento> {

    private String id;
    private String nombre;
    private String descripcion;
    private LocalDateTime fechaHoraInicio;
    private LocalDateTime fechaHoraFin;

    public Evento(String nombre, String descripcion, LocalDateTime fechaHoraInicio, LocalDateTime fechaHoraFin) {
        this.id = UUID.randomUUID().toString();
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fechaHoraInicio = fechaHoraInicio;
        this.fechaHoraFin = fechaHoraFin;
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public LocalDateTime getFechaHoraInicio() {
        return fechaHoraInicio;
    }

    public LocalDateTime getFechaHoraFin() {
        return fechaHoraFin;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Nombre: " + nombre + ", Descripción: " + descripcion
                + "\n  Inicio: " + fechaHoraInicio + ", Fin: " + fechaHoraFin;
    }

    @Override
    public Evento clonar() {
        try {
            return (Evento) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException("Error al clonar Evento", e);
        }
    }
}
