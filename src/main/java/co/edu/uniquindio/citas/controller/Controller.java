package co.edu.uniquindio.citas.controller;

import co.edu.uniquindio.citas.Citas;

public class Controller {

    private Citas citas= new Citas();

    //verifica si es afiliado o no
    public boolean verificarSiEsAfiliado(String cedulaInterfaz){
        return citas.verificarSiEsAfiliado(cedulaInterfaz);
    }

}
