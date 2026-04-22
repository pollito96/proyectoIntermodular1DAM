package com.aescenaapp.controlador;

import com.aescenaapp.modelo.Clase;
import com.aescenaapp.modelo.Sesion;
import com.aescenaapp.modelo.Usuario;
import com.aescenaapp.servicio.ClaseServicio;
import com.aescenaapp.servicio.SesionServicio;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.LocalTime;

public class ClasesProfeControlador {

    @FXML private TableView<Clase> tablaClases;
    @FXML private TableColumn<Clase, String> colNombre;

    @FXML private Label lblClaseSeleccionada;

    @FXML private DatePicker datePicker;
    @FXML private ComboBox<String> horaInicioCombo;
    @FXML private ComboBox<String> horaFinCombo;
    @FXML private TextField plazasField;

    private final ClaseServicio claseServicio = new ClaseServicio();
    private final SesionServicio sesionServicio = new SesionServicio();

    private Clase claseSeleccionada;

    @FXML
    public void initialize() {

        colNombre.setCellValueFactory(data ->
                new javafx.beans.property.SimpleStringProperty(data.getValue().getNombre())
        );

        cargarClases();
        inicializarHoras();
        configurarSeleccion();
    }

    private void cargarClases() {

        tablaClases.setItems(
                FXCollections.observableArrayList(
                        claseServicio.listarClases()
                )
        );
    }

    private void inicializarHoras() {

        for (int h = 9; h <= 20; h++) {
            for (int m = 0; m < 60; m += 30) {

                String hora = String.format("%02d:%02d", h, m);

                horaInicioCombo.getItems().add(hora);
                horaFinCombo.getItems().add(hora);
            }
        }
    }

    private void configurarSeleccion() {

        tablaClases.getSelectionModel().selectedItemProperty().addListener((obs, oldSel, newSel) -> {

            claseSeleccionada = newSel;

            lblClaseSeleccionada.setText(newSel != null ? newSel.getNombre() : "Ninguna");
        });
    }

    @FXML
    private void crearSesion() {

        if (claseSeleccionada == null) {
            alerta("Selecciona una clase");
            return;
        }

        if (datePicker.getValue() == null ||
                horaInicioCombo.getValue() == null ||
                horaFinCombo.getValue() == null ||
                plazasField.getText().isBlank()) {
            alerta("Completa todos los campos");
            return;
        }

        LocalTime horaInicio;
        LocalTime horaFin;
        int plazas;

        try {
            horaInicio = LocalTime.parse(horaInicioCombo.getValue());
            horaFin = LocalTime.parse(horaFinCombo.getValue());
            plazas = Integer.parseInt(plazasField.getText());
            if (horaFin.isBefore(horaInicio)) {
                alerta("La hora de fin debe ser posterior a la de inicio");
                return;
            }
        } catch (Exception e) {
            alerta("Datos inválidos");
            return;
        }

        Usuario profesor = com.aescenaapp.util.GestorSesion.getUsuario();

        Sesion s = new Sesion();

        s.setFecha(datePicker.getValue());
        s.setHoraInicio(horaInicio);
        s.setHoraFin(horaFin);
        s.setPlazasTotales(plazas);
        s.setIdClase(claseSeleccionada.getIdClase());
        s.setIdUsuario(profesor.getIdUsuario());

        boolean ok = sesionServicio.crearSesion(s);

        if (ok) {
            info("Sesión creada");
            limpiar();
        } else {
            alerta("Error al crear sesión");
        }
    }

    private void limpiar() {

        datePicker.setValue(null);
        horaInicioCombo.setValue(null);
        horaFinCombo.setValue(null);
        plazasField.clear();
    }

    private void alerta(String msg) {

        Alert a = new Alert(Alert.AlertType.WARNING);
        a.setContentText(msg);
        a.showAndWait();
    }

    private void info(String msg) {

        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setContentText(msg);
        a.showAndWait();
    }
}