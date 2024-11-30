# 📖 Programación de Servicios y Procesos

Este repositorio contiene ejercicios y recursos relacionados con la asignatura de **Programación de Servicios y Procesos**, una disciplina clave para entender la concurrencia, la comunicación entre procesos y la gestión de recursos compartidos.  

A través de ejemplos prácticos en **Java**, exploraremos los conceptos fundamentales de la programación concurrente, hilos y procesos. 

---

## 📂 Introducción

En este repositorio abordaremos la **programación concurrente**, centrándonos en:
- La creación y gestión de hilos.
- La sincronización de procesos concurrentes.
- La resolución de problemas clásicos como el **interbloqueo** y las **condiciones de carrera**.

La programación concurrente es esencial para optimizar aplicaciones modernas y garantizar un uso eficiente de los recursos, especialmente en sistemas distribuidos o de múltiples núcleos.

---

## 🧵 Tipos de incongruencias entre hilos

En la programación concurrente, es común enfrentarse a problemas de sincronización entre hilos que comparten recursos. A continuación, presentamos los dos principales tipos de incongruencias:

### 1. Interbloqueo (Deadlock) 🔄
Un interbloqueo ocurre cuando dos o más hilos quedan bloqueados indefinidamente porque están esperando recursos que otros hilos poseen.

- **Ejemplo:**  
  En la cena de los filósofos, ocurre cuando todos los filósofos toman un palillo y quedan esperando el segundo, causando un "atasco eterno".

- **Soluciones:**
  - Cambiar el orden en que los recursos son tomados.
  - Introducir un árbitro que gestione el acceso a los recursos.

### 2. Condición de carrera (Race Condition) ⚡
Una condición de carrera ocurre cuando varios hilos acceden y modifican un recurso compartido al mismo tiempo, causando resultados inconsistentes.

- **Ejemplo:**  
  En la cena de los filósofos, ocurre si dos filósofos intentan tomar el mismo palillo al mismo tiempo sin un mecanismo de sincronización.

- **Soluciones:**
  - Usar semáforos o bloques `synchronized` para evitar accesos simultáneos.

---

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

### 🍽️ Sistema de Pedidos en Restaurante

#### **Descripción del ejercicio**
El ejercicio simula un sistema concurrente en un restaurante, donde:
- **Camareros (Productores):** Generan pedidos y los colocan en un buffer compartido.
- **Cocineros (Consumidores):** Retiran pedidos del buffer y los procesan.

El buffer tiene una capacidad limitada, y los hilos trabajan de manera sincronizada para evitar problemas de concurrencia como:
- **Condiciones de carrera.**
- **Interbloqueos.**

---

#### **Propósito del ejercicio**
Este ejercicio permite:
- Comprender el patrón **productor-consumidor**.
- Implementar sincronización de hilos con `wait()` y `notifyAll()`.
- Simular sistemas de trabajo concurrentes.

---

#### **Estructura del código**
| Componente        | Descripción                                                                 |
|--------------------|-----------------------------------------------------------------------------|
| `BufferPedidos`    | Clase que implementa un buffer sincronizado para almacenar pedidos.        |
| `Camarero`         | Clase que representa un hilo productor, encargado de generar pedidos.      |
| `Cocinero`         | Clase que representa un hilo consumidor, encargado de procesar pedidos.    |
| `Principal`        | Clase principal que inicializa y ejecuta los hilos de camareros y cocineros.|

---

#### **Ejemplo de salida**
```plaintext
Camarero 1 tomó el pedido: Pizza #1
Pedido agregado: Pizza #1
Cocinero 1 está preparando el pedido: Pizza #1
Camarero 2 tomó el pedido: Sopa #2
Pedido agregado: Sopa #2
Cocinero 1 terminó el pedido: Pizza #1
```

## 🛠️ Herramientas y tecnologías utilizadas

- **Lenguaje:** Java  
- **Entorno de desarrollo:** Eclipse IDE  
- **Conceptos aplicados:**
  - Programación concurrente.
  - Sincronización de hilos con semáforos.
  - Prevención de interbloqueos.

---

## 📝 Autor y Curso

- **Autor:** Richard Chadwick Plaza  
- **Curso:** Segundo de DAM  
- **Asignaturas:**
  - Programación de Servicios y Procesos  

---

**¡Gracias por visitar este repositorio! Si tienes sugerencias o quieres colaborar, no dudes en abrir un _pull request_ o contactarme.**
