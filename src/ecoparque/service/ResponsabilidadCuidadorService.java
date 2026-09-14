package ecoparque.service;

import ecoparque.exception.DatosInvalidosException;
import ecoparque.exception.EntidadNoEncontradaException;
import ecoparque.exception.PersistenciaException;
import ecoparque.model.ResponsabilidadCuidador;
import java.time.LocalDate;
import java.util.List;

public interface ResponsabilidadCuidadorService {

    ResponsabilidadCuidador asignar(String nombreCuidador, String nombreEspecie, LocalDate fechaDesde)
            throws DatosInvalidosException, EntidadNoEncontradaException, PersistenciaException;

    List<ResponsabilidadCuidador> listarPorCuidador(String nombreCuidador) throws PersistenciaException;
}
