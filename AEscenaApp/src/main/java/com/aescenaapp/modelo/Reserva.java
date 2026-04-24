package com.aescenaapp.modelo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Reserva {

    private int idReserva;
    private LocalDateTime fechaReserva;

    private int idUsuario; //CLIENTE
    private int idSesion;
}
