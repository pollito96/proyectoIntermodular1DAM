module com.example.login_mpo_victor_irribarria_sanchez {
    requires javafx.controls;
    requires javafx.fxml;
    requires static lombok;
    requires java.sql;

    opens com.aescenaapp to javafx.fxml;
    exports com.aescenaapp;

    exports com.aescenaapp.controlador;
    opens com.aescenaapp.controlador to javafx.fxml;
    opens com.aescenaapp.modelo to javafx.base;
    opens com.aescenaapp.DTO to javafx.base;

}