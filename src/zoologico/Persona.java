package zoologico;

import java.io.Serializable;


public abstract class Persona implements Serializable {
    
    private String usuario;
    private String password;

    //CONSTRUCTOR
    public Persona(String usuario, String password) {
        this.usuario = usuario;
        this.password = password;
    }

    //GETTER Y SETTER
    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    //METODOS
    public abstract boolean mostrarMenu(SistemaGestionZoo sistema);
    
    public abstract void mostrar();
    
}
