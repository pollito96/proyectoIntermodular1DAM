package com.aescenaapp.controlador;


import com.aescenaapp.modelo.Usuario;
import com.aescenaapp.util.GestorNavegacion;
import com.aescenaapp.util.GestorSesion;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

public class IndexControlador {

    @FXML private Label usuarioLabel;

    @FXML private Button btnReservas;
    @FXML private Button btnClases;
    @FXML private Button btnSesiones;
    @FXML private Button btnAdmin;

    @FXML private StackPane contentPane;

    @FXML
    public void initialize() {

        Usuario u = GestorSesion.getUsuario();

        usuarioLabel.setText("Usuario: " + u.getNombre());

        // 🔐 control por roles
        boolean isAdmin = GestorSesion.hasRole("ADMIN");
        boolean isProfesor = GestorSesion.hasRole("PROFESOR");
        boolean isCliente = GestorSesion.hasRole("CLIENTE");

        // CLIENTE
        btnReservas.setVisible(isCliente || isAdmin);
        btnReservas.setManaged(isCliente || isAdmin);

        // PROFESOR
        btnClases.setVisible(isProfesor || isAdmin);
        btnClases.setManaged(isProfesor || isAdmin);

        btnSesiones.setVisible(isProfesor || isAdmin);
        btnSesiones.setManaged(isProfesor || isAdmin);

        // ADMIN
        btnAdmin.setVisible(isAdmin);
        btnAdmin.setManaged(isAdmin);
    }

    @FXML
    private void logout() {

        GestorSesion.setUsuario(null);

        GestorNavegacion.cambiarVista(
                "/com/aescenaapp/login.fxml",
                usuarioLabel
        );
    }
}