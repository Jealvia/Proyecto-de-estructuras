/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectopilas;

import Model.Memoria;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Stack;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import Model.Operaciones;
import Persistencia.Util;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author kvasconezl
 */
public class FXMLEjecutarController implements Initializable {

    @FXML
    private Button regresar;
    @FXML
    private Button siguiente;
    @FXML
    private VBox pilaGUI;
    @FXML
    private Label warning1;
    @FXML
    private Label instruccion;
    private Stack<Integer> pila = new Stack<>();
    private String abc = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    
    public void siguienteOp (ActionEvent event) {
        System.out.println("Siguiente");
        Label lbl = new Label();
        lbl.setStyle("-fx-background-color: #fa8536;");
        lbl.setTextFill(Color.WHITE);
        lbl.setMinWidth(100);
        lbl.setMinHeight(30);
        lbl.setMaxWidth(100);
        lbl.setMaxHeight(30);
        lbl.setAlignment(Pos.CENTER);
        Operaciones op = Operaciones.operaciones.poll();
        if (op != null) {
            int ban = 0;
            instruccion.setText(op.getInstruccion().getValue());
            switch (op.getInstruccion().getValue()) {
                case "PUSH":
                    pila.push(Memoria.memoria.get(op.getOperando().getValue()));
                    String s = Memoria.memoria.get(op.getOperando().getValue()).toString();
                    lbl.setText(s);
                    pilaGUI.getChildren().add(0, lbl);
                    break;
                case "POP":
                    Integer valor = pila.pop();
                    Memoria.memoria.put(op.getOperando().getValue(), valor);
                    pilaGUI.getChildren().remove(0);
                    Memoria me = new Memoria(op.getOperando().getValue(), valor);
                    int index = abc.indexOf(op.getOperando().getValue());
                    FXMLModificarController.lista_memoria.set(index, me);
                    Util.writeMemoria(FXMLModificarController.lista_memoria);
                    break;
                case "ADD":
                    if (pila.size() > 1) {
                        Integer num1 = pila.pop();
                        Integer num2 = pila.pop();
                        Integer num3 = num1 + num2;
                        pila.push(num3);
                        pilaGUI.getChildren().remove(0);
                        pilaGUI.getChildren().remove(0);
                        lbl.setText(num3.toString());
                        pilaGUI.getChildren().add(0, lbl);
                    } else {
                        ban = 1;
                    }
                    break;
                case "MUL":
                    if (pila.size() > 1) {
                        Integer num4 = pila.pop();
                        Integer num5 = pila.pop();
                        Integer num6 = num4 * num5;
                        pila.push(num6);
                        pilaGUI.getChildren().remove(0);
                        pilaGUI.getChildren().remove(0);
                        lbl.setText(num6.toString());
                        pilaGUI.getChildren().add(0, lbl);
                    } else {
                        ban = 1;
                    }
                    break;
                case "DIV":
                    if (pila.size() > 1) {
                        Integer num7 = pila.pop();
                        Integer num8 = pila.pop();
                        if (!(num8 == 0)) {
                            Integer num9 = num7 / num8;
                            pila.push(num9);
                            pilaGUI.getChildren().remove(0);
                            pilaGUI.getChildren().remove(0);
                            lbl.setText(num9.toString());
                            pilaGUI.getChildren().add(0, lbl);
                        } else {
                            ban = 2;
                        }
                    } else {
                        ban = 1;
                    }
                    break;
                case "SUB":
                    if (pila.size() > 1) {
                        Integer num10 = pila.pop();
                        Integer num11 = pila.pop();
                        Integer num12 = num10 - num11;
                        pila.push(num12);
                        pilaGUI.getChildren().remove(0);
                        pilaGUI.getChildren().remove(0);
                        lbl.setText(num12.toString());
                        pilaGUI.getChildren().add(0, lbl);
                    } else {
                        ban = 1;
                    }
                    break;
            }
            switch (ban) {
                case 0:
                    warning1.setText("Succes!");
                    break;
                case 1:
                    warning1.setText("Error: mínimo dos números.");
                    break;
                default:
                    warning1.setText("Error: división entre 0.");
                    break;
            }
        } else {
            System.out.println("Ya no hay más operaciones");
            warning1.setText("No hay más instrucciones.");
        }
        
        
    }
    
    @FXML
    private void regresarEjecutar(ActionEvent event) throws IOException {
        ((Node)(event.getSource())).getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Proyecto Pilas");
        stage.show();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}