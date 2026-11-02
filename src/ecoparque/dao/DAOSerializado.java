package ecoparque.dao;

import ecoparque.exception.PersistenciaException;
import java.io.Serializable;
import java.util.List;

/**
 * Base de todos los DAO que persisten por serializacion: cada entidad se guarda
 * en su propio archivo .dat, como una lista de objetos. Las referencias a otros
 * objetos (por ejemplo la zona de una especie) se serializan junto con el objeto.
 */
public abstract class DAOSerializado<T extends Serializable> implements GenericDAO<T> {

    private final String archivo;

    protected DAOSerializado(String archivo) {
        this.archivo = archivo;
    }

    @Override
    public void guardar(T entidad) throws PersistenciaException {
        List<T> todos = SerializacionUtil.leer(archivo);
        todos.add(entidad);
        SerializacionUtil.escribir(archivo, todos);
    }

    @Override
    public List<T> listarTodos() throws PersistenciaException {
        return SerializacionUtil.leer(archivo);
    }
}
