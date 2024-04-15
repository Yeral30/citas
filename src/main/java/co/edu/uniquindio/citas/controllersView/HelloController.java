package co.edu.uniquindio.citas.controllersView;

import co.edu.uniquindio.citas.controller.Controller;
import co.edu.uniquindio.citas.model.Cita;
import co.edu.uniquindio.citas.model.enumeraciones.TipoCita;
import co.edu.uniquindio.citas.model.enumeraciones.TipoDocumento;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import javax.swing.*;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
        if (validarCamposSolicitarCampos()) { // Asegúrate de que este método está correctamente definido para validar campos
            String cedula = txtIdA.getText();
            String nombre = txtNombreA.getText();
            TipoCita tipoCita = boxTipoCita.getValue(); // Asegúrate de que boxTipoCita está correctamente inicializado

            if (controller.verificarSiEsAfiliado(cedula)) {
                // Construye el paciente y la cita directamente en el controlador
                String numCita = controller.asignarNumeroCita(tipoCita, nombre, cedula);  // Usando el nuevo método corregido
                LocalDateTime fechaAsignacion = controller.asignarFechaCita(tipoCita);  // Usando el nuevo método corregido

                // reOrganizarInterfazSolicitarCita puede actualizarse para manejar el display de la información
                reOrganizarInterfazSolicitarCita(cedula, numCita, fechaAsignacion);
                encontroCedula = true;
            } else {
                mostrarMensaje(Alert.AlertType.WARNING, "La cédula no ha sido encontrada", """
                        Su cédula no ha sido encontrada,
                        verifique su cédula o
                        puede que no esté afiliado.
                        Comuníquese con nuestra
                        área de afiliación de Nuevo EPS
                        a través del número (10080003).""");
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
        ObservableList<TipoDocumento> tiposDocumentoList = FXCollections.observableArrayList(TipoDocumento.values());
        boxTipoCita.setItems(tiposCitaList);
        boxTipoCitaConsultarCitas.setItems(tiposCitaList);
        boxTipoDocumentoConsultarCita.setItems(tiposDocumentoList);
        // Establecer una opción predeterminada
        boxTipoCita.setValue(TipoCita.MEDICO_GENERAL);
        boxTipoDocumentoConsultarCita.setValue(TipoDocumento.CEDULA_DE_CIUDADANIA);
        boxTipoCitaConsultarCitas.setValue(TipoCita.MEDICO_GENERAL);


    }


    //  ----------------------------------------  tab consultar cita  ---------------------------------------


    @FXML
    private ChoiceBox<TipoCita> boxTipoCitaConsultarCitas;

    @FXML
    private ChoiceBox<TipoDocumento> boxTipoDocumentoConsultarCita;

    @FXML
    private Button btnAceptarConsultarCita;


    @FXML
    private Button btnLimpiarCamposConsultarCita;


    @FXML
    private Button btnVolverConsultarCita;


    @FXML
    private ImageView imgRegistro2;


    @FXML
    private Label lblInformativoG2;


    @FXML
    private Pane paneDatos2;


    @FXML
    private TextField txtIdConsultarCita;


    @FXML
    void aceptarConsultar(ActionEvent event) {
//se verifica que los campos los esten llenando
        if (txtIdConsultarCita.getText() != null && !txtIdConsultarCita.getText().equals("")) {
/**
 *             se manda por parametro el numero de cedula y el tipo de cita para poder consultar y va hasta la clase citas donde esta
 *             la cola de prioridad y se verifica de que la cita exista ; si existe devulve la cita con la informacion en caso contrario devulve null
 */

            Cita cita = controller.consultarCita(txtIdConsultarCita.getText(), boxTipoCitaConsultarCitas.getValue());

            if (cita == null) {
                JOptionPane.showMessageDialog(null, "no tiene citas pendientes");
            } else {
                JOptionPane.showMessageDialog(null, "Señ@r " + cita.getPaciente().getNombre() + " con numero de cedula " +
                        cita.getPaciente().getIdentificacion() +
                        " \nverificamos en el sistema que efectivamente tiene una cita \n el numero de la cita es: " + cita.getNumeroCita()
                        + " la fecha y hora de su cita es: " + cita.getFechaCita());
            }
        } else {
            JOptionPane.showMessageDialog(null, "Debe de llenar todos los campos");

        }

    }

    @FXML
    private AnchorPane panePrincipalConsultarCita;


    @FXML
    void limpiarCamposConsultar(ActionEvent event) {

        txtIdConsultarCita.setText("");
        boxTipoCitaConsultarCitas.setValue(TipoCita.MEDICO_GENERAL);
        boxTipoDocumentoConsultarCita.setValue(TipoDocumento.CEDULA_DE_CIUDADANIA);

    }

    @FXML
    private AnchorPane panePaderConsultarCita;
    @FXML
    private Pane paneDatosConsultarCita;


}