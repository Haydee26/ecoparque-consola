package ecoparque.util;

import ecoparque.exception.DatosInvalidosException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.Scanner;


public class LectorConsola {

    private final Scanner scanner;

    public LectorConsola(Scanner scanner) {
        this.scanner = scanner;
    }

    public String leerTexto(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    public int leerEntero(String prompt) throws DatosInvalidosException {
        String texto = leerTexto(prompt);
        try {
            return Integer.parseInt(texto);
        } catch (NumberFormatException e) {
            throw new DatosInvalidosException("'" + texto + "' no es un numero entero valido.", e);
        }
    }

    public double leerDecimal(String prompt) throws DatosInvalidosException {
        String texto = leerTexto(prompt);
        try {
            return Double.parseDouble(texto);
        } catch (NumberFormatException e) {
            throw new DatosInvalidosException("'" + texto + "' no es un numero valido.", e);
        }
    }

    public LocalDate leerFecha(String prompt) throws DatosInvalidosException {
        String texto = leerTexto(prompt + " (AAAA-MM-DD): ");
        try {
            return LocalDate.parse(texto);
        } catch (DateTimeParseException e) {
            throw new DatosInvalidosException("'" + texto + "' no es una fecha valida (use AAAA-MM-DD).", e);
        }
    }

    public LocalTime leerHora(String prompt) throws DatosInvalidosException {
        String texto = leerTexto(prompt + " (HH:MM): ");
        try {
            return LocalTime.parse(texto);
        } catch (DateTimeParseException e) {
            throw new DatosInvalidosException("'" + texto + "' no es una hora valida (use HH:MM).", e);
        }
    }
}
