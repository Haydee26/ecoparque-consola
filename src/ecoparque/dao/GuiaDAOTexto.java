package ecoparque.dao;

import ecoparque.exception.EntidadNoEncontradaException;
import ecoparque.exception.PersistenciaException;
import ecoparque.model.Guia;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class GuiaDAOTexto implements GuiaDAO {

    private static final String ARCHIVO = "guias.txt";

    @Override
    public void guardar(Guia guia) throws PersistenciaException {
        String linea = guia.getNombre() + ";"
                + guia.getDireccion() + ";"
                + guia.getTelefono() + ";"
                + guia.getFechaIngreso();

        ArchivoUtil.agregarLinea(ARCHIVO, linea);
    }

    @Override
    public List<Guia> listarTodos() throws PersistenciaException {
        List<Guia> guias = new ArrayList<>();

        for (String linea : ArchivoUtil.leerLineas(ARCHIVO)) {
            String[] partes = linea.split(";", -1);
            guias.add(new Guia(partes[0], partes[1], partes[2], LocalDate.parse(partes[3])));
        }

        return guias;
    }

    @Override
    public Guia buscarPorNombre(String nombre) throws PersistenciaException, EntidadNoEncontradaException {
        for (Guia guia : listarTodos()) {
            if (guia.getNombre().equalsIgnoreCase(nombre)) {
                return guia;
            }
        }
        throw new EntidadNoEncontradaException("No se encontro un guia con el nombre '" + nombre + "'.");
    }
}
