
# 📂 Prueba Técnica – Mejoramiento GSNF

Este repositorio contiene la solución completa a la prueba técnica solicitada por la Gerencia de Servicio de Negocios Fiduciarios de Bancolombia. La prueba está dividida en 4 secciones, cada una dentro de su carpeta correspondiente, e incluye capturas de pantalla, código fuente y comentarios explicativos.

## 🗂 Estructura del Repositorio

```
|- Seccion 1 - Sql/
|- Seccion 2 - Java POO/
|- Seccion 3 - Algoritmos/
|- Seccion 4 - Javax Swing/
```

---

## 📌 Sección 1 - SQL

### 🧪 Ejercicio 1: Consultas con JOIN y Agregación

- Se creó una base de datos con las tablas `Clientes` y `Pedidos`.
![](/img/1.png)
- Se implementaron dos consultas:
  1. Para obtener el total gastado por cliente.
    ``` 
        SELECT 
            c.nombre,
            SUM(p.monto) as total_gastado
        FROM 
            Clientes c
            INNER JOIN Pedidos p ON c.id = p.cliente_id
        GROUP BY 
            c.nombre;
    ``` 
  2. Para mostrar solo aquellos que han gastado más de 1000.
- Se usó `INNER JOIN` para relacionar ambas tablas y `GROUP BY` para agrupar por cliente.
    ``` 
        SELECT 
            c.nombre,
            SUM(p.monto) as total_gastado
        FROM 
            Clientes c
            INNER JOIN Pedidos p ON c.id = p.cliente_id
        GROUP BY 
            c.nombre;
        HAVING 
            SUM(p.monto) > 1000;
    ``` 
- Se ejecutaron en un entorno SQL (MySQL shell for VS Code)

### 🛠 Ejercicio 2: Corrección de consulta errónea

Errores en la consulta:
- Falta la cláusula GROUP BY:

    Al usar una función de agregación (SUM) junto con una columna individual (nombre), es necesario usar GROUP BY

- La sintaxis de JOIN:

    Aunque técnicamente funciona, usar la coma para unir tablas (joins implícitos) es una práctica obsoleta.
    Es mejor usar la sintaxis explícita de JOIN para mayor claridad y mantenibilidad
- Falta un alias para la columna calculada:

    La columna SUM(monto) no tiene un nombre descriptivo.
    Esto puede causar problemas al referenciar la columna en otras partes del código

📁 Ver: `Seccion 1 - Sql/`

---

## 👨‍💻 Sección 2 - Java POO

### 🏗 Ejercicio 1: Modelado de Clase `Producto`

- Se creó la clase `Producto` con atributos: ID, nombre, precio, cantidad.
- Se implementaron:
  - Constructor completo.
  - Getters y setters.
  - Método para calcular el valor total del stock (`precio * cantidad`).
- Se probó el funcionamiento en la consola de VS Code.
![](/img/2.png)

### 🔍 Ejercicio 2: Análisis de código

Analizando el código se puede ver lo siguiente:
- En el constructor, la asginación de variables es incorrecta, ya que en ningún momento es los valores que entran al cosntructor son asignados a los atributos del objeto. Esto se soluciona incluyendo la palabra reservada ```this``` en el constructor

📁 Ver: `Seccion 2 - Java POO/`

---

## 📊 Sección 3 - Algoritmos

### 🧠 Ejercicio 1: Segundo número más grande

- Se creó un método en Java que recibe una lista de enteros y retorna el segundo mayor.
- Se probaron diferentes listas (ordenadas, con duplicados, etc.).
- Se validaron casos extremos y se documentaron con capturas.

### 🎫 Ejercicio 2: Reservas en el cine

- Se diseñó un método que analiza una matriz de asientos para verificar si hay al menos dos espacios contiguos libres en alguna fila.
- Se implementó usando iteraciones sobre arreglos 2D.
- Se probó con múltiples escenarios y se documentaron los resultados.

📁 Ver: `Seccion 3 - Algoritmos/`

---

## 🎨 Sección 4 - Javax/Swing

### 🖼 Ejercicio 1: Interfaz Gráfica

- Se diseñó una interfaz con Java Swing que incluye:
  - Logo de la empresa (imagen estática).
  - Formulario de ingreso de datos de productos.
  - Botón de guardado (en memoria).
- Se aplicó diseño visual limpio y funcional.

### 📁 Ejercicio 2: Generación de archivo Excel

- Al presionar el botón "Guardar", se genera un archivo `.xlsx` con los datos del formulario.
- Se usó la librería **Apache POI** para:
  - Crear el archivo.
  - Añadir protección con contraseña.
- Se probó la apertura del archivo y se documentó el bloqueo.

📁 Ver: `Seccion 4 - Javax Swing/`

---

## ✅ Entregable

- ✅ Código fuente completo con comentarios explicativos.
- ✅ Capturas de pantalla de cada paso.
- ✅ Solución lista para sustentar.

---

## 📝 Notas Finales

- Todas las soluciones fueron desarrolladas de forma práctica.
- El código es de fácil lectura y está comentado para facilitar la sustentación.
- Para compilar y ejecutar las secciones en Java, se recomienda usar un entorno como IntelliJ, NetBeans o Eclipse.
- Para ejecutar el código SQL, se puede usar MySQL, PostgreSQL o cualquier sistema relacional compatible.
