package com.aescenaapp.controlador;

import com.aescenaapp.modelo.Rol;
import com.aescenaapp.modelo.Usuario;
import com.aescenaapp.servicio.UsuarioServicio;
import com.aescenaapp.util.GestorNavegacion;
import com.aescenaapp.util.GestorSesion;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.util.List;

public class LoginControlador {
    @FXML private TextField emailField;
    @FXML private PasswordField passwordField;
    @FXML private Label errorLabel;

    private UsuarioServicio usuarioServicio = new UsuarioServicio();

    @FXML
    private void login() {

        Usuario u = usuarioServicio.login(
                emailField.getText(),
                passwordField.getText()
        );

        if (u == null) {
            errorLabel.setText("Credenciales incorrectas");
            return;
        }

        GestorSesion.setUsuario(u);

        GestorNavegacion.cambiarVista(
                "/com/aescenaapp/index.fxml",
                emailField
        );
    }
    @FXML
    private void redireccionRegistro() {
        boolean ok = GestorNavegacion.cambiarVista("/com/aescenaapp/registro.fxml", emailField);

        if (!ok) {
            errorLabel.setText("No se pudo abrir el registro");
        }
    }
}
