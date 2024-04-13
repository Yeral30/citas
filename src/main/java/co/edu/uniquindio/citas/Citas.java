package co.edu.uniquindio.citas;

import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

public class Citas implements Initializable {

    private ArrayList<String>cedulas=new ArrayList<>();

    public ArrayList<String> getCedulas() {
        return cedulas;
    }

    public void setCedulas(ArrayList<String> cedulas) {
        this.cedulas = cedulas;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cedulas.add("123");
        cedulas.add("12");
        cedulas.add("1");
    }
}