package com.aescenaapp.util;

import com.aescenaapp.DTO.SesionDTO;
import com.aescenaapp.modelo.Reserva;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class XmlExport {
    public static void generarXML(List<SesionDTO> sesiones, List<Reserva> reservas) {

        try {

            Map<Integer, List<Reserva>> reservasPorSesion =
                    reservas.stream()
                            .collect(Collectors.groupingBy(Reserva::getIdSesion));

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            Document doc = builder.newDocument();

            Element root = doc.createElement("reporteSesiones");
            doc.appendChild(root);

            for (SesionDTO s : sesiones) {

                DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

                Element sesion = doc.createElement("sesion");
                root.appendChild(sesion);

                addElement(doc, sesion, "id", String.valueOf(s.getIdSesion()));
                addElement(doc, sesion, "fecha", s.getFecha().toString());
                addElement(doc, sesion, "horaInicio", s.getHoraInicio().format(timeFormatter));
                addElement(doc, sesion, "horaFin", s.getHoraFin().format(timeFormatter));
                addElement(doc, sesion, "plazas", String.valueOf(s.getPlazasTotales()));


                if (s.getNombreClase() != null) {
                    addElement(doc, sesion, "clase", s.getNombreClase());
                }

                if (s.getNombreProfesor() != null) {
                    addElement(doc, sesion, "profesor", s.getNombreProfesor());
                }

                Element nodoReservas = doc.createElement("reservas");
                sesion.appendChild(nodoReservas);

                List<Reserva> lista = reservasPorSesion.get(s.getIdSesion());

                if (lista != null) {

                    for (Reserva r : lista) {

                        Element reserva = doc.createElement("reserva");

                        addElement(doc, reserva, "idUsuario",
                                String.valueOf(r.getIdUsuario()));

                        nodoReservas.appendChild(reserva);
                    }
                }
            }

            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer();

            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

            DOMSource source = new DOMSource(doc);

            String fecha = LocalDateTime.now()
                    .format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

            String hora = LocalDateTime.now()
                    .format(DateTimeFormatter.ofPattern("HH-mm-ss"));

            String basePath = System.getProperty("user.dir");
            File file = new File(basePath + "/../docs/xml/exportaciones/" + fecha + "/registro_sesiones_" + hora + ".xml");
            file.getParentFile().mkdirs();

            StreamResult result = new StreamResult(file);

            transformer.transform(source, result);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private static void addElement(Document doc, Element parent, String tag, String value) {
        Element node = doc.createElement(tag);
        node.setTextContent(value);
        parent.appendChild(node);
    }
}

