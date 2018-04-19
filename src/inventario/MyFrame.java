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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class MyFrame extends javax.swing.JFrame {

    private final JSplitPane splitPane;
    private final JPanel topPanel;
    private final JPanel bottomPanel;

    private final JTextField textFieldNombre;
    private final JTextField textFieldMarca;
    private final JTextField textFieldCosto;
    private final JTextField textFieldCantidad;

    private final JButton button;
    private final JButton buttonVender;
    private final JButton buttonEliminar;
    private final JButton buttonComprar;
    private final JRadioButton accRadio;
    private final JRadioButton comRadio;

    private JTable table;
    private DefaultTableModel model;

    private Inventario i;
    private List<Producto> lista;

    private final String[] cols = {"ID", "Nombre", "Marca", "Costo", "Cantidad"};

    public MyFrame() throws IOException {
        i = new Inventario("inventario.csv");
        lista = i.getLista();

        for (int j = 0; j < lista.size(); j++) {
            System.out.println(lista.get(j).getNombre());
        }

        splitPane = new JSplitPane();
        topPanel = new JPanel();
        bottomPanel = new JPanel();
        textFieldNombre = new JTextField();
        textFieldMarca = new JTextField();
        textFieldCosto = new JTextField();
        textFieldCantidad = new JTextField();

        button = new JButton("Registrar");
        buttonVender = new JButton("Vender");
        buttonEliminar = new JButton("Eliminar");
        buttonComprar = new JButton("Comprar");
        accRadio = new JRadioButton("Accesorio");
        comRadio = new JRadioButton("Computadora");

        ButtonGroup grupo = new ButtonGroup();
        grupo.add(accRadio);
        grupo.add(comRadio);
        accRadio.setSelected(true);

        /* Cuando se presiona el boton comprar */
        buttonComprar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                int id = Integer.parseInt(JOptionPane.showInputDialog("ID: "));
                int cantidad;
                
                List<Producto> l = i.getLista();
                for (Producto p : l) {
                    if (p.getID() == id) {
                        cantidad = Integer.parseInt(JOptionPane.showInputDialog("Comprar\nCantidad: "));
                        if (cantidad > 0) {
                            model.setValueAt(p.getCantidad() + cantidad, getRow(model, id), 4);                            
                            p.setCantidad(p.getCantidad() + cantidad);
                        }
                    }
                }
                try {
                    i.actualizarArchivo();
                } catch (IOException ex) {
                    Logger.getLogger(MyFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        /* Cuando se presiona el boton vender */
        buttonVender.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(JOptionPane.showInputDialog("ID: "));
                int cantidad;

                List<Producto> l = i.getLista();
                for (Producto p : l) {
                    if (p.getID() == id) {
                        cantidad = Integer.parseInt(JOptionPane.showInputDialog("Vender\nCantidad:"));
                        
                        if (p.getCantidad() > 0 && p.getCantidad() >= cantidad) {
                            model.setValueAt(p.getCantidad() - cantidad, getRow(model, id), 4);
                            if (p.tipo == Producto.Tipo.ACCESORIO) {
                                ((Accesorio) p).venta(cantidad);
                            } else {
                                ((Computadora) p).venta(cantidad);
                            }
                            try {
                                i.actualizarArchivo();
                            } catch (IOException ex) {
                                Logger.getLogger(MyFrame.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "No hay en existencia");
                        }
                    }
                }

            }
        });

        /* Cuando se presiona el boton eliminar */
        buttonEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                int id;
                id = Integer.parseInt(JOptionPane.showInputDialog("ID:"));

                System.out.println(id);

                List<Producto> l = i.getLista();
                for (Producto p : l) {
                    if (p.getID() == id) {
                        model.removeRow(getRow(model, id));
                        JOptionPane.showMessageDialog(null, "Eliminado " + p.getNombre() + " " + p.getMarca());
                    }
                }
                try {
                    i.eliminarProducto(id);
                } catch (IOException ex) {
                    Logger.getLogger(MyFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
                lista = i.getLista();
            }
        });

        /* Cuando se presiona el boton registrar*/
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

                List<Producto> l;
                l = i.getLista();

                // limpiar los textFields
                textFieldNombre.setText("");
                textFieldMarca.setText("");
                textFieldCosto.setText("");
                textFieldCantidad.setText("");

                Object[] ok = new Object[5];
                ok[0] = l.get(l.size() - 1).getID();
                ok[1] = l.get(l.size() - 1).getNombre();
                ok[2] = l.get(l.size() - 1).getMarca();
                ok[3] = l.get(l.size() - 1).getCosto();
                ok[4] = l.get(l.size() - 1).getCantidad();

                model.addRow(ok);
            }
        });

        /* Configuracion de la ventana */
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Super inventario");
        setPreferredSize(new Dimension(400, 500));
        getContentPane().setLayout(new GridLayout());
        getContentPane().add(splitPane);

        splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
        splitPane.setDividerLocation(200);
        splitPane.setTopComponent(topPanel);
        splitPane.setBottomComponent(bottomPanel);

        /* Tabla */
        Object[][] data = listaToArray(lista);

        model = new DefaultTableModel();
        for (String s : cols) {
            model.addColumn(s);
        }

        for (Object[] o : data) {
            model.addRow(o);
        }

        table = new JTable(model);

        JScrollPane pane = new JScrollPane(table);

        topPanel.setLayout(new BorderLayout());
        topPanel.add(table.getTableHeader(), BorderLayout.PAGE_START);
        topPanel.add(pane, BorderLayout.CENTER);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        table.setEnabled(false);

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
        bottomPanel.add(new JSeparator());
        bottomPanel.add(buttonComprar);
        bottomPanel.add(buttonEliminar);
        bottomPanel.add(buttonVender);

        pack();
    }

    private Object[][] listaToArray(List<Producto> lista) {
        Object[][] data = new Object[lista.size()][5];

        for (int j = 0; j < lista.size(); j++) {
            data[j][0] = lista.get(j).getID();
            data[j][1] = lista.get(j).getNombre();
            data[j][2] = lista.get(j).getMarca();
            data[j][3] = lista.get(j).getCosto();
            data[j][4] = lista.get(j).getCantidad();
        }

        return data;
    }

    private int getRow(TableModel model, Object valor) {
        for (int j = model.getRowCount() - 1; j >= 0; j--) {
            if (model.getValueAt(j, 0).equals(valor)) {
                return j;
            }
        }
        return -1;
    }

    public static void main(String args[]) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    new MyFrame().setVisible(true);
                } catch (IOException ex) {
                    Logger.getLogger(MyFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
}
