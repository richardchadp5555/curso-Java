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
### 🚻 Control de baños con semáforo

#### **Descripción del ejercicio**
El ejercicio simula un sistema en el que varias personas comparten el uso de tres baños. Para gestionar el acceso limitado, se utiliza un semáforo que controla cuántos hilos (personas) pueden usar los baños simultáneamente. El programa produce mensajes como:
- "Antonio está esperando para entrar al baño."
- "Laura va al baño."
- "Pepe ha salido del baño."

#### **Propósito del ejercicio**
El objetivo es:
- **Controlar recursos limitados (baños):** Garantizar que no más de tres personas puedan acceder al baño al mismo tiempo.
- **Practicar el uso de semáforos:** Implementar sincronización entre múltiples hilos.
- **Simular la concurrencia:** Mostrar cómo los hilos esperan, acceden y liberan un recurso compartido.

---

#### **Estructura del código**
| Componente        | Descripción                                                                |
|--------------------|----------------------------------------------------------------------------|
| `Principal`        | Clase principal que crea e inicia los hilos que representan a las personas.|
| `Persona`          | Clase que representa a cada hilo que entra al baño, controlando su acceso mediante un semáforo.|

---

#### **Ejemplo de salida**
```plaintext
Antonio está esperando para entrar al baño.
Laura está esperando para entrar al baño.
Pepe está esperando para entrar al baño.
Antonio va al baño.
Laura va al baño.
Pepe va al baño.
Antonio ha salido del baño.
Pedro está esperando para entrar al baño.
Pedro va al baño.

```
### 🏦 Banco sincronizado

#### **Descripción del ejercicio**
El ejercicio simula una situación en la que un banco dispone de **dos cajeros automáticos** que pueden ser usados por varios clientes de forma concurrente. Para gestionar el acceso limitado, se utiliza un semáforo que controla cuántos hilos (clientes) pueden usar los cajeros simultáneamente. Los clientes realizan una transacción, esperan un tiempo aleatorio y luego liberan el cajero.

#### **Propósito del ejercicio**
El objetivo es:
- **Controlar recursos limitados (cajeros):** Garantizar que no más de dos personas puedan usar los cajeros al mismo tiempo.
- **Practicar el uso de semáforos:** Sincronizar el acceso concurrente a recursos compartidos.
- **Simular concurrencia:** Mostrar cómo los hilos esperan, acceden y liberan un recurso compartido.

---

#### **Estructura del código**
| Componente | Descripción                                                                 |
|------------|-----------------------------------------------------------------------------|
| `Principal`| Clase principal que crea e inicia los hilos que representan a los clientes.|
| `Cliente`  | Clase que representa a cada cliente que usa un cajero, sincronizando su acceso mediante un semáforo.|

---

#### **Ejemplo de salida**
```plaintext
Cliente Antonio está esperando por un cajero.
Cliente Laura está esperando por un cajero.
Cliente Antonio está usando el cajero.
Cliente Laura está usando el cajero.
Cliente Antonio ha terminado su transacción y deja el cajero.
Cliente Pepe está esperando por un cajero.
Cliente Pepe está usando el cajero.
Cliente Laura ha terminado su transacción y deja el cajero.
Cliente Silvia está esperando por un cajero.
```
### 📚 Biblioteca sincronizada

#### **Descripción del ejercicio**
El ejercicio simula un sistema de biblioteca donde:
- **Empleados (productores):** Colocan libros en un estante compartido.
- **Clientes (consumidores):** Retiran libros del estante.

El **estante** tiene una capacidad limitada y los empleados no pueden colocar más libros si está lleno, mientras que los clientes deben esperar si el estante está vacío. La sincronización se logra mediante el uso de `wait()` y `notifyAll()`.

---

#### **Propósito del ejercicio**
Este ejercicio permite:
- **Aplicar el modelo productor-consumidor:** Coordinar a los empleados (productores) y clientes (consumidores) para trabajar sobre un buffer compartido.
- **Practicar el uso de `wait()` y `notifyAll()`:** Implementar la sincronización entre hilos.
- **Controlar el acceso a recursos compartidos:** Simular un sistema concurrente eficiente.

---

#### **Estructura del código**
| Componente    | Descripción                                                                 |
|---------------|-----------------------------------------------------------------------------|
| `Principal`   | Clase principal que inicializa los hilos de empleados y clientes.          |
| `Empleado`    | Clase que representa a un productor que coloca libros en el estante.       |
| `Cliente`     | Clase que representa a un consumidor que retira libros del estante.        |
| `Estante`     | Buffer compartido que almacena libros y gestiona la sincronización.        |

---

#### **Ejemplo de salida**
```plaintext
El empleado Antonio ha agregado el libro libro nº1 al estante
El cliente Richard ha retirado el libro libro nº1 del estante
El empleado Adolfo ha agregado el libro libro nº2 al estante
El cliente Belinda ha retirado el libro libro nº2 del estante
El empleado Antonio ha agregado el libro libro nº3 al estante
El cliente Richard ha retirado el libro libro nº3 del estante
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
