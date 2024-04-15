module co.edu.uniquindio.citas {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.mail;
    requires java.desktop;


    opens co.edu.uniquindio.citas to javafx.fxml;
    exports co.edu.uniquindio.citas;
    opens co.edu.uniquindio.citas.controllersView to javafx.fxml;
    exports co.edu.uniquindio.citas.controllersView;

}