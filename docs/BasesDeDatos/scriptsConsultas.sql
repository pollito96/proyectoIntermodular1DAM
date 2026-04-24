-- Consultas reales integradas en la aplicacion


    /*Consulta para ver las sesiones con plazas disponibles en las que el usuario no haya hecho ya una reserva 
    añadiendo con joins el nombre de la clase desde la tabla clase y el nombre del profesor que la imparte desde la tabla usuario*/
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

    /*Esta consulta es para ver las sesiones que ya has reservado*/
    SELECT s.id_sesion, s.fecha, s.hora_inicio, s.hora_fin, s.plazas_totales, s.id_clase
            FROM SESION s
            JOIN RESERVA r ON s.id_sesion = r.id_sesion
            WHERE r.id_usuario = ?