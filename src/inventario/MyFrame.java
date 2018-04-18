
package inventario;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;

public class MyFrame extends javax.swing.JFrame{

    private final JSplitPane splitPane;  
    private final JPanel topPanel;       
    private final JPanel bottomPanel;    
    private final JTextField textFieldNombre;   
    private final JTextField textFieldMarca;
    private final JTextField textFieldCosto;
    private final JTextField textFieldCantidad;
    private final JButton button;  
    private final JRadioButton accRadio;
    private final JRadioButton comRadio;
    private JTable table;
    private Inventario i;
    private List<Producto> lista;
    
    private final String [] cols = { "ID", "Nombre", "Marca", "Costo", "Cantidad" };

    
    public MyFrame() throws IOException{
        i = new Inventario ("inventario.csv");
        lista = i.getLista();
        
        for (int j = 0; j < lista.size(); j++)
            System.out.println(lista.get(0).getNombre());

        splitPane = new JSplitPane();
        topPanel = new JPanel();   
        bottomPanel = new JPanel();
        textFieldNombre = new JTextField();
        textFieldMarca = new JTextField();
        textFieldCosto = new JTextField();
        textFieldCantidad = new JTextField();
        
        button = new JButton("Registrar"); 
        accRadio = new JRadioButton("Accesorio");
        comRadio = new JRadioButton("Computadora");
        
        ButtonGroup grupo = new ButtonGroup();
        grupo.add(accRadio);
        grupo.add(comRadio);
        accRadio.setSelected(true);
        
        /* Cuando se presiona el boton */
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (accRadio.isSelected()) {
                    System.out.println("ACC");
                    i.agregarProducto(Producto.Tipo.ACCESORIO, textFieldNombre.getText(),
                            textFieldMarca.getText(), Double.parseDouble(textFieldCosto.getText()),
                            Integer.parseInt(textFieldCantidad.getText()));
                    
                } else if (comRadio.isSelected()) {
                    i.agregarProducto(Producto.Tipo.COMPUTADORA, textFieldNombre.getText(),
                            textFieldMarca.getText(), Double.parseDouble(textFieldCosto.getText()),
                            Integer.parseInt(textFieldCantidad.getText()));
                    System.out.println("com");
                }
                
                try {
                    i.actualizarArchivo();
                } catch (IOException ex) {
                    Logger.getLogger(MyFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                i.getLista();
                
                // limpiar los textFields
                textFieldNombre.setText("");
                textFieldMarca.setText("");
                textFieldCosto.setText("");
                textFieldCantidad.setText("");
                
                Object [][] data = listaToArray(lista);
                table = new JTable(data, cols);
                
                table.repaint();
            }
        });
        
        /* Configuracion de la ventana */
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Super inventario");
        setPreferredSize(new Dimension(400, 400));     
        getContentPane().setLayout(new GridLayout()); 
        getContentPane().add(splitPane);              

        splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);  
        splitPane.setDividerLocation(200);
        splitPane.setTopComponent(topPanel);                 
        splitPane.setBottomComponent(bottomPanel);            

        
        /* TAbla */
        /*Object [][] data = {
            { 1, "Mouse", "Sony", new Double(230), 10 },
            { 2, "Teclado", "Dell", new Double(299), 3 },
            { 3, "Laptop", "Toshiba", new Double(9999), 1 }
        };
        */

        Object[][] data = listaToArray(lista);
        
        table = new JTable(data, cols);
        
        topPanel.setLayout(new BorderLayout());
        topPanel.add(table.getTableHeader(), BorderLayout.PAGE_START);
        topPanel.add(table, BorderLayout.CENTER);
        table.setEnabled(true);
        
        table.getSelectionModel().addListSelectionListener((ListSelectionEvent event) -> {
            System.out.println("ok");
            System.out.println(event);
        });
        
        /* Aqui se acaba la tabla */
        
        /* Formulario horrible parte baja */
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.Y_AXIS)); 
        
        /* RADIOBUTTONS                                           */
        JPanel inputRadio = new JPanel();
        inputRadio.setMaximumSize(new Dimension(Integer.MAX_VALUE, 25));
        inputRadio.setLayout(new BoxLayout(inputRadio, BoxLayout.X_AXIS));
        
        inputRadio.add(accRadio);
        inputRadio.add(comRadio);

        /* NOMBRE                                                 */
        JPanel inputPanel = new JPanel();
        inputPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 25));     
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.X_AXIS)); 

        inputPanel.add(new Label("Nombre: "));         
        inputPanel.add(textFieldNombre);   
        
        /* MARCA                                                   */
        JPanel inputMarca = new JPanel();
        inputMarca.setMaximumSize(new Dimension(Integer.MAX_VALUE, 25));
        inputMarca.setLayout(new BoxLayout(inputMarca, BoxLayout.X_AXIS));

        inputMarca.add(new Label("Marca: "));
        inputMarca.add(textFieldMarca);
        
        /* COSTO                                                    */
        JPanel inputCosto = new JPanel();
        inputCosto.setMaximumSize(new Dimension(Integer.MAX_VALUE, 25));
        inputCosto.setLayout(new BoxLayout(inputCosto, BoxLayout.X_AXIS));
        
        inputCosto.add(new Label("Costo: "));
        inputCosto.add(textFieldCosto);
        
        /* CANTIDAD                                                 */
        JPanel inputCantidad = new JPanel();
        inputCantidad.setMaximumSize(new Dimension(Integer.MAX_VALUE, 25));
        inputCantidad.setLayout(new BoxLayout(inputCantidad, BoxLayout.X_AXIS));
        
        inputCantidad.add(new Label("Cantidad: "));
        inputCantidad.add(textFieldCantidad);
        
        bottomPanel.add(inputRadio);
        bottomPanel.add(inputPanel);   
        bottomPanel.add(inputMarca);
        bottomPanel.add(inputCosto);
        bottomPanel.add(inputCantidad);
        bottomPanel.add(button);
        
        pack();   
    }
    
    public Object[][] listaToArray (List<Producto> lista) {
        Object [][] data = new Object[lista.size()][5];
        
        for (int j = 0; j < lista.size(); j++) {
          data[j][0] = lista.get(j).getID();
          data[j][1] = lista.get(j).getNombre();
          data[j][2] = lista.get(j).getMarca();
          data[j][3] = lista.get(j).getCosto();
          data[j][4] = lista.get(j).getCantidad();
        }
        
        return data;
    }

    public static void main(String args[]){
        EventQueue.invokeLater(new Runnable(){
            @Override
            public void run(){
                try {
                    new MyFrame().setVisible(true);
                } catch (IOException ex) {
                    Logger.getLogger(MyFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
}