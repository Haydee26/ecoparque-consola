package ecoparque.service;

import ecoparque.exception.DatosInvalidosException;
import ecoparque.exception.EntidadNoEncontradaException;
import ecoparque.exception.PersistenciaException;
import ecoparque.model.Especie;
import java.util.List;

public interface EspecieService {

    Especie crearEspecie(String nombreEspanol, String nombreCientifico, String descripcion,
            String nombreZona, List<String> nombresHabitats)
            throws DatosInvalidosException, EntidadNoEncontradaException, PersistenciaException;

    List<Especie> listarEspecies() throws PersistenciaException;
}

