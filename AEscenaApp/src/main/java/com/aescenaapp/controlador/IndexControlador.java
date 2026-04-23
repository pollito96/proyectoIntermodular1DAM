package com.aescenaapp.controlador;


import com.aescenaapp.modelo.Usuario;
import com.aescenaapp.util.GestorNavegacion;
import com.aescenaapp.util.GestorSesion;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

public class IndexControlador {

    @FXML private Label usuarioLabel;

    @FXML private Button btnReservas;
    @FXML private Button btnClasesProfe;
    @FXML private Button btnClasesAdmin;
    @FXML private Button btnSesiones;
    @FXML private Button btnAdmin;

    @FXML private StackPane contentPane;

    private Usuario usuario;

    @FXML
    public void initialize() {

        usuario = GestorSesion.getUsuario();

        if (usuario == null) {
            return;
        }

        usuarioLabel.setText("Usuario: " + usuario.getNombre());

        boolean isAdmin = GestorSesion.hasRole("ADMIN");
        boolean isProfesor = GestorSesion.hasRole("PROFESOR");
        boolean isCliente = GestorSesion.hasRole("CLIENTE");

        btnReservas.setVisible(isCliente);
        btnReservas.setManaged(isCliente);


        btnSesiones.setVisible(isCliente);
        btnSesiones.setManaged(isCliente);

        btnClasesProfe.setVisible(isProfesor);
        btnClasesProfe.setManaged(isProfesor);

        btnClasesAdmin.setVisible(isAdmin);
        btnClasesAdmin.setManaged(isAdmin);

        btnAdmin.setVisible(isAdmin);
        btnAdmin.setManaged(isAdmin);

        if (isAdmin) {
            cargarVista("/com/aescenaapp/adminPanel.fxml");
        } else if (isProfesor) {
            cargarVista("/com/aescenaapp/clasesProfePanel.fxml");
        } else {
            cargarVista("/com/aescenaapp/reservaPanel.fxml");
        }
    }

    private void cargarVista(String fxml) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource(fxml)
            );

            Parent vista = loader.load();

            contentPane.getChildren().setAll(vista);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void irReservas() {
        cargarVista("/com/aescenaapp/reservaPanel.fxml");
    }
    @FXML
    private void irClasesProfe() {
        cargarVista("/com/aescenaapp/clasesProfePanel.fxml");
    }

    @FXML
    private void irClasesAdmin() {
        cargarVista("/com/aescenaapp/clasesAdminPanel.fxml");
    }

    @FXML
    private void irSesiones() {
        cargarVista("/com/aescenaapp/sesionPanel.fxml");
    }

    @FXML
    private void irAdmin() {
        cargarVista("/com/aescenaapp/adminPanel.fxml");
    }

    @FXML
    private void logout() {

        GestorSesion.cerrarSesion();

        GestorNavegacion.cambiarVista(
                "/com/aescenaapp/login.fxml",
                usuarioLabel
        );
    }
}