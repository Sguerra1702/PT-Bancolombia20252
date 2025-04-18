CREATE TABLE Clientes ( 
    id INT PRIMARY KEY, 
    nombre VARCHAR(100), 
    email VARCHAR(100), 
    fecha_registro DATE
);

CREATE TABLE Pedidos ( 
    id INT PRIMARY KEY, 
    cliente_id INT, 
    monto DECIMAL(10,2), 
    fecha_pedido DATE,
    FOREIGN KEY (cliente_id) REFERENCES Clientes(id) 
);


--Consultas--

--1. Obtener el nombre de cada cliente que haya realizado un pedido en el último mes y el monto total de sus pedidos.

SELECT 
    c.nombre,
    SUM(p.monto) as total_gastado
FROM 
    Clientes c
    INNER JOIN Pedidos p ON c.id = p.cliente_id
GROUP BY 
    c.nombre;


-- 2. Añadir una condición para filtrar y mostrar solo los clientes que hayan gastado más de 1000 en total.

SELECT 
    c.nombre,
    SUM(p.monto) as total_gastado
FROM 
    Clientes c
    INNER JOIN Pedidos p ON c.id = p.cliente_id
GROUP BY 
    c.nombre
HAVING 
    SUM(p.monto) > 1000;

-- Consulta corregida

SELECT 
    c.nombre,
    SUM(p.monto) as total_gastado
FROM 
    Clientes c
    INNER JOIN Pedidos p ON c.id = p.cliente_id
GROUP BY 
    c.nombre;