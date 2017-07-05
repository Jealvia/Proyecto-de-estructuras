package espol.edu.ec.GUI;

import espol.edu.ec.Objetos.Paciente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by User on 02/07/2017.
 */
public class AgregarDiagnosticoSala2Controller implements Initializable {

    @FXML private TextArea textoDiagnostico;
    @FXML private Label labelError;
    @FXML private Label nombrePaciente;


    private Paciente pacienteAAtender;

    @FXML
    public void regresarAMenuPrincipal(ActionEvent event) throws IOException
    {
        Main.getConsultorio().getColaPacientes().add(pacienteAAtender);
        ((Node)(event.getSource())).getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("MenuPrincipal.fxml"));
        Stage stage=new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("MenuPrincipal");
        stage.setOnCloseRequest(event1 -> regresarAMenuPrincipal(stage));
        stage.show();


    }

    public void regresarAMenuPrincipal(ActionEvent event, boolean sacarPaciente) throws IOException
    {
        if(sacarPaciente)
        {
            ((Node)(event.getSource())).getScene().getWindow().hide();
            Parent root = FXMLLoader.load(getClass().getResource("MenuPrincipal.fxml"));
            Stage stage=new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("MenuPrincipal");
            stage.setOnCloseRequest(event1 -> regresarAMenuPrincipal(stage));
            stage.show();
        }
        else
        {
            Main.getConsultorio().getColaPacientes().add(pacienteAAtender);
            ((Node)(event.getSource())).getScene().getWindow().hide();
            Parent root = FXMLLoader.load(getClass().getResource("MenuPrincipal.fxml"));
            Stage stage=new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("MenuPrincipal");
            stage.setOnCloseRequest(event1 -> regresarAMenuPrincipal(stage));
            stage.show();
        }


    }

    @FXML
    public void agregarDiagnostico(ActionEvent event) throws IOException
    {
        if (textoDiagnostico.getText()==null || textoDiagnostico.getText().trim().isEmpty())
        {
            labelError.setText("Datos no ingresados");
            labelError.setVisible(true);
        }
        else
        {
            regresarAMenuPrincipal(event, true);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        labelError.setVisible(false);
        labelError.setTextFill(Color.RED);
        pacienteAAtender = Main.getConsultorio().getSiguiente();
        nombrePaciente.setText(pacienteAAtender.getNombre());
        //MainVideo.setTurno2(pacienteAAtender.getTurno());

    }

    private void regresarAMenuPrincipal(Stage primaryStage)
    {
        //((Node)(event.getSource())).getScene().getWindow().hide();
        primaryStage.close();
        try {

            Parent root = FXMLLoader.load(getClass().getResource("MenuPrincipal.fxml"));
            Stage stage=new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Agregar Paciente");
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
