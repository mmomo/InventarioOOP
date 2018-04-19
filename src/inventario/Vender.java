
package inventario;

public interface Vender {
    public enum Descuentos {
        ACCESORIO (0.05),
        COMPUTADORA (0.1);
        
        private final double descuento;
        
        Descuentos (double desc) {
            this.descuento = desc;
        }
        
        public double getDescuento () {
            return this.descuento;
        }
    }
    
    abstract void venta (int cantidad);
}
