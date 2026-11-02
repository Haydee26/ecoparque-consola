package ecoparque.dao;

import ecoparque.model.Itinerario;

public class ItinerarioDAOSerializado extends DAOSerializado<Itinerario> implements ItinerarioDAO {

    public ItinerarioDAOSerializado() {
        super("itinerarios.dat");
    }
}
