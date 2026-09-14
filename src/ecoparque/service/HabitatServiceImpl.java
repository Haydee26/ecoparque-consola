package ecoparque.service;

import ecoparque.dao.HabitatDAO;
import ecoparque.exception.DatosInvalidosException;
import ecoparque.exception.PersistenciaException;
import ecoparque.model.Habitat;
import java.util.List;

public class HabitatServiceImpl implements HabitatService {

    private final HabitatDAO habitatDAO;

    public HabitatServiceImpl(HabitatDAO habitatDAO) {
        this.habitatDAO = habitatDAO;
    }

    @Override
    public Habitat crearHabitat(String nombre, String clima, String vegetacion, List<String> continentes)
            throws DatosInvalidosException, PersistenciaException {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new DatosInvalidosException("El nombre del habitat no puede estar vacio.");
        }

        Habitat habitat = new Habitat(nombre.trim(), clima, vegetacion);
        for (String continente : continentes) {
            habitat.agregarContinente(continente);
        }

        habitatDAO.guardar(habitat);
        return habitat;
    }

    @Override
    public List<Habitat> listarHabitats() throws PersistenciaException {
        return habitatDAO.listarTodos();
    }
}
