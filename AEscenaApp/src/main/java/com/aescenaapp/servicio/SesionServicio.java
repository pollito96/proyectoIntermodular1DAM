package com.aescenaapp.servicio;

import com.aescenaapp.DTO.SesionDTO;
import com.aescenaapp.dao.SesionDAO;
import com.aescenaapp.modelo.Sesion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

public class SesionServicio {

    private final SesionDAO sesionDAO = new SesionDAO();

    public String crearSesion(Sesion s) {

        if (s == null) return "SESION_NULL";

        if (s.getFecha() == null) return "FECHA_NULL";

        if (s.getHoraInicio() == null || s.getHoraFin() == null) return "HORAS_NULL";

        if (s.getPlazasTotales() <= 0) return "PLAZAS_INVALIDAS";

        if (s.getIdClase() <= 0) return "CLASE_INVALIDA";

        if (s.getIdUsuario() <= 0) return "USUARIO_INVALIDO";

        if (s.getHoraFin().isBefore(s.getHoraInicio())) return "HORARIO_INVALIDO";

        boolean ok = sesionDAO.insertarSesion(s);

        return ok ? "OK" : "ERROR_BD";
    }

    public List<SesionDTO> obtenerSesionesPorProfesor(int idUsuario) {
        return sesionDAO.obtenerSesionesPorProfesor(idUsuario);
    }

    public List<SesionDTO> obtenerSesionesReservadas(int idUsuario) {
        return sesionDAO.obtenerSesionesReservadas(idUsuario);
    }

    public List<SesionDTO> obtenerSesionesDisponibles(int idUsuario) {
        return sesionDAO.obtenerSesionesDisponibles(idUsuario);
    }
}