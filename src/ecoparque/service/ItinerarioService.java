package ecoparque.service;

import ecoparque.exception.DatosInvalidosException;
import ecoparque.exception.EntidadNoEncontradaException;
import ecoparque.exception.PersistenciaException;
import ecoparque.model.Itinerario;
import java.util.List;

public interface ItinerarioService {

    Itinerario crearItinerario(String codigo, int duracionMinutos, double longitudMetros,
            int maximoVisitantes, int cantidadEspecies, List<String> nombresZonas)
            throws DatosInvalidosException, EntidadNoEncontradaException, PersistenciaException;

    List<Itinerario> listarItinerarios() throws PersistenciaException;
}
