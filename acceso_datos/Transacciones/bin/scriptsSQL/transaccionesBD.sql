-- Creamos la base de datos
CREATE database transaccionesBD;

-- Nos aseguramos que utilizamos esta base de datos
USE transaccionesBD;

-- Tabla articulos
CREATE TABLE articulos(
	codart INT AUTO_INCREMENT PRIMARY KEY,
    descripcion VARCHAR(100),
    existencias INT,
    precio DECIMAL(10, 2)
);

-- Tabla clientes, esta tabla no la pedía el enunciado, pero creo que el programa queda más sólido si la agregamos
CREATE TABLE clientes(
	codcli INT AUTO_INCREMENT PRIMARY KEY, 
    nombre VARCHAR(100),
    direccion VARCHAR(100),
    telefono VARCHAR(15)
);


-- Tabla de pedidos
CREATE TABLE pedidos(
	codped INT AUTO_INCREMENT PRIMARY KEY,
    fecha DATE,
    codcli INT,
    direccion VARCHAR(100),
    codart INT,
    FOREIGN KEY(codart) REFERENCES articulos(codart),
    FOREIGN KEY(codcli) REFERENCES clientes(codcli)
);

-- Tabla de riders
CREATE TABLE riders(
	codrider INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100),
    disponibilidad INTEGER CHECK (disponibilidad IN (1, 0))		-- 1 representa que el rider está disponible y 0 que no lo esta
);

-- Tabla de Envios
CREATE TABLE envios(
	codped INT,
    codrider INT, 
    terminado DATETIME,
    PRIMARY KEY (codped, codrider),
    FOREIGN KEY (codped) REFERENCES pedidos(codped),
    FOREIGN KEY (codrider) REFERENCES riders(codrider)
);

-- Datos para la tabla articulos
INSERT INTO articulos (descripcion, existencias, precio)
VALUES 
('Laptop', 50, 800.00),
('Mouse', 200, 20.00),
('Teclado', 150, 50.00);

-- Datos para la tabla riders
INSERT INTO riders (nombre, disponibilidad)
VALUES 
('Richard Chadwick', 1),
('Hugo Jiménez', 1),
('Eleazar Ballesteros', 1);

-- Datos iniciales de clientes
INSERT INTO clientes(nombre, direccion, telefono) VALUES
('Alberto', 'calle de alberto', '123456789'),
('Laura', 'calle de Laura', '098765432'),
('Jose', 'calle de Jose', '123443212');

