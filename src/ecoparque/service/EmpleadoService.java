package ecoparque.service;

import ecoparque.exception.PersistenciaException;
import ecoparque.model.Persona;
import java.util.List;


public interface EmpleadoService {

    List<Persona> listarPorAntiguedad() throws PersistenciaException;
}

