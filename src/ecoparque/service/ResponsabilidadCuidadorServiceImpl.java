package ecoparque.service;

import ecoparque.dao.ResponsabilidadCuidadorDAO;
import ecoparque.exception.DatosInvalidosException;
import ecoparque.exception.EntidadNoEncontradaException;
import ecoparque.exception.PersistenciaException;
import ecoparque.model.Cuidador;
import ecoparque.model.Especie;
import ecoparque.model.ResponsabilidadCuidador;
import java.time.LocalDate;
import java.util.List;

public class ResponsabilidadCuidadorServiceImpl implements ResponsabilidadCuidadorService {

    private final ResponsabilidadCuidadorDAO responsabilidadCuidadorDAO;
    private final CuidadorService cuidadorService;
    private final EspecieService especieService;

    public ResponsabilidadCuidadorServiceImpl(ResponsabilidadCuidadorDAO responsabilidadCuidadorDAO,
            CuidadorService cuidadorService, EspecieService especieService) {
        this.responsabilidadCuidadorDAO = responsabilidadCuidadorDAO;
        this.cuidadorService = cuidadorService;
        this.especieService = especieService;
    }

    @Override
    public ResponsabilidadCuidador asignar(String nombreCuidador, String nombreEspecie, LocalDate fechaDesde)
            throws DatosInvalidosException, EntidadNoEncontradaException, PersistenciaException {

        if (fechaDesde == null) {
            throw new DatosInvalidosException("Debe indicar la fecha desde la cual se hace cargo.");
        }

        Cuidador cuidador = cuidadorService.buscarPorNombre(nombreCuidador);

        Especie especie = especieService.listarEspecies().stream()
                .filter(e -> e.getNombreEspanol().equalsIgnoreCase(nombreEspecie))
                .findFirst()
                .orElseThrow(() -> new EntidadNoEncontradaException("No existe la especie '" + nombreEspecie + "'."));

        ResponsabilidadCuidador responsabilidad = new ResponsabilidadCuidador(cuidador, especie, fechaDesde);
        responsabilidadCuidadorDAO.guardar(responsabilidad);
        return responsabilidad;
    }

    @Override
    public List<ResponsabilidadCuidador> listarPorCuidador(String nombreCuidador) throws PersistenciaException {
        return responsabilidadCuidadorDAO.listarPorCuidador(nombreCuidador);
    }
}
