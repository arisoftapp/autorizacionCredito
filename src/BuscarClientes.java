
import complementos.apiClientes;
import java.awt.Cursor;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import org.json.JSONArray;
import org.json.JSONObject;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Desarrollo
 */
public class BuscarClientes extends javax.swing.JFrame {

    /**
     * Creates new form BuscarClientes
     */
    DefaultTableModel completo=null;
    apiClientes apiclientes=new apiClientes();
    String codigoMacroSelect="";
    public BuscarClientes() {
        initComponents();
        this.setLocationRelativeTo(null);
        rsscalelabel.RSScaleLabel.setScaleLabel(lbl_guardar, "src/images/save.png");
        tb_clientes.addMouseListener(new MouseAdapter(){
            public void mousePressed(MouseEvent mouse_evt)
                {
                    JTable tb=(JTable)mouse_evt.getSource();
                    Point point = mouse_evt.getPoint();
                    int row=tb.rowAtPoint(point);
                    codigoMacroSelect=tb.getValueAt(tb.getSelectedRow(), 0).toString();
                    //System.out.println(""+row);
                    System.out.println(""+codigoMacroSelect);
                }
        });
        DefaultTableModel modelo = (DefaultTableModel)tb_clientes.getModel();
        try{
            this.setCursor(new Cursor(WAIT_CURSOR));
            JSONObject res=apiclientes.getClientes();
            JSONArray jArray = res.getJSONArray("respuesta");
                 for (int i=0;i<jArray.length();i++){
                      JSONObject obj=jArray.getJSONObject(i);
                        String nombre=obj.getString("nombre")+" "+obj.getString("a_paterno")+" "+obj.getString("a_materno");
                        String codigo=obj.getString("codigoMacro");
                        String puesto=obj.getString("puesto");
                        //System.out.println(codigo);
                        Object [] fila = new Object[3];
                        fila[0] = codigo;
                        fila[1] = nombre;
                        fila[2] = puesto;
                        modelo.addRow ( fila );     
                }
        }catch(Exception e)
        {
            System.err.println(e.getMessage());
        }
        finally{
            this.setCursor(new Cursor(DEFAULT_CURSOR));
        }
        
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txt_nombre = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tb_clientes = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        lbl_guardar = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(510, 410));
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txt_nombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_nombreKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_nombreKeyReleased(evt);
            }
        });
        getContentPane().add(txt_nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 10, 100, -1));

        tb_clientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo MacroPro", "Nombre", "Puesto"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tb_clientes.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tb_clientes);
        if (tb_clientes.getColumnModel().getColumnCount() > 0) {
            tb_clientes.getColumnModel().getColumn(0).setResizable(false);
            tb_clientes.getColumnModel().getColumn(1).setResizable(false);
            tb_clientes.getColumnModel().getColumn(2).setResizable(false);
        }

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 484, 272));

        jLabel1.setText("Nombre:");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, -1, -1));

        lbl_guardar.setText("jLabel2");
        getContentPane().add(lbl_guardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 330, 30, 30));

        jButton1.setText("jButton1");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 320, 50, 50));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txt_nombreKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_nombreKeyPressed
        // TODO add your handling code here:
        //System.out.println(evt.getKeyCode());
        
         
                       
    }//GEN-LAST:event_txt_nombreKeyPressed

    private void txt_nombreKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_nombreKeyReleased
        // TODO add your handling code here:
        txt_nombre.setText(txt_nombre.getText().toUpperCase());
        completo=(DefaultTableModel)tb_clientes.getModel();
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<>(completo);
        tb_clientes.setRowSorter(tr);
        tr.setRowFilter(RowFilter.regexFilter(txt_nombre.getText(), 1));
    }//GEN-LAST:event_txt_nombreKeyReleased

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        if(codigoMacroSelect.equalsIgnoreCase(""))
        {
            JOptionPane.showMessageDialog(null, "no ha seleccionado Cliente");
        }
        else
        {
            System.out.println(codigoMacroSelect);
            ModificarAutorizados vista=null;
            vista.cod_macro=codigoMacroSelect;
            vista.pantalla=3;
            vista=new ModificarAutorizados();
            vista.setVisible(true);
            this.setVisible(false);
        }
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        if(codigoMacroSelect.equalsIgnoreCase(""))
        {
            //JOptionPane.showMessageDialog(null, "no ha seleccionado Cliente");
            ModificarAutorizados vista=null;
            vista.pantalla=0;
            vista=new ModificarAutorizados();
            vista.setVisible(true);
        }
        else
        {
            ModificarAutorizados vista=null;
            vista.cod_macro=codigoMacroSelect;
            vista.pantalla=3;
            vista=new ModificarAutorizados();
            vista.setVisible(true);
        }
        
            
    }//GEN-LAST:event_formWindowClosing

    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(BuscarClientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BuscarClientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BuscarClientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BuscarClientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BuscarClientes().setVisible(true);
            }
        });
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbl_guardar;
    private javax.swing.JTable tb_clientes;
    private javax.swing.JTextField txt_nombre;
    // End of variables declaration//GEN-END:variables
}
