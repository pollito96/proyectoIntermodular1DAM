package com.aescenaapp.controlador;

import com.aescenaapp.modelo.Usuario;
import com.aescenaapp.servicio.UsuarioServicio;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginControlador {
    @FXML private TextField emailField;
    @FXML private PasswordField passwordField;
    @FXML private Label errorLabel;

    private UsuarioServicio usuarioServicio = new UsuarioServicio();

    @FXML
    private void login() {

        String email = emailField.getText();
        String pass = passwordField.getText();

        Usuario usuario = usuarioServicio.login(email, pass);

        if (usuario == null) {
            errorLabel.setText("Credenciales incorrectas");
        } else {
            errorLabel.setText("Login correcto: " + usuario.getNombre());

            // aquí después navegaremos a dashboard
        }
    }
    @FXML
    private void redireccionRegistro() {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/com/aescenaapp/registro.fxml")
            );

            Parent root = loader.load();

            Stage stage = (Stage) emailField.getScene().getWindow();
            stage.getScene().setRoot(root);

        } catch (Exception e) {
            e.printStackTrace();
            errorLabel.setText("Error al abrir el formulario de registro");
        }

    }
}
