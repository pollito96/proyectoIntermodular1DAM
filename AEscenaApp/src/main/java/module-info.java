module com.example.login_mpo_victor_irribarria_sanchez {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.aescenaapp to javafx.fxml;
    exports com.aescenaapp;

}