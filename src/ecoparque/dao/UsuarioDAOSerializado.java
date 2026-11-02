package ecoparque.dao;

import ecoparque.exception.PersistenciaException;
import ecoparque.model.RolUsuario;
import ecoparque.model.Usuario;

public class UsuarioDAOSerializado extends DAOSerializado<Usuario> implements UsuarioDAO {

    public UsuarioDAOSerializado() {
        super("usuarios.dat");
    }

    @Override
    public Usuario buscarPorUsuarioYClave(String nombreUsuario, String clave) throws PersistenciaException {
        for (Usuario usuario : listarTodos()) {
            if (usuario.getNombreUsuario().equals(nombreUsuario) && usuario.validarClave(clave)) {
                return usuario;
            }
        }
        return null;
    }

    @Override
    public void crearUsuariosInicialesSiNoExisten() throws PersistenciaException {
        if (listarTodos().isEmpty()) {
            guardar(new Usuario("admin", "1234", RolUsuario.ADMINISTRADOR, ""));
            guardar(new Usuario("laura", "1111", RolUsuario.GUIA, "Laura Perez"));
            guardar(new Usuario("mario", "2222", RolUsuario.CUIDADOR, "Mario Gomez"));
        }
    }
}
