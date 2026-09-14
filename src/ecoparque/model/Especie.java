
package ecoparque.model;

import java.util.ArrayList;
import java.util.List;

public class Especie {

    private String nombreEspanol;
    private String nombreCientifico;
    private String descripcion;
    private Zona zona;
    private List<Habitat> habitats;

    public Especie(String nombreEspanol, String nombreCientifico, String descripcion, Zona zona) {
        this.nombreEspanol = nombreEspanol;
        this.nombreCientifico = nombreCientifico;
        this.descripcion = descripcion;
        this.zona = zona;
        this.habitats = new ArrayList<>();
    }

    public String getNombreEspanol() {
        return nombreEspanol;
    }

    public String getNombreCientifico() {
        return nombreCientifico;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Zona getZona() {
        return zona;
    }

    public List<Habitat> getHabitats() {
        return habitats;
    }

    public void agregarHabitat(Habitat habitat) {
        habitats.add(habitat);
    }
}
