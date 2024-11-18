# Proyecto: Transacciones

Este proyecto es una práctica para la gestión de transacciones en una base de datos MySQL utilizando Java y JDBC. Simula un sistema de gestión de pedidos, en el que se pueden realizar operaciones como insertar pedidos, registrar envíos, gestionar riders disponibles y confirmar o revertir transacciones.

## Funcionalidades del Proyecto

### 1. **Insertar Pedidos**
- Permite registrar un nuevo pedido en el sistema.
- Se solicitan los siguientes datos:
  - Nombre del cliente.
  - Dirección del pedido.
  - Nombre del artículo.
- Realiza las siguientes acciones:
  - Obtiene el código del cliente y del artículo desde la base de datos.
  - Inserta el pedido en la tabla `pedidos`.
  - Actualiza las existencias del artículo (`existencias - 1`).

### 2. **Actualizar Existencias**
- Permite actualizar manualmente las existencias de un artículo en la base de datos.
- Solicita el nombre del artículo y el nuevo valor de existencias.

### 3. **Registrar Envío**
- Asocia un rider disponible al último pedido registrado en la transacción actual.
- Acciones realizadas:
  - Verifica que haya un pedido pendiente de envío.
  - Busca un rider disponible en la base de datos.
  - Inserta el envío en la tabla `envios`.
  - Cambia el estado del rider a "no disponible".

### 4. **Confirmar Transacción (COMMIT)**
- Confirma todas las operaciones realizadas en la transacción actual.
- Si hay un envío registrado, inicia un hilo (`NotificarEntrega`) para simular el tiempo de entrega.
- El hilo realiza las siguientes acciones:
  - Espera un tiempo aleatorio (simula la entrega).
  - Actualiza la tabla `pedidos` con la fecha y hora de entrega.
  - Cambia el estado del rider a "disponible".

### 5. **Cancelar Transacción (ROLLBACK)**
- Revierte todas las operaciones realizadas en la transacción actual si ocurre algún error.

## Estructura del Proyecto

### Clases Principales

#### 1. **`GestionTransaccion`**
Es la clase principal que gestiona el flujo del programa. Incluye las siguientes responsabilidades:
- Menú principal con opciones para realizar las operaciones.
- Manejo de transacciones (`COMMIT` y `ROLLBACK`).
- Métodos principales:
  - `insertarPedido`: Registra un nuevo pedido.
  - `actualizarExistencias`: Modifica manualmente las existencias de un artículo.
  - `registrarEnvio`: Asocia un rider disponible al pedido actual.
- Gestión de las variables globales:
  - `ultimoCodPedido`: Almacena el código del último pedido registrado.
  - `ultimoCodRider`: Almacena el código del último rider asignado.

#### 2. **`NotificarEntrega`**
Es un hilo que simula el proceso de entrega de un pedido. Realiza las siguientes tareas:
- Espera un tiempo aleatorio entre 2 y 7 segundos.
- Actualiza la tabla `pedidos` con la fecha y hora de entrega.
- Cambia el estado del rider a "disponible".

### Tablas Utilizadas en la Base de Datos

#### 1. **`pedidos`**
| Campo       | Tipo       | Descripción                            |
|-------------|------------|----------------------------------------|
| `codped`    | INT        | Código del pedido (autoincremental).   |
| `fecha`     | DATETIME   | Fecha y hora del pedido/entrega.       |
| `codcli`    | INT        | Código del cliente.                   |
| `direccion` | VARCHAR    | Dirección del pedido.                 |
| `codart`    | INT        | Código del artículo.                  |

#### 2. **`articulos`**
| Campo         | Tipo       | Descripción                            |
|---------------|------------|----------------------------------------|
| `codart`      | INT        | Código del artículo (autoincremental).|
| `descripcion` | VARCHAR    | Nombre del artículo.                  |
| `existencias` | INT        | Cantidad disponible del artículo.     |

#### 3. **`clientes`**
| Campo      | Tipo       | Descripción                           |
|------------|------------|---------------------------------------|
| `codcli`   | INT        | Código del cliente (autoincremental).|
| `nombre`   | VARCHAR    | Nombre del cliente.                  |

#### 4. **`riders`**
| Campo          | Tipo       | Descripción                            |
|----------------|------------|----------------------------------------|
| `codrider`     | INT        | Código del rider (autoincremental).    |
| `disponibilidad`| BOOLEAN    | `1` si está disponible, `0` si no.    |

#### 5. **`envios`**
| Campo        | Tipo       | Descripción                            |
|--------------|------------|----------------------------------------|
| `codenvio`   | INT        | Código del envío (autoincremental).    |
| `codped`     | INT        | Código del pedido asociado.            |
| `codrider`   | INT        | Código del rider asignado.             |
| `terminado`  | BOOLEAN    | `NULL` durante el envío, `1` cuando termina. |

## Requisitos

Para ejecutar este proyecto, asegúrate de tener instalado lo siguiente:
- **Java SE 17 o superior.**
- **Eclipse IDE o cualquier editor compatible con Java.**
- **MySQL Server** y **MySQL Workbench.**
- **Conector JDBC para MySQL.**

# Información del autor
- **Autor:** Richard Chadwick Plaza
- **Curso:¨** Segundo de DAM
- **Asignatura:** Acceso a Datos
