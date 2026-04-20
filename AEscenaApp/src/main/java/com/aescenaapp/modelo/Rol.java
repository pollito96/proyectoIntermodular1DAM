package com.aescenaapp.modelo;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Rol {

    private int idRol;
    private String tipo; // CLIENTE, PROFESOR, ADMIN

}
