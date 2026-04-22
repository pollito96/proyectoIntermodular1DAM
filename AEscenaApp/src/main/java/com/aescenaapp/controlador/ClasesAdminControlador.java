package com.aescenaapp.controlador;

import com.aescenaapp.modelo.Clase;
import com.aescenaapp.servicio.ClaseServicio;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class ClasesAdminControlador {
    @FXML
    private TableView<Clase> tablaClases;

    @FXML private TableColumn<Clase, String> colNombre;
    @FXML private TableColumn<Clase, String> colDescripcion;

    private final ClaseServicio claseServicio = new ClaseServicio();

    private Clase claseSeleccionada;

    @FXML
    public void initialize() {

        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colDescripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));

        cargarClases();

        tablaClases.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldSel, newSel) -> claseSeleccionada = newSel
        );
    }

    private void cargarClases() {
        tablaClases.getItems().setAll(
                claseServicio.listarClases()
        );
    }

    @FXML
    private void onCrearClase() {

        TextInputDialog nombreDialog = new TextInputDialog();
        nombreDialog.setHeaderText("Nombre de la clase");

        String nombre = nombreDialog.showAndWait().orElse(null);
        if (nombre == null || nombre.isBlank()) return;

        TextInputDialog descDialog = new TextInputDialog();
        descDialog.setHeaderText("Descripción");

        String descripcion = descDialog.showAndWait().orElse("");

        Clase c = new Clase();
        c.setNombre(nombre);
        c.setDescripcion(descripcion);

        claseServicio.crearClase(c);

        cargarClases();
    }

    @FXML
    private void onEditarClase() {

        if (claseSeleccionada == null) return;

        TextInputDialog nombreDialog = new TextInputDialog(claseSeleccionada.getNombre());
        nombreDialog.setHeaderText("Editar nombre");

        String nombre = nombreDialog.showAndWait().orElse(null);
        if (nombre == null || nombre.isBlank()) return;

        TextInputDialog descDialog = new TextInputDialog(claseSeleccionada.getDescripcion());
        descDialog.setHeaderText("Editar descripción");

        String descripcion = descDialog.showAndWait().orElse("");

        claseSeleccionada.setNombre(nombre);
        claseSeleccionada.setDescripcion(descripcion);

        claseServicio.actualizarClase(claseSeleccionada);

        cargarClases();
    }

    @FXML
    private void onEliminarClase() {

        if (claseSeleccionada == null) return;

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmar eliminación");
        alert.setHeaderText("Eliminar clase");
        alert.setContentText("¿Seguro que quieres eliminar la clase: "
                + claseSeleccionada.getNombre() + "?");

        ButtonType botonSi = new ButtonType("Sí");
        ButtonType botonNo = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

        alert.getButtonTypes().setAll(botonSi, botonNo);

        alert.showAndWait().ifPresent(response -> {

            if (response == botonSi) {

                claseServicio.eliminarClase(claseSeleccionada.getIdClase());

                claseSeleccionada = null;

                cargarClases();
            }
        });
    }
}

