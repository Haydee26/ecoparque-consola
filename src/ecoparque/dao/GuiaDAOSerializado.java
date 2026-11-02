package ecoparque.dao;

import ecoparque.exception.EntidadNoEncontradaException;
import ecoparque.exception.PersistenciaException;
import ecoparque.model.Guia;

public class GuiaDAOSerializado extends DAOSerializado<Guia> implements GuiaDAO {

    public GuiaDAOSerializado() {
        super("guias.dat");
    }

    @Override
    public Guia buscarPorNombre(String nombre) throws PersistenciaException, EntidadNoEncontradaException {
        for (Guia guia : listarTodos()) {
            if (guia.getNombre().equalsIgnoreCase(nombre)) {
                return guia;
            }
        }
        throw new EntidadNoEncontradaException("No se encontro un guia con el nombre '" + nombre + "'.");
    }
}
