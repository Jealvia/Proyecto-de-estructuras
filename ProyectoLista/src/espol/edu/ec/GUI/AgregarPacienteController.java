package espol.edu.ec.GUI;

import espol.edu.ec.Objetos.Paciente;
import espol.edu.ec.Objetos.Sintoma;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;

/**
 * Created by User on 02/07/2017.
 */
public class AgregarPacienteController implements Initializable {
    @FXML private TextField textoCedula;
    @FXML private TextField textoNombre;
    @FXML private TextField textoApellido;
    @FXML private TextField textoEdad;
    @FXML private ChoiceBox<String> choiceGenero;
    @FXML private ChoiceBox<Sintoma> choiceSintoma;
    @FXML private Label labelError;
    private LinkedList<Sintoma> listaSintomas;
    private Integer edad;

    @FXML
    public void regresarAMenuPrincipal(ActionEvent event) throws IOException
    {
        ((Node)(event.getSource())).getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("MenuPrincipal.fxml"));
        Stage stage=new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Menu Principal");
        stage.show();

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        listaSintomas = Sintoma.readFromFile("sintomas.txt");
        for(Sintoma sintoma : listaSintomas)
        {
            this.choiceSintoma.getItems().add(sintoma);
        }
        this.choiceGenero.getItems().addAll("H", "M");
        labelError.setVisible(false);
        labelError.setTextFill(Color.RED);
    }
    @FXML
    public void guardarPaciente(ActionEvent event) throws IOException {
        if(this.comprobarDatos())
        {
            labelError.setVisible(false);
            Paciente paciente = new Paciente(textoCedula.getText(), textoNombre.getText(),textoApellido.getText(),
                    choiceGenero.getValue().charAt(0), Integer.parseInt(textoEdad.getText()), choiceSintoma.getValue());
            Paciente.guardarPaciente(paciente);
            Main.getConsultorio().agregarPaciente(paciente);
            paciente.setTurno(String.valueOf(Main.getAtendidos())+paciente.getSintoma().getPrioridad());
            /*
            if (!comprobarSala1())
            {
                Main.setPacienteSala1(paciente);
                //MenuPrincipalController.turno1.setText("00");
                System.out.println(paciente.getTurno());
            }
            else if (!comprobarSala2())
            {
                Main.setPacienteSala2(paciente);
                //MainVideo.setTurno2(paciente.getTurno());

            }
            */
            regresarAMenuPrincipal(event);
        }
    }

    private boolean comprobarDatos(){
        try
        {
            edad = Integer.parseInt(textoEdad.getText());
        }catch (NumberFormatException e)
        {
            labelError.setText("Edad incorrecta");
            labelError.setVisible(true);
            return false;
        }
        try
        {
            Integer.parseInt(textoCedula.getText());
        }catch (NumberFormatException e)
        {
            labelError.setText("Cedula incorrecta");
            labelError.setVisible(true);
            return false;
        }
        if(textoCedula.getText().length()!=10)
        {
            labelError.setText("Cedula incorrecta");
            labelError.setVisible(true);
            return false;
        }
        if (textoNombre.getText()==null || textoNombre.getText().trim().isEmpty() ||
                textoCedula.getText()==null || textoCedula.getText().trim().isEmpty() ||
                textoApellido.getText()==null || textoApellido.getText().trim().isEmpty())
        {
            labelError.setText("Datos no ingresados");
            labelError.setVisible(true);
            return false;
        }
        if(choiceGenero.getValue() == null || choiceSintoma.getValue()==null)
        {
            labelError.setText("Seleccione una Opcion");
            labelError.setVisible(true);
            return false;
        }
        return true;
    }
/*
    private boolean comprobarSala1()
    {
        if(Main.getPacienteSala1() == null)
        {
            return false;
        }
        return true;
    }

    private boolean comprobarSala2()
    {
        if(Main.getPacienteSala2() == null)
        {
            return false;
        }
        return true;
    }
*/
}
