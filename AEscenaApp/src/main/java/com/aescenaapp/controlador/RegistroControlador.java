package com.aescenaapp.controlador;

import com.aescenaapp.modelo.Usuario;
import com.aescenaapp.servicio.UsuarioServicio;
import com.aescenaapp.util.GestorNavegacion;
import com.aescenaapp.util.ValidacionUtil;
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

        if (!ValidacionUtil.camposObligatorios(email, nombre, pass)) {
            errorLabel.setText("Todos los campos son obligatorios");
            return;
        }

        if (!ValidacionUtil.emailValido(email)) {
            errorLabel.setText("El email no tiene un formato válido (ejemplo@dominio.com)");
            return;
        }

        if (!ValidacionUtil.nombreValido(nombre)) {
            errorLabel.setText("El nombre debe tener entre 2 y 50 caracteres y solo puede contener letras y espacios");
            return;
        }

        if (!ValidacionUtil.passwordValida(pass)) {
            errorLabel.setText("La contraseña debe tener mínimo 8 caracteres, 1 mayúscula, 1 número y 1 símbolo");
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


