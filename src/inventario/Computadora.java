
package inventario;

public class Computadora extends Producto implements Vender {
    
    public Computadora (int id, String nombre, String marca, double costo, int cantidad) {
        super(id, nombre, marca, costo, cantidad);
        this.tipo = Tipo.COMPUTADORA;
    }
    
    @Override
    public double venta () {
        if (this.getCantidad() > 0) {
            double desc = Math.round(this.getCosto() * Descuentos.COMPUTADORA.getDescuento());
            System.out.println("Costo: " + this.getCosto());
            System.out.println("Descuento: " + desc);
            System.out.println("Total: " + (this.getCosto() - desc));
            this.setCantidad(this.getCantidad() - 1);
            return this.getCosto() - desc;
        } else {
            System.out.println("No hay en existencia");
            return 0;
        }
    }

    @Override
    void mostrarInfo() {
        System.out.println("");
    }
}
