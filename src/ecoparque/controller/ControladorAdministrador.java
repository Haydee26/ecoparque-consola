
package ecoparque.controller;


import ecoparque.exception.EcoparqueException;
import ecoparque.model.Cuidador;
import ecoparque.model.Especie;
import ecoparque.model.Guia;
import ecoparque.model.Habitat;
import ecoparque.model.Itinerario;
import ecoparque.model.Persona;
import ecoparque.model.Zona;
import ecoparque.service.AsignacionGuiaService;
import ecoparque.service.CuidadorService;
import ecoparque.service.EmpleadoService;
import ecoparque.service.EspecieService;
import ecoparque.service.GuiaService;
import ecoparque.service.HabitatService;
import ecoparque.service.ItinerarioService;
import ecoparque.service.ResponsabilidadCuidadorService;
import ecoparque.service.ZonaService;
import ecoparque.view.Vista;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


public class ControladorAdministrador implements Controlador {

    private static final List<String> OPCIONES = Arrays.asList(
            "Listar zonas",
            "Listar habitats",
            "Listar especies",
            "Cargar zona",
            "Cargar habitat",
            "Cargar especie",
            "Cargar itinerario",
            "Listar itinerarios",
            "Cargar guia",
            "Listar guias",
            "Cargar cuidador",
            "Listar cuidadores",
            "Asignar guia a itinerario",
            "Asignar cuidador a especie",
            "Listar empleados por antiguedad"
    );

    private final Vista vista;
    private final ZonaService zonaService;
    private final HabitatService habitatService;
    private final EspecieService especieService;
    private final ItinerarioService itinerarioService;
    private final GuiaService guiaService;
    private final CuidadorService cuidadorService;
    private final AsignacionGuiaService asignacionGuiaService;
    private final ResponsabilidadCuidadorService responsabilidadCuidadorService;
    private final EmpleadoService empleadoService;

    public ControladorAdministrador(Vista vista, ZonaService zonaService, HabitatService habitatService,
            EspecieService especieService, ItinerarioService itinerarioService, GuiaService guiaService,
            CuidadorService cuidadorService, AsignacionGuiaService asignacionGuiaService,
            ResponsabilidadCuidadorService responsabilidadCuidadorService, EmpleadoService empleadoService) {
        this.vista = vista;
        this.zonaService = zonaService;
        this.habitatService = habitatService;
        this.especieService = especieService;
        this.itinerarioService = itinerarioService;
        this.guiaService = guiaService;
        this.cuidadorService = cuidadorService;
        this.asignacionGuiaService = asignacionGuiaService;
        this.responsabilidadCuidadorService = responsabilidadCuidadorService;
        this.empleadoService = empleadoService;
    }

    @Override
    public void iniciar() {
        int opcion;

        do {
            opcion = -1;
            try {
                opcion = vista.mostrarMenuYLeerOpcion("=== MENU ADMINISTRADOR ===", OPCIONES);
                ejecutar(opcion);
            } catch (EcoparqueException e) {
                vista.mostrarError(e.getMessage());
            }
        } while (opcion != 0);
    }

    private void ejecutar(int opcion) throws EcoparqueException {
        switch (opcion) {
            case 1:
                listarZonas();
                break;
            case 2:
                listarHabitats();
                break;
            case 3:
                listarEspecies();
                break;
            case 4:
                cargarZona();
                break;
            case 5:
                cargarHabitat();
                break;
            case 6:
                cargarEspecie();
                break;
            case 7:
                cargarItinerario();
                break;
            case 8:
                listarItinerarios();
                break;
            case 9:
                cargarGuia();
                break;
            case 10:
                listarGuias();
                break;
            case 11:
                cargarCuidador();
                break;
            case 12:
                listarCuidadores();
                break;
            case 13:
                asignarGuiaAItinerario();
                break;
            case 14:
                asignarCuidadorAEspecie();
                break;
            case 15:
                listarEmpleadosPorAntiguedad();
                break;
            case 0:
                vista.mostrarMensaje("Cerrando menu administrador...");
                break;
            default:
                vista.mostrarError("Opcion invalida.");
                break;
        }
    }

    private void listarZonas() throws EcoparqueException {
        List<String> nombres = zonaService.listarZonas().stream().map(Zona::getNombre).collect(Collectors.toList());
        vista.mostrarLista("Zonas:", nombres);
    }

    private void listarHabitats() throws EcoparqueException {
        List<String> nombres = habitatService.listarHabitats().stream().map(Habitat::getNombre).collect(Collectors.toList());
        vista.mostrarLista("Habitats:", nombres);
    }

    private void listarEspecies() throws EcoparqueException {
        List<String> nombres = especieService.listarEspecies().stream().map(Especie::getNombreEspanol).collect(Collectors.toList());
        vista.mostrarLista("Especies:", nombres);
    }

    private void cargarZona() throws EcoparqueException {
        String nombre = vista.leerTexto("Nombre de la zona: ");
        double extension = vista.leerDecimal("Extension: ");

        zonaService.crearZona(nombre, extension);
        vista.mostrarMensaje("Zona guardada correctamente.");
    }

    private void cargarHabitat() throws EcoparqueException {
        String nombre = vista.leerTexto("Nombre del habitat: ");
        String clima = vista.leerTexto("Clima: ");
        String vegetacion = vista.leerTexto("Vegetacion predominante: ");

        int cantidad = vista.leerEntero("Cuantos continentes desea cargar?: ");
        List<String> continentes = new ArrayList<>();
        for (int i = 0; i < cantidad; i++) {
            continentes.add(vista.leerTexto("Continente " + (i + 1) + ": "));
        }

        habitatService.crearHabitat(nombre, clima, vegetacion, continentes);
        vista.mostrarMensaje("Habitat guardado correctamente.");
    }

    private void cargarEspecie() throws EcoparqueException {
        String nombreEspanol = vista.leerTexto("Nombre en espanol: ");
        String nombreCientifico = vista.leerTexto("Nombre cientifico: ");
        String descripcion = vista.leerTexto("Descripcion: ");

        List<String> nombresZonas = zonaService.listarZonas().stream().map(Zona::getNombre).collect(Collectors.toList());
        int indiceZona = vista.leerOpcionDeLista("Seleccione una zona:", nombresZonas);

        List<String> nombresHabitats = habitatService.listarHabitats().stream().map(Habitat::getNombre).collect(Collectors.toList());
        List<String> habitatsElegidos = vista.leerVariosDeLista("habitat", nombresHabitats);

        especieService.crearEspecie(nombreEspanol, nombreCientifico, descripcion,
                nombresZonas.get(indiceZona), habitatsElegidos);
        vista.mostrarMensaje("Especie guardada correctamente.");
    }

    private void cargarItinerario() throws EcoparqueException {
        String codigo = vista.leerTexto("Codigo del itinerario: ");
        int duracion = vista.leerEntero("Duracion en minutos: ");
        double longitud = vista.leerDecimal("Longitud en metros: ");
        int maximoVisitantes = vista.leerEntero("Maximo de visitantes: ");
        int cantidadEspecies = vista.leerEntero("Cantidad de especies distintas que visita: ");

        List<String> nombresZonas = zonaService.listarZonas().stream().map(Zona::getNombre).collect(Collectors.toList());
        List<String> zonasElegidas = vista.leerVariosDeLista("zona", nombresZonas);

        itinerarioService.crearItinerario(codigo, duracion, longitud, maximoVisitantes, cantidadEspecies, zonasElegidas);
        vista.mostrarMensaje("Itinerario guardado correctamente.");
    }

    private void listarItinerarios() throws EcoparqueException {
        List<String> lineas = itinerarioService.listarItinerarios().stream()
                .map(i -> i.getCodigo() + " - " + i.getDuracionMinutos() + " minutos - "
                        + i.getCantidadEspecies() + " especies")
                .collect(Collectors.toList());
        vista.mostrarLista("Itinerarios:", lineas);
    }

    private void cargarGuia() throws EcoparqueException {
        String nombre = vista.leerTexto("Nombre: ");
        String direccion = vista.leerTexto("Direccion: ");
        String telefono = vista.leerTexto("Telefono: ");
        LocalDate fechaIngreso = vista.leerFecha("Fecha de ingreso");

        guiaService.crearGuia(nombre, direccion, telefono, fechaIngreso);
        vista.mostrarMensaje("Guia guardado correctamente.");
    }

    private void listarGuias() throws EcoparqueException {
        List<String> lineas = guiaService.listarGuias().stream()
                .map(g -> g.getNombre() + " - ingreso: " + g.getFechaIngreso())
                .collect(Collectors.toList());
        vista.mostrarLista("Guias:", lineas);
    }

    private void cargarCuidador() throws EcoparqueException {
        String nombre = vista.leerTexto("Nombre: ");
        String direccion = vista.leerTexto("Direccion: ");
        String telefono = vista.leerTexto("Telefono: ");
        LocalDate fechaIngreso = vista.leerFecha("Fecha de ingreso");

        cuidadorService.crearCuidador(nombre, direccion, telefono, fechaIngreso);
        vista.mostrarMensaje("Cuidador guardado correctamente.");
    }

    private void listarCuidadores() throws EcoparqueException {
        List<String> lineas = cuidadorService.listarCuidadores().stream()
                .map(c -> c.getNombre() + " - ingreso: " + c.getFechaIngreso())
                .collect(Collectors.toList());
        vista.mostrarLista("Cuidadores:", lineas);
    }

    private void asignarGuiaAItinerario() throws EcoparqueException {
        List<String> nombresGuias = guiaService.listarGuias().stream().map(Guia::getNombre).collect(Collectors.toList());
        int indiceGuia = vista.leerOpcionDeLista("Seleccione un guia:", nombresGuias);

        List<String> codigosItinerarios = itinerarioService.listarItinerarios().stream()
                .map(Itinerario::getCodigo).collect(Collectors.toList());
        int indiceItinerario = vista.leerOpcionDeLista("Seleccione un itinerario:", codigosItinerarios);

        LocalTime hora = vista.leerHora("Hora del recorrido");

        asignacionGuiaService.asignar(nombresGuias.get(indiceGuia), codigosItinerarios.get(indiceItinerario), hora);
        vista.mostrarMensaje("Guia asignado correctamente.");
    }

    private void asignarCuidadorAEspecie() throws EcoparqueException {
        List<String> nombresCuidadores = cuidadorService.listarCuidadores().stream()
                .map(Cuidador::getNombre).collect(Collectors.toList());
        int indiceCuidador = vista.leerOpcionDeLista("Seleccione un cuidador:", nombresCuidadores);

        List<String> nombresEspecies = especieService.listarEspecies().stream()
                .map(Especie::getNombreEspanol).collect(Collectors.toList());
        int indiceEspecie = vista.leerOpcionDeLista("Seleccione una especie:", nombresEspecies);

        LocalDate fechaDesde = vista.leerFecha("Fecha desde la cual se hace cargo");

        responsabilidadCuidadorService.asignar(nombresCuidadores.get(indiceCuidador),
                nombresEspecies.get(indiceEspecie), fechaDesde);
        vista.mostrarMensaje("Cuidador asignado correctamente.");
    }

    private void listarEmpleadosPorAntiguedad() throws EcoparqueException {
        List<String> lineas = new ArrayList<>();
        for (Persona persona : empleadoService.listarPorAntiguedad()) {
            lineas.add(persona.getTipo() + " - " + persona.getNombre() + " - ingreso: " + persona.getFechaIngreso());
        }
        vista.mostrarLista("Empleados por antiguedad:", lineas);
    }
}
