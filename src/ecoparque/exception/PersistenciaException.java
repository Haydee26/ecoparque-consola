package ecoparque.exception;


public class PersistenciaException extends EcoparqueException {

    public PersistenciaException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }
}
