package com.aescenaapp.servicio;

import com.aescenaapp.dao.ConnectionFactory;
import com.aescenaapp.dao.ReservaDAO;
import com.aescenaapp.dao.SesionDAO;

import java.sql.Connection;

public class ReservaServicio {

    private final ReservaDAO reservaDAO = new ReservaDAO();
    private final SesionDAO sesionDAO = new SesionDAO();

    public boolean crearReserva(int idUsuario, int idSesion) {

        try (Connection conn = ConnectionFactory.getConnection()) {

            conn.setAutoCommit(false);

            if (reservaDAO.existeReserva(idUsuario, idSesion)) {
                conn.rollback();
                return false;
            }

            boolean insertOk = reservaDAO.insertarReserva(conn, idUsuario, idSesion);

            boolean updateOk = sesionDAO.restarPlaza(conn, idSesion);

            if (!insertOk || !updateOk) {
                conn.rollback();
                return false;
            }

            conn.commit();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
}