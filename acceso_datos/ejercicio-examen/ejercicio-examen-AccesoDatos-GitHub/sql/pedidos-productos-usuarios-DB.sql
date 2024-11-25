-- Crear la base de datos
CREATE DATABASE gestion_usuarios_pedidos;
USE gestion_usuarios_pedidos;

-- Creación de las tablas 
CREATE TABLE usuarios (
	coduser INT AUTO_INCREMENT PRIMARY KEY,
    nombrelogin VARCHAR(20) UNIQUE NOT NULL,
    contrasena VARCHAR(64) NOT NULL,
    nombrecompleto VARCHAR(40) NOT NULL
);

CREATE TABLE productos (
	idproducto INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(40) NOT NULL,
    stock INT NOT NULL,
    precio DECIMAL(10, 2) NOT NULL
);

CREATE TABLE pedidos (
	idpedido INT AUTO_INCREMENT PRIMARY KEY,
    coduser INT NOT NULL,
    idproducto INT NOT NULL,
    cantidad INT NOT NULL,
    fecha TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (coduser) REFERENCES usuarios(coduser) ON DELETE CASCADE,
    FOREIGN KEY (idproducto) REFERENCES productos (idproducto) ON DELETE CASCADE
);

-- Insertar datos de prueba
INSERT INTO productos (nombre, stock, precio) VALUES
('Producto A', 50, 10.00),
('Producto B', 30, 15.50),
('Producto C', 20, 7.75);

-- Procedimiento almacenado InsertUser, se encarga de insertar usuarios en la tabla encriptando las contraseñas con SHA2
DROP PROCEDURE IF EXISTS insertUser;
DELIMITER $$
CREATE PROCEDURE insertUser(
	IN login VARCHAR(20),
    IN passwd VARCHAR(64),
    IN fullname VARCHAR(40)
)
BEGIN 
	INSERT INTO usuarios (nombrelogin, contrasena, nombrecompleto)
    VALUES (login, SHA2(passwd, 256), fullname);
END$$
DELIMITER ;

-- Verifico que se ha creado el procedimiento
SHOW PROCEDURE STATUS WHERE Db = 'gestion_usuarios_pedidos';
