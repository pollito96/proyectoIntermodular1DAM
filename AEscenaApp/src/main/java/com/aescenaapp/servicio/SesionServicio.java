package com.aescenaapp.servicio;

import com.aescenaapp.dao.SesionDAO;
import com.aescenaapp.modelo.Sesion;

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
}