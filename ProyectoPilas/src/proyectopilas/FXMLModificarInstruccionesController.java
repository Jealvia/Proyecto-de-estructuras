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
        stage.setTitle("Proyecto Pilas");
        stage.show();
    }
    
    @FXML private void inst_insertar(ActionEvent event) throws IOException {
        if(verificarDatos())
        {
            Operaciones op = new Operaciones(instruccion.getValue().toString(),operando.getText().toUpperCase(),parseInt(prioridad.getText()));
            lista_operaciones.add(op);
            Util.writeInstrucciones(lista_operaciones);
        }
    }
    
    @FXML private void inst_modificar(ActionEvent event) throws IOException {
        if(verificarDatos()){
            Operaciones op = new Operaciones(instruccion.getValue().toString(),operando.getText(),parseInt(prioridad.getText()));
            int selectedIndex = tablaInstrucciones.getSelectionModel().getSelectedIndex();

            lista_operaciones.set(selectedIndex, op);
            Util.writeInstrucciones(lista_operaciones);
        }
    }
    
    @FXML private void inst_eliminar(ActionEvent event) {
        try
        {
            int selectedIndex = tablaInstrucciones.getSelectionModel().getSelectedIndex();
            tablaInstrucciones.getItems().remove(selectedIndex);
            System.out.println(selectedIndex);
            lista_operaciones.remove(tablaInstrucciones.getSelectionModel());
            Util.writeInstrucciones(lista_operaciones);  
        }
        catch(ArrayIndexOutOfBoundsException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Fila no seleccionada");
            alert.setContentText("Seleccione la fila a eliminar");
            alert.show();
        }
        
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
    
    private boolean verificarDatos()
    {
        try
        {
            Integer.parseInt(prioridad.getText());
        }
        catch(NumberFormatException e)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Campo incorrecto");
            alert.setContentText("La prioridad debe ser un entero positivo");
            alert.show();
            return false;
        }
        if((operando.getText() == null ||operando.getText().trim().isEmpty()) && (("PUSH".equals(instruccion.getSelectionModel().getSelectedItem().toString()))
                || ("POP".equals(instruccion.getSelectionModel().getSelectedItem().toString())))&&
                (prioridad.getText() == null ||prioridad.getText().trim().isEmpty())&&
                instruccion.getSelectionModel().getSelectedItem().toString().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Campos incompletos");
            alert.setContentText("Faltan todos los campos");
            alert.show();
            return false;
        
        }
        if((operando.getText().length()>1) && (("PUSH".equals(instruccion.getSelectionModel().getSelectedItem().toString()))
                || ("POP".equals(instruccion.getSelectionModel().getSelectedItem().toString()))))
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Campo incorrecto");
            alert.setContentText("El operando debe ser una letra de la A a la Z");
            alert.show();
            return false;
        }
        if((!isAlpha(operando.getText())) && (("PUSH".equals(instruccion.getSelectionModel().getSelectedItem().toString()))
                || ("POP".equals(instruccion.getSelectionModel().getSelectedItem().toString()))))
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Campo incorrecto");
            alert.setContentText("El operando debe ser una letra de la A a la Z");
            alert.show();
            return false;
        }
        if(("PUSH".equals(instruccion.getSelectionModel().getSelectedItem().toString())||
                "POP".equals(instruccion.getSelectionModel().getSelectedItem().toString()))&&
                operando.getText().isEmpty())
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Campos incompletos");
            alert.setContentText("Ingresar un operando");
            alert.show();
            return false;
        }
        if(("ADD".equals(instruccion.getSelectionModel().getSelectedItem().toString())||
                "SUB".equals(instruccion.getSelectionModel().getSelectedItem().toString())||
                "MUL".equals(instruccion.getSelectionModel().getSelectedItem().toString())||
                "DIV".equals(instruccion.getSelectionModel().getSelectedItem().toString()))&&
                !operando.getText().isEmpty())
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Campo incorrecto");
            alert.setContentText("No se deben ingresar operandos con esta instruccion");
            alert.show();
            return false;
        }
        return true;
    }
    
        private boolean isAlpha(String palabra)
    {
        return (palabra.length()>0 && "ABCDEFGHIJKLMNOPQRSTUVWXYZ".contains(palabra));
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        this.instruccion.getItems().addAll("ADD","SUB","MUL","DIV","PUSH","POP");
        this.inicializarTabla();
    }    
    
}
