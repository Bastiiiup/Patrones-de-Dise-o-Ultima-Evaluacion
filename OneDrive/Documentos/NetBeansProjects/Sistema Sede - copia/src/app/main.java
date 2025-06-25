package app;

import sistema.SistemaAsistencia;
import model.Persona;
import model.Evento;
import model.RegistroAsistencia;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;
import java.util.Iterator;

public class main {

    private static SistemaAsistencia sistema;
    private static Scanner scanner;

    public static void main(String[] args) {
        sistema = SistemaAsistencia.getInstancia();
        scanner = new Scanner(System.in);

        mostrarMenuPrincipal();

        System.out.println("Hasta luego!");
    }

    private static void mostrarMenuPrincipal() {
        int opcion;
        do {
            System.out.println("\n--- MENU PRINCIPAL DEL SISTEMA DE ASISTENCIA ---");
            System.out.println("1. Gestion de Personas");
            System.out.println("2. Gestion de Eventos");
            System.out.println("3. Registro de Asistencia");
            System.out.println("4. Ver Reportes");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opcion: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    menuGestionPersonas();
                    break;
                case 2:
                    menuGestionEventos();
                    break;
                case 3:
                    menuRegistroAsistencia();
                    break;
                case 4:
                    menuVerReportes();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opcion no valida. Intente de nuevo.");
            }
        } while (opcion != 0);
    }

    private static void menuGestionPersonas() {
        int opcion;
        do {
            System.out.println("\n--- GESTION DE PERSONAS ---");
            System.out.println("1. Crear Persona con Prototype (Plantilla)");
            System.out.println("2. Ver todas las Personas Registradas");
            System.out.println("0. Volver al Menu Principal");
            System.out.print("Seleccione una opcion: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    crearPersonaConPrototype();
                    break;
                case 2:
                    verPersonasRegistradas();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opcion no valida. Intente de nuevo.");
            }
        } while (opcion != 0);
    }

    private static void crearPersonaConPrototype() {
        System.out.println("\n--- CREAR PERSONA CON PROTOTYPE ---");
        System.out.println("Seleccione un tipo de plantilla para la nueva persona:");
        System.out.println("1. Plantilla de Empleado");
        System.out.println("2. Plantilla de Visitante");
        System.out.print("Seleccione una opcion: ");
        int opcion = scanner.nextInt();
        scanner.nextLine();

        Persona prototipoBase = null;
        String tipoPrototipo = "";

        if (opcion == 1) {
            prototipoBase = new Persona("", "", "");
            tipoPrototipo = "Empleado";
        } else if (opcion == 2) {
            prototipoBase = new Persona("", "", "");
            tipoPrototipo = "Visitante";
        }

        Persona nuevaPersona = prototipoBase.clonar();
        System.out.println("Plantilla de tipo '" + tipoPrototipo + "' seleccionada. Ingrese los detalles para la nueva persona:");

        System.out.print("Ingrese el nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese el apellido: ");
        String apellido = scanner.nextLine();
        System.out.print("Ingrese el RUT: ");
        String rut = scanner.nextLine();

        Persona personaFinal = new Persona(nombre, apellido, rut);
        sistema.registrarPersona(personaFinal);
        System.out.println("Persona creada a partir de plantilla y registrada: " + personaFinal.getNombre() + " " + personaFinal.getApellido());
    }

    private static void verPersonasRegistradas() {
        System.out.println("\n--- LISTADO DE PERSONAS REGISTRADAS (Usando Iterator) ---");
        List<Persona> personas = sistema.getPersonasRegistradas();

        Iterator<Persona> it = personas.iterator();
        while (it.hasNext()) {
            Persona persona = it.next();
            System.out.println(persona);
        }
    }

    private static void menuGestionEventos() {
        int opcion;
        do {
            System.out.println("\n--- GESTION DE EVENTOS ---");
            System.out.println("1. Crear Evento con Prototype (Plantilla)");
            System.out.println("2. Ver todos los Eventos Programados");
            System.out.println("0. Volver al Menu Principal");
            System.out.print("Seleccione una opcion: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    crearEventoConPrototype();
                    break;
                case 2:
                    verEventosProgramados();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opcion no valida. Intente de nuevo.");
            }
        } while (opcion != 0);
    }

    private static void crearEventoConPrototype() {
        System.out.println("\n--- CREAR EVENTO CON PROTOTYPE ---");
        System.out.println("Seleccione un tipo de plantilla para el nuevo evento:");
        System.out.println("1. Plantilla de Reunion");
        System.out.println("2. Plantilla de Taller");
        System.out.print("Seleccione una opcion: ");
        int opcion = scanner.nextInt();
        scanner.nextLine();

        Evento prototipoBase = null;
        String tipoPrototipo = "";

        if (opcion == 1) {
            prototipoBase = new Evento("", "", LocalDateTime.MIN, LocalDateTime.MIN);
            tipoPrototipo = "Reunion";
        } else if (opcion == 2) {
            prototipoBase = new Evento("", "", LocalDateTime.MIN, LocalDateTime.MIN);
            tipoPrototipo = "Taller";
        }

        Evento nuevoEvento = prototipoBase.clonar();
        System.out.println("Plantilla de tipo '" + tipoPrototipo + "' seleccionada. Ingrese los detalles para el nuevo evento:");

        System.out.print("Ingrese el nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese la descripcion: ");
        String descripcion = scanner.nextLine();
        System.out.print("Ingrese la fecha y hora de inicio (YYYY-MM-DD HH:MM, ej: 2025-07-01 14:00): ");
        String fechaHoraInicioStr = scanner.nextLine();
        System.out.print("Ingrese la fecha y hora de fin (YYYY-MM-DD HH:MM, ej: 2025-07-01 17:00): ");
        String fechaHoraFinStr = scanner.nextLine();

        LocalDateTime fechaHoraInicio = LocalDateTime.parse(fechaHoraInicioStr, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        LocalDateTime fechaHoraFin = LocalDateTime.parse(fechaHoraFinStr, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

        Evento eventoFinal = new Evento(nombre, descripcion, fechaHoraInicio, fechaHoraFin);
        sistema.agregarEvento(eventoFinal);
        System.out.println("Evento creado a partir de plantilla y registrado: " + eventoFinal.getNombre());
    }

    private static void verEventosProgramados() {
        System.out.println("\n--- LISTADO DE EVENTOS PROGRAMADOS (Usando para recorrer Iterator) ---");
        List<Evento> eventos = sistema.getEventosProgramados();
        Iterator<Evento> it = eventos.iterator();
        while (it.hasNext()) {
            Evento evento = it.next();
            System.out.println(evento);
        }
    }

    private static void menuRegistroAsistencia() {
        int opcion;
        do {
            System.out.println("\n--- REGISTRO DE ASISTENCIA ---");
            System.out.println("1. Registrar Entrada General");
            System.out.println("2. Registrar Salida General");
            System.out.println("3. Registrar Entrada a Evento");
            System.out.println("0. Volver al Menu Principal");
            System.out.print("Seleccione una opcion: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    registrarEntradaGeneral();
                    break;
                case 2:
                    registrarSalidaGeneral();
                    break;
                case 3:
                    registrarEntradaAEvento();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opcion no valida. Intente de nuevo.");
            }
        } while (opcion != 0);
    }

    private static void registrarEntradaGeneral() {
        System.out.print("Ingrese el RUT de la persona para registrar entrada: ");
        scanner.nextLine();
        String rut = scanner.nextLine();
        sistema.registrarEntrada(rut);
    }

    private static void registrarSalidaGeneral() {
        System.out.print("Ingrese el RUT de la persona para registrar salida: ");
        scanner.nextLine();
        String rut = scanner.nextLine();
        sistema.registrarSalida(rut);
    }

    private static void registrarEntradaAEvento() {
        System.out.print("Ingrese el RUT de la persona: ");
        scanner.nextLine();
        String rut = scanner.nextLine();

        System.out.print("Ingrese el ID del evento: ");
        String idEvento = scanner.nextLine();

        sistema.registrarEntradaAEvento(rut, idEvento);
    }

    private static void menuVerReportes() {
        int opcion;
        do {
            System.out.println("\n--- VER REPORTES ---");
            System.out.println("1. Ver todos los Registros de Asistencia");
            System.out.println("2. Ver Registros de Asistencia por Persona (RUT)");
            System.out.println("3. Ver Registros de Asistencia por Evento (ID)");
            System.out.println("0. Volver al Menu Principal");
            System.out.print("Seleccione una opcion: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    verTodosLosRegistros();
                    break;
                case 2:
                    verRegistrosPorPersona();
                    break;
                case 3:
                    verRegistrosPorEvento();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opcion no valida. Intente de nuevo.");
            }
        } while (opcion != 0);
    }

    private static void verTodosLosRegistros() {
        System.out.println("\n--- LISTADO DE TODOS LOS REGISTROS DE ASISTENCIA (Con Iterator) ---");
        List<RegistroAsistencia> registros = sistema.getRegistrosAsistencia();
        Iterator<RegistroAsistencia> it = registros.iterator();
        while (it.hasNext()) {
            RegistroAsistencia registro = it.next();
            System.out.println(registro);
        }
    }

    private static void verRegistrosPorPersona() {
        System.out.print("Ingrese el RUT de la persona para ver sus registros: ");
        scanner.nextLine();
        String rut = scanner.nextLine();
        List<RegistroAsistencia> registros = sistema.getRegistrosPorPersona(rut);
        System.out.println("\n--- REGISTROS DE ASISTENCIA PARA " + rut + " (Con Iterator) ---");
        Iterator<RegistroAsistencia> it = registros.iterator();
        while (it.hasNext()) {
            RegistroAsistencia registro = it.next();
            System.out.println(registro);
        }
    }

    private static void verRegistrosPorEvento() {
        System.out.print("Ingrese el ID del evento para ver sus registros: ");
        scanner.nextLine();
        String idEvento = scanner.nextLine();
        List<RegistroAsistencia> registros = sistema.getRegistrosPorEvento(idEvento);
        System.out.println("\n--- REGISTROS DE ASISTENCIA PARA EVENTO " + idEvento + " (Con Iterator) ---");
        Iterator<RegistroAsistencia> it = registros.iterator();
        while (it.hasNext()) {
            RegistroAsistencia registro = it.next();
            System.out.println(registro);
        }
    }
}
