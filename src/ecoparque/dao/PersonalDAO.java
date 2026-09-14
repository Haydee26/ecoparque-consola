package ecoparque.dao;

import ecoparque.exception.EntidadNoEncontradaException;
import ecoparque.exception.PersistenciaException;


public interface PersonalDAO<T> extends GenericDAO<T> {

    T buscarPorNombre(String nombre) throws PersistenciaException, EntidadNoEncontradaException;
}
