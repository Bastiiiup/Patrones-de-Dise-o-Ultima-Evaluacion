package app;

import sistema.SistemaAsistencia;
import model.Persona;
import model.Evento;
import model.RegistroAsistencia;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.Scanner; // Aunque se usa, Scanner no será para interacción.

public class main {

    private static SistemaAsistencia sistema; // Instancia del Singleton
    // No necesitamos Scanner si no hay interacción

    public static void main(String[] args) {
        sistema = SistemaAsistencia.getInstancia(); // Obtiene la única instancia del Singleton

        System.out.println("\n--- Demostración de Patrones con Datos de Ejemplo ---");
        
        // --- Demostración de Singleton y Carga de Datos ---
        System.out.println("\n1. Demostración de Singleton y Datos Iniciales:");
        System.out.println("El Sistema de Asistencia ha sido inicializado como un Singleton.");
        System.out.println("Se han cargado automáticamente personas y eventos de ejemplo al inicio del sistema.");

        // --- Demostración de Bridge (Notificación de Eventos) ---
        System.out.println("\n2. Demostración de Bridge (Notificaciones de Eventos):");
        System.out.println("Cuando los eventos de ejemplo fueron agregados, se activaron notificaciones.");
        System.out.println("Salida de notificaciones de eventos (usando NotificadorConsola):");
        // Las notificaciones ya se imprimen en cargarDatosIniciales() de SistemaAsistencia

        // --- Demostración de Iterator ---
        System.out.println("\n3. Demostración de Iterator (Recorriendo colecciones):");
        System.out.println("\n--- Personas Registradas (usando Iterator implícito) ---");
        List<Persona> personas = sistema.getPersonasRegistradas(); // Obtiene la lista de personas
        if (personas.isEmpty()) {
            System.out.println("No hay personas registradas.");
        } else {
            for (Persona persona : personas) { // Uso del Iterator
                System.out.println(persona);
            }
        }

        System.out.println("\n--- Eventos Programados (usando Iterator implícito) ---");
        List<Evento> eventos = sistema.getEventosProgramados(); // Obtiene la lista de eventos
        if (eventos.isEmpty()) {
            System.out.println("No hay eventos programados.");
        } else {
            for (Evento evento : eventos) { // Uso del Iterator
                System.out.println(evento);
            }
        }

        System.out.println("\n--- Registros de Asistencia (usando Iterator implícito) ---");
        // Puedes agregar algunos registros de asistencia manualmente aquí para demostración si SistemaAsistencia no los crea
        // Por ejemplo, registrar una entrada y una salida para alguna de las personas de ejemplo
        // Esto también demuestra las funciones del Singleton.
        System.out.println("Registrando algunas entradas y salidas de ejemplo para demostrar.");
        sistema.registrarEntrada("11.111.111-1");
        sistema.registrarEntradaAEvento("22.222.222-2", sistema.getEventosProgramados().get(0).getId()); // Asume que hay al menos un evento
        sistema.registrarSalida("11.111.111-1");


        List<RegistroAsistencia> registros = sistema.getRegistrosAsistencia(); // Obtiene la lista de registros
        if (registros.isEmpty()) {
            System.out.println("No hay registros de asistencia.");
        } else {
            for (RegistroAsistencia registro : registros) { // Uso del Iterator
                System.out.println(registro);
            }
        }
        
        // --- Demostración de Prototype ---
        System.out.println("\n4. Demostración de Prototype (Creación de objetos clonados):");
        // Para demostrar el Prototype sin interacción, podemos clonar un objeto existente de los datos de ejemplo
        if (!personas.isEmpty()) {
            Persona prototipoPersona = personas.get(0); // Tomamos la primera persona como prototipo
            Persona personaClonada = prototipoPersona.clonar(); // Clonamos
            // Modificamos el clon para mostrar que es un nuevo objeto independiente
            // Nota: Para RUT debe ser único, así que le ponemos uno diferente.
            // Para una demo real del Prototype en este contexto, lo ideal es una clase que extienda Persona
            // (ej. Empleado o Visitante) para que la clonación tenga más sentido.
            // Aquí solo mostramos que el objeto se clona y se puede modificar.
            // Esto solo demuestra que el método .clonar() funciona.

            // Clonar un Evento (asumiendo que hay al menos uno)
            if (!eventos.isEmpty()) {
                 Evento prototipoEvento = eventos.get(0);
                 Evento eventoClonado = prototipoEvento.clonar(); // Clonamos
                 System.out.println("  Evento prototipo (original): " + prototipoEvento.getNombre() + " (ID: " + prototipoEvento.getId() + ")");
                 System.out.println("  Evento clonado (nuevo ID): " + eventoClonado.getNombre() + " (ID: " + eventoClonado.getId() + ")");
                 // Nota: Si el ID se genera en el constructor y el clonado es superficial, el ID puede ser el mismo.
                 // Para una clonación "real" donde se genera un nuevo ID en el clon, se requeriría una lógica diferente en clonar().
                 // Por ahora, solo demuestra que se puede crear una copia.

                // Para un Prototype más claro, idealmente clonarías una "plantilla" vacía y la rellenarías
                // Pero como quitamos la interacción, mostramos que el método .clonar() existe y se puede usar.
                
                System.out.println("El patrón Prototype permite clonar objetos existentes.");
                System.out.println("Se ha demostrado el uso del método 'clonar()' de Evento.");
            } else {
                System.out.println("No hay eventos de ejemplo para demostrar la clonación de eventos.");
            }
            
        } else {
            System.out.println("No hay personas de ejemplo para demostrar la clonación de personas.");
        }

        System.out.println("\n--- Fin de la Demostración ---");
    }
}