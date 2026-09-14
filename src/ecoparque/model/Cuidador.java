
package ecoparque.model;
import java.time.LocalDate;
   
public class Cuidador extends Persona {

    public Cuidador(String nombre, String direccion, String telefono, LocalDate fechaIngreso) {
        super(nombre, direccion, telefono, fechaIngreso);
    }

    @Override
    public String getTipo() {
        return "Cuidador";
    }
}

