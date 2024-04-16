package co.edu.uniquindio.citas.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString

public class Paciente {
    private String nombre;
    private String identificacion;
    public Paciente(String nombre, String identificacion) {
        this.nombre = nombre;
        this.identificacion = identificacion;
    }



}
