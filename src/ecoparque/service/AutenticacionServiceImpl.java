package ecoparque.service;

import ecoparque.dao.UsuarioDAO;
import ecoparque.exception.EntidadNoEncontradaException;
import ecoparque.exception.PersistenciaException;
import ecoparque.model.Usuario;

public class AutenticacionServiceImpl implements AutenticacionService {

    private final UsuarioDAO usuarioDAO;

    public AutenticacionServiceImpl(UsuarioDAO usuarioDAO) {
        this.usuarioDAO = usuarioDAO;
    }

    @Override
    public Usuario login(String nombreUsuario, String clave) throws EntidadNoEncontradaException, PersistenciaException {
        Usuario usuario = usuarioDAO.buscarPorUsuarioYClave(nombreUsuario, clave);

        if (usuario == null) {
            throw new EntidadNoEncontradaException("Usuario o clave incorrectos.");
        }

        return usuario;
    }
}
