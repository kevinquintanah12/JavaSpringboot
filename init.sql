-- Crear usuario
CREATE USER myuser;
ALTER USER myuser WITH PASSWORD 'postgres';

-- Crear base de datos
CREATE DATABASE mydb;
GRANT ALL PRIVILEGES ON DATABASE mydb TO myuser;

-- Conectar a la base de datos
\connect mydb;

-- Crear secuencias para las claves primarias
CREATE SEQUENCE clientes_id_seq START 1;
CREATE SEQUENCE productos_id_seq START 1;
CREATE SEQUENCE venta_id_seq START 1;
CREATE SEQUENCE ventadetalle_id_seq START 1;

-- Crear tabla Clientes
CREATE TABLE clientes (
    id BIGINT PRIMARY KEY DEFAULT nextval('clientes_id_seq'),
    nombre VARCHAR(255) NOT NULL
);

-- Crear tabla Productos
CREATE TABLE productos (
    id BIGINT PRIMARY KEY DEFAULT nextval('productos_id_seq'),
    nombre VARCHAR(255) NOT NULL,
    precio DECIMAL(12,6) NOT NULL
);

-- Crear tabla Ventas
CREATE TABLE venta (
    id BIGINT PRIMARY KEY DEFAULT nextval('venta_id_seq'),
    fecha TIMESTAMP NOT NULL,
    monto DECIMAL(12,6) NOT NULL,
    idCliente BIGINT NOT NULL,
    FOREIGN KEY (idCliente) REFERENCES clientes(id)
);

-- Crear tabla DetalleVenta
CREATE TABLE ventaDetalle (
    id BIGINT PRIMARY KEY DEFAULT nextval('ventadetalle_id_seq'),
    idProducto BIGINT NOT NULL,
    precio DECIMAL(12,6) NOT NULL,
    descripcion TEXT,
    cantidad INT NOT NULL,
    idVenta BIGINT NOT NULL,
    FOREIGN KEY (idProducto) REFERENCES productos(id),
    FOREIGN KEY (idVenta) REFERENCES venta(id)
);
