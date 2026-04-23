package com.aescenaapp.controlador;

import com.aescenaapp.modelo.Sesion;
import com.aescenaapp.modelo.Usuario;
import com.aescenaapp.servicio.SesionServicio;
import com.aescenaapp.util.GestorSesion;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.beans.property.SimpleStringProperty;

public class SesionControlador {

    @FXML private TableView<Sesion> tablaSesiones;

    @FXML private TableColumn<Sesion, String> colClase;
    @FXML private TableColumn<Sesion, String> colFecha;
    @FXML private TableColumn<Sesion, String> colInicio;
    @FXML private TableColumn<Sesion, String> colFin;
    @FXML private TableColumn<Sesion, String> colPlazas;

    @FXML private Label modoLabel;

    private final SesionServicio sesionServicio = new SesionServicio();

    private Usuario usuario;
    private String modo;

    @FXML
    public void initialize() {

        usuario = GestorSesion.getUsuario();

        if (esProfesor()) {
            modo = "PROFESOR";
            modoLabel.setText("Tus sesiones creadas");
        } else {
            modo = "CLIENTE";
            modoLabel.setText("Tus sesiones reservadas");
        }

        configurarColumnas();
        cargarSesiones();
    }

    private boolean esProfesor() {
        return usuario.getRoles().stream()
                .anyMatch(r -> r.getTipo().equals("PROFESOR"));
    }

    private void configurarColumnas() {

        colClase.setCellValueFactory(data ->
                new SimpleStringProperty(String.valueOf(data.getValue().getIdClase()))
        );

        colFecha.setCellValueFactory(data ->
                new SimpleStringProperty(data.getValue().getFecha().toString())
        );

        colInicio.setCellValueFactory(data ->
                new SimpleStringProperty(data.getValue().getHoraInicio().toString())
        );

        colFin.setCellValueFactory(data ->
                new SimpleStringProperty(data.getValue().getHoraFin().toString())
        );

        colPlazas.setCellValueFactory(data ->
                new SimpleStringProperty(String.valueOf(data.getValue().getPlazasTotales()))
        );
    }

    private void cargarSesiones() {

        if (modo.equals("PROFESOR")) {

            tablaSesiones.setItems(
                    FXCollections.observableArrayList(
                            sesionServicio.obtenerSesionesPorProfesor(usuario.getIdUsuario())

                    )
            );

        } else {

            tablaSesiones.setItems(
                    FXCollections.observableArrayList(
                            sesionServicio.obtenerSesionesReservadas(usuario.getIdUsuario())
                    )
            );
        }
    }
}