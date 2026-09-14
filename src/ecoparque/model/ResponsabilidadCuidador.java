
package ecoparque.model;
import java.time.LocalDate;


public class ResponsabilidadCuidador {

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

    
