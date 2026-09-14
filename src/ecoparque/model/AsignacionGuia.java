
package ecoparque.model;


import java.time.LocalTime;

public class AsignacionGuia {

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
