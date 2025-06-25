/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package notificacion;

/**
 *
 * @author trotu
 */
// Esta es la abstraccion del Bridge
public abstract class Notificacion {
    protected Notificador notificador;

    public Notificacion(Notificador notificador) {
        this.notificador = notificador;
    }

    public abstract void enviarMensaje();
}