package com.aescenaapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ReservaDAO {

    public boolean existeReserva(int idUsuario, int idSesion) {

        String sql = """
            SELECT 1 FROM RESERVA
            WHERE id_usuario = ? AND id_sesion = ?
        """;

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idUsuario);
            ps.setInt(2, idSesion);

            ResultSet rs = ps.executeQuery();
            return rs.next();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean insertarReserva(Connection conn, int idUsuario, int idSesion) {

        String sql = """
            INSERT INTO RESERVA (id_usuario, id_sesion)
            VALUES (?, ?)
        """;

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idUsuario);
            ps.setInt(2, idSesion);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
}