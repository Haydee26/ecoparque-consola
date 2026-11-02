
package ecoparque.model;

import java.io.Serializable;
import java.time.LocalDate;


public class ResponsabilidadCuidador implements Serializable {

    private static final long serialVersionUID = 1L;


    private Cuidador cuidador;
    private Especie especie;
    private LocalDate fechaDesde;

    public ResponsabilidadCuidador(Cuidador cuidador, Especie especie, LocalDate fechaDesde) {
        this.cuidador = cuidador;
        this.especie = especie;
        this.fechaDesde = fechaDesde;
    }

    public Cuidador getCuidador() {
        return cuidador;
    }

    public Especie getEspecie() {
        return especie;
    }

    public LocalDate getFechaDesde() {
        return fechaDesde;
    }
}

    
