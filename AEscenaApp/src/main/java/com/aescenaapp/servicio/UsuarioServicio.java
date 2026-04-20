package com.aescenaapp.servicio;

import com.aescenaapp.dao.UsuarioDAO;
import com.aescenaapp.modelo.Usuario;

public class UsuarioServicio {
    private UsuarioDAO usuarioDAO = new UsuarioDAO();

    public Usuario login(String email, String pass) {

        if (email == null || email.isBlank() || pass == null || pass.isBlank()) {
            return null;
        }

        return usuarioDAO.login(email, pass);
    }

    public boolean registrar(String email, String nombre, String pass) {

        if (email == null || email.isBlank() ||
                nombre == null || nombre.isBlank() ||
                pass == null || pass.isBlank()) {
            return false;
        }

        return usuarioDAO.registrar(email, nombre, pass);
    }
}

