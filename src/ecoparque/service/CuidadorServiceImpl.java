
package ecoparque.service;

import ecoparque.dao.CuidadorDAO;
import ecoparque.exception.DatosInvalidosException;
import ecoparque.exception.EntidadNoEncontradaException;
import ecoparque.exception.PersistenciaException;
import ecoparque.model.Cuidador;
import java.time.LocalDate;
import java.util.List;

public class CuidadorServiceImpl implements CuidadorService {

    private final CuidadorDAO cuidadorDAO;

    public CuidadorServiceImpl(CuidadorDAO cuidadorDAO) {
        this.cuidadorDAO = cuidadorDAO;
    }

    @Override
    public Cuidador crearCuidador(String nombre, String direccion, String telefono, LocalDate fechaIngreso)
            throws DatosInvalidosException, PersistenciaException {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new DatosInvalidosException("El nombre del cuidador no puede estar vacio.");
        }

        Cuidador cuidador = new Cuidador(nombre.trim(), direccion, telefono, fechaIngreso);
        cuidadorDAO.guardar(cuidador);
        return cuidador;
    }

    @Override
    public List<Cuidador> listarCuidadores() throws PersistenciaException {
        return cuidadorDAO.listarTodos();
    }

    @Override
    public Cuidador buscarPorNombre(String nombre) throws EntidadNoEncontradaException, PersistenciaException {
        return cuidadorDAO.buscarPorNombre(nombre);
    }
}
