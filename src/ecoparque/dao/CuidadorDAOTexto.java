package ecoparque.dao;

import ecoparque.exception.EntidadNoEncontradaException;
import ecoparque.exception.PersistenciaException;
import ecoparque.model.Cuidador;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CuidadorDAOTexto implements CuidadorDAO {

    private static final String ARCHIVO = "cuidadores.txt";

    @Override
    public void guardar(Cuidador cuidador) throws PersistenciaException {
        String linea = cuidador.getNombre() + ";"
                + cuidador.getDireccion() + ";"
                + cuidador.getTelefono() + ";"
                + cuidador.getFechaIngreso();

        ArchivoUtil.agregarLinea(ARCHIVO, linea);
    }

    @Override
    public List<Cuidador> listarTodos() throws PersistenciaException {
        List<Cuidador> cuidadores = new ArrayList<>();

        for (String linea : ArchivoUtil.leerLineas(ARCHIVO)) {
            String[] partes = linea.split(";", -1);
            cuidadores.add(new Cuidador(partes[0], partes[1], partes[2], LocalDate.parse(partes[3])));
        }

        return cuidadores;
    }

    @Override
    public Cuidador buscarPorNombre(String nombre) throws PersistenciaException, EntidadNoEncontradaException {
        for (Cuidador cuidador : listarTodos()) {
            if (cuidador.getNombre().equalsIgnoreCase(nombre)) {
                return cuidador;
            }
        }
        throw new EntidadNoEncontradaException("No se encontro un cuidador con el nombre '" + nombre + "'.");
    }
}
