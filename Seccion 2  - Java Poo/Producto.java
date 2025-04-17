

public class Producto {

    private int id;
    private String nombre;
    private double precio;
    private int CantidadStock;


    public Producto(int id, String nombre, double precio, int CantidadStock) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.CantidadStock = CantidadStock;
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getCantidadStock() {
        return CantidadStock;
    }

    public void setCantidadStock(int CantidadStock) {
        this.CantidadStock = CantidadStock;
    }

    /**
     * Calcula el valor total del stock del producto.
     *
     * 
     * 
     * @return El valor total del stock.
     */
    public String getTotalStockValue(){
        return Double.toString(precio * CantidadStock);
    }

    public static void main(String[] args) {
        Producto producto = new Producto(1, "Laptop", 1500.00, 10);
        System.out.println("Valor total del stock: " + producto.getTotalStockValue());
    }


}