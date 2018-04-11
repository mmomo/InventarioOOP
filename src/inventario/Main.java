package inventario;

import java.io.IOException;

public class Main {
    public static void main (String[] args) throws IOException {
        Inventario i = new Inventario("inventario.csv");
        Producto p = new Accesorio("borrador", "Dell", 249, 5);        
        //i.agregarProducto(p);
        
        //p = new Accesorio("audifono", "Sony", 554, 3);
        //i.agregarProducto(p);
        
        //p = new Accesorio("Memoria", "Kingston", 230, 3);
        //i.agregarProducto(p);
        i.mostrarInventario();
        i.actualizarArchivo();
    }
}
