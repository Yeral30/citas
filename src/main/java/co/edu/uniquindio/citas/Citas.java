package co.edu.uniquindio.citas;

import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

public class Citas {

    private ArrayList<String>cedulas=new ArrayList<>();

    public ArrayList<String> getCedulas() {
        return cedulas;
    }

    public ArrayList<String> cargarCedula(){
        cedulas.add("123");
        cedulas.add("12");
        cedulas.add("1");
        return cedulas;
    }




    public boolean verificarSiEsAfiliado(String cedulaInterfaz){
        cargarCedula();
        for (int i = 0; i < cedulas.size() ; i++) {
            if(cedulas.get(i).equals(cedulaInterfaz)){
                return true;
            }
        }
        return false;
    }









    public void setCedulas(ArrayList<String> cedulas) {
        this.cedulas = cedulas;
    }


}