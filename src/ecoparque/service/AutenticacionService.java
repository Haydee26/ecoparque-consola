package ecoparque.service;

import ecoparque.exception.EntidadNoEncontradaException;
import ecoparque.exception.PersistenciaException;
import ecoparque.model.Usuario;

public interface AutenticacionService {

    Usuario login(String nombreUsuario, String clave) throws EntidadNoEncontradaException, PersistenciaException;
}

