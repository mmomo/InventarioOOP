
package inventario;

public class Accesorio extends Producto {

    public Accesorio(String nombre, String marca, double costo, int cantidad) {
        super(nombre, marca, costo, cantidad);
        this.tipo = Tipo.ACCESORIO;
    }

    @Override
    void mostrarInfo() {
        System.out.println("Accesorios");
    }
    
}
