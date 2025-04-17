public class Cliente {
    
    private String nombre; 
    private int edad;


    public Cliente(String nombre, int edad) { 
        this.nombre = nombre;
        this.edad = edad; 
    }

    /**
     * 
     * Muestra la información del cliente.
     * 
     * @return void
     */
    public void mostrarInformacion() {
        System.out.println("Cliente: " + nombre + ", Edad: " + edad); 
    }

    
    public static void main(String[] args) {
        Cliente cliente = new Cliente("Juan", 30); 
        cliente.mostrarInformacion(); 
    }
}

