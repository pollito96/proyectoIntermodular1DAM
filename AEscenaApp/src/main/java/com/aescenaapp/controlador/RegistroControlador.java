package com.aescenaapp.controlador;

import com.aescenaapp.modelo.Usuario;
import com.aescenaapp.servicio.UsuarioServicio;
import com.aescenaapp.util.GestorNavegacion;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class RegistroControlador {

    @FXML
    private TextField emailField;
    @FXML
    private TextField nombreField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Label errorLabel;

    private UsuarioServicio usuarioServicio = new UsuarioServicio();

    @FXML
    private void registrar() {

        String email = emailField.getText();
        String nombre = nombreField.getText();
        String pass = passwordField.getText();

        if (email.isBlank() || nombre.isBlank() || pass.isBlank()) {
            errorLabel.setText("Todos los campos son obligatorios");
            return;
        }

        if (!email.contains("@")) {
            errorLabel.setText("Email no válido");
            return;
        }

        if (pass.length() < 6) {
            errorLabel.setText("La contraseña es demasiado corta");
            return;
        }

        Usuario u = new Usuario();
        u.setEmail(email);
        u.setNombre(nombre);
        u.setPass(pass);

        boolean registrado = usuarioServicio.registrar(u);

        if (registrado) {
            errorLabel.setStyle("-fx-text-fill: green;");
            errorLabel.setText("Usuario registrado correctamente");

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
        boolean ok = GestorNavegacion.cambiarVista("/com/aescenaapp/login.fxml", emailField);

        if (!ok) {
            errorLabel.setText("No se pudo abrir el login");
        }

    }
}


