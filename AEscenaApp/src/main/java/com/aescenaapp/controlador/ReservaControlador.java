package com.aescenaapp.controlador;

import com.aescenaapp.DTO.SesionDTO;
import com.aescenaapp.modelo.Sesion;
import com.aescenaapp.modelo.Usuario;
import com.aescenaapp.servicio.ReservaServicio;
import com.aescenaapp.servicio.SesionServicio;
import com.aescenaapp.util.GestorSesion;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class ReservaControlador {

    @FXML private TableView<SesionDTO> tablaSesiones;

    @FXML private TableColumn<SesionDTO, String> colClase;
    @FXML private TableColumn<SesionDTO, String> colFecha;
    @FXML private TableColumn<SesionDTO, String> colInicio;
    @FXML private TableColumn<SesionDTO, String> colFin;
    @FXML private TableColumn<SesionDTO, Integer> colPlazas;
    @FXML private TableColumn<SesionDTO, String> colProfesor;
    @FXML private TableColumn<SesionDTO, Void> colAccion;

    private final SesionServicio sesionServicio = new SesionServicio();
    private final ReservaServicio reservaServicio = new ReservaServicio();


    int idUsuarioLogeado = GestorSesion.getUsuario().getIdUsuario();

    @FXML
    public void initialize() {

        colClase.setCellValueFactory(new PropertyValueFactory<>("nombreClase"));
        colFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        colInicio.setCellValueFactory(new PropertyValueFactory<>("horaInicio"));
        colFin.setCellValueFactory(new PropertyValueFactory<>("horaFin"));
        colPlazas.setCellValueFactory(new PropertyValueFactory<>("plazasTotales"));
        colProfesor.setCellValueFactory(new PropertyValueFactory<>("nombreProfesor"));

        configurarBotonReserva();

        cargarSesiones();
    }

    private void cargarSesiones() {
        tablaSesiones.setItems(
                FXCollections.observableArrayList(
                        sesionServicio.obtenerSesionesDisponibles(idUsuarioLogeado)
                )
        );
    }

    private void configurarBotonReserva() {

        colAccion.setCellFactory(col -> new TableCell<>() {

            private final Button btn = new Button("Reservar");

            {
                btn.setOnAction(e -> {

                    if (GestorSesion.getUsuario() == null) {
                        System.out.println("No hay usuario logueado");
                        return;
                    }

                    SesionDTO s = getTableView().getItems().get(getIndex());

                    boolean ok = reservaServicio.crearReserva(idUsuarioLogeado, s.getIdSesion());

                    if (ok) {
                        cargarSesiones();
                    }
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {

                super.updateItem(item, empty);

                if (empty) {
                    setGraphic(null);
                    return;
                }

                setGraphic(btn);
            }
        });
    }
}