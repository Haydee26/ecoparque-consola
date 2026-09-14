package ecoparque.view;

import ecoparque.exception.DatosInvalidosException;
import ecoparque.util.LectorConsola;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class VistaConsola implements Vista {

    private final LectorConsola lector;

    public VistaConsola(Scanner scanner) {
        this.lector = new LectorConsola(scanner);
    }

    @Override
    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }

    @Override
    public void mostrarError(String mensaje) {
        System.out.println("Error: " + mensaje);
    }

    @Override
    public String leerTexto(String prompt) {
        return lector.leerTexto(prompt);
    }

    @Override
    public int leerEntero(String prompt) throws DatosInvalidosException {
        return lector.leerEntero(prompt);
    }

    @Override
    public double leerDecimal(String prompt) throws DatosInvalidosException {
        return lector.leerDecimal(prompt);
    }

    @Override
    public LocalDate leerFecha(String prompt) throws DatosInvalidosException {
        return lector.leerFecha(prompt);
    }

    @Override
    public LocalTime leerHora(String prompt) throws DatosInvalidosException {
        return lector.leerHora(prompt);
    }

    @Override
    public int leerOpcionDeLista(String titulo, List<String> opciones) throws DatosInvalidosException {
        if (opciones.isEmpty()) {
            throw new DatosInvalidosException("No hay opciones disponibles para '" + titulo + "'.");
        }

        System.out.println(titulo);
        for (int i = 0; i < opciones.size(); i++) {
            System.out.println((i + 1) + ". " + opciones.get(i));
        }

        int opcion = leerEntero("Opcion: ");
        if (opcion < 1 || opcion > opciones.size()) {
            throw new DatosInvalidosException("Opcion fuera de rango.");
        }

        return opcion - 1;
    }

    @Override
    public List<String> leerVariosDeLista(String titulo, List<String> opciones) throws DatosInvalidosException {
        List<String> seleccionados = new ArrayList<>();

        if (opciones.isEmpty()) {
            return seleccionados;
        }

        int cantidad = leerEntero("Cuantos desea seleccionar de '" + titulo + "'?: ");

        for (int i = 0; i < cantidad; i++) {
            int indice = leerOpcionDeLista("Seleccione " + titulo + " (" + (i + 1) + "/" + cantidad + "):", opciones);
            seleccionados.add(opciones.get(indice));
        }

        return seleccionados;
    }

    @Override
    public int mostrarMenuYLeerOpcion(String titulo, List<String> opciones) throws DatosInvalidosException {
        System.out.println(titulo);
        for (int i = 0; i < opciones.size(); i++) {
            System.out.println((i + 1) + ". " + opciones.get(i));
        }
        System.out.println("0. Salir");

        int opcion = leerEntero("Ingrese una opcion: ");
        if (opcion < 0 || opcion > opciones.size()) {
            throw new DatosInvalidosException("Opcion invalida.");
        }

        return opcion;
    }

    @Override
    public void mostrarLista(String titulo, List<String> items) {
        System.out.println(titulo);
        if (items.isEmpty()) {
            System.out.println("(no hay registros cargados)");
            return;
        }
        for (String item : items) {
            System.out.println("- " + item);
        }
    }
}
