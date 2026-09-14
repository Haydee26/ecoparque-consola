package ecoparque.service;

import ecoparque.exception.DatosInvalidosException;
import ecoparque.exception.EntidadNoEncontradaException;
import ecoparque.exception.PersistenciaException;
import ecoparque.model.AsignacionGuia;
import java.time.LocalTime;
import java.util.List;

public interface AsignacionGuiaService {

    AsignacionGuia asignar(String nombreGuia, String codigoItinerario, LocalTime hora)
            throws DatosInvalidosException, EntidadNoEncontradaException, PersistenciaException;

    List<AsignacionGuia> listarPorGuia(String nombreGuia) throws PersistenciaException;
}

