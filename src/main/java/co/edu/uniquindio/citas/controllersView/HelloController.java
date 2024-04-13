package co.edu.uniquindio.citas.controllersView;

import co.edu.uniquindio.citas.Citas;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class HelloController implements Initializable {

    @FXML
    private ChoiceBox<?> boxTipoCita;

    @FXML
    private Button btnCancelarOModificar;

    @FXML
    private Button btnConsultarCita;

    @FXML
    private Button btnSolicitarCita;

    @FXML
    private Button btnValidarG;

    @FXML
    private Button btnVolver;

    @FXML
    private Label lbtextoBienvenida;

    @FXML
    private Pane paneOpciones;

    @FXML
    private  Pane paneDatos;

    @FXML
    private Pane panePrincipal;

    @FXML
    private TabPane tabPane;

    @FXML
    private Tab tapCancelarModificar;

    @FXML
    private Tab tapConsultar;

    @FXML
    private Tab tapInicio;

    @FXML
    private Tab tapSolicitarCita;

    @FXML
    private TextField txtIdA;

    @FXML
    private TextField txtNombreA;

    private Citas citas = new Citas();
    private double originalX;
    private double originalY;


    @FXML
    private Label lblInformativoG;

    @FXML
    void dirigirseCancelarOModificar(ActionEvent event) {
        //para visualizar en la interfaz el tap antes de abrilo
        if (!tabPane.getTabs().contains(tapCancelarModificar)) {
            tabPane.getTabs().add(tapCancelarModificar);
        }
        TabPane tabPane = tapCancelarModificar.getTabPane();

        //sirve para pasar al tap solicitarCita
        if (tabPane != null) {
            tabPane.getSelectionModel().select(tapCancelarModificar);
        }

    }

    @FXML
    void dirigirseConsultarCita(ActionEvent event) {
        //para visualizar en la interfaz el tap antes de abrilo
        if (!tabPane.getTabs().contains(tapConsultar)) {
            tabPane.getTabs().add(tapConsultar);
        }
        TabPane tabPane = tapConsultar.getTabPane();

        //sirve para pasar al tap solicitarCita
        if (tabPane != null) {
            tabPane.getSelectionModel().select(tapConsultar);
        }

    }

    @FXML
    void dirigirInicio (ActionEvent event) {
        btnVolver.setLayoutX(originalX);
        btnVolver.setLayoutY(originalY);
        TabPane tabPane = tapInicio.getTabPane();

        //sirve para pasar al tap solicitarCita
        if (tabPane != null) {
            tabPane.getSelectionModel().select(tapInicio);
        }
        tabPane.getTabs().remove(tapSolicitarCita);




    }


    @FXML
    void dirigirseSolicitarCita(ActionEvent event) {
        //para visualizar en la interfaz el tap antes de abrilo
        if (!tabPane.getTabs().contains(tapSolicitarCita)) {
            tabPane.getTabs().add(tapSolicitarCita);
        }
        TabPane tabPane = tapSolicitarCita.getTabPane();

        //sirve para pasar al tap solicitarCita
        if (tabPane != null) {
            tabPane.getSelectionModel().select(tapSolicitarCita);
        }
    }
    @FXML
    void validarA(ActionEvent event) {
        originalX = btnVolver.getLayoutX();
        originalY = btnVolver.getLayoutY();
        panePrincipal.getChildren().add(paneDatos);
        panePrincipal.getChildren().add(btnValidarG);
        ArrayList<String> cedulas= citas.getCedulas();
        for (int i = 0; i <cedulas.size() ; i++) {
            if(cedulas.get(i).equals(txtIdA.getText())){

            }
        }
        btnVolver.setLayoutX(274);
        btnVolver.setLayoutY(261);
        panePrincipal.getChildren().remove(paneDatos);
        panePrincipal.getChildren().remove(btnValidarG);

        paneDatos = null;






    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tabPane.getTabs().remove(tapSolicitarCita);
        tabPane.getTabs().remove(tapCancelarModificar);
        tabPane.getTabs().remove(tapConsultar);

        }


}