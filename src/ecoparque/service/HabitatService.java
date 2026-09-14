package ecoparque.service;

import ecoparque.exception.DatosInvalidosException;
import ecoparque.exception.PersistenciaException;
import ecoparque.model.Habitat;
import java.util.List;

public interface HabitatService {

    Habitat crearHabitat(String nombre, String clima, String vegetacion, List<String> continentes)
            throws DatosInvalidosException, PersistenciaException;

    List<Habitat> listarHabitats() throws PersistenciaException;
}
