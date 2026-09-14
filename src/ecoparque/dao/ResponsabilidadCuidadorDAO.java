package ecoparque.dao;

import ecoparque.exception.PersistenciaException;
import ecoparque.model.ResponsabilidadCuidador;
import java.util.List;

public interface ResponsabilidadCuidadorDAO extends GenericDAO<ResponsabilidadCuidador> {

    List<ResponsabilidadCuidador> listarPorCuidador(String nombreCuidador) throws PersistenciaException;
}
