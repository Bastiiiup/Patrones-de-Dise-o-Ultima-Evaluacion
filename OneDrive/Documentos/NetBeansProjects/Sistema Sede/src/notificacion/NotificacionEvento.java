/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package notificacion;

import model.Evento;
import java.time.format.DateTimeFormatter;

public class NotificacionEvento extends Notificacion {

    private Evento evento;
    private String tipoNotificacion;
    private String mensajeAdicional;

    public NotificacionEvento(Notificador notificador, Evento evento, String tipoNotificacion, String mensajeAdicional) {
        super(notificador);
        this.evento = evento;
        this.tipoNotificacion = tipoNotificacion;
        this.mensajeAdicional = mensajeAdicional;
    }

    @Override
    public void enviarMensaje() {
        String mensaje = String.format(
                "[%s Evento] %s (ID: %s):\n  '%s'\n  Inicio: %s\n  %s",
                tipoNotificacion.toUpperCase(),
                evento.getNombre(),
                evento.getId(),
                evento.getDescripcion(),
                evento.getFechaHoraInicio().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")),
                mensajeAdicional
        );
        notificador.enviar(mensaje);
    }
}
