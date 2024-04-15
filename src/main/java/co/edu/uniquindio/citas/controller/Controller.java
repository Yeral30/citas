package co.edu.uniquindio.citas.controller;

import co.edu.uniquindio.citas.Citas;
import co.edu.uniquindio.citas.model.Cita;
import co.edu.uniquindio.citas.model.Paciente;
import co.edu.uniquindio.citas.model.enumeraciones.TipoCita;

import java.time.LocalDateTime;

public class Controller {
    private Citas citas = new Citas();

    // Verifica si es afiliado o no
    public boolean verificarSiEsAfiliado(String cedulaInterfaz){
        return citas.verificarSiEsAfiliado(cedulaInterfaz);
    }

    // Asigna un número de cita teniendo un paciente
    public String asignarNumeroCita(TipoCita tipoCita, String nombrePaciente, String idPaciente) {
        Paciente paciente = new Paciente(nombrePaciente, idPaciente); // Crea un paciente con datos proporcionados.
        return citas.asignarNumeroCita(tipoCita, paciente); // Llama al método correcto con ambos parámetros.
    }

    // Obtiene la fecha asignada a una cita
    public LocalDateTime asignarFechaCita(TipoCita tipoCita) {
        return citas.asignarFechaCita(tipoCita);
    }




    // se crea un objeto cita aux para poder mandar la informacion hasta la clase citas
    public Cita consultarCita(String cedula, TipoCita tipoCita) {

        return citas.verificarCita(new Cita(new Paciente("", cedula),tipoCita));
    }
}

