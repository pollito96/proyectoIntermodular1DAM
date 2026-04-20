package com.aescenaapp.dao;

import com.aescenaapp.modelo.Rol;
import com.aescenaapp.modelo.Usuario;

import java.sql.*;
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

    public boolean registrarUsuario(Usuario u) {

        String sql = """
            INSERT INTO USUARIO (email, pass, nombre)
            VALUES (?, ?, ?)
        """;

        try (Connection conn = ConnectionFactory.getConnection()) {

            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, u.getEmail());
            ps.setString(2, u.getPass());
            ps.setString(3, u.getNombre());

            int affected = ps.executeUpdate();

            if (affected == 0) return false;

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                int id = rs.getInt(1);

                asignarRol(id, 1, conn); //CLIENTE por defecto
            }

            return true;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    private void asignarRol(int idUsuario, int idRol, Connection conn) throws SQLException {

        String sql = """
        INSERT INTO USUARIO_ROL (id_usuario, id_rol)
        VALUES (?, ?)
    """;

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, idUsuario);
        ps.setInt(2, idRol);

        ps.executeUpdate();
    }
}
