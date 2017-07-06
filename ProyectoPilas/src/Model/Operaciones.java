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
import java.util.ListIterator;
import java.util.PriorityQueue;
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
public class Operaciones {
    
    public  StringProperty instruccion;
    public  StringProperty operando ;
    public  IntegerProperty prioridad;
    private ObservableList<Operaciones> lista_operaciones=FXCollections.observableArrayList();
    public static PriorityQueue<Operaciones> operaciones;
    
    public Operaciones() {
    }

    public Operaciones(String instruccion, String operando, int prioridad) {
        this.instruccion =new SimpleStringProperty( instruccion);
        this.operando = new SimpleStringProperty(operando);
        this.prioridad = new SimpleIntegerProperty(prioridad);
    }

    public ObservableList<Operaciones> llenarInstruccion() {
        String linea;
        try {
            FileReader f = new FileReader("instrucciones.txt");
            BufferedReader br = new BufferedReader(f);
            while (true) {
                linea = br.readLine();
                if (linea == null) {
                    break;
                }
                String valores[];
                valores = linea.split(",");
                String instruccion = valores[0].trim();
                String operando = valores[1].trim();
                int val = parseInt(valores[2].trim());
                System.out.println(linea);
                Operaciones tmp = new Operaciones(instruccion,operando,val);
                this.lista_operaciones.add(tmp);

            }
        } catch (IOException e) {
        }
        return this.lista_operaciones;
    }
    
    public Integer getIntPrioridad(){
        return prioridad.getValue().intValue();
    }
    
    public StringProperty getInstruccion() {
        return instruccion;
    }

    public void setInstruccion(StringProperty instruccion) {
        this.instruccion = instruccion;
    }

    public StringProperty getOperando() {
        return operando;
    }

    public void setOperando(StringProperty operando) {
        this.operando = operando;
    }

    public IntegerProperty getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(IntegerProperty prioridad) {
        this.prioridad = prioridad;
    }
    
    public static void loadingOp() {
        operaciones = new PriorityQueue<>((Operaciones op1, Operaciones op2) -> op1.getIntPrioridad() - op2.getIntPrioridad());
        Operaciones op = new Operaciones();
        ObservableList<Operaciones> lOp = op.llenarInstruccion();
        
        ListIterator<Operaciones> li = lOp.listIterator();
        while (li.hasNext()) {
            Operaciones o = li.next();
            operaciones.offer(o);
        }
    }
}
