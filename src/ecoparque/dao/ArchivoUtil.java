package ecoparque.dao;

import ecoparque.exception.PersistenciaException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


final class ArchivoUtil {

    private ArchivoUtil() {
    }

    static List<String> leerLineas(String archivo) throws PersistenciaException {
        List<String> lineas = new ArrayList<>();

        if (!Files.exists(Paths.get(archivo))) {
            return lineas;
        }

        try (BufferedReader lector = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = lector.readLine()) != null) {
                if (!linea.trim().isEmpty()) {
                    lineas.add(linea);
                }
            }
        } catch (IOException e) {
            throw new PersistenciaException("Error al leer el archivo " + archivo, e);
        }

        return lineas;
    }

    static void agregarLinea(String archivo, String linea) throws PersistenciaException {
        try (FileWriter escritor = new FileWriter(archivo, true)) {
            escritor.write(linea + "\n");
        } catch (IOException e) {
            throw new PersistenciaException("Error al escribir en el archivo " + archivo, e);
        }
    }
}
