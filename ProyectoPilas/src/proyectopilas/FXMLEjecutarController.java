/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectopilas;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Stack;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import Model.Operaciones;
import java.io.IOException;
import java.util.ListIterator;
import java.util.PriorityQueue;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
    private int cont = 0;
    
    public void siguienteOp (ActionEvent event) {
        System.out.println("Siguiente");
        cont++;
        Label lbl = new Label("Prueba" + Integer.toString(cont));
        pilaGUI.getChildren().add(0, lbl);
        
        
        
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