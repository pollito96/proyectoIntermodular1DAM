package com.aescenaapp.servicio;

import com.aescenaapp.DTO.SesionDTO;
import com.aescenaapp.dao.ReservaDAO;
import com.aescenaapp.dao.SesionDAO;
import com.aescenaapp.modelo.Reserva;
import com.aescenaapp.util.XmlExport;

import java.util.List;

public class ExportServicio {
    private final SesionDAO sesionDAO = new SesionDAO();
    private final ReservaDAO reservaDAO = new ReservaDAO();

    public void exportarSesionesXML() {

        List<SesionDTO> sesiones = sesionDAO.obtenerSesionesDTO();
        List<Reserva> reservas = reservaDAO.obtenerReservas();

        XmlExport.generarXML(sesiones, reservas);
    }
}
