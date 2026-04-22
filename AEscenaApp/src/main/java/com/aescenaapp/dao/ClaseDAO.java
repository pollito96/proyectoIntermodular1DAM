package com.aescenaapp.dao;

import com.aescenaapp.modelo.Clase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClaseDAO {

    public List<Clase> obtenerClases() {

        String sql = """
            SELECT id_clase, nombre, descripcion
            FROM CLASE
        """;

        List<Clase> lista = new ArrayList<>();

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Clase c = new Clase();

                c.setIdClase(rs.getInt("id_clase"));
                c.setNombre(rs.getString("nombre"));
                c.setDescripcion(rs.getString("descripcion"));

                lista.add(c);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }

    public boolean insertarClase(Clase c) {

        String sql = """
            INSERT INTO CLASE (nombre, descripcion)
            VALUES (?, ?)
        """;

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, c.getNombre());
            ps.setString(2, c.getDescripcion());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean actualizarClase(Clase c) {

        String sql = """
            UPDATE CLASE
            SET nombre = ?, descripcion = ?
            WHERE id_clase = ?
        """;

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, c.getNombre());
            ps.setString(2, c.getDescripcion());
            ps.setInt(3, c.getIdClase());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean eliminarClase(int idClase) {

        String sql = """
            DELETE FROM CLASE
            WHERE id_clase = ?
        """;

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idClase);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
}