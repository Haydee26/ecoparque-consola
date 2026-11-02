package ecoparque.dao;

import ecoparque.model.Zona;

public class ZonaDAOSerializado extends DAOSerializado<Zona> implements ZonaDAO {

    public ZonaDAOSerializado() {
        super("zonas.dat");
    }
}
