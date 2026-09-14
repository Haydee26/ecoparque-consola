package ecoparque.service;

import ecoparque.dao.ZonaDAO;
import ecoparque.exception.DatosInvalidosException;
import ecoparque.exception.PersistenciaException;
import ecoparque.model.Zona;
import java.util.List;

public class ZonaServiceImpl implements ZonaService {

    private final ZonaDAO zonaDAO;

    public ZonaServiceImpl(ZonaDAO zonaDAO) {
        this.zonaDAO = zonaDAO;
    }

    @Override
    public Zona crearZona(String nombre, double extension) throws DatosInvalidosException, PersistenciaException {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new DatosInvalidosException("El nombre de la zona no puede estar vacio.");
        }
        if (extension <= 0) {
            throw new DatosInvalidosException("La extension de la zona debe ser mayor a cero.");
        }

        Zona zona = new Zona(nombre.trim(), extension);
        zonaDAO.guardar(zona);
        return zona;
    }

    @Override
    public List<Zona> listarZonas() throws PersistenciaException {
        return zonaDAO.listarTodos();
    }
}
