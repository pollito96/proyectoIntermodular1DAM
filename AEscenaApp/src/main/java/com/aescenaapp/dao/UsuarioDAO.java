package com.aescenaapp.dao;

import com.aescenaapp.modelo.Rol;
import com.aescenaapp.modelo.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {

    public Usuario login(String email, String pass) {

        String sql = """
            SELECT id_usuario, email, pass, nombre, estado
            FROM USUARIO
            WHERE email = ? AND pass = ? AND estado = true
        """;

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

            try (PreparedStatement psRoles = conn.prepareStatement(sqlRoles)) {

                psRoles.setInt(1, idUsuario);

                try (ResultSet rsRoles = psRoles.executeQuery()) {

                    List<Rol> roles = new ArrayList<>();

                    while (rsRoles.next()) {
                        Rol r = new Rol();
                        r.setIdRol(rsRoles.getInt("id_rol"));
                        r.setTipo(rsRoles.getString("tipo"));
                        roles.add(r);
                    }

                    u.setRoles(roles);
                }
            }

            return u;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public boolean registrarUsuario(Usuario u) {

        String sql = """
            INSERT INTO USUARIO (email, pass, nombre, estado)
            VALUES (?, ?, ?, true)
        """;

        final int ROL_CLIENTE = 1;

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, u.getEmail());
            ps.setString(2, u.getPass());
            ps.setString(3, u.getNombre());

            int affected = ps.executeUpdate();

            if (affected == 0) return false;

            try (ResultSet rs = ps.getGeneratedKeys()) {

                if (rs.next()) {
                    int id = rs.getInt(1);
                    asignarRol(id, ROL_CLIENTE, conn);
                }
            }

            return true;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public void insertarRol(int idUsuario, String tipoRol) {

        String sql = """
            INSERT INTO USUARIO_ROL (id_usuario, id_rol)
            VALUES (?, (SELECT id_rol FROM ROL WHERE tipo = ?))
        """;

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idUsuario);
            ps.setString(2, tipoRol);

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void eliminarRol(int idUsuario, String tipoRol) {

        String sql = """
            DELETE FROM USUARIO_ROL
            WHERE id_usuario = ?
            AND id_rol = (SELECT id_rol FROM ROL WHERE tipo = ?)
        """;

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idUsuario);
            ps.setString(2, tipoRol);

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void asignarRol(int idUsuario, int idRol, Connection conn) throws SQLException {

        String sql = """
            INSERT INTO USUARIO_ROL (id_usuario, id_rol)
            VALUES (?, ?)
        """;

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idUsuario);
            ps.setInt(2, idRol);
            ps.executeUpdate();
        }
    }

    public List<Usuario> obtenerClientes() {

        String sql = """
            SELECT u.id_usuario, u.nombre, u.email
            FROM USUARIO u
            JOIN USUARIO_ROL ur ON u.id_usuario = ur.id_usuario
            JOIN ROL r ON r.id_rol = ur.id_rol
            WHERE r.tipo = 'CLIENTE'
            AND u.id_usuario NOT IN (
            SELECT ur2.id_usuario
            FROM USUARIO_ROL ur2
            JOIN ROL r2 ON r2.id_rol = ur2.id_rol
            WHERE r2.tipo = 'PROFESOR')
        """;

        return listarUsuarios(sql);
    }

    public List<Usuario> obtenerProfesores() {

        String sql = """
            SELECT u.id_usuario, u.nombre, u.email
            FROM USUARIO u
            JOIN USUARIO_ROL ur ON u.id_usuario = ur.id_usuario
            JOIN ROL r ON r.id_rol = ur.id_rol
            WHERE r.tipo = 'PROFESOR'
        """;

        return listarUsuarios(sql);
    }

    private List<Usuario> listarUsuarios(String sql) {

        List<Usuario> lista = new ArrayList<>();

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Usuario u = new Usuario();

                u.setIdUsuario(rs.getInt("id_usuario"));
                u.setNombre(rs.getString("nombre"));
                u.setEmail(rs.getString("email"));

                lista.add(u);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }

}