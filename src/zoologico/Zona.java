package zoologico;

import java.io.Serializable;
import java.util.ArrayList;


public class Zona implements Serializable {
    
    private String nombre;
    private double extension;
    private ArrayList<Itinerario> itinerarios;
    private ArrayList<Especie> especies;

    //CONSTRUCTOR
    public Zona(String nombre, double extension) {
        this.nombre = nombre;
        this.extension = extension;
        this.itinerarios = new ArrayList<>();
        this.especies = new ArrayList<>();
    }

    //GETTER Y SETTER
    public String getNombre() {
        return nombre;
    }

    public ArrayList<Itinerario> getItinerarios() {
        return itinerarios;
    }

    public ArrayList<Especie> getEspecies() {
        return especies;
    }
    
    
    //METODOS
    public void agregarItinerario(Itinerario i) {
        itinerarios.add(i);
    }
    
    public void eliminarItinerario(Itinerario i) {
        itinerarios.remove(i);
    }
    
    public void agregarEspecie(Especie e) {
        especies.add(e);
    }
    
    public void eliminarEspecie(Especie e) {
        especies.remove(e);
    }
    
    private void mostrarItinerarios() {
        if (!itinerarios.isEmpty()) {
            EntradaSalida.mostrarString("Itinerarios que tienen la zona " + nombre + ": ");
            int i = 1;
            for (Itinerario itinerario : itinerarios) {
                EntradaSalida.mostrarString("\t" + i + "-" + itinerario.getCodigo());
                i += 1;
            }
        } else {
            EntradaSalida.mostrarString("Este zona no esta en ningun itinerario.");
        }
    }
    
    private void mostrarEspecies() {
        if (!especies.isEmpty()) {
            EntradaSalida.mostrarString("Especie que tienen la zona " + nombre + ": ");
            int i = 1;
            for (Especie especie : especies) {
                EntradaSalida.mostrarString("\t" + i + "-" + especie.getNombreCientifico());
                i += 1;
            }
        } else {
            EntradaSalida.mostrarString("Este zona no esta en ninguna especie.");
        }
    }
    
    public void mostrar() {
        EntradaSalida.mostrarString( this.getClass().getSimpleName() + ": " + nombre);
        EntradaSalida.mostrarString("Extension: " + extension);
        EntradaSalida.mostrarString("Itinerarios: " + itinerarios.size());
        mostrarItinerarios();
        EntradaSalida.mostrarString("Especies: " + especies.size());
        mostrarEspecies();
    }
}
