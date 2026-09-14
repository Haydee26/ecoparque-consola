package ecoparque.view;

import ecoparque.exception.DatosInvalidosException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;


public interface Vista {

    void mostrarMensaje(String mensaje);

    void mostrarError(String mensaje);

    String leerTexto(String prompt);

    int leerEntero(String prompt) throws DatosInvalidosException;

    double leerDecimal(String prompt) throws DatosInvalidosException;

    LocalDate leerFecha(String prompt) throws DatosInvalidosException;

    LocalTime leerHora(String prompt) throws DatosInvalidosException;

    /**
     * Muestra una lista numerada de opciones y devuelve el indice (0-based)
     * elegido por el usuario, validando que este dentro de rango.
     */
    int leerOpcionDeLista(String titulo, List<String> opciones) throws DatosInvalidosException;

    /**
     * Igual que leerOpcionDeLista, pero permite elegir varios items
     * (por ejemplo, los habitats de una especie).
     */
    List<String> leerVariosDeLista(String titulo, List<String> opciones) throws DatosInvalidosException;

    void mostrarLista(String titulo, List<String> items);

    /**
     * Muestra un menu numerado (1..N) con una opcion "0. Salir" agregada
     * automaticamente, y devuelve la opcion elegida (0 = salir).
     */
    int mostrarMenuYLeerOpcion(String titulo, List<String> opciones) throws DatosInvalidosException;
}
