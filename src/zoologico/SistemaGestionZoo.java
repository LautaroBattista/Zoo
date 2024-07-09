package zoologico;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class SistemaGestionZoo implements Serializable {

    private ArrayList<Persona> personas;
    private ArrayList<Itinerario> itinerarios;
    private ArrayList<Especie> especies;
    private ArrayList<Habitat> habitats;
    private ArrayList<Zona> zonas;

    //CONSTRUCTOR
    public SistemaGestionZoo() {
        this.personas = new ArrayList<>();
        this.itinerarios = new ArrayList<>();
        this.especies = new ArrayList<>();
        this.habitats = new ArrayList<>();
        this.zonas = new ArrayList<>();
    }

    //GETTER Y SETTER
    public ArrayList<Persona> getPersonas() {
        return personas;
    }

    public ArrayList<Itinerario> getItinerarios() {
        return itinerarios;
    }

    public ArrayList<Especie> getEspecies() {
        return especies;
    }

    public ArrayList<Habitat> getHabitats() {
        return habitats;
    }

    public ArrayList<Zona> getZonas() {
        return zonas;
    }

    public ArrayList<Guia> getGuias() {
        ArrayList<Guia> guias = new ArrayList<>();
        for (Persona p : personas) {
            if (p instanceof Guia guia) {
                guias.add(guia);
            }
        }
        return guias;
    }

    public ArrayList<Cuidador> getCuidadores() {
        ArrayList<Cuidador> cuidadores = new ArrayList<>();
        for (Persona p : personas) {
            if (p instanceof Cuidador cuidadore) {
                cuidadores.add(cuidadore);
            }
        }
        return cuidadores;
    }

    //METODOS
    public SistemaGestionZoo deSerializar(String a) throws IOException, ClassNotFoundException {
        SistemaGestionZoo s;
        try (FileInputStream f = new FileInputStream(a); ObjectInputStream o = new ObjectInputStream(f)) {
            s = (SistemaGestionZoo) o.readObject();
        }
        return s;
    }

    public void serializar(String a) throws IOException {
        try (FileOutputStream f = new FileOutputStream(a); ObjectOutputStream o = new ObjectOutputStream(f)) {
            o.writeObject(this);
        }
    }

    public void agregarGuia(Guia guia) {
        personas.add(guia);
    }

    public void agregarCuidador(Cuidador cuidador) {
        personas.add(cuidador);
    }

    public void agregarEpecie(Especie especie) {
        especies.add(especie);
    }

    public void agregarItinerario(Itinerario itinerario) {
        itinerarios.add(itinerario);
    }

    public void agregarHabitat(Habitat habitat) {
        habitats.add(habitat);
    }

    public void agregarZona(Zona zona) {
        zonas.add(zona);
    }

    public Persona buscarPersona(String datos) {
        int i = 0;
        boolean encontrado = false;
        Persona p = null;
        while (i < personas.size() && !encontrado) {
            p = personas.get(i);
            if (datos.equals(p.getUsuario() + ":" + p.getPassword())) {
                encontrado = true;
            } else {
                i++;
            }
        }
        if (!encontrado) {
            return null;
        } else {
            return p;
        }
    }
    
    public void eliminarCuidador(Persona persona) {
        ArrayList<Cuidador> cuidadores = getCuidadores();
        for (Cuidador c : cuidadores) {
            if (c.equals(persona)) {
                ArrayList<Especie> especiesPersona = c.getEspecies();
                for (Especie ep : especiesPersona) {
                    ep.eliminarCuidador(c);
                }
            }
        }
        personas.remove(persona);
    }
    
    public void eliminarGuia(Persona persona) {
        ArrayList<Guia> guias = getGuias();
        for (Guia g : guias) {
            if (g.equals(persona)) {
                ArrayList<Itinerario> itinerariosPersona = g.getItinerarios();
                for (Itinerario ip : itinerariosPersona) {
                    ip.eliminarGuia(g);
                }
            }
        }
        personas.remove(persona);
    }
    
    public void eliminarZona(Zona zona) {
        ArrayList<Zona> zonas = getZonas();
        for (Zona z : zonas) {
            if (z.equals(zona)) {
                ArrayList<Itinerario> itinerariosZona = z.getItinerarios();
                for (Itinerario iz : itinerariosZona) {
                    iz.eliminarZona(z);
                }
                ArrayList<Especie> especiesZona = z.getEspecies();
                for (Especie ez : especiesZona) {
                    ez.setZona(null);
                }
            }
        }
        this.zonas.remove(zona);
    }
    
    public void eliminarHabitat(Habitat habitat) {
        ArrayList<Habitat> habitats = getHabitats();
        for (Habitat h : habitats) {
            if (h.equals(habitat)) {
                ArrayList<Especie> especiesHabitat = h.getEspecies();
                for (Especie eh : especiesHabitat) {
                    eh.eliminarHabitat(h);
                }
            }
        }
        this.habitats.remove(habitat);
    }
    
    public void eliminarItinerario(Itinerario itinerario) {
        ArrayList<Itinerario> itinerarios = getItinerarios();
        for (Itinerario i : itinerarios) {
            if (i.equals(itinerario)) {
                ArrayList<Guia> guiasItinerario = i.getGuias();
                
                for (Guia gi : guiasItinerario) {
                    gi.eliminarItinerario(i);
                }
                
                ArrayList<Zona> zonasItinerario = i.getZonas();
                
                for (Zona zi : zonasItinerario) {
                    zi.eliminarItinerario(i);
                }
            }
        }
        this.itinerarios.remove(itinerario);
    }
    
    public void eliminarEspecie(Especie especie) {
        ArrayList<Especie> especies = getEspecies();
        for (Especie e : especies) {
            if (e.equals(especie)) {
                ArrayList<Cuidador> cuidadoresEspecie = e.getCuidadores();
                for (Cuidador ce : cuidadoresEspecie) {
                    ce.eliminarEspecie(e);
                }
                ArrayList<Habitat> habitatsEspecies = e.getHabitats();
                for (Habitat he : habitatsEspecies) {
                    he.eliminarEspecie(e);
                }
                e.setZona(null);
            }
        }
        this.especies.remove(especie);
    }

    public Especie buscarEspecie(String info) {
        int i = 0;
        boolean encontrado = false;
        Especie esp = null;
        while (i < especies.size() && !encontrado) {
            esp = especies.get(i);
            if (info.equals(esp.getNombreCientifico())) {
                encontrado = true;
            } else {
                i++;
            }
        }
        if (!encontrado) {
            return null;
        } else {
            return esp;
        }
    }

    public Itinerario buscarItinerario(int info) {
        int i = 0;
        boolean encontrado = false;
        Itinerario it = null;
        while (i < itinerarios.size() && !encontrado) {
            it = itinerarios.get(i);
            if (info == it.getCodigo()) {
                encontrado = true;
            } else {
                i++;
            }
        }
        if (!encontrado) {
            return null;
        } else {
            return it;
        }
    }


    public Habitat buscarHabitat(String info) {
        int i = 0;
        boolean encontrado = false;
        Habitat h = null;
        while (i < habitats.size() && !encontrado) {
            h = habitats.get(i);
            if (info.equals(h.getNombre())) {
                encontrado = true;
            } else {
                i++;
            }
        }
        if (!encontrado) {
            return null;
        } else {
            return h;
        }
    }


    public Zona buscarZona(String info) {
        int i = 0;
        boolean encontrado = false;
        Zona z = null;
        while (i < zonas.size() && !encontrado) {
            z = zonas.get(i);
            if (info.equals(z.getNombre())) {
                encontrado = true;
            } else {
                i++;
            }
        }
        if (!encontrado) {
            return null;
        } else {
            return z;
        }
    }


    private int mostrarItinerario() {
        if (!itinerarios.isEmpty()) {
            EntradaSalida.mostrarString("\t***ITINERARIOS***");
            for (Itinerario i : itinerarios) {
                EntradaSalida.mostrarString("\t Codigo: " + i.getCodigo() + " - Duracion del recorrido: " + i.getDuracionRecorrido() + "\n");
            }
        } else {
            EntradaSalida.mostrarString("No hay ningun itinerario en el sistema.");
        }
        return itinerarios.size();
    }

    public Itinerario elegirItinerario() {
        int cant = mostrarItinerario();
        Itinerario it = null;
        if (cant != 0) {
            int num;
            boolean codigoValido = false;
            // Repetir hasta que se ingrese un código válido
            do {
                num = EntradaSalida.leerInt("Ingresa el codigo del itinerario que desees:");
                // Verificar que el código coincida con algún itinerario
                for (Itinerario itinerario : itinerarios) {
                    if (itinerario.getCodigo() == num) {
                        it = itinerario;
                        codigoValido = true;
                        break;
                    }
                }
                if (!codigoValido) {
                    EntradaSalida.mostrarString("Ese codigo de itinerario no coincide con ninguno.");
                }
            } while (!codigoValido);

            return it;
        }
        return it;
    }

    private int mostrarGuia() {
        ArrayList<Guia> guias = getGuias(); 
        if (!guias.isEmpty()) {
            EntradaSalida.mostrarString("\t***GUIAS***");
            for (Guia g : guias) {
                EntradaSalida.mostrarString("\tNombre de usuario: " + g.getUsuario() + "");
            }
        } else {
            EntradaSalida.mostrarString("No hay ningun guía en el sistema.");
        }
        return guias.size();
    }
    
    public Guia elegirGuia(ArrayList<Guia> guias) {
        int cant = mostrarGuia();
        Guia g = null;
        if (cant != 0) {
            boolean codigoValido = false;
            // Repetir hasta que se ingrese un código válido
            do {
                String nom = EntradaSalida.leerString("Ingresa el nombre de usuario del guia que deseas:");
                // Verificar que el código coincida con algún itinerario
                for (Guia guia : guias) {
                    if (guia.getUsuario().equals(nom)) {
                        g = guia;
                        codigoValido = true;
                        break;
                    }
                }
                if (!codigoValido) {
                    EntradaSalida.mostrarString("Ese guia no coincide con ninguno.");
                }
            } while (!codigoValido);

            return g;
        }
        return g;
    }

    private int mostrarZona() {
        if (!zonas.isEmpty()) {
            EntradaSalida.mostrarString("\t***ZONAS***");
            for (Zona z : zonas) {
                EntradaSalida.mostrarString("\t Nombre de zona: " + z.getNombre() + "");
            }
        } else {
            EntradaSalida.mostrarString("No hay ninguna zona en el sistema.");
        }
        return zonas.size();
    }

    public Zona elegirZona(ArrayList<Zona> zonas) {
        int cant = mostrarZona();
        Zona z = null;
        if (cant != 0) {
            String nom = EntradaSalida.leerString("Ingresa el nombre de la zona que deseas.");
            boolean found = false;
            for (Zona zona : zonas) {
                if (zona.getNombre().equalsIgnoreCase(nom)) {
                    z = zona;
                    found = true;
                    break;
                }
            }
            if (!found) {
                EntradaSalida.mostrarString("Ese nombre de la zona no coincide con ninguno.");
            }
        }
        return z;
    }

    private int mostrarEspecie() {
        if (!especies.isEmpty()) {
            EntradaSalida.mostrarString("\t***ESPECIES***");
            for (Especie e : especies) {
                EntradaSalida.mostrarString("\t Nombre: " + e.getNombre() + "");
            }
        } else {
            EntradaSalida.mostrarString("No hay ninguna especie en el sistema.");
        }
        return especies.size();
    }

    public Especie elegirEspecie() {
        int cant = mostrarEspecie();
        Especie e = null;
        if (cant != 0) {
            String nom = EntradaSalida.leerString("Ingresa el nombre de la especie que deseas: ");
            boolean validar = false;
            for (Especie especie : especies) {
                if (especie.getNombre().equalsIgnoreCase(nom)) {
                    e = especie;
                    validar = true;
                    break;
                }
            }
            if (!validar) {
                EntradaSalida.mostrarString("Ese nombre no coincide con ninguna especie.");
            }
        }
        return e;
    }

    private int mostrarCuidador() {
        ArrayList<Cuidador> cuidadores = getCuidadores();
        if (!cuidadores.isEmpty()) {
            EntradaSalida.mostrarString("\t***CUIDADORES***");
            for (Cuidador c : cuidadores) {
                EntradaSalida.mostrarString("\tNombre de usuario: " + c.getUsuario() + "");
            }
        } else {
            EntradaSalida.mostrarString("No hay ningun cuidador en el sistema.");
        }
        return cuidadores.size();
    }

    public Cuidador elegirCuidadore(ArrayList<Cuidador> cuidadores) {
        int cant = mostrarCuidador();
        Cuidador c = null;
        if (cant != 0) {
            String nom = EntradaSalida.leerString("Ingresa el nombre del cuidador que deseas: ");
            boolean found = false;
            for (Cuidador cuidador : cuidadores) {
                if (cuidador.getNombre().equalsIgnoreCase(nom)) {
                    c = cuidador;
                    found = true;
                    break;
                }
            }
            if (!found) {
                EntradaSalida.mostrarString("Ese nombre de cuidador no coincide con ninguno.");
            }
        }
        return c;
    }

    public int mostrarHabitat() {
        if (!habitats.isEmpty()) {
            EntradaSalida.mostrarString("\t***HABITATS***");
            for (Habitat h : habitats) {
                EntradaSalida.mostrarString("\t Nombre de habitat: " + h.getNombre() + "");
            }
        } else {
            EntradaSalida.mostrarString("No hay ningun habitat en el sistema.");
        }
        return habitats.size();
    }

    public Habitat elegirHabitat(ArrayList<Habitat> habitats) {
        int cant = mostrarHabitat();
        Habitat h = null;
        if (cant != 0) {
            String nom = EntradaSalida.leerString("Ingresa el nombre del habitat que deseas: ");
            boolean found = false;
            for (Habitat habitat : habitats) {
                if (habitat.getNombre().equalsIgnoreCase(nom)) {
                    h = habitat;
                    found = true;
                    break;
                }
            }
            if (!found) {
                EntradaSalida.mostrarString("Ese nombre del habitat no coincide con ninguno.");
            }
        }
        return h;
    }

    public static boolean buscarUser(String user, SistemaGestionZoo sistema) {
        boolean v = false;
        ArrayList<Persona> personas;
        personas = sistema.getPersonas();
        for (Persona p : personas) {
            if (p.getUsuario().equals(user)) {
                v = true;
            }
        }
        return v;
    }

    public static boolean buscarCodigoIti(int cod, SistemaGestionZoo sistema) {
        boolean v = false;
        ArrayList<Itinerario> itinerarios;
        itinerarios = sistema.getItinerarios();
        for (Itinerario i : itinerarios) {
            if (i.getCodigo() == cod) {
                v = true;
            }
        }
        return v;
    }

    public static boolean buscarNomCient(String nc, SistemaGestionZoo sistema) {
        boolean v = false;
        ArrayList<Especie> especies;
        especies = sistema.getEspecies();
        for (Especie e : especies) {
            if (e.getNombreCientifico().equals(nc)) {
                v = true;
            }
        }
        return v;
    }
    
    public Guia asignarGuia() {
        ArrayList<Guia> guias = getGuias();
        int cant = mostrarGuia();
        Guia g = null;
        if (cant != 0) {
            boolean codigoValido = false;
            // Repetir hasta que se ingrese un código válido
            do {
                String nom = EntradaSalida.leerString("Ingresa el nombre de usuario del guia que deseas:");
                // Verificar que el nombre coincida con algún itinerario
                for (Guia guia : guias) {
                    if (guia.getUsuario().equals(nom)) {
                        g = guia;
                        codigoValido = true;
                        break;
                    }
                }
                if (!codigoValido) {
                    EntradaSalida.mostrarString("Ese guia no coincide con ninguno.");
                }
            } while (!codigoValido);

            return g;
        }
        return g;
    }

    public Zona asignarZona() {
        ArrayList<Zona> zonas = getZonas();
        int cant = mostrarZona();
        Zona z = null;
        if (cant != 0) {
            boolean codigoValido = false;
            do {
                String nom = EntradaSalida.leerString("Ingresa el nombre de la zona que deseas: ");
                // Verificar que la zona coincida con algún itinerario
                for (Zona zona : zonas) {
                    if (zona.getNombre().equals(nom)) {
                        z = zona;
                        codigoValido = true;
                        break;
                    }
                }
                if (!codigoValido) {
                    EntradaSalida.mostrarString("Esa zona no coincide con ninguna.");
                }
            } while (!codigoValido);

            return z;
        }
        return z;
    }

    public Cuidador asignarCuidador() {
        ArrayList<Cuidador> cuidadores = getCuidadores();
        int cant = mostrarCuidador();
        Cuidador c = null;
        if (cant != 0) {
            boolean codigoValido = false;
            do {
                String nom = EntradaSalida.leerString("Ingresa el nombre de usuario del cuidador que deseas:");
                for (Cuidador cuidador : cuidadores) {
                    if (cuidador.getUsuario().equals(nom)) {
                        c = cuidador;
                        codigoValido = true;
                        break;
                    }
                }
                if (!codigoValido) {
                    EntradaSalida.mostrarString("Ese cuidador no coincide con ninguno.");
                }
            } while (!codigoValido);

            return c;
        }
        return c;
    }

    public Habitat asignarHabitat() {
        ArrayList<Habitat> habitats = getHabitats();
        int cant = mostrarHabitat();
        Habitat h = null;
        if (cant != 0) {
            boolean codigoValido = false;
            do {
                String nom = EntradaSalida.leerString("Ingresa el nombre de la zona que deseas:");
                for (Habitat habitat : habitats) {
                    if (habitat.getNombre().equals(nom)) {
                        h = habitat;
                        codigoValido = true;
                        break;
                    }
                }
                if (!codigoValido) {
                    EntradaSalida.mostrarString("Esa zona no coincide con ninguna.");
                }
            } while (!codigoValido);

            return h;
        }
        return h;
    }
}
