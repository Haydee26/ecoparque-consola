package ecoparque.dao;

import ecoparque.exception.PersistenciaException;
import java.util.List;

public interface GenericDAO<T> {

    void guardar(T entidad) throws PersistenciaException;

    List<T> listarTodos() throws PersistenciaException;
}
