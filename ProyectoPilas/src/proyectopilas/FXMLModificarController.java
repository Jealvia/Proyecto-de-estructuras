/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectopilas;

import Model.Memoria;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Julio Alvia
 */
public class FXMLModificarController implements Initializable {

    @FXML
    private Button btn_ingresar;
    @FXML
    private Button btn_modificar;
    @FXML
    private Button btn_eliminar;
    @FXML
    private Button btn_regresar;

    @FXML
    private TextField direccion;
    @FXML
    private TextField valor;

    @FXML
    private TableView<Memoria> tablaMemoria;
    @FXML
    private TableColumn<Memoria, String> col_direccion;
    @FXML
    private TableColumn<Memoria, Integer> col_valor;

    public static ObservableList<Memoria> lista_memoria;

    @FXML
    private void ingresoMemoria(ActionEvent event) {
        if (direccion.getText().isEmpty() && valor.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Campos incompletos");
            alert.setContentText("Falta la direccion y el valor");
            alert.show();
        } else if (direccion.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Campos incompletos");
            alert.setContentText("Falta la direccion");
            alert.show();
        } else if (valor.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Campos incompletos");
            alert.setContentText("Falta el valor");
            alert.show();
        } else {
            Memoria memoria = new Memoria(direccion.getText(), parseInt(valor.getText()));
            lista_memoria.add(memoria);
            Util.writeMemoria(lista_memoria);

        }
    }

    @FXML
    private void modificarMemoria(ActionEvent event) {
        if (direccion.getText().isEmpty() && valor.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Campos incompletos");
            alert.setContentText("Falta la direccion y el valor");
            alert.show();
        } else if (direccion.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Campos incompletos");
            alert.setContentText("Falta la direccion");
            alert.show();
        } else if (valor.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Campos incompletos");
            alert.setContentText("Falta el valor");
            alert.show();
        } else {
            Memoria memoria = new Memoria(direccion.getText(), parseInt(valor.getText()));
            int selectedIndex = tablaMemoria.getSelectionModel().getSelectedIndex();

            lista_memoria.set(selectedIndex, memoria);
            Util.writeMemoria(lista_memoria);
        }
    }

    @FXML
    private void eliminarMemoria(ActionEvent event) {
        int selectedIndex = tablaMemoria.getSelectionModel().getSelectedIndex();
        tablaMemoria.getItems().remove(selectedIndex);
        System.out.println(selectedIndex);
        lista_memoria.remove(tablaMemoria.getSelectionModel());
        Util.writeMemoria(lista_memoria);
    }

    @FXML
    private void regresarMemoria(ActionEvent event) throws IOException {
        ((Node)(event.getSource())).getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Proyecto Pilas");
        stage.show();
    }

    private void showPersonDetails(Memoria memoria) {
        if (memoria != null) {
            direccion.setText(memoria.getDireccion_memoria().getValue());
            valor.setText(memoria.getIntValor().toString());

        } else {
            direccion.setText("");
            valor.setText("");
        }
    }

    private void inicializarTabla() {
        Memoria tmp = new Memoria();
        col_direccion.setCellValueFactory(cellData -> cellData.getValue().getDireccion_memoria());
        col_valor.setCellValueFactory(cellData -> cellData.getValue().valor.asObject());
        showPersonDetails(null);
        tablaMemoria.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showPersonDetails(newValue));
        this.lista_memoria = tmp.llenarMemoria();
        tablaMemoria.setItems(this.lista_memoria);

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        this.inicializarTabla();
        //btn_modificar.setDisable(true);
        //btn_eliminar.setDisable(true);
    }

}
