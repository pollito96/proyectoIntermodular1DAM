package com.aescenaapp.servicio;

import com.aescenaapp.dao.UsuarioDAO;
import com.aescenaapp.modelo.Usuario;

import java.util.List;

public class UsuarioServicio {
    private UsuarioDAO usuarioDAO = new UsuarioDAO();

    public Usuario login(String email, String pass) {

        if (email == null || email.isBlank() || pass == null || pass.isBlank()) {
            return null;
        }

        return usuarioDAO.login(email, pass);
    }

    public String registrarUsuario(Usuario usuario) {

        if (usuarioDAO.emailExiste(usuario.getEmail())) {
            return "EMAIL_EXISTE";
        }

        boolean insert = usuarioDAO.registrarUsuario(usuario);

        return insert ? "OK" : "ERROR";
    }

    public void asignarProfesor(int idUsuario) {
        usuarioDAO.insertarRol(idUsuario, "PROFESOR");
    }

    public void quitarProfesor(int idUsuario) {
        usuarioDAO.eliminarRol(idUsuario, "PROFESOR");
    }
    public List<Usuario> obtenerClientes() {
        return usuarioDAO.obtenerClientes();
    }

    public List<Usuario> obtenerProfesores() {
        return usuarioDAO.obtenerProfesores();
    }
}

