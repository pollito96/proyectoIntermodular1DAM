package com.aescenaapp.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SesionDTO {

        private int idSesion;
        private LocalDate fecha;
        private LocalTime horaInicio;
        private LocalTime horaFin;
        private int plazasTotales;

        private String nombreClase;
        private String nombreProfesor;

}
