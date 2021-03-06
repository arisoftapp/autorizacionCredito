

import complementos.apiAutorizados;
import complementos.apiSubir;
import complementos.consultasBD;
import java.awt.Cursor;
import static java.awt.Frame.DEFAULT_CURSOR;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerListModel;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.json.JSONArray;
import org.json.JSONException;
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
public class EditarAutorizado extends javax.swing.JFrame {

    /**
     * Creates new form EditarAutorizado
     */
    public static Integer id;
    public static String cod_mac;
    apiAutorizados apiautorizados=new apiAutorizados();
    public File fichero=null;
    boolean valFoto;
    String ruta="";
    apiSubir subirImg=new apiSubir();
    consultasBD consultas=new consultasBD();
        Integer id_empresa=consultas.getIdEmpresa();
    public EditarAutorizado() {
        initComponents();
        this.setLocationRelativeTo(null);
        btn_huella.setVisible(false);
        lbl_huella.setVisible(false);
        txt_codigo.setText(cod_mac);
        txt_id.setText(""+id);
        limpiarDirectorio();
        rsscalelabel.RSScaleLabel.setScaleLabel(lbl_btnfoto, "src/images/anadir.png");
        rsscalelabel.RSScaleLabel.setScaleLabel(lbl_foto, "src/images/camera.png");
        //rsscalelabel.RSScaleLabel.setScaleLabel(lbl_huella, "src/images/finger-scanner.png");
        rsscalelabel.RSScaleLabel.setScaleLabel(lbl_guardar, "src/images/save.png");
        Thread task = new EditarAutorizado.asyncAutorizado();
        task.start();
        //spn_estatus.setValue("BAJA");
        //spn_estatus.setValue("xd");
        //spn_estatus
    }
    public class asyncAutorizado extends Thread
     {
         public void run()
         {
             
             getAutorizado();
         }
     }
    
    public void getAutorizado()
    {
         this.setCursor(new Cursor(WAIT_CURSOR));
         try{
             JSONArray array=apiautorizados.getAutorizadoId(id,cod_mac).getJSONArray("respuesta");
             for (int i=0;i<array.length();i++){
             JSONObject obj=array.getJSONObject(i);
             txt_nombre.setText(obj.getString("nombre"));
             txt_paterno.setText(obj.getString("a_paterno"));
             txt_materno.setText(obj.getString("a_materno"));
             txt_comentarios.setText(obj.getString("comentarios"));
            
             
             if(obj.getInt("estatus")==1)
             {
                 spn_estatus.setValue("ACTIVO");
             }
             else
             {
                 spn_estatus.setValue("BAJA");
             }
             if(obj.getInt("valFoto")==1)
             {
                 valFoto=true;
                 //descargar foto
                 ruta=obj.getString("foto");
                 apiautorizados.downloadFoto(ruta);
                 rsscalelabel.RSScaleLabel.setScaleLabel(lbl_foto, "src/tmpDescargas/"+ruta);
                 
             }
             else
             {
                 valFoto=false;
             }
             
             
             
         }
         System.out.println(array);
         }
         catch(JSONException e)
         {
             System.err.println(e.getMessage());
             cuadroDialogo("Problema al cargar autorizado");
             //this.setVisible(false);
         }
         finally{
              this.setCursor(new Cursor(DEFAULT_CURSOR));
         }
         
       
    }
public void limpiarDirectorio()
    {
        File directorio = new File("src/tmpDescargas");
        File[] ficheros = directorio.listFiles();
        for(int i=0;i<ficheros.length;i++)
        {
            ficheros[i].delete();
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
        txt_codigo = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txt_id = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txt_paterno = new javax.swing.JTextField();
        txt_materno = new javax.swing.JTextField();
        txt_nombre = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        txt_comentarios = new javax.swing.JTextArea();
        lbl_foto = new javax.swing.JLabel();
        lbl_btnfoto = new javax.swing.JLabel();
        btn_foto = new javax.swing.JButton();
        spn_estatus = new javax.swing.JSpinner();
        lbl_foto1 = new javax.swing.JLabel();
        lbl_guardar = new javax.swing.JLabel();
        lbl_huella = new javax.swing.JLabel();
        btn_guardar = new javax.swing.JButton();
        btn_huella = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Editar");
        setPreferredSize(new java.awt.Dimension(700, 400));
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("Codigo MacroPro:");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, -1, -1));

        txt_codigo.setEditable(false);
        getContentPane().add(txt_codigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 10, 100, 30));

        jLabel2.setText("ID Autorizado:");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 20, -1, -1));

        txt_id.setEditable(false);
        getContentPane().add(txt_id, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 10, 100, 30));

        jLabel3.setText("Nombre:");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 70, -1, -1));

        jLabel4.setText("Apellido Paterno:");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 70, -1, -1));

        jLabel5.setText("Apellido Materno:");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, -1, -1));

        jLabel6.setText("Comentarios:");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 120, -1, -1));

        txt_paterno.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_paternoKeyReleased(evt);
            }
        });
        getContentPane().add(txt_paterno, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 60, 100, 30));

        txt_materno.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_maternoKeyReleased(evt);
            }
        });
        getContentPane().add(txt_materno, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 110, 100, 30));

        txt_nombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_nombreKeyReleased(evt);
            }
        });
        getContentPane().add(txt_nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 60, 100, 30));

        txt_comentarios.setColumns(20);
        txt_comentarios.setRows(5);
        txt_comentarios.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_comentariosKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(txt_comentarios);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 120, 220, -1));
        getContentPane().add(lbl_foto, new org.netbeans.lib.awtextra.AbsoluteConstraints(585, 15, 80, 80));
        getContentPane().add(lbl_btnfoto, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 120, 30, 30));

        btn_foto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_fotoActionPerformed(evt);
            }
        });
        getContentPane().add(btn_foto, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 110, 50, 50));

        spn_estatus.setModel(new javax.swing.SpinnerListModel(new String[] {"BAJA", "ACTIVO"}));
        getContentPane().add(spn_estatus, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 10, 100, 30));

        lbl_foto1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        getContentPane().add(lbl_foto1, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 10, 90, 90));
        getContentPane().add(lbl_guardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 250, 30, 30));
        getContentPane().add(lbl_huella, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 250, 30, 30));

        btn_guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_guardarActionPerformed(evt);
            }
        });
        getContentPane().add(btn_guardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 240, 50, 50));

        btn_huella.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_huellaActionPerformed(evt);
            }
        });
        getContentPane().add(btn_huella, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 240, 50, 50));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        ModificarAutorizados vmod=null;
        vmod.pantalla=2;
        vmod.cod_macro=cod_mac;
        vmod=new ModificarAutorizados();
        vmod.setVisible(true);
        
        
    }//GEN-LAST:event_formWindowClosing

    private void txt_nombreKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_nombreKeyReleased
        // TODO add your handling code here:
        txt_nombre.setText(txt_nombre.getText().toUpperCase());
    }//GEN-LAST:event_txt_nombreKeyReleased

    private void txt_paternoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_paternoKeyReleased
        // TODO add your handling code here:
        txt_paterno.setText(txt_paterno.getText().toUpperCase());
    }//GEN-LAST:event_txt_paternoKeyReleased

    private void txt_maternoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_maternoKeyReleased
        // TODO add your handling code here:
        txt_materno.setText(txt_materno.getText().toUpperCase());
    }//GEN-LAST:event_txt_maternoKeyReleased

    private void txt_comentariosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_comentariosKeyReleased
        // TODO add your handling code here:
        txt_comentarios.setText(txt_comentarios.getText().toUpperCase());
    }//GEN-LAST:event_txt_comentariosKeyReleased

    private void btn_guardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_guardarActionPerformed
        // TODO add your handling code here:
            if(fichero==null)
            {
               //enviar misma ruta
                
                apiautorizados.updateAutorizado(crearJsonUpdate());
                //cuadroDialogo("sin foto nueva");
            }
            else
            {
                //subir foto nueva actualizar ruta
                //cuadroDialogo("foto nueva");
               
                valFoto=true;
                try {
                            //subir imagen primero
                            JSONObject json=subirImg.subirImg(fichero);
                            if(json.getBoolean("success"))
                            {
                                ruta=json.getString("nombre");
                                //cuadroDialogo(json.getString("nombre"));
                                apiautorizados.updateAutorizado(crearJsonUpdate());
                            }
                            else
                            {
                                cuadroDialogo("Problemas al actualizar imagen");
                                this.dispose();
                            }
                            //guardarAutorizadoAync(true);
                        } catch (IOException ex) {
                            cuadroDialogo(ex.getMessage());
                            Logger.getLogger(Autorizado.class.getName()).log(Level.SEVERE, null, ex);
                            
                        }
                
            }
    }//GEN-LAST:event_btn_guardarActionPerformed

    private void btn_fotoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_fotoActionPerformed
        // TODO add your handling code here:
         FileNameExtensionFilter filtroImagen=new FileNameExtensionFilter("JPG, PNG & GIF","jpg","png","gif");
        JFileChooser fc=new JFileChooser();
        fc.setFileFilter(filtroImagen);
        //Abrimos la ventana, guardamos la opcion seleccionada por el usuario
        int seleccion=fc.showOpenDialog(this);
              //Si el usuario, pincha en aceptar
        if(seleccion==JFileChooser.APPROVE_OPTION){
            //Seleccionamos el fichero
            fichero=fc.getSelectedFile();
            //System.out.println(fichero.getName());
            //Ecribe la ruta del fichero seleccionado en el campo de texto  
            rsscalelabel.RSScaleLabel.setScaleLabel(lbl_foto, fichero.getAbsolutePath());
        }
    }//GEN-LAST:event_btn_fotoActionPerformed

    private void btn_huellaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_huellaActionPerformed
        // TODO add your handling code here:
        CapturaHuella vista=null;
            vista.codigo=cod_mac;
            vista.nombre=txt_nombre.getText().trim();
            vista.id_autorizados=id;
            vista.pantalla=2;
            vista=new CapturaHuella();
            vista.setVisible(true);
    }//GEN-LAST:event_btn_huellaActionPerformed

    public JSONObject crearJsonUpdate()
    {
        
        JSONObject obj = new JSONObject();
        try {
            obj.put("codigoMacro",txt_codigo.getText().trim());
            obj.put("nombre", txt_nombre.getText().trim());
            obj.put("a_paterno",txt_paterno.getText().trim());
            obj.put("a_materno", txt_materno.getText().trim());
            obj.put("id_empresa",id_empresa);
            obj.put("comentarios", txt_comentarios.getText().trim());
            obj.put("foto", ruta);
            obj.put("valFoto", valFoto);
            obj.put("estatus", getEstatus());
            obj.put("idautorizado", id);
            }
        catch (JSONException e)
            {
             System.err.println("error al crear JSON:"+e.getMessage());  
            }
        return obj;
    }
    public Integer getEstatus()
    {
        Integer res=0;
       if(spn_estatus.getValue().toString().equalsIgnoreCase("ACTIVO"))
       {
           res=1;
       }
       else
       {
           res=0;
       }
       return res;
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
            java.util.logging.Logger.getLogger(EditarAutorizado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EditarAutorizado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EditarAutorizado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EditarAutorizado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EditarAutorizado().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_foto;
    private javax.swing.JButton btn_guardar;
    private javax.swing.JButton btn_huella;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbl_btnfoto;
    private javax.swing.JLabel lbl_foto;
    private javax.swing.JLabel lbl_foto1;
    private javax.swing.JLabel lbl_guardar;
    private javax.swing.JLabel lbl_huella;
    private javax.swing.JSpinner spn_estatus;
    private javax.swing.JTextField txt_codigo;
    private javax.swing.JTextArea txt_comentarios;
    private javax.swing.JTextField txt_id;
    private javax.swing.JTextField txt_materno;
    private javax.swing.JTextField txt_nombre;
    private javax.swing.JTextField txt_paterno;
    // End of variables declaration//GEN-END:variables
}
