package espol.edu.ec.Objetos;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by User on 01/07/2017.
 */
public class Paciente {
    private String nombre, apellido, diagnostico, cedula, turno;
    private char genero;
    private int edad;
    private Sintoma sintoma;

    public Paciente(String cedula,String nombre, String apellido, char genero, int edad, Sintoma sintoma) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.genero = genero;
        this.edad = edad;
        this.sintoma = sintoma;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public char getGenero() {
        return genero;
    }

    public void setGenero(char genero) {
        this.genero = genero;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public Sintoma getSintoma() {
        return sintoma;
    }

    public void setSintoma(Sintoma sintoma) {
        this.sintoma = sintoma;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    @Override
    public String toString() {
        return "Cedula: " + cedula +
                ", Nombre: " + nombre +
                ", Apellido: " + apellido+
                ", Genero: " + genero +
                ", Edad: " + edad + ", "+sintoma;
    }

    public static boolean guardarPaciente(Paciente paciente)
    {
        try {
            FileWriter escribir = new FileWriter("pacientes.txt", true);
            PrintWriter escribirLinea = new PrintWriter(escribir);
            escribirLinea.printf("%s"+"%n", paciente.toString());
            escribirLinea.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
