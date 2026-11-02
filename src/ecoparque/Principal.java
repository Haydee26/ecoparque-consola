package ecoparque;

import ecoparque.controller.Controlador;
import ecoparque.controller.ControladorPrincipal;
import ecoparque.dao.AsignacionGuiaDAO;
import ecoparque.dao.AsignacionGuiaDAOSerializado;
import ecoparque.dao.CuidadorDAO;
import ecoparque.dao.CuidadorDAOSerializado;
import ecoparque.dao.EspecieDAO;
import ecoparque.dao.EspecieDAOSerializado;
import ecoparque.dao.GuiaDAO;
import ecoparque.dao.GuiaDAOSerializado;
import ecoparque.dao.HabitatDAO;
import ecoparque.dao.HabitatDAOSerializado;
import ecoparque.dao.ItinerarioDAO;
import ecoparque.dao.ItinerarioDAOSerializado;
import ecoparque.dao.ResponsabilidadCuidadorDAO;
import ecoparque.dao.ResponsabilidadCuidadorDAOSerializado;
import ecoparque.dao.UsuarioDAO;
import ecoparque.dao.UsuarioDAOSerializado;
import ecoparque.dao.ZonaDAO;
import ecoparque.dao.ZonaDAOSerializado;
import ecoparque.exception.EcoparqueException;
import ecoparque.service.AsignacionGuiaService;
import ecoparque.service.AsignacionGuiaServiceImpl;
import ecoparque.service.AutenticacionService;
import ecoparque.service.AutenticacionServiceImpl;
import ecoparque.service.CuidadorService;
import ecoparque.service.CuidadorServiceImpl;
import ecoparque.service.EmpleadoService;
import ecoparque.service.EmpleadoServiceImpl;
import ecoparque.service.EspecieService;
import ecoparque.service.EspecieServiceImpl;
import ecoparque.service.GuiaService;
import ecoparque.service.GuiaServiceImpl;
import ecoparque.service.HabitatService;
import ecoparque.service.HabitatServiceImpl;
import ecoparque.service.ItinerarioService;
import ecoparque.service.ItinerarioServiceImpl;
import ecoparque.service.ResponsabilidadCuidadorService;
import ecoparque.service.ResponsabilidadCuidadorServiceImpl;
import ecoparque.service.ZonaService;
import ecoparque.service.ZonaServiceImpl;
import ecoparque.view.Vista;
import ecoparque.view.VistaConsola;
import java.util.Scanner;


public class Principal {

    public static void main(String[] args) {
        // --- DAO (persistencia por serializacion (archivos .dat)) ---
        ZonaDAO zonaDAO = new ZonaDAOSerializado();
        HabitatDAO habitatDAO = new HabitatDAOSerializado();
        EspecieDAO especieDAO = new EspecieDAOSerializado();
        ItinerarioDAO itinerarioDAO = new ItinerarioDAOSerializado();
        GuiaDAO guiaDAO = new GuiaDAOSerializado();
        CuidadorDAO cuidadorDAO = new CuidadorDAOSerializado();
        UsuarioDAO usuarioDAO = new UsuarioDAOSerializado();
        AsignacionGuiaDAO asignacionGuiaDAO = new AsignacionGuiaDAOSerializado();
        ResponsabilidadCuidadorDAO responsabilidadCuidadorDAO =
                new ResponsabilidadCuidadorDAOSerializado();

        // --- Service (logica de negocio, uno por responsabilidad) ---
        ZonaService zonaService = new ZonaServiceImpl(zonaDAO);
        HabitatService habitatService = new HabitatServiceImpl(habitatDAO);
        EspecieService especieService = new EspecieServiceImpl(especieDAO, zonaService, habitatService);
        ItinerarioService itinerarioService = new ItinerarioServiceImpl(itinerarioDAO, zonaService);
        GuiaService guiaService = new GuiaServiceImpl(guiaDAO);
        CuidadorService cuidadorService = new CuidadorServiceImpl(cuidadorDAO);
        EmpleadoService empleadoService = new EmpleadoServiceImpl(guiaService, cuidadorService);
        AsignacionGuiaService asignacionGuiaService =
                new AsignacionGuiaServiceImpl(asignacionGuiaDAO, guiaService, itinerarioService);
        ResponsabilidadCuidadorService responsabilidadCuidadorService =
                new ResponsabilidadCuidadorServiceImpl(responsabilidadCuidadorDAO, cuidadorService, especieService);
        AutenticacionService autenticacionService = new AutenticacionServiceImpl(usuarioDAO);

        try {
            usuarioDAO.crearUsuariosInicialesSiNoExisten();
        } catch (EcoparqueException e) {
            System.out.println("No se pudieron crear los usuarios iniciales: " + e.getMessage());
            return;
        }

        // --- Vista y Controlador ---
        Vista vista = new VistaConsola(new Scanner(System.in));

        Controlador controladorPrincipal = new ControladorPrincipal(vista, autenticacionService, zonaService,
                habitatService, especieService, itinerarioService, guiaService, cuidadorService,
                asignacionGuiaService, responsabilidadCuidadorService, empleadoService);

        controladorPrincipal.iniciar();
    }
}
