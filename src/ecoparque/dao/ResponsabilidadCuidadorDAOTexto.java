package ecoparque.dao;

import ecoparque.exception.PersistenciaException;
import ecoparque.model.Cuidador;
import ecoparque.model.Especie;
import ecoparque.model.ResponsabilidadCuidador;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ResponsabilidadCuidadorDAOTexto implements ResponsabilidadCuidadorDAO {

    private static final String ARCHIVO = "responsabilidades_cuidadores.txt";

    private final CuidadorDAO cuidadorDAO;
    private final EspecieDAO especieDAO;

    public ResponsabilidadCuidadorDAOTexto(CuidadorDAO cuidadorDAO, EspecieDAO especieDAO) {
        this.cuidadorDAO = cuidadorDAO;
        this.especieDAO = especieDAO;
    }

    @Override
    public void guardar(ResponsabilidadCuidador responsabilidad) throws PersistenciaException {
        String linea = responsabilidad.getCuidador().getNombre() + ";"
                + responsabilidad.getEspecie().getNombreEspanol() + ";"
                + responsabilidad.getFechaDesde();

        ArchivoUtil.agregarLinea(ARCHIVO, linea);
    }

    @Override
    public List<ResponsabilidadCuidador> listarTodos() throws PersistenciaException {
        List<ResponsabilidadCuidador> responsabilidades = new ArrayList<>();
        List<Cuidador> cuidadores = cuidadorDAO.listarTodos();
        List<Especie> especies = especieDAO.listarTodos();

        for (String linea : ArchivoUtil.leerLineas(ARCHIVO)) {
            String[] partes = linea.split(";", -1);

            Optional<Cuidador> cuidador = cuidadores.stream()
                    .filter(c -> c.getNombre().equalsIgnoreCase(partes[0]))
                    .findFirst();
            Optional<Especie> especie = especies.stream()
                    .filter(e -> e.getNombreEspanol().equalsIgnoreCase(partes[1]))
                    .findFirst();

            if (cuidador.isPresent() && especie.isPresent()) {
                responsabilidades.add(new ResponsabilidadCuidador(cuidador.get(), especie.get(), LocalDate.parse(partes[2])));
            }
        }

        return responsabilidades;
    }

    @Override
    public List<ResponsabilidadCuidador> listarPorCuidador(String nombreCuidador) throws PersistenciaException {
        List<ResponsabilidadCuidador> resultado = new ArrayList<>();
        for (ResponsabilidadCuidador responsabilidad : listarTodos()) {
            if (responsabilidad.getCuidador().getNombre().equalsIgnoreCase(nombreCuidador)) {
                resultado.add(responsabilidad);
            }
        }
        return resultado;
    }
}
