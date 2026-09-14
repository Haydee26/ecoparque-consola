package ecoparque.dao;

import ecoparque.exception.PersistenciaException;
import ecoparque.model.Zona;
import java.util.ArrayList;
import java.util.List;

public class ZonaDAOTexto implements ZonaDAO {

    private static final String ARCHIVO = "zonas.txt";

    @Override
    public void guardar(Zona zona) throws PersistenciaException {
        String linea = zona.getNombre() + ";" + zona.getExtension();
        ArchivoUtil.agregarLinea(ARCHIVO, linea);
    }

    @Override
    public List<Zona> listarTodos() throws PersistenciaException {
        List<Zona> zonas = new ArrayList<>();

        for (String linea : ArchivoUtil.leerLineas(ARCHIVO)) {
            String[] partes = linea.split(";");
            zonas.add(new Zona(partes[0], Double.parseDouble(partes[1])));
        }

        return zonas;
    }
}
