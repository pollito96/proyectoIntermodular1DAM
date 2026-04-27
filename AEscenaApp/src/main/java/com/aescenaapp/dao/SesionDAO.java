package com.aescenaapp.dao;

import com.aescenaapp.DTO.SesionDTO;
import com.aescenaapp.modelo.Sesion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SesionDAO {

    public boolean insertarSesion(Sesion s) {

        String sql = """
            INSERT INTO SESION (
                fecha,
                hora_inicio,
                hora_fin,
                plazas_totales,
                id_clase,
                id_usuario
            )
            VALUES (?, ?, ?, ?, ?, ?)
        """;

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setDate(1, Date.valueOf(s.getFecha()));
            ps.setTime(2, Time.valueOf(s.getHoraInicio()));
            ps.setTime(3, Time.valueOf(s.getHoraFin()));
            ps.setInt(4, s.getPlazasTotales());
            ps.setInt(5, s.getIdClase());
            ps.setInt(6, s.getIdUsuario());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public List<SesionDTO> obtenerSesionesPorProfesor(int idUsuario) {

        String sql = """
        SELECT
            s.id_sesion,
            s.fecha,
            s.hora_inicio,
            s.hora_fin,
            s.plazas_totales,
            c.nombre AS nombre_clase,
            u.nombre AS nombre_profesor,

            CASE
                WHEN s.id_usuario = ? THEN 'CREADA'
                ELSE 'RESERVADA'
            END AS tipo

        FROM SESION s
        JOIN CLASE c ON s.id_clase = c.id_clase
        JOIN USUARIO u ON s.id_usuario = u.id_usuario

        WHERE
            s.id_usuario = ?
            OR EXISTS (
                SELECT 1
                FROM RESERVA r
                WHERE r.id_sesion = s.id_sesion
                AND r.id_usuario = ?
            )
    """;

        return listarSesiones(sql, idUsuario, idUsuario, idUsuario);
    }

    public List<SesionDTO> obtenerSesionesReservadas(int idUsuario) {

        String sql = """
        SELECT
            s.id_sesion,
            s.fecha,
            s.hora_inicio,
            s.hora_fin,
            s.plazas_totales,
            c.nombre AS nombre_clase,
            u.nombre AS nombre_profesor,

            'RESERVADA' AS tipo

        FROM SESION s
        JOIN CLASE c ON s.id_clase = c.id_clase
        JOIN USUARIO u ON s.id_usuario = u.id_usuario
        JOIN RESERVA r ON s.id_sesion = r.id_sesion

        WHERE r.id_usuario = ?
    """;

        return listarSesiones(sql, idUsuario);
    }

    public List<SesionDTO> obtenerSesionesDisponibles(int idUsuario) {

        String sql = """
        SELECT s.id_sesion, s.fecha, s.hora_inicio, s.hora_fin,
               s.plazas_totales,
               c.nombre AS nombre_clase,
               u.nombre AS nombre_profesor
        FROM SESION s
        JOIN CLASE c ON s.id_clase = c.id_clase
        JOIN USUARIO u ON s.id_usuario = u.id_usuario
        WHERE s.plazas_totales > 0
        AND NOT EXISTS (
            SELECT 1
            FROM RESERVA r
            WHERE r.id_sesion = s.id_sesion
            AND r.id_usuario = ?
        )
    """;

        List<SesionDTO> lista = new ArrayList<>();

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idUsuario);

            try (ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {

                    SesionDTO s = new SesionDTO();

                    s.setIdSesion(rs.getInt("id_sesion"));
                    s.setFecha(rs.getDate("fecha").toLocalDate());
                    s.setHoraInicio(rs.getTime("hora_inicio").toLocalTime());
                    s.setHoraFin(rs.getTime("hora_fin").toLocalTime());
                    s.setPlazasTotales(rs.getInt("plazas_totales"));

                    s.setNombreClase(rs.getString("nombre_clase"));
                    s.setNombreProfesor(rs.getString("nombre_profesor"));

                    lista.add(s);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }

    private List<SesionDTO> listarSesiones(String sql, Object... params) {

        List<SesionDTO> lista = new ArrayList<>();

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            for (int i = 0; i < params.length; i++) {
                ps.setObject(i + 1, params[i]);
            }

            try (ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {

                    SesionDTO s = new SesionDTO();

                    s.setIdSesion(rs.getInt("id_sesion"));
                    s.setFecha(rs.getDate("fecha").toLocalDate());
                    s.setHoraInicio(rs.getTime("hora_inicio").toLocalTime());
                    s.setHoraFin(rs.getTime("hora_fin").toLocalTime());
                    s.setPlazasTotales(rs.getInt("plazas_totales"));

                    // estos vienen de JOIN
                    s.setNombreClase(rs.getString("nombre_clase"));
                    s.setNombreProfesor(rs.getString("nombre_profesor"));

                    // puede venir o no según query
                    s.setTipo(rs.getString("tipo"));

                    lista.add(s);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }

    public boolean restarPlaza(Connection conn, int idSesion) {

        String sql = """
        UPDATE SESION
        SET plazas_totales = plazas_totales - 1
        WHERE id_sesion = ? AND plazas_totales > 0
    """;

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idSesion);
            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
}