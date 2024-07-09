package zoologico;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import static zoologico.EntradaSalida.leerString;

public class Administrador extends Persona implements Serializable {

    //CONSTRUCTOR
    public Administrador(String usuario, String password) {
        super(usuario, password);
    }

    //METODOS
    private void agregarGuia(SistemaGestionZoo sistema) {
        EntradaSalida.mostrarString("INGRESAR UN GUIA AL SISTEMA.");
        String user = EntradaSalida.leerString("Ingresar el usuario: ");
        boolean f = sistema.buscarUser(user, sistema);
        while (f) {
            user = leerString("Usuario repetido, ingrese uno distinto.");
            f = sistema.buscarUser(user, sistema);
        }
        String password = EntradaSalida.leerString("Ingresar password: ");
        String nom = EntradaSalida.leerString("Ingresar el nombre : ");
        String direccion = EntradaSalida.leerString("Ingresar la direccion: ");
        int telefono = EntradaSalida.leerInt("Ingresar numero de telefono: ");
        String fecha = EntradaSalida.leerString("Ingresar la fecha en la que comenzaron a trabajar en el zoo: ");
        Guia g = new Guia(user, password, nom, direccion, telefono, fecha);
        sistema.agregarGuia(g);
    }

    private void agregarCuidador(SistemaGestionZoo sistema) {
        EntradaSalida.mostrarString("INGRESAR UN CUIDADOR AL SISTEMA.");
        String user = EntradaSalida.leerString("Ingresar el usuario: ");
        boolean f = sistema.buscarUser(user, sistema);
        while (f) {
            user = leerString("Usuario repetido, ingrese uno distinto.");
            f = sistema.buscarUser(user, sistema);
        }
        String password = EntradaSalida.leerString("Ingresar password: ");
        String nom = EntradaSalida.leerString("Ingresar el nombre : ");
        String direccion = EntradaSalida.leerString("Ingresar la direccion: ");
        int telefono = EntradaSalida.leerInt("Ingresar numero de telefono: ");
        String fecha = EntradaSalida.leerString("Ingresar la fecha de ingreso al parque: ");
        Cuidador c = new Cuidador(user, password, nom, direccion, telefono, fecha);
        sistema.agregarCuidador(c);
    }

    private void agregarEspecie(SistemaGestionZoo sistema) {
        EntradaSalida.mostrarString("INGRESAR UNA ESPECIE AL SISTEMA.");
        String nom = EntradaSalida.leerString("Ingresar el nombre: ");
        String nomCientifico = EntradaSalida.leerString("Ingresar el nombre cientifico: ");
        //verificar si se repite el nombre cientifico.
        boolean f = sistema.buscarNomCient(nomCientifico, sistema);
        while (f) {
            nomCientifico = leerString("Esta especie ya esta en el sistema, ingrese una distinta.");
            f = sistema.buscarNomCient(nomCientifico, sistema);
        }
        Zona z = sistema.asignarZona();
        String descripcion = EntradaSalida.leerString("Ingresar una descripcion general: ");
        Especie e = new Especie(nom, nomCientifico, z, descripcion);
        Cuidador c = sistema.asignarCuidador();
        Habitat h = sistema.asignarHabitat();

        if (c != null && h != null && z != null) {
            e.agregarCuidador(c);
            e.agregarHabitat(h);
            c.agregarEspecie(e);
            h.agregarEspecie(e);
            sistema.agregarEpecie(e);
            z.agregarEspecie(e);
        } else {
            EntradaSalida.mostrarString("No se puede crear la especie sin cuidador, habitat ni zona.\n");
        }

    }

    private void agregarItinerario(SistemaGestionZoo sistema) {
        EntradaSalida.mostrarString("INGRESAR UN ITINERARIO AL SISTEMA.");
        int cod = EntradaSalida.leerInt("Ingresar el codigo: ");
        //verificar si se repite el codigo.
        boolean f = sistema.buscarCodigoIti(cod, sistema);
        while (f) {
            cod = EntradaSalida.leerInt("Codigo repetido, ingrese uno distinto.");
            f = sistema.buscarCodigoIti(cod, sistema);
        }
        int duracionRecorrido = EntradaSalida.leerInt("Ingresar la duracion del recorrido[minutos]: ");
        double longDeIt = EntradaSalida.leerDouble("Ingresar la longitud del itinerario[km]: ");
        int maxVisitantesAut = EntradaSalida.leerInt("Ingresar el maximo de visitantes autorizados: ");
        int cantEspecies = EntradaSalida.leerInt("Ingresar el numero de distintas especies que visita: ");
        Itinerario i = new Itinerario(cod, duracionRecorrido, longDeIt, maxVisitantesAut, cantEspecies);
        Guia g = sistema.asignarGuia();
        Zona z = sistema.asignarZona();

        if (g != null && z != null) {
            i.agregarGuia(g);
            i.agregarZona(z);
            g.agregarItinerario(i);
            z.agregarItinerario(i);
            sistema.agregarItinerario(i);
        } else {
            EntradaSalida.mostrarString("No se puede crear el itinerario sin guia ni zona.\n");
        }
    }

    private void agregarHabitat(SistemaGestionZoo sistema) {
        EntradaSalida.mostrarString("INGRESAR UN HABITAT AL SISTEMA.");
        String nom = EntradaSalida.leerString("Ingresar el nombre: ");
        String clima = EntradaSalida.leerString("Ingresar el clima: ");
        Habitat h = new Habitat(nom, clima);
        sistema.agregarHabitat(h);
    }

    private void agregarZona(SistemaGestionZoo sistema) {
        EntradaSalida.mostrarString("INGRESAR UNA ZONA AL SISTEMA.");
        String nom = EntradaSalida.leerString("Ingresar el nombre: ");
        double extencion = EntradaSalida.leerDouble("Ingresar la extension que ocupan[metros]: ");
        Zona z = new Zona(nom, extencion);
        sistema.agregarZona(z);
    }

    private void verPersonas(SistemaGestionZoo sistema) {
        ArrayList<Persona> p = sistema.getPersonas();
        if (p.isEmpty()) {
            EntradaSalida.mostrarString("No hay ningn empleado cargado en el sistema.");
        } else {
            for (Persona persona : p) {
                persona.mostrar();
                EntradaSalida.mostrarString("");
            }
        }
    }

    private void verEspecie(SistemaGestionZoo sistema) {
        ArrayList<Especie> e = sistema.getEspecies();
        e = sistema.getEspecies();
        if (e.isEmpty()) {
            EntradaSalida.mostrarString("No hay ningna especie cargada en el sistema.");
        } else {
            for (Especie especie : e) {
                especie.mostrar();
                EntradaSalida.mostrarString("");
            }
        }
    }

    private void verItinerario(SistemaGestionZoo sistema) {
        ArrayList<Itinerario> i = sistema.getItinerarios();
        if (i.isEmpty()) {
            EntradaSalida.mostrarString("No hay ningn itinerario cargado en el sistema.");
        } else {
            for (Itinerario itinerario : i) {
                itinerario.mostrar();
                EntradaSalida.mostrarString("");
            }
        }
    }

    private void verHabitat(SistemaGestionZoo sistema) {
        ArrayList<Habitat> h = sistema.getHabitats();
        if (h.isEmpty()) {
            EntradaSalida.mostrarString("No hay ningn habitat cargado en el sistema.");
        } else {
            for (Habitat habitat : h) {
                habitat.mostrar();
                EntradaSalida.mostrarString("");
            }
        }
    }

    private void verZona(SistemaGestionZoo sistema) {
        ArrayList<Zona> z = sistema.getZonas();
        if (z.isEmpty()) {
            EntradaSalida.mostrarString("No hay ningna zona cargada en el sistema.");
        } else {
            for (Zona zona : z) {
                zona.mostrar();
                EntradaSalida.mostrarString("");
            }
        }
    }

    public void editarItinerario(Itinerario iti, SistemaGestionZoo sistema) {
        int op;
        do {
            op = EntradaSalida.leerInt(
                    "OPCIONES DE EDITAR ITINERARIO:\n"
                    + "[1] Agregar guia.\n"
                    + "[2] Eliminar guia.\n"
                    + "[3] Agregar zona.\n"
                    + "[4] Eliminar zona.\n"
                    + "[5] Salir de este menu.\n");
            switch (op) {
                case 1: {
                    ArrayList<Guia> aux = new ArrayList();
                    for (Guia guia : sistema.getGuias()) {
                        if (!iti.getGuias().contains(guia)) {
                            aux.add(guia);
                        }
                    }
                    Guia g = sistema.elegirGuia(aux);
                    if (g != null) {
                        iti.agregarGuia(g);
                        g.agregarItinerario(iti);
                    }
                }
                break;
                case 2: {
                    Guia g = sistema.elegirGuia(iti.getGuias());
                    if (g != null) {
                        iti.eliminarGuia(g);
                        g.eliminarItinerario(iti);
                    }
                }
                break;
                case 3: {
                    ArrayList<Zona> aux = new ArrayList();
                    for (Zona zona : sistema.getZonas()) {
                        if (!iti.getZonas().contains(zona)) {
                            aux.add(zona);
                        }
                    }
                    Zona z = sistema.elegirZona(aux);
                    if (z != null) {
                        iti.agregarZona(z);
                        z.agregarItinerario(iti);
                    }
                }
                break;
                case 4: {
                    Zona z = sistema.elegirZona(iti.getZonas());
                    if (z != null) {
                        iti.eliminarZona(z);
                        z.eliminarItinerario(iti);
                    }
                }
                break;
                case 5:
                    break;
                default:
                    EntradaSalida.mostrarString("ERROR: Opcion invalida\n");
                    op = 0;
            }
        } while (op != 5);
    }

    public void editarEspecie(Especie esp, SistemaGestionZoo sistema) {
        int op;
        do {
            op = EntradaSalida.leerInt(
                    "OPCIONES DE EDITAR ESPECIE:\n"
                    + "[1] Agregar cuidador.\n"
                    + "[2] Eliminar cuidador.\n"
                    + "[3] Agregar habitat.\n"
                    + "[4] Eliminar habitat.\n"
                    + "[5] Salir de este menu.\n");
            switch (op) {
                case 1: {
                    ArrayList<Cuidador> aux = new ArrayList();
                    for (Cuidador cuidador : sistema.getCuidadores()) {
                        if (!esp.getCuidadores().contains(cuidador)) {
                            aux.add(cuidador);
                        }
                    }
                    Cuidador c = sistema.elegirCuidadore(aux);
                    if (c != null) {
                        esp.agregarCuidador(c);
                        c.agregarEspecie(esp);
                    }
                }
                break;
                case 2: {
                    Cuidador c = sistema.elegirCuidadore(esp.getCuidadores());
                    if (c != null) {
                        esp.eliminarCuidador(c);
                        c.eliminarEspecie(esp);
                    }
                }
                break;
                case 3: {
                    ArrayList<Habitat> aux = new ArrayList();
                    for (Habitat habitat : sistema.getHabitats()) {
                        if (!esp.getHabitats().contains(habitat)) {
                            aux.add(habitat);
                        }
                    }
                    Habitat h = sistema.elegirHabitat(aux);
                    if (h != null) {
                        esp.agregarHabitat(h);
                        h.agregarEspecie(esp);
                    }
                }
                break;
                case 4: {
                    Habitat h = sistema.elegirHabitat(esp.getHabitats());
                    if (h != null) {
                        esp.eliminarHabitat(h);
                        h.eliminarEspecie(esp);
                    }
                }
                break;
                case 5:
                    break;
                default:
                    EntradaSalida.mostrarString("ERROR: Opcion invalida\n");
                    op = 0;
            }
        } while (op != 5);
    }

    @Override
    public boolean mostrarMenu(SistemaGestionZoo sistema) {
        int opc;
        String user;
        String password;
        String nom;
        boolean seguir = true;
        do {
            opc = EntradaSalida.leerInt(
                    "OPCIONES DE ADMINISTRADOR:\n"
                    + "[01] Ver todas las presonas en el sistema\n"//
                    + "[02] Ver especies\n"//
                    + "[03] Ver itinerarios\n" // TENGO QUE HACER UN METODO ABSTRACTO EN CADA CLASE PARA MOSTRAR LA INFO(bien)
                    + "[04] Ver habitats\n"//
                    + "[05] Ver zonas\n"//
                    + "[06] Agregar Guia\n"
                    + "[07] Agregar Cuidador\n"
                    + "[08] Agregar especies\n"
                    + "[09] Agregar itinerarios\n"
                    + "[10] Agregar habitats\n"
                    + "[11] Agregar zonas\n"
                    + "[12] Editar itinerarios\n"
                    + "[13] Editar especies\n"
                    + "[14] Eliminar Guia\n"//
                    + "[15] Eliminar Cuidador\n"//
                    + "[16] Eliminar especies\n" // METODO BUSCAR EN SISTEMA PARA CADA UNO
                    + "[17] Eliminar itinerarios\n"//
                    + "[18] Eliminar habitats\n"//
                    + "[19] Eliminar zonas\n"//
                    + "[20] Salir del menu\n"
                    + "[21] Salir del sistema\n");
            switch (opc) {
                case 1:
                    verPersonas(sistema);
                    break;
                case 2:
                    verEspecie(sistema);
                    break;
                case 3:
                    verItinerario(sistema);
                    break;
                case 4:
                    verHabitat(sistema);
                    break;
                case 5:
                    verZona(sistema);
                    break;
                case 6:
                    agregarGuia(sistema);
                    break;
                case 7:
                    agregarCuidador(sistema);
                    break;
                case 8:
                    agregarEspecie(sistema);
                    break;
                case 9:
                    agregarItinerario(sistema);
                    break;
                case 10:
                    agregarHabitat(sistema);
                    break;
                case 11:
                    agregarZona(sistema);
                    break;
                case 12:
                    Itinerario iti = sistema.elegirItinerario();
                    if (iti != null) {
                        editarItinerario(iti, sistema);
                    } else {
                        EntradaSalida.mostrarString("No se encontro el itinerario.\n");
                    }
                    break;
                case 13:
                    Especie esp = sistema.elegirEspecie();
                    if (esp != null) {
                        editarEspecie(esp, sistema);
                    } else {
                        EntradaSalida.mostrarString("No se encontro la especie.\n");
                    }
                    break;
                case 14:
                    EntradaSalida.mostrarString("ELIMINAR GUIA.");
                    user = EntradaSalida.leerString("Ingresar el usuario del guia a eliminar: ");
                    password = EntradaSalida.leerString("Ingresar password del guia a eliminar: ");
                    Persona g = sistema.buscarPersona(user + ":" + password);
                    if (g != null) {
                        sistema.eliminarGuia(g);
                    } else {
                        System.out.println("No se encontro ninguna persona con estos datos.");
                    }
                    break;
                case 15:
                    EntradaSalida.mostrarString("ELIMINAR CUIDADOR.");
                    user = EntradaSalida.leerString("Ingresar el usuario del cuidador a eliminar: ");
                    password = EntradaSalida.leerString("Ingresar password del cuidador a eliminar: ");
                    Persona c = sistema.buscarPersona(user + ":" + password);
                    if (c != null) {
                        sistema.eliminarCuidador(c);
                    } else {
                        System.out.println("No se encontro ninguna persona con estos datos.");
                    }
                    break;
                case 16:
                    EntradaSalida.mostrarString("ELIMINAR ESPECIE.");
                    String nomCientifico = EntradaSalida.leerString("Ingresar el nombre cientifico de la especie a eliminar: ");
                    Especie e = sistema.buscarEspecie(nomCientifico);
                    if (e != null) {
                        sistema.eliminarEspecie(e);
                    } else {
                        System.out.println("No se encontro ninguna especie con este nombre cientifico.");
                    }
                    break;
                case 17:
                    EntradaSalida.mostrarString("ELIMINAR ITINERARIO.");
                    int codigo = EntradaSalida.leerInt("Ingresar el codigo del itinerario a eliminar: ");
                    Itinerario i = sistema.buscarItinerario(codigo);
                    if (i != null) {
                        sistema.eliminarItinerario(i);
                    } else {
                        System.out.println("No se encontro ningun itinerario con este codigo.");
                    }
                    break;
                case 18:
                    EntradaSalida.mostrarString("ELIMINAR HABITAT.");
                    nom = EntradaSalida.leerString("Ingresar el nombre del habitat a eliminar: ");
                    Habitat h = sistema.buscarHabitat(nom);
                    if (h != null) {
                        sistema.eliminarHabitat(h);
                    } else {
                        System.out.println("No se encontro ningun habitat con este nombre.");
                    }
                    break;
                case 19:
                    EntradaSalida.mostrarString("ELIMINAR ZONA.");
                    nom = EntradaSalida.leerString("Ingresar el nombre de la zona a eliminar: ");
                    Zona z = sistema.buscarZona(nom);
                    if (z != null) {
                        sistema.eliminarZona(z);
                    } else {
                        System.out.println("No se encontro ninguna zona con este nombre.");
                    }
                    break;
                case 20:
                    seguir = true;
                    break;
                case 21:
                    seguir = false;
                    break;
            }
            EntradaSalida.mostrarString("");
            if (opc >= 6 && opc <= 19) {
                try {
                    sistema.serializar("zoologico.txt");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } while (opc != 20 && opc != 21);
        return seguir;
    }

    @Override
    public void mostrar() {
        EntradaSalida.mostrarString(this.getClass().getSimpleName() + " - Usuario: " + this.getUsuario());
        EntradaSalida.mostrarString("Password: " + this.getPassword());
    }
}