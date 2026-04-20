package com.aescenaapp.util;

import com.aescenaapp.modelo.Usuario;

public class GestorSesion {

    private static Usuario usuario;

    public static void setUsuario(Usuario u) {
        usuario = u;
    }

    public static Usuario getUsuario() {
        return usuario;
    }

    public static boolean hasRole(String rol) {
        return usuario.getRoles()
                .stream()
                .anyMatch(r -> r.getTipo().equals(rol));
    }
}
