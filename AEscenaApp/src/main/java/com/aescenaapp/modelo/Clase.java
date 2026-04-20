package com.aescenaapp.modelo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Clase {
    private int idClase;
    private String nombre;
    private String descripcion;
    private String nivel;

    private List<Sesion> sesiones = new ArrayList<>();
}
