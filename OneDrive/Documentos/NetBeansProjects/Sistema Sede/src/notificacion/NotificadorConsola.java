/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package notificacion;

/**
 *
 * @author trotu
 */
public class NotificadorConsola implements Notificador {
    @Override
    public void enviar(String mensaje) {
        System.out.println("--- Notificación (Consola) ---");
        System.out.println(mensaje);
        System.out.println("-----------------------------");
    }
}