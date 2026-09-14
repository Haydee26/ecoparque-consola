package ecoparque.dao;

import ecoparque.exception.PersistenciaException;
import ecoparque.model.AsignacionGuia;
import java.util.List;

public interface AsignacionGuiaDAO extends GenericDAO<AsignacionGuia> {

    List<AsignacionGuia> listarPorGuia(String nombreGuia) throws PersistenciaException;
}
