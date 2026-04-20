package com.aescenaapp.modelo;

import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

    private int idUsuario;
    private String email;
    private String pass;
    private String nombre;
    private boolean estado;
    private LocalDateTime fechaCreacion;

    private List<Rol> roles = new ArrayList<>();
}
