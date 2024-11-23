# Gestión de Usuarios y Pedidos

Proyecto desarrollado como parte de la asignatura **Acceso a Datos**, enfocado en el uso de bases de datos MySQL y Java para implementar procedimientos almacenados, transacciones y exportación de datos.

## 📄 Enunciado del Ejercicio

El programa debe realizar las siguientes tareas:

1. **Archivo de Configuración**:
   - Leer un archivo `config.properties` para obtener los parámetros de conexión a la base de datos (URL, usuario y contraseña).

2. **Inserción de Usuarios**:
   - Implementar un procedimiento almacenado en MySQL llamado `InsertUser` que:
     - Reciba como parámetros el nombre de usuario, contraseña y nombre completo.
     - Almacene la contraseña encriptada usando **SHA2**.
   - Desde Java, llamar al procedimiento para insertar al menos **tres usuarios**.

3. **Gestión de Pedidos**:
   - Diseñar las tablas necesarias para gestionar **pedidos** y **productos**, considerando las siguientes relaciones:
     - Cada pedido debe estar asociado a un usuario (clave foránea).
     - Los productos tienen un stock que debe actualizarse al realizar un pedido.
   - Implementar una **transacción** en Java que:
     - Inserte un nuevo pedido.
     - Reste la cantidad pedida del stock del producto correspondiente.
     - Si ocurre un error (por ejemplo, stock insuficiente), revertir toda la operación.

4. **Consulta y Exportación**:
   - Permitir al usuario introducir el nombre de un usuario (login).
   - Consultar todos los pedidos asociados a ese usuario y mostrarlos por pantalla.
   - Exportar el resultado de la consulta a un archivo **CSV** con el formato: `pedido_id, producto, cantidad, fecha`.

5. **Extras (opcional)**:
   - Implementar un sistema de **concurrencia** con dos hilos que intenten realizar pedidos al mismo tiempo sobre el mismo producto. Verificar cómo se comporta el programa.

## 🛠️ Tecnologías Utilizadas

- **Java**: Lenguaje principal para implementar la lógica del programa.
- **MySQL**: Base de datos relacional para gestionar los datos de usuarios, pedidos y productos.
- **JDBC**: Librería para conectar Java con MySQL.
- **Archivo .properties**: Para gestionar configuraciones de conexión a la base de datos.
- **CSV**: Exportación de datos a archivos en formato delimitado por comas.
- **GitHub**: Plataforma para la gestión del proyecto.

## 📂 Estructura del Proyecto

- `src/` : Código fuente del proyecto.
- `resources/config.properties` : Archivo de configuración con los parámetros de conexión.
- `sql/` : Archivo `.sql` con la creación de tablas y procedimientos almacenados.
- `output/` : Carpeta donde se generarán los archivos CSV exportados.
- `README.md` : Archivo de documentación del proyecto.

## 🚀 Ejecución del Proyecto

1. **Configuración Inicial**:
   - Crear las tablas en la base de datos ejecutando el archivo SQL proporcionado en la carpeta `sql/`.
   - Configurar los datos de conexión en el archivo `config.properties` dentro de la carpeta `resources/`.

2. **Ejecución**:
   - Compilar y ejecutar el proyecto en un entorno Java como **Eclipse** o **IntelliJ IDEA**.
   - Asegurarse de que la base de datos esté activa y accesible.

3. **Resultados**:
   - Insertar usuarios usando el procedimiento almacenado.
   - Registrar pedidos en la base de datos y exportar los resultados en un archivo CSV.
   - Simular concurrencia en pedidos (opcional).

## 🧩 Clases y Nociones Necesarias

### **Clases Java**
1. **Gestión de la conexión a la base de datos:**
   - `DriverManager`: Para establecer la conexión con MySQL.
   - `Connection`: Para administrar conexiones y manejar transacciones (`setAutoCommit`, `commit`, `rollback`).
   - `PreparedStatement`: Para ejecutar consultas parametrizadas de forma segura.
   - `CallableStatement`: Para ejecutar procedimientos almacenados desde Java.
   - `ResultSet`: Para procesar los datos obtenidos de una consulta SQL.

2. **Gestión de archivos:**
   - `FileInputStream` y `Properties`: Para leer el archivo `config.properties` con las credenciales de conexión.
   - `PrintWriter`: Para escribir datos en archivos CSV.

3. **Control de excepciones:**
   - `SQLException`: Para manejar errores relacionados con la base de datos.
   - `IOException`: Para manejar problemas en la lectura/escritura de archivos.

4. **Manejo de concurrencia (opcional):**
   - `Thread`: Para simular múltiples usuarios accediendo a la base de datos simultáneamente.
   - `Runnable`: Para definir la lógica de cada hilo.

### **Nociones Clave**
1. **MySQL y SQL:**
   - Creación y uso de procedimientos almacenados.
   - Gestión de transacciones:
     - Comandos SQL como `COMMIT` y `ROLLBACK`.
   - Encriptación de contraseñas con `SHA2` u otras funciones de MySQL.
   - Diseño de relaciones entre tablas (claves primarias y foráneas).

2. **Exportación de datos:**
   - Generación de archivos CSV con datos extraídos de la base de datos.

3. **Configuración:**
   - Uso de un archivo `.properties` para manejar configuraciones externas, como:
     - URL de la base de datos.
     - Credenciales de usuario y contraseña.
     - Parámetros adicionales.

4. **Concurrencia (opcional):**
   - Problemas derivados del acceso simultáneo a la base de datos.
   - Soluciones mediante transacciones y gestión de bloqueos en MySQL.

## 📚 Autor

- **Richard Chadwick Plaza**

## 🏫 Asignatura

- **Acceso a Datos**

---

### 🌟 Notas Adicionales

- Este proyecto es un ejemplo práctico para integrar el uso de bases de datos y programación en Java.
- Cualquier contribución o sugerencia será bienvenida.
