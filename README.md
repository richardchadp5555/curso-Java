# Curso Java (Español)

Este repositorio contiene ejercicios, ejemplos y proyectos realizados en el desarrollo de dos asignaturas del segundo curso de DAM, estas son "Acceso a datos" y "Programación de servicios y procesos".

## Estructura del Repositorio

- **`acceso_datos/`**: Ejercicios y ejemplos relacionados con la asignatura de Acceso a Datos.
  - **`conexionJDBC/`**: Ejercicio para practicar la conexión a una base de datos MySQL usando JDBC. Incluye:
    - **`src/`**: Código fuente de la clase `ConexionJDBC.java`.
    - **`sql_scripts/`**: Scripts SQL utilizados para configurar las bases de datos.
  - **`transacciones/`**: Proyecto que implementa la gestión de transacciones en una base de datos MySQL. 
    - **Descripción**: Este ejercicio simula la gestión de pedidos en un sistema de base de datos, permitiendo realizar operaciones como:
      - Insertar pedidos.
      - Registrar envíos con riders disponibles.
      - Confirmar transacciones (`COMMIT`).
      - Revertir cambios en caso de errores (`ROLLBACK`).
      - Incluye la implementación de un hilo (`NotificarEntrega`) para simular el tiempo de entrega de un pedido y la actualización automática de la base de datos.
    - **Tecnologías utilizadas**: Java, MySQL, JDBC.

*(En el futuro se incluirán ejercicios y ejemplos para otras asignaturas como Programación de Procesos y Servicios.)*

## Requisitos

Para ejecutar los ejercicios de este repositorio, se recomienda:

- **Java SE 17 o superior**
- **Eclipse IDE o cualquier editor compatible con Java**
- **MySQL Server** y **MySQL Workbench**
- **Conector JDBC para MySQL** (incluido en los proyectos donde es necesario)

---

**Autor:** Richard Chadwick Plaza  
**Curso:** Segundo de DAM  
**Asignaturas:** Acceso a Datos, Programación de Servicios y Procesos  

*(Este README será actualizado conforme se agreguen más ejercicios y asignaturas al repositorio.)*
