package co.edu.uniquindio.citas;

import co.edu.uniquindio.citas.model.enumeraciones.Prioridad;
import co.edu.uniquindio.citas.model.enumeraciones.TipoCita;
import javafx.fxml.Initializable;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

public class Citas {
    private String numeroCita;
    private int numeroMedicoGeneral = 0;
    private int numeroCirugiaProgramada = 0;
    private int numeroUrgencia = 0;
    private LocalDateTime fechaAsignacionGeneral = LocalDateTime.now();
    ;
    private LocalDateTime fechaAsignacionProgramadas = LocalDateTime.now();
    ;
    private LocalDateTime fechaAsignacionUrgencias = LocalDateTime.now();
    ;


    private ArrayList<String> cedulas = new ArrayList<>();

    public ArrayList<String> getCedulas() {
        return cedulas;
    }

    public ArrayList<String> cargarCedula() {
        cedulas.add("123");
        cedulas.add("12");
        cedulas.add("1");
        return cedulas;
    }


    public boolean verificarSiEsAfiliado(String cedulaInterfaz) {
        cargarCedula();
        for (int i = 0; i < cedulas.size(); i++) {
            if (cedulas.get(i).equals(cedulaInterfaz)) {
                return true;
            }
        }
        return false;
    }

    /**
     * - Si la prioridad del tipo de cita es menor que la prioridad "ALTA",
     * 'compareTo()' devolverá un valor negativo.
     * - Si son iguales, devolverá cero.
     * - Si la prioridad del tipo de cita es mayor que la prioridad "ALTA",
     * devolverá un valor positivo.
     *
     * @param tipoCita
     * @return
     */
    public String AsignarNumeroCita(TipoCita tipoCita) {
        String numeroCita = "";
        if (tipoCita.getPrioridad().compareTo(Prioridad.ALTA) == 0) {
            numeroCita = "U" + numeroUrgencia;
            numeroUrgencia++;
        } else if (tipoCita.getPrioridad().compareTo(Prioridad.MEDIA) == 0) {
            numeroCita = "C" + numeroCirugiaProgramada;
            numeroCirugiaProgramada++;
        } else {
            numeroCita = "G" + numeroMedicoGeneral;
            numeroMedicoGeneral++;

        }


        return numeroCita;
    }

    //asignacion fecha
    public LocalDateTime aiginarFehaCita(TipoCita tipoCita) {
        if (tipoCita.getPrioridad().compareTo(Prioridad.ALTA) == 0) {
            fechaAsignacionUrgencias=incrementarHora(fechaAsignacionUrgencias,30);

            return fechaAsignacionUrgencias;
        } else if (tipoCita.getPrioridad().compareTo(Prioridad.MEDIA) == 0) {

            fechaAsignacionProgramadas=incrementarHora(fechaAsignacionProgramadas,25);
            return fechaAsignacionProgramadas;
        } else {
            fechaAsignacionGeneral=incrementarHora(fechaAsignacionGeneral,10);
            return fechaAsignacionGeneral;
        }

    }

    private  LocalDateTime incrementarHora(LocalDateTime fecha, int minutos) {
        return fecha.plusMinutes(minutos);
    }


    public void setCedulas(ArrayList<String> cedulas) {
        this.cedulas = cedulas;
    }


}
