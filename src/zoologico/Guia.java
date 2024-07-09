package zoologico;

import java.io.Serializable;
import java.util.ArrayList;


public class Guia extends Empleado implements Serializable {
    
    private String fechaDeInicioDeTrabajoAlZoo;
    private ArrayList<Itinerario> itinerarios;

    //CONSTRUCTOR
    public Guia(String usuario, String password, String nombre, String direccion, int telefono, String fechaDeInicioDeTrabajoAlZoo) {
        super(usuario, password, nombre, direccion, telefono);
        this.fechaDeInicioDeTrabajoAlZoo = fechaDeInicioDeTrabajoAlZoo;
        this.itinerarios = new ArrayList<>();
    }

    //GETTER Y SETTER
    public String getFechaDeInicioDeTrabajoAlZoo() {
        return fechaDeInicioDeTrabajoAlZoo;
    }

    public void setFechaDeInicioDeTrabajoAlZoo(String fechaDeInicioDeTrabajoAlZoo) {
        this.fechaDeInicioDeTrabajoAlZoo = fechaDeInicioDeTrabajoAlZoo;
    }

    public ArrayList<Itinerario> getItinerarios() {
        return itinerarios;
    }

    public void setItinerarios(ArrayList<Itinerario> itinerarios) {
        this.itinerarios = itinerarios;
    }

    //Metodos
    public void agregarItinerario(Itinerario i) {
        itinerarios.add(i);
    }
    
    public void eliminarItinerario(Itinerario i) {
        itinerarios.remove(i);
    }
    
    private void mostrarItinerarios() {
        if (!itinerarios.isEmpty()) {
            EntradaSalida.mostrarString("Itinerarios del guia " + super.getNombre() + ": ");
            int i = 1;
            for (Itinerario itinerario : itinerarios) {
                EntradaSalida.mostrarString("\t" + i + "-" + itinerario.getCodigo());
                i += 1;
            }
        } else {
            EntradaSalida.mostrarString("Este guia no tiene itinerarios.");
        }
    }
    
    @Override
    public boolean mostrarMenu(SistemaGestionZoo sistema) {
        char opc;
        boolean seguir = true;
        do {
            opc = EntradaSalida.leerChar(
                    "OPCIONES DE GUIA:\n"
                    + "[1] Ver itinerarios\n"
                    + "[2] Salir del menu\n");
            switch (opc) {
                case '1':
                    if(!itinerarios.isEmpty()){
                        for (Itinerario i : itinerarios) {
                            i.mostrar();
                        }
                    }else {
                        EntradaSalida.mostrarString("NO SE LE ASIGNO NINGUN ITINERARIO.\n");
                    }
                    break;
                case '2':
                    seguir = true;
                    break;
            }
            EntradaSalida.mostrarString("");
        } while (opc != '2');
        return seguir;
    }
    
    @Override
    public void mostrar() {
        EntradaSalida.mostrarString( this.getClass().getSimpleName() + " - Usuario: " + super.getUsuario());
        EntradaSalida.mostrarString("Password: " + super.getPassword());
        EntradaSalida.mostrarString("Nombre: " + super.getNombre());
        EntradaSalida.mostrarString("Direccion: " + super.getDireccion());
        EntradaSalida.mostrarString("Telefono: " + super.getTelefono());
        EntradaSalida.mostrarString("Fecha de inicio de trabajo al zoo: " + this.getFechaDeInicioDeTrabajoAlZoo());
        mostrarItinerarios();
    }
}
