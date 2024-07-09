package zoologico;

import java.io.Serializable;
import java.util.ArrayList;


public class Especie implements Serializable {
    
    private String nombre;
    private String nombreCientifico;
    private Zona zona;
    private String descripcion;
    private ArrayList<Cuidador> cuidadores;
    private ArrayList<Habitat> habitats;

    //CONSTRUCTOR
    public Especie(String nombre, String nombreCientifico, Zona zona, String descripcion) {
        this.nombre = nombre;
        this.nombreCientifico = nombreCientifico;
        this.zona = zona;
        this.descripcion = descripcion;
        this.cuidadores = new ArrayList<>();
        this.habitats = new ArrayList<>();
    }

    //GETTER Y SETTER
    public String getNombre() {
        return nombre;
    }

    public String getNombreCientifico() {
        return nombreCientifico;
    }

    public Zona getZona() {
        return zona;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public ArrayList<Cuidador> getCuidadores() {
        return cuidadores;
    }

    public ArrayList<Habitat> getHabitats() {
        return habitats;
    }

    public void setZona(Zona zona) {
        this.zona = zona;
    }
    
    
    //METODOS
    public void agregarCuidador(Cuidador c) {
        cuidadores.add(c);
    }
    
    public void eliminarCuidador(Cuidador c) {
        cuidadores.remove(c);
    }
    
    public void agregarHabitat(Habitat h) {
        habitats.add(h);
    }
    
    public void eliminarHabitat(Habitat h) {
        habitats.remove(h);
    }
    
    private void mostrarCuidadores() {
        if (!cuidadores.isEmpty()) {
            EntradaSalida.mostrarString("Cuidadores de la especie " + nombreCientifico + ":");
            int i = 1;
            for (Cuidador cuidador : cuidadores) {
                EntradaSalida.mostrarString("\t" + i + "-" + cuidador.getNombre());
                i += 1;
            }
        } else {
            EntradaSalida.mostrarString("Esta especie no tiene cuidadores.");
        }
    }
    
    private void mostrarHabitats() {
        if (!habitats.isEmpty()) {
            EntradaSalida.mostrarString("Habitat de la especie " + nombreCientifico + ":");
            int i = 1;
            for (Habitat habitat : habitats) {
                EntradaSalida.mostrarString("\t" + i + "-" + habitat.getNombre());
                i += 1;
            }
        } else {
            EntradaSalida.mostrarString("Esta especie no tiene habitat.");
        }
    }
    
    public void mostrar() {
        EntradaSalida.mostrarString( this.getClass().getSimpleName() + ": " + nombre);
        EntradaSalida.mostrarString("Nombre cientifico: " + nombreCientifico);
        if(zona==null){
            EntradaSalida.mostrarString("No tiene una zona asignada.");
        }else{
            EntradaSalida.mostrarString("Zona: " + zona.getNombre());
        }
        EntradaSalida.mostrarString("Descripcion: " + descripcion);
        EntradaSalida.mostrarString("Cuidadores: " + cuidadores.size());
        mostrarCuidadores();
        EntradaSalida.mostrarString("Habitat: " + habitats.size());
        mostrarHabitats();
    }
}
