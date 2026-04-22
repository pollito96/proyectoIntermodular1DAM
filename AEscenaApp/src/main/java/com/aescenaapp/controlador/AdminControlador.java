package com.aescenaapp.controlador;

import com.aescenaapp.modelo.Usuario;
import com.aescenaapp.servicio.UsuarioServicio;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;
import java.util.List;

public class AdminControlador {

    @FXML private TableView<Usuario> tablaUsuarios;

    @FXML private TableColumn<Usuario, String> colNombre;
    @FXML private TableColumn<Usuario, String> colEmail;
    @FXML private TableColumn<Usuario, Void> colAcciones;

    @FXML private Label modoLabel;

    @FXML private ToggleButton btnClientes;
    @FXML private ToggleButton btnProfesores;

    private final UsuarioServicio usuarioServicio = new UsuarioServicio();

    private String modoActual = "CLIENTES";

    private List<Usuario> listaActual = new ArrayList<>();

    @FXML
    public void initialize() {

        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));

        configurarAcciones();

        verClientes();
    }

    @FXML
    private void verClientes() {

        modoActual = "CLIENTES";

        actualizarUI();

        listaActual = usuarioServicio.obtenerClientes();

        tablaUsuarios.setItems(
                FXCollections.observableArrayList(listaActual)
        );

    }

    @FXML
    private void verProfesores() {

        modoActual = "PROFESORES";

        actualizarUI();

        listaActual = usuarioServicio.obtenerProfesores();

        tablaUsuarios.setItems(
                FXCollections.observableArrayList(listaActual)
        );
    }

    private void actualizarUI() {

        modoLabel.setText("Vista actual: " + modoActual);

        btnClientes.setSelected(modoActual.equals("CLIENTES"));
        btnProfesores.setSelected(modoActual.equals("PROFESORES"));
    }

    private void configurarAcciones() {

        colAcciones.setCellFactory(col -> new TableCell<>() {

            private final Button btn = new Button();

            {
                btn.setOnAction(e -> {

                    Usuario u = getTableView().getItems().get(getIndex());

                    if (modoActual.equals("CLIENTES")) {

                        usuarioServicio.asignarProfesor(u.getIdUsuario());
                    }

                    else if (modoActual.equals("PROFESORES")) {

                        usuarioServicio.quitarProfesor(u.getIdUsuario());
                    }

                    refrescar();
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {

                super.updateItem(item, empty);

                if (empty) {
                    setGraphic(null);
                    return;
                }

                Usuario u = (Usuario) getTableRow().getItem();

                if (u == null) {
                    setGraphic(null);
                    return;
                }

                if (modoActual.equals("CLIENTES")) {

                    btn.setText("Hacer profesor");
                    btn.setStyle("-fx-background-color: #2ecc71; -fx-text-fill: white;");
                    setGraphic(btn);
                } else {

                    btn.setText("Quitar profesor");
                    btn.setStyle("-fx-background-color: #e74c3c; -fx-text-fill: white;");
                    setGraphic(btn);
                }
            }
        });
    }

    private void refrescar() {

        if (modoActual.equals("CLIENTES")) {
            verClientes();
        } else {
            verProfesores();
        }
    }
}