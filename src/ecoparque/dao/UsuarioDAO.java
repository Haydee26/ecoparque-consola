package ecoparque.dao;

import ecoparque.exception.PersistenciaException;
import ecoparque.model.Usuario;

public interface UsuarioDAO extends GenericDAO<Usuario> {

    
    Usuario buscarPorUsuarioYClave(String nombreUsuario, String clave) throws PersistenciaException;

    void crearUsuariosInicialesSiNoExisten() throws PersistenciaException;
}
