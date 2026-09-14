package ecoparque.exception;


public class EcoparqueException extends Exception {

    public EcoparqueException(String mensaje) {
        super(mensaje);
    }

    public EcoparqueException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }
}
