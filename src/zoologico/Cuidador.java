package zoologico;

import java.io.Serializable;
import java.util.ArrayList;


public class Cuidador extends Empleado implements Serializable {
    
    private String fechaDeIngresoEnElParque;
    private ArrayList<Especie> especies;

    //CONSTRUCTOR
    public Cuidador(String usuario, String password, String nombre, String direccion, int telefono, String fechaDeIngresoAlZoo) {
        super(usuario, password, nombre, direccion, telefono);
        this.fechaDeIngresoEnElParque = fechaDeIngresoAlZoo;
        this.especies = new ArrayList<>();
    }

    //GETTER Y SETTER
    public String getFechaDeIngresoEnElParque() {
        return fechaDeIngresoEnElParque;
    }

    public void setFechaDeIngresoEnElParque(String fechaDeIngresoAlZoo) {
        this.fechaDeIngresoEnElParque = fechaDeIngresoAlZoo;
    }
    
    public ArrayList<Especie> getEspecies() {
        return especies;
    }

    public void setEspecies(ArrayList<Especie> especies) {
        this.especies = especies;
    }
    
    //METODOS
    public void agregarEspecie(Especie e) {
        especies.add(e);
    }
    
    public void eliminarEspecie(Especie e) {
        especies.remove(e);
    }
    
    private void mostrarEspecies() {
        if (!especies.isEmpty()) {
            EntradaSalida.mostrarString("Especies del cuidador " + super.getNombre() + ":");
            int i = 1;
            for (Especie especie : especies) {
                EntradaSalida.mostrarString("\t" + i + "-" + especie.getNombre());
                i += 1;
            }
        } else {
            EntradaSalida.mostrarString("Esta especie no tiene habitat.");
        }
    }
    
   @Override
    public boolean mostrarMenu(SistemaGestionZoo sistema) {
        char opc;
        boolean seguir = true;
        do {
            opc = EntradaSalida.leerChar(
                    "OPCIONES DE CUIDADOR:\n"
                    + "[1] Ver especies\n"
                    + "[2] Salir del menu\n");
            switch (opc) {
                case '1':
                    if(!especies.isEmpty()){
                        for (Especie e : especies) {
                            e.mostrar();
                        }
                    }else {
                        EntradaSalida.mostrarString("NO SE LE ASIGNO NINGUNA ESPECIE.\n");
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
        EntradaSalida.mostrarString("Fecha de ingreso en el parque: " + this.getFechaDeIngresoEnElParque());
        mostrarEspecies();
    }

}
