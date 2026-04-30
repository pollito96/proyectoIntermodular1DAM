
-- Queries DML

INSERT INTO ROL (tipo) VALUES
('CLIENTE'),
('PROFESOR'),
('ADMIN');

INSERT INTO USUARIO (email, pass, nombre)
VALUES 
('admin@gmail.com', 'Qwer1234_', 'admin'),
('profe@gmail.com', 'Qwer1234_', 'profe'),
('cliente@gmail.com', 'Qwer1234_', 'cliente');

INSERT INTO USUARIO_ROL (id_usuario, id_rol)
VALUES 
(1, 1),
(1, 2),
(1, 3),
(2, 1),
(2, 2),
(3, 1);

INSERT INTO CLASE (nombre, descripcion) VALUES
('Baile urbano', 'Coreografías modernas con hip hop y street dance'),
('Salsa', 'Baile latino en pareja con pasos básicos y giros'),
('Bachata', 'Ritmos caribeños con movimientos suaves'),
('Danza contemporánea', 'Expresión corporal con técnica moderna');

INSERT INTO SESION (fecha, hora_inicio, hora_fin, plazas_totales, id_clase, id_usuario) VALUES
('2026-05-01', '18:00:00', '19:30:00', 20, 1, 2),
('2026-05-03', '18:00:00', '19:30:00', 20, 1, 2),
('2026-05-02', '19:00:00', '20:30:00', 16, 2, 2),
('2026-05-04', '19:00:00', '20:30:00', 16, 2, 2),
('2026-05-01', '19:00:00', '20:00:00', 18, 3, 2),
('2026-05-03', '19:00:00', '20:00:00', 18, 3, 2),
('2026-05-02', '17:00:00', '18:30:00', 12, 4, 2),
('2026-05-04', '17:00:00', '18:30:00', 12, 4, 2);