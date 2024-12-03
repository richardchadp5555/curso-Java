
### 📜 Ejercicios: La cena de los filósofos

En este repositorio se incluyen los siguientes ejercicios sobre **La cena de los filósofos**:

- [**Modo Fácil:**](cena-filosofos-semaforo/src/filosofosFacil) Implementación usando un semáforo global.
- [**Modo Difícil:**](cena-filosofos-semaforo/src/filosofosDificil) Implementación con semáforos individuales para cada palillo.

| Modo         | Palillos                              | Sincronización       | Complejidad |
|--------------|---------------------------------------|----------------------|-------------|
| **Fácil**    | Todos los palillos son un recurso único. | Semáforo global.    | Baja        |
| **Difícil**  | Cada palillo es un recurso individual. | Semáforo por palillo.| Alta        |

### 🥢 Propósito del ejercicio
El ejercicio **La cena de los filósofos** nos permite explorar los problemas comunes de la programación concurrente, como la sincronización de recursos compartidos y la prevención de conflictos entre hilos. Este problema clásico, propuesto por Edsger Dijkstra, es una excelente forma de aplicar conceptos como:

- **Sincronización de hilos.**
- **Prevención de interbloqueos.**
- **Manejo de recursos limitados.**

### 🥢 Descripción del ejercicio
El problema de **La cena de los filósofos** es un clásico en la programación concurrente y se usa para estudiar la sincronización de hilos y la gestión de recursos compartidos. En este ejercicio, se simula a cinco filósofos que deben alternar entre **pensar** y **comer**, compartiendo cinco palillos colocados en una mesa circular.

Cada filósofo necesita tomar dos palillos (uno a su izquierda y otro a su derecha) para comer, pero como los palillos son recursos limitados, deben coordinarse para evitar conflictos.

---

### 🎮 Modos de resolución
1. **Modo Fácil:**
   - Todos los palillos están en el centro de la mesa y cualquier filósofo puede tomar dos palillos si están disponibles.
   - Sincronización mediante un semáforo que controla el acceso al total de palillos.

2. **Modo Difícil:**
   - Cada filósofo solo puede tomar los palillos directamente a su izquierda y derecha.
   - Se deben evitar problemas como:
     - **Interbloqueo (Deadlock):** Cuando todos los filósofos toman un palillo y quedan esperando indefinidamente por el segundo.
     - **Condiciones de carrera (Race Condition):** Cuando dos filósofos intentan tomar el mismo palillo simultáneamente.

---

### 🎯 Objetivo
El objetivo del ejercicio es aplicar conceptos de programación concurrente para garantizar que:
- Los filósofos puedan alternar entre pensar y comer sin bloquearse.
- Se usen mecanismos de sincronización como **semáforos** o **bloques sincronizados** para evitar conflictos.
- Se minimicen problemas como el interbloqueo y la inanición (un filósofo que nunca puede comer).

---

## 📝 Autor y Curso

- **Autor:** Richard Chadwick Plaza  
- **Curso:** Segundo de DAM  
- **Asignaturas:**
  - Programación de Servicios y Procesos  

---
