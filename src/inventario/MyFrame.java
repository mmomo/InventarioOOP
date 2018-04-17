
package inventario;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;

public class MyFrame extends javax.swing.JFrame{

    private final JSplitPane splitPane;  
    private final JPanel topPanel;       
    private final JPanel bottomPanel;    
    private final JScrollPane scrollPane; 
    private final JTextArea textArea;     
    private final JPanel inputPanel;      
    private final JTextField textField;   
    private final JButton button;         
    private final JButton button2;

    public MyFrame(){

        splitPane = new JSplitPane();

        topPanel = new JPanel();   
        bottomPanel = new JPanel();

        scrollPane = new JScrollPane();  
        textArea = new JTextArea(); 

        inputPanel = new JPanel();
        textField = new JTextField();   
        button = new JButton("send"); 
        button2 = new JButton("Ok");
        
        button.addActionListener((ActionEvent e) -> {
            System.out.println("presionado");
        });
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(400, 400));     
        getContentPane().setLayout(new GridLayout()); 
        getContentPane().add(splitPane);              

        splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);  
        splitPane.setDividerLocation(200);
        splitPane.setTopComponent(topPanel);                 
        splitPane.setBottomComponent(bottomPanel);            

        
        /* TAbla */
        String [] cols = { "ID", "Nombre", "Marca", "Costo", "Cantidad" };
        Object [][] data = {
            { 1, "Mouse", "Sony", new Double(230), 10 },
            { 2, "Teclado", "Dell", new Double(299), 3 },
            { 3, "Laptop", "Toshiba", new Double(9999), 1 }
        };
        
        JTable table = new JTable(data, cols);
        
        topPanel.setLayout(new BorderLayout());
        topPanel.add(table.getTableHeader(), BorderLayout.PAGE_START);
        topPanel.add(table, BorderLayout.CENTER);
        table.setEnabled(false);
        
        table.getSelectionModel().addListSelectionListener((ListSelectionEvent event) -> {
            System.out.println("ok");
        });
        
        /* Aqui se acaba la tabla */
        
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.Y_AXIS)); 

        bottomPanel.add(scrollPane);              
        scrollPane.setViewportView(textArea);       
        bottomPanel.add(inputPanel);                

        inputPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 75));     
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.X_AXIS));  

        inputPanel.add(textField);        
        inputPanel.add(button);           
        pack();   
    }

    public static void main(String args[]){
        EventQueue.invokeLater(new Runnable(){
            @Override
            public void run(){
                new MyFrame().setVisible(true);
            }
        });
    }
}