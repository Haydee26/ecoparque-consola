package ecoparque.controller;

import ecoparque.exception.EcoparqueException;
import ecoparque.model.AsignacionGuia;
import ecoparque.model.Guia;
import ecoparque.model.Usuario;
import ecoparque.service.AsignacionGuiaService;
import ecoparque.service.GuiaService;
import ecoparque.view.Vista;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ControladorGuia implements Controlador {

    private static final List<String> OPCIONES = Arrays.asList("Ver mis datos", "Ver mis itinerarios");

    private final Vista vista;
    private final GuiaService guiaService;
    private final AsignacionGuiaService asignacionGuiaService;
    private final Usuario usuario;

    public ControladorGuia(Vista vista, GuiaService guiaService, AsignacionGuiaService asignacionGuiaService,
            Usuario usuario) {
        this.vista = vista;
        this.guiaService = guiaService;
        this.asignacionGuiaService = asignacionGuiaService;
        this.usuario = usuario;
    }

    @Override
    public void iniciar() {
        int opcion;

        do {
            opcion = -1;
            try {
                opcion = vista.mostrarMenuYLeerOpcion("=== MENU GUIA ===", OPCIONES);

                if (opcion == 1) {
                    verMisDatos();
                } else if (opcion == 2) {
                    verMisItinerarios();
                } else if (opcion == 0) {
                    vista.mostrarMensaje("Cerrando menu guia...");
                } else {
                    vista.mostrarError("Opcion invalida.");
                }
            } catch (EcoparqueException e) {
                vista.mostrarError(e.getMessage());
            }
        } while (opcion != 0);
    }

    private void verMisDatos() throws EcoparqueException {
        Guia guia = guiaService.buscarPorNombre(usuario.getNombrePersona());

        vista.mostrarMensaje("Nombre: " + guia.getNombre());
        vista.mostrarMensaje("Direccion: " + guia.getDireccion());
        vista.mostrarMensaje("Telefono: " + guia.getTelefono());
        vista.mostrarMensaje("Fecha de ingreso: " + guia.getFechaIngreso());
    }

    private void verMisItinerarios() throws EcoparqueException {
        List<AsignacionGuia> asignaciones = asignacionGuiaService.listarPorGuia(usuario.getNombrePersona());

        List<String> lineas = asignaciones.stream()
                .map(a -> a.getItinerario().getCodigo() + " - hora: " + a.getHora())
                .collect(Collectors.toList());

        vista.mostrarLista("Mis itinerarios:", lineas);
    }
}
