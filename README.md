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

- **`servicios_procesos/`**: Ejercicios y ejemplos relacionados con la asignatura de Programación de Servicios y Procesos.
  - **`control_recursos_semaforos/`**: Proyecto para gestionar el acceso concurrente a recursos limitados utilizando semáforos en Java.
    - **Descripción**: Este proyecto simula un sistema donde varias personas (hilos) intentan acceder a tres baños. Se utiliza la clase `Semaphore` para sincronizar el acceso, garantizando que solo tres hilos puedan usar los recursos simultáneamente.
    - **Funcionalidades principales**:
      - Simulación del uso de los baños con tiempos aleatorios de 1 a 6 segundos.
      - Control del acceso concurrente mediante los métodos `acquire()` y `release()` de la clase `Semaphore`.
      - Mensajes descriptivos en la consola para seguir el flujo de la simulación.
    - **Tecnologías utilizadas**: Java y concurrencia con `Semaphore`.

## Requisitos

Para ejecutar los ejercicios de este repositorio, se recomienda:

- **Java SE 17 o superior**
- **Eclipse IDE o cualquier editor compatible con Java**
- **MySQL Server** y **MySQL Workbench** (para los ejercicios relacionados con bases de datos)
- **Conector JDBC para MySQL** (incluido en los proyectos donde es necesario)

---

**Autor:** Richard Chadwick Plaza  
**Curso:** Segundo de DAM  
**Asignaturas:** Acceso a Datos, Programación de Servicios y Procesos  

