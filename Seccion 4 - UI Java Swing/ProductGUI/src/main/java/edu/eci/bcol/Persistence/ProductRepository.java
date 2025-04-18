package edu.eci.bcol.Persistence;



import edu.eci.bcol.Domain.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductRepository {
    private List<Product> productos = new ArrayList<>();

    public void save(Product producto) {
        productos.add(producto);
    }

    public List<Product> obtenerTodos() {
        return productos;
    }
}
