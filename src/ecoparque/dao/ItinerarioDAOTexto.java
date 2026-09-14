package ecoparque.dao;

import ecoparque.exception.PersistenciaException;
import ecoparque.model.Itinerario;
import ecoparque.model.Zona;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ItinerarioDAOTexto implements ItinerarioDAO {

    private static final String ARCHIVO = "itinerarios.txt";

    private final ZonaDAO zonaDAO;

    public ItinerarioDAOTexto(ZonaDAO zonaDAO) {
        this.zonaDAO = zonaDAO;
    }

    @Override
    public void guardar(Itinerario itinerario) throws PersistenciaException {
        List<String> nombresZonas = new ArrayList<>();
        for (Zona zona : itinerario.getZonas()) {
            nombresZonas.add(zona.getNombre());
        }

        String linea = itinerario.getCodigo() + ";"
                + itinerario.getDuracionMinutos() + ";"
                + itinerario.getLongitudMetros() + ";"
                + itinerario.getMaximoVisitantes() + ";"
                + itinerario.getCantidadEspecies() + ";"
                + String.join(",", nombresZonas);

        ArchivoUtil.agregarLinea(ARCHIVO, linea);
    }

    @Override
    public List<Itinerario> listarTodos() throws PersistenciaException {
        List<Itinerario> itinerarios = new ArrayList<>();
        List<Zona> zonasDisponibles = zonaDAO.listarTodos();

        for (String linea : ArchivoUtil.leerLineas(ARCHIVO)) {
            String[] partes = linea.split(";", -1);

            Itinerario itinerario = new Itinerario(
                    partes[0],
                    Integer.parseInt(partes[1]),
                    Double.parseDouble(partes[2]),
                    Integer.parseInt(partes[3]),
                    Integer.parseInt(partes[4])
            );

            if (partes.length > 5 && !partes[5].trim().isEmpty()) {
                for (String nombreZona : partes[5].split(",")) {
                    buscarZonaOpcional(zonasDisponibles, nombreZona)
                            .ifPresent(itinerario::agregarZona);
                }
            }

            itinerarios.add(itinerario);
        }

        return itinerarios;
    }

    private Optional<Zona> buscarZonaOpcional(List<Zona> zonas, String nombre) {
        return zonas.stream()
                .filter(z -> z.getNombre().equalsIgnoreCase(nombre))
                .findFirst();
    }
}
