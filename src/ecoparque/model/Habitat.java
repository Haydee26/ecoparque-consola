
package ecoparque.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

public class Habitat implements Serializable {

    private static final long serialVersionUID = 1L;


    private String nombre;
    private String clima;
    private String vegetacionPredominante;
    private List<String> continentes;

    public Habitat(String nombre, String clima, String vegetacionPredominante) {
        this.nombre = nombre;
        this.clima = clima;
        this.vegetacionPredominante = vegetacionPredominante;
        this.continentes = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public String getClima() {
        return clima;
    }

    public String getVegetacionPredominante() {
        return vegetacionPredominante;
    }

    public List<String> getContinentes() {
        return continentes;
    }

    public void agregarContinente(String continente) {
        continentes.add(continente);
    }
}
