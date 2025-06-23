package model;

import java.util.UUID;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author trotu
 */
public class Persona implements Clonable<Persona> {

    private String id;
    private String nombre;
    private String apellido;
    private String rut;

    public Persona(String nombre, String apellido, String rut) {
        this.id = UUID.randomUUID().toString();
        this.nombre = nombre;
        this.apellido = apellido;
        this.rut = rut;
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getRut() {
        return rut;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Nombre: " + nombre + " " + apellido + ", RUT: " + rut;
    }

    @Override
    public Persona clonar() {
        try {
            return (Persona) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException("Error al clonar Persona", e);
        }
    }
}
