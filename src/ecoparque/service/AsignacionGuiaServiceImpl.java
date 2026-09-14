package ecoparque.service;

import ecoparque.dao.AsignacionGuiaDAO;
import ecoparque.exception.DatosInvalidosException;
import ecoparque.exception.EntidadNoEncontradaException;
import ecoparque.exception.PersistenciaException;
import ecoparque.model.AsignacionGuia;
import ecoparque.model.Guia;
import ecoparque.model.Itinerario;
import java.time.LocalTime;
import java.util.List;

public class AsignacionGuiaServiceImpl implements AsignacionGuiaService {

    private final AsignacionGuiaDAO asignacionGuiaDAO;
    private final GuiaService guiaService;
    private final ItinerarioService itinerarioService;

    public AsignacionGuiaServiceImpl(AsignacionGuiaDAO asignacionGuiaDAO, GuiaService guiaService,
            ItinerarioService itinerarioService) {
        this.asignacionGuiaDAO = asignacionGuiaDAO;
        this.guiaService = guiaService;
        this.itinerarioService = itinerarioService;
    }

    @Override
    public AsignacionGuia asignar(String nombreGuia, String codigoItinerario, LocalTime hora)
            throws DatosInvalidosException, EntidadNoEncontradaException, PersistenciaException {

        if (hora == null) {
            throw new DatosInvalidosException("Debe indicar la hora del recorrido.");
        }

        Guia guia = guiaService.buscarPorNombre(nombreGuia);

        Itinerario itinerario = itinerarioService.listarItinerarios().stream()
                .filter(i -> i.getCodigo().equalsIgnoreCase(codigoItinerario))
                .findFirst()
                .orElseThrow(() -> new EntidadNoEncontradaException("No existe el itinerario '" + codigoItinerario + "'."));

        AsignacionGuia asignacion = new AsignacionGuia(guia, itinerario, hora);
        asignacionGuiaDAO.guardar(asignacion);
        return asignacion;
    }

    @Override
    public List<AsignacionGuia> listarPorGuia(String nombreGuia) throws PersistenciaException {
        return asignacionGuiaDAO.listarPorGuia(nombreGuia);
    }
}

