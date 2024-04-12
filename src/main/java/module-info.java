module co.edu.uniquindio.citas {
    requires javafx.controls;
    requires javafx.fxml;


    opens co.edu.uniquindio.citas to javafx.fxml;
    exports co.edu.uniquindio.citas;
}