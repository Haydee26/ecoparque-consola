package ecoparque.dao;

import ecoparque.exception.PersistenciaException;
import ecoparque.model.RolUsuario;
import ecoparque.model.Usuario;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAOTexto implements UsuarioDAO {

    private static final String ARCHIVO = "usuarios.txt";

    @Override
    public void guardar(Usuario usuario) throws PersistenciaException {
        String linea = usuario.getNombreUsuario() + ";"
                + usuario.getClave() + ";"
                + usuario.getRol() + ";"
                + usuario.getNombrePersona();

        ArchivoUtil.agregarLinea(ARCHIVO, linea);
    }

    @Override
    public List<Usuario> listarTodos() throws PersistenciaException {
        List<Usuario> usuarios = new ArrayList<>();

        for (String linea : ArchivoUtil.leerLineas(ARCHIVO)) {
            String[] partes = linea.split(";", -1);
            usuarios.add(new Usuario(
                    partes[0].trim(),
                    partes[1].trim(),
                    RolUsuario.valueOf(partes[2].trim()),
                    partes[3].trim()
            ));
        }

        return usuarios;
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
