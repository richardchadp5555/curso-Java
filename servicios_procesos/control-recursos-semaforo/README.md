# Proyecto: Control de Recursos con Semáforos

Este proyecto es una práctica para aprender a gestionar el acceso concurrente a recursos limitados utilizando la clase `Semaphore` en Java. Simula un sistema de control para tres baños, donde varias personas (hilos) intentan acceder de manera sincronizada.

## Funcionalidades del Proyecto

### 1. **Simulación de acceso a los baños**
- Gestiona el acceso a un recurso limitado (3 baños) mediante semáforos.
- Controla que solo un máximo de 3 personas puedan usar los baños simultáneamente.
- Cada persona:
  - Espera su turno si no hay baños disponibles.
  - Usa el baño por un tiempo aleatorio entre 1 y 6 segundos.
  - Sale del baño, liberando el recurso para que otros puedan entrar.

### 2. **Control concurrente**
- Utiliza los métodos principales de la clase `Semaphore`:
  - `acquire()`: Para solicitar un permiso antes de entrar al baño.
  - `release()`: Para liberar un permiso al salir del baño.
- Proporciona mensajes descriptivos en la consola para seguir el flujo de acceso y liberación de los baños.

## Estructura del Proyecto
- **`Principal.java`**:
  - Clase principal que gestiona los semáforos y controla la creación de los hilos.
- **`Persona.java`**:
  - Clase que define el comportamiento de los hilos que intentan acceder a los recursos compartidos (baños).

### Clases Principales

#### 1. **`Principal`**
Es la clase principal que inicializa el programa. Sus responsabilidades incluyen:
- Crear y gestionar 10 hilos que simulan personas intentando acceder al baño.
- Utilizar un semáforo con 3 permisos (`Semaphore`) para controlar el acceso.
- Añadir pausas entre la creación de cada hilo para simular un flujo continuo de personas.

#### 2. **`Persona`**
Clase que representa a cada hilo/persona en la simulación. Implementa la interfaz `Runnable` para definir el comportamiento de cada hilo.

Métodos principales:
- **`run()`:**
  - Solicita un permiso para entrar al baño usando `Semaphore.acquire()`.
  - Simula el uso del baño con un tiempo aleatorio.
  - Libera el permiso al terminar, utilizando `Semaphore.release()`.

## Requisitos
Para ejecutar este proyecto, asegúrate de tener instalado lo siguiente:
- **Java SE 17 o superior.**
- **Eclipse IDE o cualquier editor compatible con Java.**

## Información del autor
- **Autor:** Richard Chadwick Plaza
- **Curso:** Segundo de DAM
- **Asignatura:** Programación de servicios y procesos
