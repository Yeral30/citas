package co.edu.uniquindio.citas.model;

import co.edu.uniquindio.citas.model.enumeraciones.Prioridad;
import co.edu.uniquindio.citas.model.enumeraciones.TipoCita;

import java.time.LocalDate;

public class Cita {

    private Paciente paciente;
    private String numeroCita;
    private LocalDate fechaCita;
    private TipoCita tipoCita;
    private static int numeroMedicoGeneral;
    private static int numeroCirugiaProgramada;
    private static int numeroUrgencia;



    public Cita(Paciente paciente, TipoCita tipoCita) {
        this.paciente = paciente;
        this.numeroCita = AsignarNumeroCita(tipoCita);
        this.fechaCita = AsignarfechaCita(tipoCita);
        this.tipoCita = tipoCita;

    }

    /**
     * - Si la prioridad del tipo de cita es menor que la prioridad "ALTA",
     * 'compareTo()' devolverá un valor negativo.
     *  - Si son iguales, devolverá cero.
     *  - Si la prioridad del tipo de cita es mayor que la prioridad "ALTA",
     *  devolverá un valor positivo.
     * @param tipoCita
     * @return
     */

    public String AsignarNumeroCita(TipoCita tipoCita){
        String numeroCita="";
         if(tipoCita.getPrioridad().compareTo(Prioridad.ALTA)==0){
             numeroCita= "U"+numeroUrgencia;
             numeroUrgencia++;
         } else if (tipoCita.getPrioridad().compareTo(Prioridad.MEDIA)==0) {
             numeroCita= "C"+numeroCirugiaProgramada;
             numeroCirugiaProgramada++;
         }else {
             numeroCita= "G"+numeroMedicoGeneral;
             numeroMedicoGeneral++;

         }


        return numeroCita;
    }


    /**
     * se necesita la lista donde se van almacenar a los pacientes para poder hacer
     * la logica para este metodo
     * @param tipoCita
     * @return
     */
    public LocalDate AsignarfechaCita(TipoCita tipoCita){
        LocalDate fecha = null;


        if(tipoCita.getPrioridad().compareTo(Prioridad.ALTA)==0){

            //logica
        } else if (tipoCita.getPrioridad().compareTo(Prioridad.MEDIA)==0) {
            //logica

        }else {
            //logica


        }

        return  fecha;

    }


    public Paciente getPaciente() {
        return paciente;
    }

    public String getNumeroCita() {
        return numeroCita;
    }

    public LocalDate getFechaCita() {
        return fechaCita;
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

    public void setFechaCita(LocalDate fechaCita) {
        this.fechaCita = fechaCita;
    }

    public void setTipoCita(TipoCita tipoCita) {
        this.tipoCita = tipoCita;
    }
}
