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

    public boolean registrar(Usuario u) {
        return usuarioDAO.registrarUsuario(u);
    }
}

