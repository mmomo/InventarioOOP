package inventario;

import java.io.IOException;

public class Main {
    public static void main (String[] args) throws IOException {
        Inventario i = new Inventario("inv.csv");
        
        i.agregarProducto(Producto.Tipo.ACCESORIO, "Teclado", "Dell", 380, 1);
        i.agregarProducto(Producto.Tipo.COMPUTADORA, "Laptop", "HP", 8900, 1);
        
        i.actualizarArchivo();
    }
}
