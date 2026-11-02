package ecoparque.dao;

import ecoparque.model.Especie;

public class EspecieDAOSerializado extends DAOSerializado<Especie> implements EspecieDAO {

    public EspecieDAOSerializado() {
        super("especies.dat");
    }
}
