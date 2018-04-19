
package inventario;

import javax.swing.JOptionPane;

public class Computadora extends Producto implements Vender {
    
    public Computadora (int id, String nombre, String marca, double costo, int cantidad) {
        super(id, nombre, marca, costo, cantidad);
        this.tipo = Tipo.COMPUTADORA;
    }
    
    @Override
    public void venta (int cantidad) {
        if (this.getCantidad() > 0 && this.getCantidad() >= cantidad) {
            double desc = Math.round(this.getCosto() * cantidad * Descuentos.COMPUTADORA.getDescuento());
            JOptionPane.showMessageDialog(null, "Costo: " + this.getCosto() * cantidad + "\n" + 
            "Descuento: " + desc + "\n" +
            "Total: " + (this.getCosto() * cantidad - desc));
            this.setCantidad(this.getCantidad() - cantidad);
        } else {
            System.out.println("No hay en existencia");
        }
    }

    @Override
    void mostrarInfo() {
        System.out.println("");
    }
}
