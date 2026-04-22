package com.aescenaapp.controlador;

import com.aescenaapp.modelo.Usuario;
import com.aescenaapp.servicio.UsuarioServicio;
import com.aescenaapp.util.GestorNavegacion;
import com.aescenaapp.util.ValidacionUtil;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class RegistroControlador {

    @FXML
    private TextField emailField;
    @FXML
    private TextField nombreField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Label errorLabel;
    @FXML
    private Label emailErrorLabel;
    @FXML
    private Label nombreErrorLabel;
    @FXML
    private Label passwordErrorLabel;
    @FXML
    private Button registrarButton;

    private UsuarioServicio usuarioServicio = new UsuarioServicio();

    private boolean emailOk = false;
    private boolean nombreOk = false;
    private boolean passwordOk = false;

    @FXML
    public void initialize() {



        emailField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!ValidacionUtil.emailValido(newValue)) {
                emailErrorLabel.setText("El email no tiene un formato válido (ejemplo@dominio.com)");
                emailOk = false;
            }else  {
                emailErrorLabel.setText("");
                emailOk = true;
            }
            actualizarBoton();
        });
        nombreField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!ValidacionUtil.nombreValido(newValue)) {
                nombreErrorLabel.setText("El nombre debe tener entre 2 y 50 caracteres y solo puede contener letras y espacios");
                nombreOk = false;
            }else  {
                nombreErrorLabel.setText("");
                nombreOk = true;
            }
            actualizarBoton();
        });
        passwordField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!ValidacionUtil.passwordValida(newValue)) {
                passwordErrorLabel.setText("La contraseña debe tener mínimo 8 caracteres, 1 mayúscula, 1 número y 1 símbolo");
                passwordOk = false;
            }else {
                passwordErrorLabel.setText("");
                passwordOk = true;
            }
            actualizarBoton();
        });
    }

    private void actualizarBoton() {
        registrarButton.setDisable(!(emailOk && nombreOk && passwordOk));
    }

    @FXML
    private void registrar() {

        String email = emailField.getText();
        String nombre = nombreField.getText();
        String pass = passwordField.getText();

        if (!ValidacionUtil.camposObligatorios(email, nombre, pass)) {
            errorLabel.setText("Todos los campos son obligatorios");
            return;
        }

        if (!ValidacionUtil.emailValido(email)||!ValidacionUtil.nombreValido(nombre) || !ValidacionUtil.passwordValida(pass)) {
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


