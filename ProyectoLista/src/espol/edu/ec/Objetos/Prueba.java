package espol.edu.ec.Objetos;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * Created by User on 01/07/2017.
 */
public class Prueba {

    public static void main(String[] args) throws FileNotFoundException {
        //Paciente p = new Paciente("juan f", "laso", 'm', 20, new Sintoma("Gripe", 0));
        //Paciente p1 = new Paciente("jf", "laso", 'm', 20, new Sintoma("Gripe", 0));
       // System.out.println(p);
        //System.out.println(Paciente.guardarPaciente(p));
        LinkedList<Sintoma> listaSintomas = Sintoma.readFromFile("sintomas.txt");
        System.out.println(listaSintomas);
        Scanner sc = new Scanner(new File("sintomas.txt"));
        System.out.println(sc.nextLine());
        //System.out.println(Integer.parseInt(null));
       // Playlist p =new Playlist("media.txt");
        PriorityQueue pq = new PriorityQueue<>((Paciente p1, Paciente p2)->p2.getSintoma().getPrioridad()-p1.getSintoma().getPrioridad());
        System.out.println(pq.poll());
        System.out.println(pq.poll());
    }
}
