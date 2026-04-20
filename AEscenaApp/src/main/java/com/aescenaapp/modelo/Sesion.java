package com.aescenaapp.modelo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Sesion {
    private int idSesion;
    private LocalDate fecha;
    private LocalTime horaInicio;
    private LocalTime horaFin;
    private int plazasTotales;

    private int idClase;
    private int idUsuario; //PROFESOR

    private List<Reserva> reservas = new ArrayList<>();
}

