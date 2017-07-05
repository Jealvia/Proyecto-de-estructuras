package espol.edu.ec.Objetos;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by User on 01/07/2017.
 */
public class Sintoma {
    private String nombre;
    private int prioridad;

    public Sintoma(String nombre, int prioridad) {
        this.nombre = nombre;
        this.prioridad = prioridad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(int prioridad) {
        this.prioridad = prioridad;
    }

    @Override
    public String toString() {
        return "Sintoma: " + nombre + ", Prioridad: " + prioridad;
    }

    public static LinkedList<Sintoma> readFromFile(String archivo)
    {
        LinkedList<Sintoma> procesos = new LinkedList<>();
        try {
            Scanner sc = new Scanner(new File(archivo));
            while(sc.hasNext())
            {
                String linea = sc.nextLine();
                String[] arreglo = linea.split("\\|");
                Sintoma p = new Sintoma(arreglo[0], Integer.parseInt(arreglo[1]));
                procesos.add(p);
            }
        } catch (FileNotFoundException ex) {
            System.out.println("archivo no encontrado");
        }
        return procesos;
    }
}
