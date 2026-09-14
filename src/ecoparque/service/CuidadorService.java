package ecoparque.service;

import ecoparque.exception.DatosInvalidosException;
import ecoparque.exception.EntidadNoEncontradaException;
import ecoparque.exception.PersistenciaException;
import ecoparque.model.Cuidador;
import java.time.LocalDate;
import java.util.List;

public interface CuidadorService {

    Cuidador crearCuidador(String nombre, String direccion, String telefono, LocalDate fechaIngreso)
            throws DatosInvalidosException, PersistenciaException;

    List<Cuidador> listarCuidadores() throws PersistenciaException;

    Cuidador buscarPorNombre(String nombre) throws EntidadNoEncontradaException, PersistenciaException;
}
