
package inventario;

public class Computadora extends Producto implements Vender {
    
    public Computadora (String nombre, String marca, double costo, int cantidad) {
        super(nombre, marca, costo, cantidad);
    }
    
    @Override
    public void venta () {
        if (this.cantidad > 0) {
            double desc = Math.round(this.costo * Descuentos.COMPUTADORA.getDescuento());
            System.out.println("Costo: " + this.costo);
            System.out.println("Descuento: " + desc);
            System.out.println("Total: " + this.costo - desc);
            this.cantidad--;
        } else {
            System.out.println("No hay en existencia");
        }
    }

    @Override
    void mostrarInfo() {
        System.out.println("");
    }
}
