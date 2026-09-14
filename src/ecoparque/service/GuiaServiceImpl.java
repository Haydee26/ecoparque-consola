package ecoparque.service;

import ecoparque.dao.GuiaDAO;
import ecoparque.exception.DatosInvalidosException;
import ecoparque.exception.EntidadNoEncontradaException;
import ecoparque.exception.PersistenciaException;
import ecoparque.model.Guia;
import java.time.LocalDate;
import java.util.List;

public class GuiaServiceImpl implements GuiaService {

    private final GuiaDAO guiaDAO;

    public GuiaServiceImpl(GuiaDAO guiaDAO) {
        this.guiaDAO = guiaDAO;
    }

    @Override
    public Guia crearGuia(String nombre, String direccion, String telefono, LocalDate fechaIngreso)
            throws DatosInvalidosException, PersistenciaException {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new DatosInvalidosException("El nombre del guia no puede estar vacio.");
        }

        Guia guia = new Guia(nombre.trim(), direccion, telefono, fechaIngreso);
        guiaDAO.guardar(guia);
        return guia;
    }

    @Override
    public List<Guia> listarGuias() throws PersistenciaException {
        return guiaDAO.listarTodos();
    }

    @Override
    public Guia buscarPorNombre(String nombre) throws EntidadNoEncontradaException, PersistenciaException {
        return guiaDAO.buscarPorNombre(nombre);
    }
}
