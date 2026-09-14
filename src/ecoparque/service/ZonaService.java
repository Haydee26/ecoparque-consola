package ecoparque.service;

import ecoparque.exception.DatosInvalidosException;
import ecoparque.exception.PersistenciaException;
import ecoparque.model.Zona;
import java.util.List;

public interface ZonaService {

    Zona crearZona(String nombre, double extension) throws DatosInvalidosException, PersistenciaException;

    List<Zona> listarZonas() throws PersistenciaException;
}
