
package ecoparque.model;
import java.time.LocalDate;

public class Guia extends Persona {

    public Guia(String nombre, String direccion, String telefono, LocalDate fechaIngreso) {
        super(nombre, direccion, telefono, fechaIngreso);
    }

    @Override
    public String getTipo() {
        return "Guia";
    }
}
