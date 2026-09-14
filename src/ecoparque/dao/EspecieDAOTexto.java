package ecoparque.dao;

import ecoparque.exception.PersistenciaException;
import ecoparque.model.Especie;
import ecoparque.model.Habitat;
import ecoparque.model.Zona;
import java.util.ArrayList;
import java.util.List;


public class EspecieDAOTexto implements EspecieDAO {

    private static final String ARCHIVO = "especies.txt";

    private final ZonaDAO zonaDAO;
    private final HabitatDAO habitatDAO;

    public EspecieDAOTexto(ZonaDAO zonaDAO, HabitatDAO habitatDAO) {
        this.zonaDAO = zonaDAO;
        this.habitatDAO = habitatDAO;
    }

    @Override
    public void guardar(Especie especie) throws PersistenciaException {
        List<String> nombresHabitats = new ArrayList<>();
        for (Habitat habitat : especie.getHabitats()) {
            nombresHabitats.add(habitat.getNombre());
        }

        String linea = especie.getNombreEspanol() + ";"
                + especie.getNombreCientifico() + ";"
                + especie.getDescripcion() + ";"
                + especie.getZona().getNombre() + ";"
                + String.join(",", nombresHabitats);

        ArchivoUtil.agregarLinea(ARCHIVO, linea);
    }

    @Override
    public List<Especie> listarTodos() throws PersistenciaException {
        List<Especie> especies = new ArrayList<>();
        List<Zona> zonas = zonaDAO.listarTodos();
        List<Habitat> habitatsDisponibles = habitatDAO.listarTodos();

        for (String linea : ArchivoUtil.leerLineas(ARCHIVO)) {
            String[] partes = linea.split(";", -1);

            Zona zona = buscarZona(zonas, partes[3]);

            Especie especie = new Especie(partes[0], partes[1], partes[2], zona);

            if (partes.length > 4 && !partes[4].trim().isEmpty()) {
                for (String nombreHabitat : partes[4].split(",")) {
                    buscarHabitatOpcional(habitatsDisponibles, nombreHabitat)
                            .ifPresent(especie::agregarHabitat);
                }
            }

            especies.add(especie);
        }

        return especies;
    }

    private Zona buscarZona(List<Zona> zonas, String nombre) throws PersistenciaException {
        for (Zona zona : zonas) {
            if (zona.getNombre().equalsIgnoreCase(nombre)) {
                return zona;
            }
        }
        // Una zona referenciada por especies.txt que no existe en zonas.txt
        // es una inconsistencia del archivo, no una busqueda fallida del
        // usuario: se trata como un problema de persistencia.
        throw new PersistenciaException(
                "El archivo de especies referencia la zona '" + nombre + "', que no existe en zonas.txt.", null);
    }

    private java.util.Optional<Habitat> buscarHabitatOpcional(List<Habitat> habitats, String nombre) {
        return habitats.stream()
                .filter(h -> h.getNombre().equalsIgnoreCase(nombre))
                .findFirst();
    }
}
