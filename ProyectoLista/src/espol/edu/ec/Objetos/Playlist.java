package espol.edu.ec.Objetos;

import espol.edu.ec.TDAs.CircularLinkedList;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by User on 02/07/2017.
 */
public class Playlist {

    private CircularLinkedList<MediaPlayer> lista;

    public Playlist(String archivo)
    {
        this.lista = Playlist.readPlaylistFromFile(archivo);
    }

    public static CircularLinkedList<MediaPlayer> readPlaylistFromFile(String archivo)
    {
        CircularLinkedList<MediaPlayer> videos = new CircularLinkedList<>();
        try {
            Scanner sc = new Scanner(new File(archivo));
            while(sc.hasNext())
            {
                String linea = sc.nextLine();
                videos.addLast(new MediaPlayer(new Media(Paths.get(linea).toUri().toString())));

            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(String.class.getName()).log(Level.SEVERE, null, ex);
        }
        return videos;
    }

    public CircularLinkedList getLista() {
        return lista;
    }

    public void setLista(CircularLinkedList lista) {
        this.lista = lista;
    }

    @Override
    public String toString() {
        return "Playlist: "+lista;
    }
}
