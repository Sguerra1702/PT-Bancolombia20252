
# Prueba Técnica – Mejoramiento GSNF

Este repositorio contiene la solución completa a la prueba técnica solicitada por la Gerencia de Servicio de Negocios Fiduciarios de Bancolombia. La prueba está dividida en 4 secciones, cada una dentro de su carpeta correspondiente, e incluye capturas de pantalla, código fuente y comentarios explicativos.

## Estructura del Repositorio

```
|- Seccion 1 - Sql/
|- Seccion 2 - Java POO/
|- Seccion 3 - Algoritmos/
|- Seccion 4 - Javax Swing/
```

---

## Sección 1 - SQL

### Ejercicio 1: Consultas con JOIN y Agregación

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

### Ejercicio 2: Corrección de consulta errónea

Errores en la consulta:
- Falta la cláusula GROUP BY:

    Al usar una función de agregación (SUM) junto con una columna individual (nombre), es necesario usar GROUP BY

- La sintaxis de JOIN:

    Aunque técnicamente funciona, usar la coma para unir tablas (joins implícitos) es una práctica obsoleta.
    Es mejor usar la sintaxis explícita de JOIN para mayor claridad y mantenibilidad
- Falta un alias para la columna calculada:

    La columna SUM(monto) no tiene un nombre descriptivo.
    Esto puede causar problemas al referenciar la columna en otras partes del código

Consulta corregida:
![](/img/2.png)
Ver: `Seccion 1 - Sql/`

---

## Sección 2 - Java POO

### Ejercicio 1: Modelado de Clase `Producto`

- Se creó la clase `Producto` con atributos: ID, nombre, precio, cantidad.
- Se implementaron:
  - Constructor completo.
  - Getters y setters.
  - Método para calcular el valor total del stock (`precio * cantidad`).
- Se probó el funcionamiento en la consola de VS Code.
![](/img/3.png)

### Ejercicio 2: Análisis de código

Analizando el código se puede ver lo siguiente:
- En el constructor, la asginación de variables es incorrecta, ya que en ningún momento es los valores que entran al cosntructor son asignados a los atributos del objeto. Esto se soluciona incluyendo la palabra reservada ```this``` en el constructor.<br>
Para poder probar las correciones, se añadió el método main para poder ejecutar la clase, obteniendo lo siguiente:
![](/img/4.png)

Ver: `Seccion 2 - Java POO/`

## Sección 3 - Algoritmos

### Ejercicio 1: Segundo número más grande
En la clase SegundoMayor se encuentra la solucion al ejercicio -  Se recorrió la lista, separando el mayor primero y luego, encontrando el segundo mayor
![](/img/5.png)


### Ejercicio 2: Reservas en el cine
Para este ejercicio, se recorre la matriz buscando ceros que esten en las posiciones inmediatamente siguientes y así determinar si la reserva se puede hacer
![](/img/6.png)


Ver: `Seccion 3 - Algoritmos/`

## Sección 4 - Javax/Swing

### Ejercicio 1: Interfaz Gráfica
Teniendo en cuenta que este proyecto más adelante usará Apache POI para generar los archivos de excel, se decidió usar maven para la arquitectura del proyecto, una vez generado el scaffolding, se dividió el programa en paquetes: Dominio, el cual aloja la lógica del programa (Clase Producto). Persistencia, que guarda en memoria (una lista) los productos registrados y Presentación que se encarga de la interfaz gráfica del software. Aquí una captura de la aplicación funcionando:
![](/img/7.png)

Luego, al ingresar y guardar un producto:
![](/img/8.png)


### Ejercicio 2: Generación de archivo Excel
Utilizando la dependencia de Apache POI, se generó un archivo excel protegido con contraseña que muestra la lista de productos con un timestamp indicando el momento que fue añadido:
![](/img/9.png)

![](/img/10.png)


Ver: `Seccion 4 - Javax Swing/`



##  Notas Finales
- Para verificar las consultas, se recomienda instalar la extensión MySQL Shell for VS Code
- Los archivos de java Se pueden ejecutar con la extensión de java para VS Code
- El proyecto de la sección 4 - UI con java Swing se recomienda ejecutar con un IDE como intellij para la carga de los repositorios de maven central.

