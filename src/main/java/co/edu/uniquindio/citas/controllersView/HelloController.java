package co.edu.uniquindio.citas.controllersView;

import co.edu.uniquindio.citas.Citas;
import co.edu.uniquindio.citas.controller.Controller;
import co.edu.uniquindio.citas.model.Cita;
import co.edu.uniquindio.citas.model.Paciente;
import co.edu.uniquindio.citas.model.enumeraciones.TipoCita;
import javafx.animation.Animation;
import javafx.animation.ParallelTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.Transition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class HelloController implements Initializable {

    @FXML
    private ChoiceBox<TipoCita> boxTipoCita;

    @FXML
    private Button btnCancelarOModificar;

    @FXML
    private Button btnConsultarCita;

    @FXML
    private Button btnSolicitarCita;

    @FXML
    private Button btnValidarG;

    @FXML
    private Button btnValidarGAux;

    @FXML
    private Button btnVolver;

    @FXML
    private Label lbtextoBienvenida;

    @FXML
    private Pane paneOpciones;

    @FXML
    private Pane paneDatos;

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

    private Controller controller = new Controller();


    private double originalX;
    private double originalY;

    @FXML
    private Label labelMensajeG = new Label();

    @FXML
    private Pane panePaderSolicitarCita;
    @FXML
    private Pane panePaderSolicitarCitaAux;

    @FXML
    private Pane panePrincipalSolicitarCita;


    @FXML
    private Label lblInformativoG;

    @FXML
    private ImageView imgRegistro;

    private double cordenadasXimg;
    private double cordenadasYimg;


    private LocalDateTime fechaAsignacion;

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy 'a las' HH:mm:ss");

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
    void dirigirInicio(ActionEvent event) {
        // Restaurar las coordenadas originales de btnVolver
        btnVolver.setLayoutX(originalX);
        btnVolver.setLayoutY(originalY);

        TabPane tabPane = tapInicio.getTabPane();

        // Seleccionar la pestaña de inicio
        if (tabPane != null) {
            tabPane.getSelectionModel().select(tapInicio);
        }

        // Remover la pestaña de solicitar cita si existe
        if (tabPane.getTabs().contains(tapSolicitarCita)) {
            tabPane.getTabs().remove(tapSolicitarCita);
        }

        // Verificar si paneAux no es nulo y si no está ya presente en panePaderSolicitarCita
        if (paneDatos != null && !panePaderSolicitarCita.getChildren().contains(paneDatos)) {
            // Agregar paneAux a panePaderSolicitarCita si no está presente y no es nulo
            panePaderSolicitarCita.getChildren().add(paneDatos);
        }
        // Verificar si btnValidar no es nulo y si no está ya presente en panePaderSolicitarCita
        if (btnValidarG != null && !panePaderSolicitarCita.getChildren().contains(btnValidarG)) {
            // Agregar btnValidar a panePaderSolicitarCita si no está presente y no es nulo
            panePaderSolicitarCita.getChildren().add(btnValidarG);
        }
        //vuelven los datos prederteminados
        lblInformativoG.setText("Ingresa los siguientes datos para agendar tu turno:");
        imgRegistro.setLayoutY(cordenadasYimg);
        panePaderSolicitarCita.getChildren().remove(labelMensajeG);
        vaciarCampos();

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
        boolean encontroCedula = false;
        if (validarCamposSolicitarCampos()) {

            if (controller.verificarSiEsAfiliado(txtIdA.getText())) {


                // se contruye el paciente y la cita
                String numCIta = controller.numeroCita(boxTipoCita.getValue());
                fechaAsignacion = controller.obtenerFechas(boxTipoCita.getValue());

                Paciente paciente = new Paciente(txtNombreA.getText(), txtIdA.getText());
                Cita cita = new Cita(paciente, boxTipoCita.getValue(), numCIta, fechaAsignacion);
                reOrganizarInterfazSolicitarCita(txtIdA.getText(), numCIta, fechaAsignacion);
                encontroCedula = true;

                //la patron



            } else {
                if (!encontroCedula) {
                    mostrarMensaje(Alert.AlertType.WARNING, "La cedula no ha sido encontrada", """
                            su cedula no ha sido encontrada,
                            verifique su cedula o
                            puede que no este afiliado
                            comuniquese con nuestra
                            area de afiliacion nuevo eps
                            atra ves del numero (10080003)""");
                }
            }

        }

    }

    private void vaciarCampos() {
        txtNombreA.setText("");
        txtIdA.setText("");
    }
    //---------------------------------





    //-----------------------



    private void reOrganizarInterfazSolicitarCita(String cedula, String numCita, LocalDateTime fechaAsignacion) {
        //se guardan las coordenadas del pane
        originalX = btnVolver.getLayoutX();
        originalY = btnVolver.getLayoutY();
        //se agregan los objetos de interfaz que se van a quitar
        if (paneDatos != null && !panePaderSolicitarCita.getChildren().contains(paneDatos)) {
            panePrincipal.getChildren().add(paneDatos);
        }
        if (btnValidarG != null && !panePaderSolicitarCita.getChildren().contains(btnValidarG)) {
            panePrincipal.getChildren().add(btnValidarG);
        }
        // se mueven los botones para una nueva interfas
        btnVolver.setLayoutX(274);
        btnVolver.setLayoutY(261);

        panePaderSolicitarCita.getChildren().remove(paneDatos);
        panePaderSolicitarCita.getChildren().remove(btnValidarG);

        lblInformativoG.setText("""
                Su cita ha sido registrada correctamente""");
        labelMensajeG.setText("su numero de cita es: " + numCita + "\nfue asignada para el dia: \n" + fechaAsignacion.format(formatter));
        panePaderSolicitarCita.getChildren().add(labelMensajeG);
        labelMensajeG.setLayoutY(80);
        labelMensajeG.setLayoutX(145);
        labelMensajeG.setStyle("-fx-font-size: 35px; -fx-font-weight: bold; -fx-underline: true;");
        cordenadasXimg = imgRegistro.getLayoutX();
        cordenadasYimg = imgRegistro.getLayoutY();
        imgRegistro.setLayoutX(101);






    }





    private boolean validarCamposSolicitarCampos() {
        boolean confirmacion = true;
        String mensaje = "";
        if (txtIdA.getText() == null || txtIdA.getText().equals("")) {
            mensaje += "Debe poner  su identificacion en el  campo identificacion\n";
        }
        if (txtNombreA.getText() == null || txtNombreA.getText().equals("")) {
            mensaje += "Debe poner su nombre en el campo Nombre\n";
        }

        if (mensaje != "") {
            confirmacion = false;
            mostrarMensaje(Alert.AlertType.INFORMATION, "Faltan campos Por rellenar", mensaje);
        }
        return confirmacion;
    }

    private void mostrarMensaje(Alert.AlertType type, String title, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tabPane.getTabs().remove(tapSolicitarCita);
        tabPane.getTabs().remove(tapCancelarModificar);
        tabPane.getTabs().remove(tapConsultar);


        originalX = btnVolver.getLayoutX();
        originalY = btnVolver.getLayoutY();

        ObservableList<TipoCita> tiposCitaList = FXCollections.observableArrayList(TipoCita.values());
        boxTipoCita.setItems(tiposCitaList);
        // Establecer una opción predeterminada
        boxTipoCita.setValue(TipoCita.MEDICO_GENERAL);


    }


}