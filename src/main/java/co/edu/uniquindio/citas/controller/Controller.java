package co.edu.uniquindio.citas.controller;

import co.edu.uniquindio.citas.Citas;
import co.edu.uniquindio.citas.model.Cita;
import co.edu.uniquindio.citas.model.Paciente;
import co.edu.uniquindio.citas.model.enumeraciones.TipoCita;

import java.time.LocalDateTime;

public class Controller {
    private Citas citas = new Citas();

    public  Cita obtenerCita(Cita cita) {

        return citas.verificarNumeroCita(cita);
    }



    // Verifica si es afiliado o no
    public boolean verificarSiEsAfiliado(String cedulaInterfaz){
        return citas.verificarSiEsAfiliado(cedulaInterfaz);
    }

    // Asigna un número de cita teniendo un paciente
    public Cita asignarCita(TipoCita tipoCita, String nombrePaciente, String idPaciente, LocalDateTime fecha) {

        return citas.CreasCita(tipoCita,idPaciente,nombrePaciente,fecha); // Llama al método correcto con ambos parámetros.
    }

    // Obtiene la fecha asignada a una cita
    public LocalDateTime asignarFechaCita(TipoCita tipoCita) {
        return citas.asignarUrgencias(tipoCita);
    }




    // se crea un objeto cita aux para poder mandar la informacion hasta la clase citas
    public Cita consultarCita(String cedula, TipoCita tipoCita) {

        return citas.verificarCita(new Cita(new Paciente("", cedula),tipoCita));
    }
}

