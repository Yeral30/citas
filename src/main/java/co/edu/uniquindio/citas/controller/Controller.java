package co.edu.uniquindio.citas.controller;

import co.edu.uniquindio.citas.Citas;
import co.edu.uniquindio.citas.model.enumeraciones.TipoCita;

import java.time.LocalDateTime;

public class Controller {

    private Citas citas= new Citas();

    //verifica si es afiliado o no
    public boolean verificarSiEsAfiliado(String cedulaInterfaz){
        return citas.verificarSiEsAfiliado(cedulaInterfaz);
    }

    public String numeroCita(TipoCita tipoCita){
        return citas.AsignarNumeroCita(tipoCita);
    }

    public LocalDateTime obtenerFechas(TipoCita tipoCita){
        return citas.aiginarFehaCita(tipoCita);
    }

}
