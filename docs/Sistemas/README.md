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

## Instalación y ejecución

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