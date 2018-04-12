
package inventario;

public class Accesorio extends Producto implements Vender{

    public Accesorio(String nombre, String marca, double costo, int cantidad) {
        super(nombre, marca, costo, cantidad);
        this.tipo = Tipo.ACCESORIO;
    }

    @Override
    public void mostrarInfo() {
        System.out.println("Accesorios");
    }
    
    @Override
    public void venta () {
        if (this.getCantidad() > 0) {
            double desc = Math.round(this.getCosto() * Descuentos.ACCESORIO.getDescuento());
            System.out.println("Costo: " + this.getCosto());
            System.out.println("Descuento: " + desc);
            System.out.println("Total: " + (this.getCosto() - desc));
            this.setCantidad(this.getCantidad() - 1);
        } else {
            System.out.println("No hay en existencia");
        }
    }
}
