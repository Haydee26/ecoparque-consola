package ecoparque.service;

import ecoparque.dao.ItinerarioDAO;
import ecoparque.exception.DatosInvalidosException;
import ecoparque.exception.EntidadNoEncontradaException;
import ecoparque.exception.PersistenciaException;
import ecoparque.model.Itinerario;
import ecoparque.model.Zona;
import java.util.List;

public class ItinerarioServiceImpl implements ItinerarioService {

    private final ItinerarioDAO itinerarioDAO;
    private final ZonaService zonaService;

    public ItinerarioServiceImpl(ItinerarioDAO itinerarioDAO, ZonaService zonaService) {
        this.itinerarioDAO = itinerarioDAO;
        this.zonaService = zonaService;
    }

    @Override
    public Itinerario crearItinerario(String codigo, int duracionMinutos, double longitudMetros,
            int maximoVisitantes, int cantidadEspecies, List<String> nombresZonas)
            throws DatosInvalidosException, EntidadNoEncontradaException, PersistenciaException {

        if (codigo == null || codigo.trim().isEmpty()) {
            throw new DatosInvalidosException("El codigo del itinerario no puede estar vacio.");
        }
        if (duracionMinutos <= 0 || longitudMetros <= 0 || maximoVisitantes <= 0) {
            throw new DatosInvalidosException("Duracion, longitud y maximo de visitantes deben ser mayores a cero.");
        }
        if (cantidadEspecies < 0) {
            throw new DatosInvalidosException("La cantidad de especies no puede ser negativa.");
        }

        Itinerario itinerario = new Itinerario(codigo.trim(), duracionMinutos, longitudMetros,
                maximoVisitantes, cantidadEspecies);

        List<Zona> disponibles = zonaService.listarZonas();
        for (String nombreZona : nombresZonas) {
            Zona zona = disponibles.stream()
                    .filter(z -> z.getNombre().equalsIgnoreCase(nombreZona))
                    .findFirst()
                    .orElseThrow(() -> new EntidadNoEncontradaException("No existe la zona '" + nombreZona + "'."));
            itinerario.agregarZona(zona);
        }

        itinerarioDAO.guardar(itinerario);
        return itinerario;
    }

    @Override
    public List<Itinerario> listarItinerarios() throws PersistenciaException {
        return itinerarioDAO.listarTodos();
    }
}
