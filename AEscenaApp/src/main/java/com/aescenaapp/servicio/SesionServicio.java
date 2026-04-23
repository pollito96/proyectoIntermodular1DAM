package com.aescenaapp.servicio;

import com.aescenaapp.DTO.SesionDTO;
import com.aescenaapp.dao.SesionDAO;
import com.aescenaapp.modelo.Sesion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

public class SesionServicio {

    private final SesionDAO sesionDAO = new SesionDAO();

    public boolean crearSesion(Sesion s) {

        if (s == null) return false;

        if (s.getFecha() == null) return false;

        if (s.getHoraInicio() == null || s.getHoraFin() == null) return false;

        if (s.getPlazasTotales() <= 0) return false;

        if (s.getIdClase() <= 0) return false;

        if (s.getIdUsuario() <= 0) return false;

        if (s.getHoraFin().isBefore(s.getHoraInicio())) return false;

        return sesionDAO.insertarSesion(s);
    }

    public List<Sesion> obtenerSesionesPorProfesor(int idUsuario) {
        return sesionDAO.obtenerSesionesPorProfesor(idUsuario);
    }


    public List<Sesion> obtenerSesionesReservadas(int idUsuario) {
        return sesionDAO.obtenerSesionesReservadas(idUsuario);
    }

    public List<SesionDTO> obtenerSesionesDisponibles(int idUsuario) {
        return sesionDAO.obtenerSesionesDisponibles(idUsuario);
    }
}