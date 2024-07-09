package zoologico;

import java.io.Serializable;


public abstract class Empleado extends Persona implements Serializable {
    
    private String nombre;
    private String direccion;
    private int telefono;

    //CONSTRUCTOR
    public Empleado(String usuario, String password, String nombre, String direccion, int telefono) {
        super(usuario, password);
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
    }

    //Getter y Setter
    public String getNombre() {
        return nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public int getTelefono() {
        return telefono;
    }
}
