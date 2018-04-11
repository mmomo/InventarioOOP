package inventario;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Inventario {

    private final String archivo;
    private List<Producto> productos;
    private BufferedReader f ;
    private BufferedWriter writer;
    private String linea = "";

    public Inventario(String archivo) throws IOException {
        this.archivo = archivo;
        productos = new ArrayList<> ();
        
        try {
            this.f = new BufferedReader(new FileReader(this.archivo));
            System.out.println(this.archivo + " leido con exito");
            
            while ((linea = f.readLine()) != null) {
                String [] valores = linea.split(",");
                
                if (valores[0].equals(String.valueOf(Producto.Tipo.ACCESORIO))) {
                    this.productos.add(new Accesorio(valores[1], valores[2], 
                            Double.parseDouble(valores[3]), Integer.parseInt(valores[4])));
                }                
            }
            
        } catch (FileNotFoundException e) {
            System.out.println("¡" + this.archivo + " no existe!");
            System.out.println("=> Generando archivo " + this.archivo);
         
            writer = new BufferedWriter(new FileWriter(this.archivo));
            writer.write("Hola Manolo\n");
            writer.close();
            
            System.out.println("Listo");            
        }
    }
    
    public void agregarProducto (Producto p) {
        this.productos.add(p);
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
            writer.write("\n");
        }
        writer.close();
    }
    
}
