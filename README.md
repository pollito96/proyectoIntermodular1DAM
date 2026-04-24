# AEscenaApp

## Descripción del proyecto

AEscenaApp es una aplicación de gestión de reservas de sesiones para una escuela de danza. Permite a distintos tipos de usuarios (clientes, profesores y administradores) interactuar con el sistema según su rol.

El sistema gestiona sesiones disponibles, reservas de usuarios y administración de clases y usuarios.

---

## Problema que resuelve

El proyecto soluciona la gestión manual de una empresa familiar pequeña así como la gestion de datos que aunque este informatizada es muy poco eficiente.

---

## Funcionalidades principales

- Login de Usuarios
- Registro de Usuarios controlando las entradas de datos con REGEX y dando el rol 'CLIENTE' por defecto 
- Capacidad del administrador de gestionar el rol de profesor
- Visualización de Usuarios separando 'CLIENTE' y 'PROFESOR' para el administrador
- Creación de moldes de Clases por parte del Usuario Admin
- Creación de Sesiones a partir de Clases por parte de Usuarios 'PROFESOR'
- Visualización de Sesiones creadas por un@ mismo para cada usuario 'PROFESOR'
- Creación de Reservas para los Usuarios 'CLIENTE'
- Visualización de Sesiones en las que tienes una Reserva como Usuario 'CLIENTE'

---

## Tecnologías utilizadas

- Java 21+
- JavaFX (interfaz gráfica)
- MySQL (base de datos)
- JDBC (conexión a base de datos)
- Arquitectura MVC (Modelo - Vista - Controlador)
- Patrón DAO (Data Access Object)

---
## Instalación y ejecución

### 1. Clonar el repositorio

```bash
git clone https://github.com/pollito96/proyectoIntermodular1DAM.git
```

### 2. Configurar la base de datos

Dentro del repositorio se encuentran los scripts SQL en: docs/BasesDeDatos/scripts/

Ejecuta los siguientes archivos en MySQL:

- scriptsCreacionDB.sql → crea la estructura de la base de datos
- scriptsInsercionDB.sql → inserta datos de ejemplo iniciales

### 3. Configurar conexion a la base de datos

Asegúrate de ajustar los datos de conexión (usuario, contraseña, URL) en la clase de conexión del proyecto: ConnectionFactory.java

proyectoIntermodular1DAM/AEscenaApp/src/main/java/com/aescenaapp/dao/ConnectionFactory.java

### 4. Ejecuta la aplicación

Ejecuta el proyecto desde tu IDE lanzando la clase: main.java

proyectoIntermodular1DAM/AEscenaApp/src/main/java/com/aescenaapp/main.java

---


## Estructura del proyecto
proyectoIntermodular1DAM/
├── AEscenaApp/
    ├── src/
    │   ├── main/
    │       ├── java/
    │       │   ├── com/
    │       │   │   ├── aescenaapp/
    │       │   │       ├── DTO/
    │       │   │       │   └── SesionDTO.java
    │       │   │       ├── controlador/
    │       │   │       │   ├── AdminControlador.java
    │       │   │       │   ├── ClasesAdminControlador.java
    │       │   │       │   ├── ClasesProfeControlador.java
    │       │   │       │   ├── IndexControlador.java
    │       │   │       │   ├── LoginControlador.java
    │       │   │       │   ├── RegistroControlador.java
    │       │   │       │   ├── ReservaControlador.java
    │       │   │       │   └── SesionControlador.java
    │       │   │       ├── dao/
    │       │   │       │   ├── ClaseDAO.java
    │       │   │       │   ├── ConnectionFactory.java
    │       │   │       │   ├── ReservaDAO.java
    │       │   │       │   ├── SesionDAO.java
    │       │   │       │   └── UsuarioDAO.java
    │       │   │       ├── modelo/
    │       │   │       │   ├── Clase.java
    │       │   │       │   ├── Reserva.java
    │       │   │       │   ├── Rol.java
    │       │   │       │   ├── Sesion.java
    │       │   │       │   └── Usuario.java
    │       │   │       ├── servicio/
    │       │   │       │   ├── ClaseServicio.java
    │       │   │       │   ├── ReservaServicio.java
    │       │   │       │   ├── SesionServicio.java
    │       │   │       │   └── UsuarioServicio.java
    │       │   │       ├── util/
    │       │   │       │   ├── GestorNavegacion.java
    │       │   │       │   ├── GestorSesion.java
    │       │   │       │   └── ValidacionUtil.java
    │       │   │       └── AEscenaApp.java
    │       │   └── module-info.java
    │       ├── resources/
    │           ├── com/
    │               ├── aescenaapp/
    │                   ├── adminPanel.fxml
    │                   ├── clasesAdminPanel.fxml
    │                   ├── clasesProfePanel.fxml
    │                   ├── index.fxml
    │                   ├── login.fxml
    │                   ├── registro.fxml
    │                   ├── reservaPanel.fxml
    │                   └── sesionPanel.fxml
    ├── mvnw
    ├── mvnw.cmd
    └── pom.xml 
├── docs/
    ├── assets/
        └──images/
            ├── admin.PNG
            ├── registro.PNG
            └── sesiones.PNG
    ├── BasesDeDatos/
        ├── diagrams
            ├── Modelo_ER.png
            ├── Modelo_ER.TXT
            ├── ModdeloRelacional.png
            └── ModeloRelacional.TXT
        ├── scripts
            ├── scriptsConsultas.sql 
            ├── scriptsCreacionDB.sql 
            └── scriptsInsercionDB.sql 
        └── README.md
    ├── Empleabilidad/
    ├── EntornosDeDesarrollo/
    ├── LenguajeDeMarcas/
    ├── MPO/
    └── Sistemas/
├── .gitattributes
└── README.md

---

## Capturas

### Pantalla de registro

![Registro](docs/assets/images/registro.PNG)

### Panel de administración

![Administración](docs/assets/images/admin.PNG)

### Gestión de sesiones 

![Sesiones](docs/assets/images/sesiones.PNG)
