package ecoparque.service;

import ecoparque.exception.DatosInvalidosException;
import ecoparque.exception.EntidadNoEncontradaException;
import ecoparque.exception.PersistenciaException;
import ecoparque.model.Guia;
import java.time.LocalDate;
import java.util.List;

public interface GuiaService {

    Guia crearGuia(String nombre, String direccion, String telefono, LocalDate fechaIngreso)
            throws DatosInvalidosException, PersistenciaException;

    List<Guia> listarGuias() throws PersistenciaException;

    Guia buscarPorNombre(String nombre) throws EntidadNoEncontradaException, PersistenciaException;
}
