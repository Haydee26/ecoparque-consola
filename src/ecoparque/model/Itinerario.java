
package ecoparque.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Itinerario implements Serializable {

    private static final long serialVersionUID = 1L;


    private String codigo;
    private int duracionMinutos;
    private double longitudMetros;
    private int maximoVisitantes;
    private int cantidadEspecies;
    private List<Zona> zonas;

    public Itinerario(String codigo, int duracionMinutos, double longitudMetros, int maximoVisitantes,
            int cantidadEspecies) {
        this.codigo = codigo;
        this.duracionMinutos = duracionMinutos;
        this.longitudMetros = longitudMetros;
        this.maximoVisitantes = maximoVisitantes;
        this.cantidadEspecies = cantidadEspecies;
        this.zonas = new ArrayList<>();
    }

    public String getCodigo() {
        return codigo;
    }

    public int getDuracionMinutos() {
        return duracionMinutos;
    }

    public double getLongitudMetros() {
        return longitudMetros;
    }

    public int getMaximoVisitantes() {
        return maximoVisitantes;
    }

    public int getCantidadEspecies() {
        return cantidadEspecies;
    }

    public List<Zona> getZonas() {
        return zonas;
    }

    public void agregarZona(Zona zona) {
        zonas.add(zona);
    }
}
