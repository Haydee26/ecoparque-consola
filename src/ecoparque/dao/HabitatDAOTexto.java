package ecoparque.dao;

import ecoparque.exception.PersistenciaException;
import ecoparque.model.Habitat;
import java.util.ArrayList;
import java.util.List;

public class HabitatDAOTexto implements HabitatDAO {

    private static final String ARCHIVO = "habitats.txt";

    @Override
    public void guardar(Habitat habitat) throws PersistenciaException {
        String continentes = String.join(",", habitat.getContinentes());

        String linea = habitat.getNombre() + ";"
                + habitat.getClima() + ";"
                + habitat.getVegetacionPredominante() + ";"
                + continentes;

        ArchivoUtil.agregarLinea(ARCHIVO, linea);
    }

    @Override
    public List<Habitat> listarTodos() throws PersistenciaException {
        List<Habitat> habitats = new ArrayList<>();

        for (String linea : ArchivoUtil.leerLineas(ARCHIVO)) {
            String[] partes = linea.split(";", -1);

            Habitat habitat = new Habitat(partes[0], partes[1], partes[2]);

            if (partes.length > 3 && !partes[3].trim().isEmpty()) {
                for (String continente : partes[3].split(",")) {
                    habitat.agregarContinente(continente);
                }
            }

            habitats.add(habitat);
        }

        return habitats;
    }
}
