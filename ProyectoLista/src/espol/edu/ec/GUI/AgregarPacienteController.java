package espol.edu.ec.GUI;

import espol.edu.ec.Objetos.Paciente;
import espol.edu.ec.Objetos.Sintoma;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;

/**
 * Created by User on 02/07/2017.
 */
public class AgregarPacienteController implements Initializable {
    //Atributos FXML
    @FXML private TextField textoCedula;
    @FXML private TextField textoNombre;
    @FXML private TextField textoApellido;
    @FXML private TextField textoEdad;
    @FXML private ChoiceBox<String> choiceGenero;
    @FXML private ChoiceBox<Sintoma> choiceSintoma;
    @FXML private Label labelError;
    @FXML private GridPane pane;
    //Atributos normales
    private LinkedList<Sintoma> listaSintomas;
    private Integer edad;
    private int turn=0;
    private static Stage stage1=new Stage();

    
    
    
    @FXML
    public void regresarAMenuPrincipal(ActionEvent event) throws IOException
    {
        ((Node)(event.getSource())).getScene().getWindow().hide();
        AgregarPacienteController.stage1.close();
        
        /**Parent root = FXMLLoader.load(getClass().getResource("PaginaPrincipal.fxml"));
        
        Stage stage=new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Menu Principal");
        stage.show();*/
        
    }

    
    @FXML
    public void guardarPaciente(ActionEvent event) throws IOException {
        if (this.comprobarDatos()) {
            PaginaPrincipalController temporal = new PaginaPrincipalController();
            labelError.setVisible(false);
            Paciente paciente = new Paciente(textoCedula.getText(), textoNombre.getText(), textoApellido.getText(),
                    choiceGenero.getValue().charAt(0), Integer.parseInt(textoEdad.getText()), choiceSintoma.getValue());
            Paciente.guardarPaciente(paciente);
            PaginaPrincipalController.getConsultorio().agregarPaciente(paciente);
            //paciente.setTurno(String.valueOf(MainPrincipal.getAtendidos())+paciente.getSintoma().getPrioridad());
            this.setTurn(this.getTurn() + 1);
            paciente.setTurno(String.valueOf(this.getTurn()));
            /**if (PaginaPrincipalController.consultorio.getColaPacientes().isEmpty()) {

            } else if (PaginaPrincipalController.consultorio.getColaPacientes().size() == 1) {
                //Label lbl=new Label(PaginaPrincipalController.consultorio.getColaPacientes().peek().getTurno());
                temporal.getTurno1().setText(PaginaPrincipalController.consultorio.getColaPacientes().peek().getTurno());// = new Label(PaginaPrincipalController.consultorio.getColaPacientes().peek().getTurno());

                //pane.getChildren().add(lbl);//add(lbl, 0, 1);
                //pane_abajo.getChildren().add(pane);
                //turno1.setText(PaginaPrincipalController.consultorio.getColaPacientes().peek().getTurno());
                //turno1.setVisible(true);
            } else {
                Paciente tmp1 = PaginaPrincipalController.consultorio.getColaPacientes().poll();
                Paciente tmp2 = PaginaPrincipalController.consultorio.getColaPacientes().poll();
                temporal.getTurno1().setText(tmp1.getTurno());
                temporal.getTurno2().setText(tmp2.getTurno());
                PaginaPrincipalController.consultorio.agregarPaciente(tmp1);
                PaginaPrincipalController.consultorio.agregarPaciente(tmp2);
            }*/
            temporal.modificarTurnos();
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
    
    public static Stage getStage1() {
        return stage1;
    }

    public static void setStage1(Stage stage1) {
        AgregarPacienteController.stage1 = stage1;
    }
    
    
    public int getTurn() {
        return turn;
    }

    public void setTurn(int turn) {
        this.turn = turn;
    }
    
}
