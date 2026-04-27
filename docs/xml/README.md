## Exportación XML

El sistema implementa un módulo de exportación de datos que genera un informe en formato XML con la información de las sesiones y sus reservas asociadas. Cada informe es creado dentro de una carpera con la fecha actual.

El archivo se genera automáticamente desde la aplicación y se almacena en la ruta: /docs/xml/exportaciones/fecha

- Identificador de la sesión
- Fecha y horario de la sesión
- Plazas totales disponibles
- Nombre de la lase asociada
- Profesor responsable
- Listado de id de usuario con reserva (id para evitar datos iguales podrian ser los nombres)

---

## Validación
El documento XML generado es validado mediante un esquema XSD (`esquema.xsd`) que garantiza:

- Estructura jerárquica correcta del documento
- Tipos de datos estrictos (`xs:date`, `xs:time`, `xs:int`)
- Cardinalidad de elementos (reservas múltiples por sesión)

Esta validación asegura la integridad y coherencia del documento exportado.

---

## Evidencia de validación

Se adjuntan capturas que demuestran el correcto funcionamiento del sistema de validación:

### XML validado correctamente contra el XSD

![Validación correcta](docs/assets/capturas_validacion/Validacion_Correcta.png)

### Ejemplo de error detectado por el XSD

![Validación fallida](docs/assets/capturas_validacion/Validacion_Fallida.png)

---

## Integración en el sistema

Esta funcionalidad forma parte del módulo de mejora del proyecto (MPO), permitiendo la exportación de datos del sistema a un formato XML.

La generación del archivo está integrada en la capa de servicio de la aplicación y se alimenta directamente de la base de datos mediante DTOs y DAOs, asegurando consistencia entre los datos del sistema y la exportación.

La exportación es unica de cada fecha y hora debido a una decisión personal de diseño para mantener un histórico de exportaciones.