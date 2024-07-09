package zoologico;

import java.io.Serializable;
import java.util.ArrayList;

public class Itinerario implements Serializable {

    private int codigo;
    private int duracionRecorrido;
    private double longitud;
    private int maxVisitantes;
    private int cantEspecies;
    private ArrayList<Guia> guias;
    private ArrayList<Zona> zonas;

    //CONSTRUCTOR
    public Itinerario(int codigo, int duracionRecorrido, double longitud, int maxVisitantes, int cantEspecies) {
        this.codigo = codigo;
        this.duracionRecorrido = duracionRecorrido;
        this.longitud = longitud;
        this.maxVisitantes = maxVisitantes;
        this.cantEspecies = cantEspecies;
        this.guias = new ArrayList<>();
        this.zonas = new ArrayList<>();
    }

    //GETTER Y SETTER
    public int getCodigo() {
        return codigo;
    }

    public int getDuracionRecorrido() {
        return duracionRecorrido;
    }

    public ArrayList<Guia> getGuias() {
        return guias;
    }

    public ArrayList<Zona> getZonas() {
        return zonas;
    }

    //METODOS
    private void mostrarGuias() {
        if (!guias.isEmpty()) {
            EntradaSalida.mostrarString("Guias del itinerario " + codigo + ":");
            int i = 1;
            for (Guia guia : guias) {
                EntradaSalida.mostrarString("\t" + i + "-" + guia.getNombre());
                i += 1;
            }
        } else {
            EntradaSalida.mostrarString("Este itinerario no tiene guias.");
        }
    }

    private void mostrarZonas() {
        if (!zonas.isEmpty()) {
            EntradaSalida.mostrarString("Zonas del itinerario " + codigo + ":");
            int i = 1;
            for (Zona zona : zonas) {
                EntradaSalida.mostrarString("\t" + i + "-" + zona.getNombre());
                i += 1;
            }
        } else {
            EntradaSalida.mostrarString("Este itinerario no tiene zonas.");
        }
    }

    public void agregarGuia(Guia g) {
        guias.add(g);
    }

    public void eliminarGuia(Guia g) {
        guias.remove(g);
    }

    public void agregarZona(Zona z) {
        zonas.add(z);
    }

    public void eliminarZona(Zona z) {
        zonas.remove(z);
    }

    public void mostrar() {
        EntradaSalida.mostrarString("**********************************************");
        EntradaSalida.mostrarString(this.getClass().getSimpleName() + " - Codigo: " + codigo);
        EntradaSalida.mostrarString("Duracion del recorrido: " + duracionRecorrido);
        EntradaSalida.mostrarString("longitud: " + longitud);
        EntradaSalida.mostrarString("Maximo de visitantes: " + maxVisitantes);
        EntradaSalida.mostrarString("Cantidad de especies: " + cantEspecies);
        EntradaSalida.mostrarString("Guias: " + guias.size());
        mostrarGuias();
        System.out.println("Zonas: " + zonas.size());
        mostrarZonas();
    }

}
