package sistema;
import model.Evento;
import model.Persona;
import model.RegistroAsistencia;
import notificacion.NotificacionEvento;
import notificacion.Notificador;
import notificacion.NotificadorConsola;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SistemaAsistencia {
    private static SistemaAsistencia instanciaUnica;

    private List<Persona> personasRegistradas;
    private List<Evento> eventosProgramados;
    private List<RegistroAsistencia> registrosAsistencia;
    private Notificador notificadorPorDefecto;

    private SistemaAsistencia() {
        personasRegistradas = new ArrayList<>();
        eventosProgramados = new ArrayList<>();
        registrosAsistencia = new ArrayList<>();
        this.notificadorPorDefecto = new NotificadorConsola();
        cargarDatosIniciales();
    }

    public static SistemaAsistencia getInstancia() {
        if (instanciaUnica == null) {
            instanciaUnica = new SistemaAsistencia();
        }
        return instanciaUnica;
    }

    public void registrarPersona(Persona persona) {
        boolean rutYaExiste = personasRegistradas.stream()
                                .anyMatch(p -> p.getRut().equals(persona.getRut()));
        if (rutYaExiste) {
            System.out.println("Error: Ya hay una persona con el RUT " + persona.getRut());
            return;
        }
        personasRegistradas.add(persona);
        System.out.println("Persona registrada con exito: " + persona.getNombre() + " " + persona.getApellido());
    }

    public Optional<Persona> buscarPersonaPorRut(String rut) {
        return personasRegistradas.stream()
                                  .filter(p -> p.getRut().equals(rut))
                                  .findFirst();
    }

    public List<Persona> getPersonasRegistradas() {
        return new ArrayList<>(personasRegistradas);
    }

    public void agregarEvento(Evento evento) {
        eventosProgramados.add(evento);
        System.out.println("Evento agregado con exito: " + evento.getNombre());

        NotificacionEvento notificacion = new NotificacionEvento(notificadorPorDefecto, evento, "NUEVO EVENTO", "Un nuevo evento ha sido programado.");
        notificacion.enviarMensaje();
    }

    public Optional<Evento> buscarEventoPorId(String idEvento) {
        return eventosProgramados.stream()
                                 .filter(e -> e.getId().equals(idEvento))
                                 .findFirst();
    }

    public List<Evento> getEventosProgramados() {
        return new ArrayList<>(eventosProgramados);
    }

    public void registrarEntrada(String rutPersona) {
        Optional<Persona> personaOpt = buscarPersonaPorRut(rutPersona);
        if (personaOpt.isPresent()) {
            Persona persona = personaOpt.get();
            boolean tieneEntradaPendiente = registrosAsistencia.stream()
                                                .filter(r -> r.getPersona().equals(persona) && r.getFechaHoraSalida() == null)
                                                .anyMatch(r -> true);
            if (tieneEntradaPendiente) {
                System.out.println("La persona " + persona.getNombre() + " ya tiene una entrada registrada sin salida.");
                return;
            }
            RegistroAsistencia registro = new RegistroAsistencia(persona);
            registrosAsistencia.add(registro);
            System.out.println("Entrada registrada para: " + persona.getNombre() + " " + persona.getApellido());
        } else {
            System.out.println("Error: Persona con RUT " + rutPersona + " no encontrada.");
        }
    }

    public void registrarEntradaAEvento(String rutPersona, String idEvento) {
        Optional<Persona> personaOpt = buscarPersonaPorRut(rutPersona);
        Optional<Evento> eventoOpt = buscarEventoPorId(idEvento);

        if (personaOpt.isPresent() && eventoOpt.isPresent()) {
            Persona persona = personaOpt.get();
            Evento evento = eventoOpt.get();

            boolean yaRegistradoEnEvento = registrosAsistencia.stream()
                                                .filter(r -> r.getPersona().equals(persona) && r.getEvento() != null &&
                                                             r.getEvento().getId().equals(evento.getId()) && r.getFechaHoraSalida() == null)
                                                .anyMatch(r -> true);
            if (yaRegistradoEnEvento) {
                System.out.println("La persona " + persona.getNombre() + " ya está registrada en el evento " + evento.getNombre() + " sin salida.");
                return;
            }

            RegistroAsistencia registro = new RegistroAsistencia(persona, evento);
            registrosAsistencia.add(registro);
            System.out.println("Entrada registrada para " + persona.getNombre() + " en el evento: " + evento.getNombre());
        } else {
            System.out.println("Error: Persona o Evento no encontrados.");
        }
    }

    public void registrarSalida(String rutPersona) {
        Optional<Persona> personaOpt = buscarPersonaPorRut(rutPersona);
        if (personaOpt.isPresent()) {
            Persona persona = personaOpt.get();
            Optional<RegistroAsistencia> registroActivoOpt = registrosAsistencia.stream()
                                                                                .filter(r -> r.getPersona().getId().equals(persona.getId()) && r.getFechaHoraSalida() == null)
                                                                                .findFirst();
            if (registroActivoOpt.isPresent()) {
                registroActivoOpt.get().registrarSalida();
                System.out.println("Salida registrada para: " + persona.getNombre() + " " + persona.getApellido());
            } else {
                System.out.println("Error: No se encontró un registro de entrada pendiente para " + persona.getNombre() + " " + persona.getApellido());
            }
        } else {
            System.out.println("Error: Persona con RUT " + rutPersona + " no encontrada.");
        }
    }

    public List<RegistroAsistencia> getRegistrosAsistencia() {
        return new ArrayList<>(registrosAsistencia);
    }

    public List<RegistroAsistencia> getRegistrosPorPersona(String rutPersona) {
        Optional<Persona> personaOpt = buscarPersonaPorRut(rutPersona);
        if (personaOpt.isPresent()) {
            return registrosAsistencia.stream()
                    .filter(r -> r.getPersona().getId().equals(personaOpt.get().getId()))
                    .collect(java.util.stream.Collectors.toList());
        }
        return new ArrayList<>();
    }

    public List<RegistroAsistencia> getRegistrosPorEvento(String idEvento) {
        Optional<Evento> eventoOpt = buscarEventoPorId(idEvento);
        if (eventoOpt.isPresent()) {
            return registrosAsistencia.stream()
                    .filter(r -> r.getEvento() != null && r.getEvento().getId().equals(eventoOpt.get().getId()))
                    .collect(java.util.stream.Collectors.toList());
        }
        return new ArrayList<>();
    }

    private void cargarDatosIniciales() {
        Persona p1 = new Persona("Ana", "García", "11.111.111-1");
        Persona p2 = new Persona("Luis", "Martínez", "22.222.222-2");
        Persona p3 = new Persona("María", "López", "33.333.333-3");
        registrarPersona(p1);
        registrarPersona(p2);
        registrarPersona(p3);

        Evento e1 = new Evento("Reunión Semanal", "Reunión de planificación de equipo",
                                LocalDateTime.of(2025, 6, 23, 9, 0), LocalDateTime.of(2025, 6, 23, 10, 0));
        Evento e2 = new Evento("Taller de Java", "Capacitación sobre patrones de diseño",
                                LocalDateTime.of(2025, 7, 1, 14, 0), LocalDateTime.of(2025, 7, 1, 17, 0));
        agregarEvento(e1);
        agregarEvento(e2);

        System.out.println("Datos de ejemplo cargados.");
    }
}