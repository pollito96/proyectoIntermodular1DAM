package com.aescenaapp.controlador;

import com.aescenaapp.DTO.SesionDTO;
import com.aescenaapp.modelo.Sesion;
import com.aescenaapp.modelo.Usuario;
import com.aescenaapp.servicio.SesionServicio;
import com.aescenaapp.util.GestorSesion;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.beans.property.SimpleStringProperty;

import java.util.List;

public class SesionControlador {

    @FXML private TableView<SesionDTO> tablaSesiones;

    @FXML private TableColumn<SesionDTO, String> colClase;
    @FXML private TableColumn<SesionDTO, String> colFecha;
    @FXML private TableColumn<SesionDTO, String> colInicio;
    @FXML private TableColumn<SesionDTO, String> colFin;
    @FXML private TableColumn<SesionDTO, String> colPlazas;
    @FXML private TableColumn<SesionDTO, String> colTipo;
    @FXML private Button btnTodas;
    @FXML private Button btnCreadas;
    @FXML private Button btnReservadas;

    @FXML private Label titleLabel;

    private final SesionServicio sesionServicio = new SesionServicio();

    private Usuario usuario;

    private List<SesionDTO> sesionesBase;

    @FXML
    private void filtrarTodas() {

        tablaSesiones.setItems(
                FXCollections.observableArrayList(sesionesBase)
        );
    }

    @FXML
    private void filtrarCreadas() {

        List<SesionDTO> filtradas = sesionesBase.stream()
                .filter(s -> "CREADA".equals(s.getTipo()))
                .toList();

        tablaSesiones.setItems(
                FXCollections.observableArrayList(filtradas)
        );
    }

    @FXML
    public void initialize() {

        usuario = GestorSesion.getUsuario();

        boolean esProfesor = usuario.getRoles().stream()
                .anyMatch(r -> r.getTipo().equals("PROFESOR"));

        btnTodas.setVisible(esProfesor);
        btnTodas.setManaged(esProfesor);

        btnCreadas.setVisible(esProfesor);
        btnCreadas.setManaged(esProfesor);

        btnReservadas.setVisible(esProfesor);
        btnReservadas.setManaged(esProfesor);

        configurarColumnas();
        cargarSesiones();

    }

    @FXML
    private void filtrarReservadas() {

        List<SesionDTO> filtradas = sesionesBase.stream()
                .filter(s -> "RESERVADA".equals(s.getTipo()))
                .toList();

        tablaSesiones.setItems(
                FXCollections.observableArrayList(filtradas)
        );
    }

    private void configurarColumnas() {

        colClase.setCellValueFactory(data ->
                new SimpleStringProperty(data.getValue().getNombreClase())
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

        colTipo.setCellValueFactory(data ->
                new SimpleStringProperty(data.getValue().getTipo())
        );
    }

    private void cargarSesiones() {

        sesionesBase = sesionServicio
                .obtenerSesionesPorProfesor(usuario.getIdUsuario());

        tablaSesiones.setItems(
                FXCollections.observableArrayList(sesionesBase)
        );
    }
}