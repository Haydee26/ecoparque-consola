package ecoparque.service;

import ecoparque.exception.PersistenciaException;
import ecoparque.model.Persona;
import java.util.ArrayList;
import java.util.List;

public class EmpleadoServiceImpl implements EmpleadoService {

    private final GuiaService guiaService;
    private final CuidadorService cuidadorService;

    public EmpleadoServiceImpl(GuiaService guiaService, CuidadorService cuidadorService) {
        this.guiaService = guiaService;
        this.cuidadorService = cuidadorService;
    }

    @Override
    public List<Persona> listarPorAntiguedad() throws PersistenciaException {
        List<Persona> empleados = new ArrayList<>();
        empleados.addAll(guiaService.listarGuias());
        empleados.addAll(cuidadorService.listarCuidadores());
        empleados.sort((p1, p2) -> p1.getFechaIngreso().compareTo(p2.getFechaIngreso()));
        return empleados;
    }
}

