package co.edu.uniquindio.citas.model;

import co.edu.uniquindio.citas.model.enumeraciones.Prioridad;
import co.edu.uniquindio.citas.model.enumeraciones.TipoCita;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Cita {

    private Paciente paciente;
    private String numeroCita;
    private LocalDateTime fechaCita;
    private TipoCita tipoCita;




    public Cita(Paciente paciente, TipoCita tipoCita, String numeroCita, LocalDateTime programacionCita) {
        fechaCita=programacionCita;
        this.paciente = paciente;
        this.numeroCita=numeroCita;
        this.tipoCita = tipoCita;

    }









    public Paciente getPaciente() {
        return paciente;
    }

    public String getNumeroCita() {
        return numeroCita;
    }



    public TipoCita getTipoCita() {
        return tipoCita;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public void setNumeroCita(String numeroCita) {
        this.numeroCita = numeroCita;
    }



    public void setTipoCita(TipoCita tipoCita) {
        this.tipoCita = tipoCita;
    }
}
