# Informe Técnico de Entorno de Ejecución

## 1. Tipo de sistema donde se ejecuta la aplicación

La aplicación se ejecuta en un entorno de escritorio (PC de usuario). 

Al ser uno de mis objetivos desarrollar la aplicación con JavaFX consideré que la forma más sencilla para el usuario promedio seria que se ejecute en un entorno de escritorio.

---

## 2. Requisitos de hardware

### Uso:
- CPU: El uso de CPU apenas es notable
- RAM: Apenas 2 GB debido principalmente al IDE, 126 MB de la aplicación.
- Almacenamiento : 1,7 MB 

### Requisitos mínimos:
- CPU: Intel i3 / AMD Ryzen 3 o equivalente
- RAM: 4 GB 
- Almacenamiento: 100 MB libres 

### Requisitos recomendados:
- CPU: Intel i5 / AMD Ryzen 5 o superior
- RAM: 8 GB
- Almacenamiento: 200 MB libre

### Periféricos:
- Pantalla
- Ratón
- Teclado

---

## 3. Sistema operativo recomendado

- Sistema operativo principal: Windows 10 / **Windows 11**

La elección de Windows concretamente el 11 se debe a que es una aplicación desarrollada para gestionar una empresa pequeña real en la que el unico sistema operativo que manejan es Windows. En concreto, se recomienda Windows 11 por ser la versión con soporte más actualizado y mejoras en rendimiento, seguridad y compatibilidad con software actual.

---

## 4. Instalación y ejecución

### 1. Clonar el repositorio

Para obtener el proyecto, es necesario clonar el repositorio desde GitHub en el equipo local.

#### Pasos:

1. Crear una carpeta en el sistema donde se alojará el proyecto.
2. Abrir dicha carpeta en el explorador de archivos.
3. Abrir una terminal en esa ubicación:
   - En Windows, se puede escribir `cmd` en la barra de ruta del explorador o usar “Abrir en terminal”.

4. Ejecutar el siguiente comando:

```bash
git clone https://github.com/pollito96/proyectoIntermodular1DAM.git
```

---

### 2. Instalación y configuración de la base de datos

La aplicación utiliza MySQL como sistema de gestión de base de datos, por lo que es necesario disponer de un entorno de servidor local para su ejecución.

---

#### 2.1 Instalación de XAMPP

Para facilitar la gestión del servidor MySQL, se utiliza XAMPP como entorno local.

Pasos:

1. Descargar XAMPP desde su página oficial.
2. Instalar el paquete seleccionando los componentes:
   - Apache
   - MySQL
3. Iniciar el panel de control de XAMPP.
4. Activar los servicios de **Apache** y **MySQL**.

---

#### 2.2 Configuración de MySQL

Una vez iniciado MySQL en XAMPP:

1. Acceder a `http://localhost/phpmyadmin`.
2. Crear una nueva base de datos.
3. Importar los scripts SQL ubicados en el proyecto:
```
docs/BasesDeDatos/scripts/
```
Ejecutar en el siguiente orden:

- scriptsCreacionDB.sql → crea la estructura de la base de datos
- scriptsInsercionDB.sql → inserta datos de ejemplo 

#### 2.3 Verificación

Tras la importación, se debe comprobar que las tablas han sido creadas correctamente y que los datos se encuentran disponibles para la ejecución de la aplicación.

---

### 3. Configurar conexion a la base de datos

Asegúrate de ajustar los datos de conexión (usuario, contraseña, URL) en la clase de conexión del proyecto: ConnectionFactory.java
```
proyectoIntermodular1DAM/AEscenaApp/src/main/java/com/aescenaapp/dao/ConnectionFactory.java
```

#### Pasos de configuración:

1. Abrir el proyecto en un entorno de desarrollo (IDE) como IntelliJ IDEA o NetBeans.
2. Localizar la clase `ConnectionFactory.java`.
3. Modificar los siguientes parámetros según la configuración local de MySQL:
   - URL de conexión
   - Usuario de la base de datos
   - Contraseña

---
### 4. Ejecuta la aplicación

Ejecuta el proyecto desde tu IDE lanzando la clase: main.java
```
proyectoIntermodular1DAM/AEscenaApp/src/main/java/com/aescenaapp/main.java
```

---

## 5. Usuarios, permisos y estructura del sistema

### 5.1 Tipos de usuarios

La aplicación define tres tipos de usuarios principales:

- **Administrador**: gestión completa del sistema.
- **Profesor**: creación y gestión de sesiones.
- **Cliente**: visualización y reserva de sesiones.

---

### 5.2 Permisos del sistema

- **Administrador**
  - Gestión de usuarios
  - Creación de clases
  - 

- **Profesor**
  - Creación de sesiones
  - Consulta de sesiones creadas
  - Gestión de plazas disponibles

- **Cliente**
  - Visualización de sesiones disponibles
  - Realización de reservas
  - Consulta de sesiones reservadas

---

### 5.3 Carpetas utilizadas

La carpeta principalmente utilizada es:


```
proyectoIntermodular1DAM/AEscenaApp/src/main/..

```

Que donde queda contenida la aplicacion principalmente, también se utiliza:

´´´
proyectoIntermodular1DAM/docs/xml/exportaciones/..
´´´

Aquí es donde quedan guardadas las exportaciones xml de las sesiones y reservas actuales con el formato /fecha_actual/registro_sesiones_hora_actual.xml

### 5.4 Almacenamiento de datos

- **Base de datos MySQL**:
  - Almacena usuarios, sesiones, reservas y clases
  - Gestionada mediante XAMPP en entorno local 

- **Archivos XML**:
  - Se generan exportaciones mencionadas anteriormente.
  
---

### 5.5 Copias de seguridad

- La base de datos puede exportarse mediante scripts SQL ubicados en:

```
docs/BasesDeDatos/scripts/
```
- Las exportaciones XML funcionan como respaldo de información de sesiones y reservas.

## 6. Mantenimiento básico del sistema

### 6.1 Elementos a actualizar

- Dependencias del proyecto (Java, JavaFX, librerías JDBC)
- Sistema de gestión de base de datos (MySQL)
- Scripts de base de datos si se amplía la estructura
- Posibles mejoras en la interfaz o lógica de negocio

---

### 6.2 Frecuencia de actualización

- Dependencias del sistema: cada vez que haya versiones estables o mejoras de seguridad.
- Base de datos: cuando se realicen cambios en la estructura o funcionalidades.
- Aplicación: tras la incorporación de nuevas funcionalidades o corrección de errores.

---

### 6.3 Elementos a revisar

- Correcto funcionamiento de la conexión a la base de datos
- Integridad de los datos (usuarios, sesiones y reservas)
- Generación correcta de exportaciones XML
- Validación del XML mediante el esquema XSD
- Funcionamiento de los distintos roles de usuario

---

### 6.4 Gestión de errores o fallos

En caso de fallo del sistema:

- Revisar los logs de la aplicación en consola
- Comprobar la conexión a la base de datos
- Verificar que los scripts SQL están correctamente ejecutados
- Restaurar la base de datos desde los scripts de backup si es necesario
- Revisar posibles errores en la configuración del `ConnectionFactory`

---

### 6.5 Copias de seguridad

Se recomienda realizar copias de seguridad periódicas de:

- Base de datos MySQL mediante exportación de scripts SQL
- Archivos XML generados en el sistema

Esto permite recuperar el estado del sistema en caso de error crítico.

## Estructura del proyecto 

```
proyectoIntermodular1DAM/
├── AEscenaApp/
│   ├── src/
│   │   ├── main/
│   │       ├── java/
│   │       │   ├── com/
│   │       │   │   ├── aescenaapp/
│   │       │   │       ├── DTO/
│   │       │   │       │   └── SesionDTO.java
│   │       │   │       ├── controlador/
│   │       │   │       │   ├── AdminControlador.java
│   │       │   │       │   ├── ClasesAdminControlador.java
│   │       │   │       │   ├── ClasesProfeControlador.java
│   │       │   │       │   ├── IndexControlador.java
│   │       │   │       │   ├── LoginControlador.java
│   │       │   │       │   ├── RegistroControlador.java
│   │       │   │       │   ├── ReservaControlador.java
│   │       │   │       │   └── SesionControlador.java
│   │       │   │       ├── dao/
│   │       │   │       │   ├── ClaseDAO.java
│   │       │   │       │   ├── ConnectionFactory.java
│   │       │   │       │   ├── ReservaDAO.java
│   │       │   │       │   ├── SesionDAO.java
│   │       │   │       │   └── UsuarioDAO.java
│   │       │   │       ├── modelo/
│   │       │   │       │   ├── Clase.java
│   │       │   │       │   ├── Reserva.java
│   │       │   │       │   ├── Rol.java
│   │       │   │       │   ├── Sesion.java
│   │       │   │       │   └── Usuario.java
│   │       │   │       ├── servicio/
│   │       │   │       │   ├── ClaseServicio.java
│   │       │   │       │   ├── ExportServicio.java
│   │       │   │       │   ├── ReservaServicio.java
│   │       │   │       │   ├── SesionServicio.java
│   │       │   │       │   └── UsuarioServicio.java
│   │       │   │       ├── util/
│   │       │   │       │   ├── GestorNavegacion.java
│   │       │   │       │   ├── GestorSesion.java
│   │       │   │       │   ├── ValidacionUtil.java
│   │       │   │       │   └── XmlExport.java
│   │       │   │       └── main.java
│   │       │   └── module-info.java
│   │       ├── resources/
│   │           ├── com/
│   │               ├── aescenaapp/
│   │                   ├── adminPanel.fxml
│   │                   ├── clasesAdminPanel.fxml
│   │                   ├── clasesProfePanel.fxml
│   │                   ├── index.fxml
│   │                   ├── login.fxml
│   │                   ├── registro.fxml
│   │                   ├── reservaPanel.fxml
│   │                   └── sesionPanel.fxml
│   ├── target/
│   │   ├── classes/
│   │   │   ├── com/
│   │   │   │   ├── aescenaapp/
│   │   │   │       ├── DTO/
│   │   │   │       │   └── SesionDTO.class
│   │   │   │       ├── controlador/
│   │   │   │       │   ├── AdminControlador$1.class
│   │   │   │       │   ├── AdminControlador.class
│   │   │   │       │   ├── ClasesAdminControlador.class
│   │   │   │       │   ├── ClasesProfeControlador.class
│   │   │   │       │   ├── IndexControlador.class
│   │   │   │       │   ├── LoginControlador.class
│   │   │   │       │   ├── RegistroControlador.class
│   │   │   │       │   ├── ReservaControlador$1.class
│   │   │   │       │   ├── ReservaControlador.class
│   │   │   │       │   └── SesionControlador.class
│   │   │   │       ├── dao/
│   │   │   │       │   ├── ClaseDAO.class
│   │   │   │       │   ├── ConnectionFactory.class
│   │   │   │       │   ├── ReservaDAO.class
│   │   │   │       │   ├── SesionDAO.class
│   │   │   │       │   └── UsuarioDAO.class
│   │   │   │       ├── modelo/
│   │   │   │       │   ├── Clase.class
│   │   │   │       │   ├── Reserva.class
│   │   │   │       │   ├── Rol.class
│   │   │   │       │   ├── Sesion.class
│   │   │   │       │   └── Usuario.class
│   │   │   │       ├── servicio/
│   │   │   │       │   ├── ClaseServicio.class
│   │   │   │       │   ├── ExportServicio.class
│   │   │   │       │   ├── ReservaServicio.class
│   │   │   │       │   ├── SesionServicio.class
│   │   │   │       │   └── UsuarioServicio.class
│   │   │   │       ├── util/
│   │   │   │       │   ├── GestorNavegacion.class
│   │   │   │       │   ├── GestorSesion.class
│   │   │   │       │   ├── ValidacionUtil.class
│   │   │   │       │   └── XmlExport.class
│   │   │   │       ├── adminPanel.fxml
│   │   │   │       ├── clasesAdminPanel.fxml
│   │   │   │       ├── clasesProfePanel.fxml
│   │   │   │       ├── index.fxml
│   │   │   │       ├── login.fxml
│   │   │   │       ├── main.class
│   │   │   │       ├── registro.fxml
│   │   │   │       ├── reservaPanel.fxml
│   │   │   │       └── sesionPanel.fxml
│   │   │   └── module-info.class
│   │   ├── generated-sources/
│   │       ├── annotations/
│   ├── mvnw
│   ├── mvnw.cmd
│   └── pom.xml
├── docs/
│   ├── BasesDeDatos/
│   │   ├── diagrams/
│   │   │   ├── ModeloRelacional.TXT
│   │   │   ├── ModeloRelacional.png
│   │   │   ├── Modelo_ER.TXT
│   │   │   └── Modelo_ER.png
│   │   ├── scripts/
│   │   │   ├── scriptsConsultas.sql
│   │   │   ├── scriptsCreacionDB.sql
│   │   │   └── scriptsInsercionDB.sql
│   │   └── README.md
│   ├── Empleabilidad/
│   │   └── README.md
│   ├── MPO/
│   │   └── README.md
│   ├── Sistemas/
│   │   └── README.md
│   ├── assets/
│   │   ├── images/
│   │       ├── admin.PNG
│   │       ├── registro.PNG
│   │       └── sesiones.PNG
│   ├── xml/
│       ├── capturas_validacion/
│       │   ├── Validacion_Correcta.PNG
│       │   └── Validacion_Fallida.PNG
│       ├── exportaciones/
│       │   ├── 2026-04-27/
│       │       └── registro_sesiones_15-36-38.xml
│       ├── README.md
│       └── esquema.xsd
└── README.md
```



---

## Capturas

### Pantalla de registro

![Registro](docs/assets/images/registro.PNG)

### Panel de administración

![Administración](docs/assets/images/admin.PNG)

### Gestión de sesiones 

![Sesiones](docs/assets/images/sesiones.PNG)
