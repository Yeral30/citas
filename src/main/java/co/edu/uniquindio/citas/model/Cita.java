package co.edu.uniquindio.citas.model;

import co.edu.uniquindio.citas.model.enumeraciones.Prioridad;
import co.edu.uniquindio.citas.model.enumeraciones.TipoCita;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString

public class Cita {

    private Paciente paciente;
    private String numeroCita;
    private LocalDateTime fechaCita;
    private TipoCita tipoCita;
    private Prioridad prioridad;




    public Cita(Paciente paciente, TipoCita tipoCita, String numeroCita, LocalDateTime programacionCita) {
        fechaCita=programacionCita;
        this.paciente = paciente;
        this.numeroCita=numeroCita;
        this.tipoCita = tipoCita;
        this.prioridad = tipoCita.getPrioridad();

    }
    public Cita (Paciente paciente,TipoCita tipoCita){
        this.paciente = paciente;
        this.tipoCita = tipoCita;
    }




}
