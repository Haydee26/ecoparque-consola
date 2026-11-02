
package ecoparque.model;

import java.io.Serializable;


public class Zona implements Serializable {

    private static final long serialVersionUID = 1L;


    private final String nombre;
    private final double extension;

    public Zona(String nombre, double extension) {
        this.nombre = nombre;
        this.extension = extension;
    }

    public String getNombre() {
        return nombre;
    }

    public double getExtension() {
        return extension;
    }
}

