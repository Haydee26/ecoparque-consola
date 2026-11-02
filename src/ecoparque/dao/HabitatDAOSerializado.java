package ecoparque.dao;

import ecoparque.model.Habitat;

public class HabitatDAOSerializado extends DAOSerializado<Habitat> implements HabitatDAO {

    public HabitatDAOSerializado() {
        super("habitats.dat");
    }
}
