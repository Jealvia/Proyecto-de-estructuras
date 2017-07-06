package espol.edu.ec.GUI;/**
 * Created by User on 02/07/2017.
 */

import espol.edu.ec.Objetos.Playlist;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

public class MainVideo extends Application {

    private static MediaPlayer mp;
    private static MediaView mv;
    private static Media media;
    private static MediaPlayer mediaPlayerActual;
    private static MediaPlayer nextPlayer;
    private static MediaPlayer playerActual;
    public static  Label turno1;
    private static  Label turno2;

    public static void main(String[] args) {
        launch(args);
    }


    public static void setTurno1(String turno) {
        MainVideo.turno1.setText(turno);
    }

    public static void setTurno2(String turno) {
        MainVideo.turno1.setText(turno);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        GridPane backPane = new GridPane();
        GridPane rightPane = new GridPane();
        //Group leftVideo = new Group();

        rightPane.setHgap(100);
        rightPane.setVgap(100);
        rightPane.setPadding(new Insets(10, 10, 10, 10));
        /*
        String path = "src/media/vid001.mp4";
        media = new Media(Paths.get(path).toUri().toString());
        mp = new MediaPlayer(media);
        mv = new MediaView(mp);
        mp.setAutoPlay(true);
        TimeUnit.MILLISECONDS.sleep(1000);
        mp.stop();
        */
        final Playlist playlist = new Playlist("media.txt");
        mediaPlayerActual = (MediaPlayer)playlist.getLista().next();
        mv = new MediaView(mediaPlayerActual);
        
        Pane videoPane = new Pane();
        videoPane.getChildren().add(mv);
        videoPane.setStyle("-fx-background-color: #333333");
        mv.setFitHeight(600);
        rightPane.setAlignment(Pos.CENTER);



        Label turno = new Label("TURNO: ");
        turno1 = new Label("Turno 1");
        turno2 = new Label("Turno 2");
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
        Scene scene = new Scene(backPane, 1500,600);

        primaryStage.setScene(scene);
        primaryStage.show();
        mediaPlayerActual = (MediaPlayer)playlist.getLista().next();
        for(int i = 0; i <playlist.getLista().size(); i++)
        {
            nextPlayer = (MediaPlayer) playlist.getLista().get(i);
                mediaPlayerActual.setOnEndOfMedia(new Runnable() {
                    @Override
                    public void run() {
                        mv.setMediaPlayer(nextPlayer);
                        nextPlayer.play();
                    }
                });
            
        }
     
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
}
