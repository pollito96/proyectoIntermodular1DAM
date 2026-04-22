package com.aescenaapp.servicio;

import com.aescenaapp.dao.ClaseDAO;
import com.aescenaapp.modelo.Clase;

import java.util.List;

public class ClaseServicio {

    private final ClaseDAO claseDAO = new ClaseDAO();

    public List<Clase> listarClases() {
        return claseDAO.obtenerClases();
    }

    public boolean crearClase(Clase c) {

        if (c == null) return false;
        if (c.getNombre() == null || c.getNombre().isBlank()) return false;

        return claseDAO.insertarClase(c);
    }

    public boolean actualizarClase(Clase c) {

        if (c == null || c.getIdClase() <= 0) return false;
        if (c.getNombre() == null || c.getNombre().isBlank()) return false;

        return claseDAO.actualizarClase(c);
    }

    public boolean eliminarClase(int idClase) {

        if (idClase <= 0) return false;

        return claseDAO.eliminarClase(idClase);
    }
}