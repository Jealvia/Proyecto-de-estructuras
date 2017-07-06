package espol.edu.ec.GUI;

import espol.edu.ec.Objetos.Paciente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AgregarDiagnosticoSala1Controller implements Initializable {

    @FXML
    private TextArea textoDiagnostico;
    @FXML
    private Label labelError;
    @FXML
    private Label nombrePaciente;

    private Paciente pacienteAAtender;
    private static Stage stage1 = new Stage();

    public static Stage getStage1() {
        return stage1;
    }

    public static void setStage1(Stage stage1) {
        AgregarDiagnosticoSala1Controller.stage1 = stage1;
    }

    @FXML
    public void regresarAMenuPrincipal(ActionEvent event) throws IOException {
        
        AgregarDiagnosticoSala1Controller.stage1.close();
        

    }

    @FXML
    public void agregarDiagnostico(ActionEvent event) throws IOException {
        if (textoDiagnostico.getText() == null || textoDiagnostico.getText().trim().isEmpty()) {
            labelError.setText("Datos no ingresados");
            labelError.setVisible(true);
        } else {
            pacienteAAtender = PaginaPrincipalController.getConsultorio().getColaPacientes().poll();
            regresarAMenuPrincipal(event);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        labelError.setVisible(false);
        labelError.setTextFill(Color.RED);
        pacienteAAtender = PaginaPrincipalController.getConsultorio().getColaPacientes().peek();
        nombrePaciente.setText(pacienteAAtender.getNombre());
        //MainVideo.setTurno2(pacienteAAtender.getTurno());

    }

}
