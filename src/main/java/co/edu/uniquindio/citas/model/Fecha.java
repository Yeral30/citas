package co.edu.uniquindio.citas.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Fecha {
    private LocalDate dia;
    private ArrayList<String> listaHoras;


    public ArrayList<String>eliminarFecha(String fechaSeleccionada){
        listaHoras.remove(fechaSeleccionada);
        return listaHoras;
    }
    public Fecha(LocalDate dia) {
        this.dia = dia;
        listaHoras=new ArrayList<>();
        for (int hora = 6; hora <= 18; hora++) {
            listaHoras.add(String.format("%02d:00", hora));
        }

    }
    public void añadirHora(String hora){
        listaHoras.add(hora);
    }

    public void imprimirListaHoras(){
        for (int i = 0; i <listaHoras.size() ; i++) {
            System.out.println(listaHoras.get(i));
        }
    }

    public Fecha() {
    }



}
