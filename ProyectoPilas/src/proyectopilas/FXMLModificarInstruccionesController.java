/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectopilas;

import Model.Operaciones;
import Persistencia.Util;
import java.io.IOException;
import static java.lang.Integer.parseInt;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Julio Alvia
 */
public class FXMLModificarInstruccionesController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML private Button btn_ingresar1;
    @FXML private Button btn_modificar1;
    @FXML private Button btn_eliminar1;
    @FXML private Button btn_regresar1;
    
    @FXML private TextField operando;
    @FXML private TextField prioridad;
    @FXML private ChoiceBox instruccion;
    @FXML private TableView<Operaciones> tablaInstrucciones;
    @FXML private TableColumn<Operaciones,String> col_instruccion;
    @FXML private TableColumn<Operaciones,String> col_operando;
    @FXML private TableColumn<Operaciones,Integer> col_prioridad;
    
    private ObservableList<Operaciones> lista_operaciones=FXCollections.observableArrayList();
    
    @FXML private void inst_regresar(ActionEvent event) throws IOException {
        ((Node)(event.getSource())).getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        Stage stage=new Stage(); 
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Modificar Archivo de Instrucciones");
        stage.show();
    }
    
    @FXML private void inst_insertar(ActionEvent event) throws IOException {
        if(operando.getText().isEmpty()||prioridad.getText().isEmpty()||instruccion.getSelectionModel().getSelectedItem().toString().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Campos incompletos");
            alert.setContentText("Faltan todos los campos");
            alert.show();
        
        }else{
                Operaciones op = new Operaciones(instruccion.getValue().toString(),operando.getText(),parseInt(prioridad.getText()));
                lista_operaciones.add(op);
                Util.writeInstrucciones(lista_operaciones);
                
        }
    }
    
    @FXML private void inst_modificar(ActionEvent event) throws IOException {
        if(operando.getText().isEmpty()||prioridad.getText().isEmpty()||instruccion.getSelectionModel().getSelectedItem().toString().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Campos incompletos");
            alert.setContentText("Faltan llenar campos");
            alert.show();
        
        }else{
        
            Operaciones op = new Operaciones(instruccion.getValue().toString(),operando.getText(),parseInt(prioridad.getText()));
            int selectedIndex = tablaInstrucciones.getSelectionModel().getSelectedIndex();

            lista_operaciones.set(selectedIndex, op);
            Util.writeInstrucciones(lista_operaciones);
        }
    }
    
    @FXML private void inst_eliminar(ActionEvent event) {
        int selectedIndex = tablaInstrucciones.getSelectionModel().getSelectedIndex();
        tablaInstrucciones.getItems().remove(selectedIndex);
        System.out.println(selectedIndex);
        lista_operaciones.remove(tablaInstrucciones.getSelectionModel());
        Util.writeInstrucciones(lista_operaciones);
    }
    
    
    private void showPersonDetails(Operaciones operaciones) {
        if (operaciones != null) {
            operando.setText(operaciones.getInstruccion().getValue());
            instruccion.setValue(operaciones.getOperando());
            prioridad.setText(operaciones.getPrioridad().getValue().toString());

        } else {
            operando.setText("");
            prioridad.setText("");
        }
    }

    private void inicializarTabla() {
        Operaciones tmp = new Operaciones();
        col_instruccion.setCellValueFactory(cellData -> cellData.getValue().getInstruccion());
        col_operando.setCellValueFactory(cellData -> cellData.getValue().getOperando());
        col_prioridad.setCellValueFactory(cellData -> cellData.getValue().prioridad.asObject());
        showPersonDetails(null);
        tablaInstrucciones.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showPersonDetails(newValue));
        this.lista_operaciones = tmp.llenarInstruccion();
        tablaInstrucciones.setItems(this.lista_operaciones);

    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        this.instruccion.getItems().addAll("ADD","SUB","MUL","DIV","PUSH","POP");
        this.inicializarTabla();
    }    
    
}
