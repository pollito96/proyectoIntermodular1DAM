package com.aescenaapp.dao;

import com.aescenaapp.modelo.Sesion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Date;
import java.sql.Time;

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
}