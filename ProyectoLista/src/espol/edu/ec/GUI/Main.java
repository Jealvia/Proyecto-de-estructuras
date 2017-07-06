package espol.edu.ec.GUI;

import espol.edu.ec.Objetos.Consultorio;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {
    private static Consultorio consultorio;
    private static int atendidos, ultimoConsultorio;
    //private static Paciente pacienteSala1, pacienteSala2;

    @Override
    public void start(Stage primaryStage)  throws Exception{
        consultorio = new Consultorio();
        ultimoConsultorio = 2;
        
        Parent root = FXMLLoader.load(getClass().getResource("MenuPrincipal.fxml"));
        primaryStage.setTitle("Menu Principal");
        primaryStage.setScene(new Scene(root, 407, 220));
        primaryStage.setMinHeight(220);
        primaryStage.setMinWidth(407);
        primaryStage.setResizable(false);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);

    }

    public static Consultorio getConsultorio() {
        return consultorio;
    }

    public static void setConsultorio(Consultorio consultorio) {
        Main.consultorio = consultorio;
    }

    public static int getAtendidos() {
        return atendidos;
    }

    public static void setAtendidos(int atendidos) {
        Main.atendidos = atendidos;
    }
/*
    public static Paciente getPacienteSala1() {
        return pacienteSala1;
    }

    public static void setPacienteSala1(Paciente pacienteSala1) {
        Main.pacienteSala1 = pacienteSala1;
    }

    public static Paciente getPacienteSala2() {
        return pacienteSala2;
    }

    public static void setPacienteSala2(Paciente pacienteSala2) {
        Main.pacienteSala2 = pacienteSala2;
    }
*/
    public static int getUltimoConsultorio() {
        return ultimoConsultorio;
    }

    public static void setUltimoConsultorio(int ultimoConsultorio) {
        Main.ultimoConsultorio = ultimoConsultorio;
    }
}
