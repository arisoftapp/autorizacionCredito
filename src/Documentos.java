
import complementos.apiClientes;
import complementos.apiSubir;
import complementos.consultasBD;
import java.awt.Cursor;
import static java.awt.Frame.WAIT_CURSOR;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
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
public class Documentos extends javax.swing.JFrame {

    /**
     * Creates new form Documentos
     */
    public static String codigoMacro;
    public File fichero1=null;
    consultasBD consultas=new consultasBD();
    Integer id_empresa=consultas.getIdEmpresa();
    apiClientes apiclientes=new apiClientes();
    JPopupMenu popup = new JPopupMenu();
    JMenuItem jMItem = new JMenuItem("Eliminar registro");
    JMenuItem jMItem2 = new JMenuItem("Modificar registro");
    apiSubir apisubir=new apiSubir();
    boolean valdoc=false,valruta=false;
    boolean tieneDoc=false;
    int cantDoc=0;
   
    public Documentos() {
        
        initComponents();
        popup.add(jMItem);
        popup.add(jMItem2);
        tb_doc.setComponentPopupMenu(popup);
        this.setLocationRelativeTo(null);
        lbl_cliente.setText(codigoMacro);

    
            jMItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                //System.out.println(tb_doc.getSize());
                if(tieneDoc==true)
                {
                    if(tb_doc.getSelectedRow()<cantDoc)
                    {
                        cuadroDialogo("Archivos ya subidos");
                        
                    }
                    else
                    {
                        DefaultTableModel modelo = (DefaultTableModel)tb_doc.getModel();
                        modelo.removeRow(tb_doc.getSelectedRow());
                    }
                }
                else
                {
                    if(tb_doc.getSelectedRow()>-1)
                    {
                        DefaultTableModel modelo = (DefaultTableModel)tb_doc.getModel();
                        modelo.removeRow(tb_doc.getSelectedRow());  
                    }
                    else
                    {
                        cuadroDialogo("Seleccione documento");
                    }
                }
                
                
      
                
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        jMItem2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                //System.out.println("prueba2");
                if(tieneDoc==true)
                {
                    if(tb_doc.getSelectedRow()<cantDoc)
                    {
                        cuadroDialogo("Archivos ya subidos");
                        
                    }
                    else
                    {
                        tb_doc.editCellAt(tb_doc.getSelectedRow(),0);
                        tb_doc.getEditorComponent().requestFocusInWindow();
                    }
                }
                else
                {
                    if(tb_doc.getSelectedRow()>-1)
                    {
                        tb_doc.editCellAt(tb_doc.getSelectedRow(),0);
                        tb_doc.getEditorComponent().requestFocusInWindow();
                    }
                    else
                    {
                        cuadroDialogo("Seleccione archivo");
                    }
                }
                
               
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        
        
        tb_doc.addMouseListener(new MouseAdapter(){
            public void mousePressed(MouseEvent mouse_evt)
                {
                    JTable tb=(JTable)mouse_evt.getSource();
                    Point point = mouse_evt.getPoint();
                    int row=tb.rowAtPoint(point);   
                    

                }
        });
        rsscalelabel.RSScaleLabel.setScaleLabel(lbl_buscardoc, "src/images/add-doc.png");
        rsscalelabel.RSScaleLabel.setScaleLabel(lbl_guardar, "src/images/save.png");
        //cambiarCursor(2);
        try{
             
           
            Thread t = new Documentos.async_rutasDoc();
            t.start();
            //t.join();
        }catch(Exception e)
        {
            System.err.println(e.getMessage());
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

        jLabel1 = new javax.swing.JLabel();
        lbl_cliente = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tb_doc = new javax.swing.JTable();
        lbl_guardar = new javax.swing.JLabel();
        lbl_buscardoc = new javax.swing.JLabel();
        btn_buscardoc = new javax.swing.JButton();
        btn_guardar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Guardar Documentos");
        setPreferredSize(new java.awt.Dimension(700, 300));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("Cliente:");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));
        getContentPane().add(lbl_cliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 10, 100, 20));

        tb_doc.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre", "Tipo", "Ruta"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                true, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tb_doc.setRowHeight(30);
        tb_doc.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tb_doc);
        if (tb_doc.getColumnModel().getColumnCount() > 0) {
            tb_doc.getColumnModel().getColumn(0).setResizable(false);
            tb_doc.getColumnModel().getColumn(0).setPreferredWidth(200);
            tb_doc.getColumnModel().getColumn(1).setResizable(false);
            tb_doc.getColumnModel().getColumn(1).setPreferredWidth(50);
            tb_doc.getColumnModel().getColumn(2).setResizable(false);
            tb_doc.getColumnModel().getColumn(2).setPreferredWidth(300);
        }

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 690, 180));
        getContentPane().add(lbl_guardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 20, 30, 30));
        getContentPane().add(lbl_buscardoc, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 20, 30, 30));

        btn_buscardoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_buscardocActionPerformed(evt);
            }
        });
        getContentPane().add(btn_buscardoc, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 10, 50, 50));

        btn_guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_guardarActionPerformed(evt);
            }
        });
        getContentPane().add(btn_guardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 10, 50, 50));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_buscardocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_buscardocActionPerformed
        // TODO add your handling code here:
        DefaultTableModel modelo = (DefaultTableModel)tb_doc.getModel();
        if(modelo.getRowCount()<5)
        {
            agregarDoc();
        }
        else
        {
            cuadroDialogo("Alcanzo el numero maximo de documentos");
        }
    }//GEN-LAST:event_btn_buscardocActionPerformed

    private void btn_guardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_guardarActionPerformed
        // TODO add your handling code here:
        if(tb_doc.getRowCount()>cantDoc)
        {
            if(valdoc==true)
        {
            if(valruta==false)
            {
                JSONObject json=new JSONObject();
                    json.put("data",crearJsonArray());
                    if(apiclientes.insertarRutaDoc(json)==true)
                    {
                        valruta=true;
                    }
            }
            
        }
        else
        {
            subirArchivos();
        }
        }
        else
        {
            cuadroDialogo("Agregue documento");
        }
        
        
        
        //System.out.println(crearJsonArray());
    }//GEN-LAST:event_btn_guardarActionPerformed
public class async_rutasDoc extends Thread
     {
         public void run()
         {
            try {     
                DefaultTableModel modelo = (DefaultTableModel)tb_doc.getModel();
                JSONObject res=apiclientes.getRutasDoc(codigoMacro);
                System.out.println(res);
                if(res.getBoolean("success"))
                {
                    JSONArray jarray=res.getJSONArray("respuesta");
                    cantDoc=jarray.length();
                    tieneDoc=true;
                    for (int i = 0; i < jarray.length(); i++) {
                    JSONObject object = jarray.getJSONObject(i);
                    String nom_archivo = object.getString("descripcion");  
                    
                Object [] fila = new Object[3];
                fila[0] = nom_archivo;
                fila[1] = "";
                fila[2] = "";
                modelo.addRow(fila);

                        System.out.println(nom_archivo);
                    }            
                }
            } catch (Exception ex) {
                System.err.println(ex.getMessage());
            } 
         }
     }

    public void subirArchivos()
    {
        try {
            
            if(apisubir.subirDocumentos(crearJsonArray()).getBoolean("success")==true)
            {
                valdoc=true;
                if(valruta==false)
                {
                    JSONObject json=new JSONObject();
                    json.put("data",crearJsonArray());
                    if(apiclientes.insertarRutaDoc(json)==true)
                    {
                        valruta=true;
                    }
                }
            }
            //apiclientes.insertarRutaDoc("");
        } catch (IOException ex) {
            Logger.getLogger(Documentos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public JSONArray crearJsonArray()
    {
         JSONArray res=new JSONArray();
        try{
            
         for (int i = cantDoc; i < tb_doc.getRowCount(); i++) {
             //System.out.println(tb_doc.getValueAt(i, 0));
             JSONObject json = new JSONObject();
            json.put("id_empresa", id_empresa);
            json.put("codigoMacro", codigoMacro);
            json.put("ruta", tb_doc.getValueAt(i, 2));
            json.put("descripcion", tb_doc.getValueAt(i, 0)); 
            res.put(json);
         }
        }
        catch(Exception e)
        {
            System.err.println(e.getMessage());
        }
       
        
        return res;
    }
    public void agregarDoc()
    {
        String nom_archivo="",tipo="",ruta="";
        
        JFileChooser fc=new JFileChooser();
        //fc.getFileFilter().toString().replaceFirst(".*extensions=\\[(.*)]]", ".$1").replaceFirst(".*AcceptAllFileFilter.*", "");
        //Abrimos la ventana, guardamos la opcion seleccionada por el usuario
        int seleccion=fc.showOpenDialog(this);
              //Si el usuario, pincha en aceptar
        if(seleccion==JFileChooser.APPROVE_OPTION){
            //Seleccionamos el fichero
            DefaultTableModel modelo = (DefaultTableModel)tb_doc.getModel();
        
                fichero1=fc.getSelectedFile();
                nom_archivo=fichero1.getName();
                tipo=getFileExtension(fichero1);
                ruta=fichero1.getAbsolutePath();    
                Object [] fila = new Object[3];
                fila[0] = nom_archivo;
                fila[1] = tipo;
                fila[2] = ruta;
                modelo.addRow(fila);
            
       
            
            //System.out.println(fichero.getName());
            //Ecribe la ruta del fichero seleccionado en el campo de texto  
        }
    }
    private static String getFileExtension(File file) {
        String fileName = file.getName();
        if(fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
        return fileName.substring(fileName.lastIndexOf(".")+1);
        else return "";
    }
    public void cuadroDialogo(String mensaje)
    {
          JOptionPane.showMessageDialog(null, mensaje);
    }
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
            java.util.logging.Logger.getLogger(Documentos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Documentos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Documentos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Documentos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Documentos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_buscardoc;
    private javax.swing.JButton btn_guardar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbl_buscardoc;
    private javax.swing.JLabel lbl_cliente;
    private javax.swing.JLabel lbl_guardar;
    private javax.swing.JTable tb_doc;
    // End of variables declaration//GEN-END:variables
}
