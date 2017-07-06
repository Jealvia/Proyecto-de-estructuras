/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import static java.lang.Integer.parseInt;
import java.util.HashMap;
import java.util.ListIterator;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Julio Alvia
 */
public class Memoria {
    
    public  StringProperty direccion_memoria ;
    public  IntegerProperty valor;
    private ObservableList<Memoria> lista_memoria=FXCollections.observableArrayList();
    public static HashMap<String, Integer> memoria;

    Memoria(String instruccion, String operando, int val) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public ObservableList<Memoria> getLista_memoria() {
        return lista_memoria;
    }

    public void setLista_memoria(ObservableList<Memoria> lista_memoria) {
        this.lista_memoria = lista_memoria;
    }
    
    public Memoria() {
    }

    public Memoria(String direccion_memoria, int valor) {
        this.direccion_memoria = new SimpleStringProperty(direccion_memoria);
        this.valor = new SimpleIntegerProperty(valor);
    }

    public Integer getIntValor(){
        return valor.getValue().intValue();
    }
    
    
    public StringProperty getDireccion_memoria() {
        return direccion_memoria;
    }

    public void setDireccion_memoria(StringProperty direccion_memoria) {
        this.direccion_memoria = direccion_memoria;
    }

    public IntegerProperty getValor() {
        return valor;
    }

    public void setValor(IntegerProperty valor) {
        this.valor = valor;
    }
    
    public String getDireccionString() {
        return direccion_memoria.getValue();
    }

    


    public ObservableList<Memoria> llenarMemoria() {
        String linea;
        try {
            FileReader f = new FileReader("memoria.txt");
            BufferedReader br = new BufferedReader(f);
            while (true) {
                linea = br.readLine();
                if (linea == null) {
                    break;
                }
                String valores[];
                valores = linea.split(",");
                String dir_memoria = valores[0].trim();
                int val = parseInt(valores[1].trim());
                System.out.println(linea);
                Memoria tmp = new Memoria(dir_memoria,val);
                this.lista_memoria.add(tmp);
                //temporal.add(tmp);

            }
        } catch (IOException e) {
        }
        return this.lista_memoria;
    }
    
    public static void loadMemory() {
        memoria = new HashMap<>();
        Memoria me = new Memoria();
        ObservableList<Memoria> lMe = me.llenarMemoria();
        ListIterator<Memoria> li = lMe.listIterator();
        
        while (li.hasNext()) {
            Memoria m = li.next();
            memoria.put(m.getDireccionString(), m.getIntValor());
        }
    }
    
    
}
