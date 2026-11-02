
package ecoparque.model;

import java.io.Serializable;

public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;


    private String nombreUsuario;
    private String clave;
    private RolUsuario rol;
    private String nombrePersona;

    public Usuario(String nombreUsuario, String clave, RolUsuario rol, String nombrePersona) {
        this.nombreUsuario = nombreUsuario;
        this.clave = clave;
        this.rol = rol;
        this.nombrePersona = nombrePersona;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public String getClave() {
        return clave;
    }

    public RolUsuario getRol() {
        return rol;
    }

    public String getNombrePersona() {
        return nombrePersona;
    }

    public boolean validarClave(String claveIngresada) {
        return clave.equals(claveIngresada);
    }
}
