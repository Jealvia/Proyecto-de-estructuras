package espol.edu.ec.Objetos;

import java.util.LinkedList;
import java.util.PriorityQueue;

/**
 * Created by User on 03/07/2017.
 */
public class Consultorio {

    private PriorityQueue<Paciente> colaPacientes;

    public Consultorio(LinkedList<Paciente> pacientes) {
        colaPacientes = new PriorityQueue<>((Paciente p1, Paciente p2) -> p2.getSintoma().getPrioridad() - p1.getSintoma().getPrioridad());
        colaPacientes.addAll(pacientes);
    }

    public Consultorio() {
        colaPacientes = new PriorityQueue<>((Paciente p1, Paciente p2) -> p1.getSintoma().getPrioridad() - p2.getSintoma().getPrioridad());
    }

    public Paciente getSiguiente() {
        return colaPacientes.poll();
    }

    public void agregarPaciente(Paciente paciente) {
        this.colaPacientes.offer(paciente);
    }

    public PriorityQueue<Paciente> getColaPacientes() {
        return colaPacientes;
    }

    @Override
    public String toString() {
        return "Consultorio: " + colaPacientes;
    }
}
