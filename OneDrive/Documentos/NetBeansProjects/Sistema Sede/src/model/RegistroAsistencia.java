package model;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author trotu
 */
import java.time.LocalDateTime;
import java.util.UUID;

public class RegistroAsistencia {

    private String id;
    private Persona persona;
    private Evento evento;
    private LocalDateTime fechaHoraEntrada;
    private LocalDateTime fechaHoraSalida;

    public RegistroAsistencia(Persona persona) {
        this.id = UUID.randomUUID().toString();
        this.persona = persona;
        this.fechaHoraEntrada = LocalDateTime.now();
        this.evento = null;
    }

    public RegistroAsistencia(Persona persona, Evento evento) {
        this.id = UUID.randomUUID().toString();
        this.persona = persona;
        this.evento = evento;
        this.fechaHoraEntrada = LocalDateTime.now();
    }

    public void registrarSalida() {
        this.fechaHoraSalida = LocalDateTime.now();
    }

    public String getId() {
        return id;
    }

    public Persona getPersona() {
        return persona;
    }

    public Evento getEvento() {
        return evento;
    }

    public LocalDateTime getFechaHoraEntrada() {
        return fechaHoraEntrada;
    }

    public LocalDateTime getFechaHoraSalida() {
        return fechaHoraSalida;
    }

    @Override
    public String toString() {
        String infoEvento = (evento != null) ? " (Evento: " + evento.getNombre() + ")" : "";
        return "ID Registro: " + id + ", Persona: " + persona.getNombre() + " " + persona.getApellido() + infoEvento
                + "\n  Entrada: " + fechaHoraEntrada
                + (fechaHoraSalida != null ? ", Salida: " + fechaHoraSalida : ", Salida: Pendiente");
    }
}
