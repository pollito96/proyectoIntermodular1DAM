
-- Queries DDL

CREATE DATABASE aescena;
USE aescena;

CREATE TABLE USUARIO (
    id_usuario INT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(255) NOT NULL UNIQUE,
    pass VARCHAR(255) NOT NULL,
    nombre VARCHAR(100) NOT NULL,
    estado TINYINT(1) NOT NULL DEFAULT 1,
    fecha_creacion DATETIME DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE ROL (
  id_rol INT AUTO_INCREMENT PRIMARY KEY,
  tipo VARCHAR(20) UNIQUE NOT NULL
);

CREATE TABLE USUARIO_ROL (
    id_usuario INT NOT NULL,
    id_rol INT NOT NULL,

    PRIMARY KEY (id_usuario, id_rol),

    FOREIGN KEY (id_usuario) REFERENCES USUARIO(id_usuario)
        ON DELETE CASCADE,

    FOREIGN KEY (id_rol) REFERENCES ROL(id_rol)
        ON DELETE CASCADE
);

CREATE TABLE CLASE (
    id_clase INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    descripcion TEXT 
);

CREATE TABLE SESION (
    id_sesion INT AUTO_INCREMENT PRIMARY KEY,
    fecha DATE NOT NULL,
    hora_inicio TIME NOT NULL,
    hora_fin TIME NOT NULL,
    plazas_totales INT NOT NULL,
    id_clase INT NOT NULL,
    id_usuario INT NOT NULL,

    FOREIGN KEY (id_clase) REFERENCES CLASE(id_clase)
        ON DELETE CASCADE,

    FOREIGN KEY (id_usuario) REFERENCES USUARIO(id_usuario)
        on DELETE RESTRICT,

    CONSTRAINT chk_horas CHECK (hora_fin > hora_inicio)
);

CREATE TABLE RESERVA (
    id-reserva INT AUTO_INCREMENT PRIMARY KEY,
    fecha_reserva DATETIME DEFAULT CURRENT_TIMESTAMP,
    id_usuario INT NOT NULL,
    id_sesion INT NOT NULL,

    FOREIGN KEY (id_usuario) REFERENCES USUARIO(id_usuario)
        ON DELETE RESTRICT,

    FOREIGN KEY (id_sesion) REFERENCES SESION(id_sesion)
        ON DELETE CASCADE,

    UNIQUE (id_usuario, id_sesion)
);
