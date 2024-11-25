# Gestión de Usuarios y Pedidos

Proyecto desarrollado como parte de la asignatura **Acceso a Datos**, enfocado en el uso de bases de datos MySQL y Java para implementar procedimientos almacenados, transacciones, importación/exportación de datos en formato CSV y gestión de errores.

---

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

4. **Importación desde CSV**:
   - Permitir importar datos de usuarios, productos y pedidos desde archivos **CSV**.
   - Validar los datos antes de insertarlos en la base de datos.

5. **Consulta y Exportación**:
   - Exportar los productos almacenados en la base de datos a un archivo **CSV**.
   - El archivo incluirá las columnas `idproducto, nombre, stock, precio`.

6. **Extras (opcional)**:
   - Implementar un sistema de **concurrencia** con dos hilos que intenten realizar pedidos al mismo tiempo sobre el mismo producto. Verificar cómo se comporta el programa.

---

## 🛠️ Tecnologías Utilizadas

- **Java**: Lenguaje principal para implementar la lógica del programa.
- **MySQL**: Base de datos relacional para gestionar los datos de usuarios, pedidos y productos.
- **JDBC**: Librería para conectar Java con MySQL.
- **Archivo .properties**: Para gestionar configuraciones de conexión a la base de datos.
- **CSV**: Importación y exportación de datos en formato delimitado por comas.
- **GitHub**: Plataforma para la gestión del proyecto.

---

## 📂 Estructura del Proyecto

```plaintext
.
├── src/
│   └── gestion/
│       ├── ConexionBD.java           # Gestión de conexión a la base de datos.
│       ├── ExportarProductos.java    # Exportación de datos a CSV.
│       ├── ImportarCSV.java          # Importación de datos desde CSV.
│       ├── PedidoDAO.java            # Gestión de pedidos con transacciones.
│       ├── UsuarioDAO.java           # Gestión de usuarios (DAO).
│       └── Principal.java            # Clase principal para ejecución del programa.
├── sql/
│   └── pedidos-productos-usuarios-DB.sql  # Script para crear la base de datos.
├── config.properties                 # Configuración para la conexión.
├── productos.csv                     # Datos iniciales de productos.
├── usuarios.csv                      # Datos iniciales de usuarios.
├── pedidos.csv                       # Datos iniciales de pedidos.
├── productos_exportados.csv          # Archivo generado al exportar productos.
```

## 🚀 Ejecución del Proyecto

### 1. **Configuración Inicial**:
   - Crear las tablas en la base de datos ejecutando el archivo SQL proporcionado en la carpeta `sql/`.
   - Configurar los datos de conexión en el archivo `config.properties` en la raíz del proyecto:

     ```properties
     db.url=jdbc:mysql://localhost:3306/gestion_usuarios_pedidos
     db.user=tu_usuario
     db.password=tu_contraseña
     ```

### 2. **Ejecución**:
   - Compilar y ejecutar el proyecto en un entorno Java como **Eclipse** o **IntelliJ IDEA**.
   - Asegurarse de que la base de datos esté activa y accesible.

### 3. **Resultados Esperados**:
   - **Inserción de Usuarios Manuales**:
     - En la consola, se mostrarán mensajes indicando que los usuarios se han insertado correctamente, o posibles errores si ya existen.

   - **Registro de Pedidos**:
     - El programa insertará nuevos pedidos y actualizará el stock en la base de datos.
     - En caso de errores (como stock insuficiente), la transacción se revertirá.

   - **Importación desde CSV**:
     - Los datos de los archivos `usuarios.csv`, `productos.csv` y `pedidos.csv` se leerán y se insertarán en las tablas correspondientes.
     - Se validará la estructura de los archivos y los datos para evitar duplicados o errores.

   - **Exportación a CSV**:
     - La clase `ExportarProductos` generará un archivo `productos_exportados.csv` con los datos actuales de la tabla `productos`.

---

### 🌟 Notas Adicionales

- **Errores Comunes**:
  - Verificar que la base de datos esté activa y configurada correctamente.
  - Asegurarse de que los archivos CSV tengan el formato adecuado y no contengan datos duplicados o inválidos.

- **Mejoras Futuras**:
  - Implementar un sistema de concurrencia con hilos para pedidos simultáneos.
  - Agregar más validaciones a nivel de aplicación y base de datos.

- **Contacto**:
  - Autor: Richard Chadwick Plaza