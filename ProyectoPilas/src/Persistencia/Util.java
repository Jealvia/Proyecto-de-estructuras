/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import Model.Memoria;
import Model.Operaciones;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.Integer.parseInt;
import java.util.ArrayList;
import javafx.collections.ObservableList;

/**
 *
 * @author Julio Alvia
 */
public class Util {
    
    public static void writeInstrucciones(ObservableList<Operaciones> lista){
        Operaciones[] usuariosArray = lista.toArray(new Operaciones[0]);
        
        try {
            PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("instrucciones.txt")));
            for (Operaciones u : usuariosArray) {
                pw.println(u.getInstruccion().getValue()+","+u.getOperando().getValue()+","+u.getIntPrioridad().toString());
            }

            pw.close();
        } catch (FileNotFoundException ex) {
            System.out.println("el archivo instrucciones.txt no existe");
        } catch (IOException ex) {
            System.out.println("se produjo error al escribir instrucciones.txt");
        }
    
    }
    
    public static void writeMemoria(ObservableList<Memoria> lista){
        Memoria[] usuariosArray = lista.toArray(new Memoria[0]);
        
        try {
            PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("memoria.txt")));
            for (Memoria u : usuariosArray) {
                pw.println(u.getDireccion_memoria().getValue()+","+u.getIntValor().toString());
            }

            pw.close();
        } catch (FileNotFoundException ex) {
            System.out.println("El archivo memoria.txt no existe");
        } catch (IOException ex) {
            System.out.println("Se produjo error al escribir memoria.txt");
        }
    }

}
