
package ecoparque.model;


public class Zona {

    private String nombre;
    private double extension;

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

