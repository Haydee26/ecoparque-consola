package ecoparque.dao;

import ecoparque.exception.PersistenciaException;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Lee y graba una lista completa de objetos en un archivo binario (serializacion).
 * Es el equivalente de ArchivoUtil, pero en vez de lineas de texto guarda objetos.
 */
final class SerializacionUtil {

    private SerializacionUtil() {
    }

    @SuppressWarnings("unchecked")
    static <T> List<T> leer(String archivo) throws PersistenciaException {
        if (!Files.exists(Paths.get(archivo))) {
            return new ArrayList<>();
        }
        // ObjectInputStream decora a un BufferedInputStream (eficiencia) que decora a un FileInputStream.
        try (ObjectInputStream entrada = new ObjectInputStream(
                new BufferedInputStream(new FileInputStream(archivo)))) {
            return (List<T>) entrada.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new PersistenciaException("Error al leer el archivo " + archivo, e);
        }
    }

    static <T> void escribir(String archivo, List<T> elementos) throws PersistenciaException {
        // try-with-resources cierra el archivo: asi se garantiza que los datos se graben.
        try (ObjectOutputStream salida = new ObjectOutputStream(
                new BufferedOutputStream(new FileOutputStream(archivo)))) {
            salida.writeObject(new ArrayList<>(elementos));
        } catch (IOException e) {
            throw new PersistenciaException("Error al escribir en el archivo " + archivo, e);
        }
    }
}
