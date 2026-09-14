
package ecoparque.controller;

import ecoparque.exception.EcoparqueException;
import ecoparque.model.Cuidador;
import ecoparque.model.ResponsabilidadCuidador;
import ecoparque.model.Usuario;
import ecoparque.service.CuidadorService;
import ecoparque.service.ResponsabilidadCuidadorService;
import ecoparque.view.Vista;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ControladorCuidador implements Controlador {

    private static final List<String> OPCIONES = Arrays.asList("Ver mis datos", "Ver especies a cargo");

    private final Vista vista;
    private final CuidadorService cuidadorService;
    private final ResponsabilidadCuidadorService responsabilidadCuidadorService;
    private final Usuario usuario;

    public ControladorCuidador(Vista vista, CuidadorService cuidadorService,
            ResponsabilidadCuidadorService responsabilidadCuidadorService, Usuario usuario) {
        this.vista = vista;
        this.cuidadorService = cuidadorService;
        this.responsabilidadCuidadorService = responsabilidadCuidadorService;
        this.usuario = usuario;
    }

    @Override
    public void iniciar() {
        int opcion;

        do {
            opcion = -1;
            try {
                opcion = vista.mostrarMenuYLeerOpcion("=== MENU CUIDADOR ===", OPCIONES);

                if (opcion == 1) {
                    verMisDatos();
                } else if (opcion == 2) {
                    verEspeciesACargo();
                } else if (opcion == 0) {
                    vista.mostrarMensaje("Cerrando menu cuidador...");
                } else {
                    vista.mostrarError("Opcion invalida.");
                }
            } catch (EcoparqueException e) {
                vista.mostrarError(e.getMessage());
            }
        } while (opcion != 0);
    }

    private void verMisDatos() throws EcoparqueException {
        Cuidador cuidador = cuidadorService.buscarPorNombre(usuario.getNombrePersona());

        vista.mostrarMensaje("Nombre: " + cuidador.getNombre());
        vista.mostrarMensaje("Direccion: " + cuidador.getDireccion());
        vista.mostrarMensaje("Telefono: " + cuidador.getTelefono());
        vista.mostrarMensaje("Fecha de ingreso: " + cuidador.getFechaIngreso());
    }

    private void verEspeciesACargo() throws EcoparqueException {
        List<ResponsabilidadCuidador> responsabilidades =
                responsabilidadCuidadorService.listarPorCuidador(usuario.getNombrePersona());

        List<String> lineas = responsabilidades.stream()
                .map(r -> r.getEspecie().getNombreEspanol() + " - desde: " + r.getFechaDesde())
                .collect(Collectors.toList());

        vista.mostrarLista("Especies a cargo:", lineas);
    }
}
