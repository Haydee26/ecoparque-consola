package ecoparque.dao;

import ecoparque.exception.PersistenciaException;
import ecoparque.model.AsignacionGuia;
import java.util.ArrayList;
import java.util.List;

public class AsignacionGuiaDAOSerializado extends DAOSerializado<AsignacionGuia> implements AsignacionGuiaDAO {

    public AsignacionGuiaDAOSerializado() {
        super("asignaciones_guias.dat");
    }

    @Override
    public List<AsignacionGuia> listarPorGuia(String nombreGuia) throws PersistenciaException {
        List<AsignacionGuia> resultado = new ArrayList<>();
        for (AsignacionGuia asignacion : listarTodos()) {
            if (asignacion.getGuia().getNombre().equalsIgnoreCase(nombreGuia)) {
                resultado.add(asignacion);
            }
        }
        return resultado;
    }
}
