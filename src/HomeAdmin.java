
import complementos.consultasApi;
import complementos.consultasBD;
import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dialog;
import java.awt.Frame;
import static java.awt.Frame.DEFAULT_CURSOR;
import static java.awt.Frame.WAIT_CURSOR;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Desarrollo
 */
public class HomeAdmin extends javax.swing.JFrame {

    /**
     * Creates new form HomeAdmin
     */
    consultasApi consultasApi=new consultasApi();
    consultasBD consultasBD=new consultasBD();
    String usuario="";
    Integer id_empresa=0;
    String nom_empresa="";
    public HomeAdmin() {
        initComponents();
        usuario=consultasBD.getUsuario();
          id_empresa=consultasBD.getIdEmpresa();
          nom_empresa=consultasBD.getNombreEmpresa();
         this.setLocationRelativeTo(null);
         rsscalelabel.RSScaleLabel.setScaleLabel(img_logout, "src/images/logout.png");
         
    }
private void ModalEmpresaActionPerformed() {
ModalEmpresa nv = new ModalEmpresa(this,true);
JDialog jd = new JDialog(nv, "", Dialog.ModalityType.DOCUMENT_MODAL);
nv.setVisible(true);
}
private void ModalUsuariosActionPerformed() {
ModalUsuarios nv = new ModalUsuarios(this,true);
JDialog jd = new JDialog(nv, "", Dialog.ModalityType.DOCUMENT_MODAL);
nv.setVisible(true);
}
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        img_logout = new javax.swing.JLabel();
        btn_salir = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        btn_agregarEmp = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Admin");
        setPreferredSize(new java.awt.Dimension(700, 300));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(img_logout, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 160, 70, 70));

        btn_salir.setText("Cerrar Sesion");
        btn_salir.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btn_salir.setMargin(new java.awt.Insets(2, 14, 15, 14));
        btn_salir.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        btn_salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_salirActionPerformed(evt);
            }
        });
        getContentPane().add(btn_salir, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 150, 200, 100));

        jButton1.setText("Alta Usuarios");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 20, 200, 100));

        btn_agregarEmp.setText("Alta Empresas");
        btn_agregarEmp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_agregarEmpActionPerformed(evt);
            }
        });
        getContentPane().add(btn_agregarEmp, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 200, 100));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_salirActionPerformed
        // TODO add your handling code here:
       try {
      Thread t = new HomeAdmin.at_cerrar();
            t.start();
        } catch (Exception ex) {
            //Logger.getLogger(home.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println(ex.getMessage());
        }

    }//GEN-LAST:event_btn_salirActionPerformed

    private void btn_agregarEmpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_agregarEmpActionPerformed
        // TODO add your handling code here:
        /*
        this.setVisible(false);
        AltaEmpresa vista=new AltaEmpresa();
        vista.setVisible(true);
        */
        ModalEmpresaActionPerformed();
    }//GEN-LAST:event_btn_agregarEmpActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        ModalUsuariosActionPerformed();
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(HomeAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HomeAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HomeAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HomeAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new HomeAdmin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_agregarEmp;
    private javax.swing.JButton btn_salir;
    private javax.swing.JLabel img_logout;
    private javax.swing.JButton jButton1;
    // End of variables declaration//GEN-END:variables
}
