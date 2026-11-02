
package ecoparque.model;

import java.io.Serializable;

import java.time.LocalDate;

public abstract class Persona implements Serializable {

    private static final long serialVersionUID = 1L;


    private String nombre;
    private String direccion;
    private String telefono;
    private LocalDate fechaIngreso;

    public Persona(String nombre, String direccion, String telefono, LocalDate fechaIngreso) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.fechaIngreso = fechaIngreso;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public LocalDate getFechaIngreso() {
        return fechaIngreso;
    }


    public abstract String getTipo();
}

    
