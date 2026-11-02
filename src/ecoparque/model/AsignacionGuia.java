
package ecoparque.model;

import java.io.Serializable;


import java.time.LocalTime;

public class AsignacionGuia implements Serializable {

    private static final long serialVersionUID = 1L;


    private Guia guia;
    private Itinerario itinerario;
    private LocalTime hora;

    public AsignacionGuia(Guia guia, Itinerario itinerario, LocalTime hora) {
        this.guia = guia;
        this.itinerario = itinerario;
        this.hora = hora;
    }

    public Guia getGuia() {
        return guia;
    }

    public Itinerario getItinerario() {
        return itinerario;
    }

    public LocalTime getHora() {
        return hora;
    }
}
