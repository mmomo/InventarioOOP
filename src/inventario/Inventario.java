package inventario;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Inventario {

    private final String archivo;
    private List<Producto> productos;
    private BufferedReader f ;
    private BufferedWriter writer;
    private String linea = "";
    
    private int id = 1;
    private double costoTotal;

    public Inventario(String archivo) throws IOException {
        this.archivo = archivo;
        productos = new ArrayList<> ();
        
        try {
            this.f = new BufferedReader(new FileReader(this.archivo));
            System.out.println(this.archivo + " leido con exito");
            
            while ((linea = f.readLine()) != null) {
                String [] valores = linea.split(",");
                this.id = Integer.parseInt(valores[5]) + 1;
                
                if (valores[0].equals(String.valueOf(Producto.Tipo.ACCESORIO))) {
                    this.productos.add(new Accesorio(Integer.parseInt(valores[5]), valores[1], valores[2], 
                            Double.parseDouble(valores[3]), Integer.parseInt(valores[4])));
                } else if (valores[0].equals(String.valueOf(Producto.Tipo.COMPUTADORA))) {
                    this.productos.add(new Computadora(Integer.parseInt(valores[5]), valores[1], valores[2],
                            Double.parseDouble(valores[3]), Integer.parseInt(valores[4])));
                }               
            }
            
        } catch (FileNotFoundException e) {
            System.out.println("¡" + this.archivo + " no existe!");
            System.out.println("=> Generando archivo " + this.archivo);
         
            writer = new BufferedWriter(new FileWriter(this.archivo));
            writer.close();
            
            System.out.println("Listo");            
        }
    }
    
    public double getCostoTotal () {
        return this.costoTotal;
    }
    
    public void agregarProducto (Producto.Tipo tipo, String nombre, String marca, double costo, int cantidad) {
        Producto p = null;
        
        if (tipo == Producto.Tipo.ACCESORIO) {
            p = new Accesorio (this.id, nombre, marca, costo, cantidad);
            this.id++;
        } else if (tipo == Producto.Tipo.COMPUTADORA) {
            p = new Computadora(this.id, nombre, marca, costo, cantidad);
            this.id++;
        }
        
        this.productos.add(p);
    }
    
    public void eliminarProducto (int id) {
        for (Iterator<Producto> i = productos.listIterator(); i.hasNext();) {
            Producto p = i.next();
            if (p.getID() == id) {
                i.remove();
            }
        }
        //this.actualizarArchivo();
    }
    
    public void mostrarInventario () {
        for (Producto p : this.productos) {
            System.out.print(p.getNombre() + "\t");
            System.out.print(p.getMarca() + "\t");
            System.out.print(p.getCosto() + "\t");
            System.out.println(p.getCantidad());
        }
    }
    
    public void actualizarArchivo () throws IOException {
        writer = new BufferedWriter (new FileWriter(this.archivo));

        for (Producto p : this.productos) {
            writer.write(String.valueOf(p.tipo));
            writer.write(",");
            writer.write(p.getNombre());
            writer.write(",");
            writer.write(p.getMarca());
            writer.write(",");
            writer.write(String.valueOf(p.getCosto()));
            writer.write(",");
            writer.write(String.valueOf(p.getCantidad()));
            writer.write(",");
            writer.write(String.valueOf(p.getID()));
            writer.write("\n");
        }
        writer.close();
    }
    
}
