package com.aescenaapp.dao;

import com.aescenaapp.modelo.Rol;
import com.aescenaapp.modelo.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {

    public Usuario login(String email, String pass) {

        String sql = "SELECT * FROM USUARIO WHERE email = ? AND pass = ? AND estado = true";

        String sqlRoles = """
            SELECT r.id_rol, r.tipo
            FROM ROL r
            JOIN USUARIO_ROL ur ON r.id_rol = ur.id_rol
            WHERE ur.id_usuario = ?
        """;

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, email);
            ps.setString(2, pass);

            ResultSet rs = ps.executeQuery();

            if (!rs.next()) return null;

            Usuario u = new Usuario();
            int idUsuario = rs.getInt("id_usuario");

            u.setIdUsuario(idUsuario);
            u.setEmail(rs.getString("email"));
            u.setNombre(rs.getString("nombre"));
            u.setEstado(rs.getBoolean("estado"));

            // 2. Roles
            PreparedStatement psRoles = conn.prepareStatement(sqlRoles);
            psRoles.setInt(1, idUsuario);

            ResultSet rsRoles = psRoles.executeQuery();

            List<Rol> roles = new ArrayList<>();

            while (rsRoles.next()) {
                Rol r = new Rol();
                r.setIdRol(rsRoles.getInt("id_rol"));
                r.setTipo(rsRoles.getString("tipo"));
                roles.add(r);
            }

            u.setRoles(roles);

            return u;


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
