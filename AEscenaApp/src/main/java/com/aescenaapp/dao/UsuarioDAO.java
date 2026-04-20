package com.aescenaapp.dao;

import com.aescenaapp.modelo.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UsuarioDAO {

    public Usuario login(String email, String pass) {

        String sql = "SELECT * FROM USUARIO WHERE email = ? AND pass = ? AND estado = true";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, email);
            ps.setString(2, pass);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Usuario u = new Usuario();
                u.setIdUsuario(rs.getInt("id_usuario"));
                u.setEmail(rs.getString("email"));
                u.setNombre(rs.getString("nombre"));
                u.setEstado(rs.getBoolean("estado"));
                return u;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public boolean registrar(String email, String nombre, String pass) {

        String sql = "INSERT INTO USUARIO (email, pass, nombre, estado) VALUES (?, ?, ?, true)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, email);
            ps.setString(2, pass);
            ps.setString(3, nombre);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
}
