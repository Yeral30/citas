package co.edu.uniquindio.citas;

import co.edu.uniquindio.citas.model.Cita;
import co.edu.uniquindio.citas.model.Paciente;
import co.edu.uniquindio.citas.model.enumeraciones.Prioridad;
import co.edu.uniquindio.citas.model.enumeraciones.TipoCita;

import java.time.LocalDateTime;
import java.util.*;

public class Citas {
    private String numeroCita;
    private int numeroMedicoGeneral = 1;
    private int numeroCirugiaProgramada = 1;
    private int numeroUrgencia = 1;
    private LocalDateTime fechaAsignacionGeneral = LocalDateTime.now();
    ;
    private LocalDateTime fechaAsignacionProgramadas = LocalDateTime.now();
    ;
    private LocalDateTime fechaAsignacionUrgencias = LocalDateTime.now();
    ;
    private ArrayList<String> cedulas = new ArrayList<>();

    private ArrayList<LocalDateTime>fechas= new ArrayList<>();




    public ArrayList<String> getCedulas() {
        return cedulas;
    }

    // Crear una PriorityQueue para manejar las citas con prioridad
    private PriorityQueue<Cita> colaDeCitas = new PriorityQueue<>(new Comparator<Cita>() {
        @Override
        public int compare(Cita c1, Cita c2) {
            return c2.getPrioridad().compareTo(c1.getPrioridad());
        }
    });

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
    private String asignarNumeroCita(TipoCita tipoCita) {
        String numeroCita = calcularNumeroCita(tipoCita);
        return numeroCita;
    }



    public Cita CreasCita(TipoCita tipoCita,String id, String nombre, LocalDateTime fecha){
        Cita cita= new Cita(new Paciente(nombre,id),tipoCita,asignarNumeroCita(tipoCita),fecha);
        colaDeCitas.add(cita);
        return  cita;
    }

    private String calcularNumeroCita(TipoCita tipoCita) {
        String numeroCita = "";
        if (tipoCita.getPrioridad() == Prioridad.ALTA) {
            numeroCita = "U" + numeroUrgencia++;
        } else if (tipoCita.getPrioridad() == Prioridad.MEDIA) {
            numeroCita = "C" + numeroCirugiaProgramada++;
        } else {
            numeroCita = "G" + numeroMedicoGeneral++;
        }
        return numeroCita;
    }

    //asignacion fecha
    public LocalDateTime asignarUrgencias(TipoCita tipoCita) {
        if (tipoCita.getPrioridad().compareTo(Prioridad.ALTA) == 0) {
            fechaAsignacionUrgencias = incrementarHora(fechaAsignacionUrgencias, 30);
            return fechaAsignacionUrgencias;
        } else if (tipoCita.getPrioridad().compareTo(Prioridad.MEDIA) == 0) {
            fechaAsignacionProgramadas = incrementarHora(fechaAsignacionProgramadas, 25);
            return fechaAsignacionProgramadas;
        } else {
            fechaAsignacionGeneral = incrementarHora(fechaAsignacionGeneral, 10);
            return fechaAsignacionGeneral;
        }
    }

    public void obtenerFecha(LocalDateTime asignacion){
        fechas.add(asignacion);

    }

    private LocalDateTime incrementarHora(LocalDateTime fecha, int minutos) {
        return fecha.plusMinutes(minutos);
    }

    public void setCedulas(ArrayList<String> cedulas) {
        this.cedulas = cedulas;
    }

    /**
     * verificamos si la cita existe o no existe
     * @param cita
     * @return
     */
    public Cita verificarCita(Cita cita) {

        Cita citaAux = null;
        Iterator<Cita> iterator = colaDeCitas.iterator();
        while (iterator.hasNext()) {
            System.out.println(cita.getPaciente().getNombre());
            Cita c = iterator.next();
            if (c.getTipoCita().compareTo(cita.getTipoCita()) == 0) {
                if (c.getPaciente().getIdentificacion().compareTo(cita.getPaciente().getIdentificacion())==0){
                    citaAux=c;
                }
            }
        }
        return citaAux;

    }
    public Cita verificarNumeroCita(Cita cita) {

        Cita citaAux = null;
        Iterator<Cita> iterator = colaDeCitas.iterator();
        while (iterator.hasNext()) {
            System.out.println(cita.getPaciente().getNombre());
            Cita c = iterator.next();
            if (c.getTipoCita().compareTo(cita.getTipoCita()) == 0) {
                System.out.println("condicion1");
                if (c.getPaciente().getIdentificacion().compareTo(cita.getPaciente().getIdentificacion())==0){
                    System.out.println("condicion2");

                   if (c.getNumeroCita().compareTo(cita.getNumeroCita())==0){
                       System.out.println("condicion3");
                      citaAux=c;


                    }
                }
            }
        }
        return citaAux;

    }


}
