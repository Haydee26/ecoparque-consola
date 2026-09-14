package ecoparque.controller;

import ecoparque.exception.EcoparqueException;
import ecoparque.model.RolUsuario;
import ecoparque.model.Usuario;
import ecoparque.service.AsignacionGuiaService;
import ecoparque.service.AutenticacionService;
import ecoparque.service.CuidadorService;
import ecoparque.service.EmpleadoService;
import ecoparque.service.EspecieService;
import ecoparque.service.GuiaService;
import ecoparque.service.HabitatService;
import ecoparque.service.ItinerarioService;
import ecoparque.service.ResponsabilidadCuidadorService;
import ecoparque.service.ZonaService;
import ecoparque.view.Vista;

/**
 * Punto de entrada de la interaccion: pide credenciales y, segun el
 * rol del usuario autenticado, delega en el controlador especifico
 * (Administrador, Guia o Cuidador). No conoce los detalles de ningun
 * menu especifico: solo orquesta el login y la derivacion por rol.
 */
public class ControladorPrincipal implements Controlador {

    private final Vista vista;
    private final AutenticacionService autenticacionService;

    // Dependencias necesarias para construir los controladores por rol
    // una vez que se conoce el usuario autenticado.
    private final ZonaService zonaService;
    private final HabitatService habitatService;
    private final EspecieService especieService;
    private final ItinerarioService itinerarioService;
    private final GuiaService guiaService;
    private final CuidadorService cuidadorService;
    private final AsignacionGuiaService asignacionGuiaService;
    private final ResponsabilidadCuidadorService responsabilidadCuidadorService;
    private final EmpleadoService empleadoService;

    public ControladorPrincipal(Vista vista, AutenticacionService autenticacionService, ZonaService zonaService,
            HabitatService habitatService, EspecieService especieService, ItinerarioService itinerarioService,
            GuiaService guiaService, CuidadorService cuidadorService, AsignacionGuiaService asignacionGuiaService,
            ResponsabilidadCuidadorService responsabilidadCuidadorService, EmpleadoService empleadoService) {
        this.vista = vista;
        this.autenticacionService = autenticacionService;
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
        vista.mostrarMensaje("=== ECOPARQUE ===");
        vista.mostrarMensaje("Inicio de sesion");

        String nombreUsuario = vista.leerTexto("Usuario: ");
        String clave = vista.leerTexto("Clave: ");

        try {
            Usuario usuario = autenticacionService.login(nombreUsuario, clave);
            Controlador controladorDeRol = crearControladorParaRol(usuario);
            controladorDeRol.iniciar();
        } catch (EcoparqueException e) {
            vista.mostrarError(e.getMessage());
        }
    }

    private Controlador crearControladorParaRol(Usuario usuario) {
        if (usuario.getRol() == RolUsuario.ADMINISTRADOR) {
            return new ControladorAdministrador(vista, zonaService, habitatService, especieService,
                    itinerarioService, guiaService, cuidadorService, asignacionGuiaService,
                    responsabilidadCuidadorService, empleadoService);
        }
        if (usuario.getRol() == RolUsuario.GUIA) {
            return new ControladorGuia(vista, guiaService, asignacionGuiaService, usuario);
        }
        return new ControladorCuidador(vista, cuidadorService, responsabilidadCuidadorService, usuario);
    }
}
