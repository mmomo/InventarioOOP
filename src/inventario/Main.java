package inventario;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    
    public static void main(String[] args) throws IOException {

        Scanner sc = new Scanner(System.in);
        MyFrame frame = new MyFrame();
        frame.setVisible(true);
        //Inventario i = new Inventario("inv.csv");
        
        //i.agregarProducto(Producto.Tipo.ACCESORIO, "Mouse", "Sony", 1, 0);
      
        boolean ciclar = true;
        int opc;
/*
        do {
            System.out.print(": ");

            opc = sc.nextInt();
            
            switch (opc) {
                case 1:
                    System.out.println("crear producto");
                    break;
                case 2:
                    System.out.println("Borrar producto");
                    break;
                case 3:
                    System.out.println("Vender");
                    break;
                case 4:
                    System.out.println("comprar");
                    break;
                case 9:
                    System.out.println("Salir");
                    ciclar = false;
            }
        } while (ciclar);
*/
  //      i.eliminarProducto(4);
        //i.actualizarArchivo();
    }
}
