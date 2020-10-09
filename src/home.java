

import complementos.apiAutorizados;
import complementos.consultasApi;
import complementos.consultasBD;
import java.awt.Cursor;
import static java.awt.Frame.WAIT_CURSOR;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.IOException;
import java.io.InputStream;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.FileEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.ContentBody;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author antonio
 */
public class home extends javax.swing.JFrame {
    
    /**
     * Creates new form inicio
     */
    public home() {
        initComponents();
        this.setLocationRelativeTo(null);
         rsscalelabel.RSScaleLabel.setScaleLabel(img_clientes, "src/images/persona.png");
        rsscalelabel.RSScaleLabel.setScaleLabel(lbl_mod, "src/images/register.png");
        rsscalelabel.RSScaleLabel.setScaleLabel(lbl_verificar, "src/images/fingerprint.png");
         rsscalelabel.RSScaleLabel.setScaleLabel(img_logout, "src/images/logout.png");
         rsscalelabel.RSScaleLabel.setScaleLabel(lbl_consulta, "src/images/lupa.png");
          usuario=consultasBD.getUsuario();
          id_empresa=consultasBD.getIdEmpresa();
          nom_empresa=consultasBD.getNombreEmpresa();
          System.out.println(usuario);
          lbl_empresa.setText(" "+id_empresa+" - "+nom_empresa);
          jButton1.setVisible(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbl_verificar = new javax.swing.JLabel();
        lbl_mod = new javax.swing.JLabel();
        img_clientes = new javax.swing.JLabel();
        img_logout = new javax.swing.JLabel();
        btn_alta = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        btn_salir = new javax.swing.JButton();
        btn_verificar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        lbl_empresa = new javax.swing.JLabel();
        lbl_consulta = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        btn_consulta = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Home");
        setMinimumSize(new java.awt.Dimension(700, 400));
        setPreferredSize(new java.awt.Dimension(700, 300));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(lbl_verificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 50, 70, 70));
        getContentPane().add(lbl_mod, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 50, 70, 70));
        getContentPane().add(img_clientes, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 50, 70, 70));
        getContentPane().add(img_logout, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 170, 70, 70));

        btn_alta.setText("Alta de Cliente");
        btn_alta.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btn_alta.setMargin(new java.awt.Insets(2, 14, 15, 14));
        btn_alta.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        btn_alta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_altaActionPerformed(evt);
            }
        });
        getContentPane().add(btn_alta, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, 200, 100));

        jButton3.setText("Modificar Cliente");
        jButton3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButton3.setMargin(new java.awt.Insets(2, 14, 15, 14));
        jButton3.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 40, 200, 100));

        btn_salir.setText("Cerrar Sesion");
        btn_salir.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btn_salir.setMargin(new java.awt.Insets(2, 14, 15, 14));
        btn_salir.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        btn_salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_salirActionPerformed(evt);
            }
        });
        getContentPane().add(btn_salir, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 160, 200, 100));

        btn_verificar.setText("Verificar Huella");
        btn_verificar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btn_verificar.setMargin(new java.awt.Insets(2, 14, 15, 14));
        btn_verificar.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        btn_verificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_verificarActionPerformed(evt);
            }
        });
        getContentPane().add(btn_verificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 40, 200, 100));

        jLabel1.setText("Empresa:");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, -1, -1));

        lbl_empresa.setText("empresa");
        getContentPane().add(lbl_empresa, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 10, -1, -1));
        getContentPane().add(lbl_consulta, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 170, 70, 70));

        jButton1.setText("jButton1");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 0, -1, -1));

        btn_consulta.setText("Consultar");
        btn_consulta.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btn_consulta.setMargin(new java.awt.Insets(2, 14, 15, 14));
        btn_consulta.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        btn_consulta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_consultaActionPerformed(evt);
            }
        });
        getContentPane().add(btn_consulta, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 160, 200, 100));

        jMenu1.setText("Clientes");

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F1, 0));
        jMenuItem1.setText("Alta Cliente");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_altaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_altaActionPerformed
        // TODO add your handling code here:
        //this.setVisible(false);
        //AltaClientes vista=new AltaClientes();
        this.setVisible(false);
        AltaClientes vista=new AltaClientes();
        vista.setVisible(true);
    
    }//GEN-LAST:event_btn_altaActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        //this.setVisible(false);
        this.setVisible(false);
        AltaClientes vista=new AltaClientes();
        vista.setVisible(true);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void btn_salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_salirActionPerformed
        // TODO add your handling code here:
  
        try {
      Thread t = new home.at_cerrar();
            t.start();
        } catch (Exception ex) {
            //Logger.getLogger(home.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println(ex.getMessage());
        }

               
     
        
 
 
    }//GEN-LAST:event_btn_salirActionPerformed

    public class at_cerrar extends Thread
    {
        public void run()
        {
            cerrar();        
        }


    }
    public void cerrar()
    {
        this.setCursor(new Cursor(WAIT_CURSOR));
            System.out.println("inicio tret");
            try
            {
                   if(consultasApi.CerrarSesion(usuario))
            {
                consultasBD.deleteUserLogout();
                 if(consultasBD.tablaVacia())
                {
                        this.setVisible(false);
                       Login vista=new Login();
                vista.setVisible(true);
                }
                
            }
                    else
                 {
                    JOptionPane.showMessageDialog(null, "Problemas al cerrar sesion", "Error",  JOptionPane.ERROR_MESSAGE );
                 }
            }catch(Exception e)
            {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Error",  JOptionPane.ERROR_MESSAGE );
            }
            finally{
                this.setCursor(new Cursor(DEFAULT_CURSOR));
            }
         
            
    }
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:

       //this.setVisible(false);
      
        ModificarAutorizados vh=null;
        vh.pantalla=0;
               vh= new ModificarAutorizados();
        vh.setVisible(true);
         this.setVisible(false);

    }//GEN-LAST:event_jButton3ActionPerformed

    private void btn_verificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_verificarActionPerformed
        // TODO add your handling code here:
        try{
            this.setVisible(false);
            VerificarHuella vh=new VerificarHuella();
            vh.setVisible(true);
        }catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error",  JOptionPane.ERROR_MESSAGE );
        }
        
        
    }//GEN-LAST:event_btn_verificarActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
      Documentos vdoc=null;
        vdoc.codigoMacro="PRUEBADOC";
                vdoc=new Documentos();
                vdoc.setVisible(true);
                
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btn_consultaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_consultaActionPerformed
        // TODO add your handling code here:
        Consultar vc=null;
        vc.pantalla=0;
               vc= new Consultar();
        vc.setVisible(true);
         this.setVisible(false);
    }//GEN-LAST:event_btn_consultaActionPerformed
   
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
            java.util.logging.Logger.getLogger(home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }
    //variables
    consultasApi consultasApi=new consultasApi();
    consultasBD consultasBD=new consultasBD();
    String usuario="";
    Integer id_empresa=0;
    String nom_empresa="";
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_alta;
    private javax.swing.JButton btn_consulta;
    private javax.swing.JButton btn_salir;
    private javax.swing.JButton btn_verificar;
    private javax.swing.JLabel img_clientes;
    private javax.swing.JLabel img_logout;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JLabel lbl_consulta;
    private javax.swing.JLabel lbl_empresa;
    private javax.swing.JLabel lbl_mod;
    private javax.swing.JLabel lbl_verificar;
    // End of variables declaration//GEN-END:variables
}
