package ecoparque.dao;

import ecoparque.exception.PersistenciaException;
import ecoparque.model.AsignacionGuia;
import ecoparque.model.Guia;
import ecoparque.model.Itinerario;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AsignacionGuiaDAOTexto implements AsignacionGuiaDAO {

    private static final String ARCHIVO = "asignaciones_guias.txt";

    private final GuiaDAO guiaDAO;
    private final ItinerarioDAO itinerarioDAO;

    public AsignacionGuiaDAOTexto(GuiaDAO guiaDAO, ItinerarioDAO itinerarioDAO) {
        this.guiaDAO = guiaDAO;
        this.itinerarioDAO = itinerarioDAO;
    }

    @Override
    public void guardar(AsignacionGuia asignacion) throws PersistenciaException {
        String linea = asignacion.getGuia().getNombre() + ";"
                + asignacion.getItinerario().getCodigo() + ";"
                + asignacion.getHora();

        ArchivoUtil.agregarLinea(ARCHIVO, linea);
    }

    @Override
    public List<AsignacionGuia> listarTodos() throws PersistenciaException {
        List<AsignacionGuia> asignaciones = new ArrayList<>();
        List<Guia> guias = guiaDAO.listarTodos();
        List<Itinerario> itinerarios = itinerarioDAO.listarTodos();

        for (String linea : ArchivoUtil.leerLineas(ARCHIVO)) {
            String[] partes = linea.split(";", -1);

            Optional<Guia> guia = guias.stream()
                    .filter(g -> g.getNombre().equalsIgnoreCase(partes[0]))
                    .findFirst();
            Optional<Itinerario> itinerario = itinerarios.stream()
                    .filter(i -> i.getCodigo().equalsIgnoreCase(partes[1]))
                    .findFirst();

            if (guia.isPresent() && itinerario.isPresent()) {
                asignaciones.add(new AsignacionGuia(guia.get(), itinerario.get(), LocalTime.parse(partes[2])));
            }
        }

        return asignaciones;
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
