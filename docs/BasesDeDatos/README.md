# AEscena - Diseño de DB

## Objetivo del sistema

Uno de los objetivos especificos del diseño era que los usuarios pudiesen tener varios roles simultaneamente, de ahi que haya usado herencia para diferenciar lso diferentes roles y si un tabla intermedia, tambien posibilitando asi la posible futura escalabilidad a personal de diferente tipo en la empresa.

Más allá de eso es un sistema de creacion de clases, sesiones y reservas para una escuela de danza.

---

## Modelo de datos
 
### Usuario

He elegido un diseño simple en este caso a nivel de campos pero con algunas decisiones que considero importantes como:

-Estado: En vez de borrar a los usuarios en caso de darse de baja he añadido el atributo estado que representa alta o baja en forma de booleano.
-fecha_creacion: Me ha parecido importante guardar y mantener esta informacion que, aunque no sea usada en la aplicacion creo que en un nivel más profesional puede ser importante en cuanto a auditorias.

---

### Rol

Aqui se definen los posibles roles de cada usuario, relacionandose con estos por medio de una tabla intermedia "Usuario_Rol", como he explicado anteriormente este diseño me parecio mas facilmente escalable en caso de tener la necesidad de añadir nuevos roles o como es el caso de la empresa real en la que he inspirado la aplicación, los usuarios puedan tener varios roles simultaneamente.

---

### Clase

El diseño de clase esta pensado para ser simplemente un molde, creado por el administrador, sobre el que luego los profesores podran crear sesiones. 

---

### Sesion

Sesion sale directamente del molde de cada clase pero concretando una fecha, un horario concreto y un numero de plazas, creado por un usuario(profesor) específico y sobre ellas los usuarios(cliente) podrán hacer sus reservas.

---

### Reserva

Aquí está el propósito final de la aplicacción, que los clientes puedan hacer reservas sobre sesiones concretas de disintas clases.

---

## Conclusion

La idea al principio era hacer un diseño relatívamente simple pero al plantearlo de cara a una empresa real, me vi obligado a ir añadiendo una cierta complejidad y capas extra en la DB que al principio no tuve en cuenta.
