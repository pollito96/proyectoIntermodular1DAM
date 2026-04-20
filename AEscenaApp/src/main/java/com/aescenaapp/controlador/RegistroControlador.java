package com.aescenaapp.controlador;

import com.aescenaapp.servicio.UsuarioServicio;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class RegistroControlador {

    @FXML private TextField emailField;
    @FXML private TextField nombreField;
    @FXML private PasswordField passwordField;
    @FXML private Label errorLabel;

    private UsuarioServicio usuarioServicio = new UsuarioServicio();

    @FXML
    private void registrar() {

        String email = emailField.getText();
        String nombre = nombreField.getText();
        String pass = passwordField.getText();


        // VALIDACIÓN BÁSICA
        if (email.isBlank() || nombre.isBlank() || pass.isBlank()) {
            errorLabel.setText("Todos los campos son obligatorios");
            return;
        }

        boolean registrado = usuarioServicio.registrar(email, nombre, pass);

        if (registrado) {
            errorLabel.setStyle("-fx-text-fill: green;");
            errorLabel.setText("Usuario registrado correctamente");

            // opcional: limpiar campos
            emailField.clear();
            nombreField.clear();
            passwordField.clear();

        } else {
            errorLabel.setStyle("-fx-text-fill: red;");
            errorLabel.setText("Error al registrar usuario");
        }
    }
    @FXML
    private void redireccionLogin() {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/com/aescenaapp/login.fxml")
            );

            Parent root = loader.load();

            Stage stage = (Stage) emailField.getScene().getWindow();
            stage.getScene().setRoot(root);

        } catch (Exception e) {
            e.printStackTrace();
            errorLabel.setText("Error al abrir el login");
        }

    }
}


