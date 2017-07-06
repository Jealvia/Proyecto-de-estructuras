/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package espol.edu.ec.GUI;

import espol.edu.ec.Objetos.Consultorio;
import espol.edu.ec.Objetos.Paciente;
import espol.edu.ec.Objetos.Playlist;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Julio Alvia
 */
public class PaginaPrincipalController implements Initializable {

    @FXML private  Label turno1;//=new Label();
    @FXML private  Label turno2;//=new Label();
    @FXML private  Label sala1_lbl=new Label();
    @FXML private  Label sala2_lbl=new Label();
    @FXML private MediaView media;
    @FXML private Button agregar_paciente;
    @FXML private Button sala1;
    @FXML private Button sala2;
    @FXML private GridPane pane;
    private static MediaPlayer mediaPlayerActual;
    private static MediaPlayer nextPlayer;
    private static MediaPlayer playerActual;
    static Consultorio consultorio=new Consultorio();
    
    
    
    
    @FXML
    private void agregarPaciente(ActionEvent event) throws IOException {
        //((Node)(event.getSource())).getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("AgregarPaciente.fxml"));
        
        Stage stage=AgregarPacienteController.getStage1();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Agregar Paciente");
        stage.show();
    }
    
    @FXML
    private void atenderSala1(ActionEvent event) throws IOException {
        if (PaginaPrincipalController.getConsultorio().getColaPacientes().peek() == null)
        {
            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setTitle("Alerta");
            alerta.setHeaderText(null);
            alerta.setContentText("No existen pacientes en espera.");

            alerta.showAndWait();
        }
        else
        {
            //((Node)(event.getSource())).getScene().getWindow().hide();
            Stage stage=AgregarDiagnosticoSala1Controller.getStage1();
            Parent root = FXMLLoader.load(getClass().getResource("AgregarDiagnosticoSala1.fxml"));
            //Stage stage=new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Agregar diagnóstico");
            stage.show();
        }
    }
    
    @FXML
    private void atenderSala2(ActionEvent event) throws IOException {
        if (PaginaPrincipalController.getConsultorio().getColaPacientes().peek() == null)
        {
            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setTitle("Alerta");
            alerta.setHeaderText(null);
            alerta.setContentText("No existen pacientes en espera.");

            alerta.showAndWait();
        }
        else
        {
            //((Node)(event.getSource())).getScene().getWindow().hide();
            Parent root = FXMLLoader.load(getClass().getResource("AgregarDiagnosticoSala2.fxml"));
            Stage stage=AgregarDiagnosticoSala2Controller.getStage1();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Agregar diagnóstico");
            stage.show();
        }
    }
    
    public void modificarTurnos() {
        if (PaginaPrincipalController.consultorio.getColaPacientes().isEmpty()) {
            
        } else if(PaginaPrincipalController.consultorio.getColaPacientes().size() == 1){
            //Label lbl=new Label(PaginaPrincipalController.consultorio.getColaPacientes().peek().getTurno());
            turno1=new Label(PaginaPrincipalController.consultorio.getColaPacientes().peek().getTurno());
            
            //pane.getChildren().add(lbl);//add(lbl, 0, 1);
            //pane_abajo.getChildren().add(pane);
            //turno1.setText(PaginaPrincipalController.consultorio.getColaPacientes().peek().getTurno());
            //turno1.setVisible(true);
        }else{
            Paciente tmp1=PaginaPrincipalController.consultorio.getColaPacientes().poll();
            Paciente tmp2=PaginaPrincipalController.consultorio.getColaPacientes().poll();
            turno1.setText(tmp1.getTurno());
            turno2.setText(tmp2.getTurno());
            PaginaPrincipalController.consultorio.agregarPaciente(tmp1);
            PaginaPrincipalController.consultorio.agregarPaciente(tmp2);
        }
    }

    private void inicializar(){
        /**if(PaginaPrincipalController.consultorio.getColaPacientes().peek()==null){
            PaginaPrincipalController.consultorio=new Consultorio();
        }else{
            this.turno1.setText(PaginaPrincipalController.consultorio.getColaPacientes().peek().getTurno());
            
        }*/
        modificarTurnos();
        final Playlist playlist = new Playlist("media.txt");
        mediaPlayerActual = (MediaPlayer)playlist.getLista().next();
        media.setMediaPlayer(mediaPlayerActual);// = new MediaView(mediaPlayerActual);
        
        mediaPlayerActual = (MediaPlayer)playlist.getLista().next();
        for(int i = 0; i <playlist.getLista().size(); i++)
        {
            nextPlayer = (MediaPlayer) playlist.getLista().get(i);
                mediaPlayerActual.setOnEndOfMedia(new Runnable() {
                    @Override
                    public void run() {
                        media.setMediaPlayer(nextPlayer);
                        nextPlayer.play();
                    }
                });
            
        }
        
        media.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                playerActual = media.getMediaPlayer();
                nextPlayer = (MediaPlayer)playlist.getLista().next();
                media.setMediaPlayer(nextPlayer);
                playerActual.stop();
                nextPlayer.play();

            }
        });


        media.setMediaPlayer(mediaPlayerActual);
        media.getMediaPlayer().play();
        
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //SI QUIERES QUE EL VIDEO SE REPRODUSCA QUITA EL COMENTARIO DE ABAJO Y PON EN LA CARPETA SRC EL MEDIA
        //QUE ENVIO JUAN
        //inicializar();
        
    }
    
    public  Label getSala1_lbl() {
        return sala1_lbl;
    }

    public  void setSala1_lbl(Label sala1_lbl) {
        this.sala1_lbl = sala1_lbl;
    }

    public  Label getSala2_lbl() {
        return sala2_lbl;
    }

    public  void setSala2_lbl(Label sala2_lbl) {
        this.sala2_lbl = sala2_lbl;
    }
    
    public PaginaPrincipalController() {
    }

    public static Consultorio getConsultorio() {
        return consultorio;
    }


    public Label getTurno1() {
        return turno1;
    }

    public void setTurno1(String turno1) {
        this.turno1 =new Label(turno1);
    }

    public Label getTurno2() {
        return turno2;
    }

    public void setTurno2(String turno2) {
        this.turno2 =new Label(turno2);
    }

    public Button getSala1() {
        return sala1;
    }

    public void setSala1(Button sala1) {
        this.sala1 = sala1;
    }

    public Button getSala2() {
        return sala2;
    }

    public void setSala2(Button sala2) {
        this.sala2 = sala2;
    }

    

    public MediaView getMedia() {
        return media;
    }

    public void setMedia(MediaView media) {
        this.media = media;
    }
}
