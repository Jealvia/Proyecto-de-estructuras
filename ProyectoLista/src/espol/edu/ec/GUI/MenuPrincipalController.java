package espol.edu.ec.GUI;

import espol.edu.ec.Objetos.Playlist;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MenuPrincipalController implements Initializable{
    private static MediaPlayer mp;
    private static MediaView mv;
    private static Media media;
    //private static Playlist playlist;
    private static MediaPlayer mediaPlayerActual;
    private static MediaPlayer playerActual;
    private static MediaPlayer nextPlayer;
    public static Label turno1;
    @FXML
    private void agregarPaciente(ActionEvent event) throws IOException
    {
        ((Node)(event.getSource())).getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("AgregarPaciente.fxml"));
        Stage stage=new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Agregar Paciente");
        stage.setOnCloseRequest(event1 -> regresarAMenuPrincipal(stage));
        stage.show();
    }

    @FXML
    private void agregarDiagnosticoSala2(ActionEvent event) throws IOException
    {
        if (Main.getConsultorio().getColaPacientes().peek() == null)
        {
            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setTitle("Alerta");
            alerta.setHeaderText(null);
            alerta.setContentText("No existen pacientes en espera.");

            alerta.showAndWait();
        }
        else
        {
            ((Node)(event.getSource())).getScene().getWindow().hide();
            Parent root = FXMLLoader.load(getClass().getResource("AgregarDiagnosticoSala2.fxml"));
            Stage stage=new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Agregar diagnóstico");
            stage.setOnCloseRequest(event1 -> regresarAMenuPrincipal(stage));
            stage.show();
        }

    }

    @FXML
    private void agregarDiagnosticoSala1(ActionEvent event) throws IOException
    {
        if (Main.getConsultorio().getColaPacientes().peek() == null)
        {
            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setTitle("Alerta");
            alerta.setHeaderText(null);
            alerta.setContentText("No existen pacientes en espera.");

            alerta.showAndWait();
        }
        else
        {
            ((Node)(event.getSource())).getScene().getWindow().hide();
            Parent root = FXMLLoader.load(getClass().getResource("AgregarDiagnosticoSala1.fxml"));
            Stage stage=new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Agregar diagnóstico");
            stage.setOnCloseRequest(event1 -> regresarAMenuPrincipal(stage));
            stage.show();
        }

    }

    @FXML
    private void verTurnos(ActionEvent event) throws IOException
    {
        ((Node)(event.getSource())).getScene().getWindow().hide();
        Stage primaryStage = new Stage();
        GridPane backPane = new GridPane();
        GridPane rightPane = new GridPane();
        //Group leftVideo = new Group();

        rightPane.setHgap(100);
        rightPane.setVgap(100);
        rightPane.setPadding(new Insets(10, 10, 10, 10));

        final Playlist playlist = new Playlist("media.txt");
        mediaPlayerActual = (MediaPlayer)playlist.getLista().next();
        mv = new MediaView(mediaPlayerActual);
        //DoubleProperty width = mv.fitWidthProperty();
        //DoubleProperty height = mv.fitHeightProperty();
        //width.bind(Bindings.selectDouble(mv.sceneProperty(), "width"));
        //height.bind(Bindings.selectDouble(mv.sceneProperty(), "height"));
        Pane videoPane = new Pane();
        videoPane.getChildren().add(mv);
        videoPane.setStyle("-fx-background-color: #333333");
        mv.setFitHeight(600);
        rightPane.setAlignment(Pos.CENTER);



        Label turno = new Label("TURNO: ");
        turno1 = new Label("Turno 1");
        Label turno2 = new Label("Turno 2");
        Label consultorio = new Label("CONSULTORIO: ");
        Label consultorio1 = new Label("Consultorio 1");
        Label consultorio2 = new Label("Consultorio 2");



        rightPane.add(turno,0,0,1,1);
        rightPane.add(turno1,0,1,1,1);
        rightPane.add(turno2,0,2,1,1);
        rightPane.add(consultorio,1,0,1,1);
        rightPane.add(consultorio1,1,1,1,1);
        rightPane.add(consultorio2,1,2,1,1);
        
        backPane.add(videoPane, 0, 0, 1, 1);
        backPane.add(rightPane,1,0,1,1);
        //ColumnConstraints halfConstraint = ColumnConstraintsBuilder.create().percentWidth(50).build();
        //backPane.getColumnConstraints().addAll(halfConstraint, halfConstraint);


        Scene scene = new Scene(backPane, 1500,600);
        //videoPane.setMaxHeight((scene.getHeight())/2);
        //videoPane.setMaxWidth((scene.getWidth())/2);
        primaryStage.setOnCloseRequest(event1 -> regresarAMenuPrincipal(primaryStage, mediaPlayerActual));

        primaryStage.setScene(scene);
        primaryStage.show();

        mediaPlayerActual = (MediaPlayer)playlist.getLista().next();
        for(int i = 0; i <playlist.getLista().size(); i++)
        {
            
            nextPlayer = (MediaPlayer)playlist.getLista().next();
            mediaPlayerActual.setOnEndOfMedia(new Runnable() {
                @Override
                public void run() {
                    playerActual=mv.getMediaPlayer();//.setMediaPlayer(nextPlayer);
                    nextPlayer = (MediaPlayer)playlist.getLista().next();
                    mv.setMediaPlayer(nextPlayer);
                    nextPlayer.play();
                }
            });
            //System.out.println("Actual"+mediaPlayerActual);
            //System.out.println("Siguiente: "+nextPlayer);
            //mediaPlayerActual = nextPlayer;
            //System.out.println("ActualDespuesC: "+mediaPlayerActual);
        }
        /*
        nextPlayer = (MediaPlayer) playlist.getLista().next();
        mediaPlayerActual.setOnEndOfMedia(new Runnable() {
            @Override
            public void run() {
                mv.setMediaPlayer(nextPlayer);
                nextPlayer.play();
                mediaPlayerActual = nextPlayer;
            }
        });
        */

        mv.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                playerActual = mv.getMediaPlayer();
                nextPlayer = (MediaPlayer)playlist.getLista().next();
                mv.setMediaPlayer(nextPlayer);
                playerActual.stop();
                nextPlayer.play();

            }
        });


        mv.setMediaPlayer(mediaPlayerActual);
        mv.getMediaPlayer().play();
    }

    public void regresarAMenuPrincipal(Stage primaryStage, MediaPlayer mediap)
    {
        //((Node)(event.getSource())).getScene().getWindow().hide();
        mv.getMediaPlayer().stop();
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
    public void regresarAMenuPrincipal(Stage primaryStage)
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


}
