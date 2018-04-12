package inventario;

import java.io.IOException;

public class Main {
    public static void main (String[] args) throws IOException {
        Inventario i = new Inventario("inv.csv");
        
        Producto p = new Computadora(2, "Laptop", "Lenovo", 14500, 2);
        i.agregarProducto(p);
        p.mostrarInfo();
        //i.mostrarInventario();
        i.actualizarArchivo();
    }
}
