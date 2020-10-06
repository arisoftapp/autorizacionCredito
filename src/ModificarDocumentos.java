

import complementos.apiClientes;
import complementos.apiSubir;
import complementos.consultasBD;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
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
public class ModificarDocumentos extends javax.swing.JFrame {

    /**
     * Creates new form ModificarDocumentos
     */
    consultasBD consultas=new consultasBD();
    Integer id_empresa=consultas.getIdEmpresa();
    apiClientes apiclientes=new apiClientes();
    apiSubir apisubir=new apiSubir();
    public static String codigoMacro;
    public Integer cantDoc=0;
    public Integer cantGuard=0;
    public boolean eliminar=false,guardar=false;
    boolean valdoc=false,valruta=false;
    public File fichero1=null;
     Object [] filaGuard=null;
    JPopupMenu popup = new JPopupMenu();
    JMenuItem jMItem = new JMenuItem("Eliminar registro");
    public ModificarDocumentos() {
        initComponents();
        this.setLocationRelativeTo(null);
        rsscalelabel.RSScaleLabel.setScaleLabel(lbl_guardar, "src/images/save.png");
        rsscalelabel.RSScaleLabel.setScaleLabel(lbl_agregar, "src/images/plus.png");
        popup.add(jMItem);
        tb_docGuard.setComponentPopupMenu(popup);
        jMItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                if(guardar==false)
                {
                    int comfir=JOptionPane.showConfirmDialog(null," Esta Opcion eliminar el archivo guardado.¿Esta seguro que desea continuar?", "Eliminar",JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
                   
                    if(comfir==JOptionPane.YES_OPTION)
                    {
                        if(apiclientes.eliminarRutaDoc(tb_docGuard.getValueAt(tb_docGuard.getSelectedRow(),0 ).toString())==true)
                        {
                            DefaultTableModel modelo = (DefaultTableModel)tb_docGuard.getModel();
                            modelo.removeRow(tb_docGuard.getSelectedRow());
                            cantDoc--;
                        }
                        System.out.println(tb_docGuard.getValueAt(tb_docGuard.getSelectedRow(),0 ));
                    }
                    
                }
                else
                {
                    cuadroDialogo("cambios ya guardados");
                }
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
  
        try{
             
           
            Thread t = new ModificarDocumentos.async_rutasDoc();
            t.start();
            //t.join();
        }catch(Exception e)
        {
            System.err.println(e.getMessage());
        }

        };
    public class async_rutasDoc extends Thread
    {
        public void run()
        {
            try {     
                DefaultTableModel modelo = (DefaultTableModel)tb_docGuard.getModel();
                JSONObject res=apiclientes.getRutasDoc(codigoMacro);
                System.out.println(res);
                if(res.getBoolean("success"))
                {
                    JSONArray jarray=res.getJSONArray("respuesta");
                    cantDoc=jarray.length();
                    for (int i = 0; i < jarray.length(); i++) {
                    JSONObject object = jarray.getJSONObject(i);
                    String nom_archivo = object.getString("descripcion");  
                    
                Object [] fila = new Object[3];
                fila[1] = nom_archivo;
                fila[0]=object.getInt("iddocumentos");
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
                        guardar=true;
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
            System.out.println("cantDoc"+cantDoc);
            System.out.println("cant"+tb_docSub.getRowCount());
         for (int i = 0; i < tb_docSub.getRowCount(); i++) {
             System.out.println("valores al crear json"+tb_docSub.getValueAt(i, 0));
             JSONObject json = new JSONObject();
            json.put("id_empresa", id_empresa);
            json.put("codigoMacro", codigoMacro);
            json.put("ruta", tb_docSub.getValueAt(i, 1));
            json.put("descripcion", tb_docSub.getValueAt(i,0 )); 
            res.put(json);
         }
        }
        catch(Exception e)
        {
            System.err.println(e.getMessage());
        }
       
        
        return res;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        tb_docSub = new javax.swing.JTable();
        jScrollPane1 = new javax.swing.JScrollPane();
        tb_docGuard = new javax.swing.JTable();
        lbl_guardar = new javax.swing.JLabel();
        lbl_agregar = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Modificar Documentos");
        setPreferredSize(new java.awt.Dimension(700, 400));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tb_docSub.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre del Documento", "Ruta"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tb_docSub.setRowHeight(30);
        jScrollPane2.setViewportView(tb_docSub);
        if (tb_docSub.getColumnModel().getColumnCount() > 0) {
            tb_docSub.getColumnModel().getColumn(0).setResizable(false);
            tb_docSub.getColumnModel().getColumn(1).setResizable(false);
        }

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 70, 300, 180));

        tb_docGuard.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Documento Guardados"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tb_docGuard.setRowHeight(30);
        jScrollPane1.setViewportView(tb_docGuard);
        if (tb_docGuard.getColumnModel().getColumnCount() > 0) {
            tb_docGuard.getColumnModel().getColumn(0).setMinWidth(20);
            tb_docGuard.getColumnModel().getColumn(0).setPreferredWidth(30);
            tb_docGuard.getColumnModel().getColumn(0).setMaxWidth(30);
            tb_docGuard.getColumnModel().getColumn(1).setResizable(false);
        }

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, 300, 180));
        getContentPane().add(lbl_guardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 270, 30, 30));
        getContentPane().add(lbl_agregar, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 270, 30, 30));

        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 260, 50, 50));

        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 260, 50, 50));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        if(guardar==false)
        {
           Integer cantTotal=cantDoc+cantGuard;
        System.out.println(cantTotal);
        if(cantTotal<5)
        {
            cargarDoc();
        }
        else
        {
            cuadroDialogo("Cantidad Maxima de documentos");
        } 
        }
        else
        {
            cuadroDialogo("cambios ya guardados");
        }
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        if(guardar==false)
        {
            
            //if(tb_docSub.getRowCount()>cantDoc)
            if(tb_docSub.getRowCount()>0)
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
                cuadroDialogo("Agregue Documentos");
            }
                
            System.out.println(cantGuard);
        }
        else
        {
            cuadroDialogo("Ya se guardaron cambios");
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    public void cargarDoc()
    {
        String nom_archivo="",tipo="",ruta="";
        JFileChooser fc=new JFileChooser();
        //fc.getFileFilter().toString().replaceFirst(".*extensions=\\[(.*)]]", ".$1").replaceFirst(".*AcceptAllFileFilter.*", "");
        //Abrimos la ventana, guardamos la opcion seleccionada por el usuario
        int seleccion=fc.showOpenDialog(this);
              //Si el usuario, pincha en aceptar
        if(seleccion==JFileChooser.APPROVE_OPTION){
            //Seleccionamos el fichero
            DefaultTableModel modelo = (DefaultTableModel)tb_docSub.getModel();
        
                fichero1=fc.getSelectedFile();
                nom_archivo=fichero1.getName();
                ruta=fichero1.getAbsolutePath();    
                Object [] fila = new Object[3];
                fila[0] = nom_archivo;
                fila[1] = ruta;
                modelo.addRow(fila);
                cantGuard++;
        }
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
            java.util.logging.Logger.getLogger(ModificarDocumentos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ModificarDocumentos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ModificarDocumentos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ModificarDocumentos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ModificarDocumentos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lbl_agregar;
    private javax.swing.JLabel lbl_guardar;
    private javax.swing.JTable tb_docGuard;
    private javax.swing.JTable tb_docSub;
    // End of variables declaration//GEN-END:variables
}
