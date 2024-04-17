package co.edu.uniquindio.citas.controllersView;

import co.edu.uniquindio.citas.controller.Controller;
import co.edu.uniquindio.citas.model.Cita;
import co.edu.uniquindio.citas.model.Fecha;
import co.edu.uniquindio.citas.model.Paciente;
import co.edu.uniquindio.citas.model.enumeraciones.TipoCita;
import co.edu.uniquindio.citas.model.enumeraciones.TipoDocumento;
import co.edu.uniquindio.citas.model.enumeraciones.TipoProcedimiento;
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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ViewController implements Initializable {
    @FXML
    private ComboBox<String> boxHoras;
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


    private List<LocalDate> fechasSeleccionadas;
    private List<String> horasDisponibles;


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
        if (tabPane.getTabs().contains(tapCalendario)) {
            tabPane.getTabs().remove(tapCalendario);
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

    private LocalDateTime actualizarDateTime(DatePicker datePicker, ComboBox<String> comboBox) {
        LocalDate fechaSeleccionada = datePicker.getValue();
        String horaSeleccionada = comboBox.getValue();
        if (fechaSeleccionada != null && horaSeleccionada != null) {
            // Convertir la hora seleccionada a LocalTime
            LocalTime hora = LocalTime.parse(horaSeleccionada);

            // Crear LocalDateTime combinando la fecha y la hora
            LocalDateTime dateTime = fechaSeleccionada.atTime(hora);
            return dateTime;
        }
        return null;
    }


    @FXML
    void validarA(ActionEvent event) {

        if (validarCamposSolicitarCampos()) {
            btnModificarCalendario.setVisible(false);
            if (controller.verificarSiEsAfiliado(txtIdA.getText())) {
                if (boxTipoCita.getValue().equals(TipoCita.URGENCIA)) {
                    LocalDateTime fechaCita = controller.asignarFechaCita(TipoCita.URGENCIA);
                    Cita cita = controller.asignarCita(boxTipoCita.getValue(), txtNombreA.getText(), txtIdA.getText(), fechaCita);
                    reOrganizarInterfazSolicitarCita(cita.getPaciente().getIdentificacion(), cita.getNumeroCita(), cita.getFechaCita());
                    controller.imprirCitas();
                    System.out.println("-----------------------------");
                    if (!tabPane.getTabs().contains(tapSolicitarCita)) {
                        tabPane.getTabs().add(tapSolicitarCita);
                    }
                    TabPane tabPane = tapSolicitarCita.getTabPane();

                    //sirve para pasar al tap solicitarCita
                    if (tabPane != null) {
                        tabPane.getSelectionModel().select(tapSolicitarCita);
                    }

                } else {
                    dirigirCalendario();
                }
            }
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

    @FXML
    private Button btnModificarCalendario;

    @FXML
    void modificarCalendario(ActionEvent event) {

        LocalDate fechaSeleccionada = dateCalendario.getValue();// Asegúrate de que boxTipoCita está correctamente inicializado
        String horaSeleccionada = boxHoras.getValue();
        if (horaSeleccionada != null && fechaSeleccionada != null) {
            int solaHora = Integer.parseInt(horaSeleccionada.substring(0, 2));
            LocalDateTime nuevaFecha = fechaSeleccionada.atTime(solaHora, 0);
            String horaAnterior = String.valueOf(citaAux.getFechaCita()).substring(String.valueOf(citaAux.getFechaCita()).indexOf('T') + 1);
            System.out.println(horaAnterior);
            restablecerFechaCambioCita(fechaSeleccionada, horaAnterior);
                infoModificar(horaSeleccionada,fechaSeleccionada,nuevaFecha);
            btnAceptarCalendario.setVisible(true);
            limpiarCamposCancelar();
            if (!tabPane.getTabs().contains(tapCancelarModificar)) {
                tabPane.getTabs().add(tapCancelarModificar);
            }
            TabPane tabPane = tapCancelarModificar.getTabPane();

            //sirve para pasar al tap solicitarCita
            if (tabPane != null) {
                tabPane.getSelectionModel().select(tapCancelarModificar);
                JOptionPane.showMessageDialog(null, "La fecha de la cita a sido modificada con exito.");
            }
        }

    }

    private LocalDateTime infoModificar(String  horaSeleccionada,LocalDate fecha,LocalDateTime fechaNueva) {
        this.citaAux.setFechaCita(fechaNueva);
        for (int i = 0; i <  listFechas.size(); i++) {
            if(listFechas.get(i).getDia().equals(fecha)){
                listFechas.get(i).eliminarFecha(horaSeleccionada);
                break;
            }
        }

        return fechaNueva;
    }
    private void restablecerFechaCambioCita(LocalDate fecha, String hora){

        for (int i = 0; i <  listFechas.size(); i++) {
            if(listFechas.get(i).getDia().equals(fecha)){
                listFechas.get(i).añadirHora(hora);
                break;
            }
        }



    }


    @FXML
    void aceptarCalendario(ActionEvent event) {
        boolean encontroCedula = false;
        String cedula = txtIdA.getText();
        String nombre = txtNombreA.getText();
        TipoCita tipoCita = boxTipoCita.getValue();
        LocalDate fechaSeleccionada = dateCalendario.getValue();// Asegúrate de que boxTipoCita está correctamente inicializado
        LocalDateTime fecha = actualizarDateTime(dateCalendario, boxHoras);
        String horaSeleccionada = boxHoras.getValue();
        if (controller.verificarSiEsAfiliado(cedula)) {
            LocalDateTime fecha1 = fechaSeleccionada.atTime(Integer.parseInt(horaSeleccionada.substring(0, 2)), 0);

            // Construye el paciente y la cita directamente en el controlador
            Cita cita = controller.asignarCita(tipoCita, nombre, cedula, fecha);
            // reOrganizarInterfazSolicitarCita puede actualizarse para manejar el display de la información
            reOrganizarInterfazSolicitarCita(cedula, cita.getNumeroCita(), cita.getFechaCita());
            boxHoras.getSelectionModel().clearSelection();
            actualizarListasDespuesDeSeleccion(fechaSeleccionada, horaSeleccionada);
            encontroCedula = true;
            if (!tabPane.getTabs().contains(tapSolicitarCita)) {
                tabPane.getTabs().add(tapSolicitarCita);
            }
            TabPane tabPane = tapSolicitarCita.getTabPane();

            //sirve para pasar al tap solicitarCita
            if (tabPane != null) {
                tabPane.getSelectionModel().select(tapSolicitarCita);
            }

        }
        controller.imprirCitas();
        System.out.println("-----------------------------");
        btnModificarCalendario.setVisible(true);


    }


    private void vaciarCampos() {
        txtNombreA.setText("");
        txtIdA.setText("");
    }
    //---------------------------------


    //-----------------------
    private List<Fecha> listFechas;

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
        tabPane.getTabs().remove(tapCalendario);

        dateCalendario.setValue(LocalDate.now());
        listFechas = new ArrayList<>();
        //--------------------------------------
        // Establecer una fecha inicial predeterminada
        dateCalendario.setValue(LocalDate.now());

        // Configurar el DatePicker para que muestre el calendario directamente
        dateCalendario.setEditable(false); // Deshabilita la edición del campo de texto


//------------------------------------------
        originalX = btnVolver.getLayoutX();
        originalY = btnVolver.getLayoutY();

        ObservableList<TipoCita> tiposCitaList = FXCollections.observableArrayList(TipoCita.values());
        ObservableList<TipoDocumento> tiposDocumentoList = FXCollections.observableArrayList(TipoDocumento.values());
        ObservableList<TipoProcedimiento> tiposProcedimientoList = FXCollections.observableArrayList(TipoProcedimiento.values());

        boxTipoCita.setItems(tiposCitaList);
        boxTipoCitaConsultarCitas.setItems(tiposCitaList);
        boxTipoDocumentoConsultarCita.setItems(tiposDocumentoList);
        // Establecer una opción predeterminada
        boxTipoCita.setValue(TipoCita.MEDICO_GENERAL);
        boxTipoDocumentoConsultarCita.setValue(TipoDocumento.CEDULA_DE_CIUDADANIA);
        boxTipoCitaConsultarCitas.setValue(TipoCita.MEDICO_GENERAL);
        boxTipoDocumentoCancelar.setItems(tiposDocumentoList);
        boxTipoDocumentoCancelar.setValue(TipoDocumento.CEDULA_DE_CIUDADANIA);
        boxTipoProcedimientoCancelar1.setItems(tiposProcedimientoList);


        // Obtener el último día del año actual
        LocalDate lastDayOfYear = LocalDate.now().with(TemporalAdjusters.lastDayOfYear());

        // Establecer la fecha máxima como el último día del año actual
        dateCalendario.setDayCellFactory(picker -> new DateCell() {
            @Override
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                setDisable(empty || date.isAfter(lastDayOfYear));
            }
        });
        // Establecer la fecha mínima como la fecha actual
        dateCalendario.setDayCellFactory(picker -> new DateCell() {
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                setDisable(empty || date.isBefore(LocalDate.now()) || date.getYear() != LocalDate.now().getYear());
            }
        });




    }


    private void actualizarListasDespuesDeSeleccion(LocalDate fechaSeleccionada, String horaSeleccionada) {
        ArrayList<String> horasDisponibles = new ArrayList<>();
        boolean bandera = true;

        for (int i = 0; i < listFechas.size(); i++) {
            if (listFechas.get(i).getDia().equals(fechaSeleccionada)) {
                horasDisponibles = listFechas.get(i).eliminarFecha(horaSeleccionada);
                bandera = false; // es porque existe la fecha;
                break;
            }
        }
        if (bandera) {
            Fecha fecha = new Fecha(fechaSeleccionada);
            horasDisponibles = fecha.eliminarFecha(horaSeleccionada);
            listFechas.add(fecha);
        }




    }

    @FXML
    private Button btnreFrescarCalendario;

    @FXML
    void refrescarHoras(ActionEvent event) {
        ArrayList<String> horasDisponibles = new ArrayList<>();
        boolean bandera = true;
        for (int i = 0; i < listFechas.size(); i++) {
            if (listFechas.get(i).getDia().equals(dateCalendario.getValue())) {
                horasDisponibles = listFechas.get(i).getListaHoras();
                bandera = false; // es porque existe la fecha;
                break;
            }
        }
        if (bandera) {
            Fecha fecha = new Fecha(dateCalendario.getValue());
            listFechas.add(fecha);
            for (int i = 0; i < listFechas.size(); i++) {
                if (listFechas.get(i).getDia().equals(dateCalendario.getValue())) {
                    horasDisponibles = listFechas.get(i).getListaHoras();
                    break;
                }
            }
        }
        boxHoras.setItems(FXCollections.observableArrayList(horasDisponibles));


    }


    //  ----------------------------------------  tab consultar cita  ---------------------------------------

    @FXML
    private TextField txtNumeroCitaConsultar;


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
            if (txtNumeroCitaConsultar.getText() != null && !txtNumeroCitaConsultar.getText().equals("")) {


/**
 *             se manda por parametro el numero de cedula y el tipo de cita para poder consultar y va hasta la clase citas donde esta
 *             la cola de prioridad y se verifica de que la cita exista ; si existe devulve la cita con la informacion en caso contrario devulve null
 */
                String id = txtIdConsultarCita.getText();
                TipoCita tipoCita = boxTipoCitaConsultarCitas.getValue();
                TipoDocumento tipoDocumento = boxTipoDocumentoConsultarCita.getValue();
                String numeroCita = txtNumeroCitaConsultar.getText().toUpperCase();


                //Cita cita = controller.consultarCita(txtIdConsultarCita.getText(), boxTipoCitaConsultarCitas.getValue());
                Cita cita = controller.consultarCita(new Cita(new Paciente("", id), tipoCita, numeroCita, null));

                if (cita == null) {
                    JOptionPane.showMessageDialog(null, "no tiene citas pendientes");
                } else {
                    //se guardan las coordenadas del pane
                    originalX = btnVolverConsultarCita.getLayoutX();
                    originalY = btnVolverConsultarCita.getLayoutY();
                    //se agregan los objetos de interfaz que se van a quitar
                    if (paneDatosConsultarCita != null && !panePaderConsultarCita.getChildren().contains(paneDatosConsultarCita)) {
                        panePrincipal.getChildren().add(paneDatosConsultarCita);
                    }
                    if (btnConsultarCita != null && !panePaderConsultarCita.getChildren().contains(btnAceptarConsultarCita)) {
                        panePrincipal.getChildren().add(btnAceptarConsultarCita);
                    }
                    // se mueven los botones para una nueva interfas
                    btnVolverConsultarCita.setLayoutX(274);
                    btnVolverConsultarCita.setLayoutY(261);

                    panePaderConsultarCita.getChildren().remove(paneDatosConsultarCita);
                    panePaderConsultarCita.getChildren().remove(btnAceptarConsultarCita);

                    lblInformativoG.setText("""
                            Su cita ha sido registrada correctamente""");
                    labelMensajeG.setText("su numero de cita es: " + cita.getNumeroCita() + "\nfue asignada para el dia: \n" + cita.getFechaCita().format(formatter));
                    panePaderConsultarCita.getChildren().add(labelMensajeG);
                    labelMensajeG.setLayoutY(80);
                    labelMensajeG.setLayoutX(145);
                    labelMensajeG.setStyle("-fx-font-size: 35px; -fx-font-weight: bold; -fx-underline: true;");
                    cordenadasXimg = imgRegistro.getLayoutX();
                    cordenadasYimg = imgRegistro.getLayoutY();
                    imgRegistro.setLayoutX(101);


                }


            }

        }

    }

    @FXML
    private Button btnAceptarCalendario;


    @FXML
    private Tab tapCalendario;

    private void dirigirCalendario() {
        if (!tabPane.getTabs().contains(tapCalendario)) {
            tabPane.getTabs().add(tapCalendario);
        }
        TabPane tabPane = tapCalendario.getTabPane();

        //sirve para pasar al tap solicitarCita
        if (tabPane != null) {
            tabPane.getSelectionModel().select(tapCalendario);
        }
    }

    private void dirigirCalendario2() {
        if (!tabPane.getTabs().contains(tapCalendario)) {
            tabPane.getTabs().add(tapCalendario);
        }
        TabPane tabPane = tapCalendario.getTabPane();

        //sirve para pasar al tap solicitarCita
        if (tabPane != null) {
            tabPane.getSelectionModel().select(tapCalendario);
            btnAceptarCalendario.setVisible(false);
        }
    }


    @FXML
    private AnchorPane panePrincipalConsultarCita;


    public void limpiarCamposConsultar() {
        txtNumeroCitaConsultar.setText("");
        txtIdConsultarCita.setText("");
        boxTipoCitaConsultarCitas.setValue(TipoCita.MEDICO_GENERAL);
        boxTipoDocumentoConsultarCita.setValue(TipoDocumento.CEDULA_DE_CIUDADANIA);

    }

    @FXML
    private AnchorPane panePaderConsultarCita;
    @FXML
    private Pane paneDatosConsultarCita;

    @FXML
    void dirigirInicioDesdeConsultar(ActionEvent event) {
        // Restaurar las coordenadas originales de btnVolver

        limpiarCamposConsultar();


        btnVolverConsultarCita.setLayoutX(originalX);
        btnVolverConsultarCita.setLayoutY(originalY);
        TabPane tabPane = tapInicio.getTabPane();

        // Seleccionar la pestaña de inicio
        if (tabPane != null) {
            tabPane.getSelectionModel().select(tapInicio);
        }

        // Remover la pestaña de solicitar cita si existe
        if (tabPane.getTabs().contains(tapConsultar)) {
            tabPane.getTabs().remove(tapConsultar);
        }

        // Verificar si paneAux no es nulo y si no está ya presente en panePaderSolicitarCita
        if (paneDatosConsultarCita != null && !panePaderConsultarCita.getChildren().contains(paneDatosConsultarCita)) {
            // Agregar paneAux a panePaderSolicitarCita si no está presente y no es nulo
            panePaderConsultarCita.getChildren().add(paneDatosConsultarCita);
        }
        // Verificar si btnValidar no es nulo y si no está ya presente en panePaderSolicitarCita
        if (btnAceptarConsultarCita != null && !panePaderConsultarCita.getChildren().contains(btnAceptarConsultarCita)) {
            // Agregar btnValidar a panePaderSolicitarCita si no está presente y no es nulo
            panePaderConsultarCita.getChildren().add(btnAceptarConsultarCita);
        }
        //vuelven los datos prederteminados
        lblInformativoG.setText("Ingresa los siguientes datos para agendar tu turno:");
        imgRegistro.setLayoutY(cordenadasYimg);
        panePaderConsultarCita.getChildren().remove(labelMensajeG);
        limpiarCampos();


    }


    //-----------------------------------cancelar o modificar -----------------------------


    @FXML
    private ChoiceBox<TipoDocumento> boxTipoDocumentoCancelar;

    @FXML
    private Button btnAceptarCancelar;

    @FXML
    private Button btnVolverCancelar;

    @FXML
    private ImageView imgRegistro21;

    @FXML
    private Label lblInformativoG21;

    @FXML
    private Label lblInformativoG2111;


    @FXML
    private Pane paneDatosCancelar;

    @FXML
    private AnchorPane panePaderCancelar;

    @FXML
    private AnchorPane panePrincipalCancelar;


    @FXML
    private TextField txtIdCancelar;

    @FXML
    private TextField txtNumeroCitaCancelar;

    @FXML
    private DatePicker dateCalendario;

    private void limpiarCampos() {
        txtIdConsultarCita.setText((""));
    }

    Cita citaAux;

    @FXML
    void aceptarCancelar(ActionEvent event) {
        Cita cita;
        TipoCita tipoCita;
        String id = txtIdCancelar.getText();
        String numeroCita = txtNumeroCitaCancelar.getText().toUpperCase();


        if (verificarCamposCancelar()) {

            if (numeroCita.charAt(0) == 'U') {
                tipoCita = TipoCita.URGENCIA;
            } else if (numeroCita.charAt(0) == 'C') {

                tipoCita = TipoCita.CIRUGIA_PROGRAMADA;
            } else {
                tipoCita = TipoCita.MEDICO_GENERAL;
            }
            if (boxTipoProcedimientoCancelar1.getValue().compareTo(TipoProcedimiento.MODIFICAR) == 0) {
                cita = controller.obtenerCita(new Cita(new Paciente("", id), tipoCita, numeroCita, null));
                if (cita == null) {
                    JOptionPane.showMessageDialog(null, "Usted no tiene citas pendientes");
                } else {
                    this.citaAux = cita;
                    dirigirCalendario2();

                }

            } else {

                boolean bandera = controller.obtenerCita2(new Cita(new Paciente("", id), tipoCita, numeroCita, null));
                if (bandera) {
                    JOptionPane.showMessageDialog(null, "Su cita fue cancelada con exito");
                } else {
                    JOptionPane.showMessageDialog(null, "No fue posible cancelar su cita");


                }

            }
        } else {
            JOptionPane.showMessageDialog(null, "Debe de llenar todos los campos");
        }

    }

    private boolean verificarCamposCancelar() {
        boolean bandera = false;

        if (!txtIdCancelar.getText().equals("") && txtIdCancelar != null) {

            if (!txtNumeroCitaCancelar.getText().equals("") && txtNumeroCitaCancelar != null) {
                bandera = true;
            }

        }
        return bandera;
    }


    @FXML
    void dirigirInicioDesdeCancelar(ActionEvent event) {
        limpiarCamposCancelar();



        TabPane tabPane = tapInicio.getTabPane();

        // Seleccionar la pestaña de inicio
        if (tabPane != null) {
            tabPane.getSelectionModel().select(tapInicio);
        }

        // Remover la pestaña de solicitar cita si existe
        if (tabPane.getTabs().contains(tapCancelarModificar)) {
            tabPane.getTabs().remove(tapCancelarModificar);

        }
        if (tabPane.getTabs().contains(tapCalendario)) {
            tabPane.getTabs().remove(tapCalendario);

        }

        // Verificar si paneAux no es nulo y si no está ya presente en panePaderSolicitarCita
        if (paneDatosCancelar != null && !panePaderCancelar.getChildren().contains(paneDatosCancelar)) {
            // Agregar paneAux a panePaderSolicitarCita si no está presente y no es nulo
            panePaderCancelar.getChildren().add(paneDatosCancelar);
        }
        // Verificar si btnValidar no es nulo y si no está ya presente en panePaderSolicitarCita
        if (btnAceptarCancelar != null && !panePaderCancelar.getChildren().contains(btnAceptarCancelar)) {
            // Agregar btnValidar a panePaderSolicitarCita si no está presente y no es nulo
            panePaderCancelar.getChildren().add(btnAceptarCancelar);
        }
        //vuelven los datos prederteminados
        lblInformativoG.setText("Ingresa los siguientes datos para agendar tu turno:");
        imgRegistro.setLayoutY(cordenadasYimg);
        panePaderConsultarCita.getChildren().remove(labelMensajeG);


    }

    private void limpiarCamposCancelar() {

        boxTipoDocumentoCancelar.setValue(TipoDocumento.CEDULA_DE_CIUDADANIA);
        txtNumeroCitaCancelar.setText("");
        txtIdCancelar.setText("");
    }

    @FXML
    private ChoiceBox<TipoProcedimiento> boxTipoProcedimientoCancelar1;

}



