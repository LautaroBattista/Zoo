package zoologico;

import java.io.Serializable;
import java.util.ArrayList;


public class Habitat implements Serializable {
    
    private String nombre;
    private String clima;
    private ArrayList<Especie> especies;

    //CONSTRUCTOR
    public Habitat(String nombre, String clima) {
        this.nombre = nombre;
        this.clima = clima;
        this.especies = new ArrayList<>();    
    }

    //GETTER Y SETTER
    public String getNombre() {
        return nombre;
    }

    public String getClima() {
        return clima;
    }

    public ArrayList<Especie> getEspecies() {
        return especies;
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
            EntradaSalida.mostrarString("Especies del habitat: " + nombre );
            int i = 1;
            for (Especie especie : especies) {
                EntradaSalida.mostrarString("\t" + i + "-" + especie.getNombre());
                i += 1;
            }
        } else {
            EntradaSalida.mostrarString("Este habitat no tiene especies.");
        }
    }
    
    public void mostrar() {
        EntradaSalida.mostrarString( this.getClass().getSimpleName() + ": " + nombre);
        EntradaSalida.mostrarString( "Clima: " + clima);
        mostrarEspecies();
    }
}
