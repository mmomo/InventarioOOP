package inventario;

public abstract class Producto {
    public enum Tipo {
        ACCESORIO,
        COMPUTADORA
    }

    private final int ID;
    private String nombre;
    private String marca;
    private double costo;
    private int cantidad;
    public Tipo tipo;

    public Producto(int id, String nombre, String marca, double costo, int cantidad) {
        this.ID = id;
        this.nombre = nombre;        
        this.marca = marca;
        this.costo = costo;
        this.cantidad = cantidad;
    }
    
    public int getID () {
        return this.ID;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    
    abstract void mostrarInfo ();
}
