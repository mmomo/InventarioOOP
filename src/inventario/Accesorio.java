
package inventario;

import javax.swing.JOptionPane;

public class Accesorio extends Producto implements Vender{

    public Accesorio(int id, String nombre, String marca, double costo, int cantidad) {
        super(id, nombre, marca, costo, cantidad);
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
            JOptionPane.showMessageDialog(null, "Costo: " + this.getCosto() + "\n" + 
            "Descuento: " + desc + "\n" +
            "Total: " + (this.getCosto() - desc));
            this.setCantidad(this.getCantidad() - 1);
            
        } else {
            System.out.println("No hay en existencia");
        }
    }
}
