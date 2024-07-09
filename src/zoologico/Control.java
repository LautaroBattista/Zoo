package zoologico;

import java.io.IOException;
import java.io.Serializable;


public class Control implements Serializable {
    
    public void ejecutar() {

        SistemaGestionZoo sistema = new SistemaGestionZoo();

        boolean seguir = true;
        try {
            sistema = sistema.deSerializar("zoologico.txt");
            EntradaSalida.mostrarString("\t********SISTEMA DE ZOOLOGICO********\n");
        } catch (Exception e) {
            String usuario = EntradaSalida.leerString("Arranque inicial del sistema.\n"
                    + "Sr(a) Administrador(a), ingrese su nombre de usuario:");
            if (usuario.equals("")) {
                throw new NullPointerException("ERROR: El usuario no puede ser nulo.");
            }
            String password = EntradaSalida.leerPassword("Ingrese su password:");
            if (password.equals("")) {
                throw new NullPointerException("ERROR: La password no puede ser nula.");
            }
            sistema.getPersonas().add(new Administrador(usuario, password));
            try {
                sistema.serializar("zoologico.txt");
                EntradaSalida.mostrarString("El arranque ha sido exitoso. Ahora se debe reiniciar el sistema...");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            seguir = false;
        }

        while (seguir) {
            String usuario = EntradaSalida.leerString("Ingrese el usuario:");
            String password = EntradaSalida.leerPassword("Ingrese la password:");

            Persona p = sistema.buscarPersona(usuario + ":" + password);

            if (p == null) {
                EntradaSalida.mostrarString("ERROR: La combinacion usuario/password ingresada no es valida.");
            } else {
                seguir = p.mostrarMenu(sistema);  // POLIMORFISMO!!!!
            }
        }
    }
}
