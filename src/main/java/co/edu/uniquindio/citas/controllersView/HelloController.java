package co.edu.uniquindio.citas.controllersView;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {

    @FXML
    private Button btnCancelarOModificar;

    @FXML
    private Button btnConsultarCita;

    @FXML
    private Button btnSolicitarCita;

    @FXML
    private Button btnSolicitarCita1;

    @FXML
    private Button btnSolicitarCita11;

    @FXML
    private Label lbtextoBienvenida;
    @FXML
    private Pane paneOpciones;

    @FXML
    private  Pane panePrincipal;

    @FXML
    private Tab tapSolicitarCita=new Tab();


    @FXML
    private Tab tapConsultar  =new Tab();

    @FXML
    private Tab tapCancelarModificar=new Tab();
    @FXML
    private Tab tapInicio = new Tab();

    @FXML
    private  TabPane tabPane;

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



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tabPane.getTabs().remove(tapSolicitarCita);
        tabPane.getTabs().remove(tapCancelarModificar);
        tabPane.getTabs().remove(tapConsultar);

        }


}