package ecoparque.dao;

import ecoparque.exception.PersistenciaException;
import ecoparque.model.ResponsabilidadCuidador;
import java.util.ArrayList;
import java.util.List;

public class ResponsabilidadCuidadorDAOSerializado extends DAOSerializado<ResponsabilidadCuidador>
        implements ResponsabilidadCuidadorDAO {

    public ResponsabilidadCuidadorDAOSerializado() {
        super("responsabilidades_cuidadores.dat");
    }

    @Override
    public List<ResponsabilidadCuidador> listarPorCuidador(String nombreCuidador) throws PersistenciaException {
        List<ResponsabilidadCuidador> resultado = new ArrayList<>();
        for (ResponsabilidadCuidador responsabilidad : listarTodos()) {
            if (responsabilidad.getCuidador().getNombre().equalsIgnoreCase(nombreCuidador)) {
                resultado.add(responsabilidad);
            }
        }
        return resultado;
    }
}
