/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectopilas;

import Model.Memoria;
import Model.Operaciones;
import Persistencia.Util;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Julio Alvia
 */
public class FXMLDocumentController implements Initializable {
    
    
    
    @FXML
    private void modificarMemoria(ActionEvent event) throws IOException {
        System.out.println("Modificar Archivo de Memoria");
        
        ((Node)(event.getSource())).getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("FXMLModificar.fxml"));
        Stage stage=new Stage(); 
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Modificar Archivo de Memoria");
        stage.show();
    }
    
    @FXML
    private void modificarInstrucciones(ActionEvent event) throws IOException {
        System.out.println("Modificar Instrucciones");
        ((Node)(event.getSource())).getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("FXMLModificarInstrucciones.fxml"));
        Stage stage=new Stage(); 
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Modificar Archivo de Instrucciones");
        stage.show();
    }
    
    @FXML
    private void ejecutar(ActionEvent event) throws IOException {
        System.out.println("Ejecutar");
        Operaciones.loadingOp();
        Memoria.loadMemory();
        Memoria m = new Memoria();
        FXMLModificarController.lista_memoria = m.llenarMemoria();
        ((Node)(event.getSource())).getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("FXMLEjecutar.fxml"));
        Stage stage=new Stage(); 
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Ejecutar");
        stage.show();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
