package ecoparque.service;

import ecoparque.dao.EspecieDAO;
import ecoparque.exception.DatosInvalidosException;
import ecoparque.exception.EntidadNoEncontradaException;
import ecoparque.exception.PersistenciaException;
import ecoparque.model.Especie;
import ecoparque.model.Habitat;
import ecoparque.model.Zona;
import java.util.List;


public class EspecieServiceImpl implements EspecieService {

    private final EspecieDAO especieDAO;
    private final ZonaService zonaService;
    private final HabitatService habitatService;

    public EspecieServiceImpl(EspecieDAO especieDAO, ZonaService zonaService, HabitatService habitatService) {
        this.especieDAO = especieDAO;
        this.zonaService = zonaService;
        this.habitatService = habitatService;
    }

    @Override
    public Especie crearEspecie(String nombreEspanol, String nombreCientifico, String descripcion,
            String nombreZona, List<String> nombresHabitats)
            throws DatosInvalidosException, EntidadNoEncontradaException, PersistenciaException {

        if (nombreEspanol == null || nombreEspanol.trim().isEmpty()) {
            throw new DatosInvalidosException("El nombre de la especie no puede estar vacio.");
        }

        Zona zona = zonaService.listarZonas().stream()
                .filter(z -> z.getNombre().equalsIgnoreCase(nombreZona))
                .findFirst()
                .orElseThrow(() -> new EntidadNoEncontradaException("No existe la zona '" + nombreZona + "'."));

        Especie especie = new Especie(nombreEspanol.trim(), nombreCientifico, descripcion, zona);

        List<Habitat> disponibles = habitatService.listarHabitats();
        for (String nombreHabitat : nombresHabitats) {
            Habitat habitat = disponibles.stream()
                    .filter(h -> h.getNombre().equalsIgnoreCase(nombreHabitat))
                    .findFirst()
                    .orElseThrow(() -> new EntidadNoEncontradaException("No existe el habitat '" + nombreHabitat + "'."));
            especie.agregarHabitat(habitat);
        }

        especieDAO.guardar(especie);
        return especie;
    }

    @Override
    public List<Especie> listarEspecies() throws PersistenciaException {
        return especieDAO.listarTodos();
    }
}

