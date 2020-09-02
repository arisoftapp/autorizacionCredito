
import com.sun.xml.internal.messaging.saaj.packaging.mime.internet.ContentDisposition;
import static com.sun.xml.internal.ws.api.message.Packet.Status.Request;

import complementos.apiAutorizados;
import complementos.consultasApi;
import complementos.consultasBD;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.IOException;
import java.io.InputStream;
import javax.swing.JFileChooser;
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
         //rsscalelabel.RSScaleLabel.setScaleLabel(img_clientes,"");
        
         rsscalelabel.RSScaleLabel.setScaleLabel(img_logout, "src/images/logout.png");
          usuario=consultasBD.getUsuario();
          System.out.println(usuario);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        img_clientes = new javax.swing.JLabel();
        img_logout = new javax.swing.JLabel();
        btn_alta = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        btn_cerrarsesion = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(700, 500));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(img_clientes, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 50, 80, 80));
        getContentPane().add(img_logout, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 170, 80, 80));

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
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 40, 200, 100));

        jButton2.setText("jButton1");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(475, 40, 200, 100));

        btn_cerrarsesion.setText("Cerrar Sesion");
        btn_cerrarsesion.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btn_cerrarsesion.setMargin(new java.awt.Insets(2, 14, 15, 14));
        btn_cerrarsesion.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        btn_cerrarsesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cerrarsesionActionPerformed(evt);
            }
        });
        getContentPane().add(btn_cerrarsesion, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 160, 200, 100));

        jButton1.setText("jButton1");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

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
    
        AltaClientes vista=new AltaClientes();
        vista.setVisible(true);
    
    }//GEN-LAST:event_btn_altaActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        //this.setVisible(false);
        AltaClientes vista=new AltaClientes();
        vista.setVisible(true);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void btn_cerrarsesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cerrarsesionActionPerformed
        // TODO add your handling code here:
        Thread t = new home.at_cerrar();
            t.start();
        try {
            t.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(home.class.getName()).log(Level.SEVERE, null, ex);
        }
                System.out.println("despues de tret");
                if(consultasBD.tablaVacia())
                {
                        this.setVisible(false);
                       Login vista=new Login();
                vista.setVisible(true);
                }
     
        
 
 
    }//GEN-LAST:event_btn_cerrarsesionActionPerformed

    public class at_cerrar extends Thread
    {
        public void run()
        {
            System.out.println("inicio tret");
            if(consultasApi.CerrarSesion(usuario))
            {
                consultasBD.deleteUserLogout();
            }
            
        }


    }
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:

       //this.setVisible(false);

    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        //this.setVisible(false);
  
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
                JSONArray info=new JSONArray();
        apiAutorizados apiautorizados=new apiAutorizados();
        info=apiautorizados.pruebasget().getJSONArray("info");
        
        for (int i = 0; i < info.length(); i++) {
    JSONObject datos = info.getJSONObject(i);
    //System.out.println(+i+" "+datos.get("datos"));
    JSONObject d=new JSONObject();
    d=datos.getJSONObject("datos");
   
    System.out.println(" "+d.get("data"));
        
        
    }//GEN-LAST:event_jButton1ActionPerformed
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

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_alta;
    private javax.swing.JButton btn_cerrarsesion;
    private javax.swing.JLabel img_clientes;
    private javax.swing.JLabel img_logout;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    // End of variables declaration//GEN-END:variables
}
