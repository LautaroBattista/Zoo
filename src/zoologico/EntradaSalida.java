package zoologico;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.Timer;


public class EntradaSalida implements Serializable {
    
    public static char leerChar(String texto) {
        System.out.println(texto);
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        char caracter = (line.length() > 0) ? line.charAt(0) : '\0';
        return caracter;
    }
    
    public static String leerString(String texto) {
        System.out.println(texto);
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        return line;
    }
    
    public static int leerInt(String texto) {
        System.out.println(texto);
        int num=0;
        boolean seguir=true;
        while(seguir){
            try {
                Scanner scanner = new Scanner(System.in);
                num = scanner.nextInt();
                seguir = false;
            } catch (Exception e) {
                mostrarString("ERROR, ingresa un entero.\n");
            }
        }
        return num;
    }
    
    public static double leerDouble(String texto) {
        System.out.println(texto);
        double num=0;
        boolean seguir=true;num=0;
        while(seguir){
            try {
                Scanner scanner = new Scanner(System.in);
                num = scanner.nextDouble();
                seguir = false;
            } catch (Exception e) {
                mostrarString("ERROR, ingresa un entero.\n");
            }
        }
        return num;
    }
    
    public static void mostrarString(String texto) {
        System.out.println(texto);
    }
    
    public static String leerPassword(String texto) {
        System.out.println(texto);
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        return line;
    }
    
    public static boolean leerBoolean(String texto) {
        int i = JOptionPane.showConfirmDialog(null, texto, "Consulta", JOptionPane.YES_NO_OPTION);
        return i == JOptionPane.YES_OPTION;
    }
}
