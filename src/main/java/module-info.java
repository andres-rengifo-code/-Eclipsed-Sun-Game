module com.example.proyecto__sol__eclipsado {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    //opens com.example.proyecto__sol__eclipsado to javafx.fxml;
    //exports com.example.proyecto__sol__eclipsado;
    exports application;
    opens application to javafx.fxml;
    exports controllers;
    opens controllers to javafx.fxml;
}