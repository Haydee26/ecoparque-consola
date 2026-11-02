package ecoparque.dao;

import ecoparque.exception.EntidadNoEncontradaException;
import ecoparque.exception.PersistenciaException;
import ecoparque.model.Cuidador;

public class CuidadorDAOSerializado extends DAOSerializado<Cuidador> implements CuidadorDAO {

    public CuidadorDAOSerializado() {
        super("cuidadores.dat");
    }

    @Override
    public Cuidador buscarPorNombre(String nombre) throws PersistenciaException, EntidadNoEncontradaException {
        for (Cuidador cuidador : listarTodos()) {
            if (cuidador.getNombre().equalsIgnoreCase(nombre)) {
                return cuidador;
            }
        }
        throw new EntidadNoEncontradaException("No se encontro un cuidador con el nombre '" + nombre + "'.");
    }
}
