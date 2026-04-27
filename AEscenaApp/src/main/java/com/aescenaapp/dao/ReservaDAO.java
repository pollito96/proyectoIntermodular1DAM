package com.aescenaapp.dao;

import com.aescenaapp.modelo.Reserva;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

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

    public List<Reserva> obtenerReservas() {

        String sql = """
        SELECT r.id_sesion,
               r.id_usuario
        FROM RESERVA r
    """;

        List<Reserva> lista = new ArrayList<>();

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Reserva r = new Reserva();

                r.setIdSesion(rs.getInt("id_sesion"));
                r.setIdUsuario(rs.getInt("id_usuario"));

                lista.add(r);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }
}