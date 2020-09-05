
import com.digitalpersona.onetouch.DPFPDataPurpose;
import com.digitalpersona.onetouch.DPFPFeatureSet;
import com.digitalpersona.onetouch.DPFPGlobal;
import com.digitalpersona.onetouch.DPFPSample;
import com.digitalpersona.onetouch.DPFPTemplate;
import com.digitalpersona.onetouch.capture.DPFPCapture;
import com.digitalpersona.onetouch.capture.event.DPFPDataAdapter;
import com.digitalpersona.onetouch.capture.event.DPFPDataEvent;
import com.digitalpersona.onetouch.capture.event.DPFPErrorAdapter;
import com.digitalpersona.onetouch.capture.event.DPFPErrorEvent;
import com.digitalpersona.onetouch.capture.event.DPFPReaderStatusAdapter;
import com.digitalpersona.onetouch.capture.event.DPFPReaderStatusEvent;
import com.digitalpersona.onetouch.capture.event.DPFPSensorAdapter;
import com.digitalpersona.onetouch.capture.event.DPFPSensorEvent;
import com.digitalpersona.onetouch.processing.DPFPEnrollment;
import com.digitalpersona.onetouch.processing.DPFPFeatureExtraction;
import com.digitalpersona.onetouch.processing.DPFPImageQualityException;
import com.digitalpersona.onetouch.verification.DPFPVerification;
import com.digitalpersona.onetouch.verification.DPFPVerificationResult;
import complementos.apiAutorizados;
import complementos.consultasBD;
import java.awt.Image;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
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
public class RegistroHuella extends javax.swing.JFrame {

    /**
     * Creates new form RegistroHuella
     */
    //declarando variables para huella
    //Nos sirve para identificar al dispositivo
    private DPFPCapture Lector = DPFPGlobal.getCaptureFactory().createCapture();
    //Nos sirve para leer a modo de enrrolar, y crear una plantilla nueva, a base de 4 huellas.
    private DPFPEnrollment Reclutador = DPFPGlobal.getEnrollmentFactory().createEnrollment();
    //Nos sirve para leer a modo de verificar o comparar, a base de una plantilla creada anteriormente
    private DPFPVerification Verificador = DPFPGlobal.getVerificationFactory().createVerification();
    //La plantilla, nueva o rescatada
    private DPFPTemplate template1;
    private DPFPTemplate template2;
    private DPFPTemplate template3;
    private DPFPTemplate template4;
    private DPFPTemplate template5;
    private DPFPTemplate tempAux;
    //A modo de CONSTANTE para crear plantillas
    public String TEMPLATE_PROPERTY = "template";
    //Para leer la huella, y definirla como un enrrolamiento
    public DPFPFeatureSet featureSetInscripcion;
    //Para leer la huella, y definirla como una verificación
    public DPFPFeatureSet featureSetVerificacion;

    public static String param;
    public File fichero=null;
    apiAutorizados apiautorizados=new apiAutorizados();
    //metodos leer huella eventos
     protected void Iniciar(){
   Lector.addDataListener(new DPFPDataAdapter() {
    @Override public void dataAcquired(final DPFPDataEvent e) {
    SwingUtilities.invokeLater(new Runnable() {	public void run() {
    EnviarTexto("La Huella Digital ha sido Capturada");
    ProcesarCaptura(e.getSample());
    }});}
   });

   Lector.addReaderStatusListener(new DPFPReaderStatusAdapter() {
    @Override public void readerConnected(final DPFPReaderStatusEvent e) {
    SwingUtilities.invokeLater(new Runnable() {	public void run() {
    EnviarTexto("El Sensor de Huella Digital esta Activado o Conectado");
    }});}
    @Override public void readerDisconnected(final DPFPReaderStatusEvent e) {
    SwingUtilities.invokeLater(new Runnable() {	public void run() {
    EnviarTexto("El Sensor de Huella Digital esta Desactivado o no Conectado");
    }});}
   });

   Lector.addSensorListener(new DPFPSensorAdapter() {
    @Override public void fingerTouched(final DPFPSensorEvent e) {
    SwingUtilities.invokeLater(new Runnable() {	public void run() {
   //EnviarTexto("El dedo ha sido colocado sobre el Lector de Huella");
   // EnviarTexto("Realizando lectura dactilar...");
    }});}
    @Override public void fingerGone(final DPFPSensorEvent e) {
    SwingUtilities.invokeLater(new Runnable() {	public void run() {
    //EnviarTexto("El dedo ha sido quitado del Lector de Huella");
    }});}
   });

   Lector.addErrorListener(new DPFPErrorAdapter(){
    public void errorReader(final DPFPErrorEvent e){
    SwingUtilities.invokeLater(new Runnable() {  public void run() {
    EnviarTexto("Error: "+e.getError());
    }});}
   });
}
     
     public void EnviarTexto(String mensaje){
  txt_Area.append(mensaje + "\n");
}
 public void start(){
  Lector.startCapture();
  EnviarTexto("Utilizando lector de huella dactilar");
  EstadoHuellas();
}
public void stop(){
  Reclutador.clear();
  Lector.stopCapture();
  EnviarTexto("Lector detenido");
}
 public void EstadoHuellas(){
  EnviarTexto("Muestra de huellas necesarias para guardar plantilla: " + Reclutador.getFeaturesNeeded());
}


    public DPFPTemplate getTemplate() {
        return template1;
    }

    public DPFPTemplate getTemplate2() {
        return template2;
    }

    public void setTemplate2(DPFPTemplate template2) {
         DPFPTemplate antigua = this.template2;
  this.template2 = template2;
  firePropertyChange(TEMPLATE_PROPERTY, antigua, template2);
    }

    public DPFPTemplate getTemplate3() {
        return template3;
    }

    public void setTemplate3(DPFPTemplate template3) {
          DPFPTemplate antigua = this.template3;
  this.template3 = template3;
  firePropertyChange(TEMPLATE_PROPERTY, antigua, template3);
    }

    public DPFPTemplate getTemplate4() {
        return template4;
    }

    public void setTemplate4(DPFPTemplate template4) {
          DPFPTemplate antigua = this.template4;
  this.template4 = template4;
  firePropertyChange(TEMPLATE_PROPERTY, antigua, template4);
    }

    public DPFPTemplate getTemplate5() {
        return template5;
    }

    public void setTemplate5(DPFPTemplate template5) {
         DPFPTemplate antigua = this.template5;
  this.template5 = template5;
  firePropertyChange(TEMPLATE_PROPERTY, antigua, template5);
    }

    public void setTemplate(DPFPTemplate template) {
  DPFPTemplate antigua = this.template1;
  this.template1 = template;
  firePropertyChange(TEMPLATE_PROPERTY, antigua, template);
}
 
 public DPFPFeatureSet extraerCaracteristicas(DPFPSample muestra, DPFPDataPurpose motivo){
  DPFPFeatureExtraction extractor = DPFPGlobal.getFeatureExtractionFactory().createFeatureExtraction();
  try{
    return extractor.createFeatureSet(muestra, motivo);
  } catch (DPFPImageQualityException e) {
    System.out.println(e.getMessage());
    return null;
  }
}
 
 public Image CrearImagenHuella(DPFPSample muestra){
  return DPFPGlobal.getSampleConversionFactory().createImage(muestra);
}
public void DibujarHuella(Image image){
    if(rb1.isSelected())
    {
          lbl_huellaimg1.setIcon(
    new ImageIcon(
      image.getScaledInstance(
        lbl_huellaimg1.getWidth(),
        lbl_huellaimg1.getHeight(),
        Image.SCALE_DEFAULT
      )
    )
  );
    }
    if(rb2.isSelected())
    {
          lbl_huellaimg2.setIcon(
    new ImageIcon(
      image.getScaledInstance(
        lbl_huellaimg2.getWidth(),
        lbl_huellaimg2.getHeight(),
        Image.SCALE_DEFAULT
      )
    )
  );
    }
        if(rb2.isSelected())
    {
          lbl_huellaimg2.setIcon(
    new ImageIcon(
      image.getScaledInstance(
        lbl_huellaimg2.getWidth(),
        lbl_huellaimg2.getHeight(),
        Image.SCALE_DEFAULT
      )
    )
  );
    }
            if(rb3.isSelected())
    {
          lbl_huellaimg3.setIcon(
    new ImageIcon(
      image.getScaledInstance(
        lbl_huellaimg3.getWidth(),
        lbl_huellaimg3.getHeight(),
        Image.SCALE_DEFAULT
      )
    )
  );
    }
                if(rb4.isSelected())
    {
          lbl_huellaimg4.setIcon(
    new ImageIcon(
      image.getScaledInstance(
        lbl_huellaimg4.getWidth(),
        lbl_huellaimg4.getHeight(),
        Image.SCALE_DEFAULT
      )
    )
  );
    }
                    if(rb5.isSelected())
    {
          lbl_huellaimg5.setIcon(
    new ImageIcon(
      image.getScaledInstance(
        lbl_huellaimg5.getWidth(),
        lbl_huellaimg5.getHeight(),
        Image.SCALE_DEFAULT
      )
    )
  );
    }
    

}

    public  void ProcesarCaptura(DPFPSample sample)
{
    // Procesar la muestra de la huella y crear un conjunto de características con el propósito de inscripción.
    featureSetInscripcion = extraerCaracteristicas(sample, DPFPDataPurpose.DATA_PURPOSE_ENROLLMENT);

    // Procesar la muestra de la huella y crear un conjunto de características con el propósito de verificacion.
    featureSetVerificacion = extraerCaracteristicas(sample, DPFPDataPurpose.DATA_PURPOSE_VERIFICATION);

    // Comprobar la calidad de la muestra de la huella y lo añade a su reclutador si es bueno
    if (featureSetInscripcion != null)
        try{
        System.out.println("Las Caracteristicas de la Huella han sido creada");
        Reclutador.addFeatures(featureSetInscripcion);// Agregar las caracteristicas de la huella a la plantilla a crear
        identificar();
        }catch (DPFPImageQualityException ex) {
        System.err.println("Error: "+ex.getMessage());
        }

        finally {
        EstadoHuellas();
        // Comprueba si la plantilla se ha creado.
           switch(Reclutador.getTemplateStatus())
           {
               case TEMPLATE_STATUS_READY:	// informe de éxito y detiene  la captura de huellas
               
               if(rb1.isSelected())
               {
                   setTemplate(Reclutador.getTemplate());
               }
               if(rb2.isSelected())
               {
                   setTemplate2(Reclutador.getTemplate());
               }
               if(rb3.isSelected())
               {
                   setTemplate3(Reclutador.getTemplate());
               }
               if(rb4.isSelected())
               {
                   setTemplate4(Reclutador.getTemplate());
               }
               if(rb5.isSelected())
               {
                   setTemplate5(Reclutador.getTemplate());
               }
                // Dibuja la huella dactilar capturada.
                Image image=CrearImagenHuella(sample);
                DibujarHuella(image);
               stop();
               EnviarTexto("La Plantilla de la Huella ha Sido Creada, ya puede Guardarla");
               break;

               case TEMPLATE_STATUS_FAILED: // informe de fallas y reiniciar la captura de huellas
               //Reclutador.clear();
               stop();
               JOptionPane.showMessageDialog(this, "La Plantilla de la Huella no pudo ser creada, Repita el Proceso", "Inscripcion de Huellas Dactilares", JOptionPane.ERROR_MESSAGE);
               start();
               break;
           }
                }
}
    public void identificar()
    {
        
    }
    public RegistroHuella() {
           try{
                for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                    if ("Nimbus".equals(info.getName())) {
                        UIManager.setLookAndFeel(info.getClassName());
                    break;
                        }
                    }
                }catch(Exception e){
                    JOptionPane.showMessageDialog(null,
                    "No es posible cambiar tema visual",
                    "LookAndFeel inválido",
                    JOptionPane.ERROR_MESSAGE);
                }
        initComponents();
        System.out.println(param);
         this.setLocationRelativeTo(null);
         rsscalelabel.RSScaleLabel.setScaleLabel(lbl_foto, "src/images/camera.png");
         rsscalelabel.RSScaleLabel.setScaleLabel(lbl_huella1, "src/images/huella-dactilar.png");
         rsscalelabel.RSScaleLabel.setScaleLabel(lbl_huella2, "src/images/huella-dactilar.png");
         rsscalelabel.RSScaleLabel.setScaleLabel(lbl_huella3, "src/images/huella-dactilar.png");
         rsscalelabel.RSScaleLabel.setScaleLabel(lbl_huella4, "src/images/huella-dactilar.png");
         rsscalelabel.RSScaleLabel.setScaleLabel(lbl_huella5, "src/images/huella-dactilar.png");
         //jPanel1.setVisible(false);
         //stop();
             
           //
           Iniciar();
           start();
    }
    public void desactivarComp()
    {
        txt_comentarios.setEnabled(false);
        txt_materno.setEnabled(false);
        txt_paterno.setEnabled(false);
        txt_nombre.setEnabled(false);
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        rb_group = new javax.swing.ButtonGroup();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txt_nombre = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txt_paterno = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txt_materno = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        txt_comentarios = new javax.swing.JTextArea();
        jLabel9 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        lbl_foto = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        lbl_huellaimg5 = new javax.swing.JLabel();
        lbl_huellaimg3 = new javax.swing.JLabel();
        lbl_huellaimg2 = new javax.swing.JLabel();
        lbl_huellaimg4 = new javax.swing.JLabel();
        lbl_huellaimg1 = new javax.swing.JLabel();
        lbl_huella5 = new javax.swing.JLabel();
        lbl_huella4 = new javax.swing.JLabel();
        lbl_huella3 = new javax.swing.JLabel();
        lbl_huella2 = new javax.swing.JLabel();
        lbl_huella1 = new javax.swing.JLabel();
        rb5 = new javax.swing.JRadioButton();
        rb1 = new javax.swing.JRadioButton();
        rb2 = new javax.swing.JRadioButton();
        rb3 = new javax.swing.JRadioButton();
        rb4 = new javax.swing.JRadioButton();
        jPanel6 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txt_Area = new javax.swing.JTextArea();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(600, 500));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setPreferredSize(new java.awt.Dimension(0, 200));
        jPanel2.setVerifyInputWhenFocusTarget(false);
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("Nombre:");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 20, -1, -1));
        jPanel2.add(txt_nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 20, 150, -1));

        jLabel2.setText("Apellido Paterno:");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 20, -1, -1));
        jPanel2.add(txt_paterno, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 20, 150, -1));

        jLabel3.setText("Apellido Materno:");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, -1, -1));
        jPanel2.add(txt_materno, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 60, 150, -1));

        txt_comentarios.setColumns(20);
        txt_comentarios.setRows(5);
        jScrollPane2.setViewportView(txt_comentarios);

        jPanel2.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 60, 230, 60));

        jLabel9.setText("Comentarios:");
        jPanel2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 60, -1, -1));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 600, 150));

        jPanel1.setPreferredSize(new java.awt.Dimension(0, 300));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton3.setText("foto");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 140, 60, 40));

        jPanel8.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbl_foto, javax.swing.GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbl_foto, javax.swing.GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 20, 82, 82));

        jButton1.setText("ok");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 90, 70, 40));

        jButton2.setText("cancel");
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 140, 70, 40));
        jPanel1.add(lbl_huellaimg5, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 100, 50, 70));
        jPanel1.add(lbl_huellaimg3, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 100, 50, 70));
        jPanel1.add(lbl_huellaimg2, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 100, 50, 70));
        jPanel1.add(lbl_huellaimg4, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 100, 50, 70));
        jPanel1.add(lbl_huellaimg1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 50, 70));
        jPanel1.add(lbl_huella5, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 40, 50, 50));
        jPanel1.add(lbl_huella4, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 40, 50, 50));
        jPanel1.add(lbl_huella3, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 40, 50, 50));
        jPanel1.add(lbl_huella2, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 40, 50, 50));
        jPanel1.add(lbl_huella1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 50, 50));

        rb_group.add(rb5);
        rb5.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                rb5ItemStateChanged(evt);
            }
        });
        jPanel1.add(rb5, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 170, -1, -1));

        rb_group.add(rb1);
        rb1.setSelected(true);
        rb1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                rb1ItemStateChanged(evt);
            }
        });
        rb1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rb1ActionPerformed(evt);
            }
        });
        jPanel1.add(rb1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 170, -1, -1));

        rb_group.add(rb2);
        rb2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                rb2ItemStateChanged(evt);
            }
        });
        jPanel1.add(rb2, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 170, -1, -1));

        rb_group.add(rb3);
        rb3.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                rb3ItemStateChanged(evt);
            }
        });
        jPanel1.add(rb3, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 170, -1, -1));

        rb_group.add(rb4);
        rb4.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                rb4ItemStateChanged(evt);
            }
        });
        jPanel1.add(rb4, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 170, -1, -1));

        jPanel6.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 46, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 66, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 100, 50, 70));

        jPanel5.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 46, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 66, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 100, 50, 70));

        jPanel4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 46, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 66, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 100, 50, 70));

        jPanel3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 46, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 66, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 100, 50, 70));

        jPanel7.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 46, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 66, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 50, 70));

        jScrollPane1.setEnabled(false);

        txt_Area.setColumns(20);
        txt_Area.setRows(5);
        txt_Area.setEnabled(false);
        jScrollPane1.setViewportView(txt_Area);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 200, 350, 100));

        jButton4.setText("set huella");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jButton5.setText("get huella");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 0, -1, -1));

        jButton6.setText("huella bd");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 0, -1, -1));

        jButton7.setText("set huella bd");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 0, -1, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 151, 600, 450));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
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
    }//GEN-LAST:event_jButton3ActionPerformed

    private void rb1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_rb1ItemStateChanged
        // TODO add your handling code here:
        txt_Area.setText("");
                if(rb1.isSelected())
        {
            System.out.println("cambio activo 1");
             
             if(template1==null)
             {
                 start();
             }
             else
             {
                 cuadroDialogo("Huella ya capturada");
             }
        }
        else
        {
             System.out.println("cambio desactivado 1");
            stop();

        }
    }//GEN-LAST:event_rb1ItemStateChanged

    private void rb2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_rb2ItemStateChanged
        // TODO add your handling code here:
        txt_Area.setText("");
                if(rb2.isSelected())
        {
            if(template2==null)
             {
                 start();
             }
             else
             {
                 cuadroDialogo("Huella ya capturada");
             }
        }
        else
        {
             System.out.println("cambio desactivado 2");
            stop();
        }
    }//GEN-LAST:event_rb2ItemStateChanged

    private void rb3ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_rb3ItemStateChanged
        // TODO add your handling code here:
        txt_Area.setText("");
                if(rb3.isSelected())
        {
            if(template3==null)
             {
                 start();
             }
             else
             {
                 cuadroDialogo("Huella ya capturada");
             }
        }
        else
        {
             System.out.println("desactivado 3");
            stop();
        }
    }//GEN-LAST:event_rb3ItemStateChanged

    private void rb4ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_rb4ItemStateChanged
        // TODO add your handling code here:
        txt_Area.setText("");
                if(rb4.isSelected())
        {
            if(template4==null)
             {
                 start();
             }
             else
             {
                 cuadroDialogo("Huella ya capturada");
             }
        }
        else
        {
             System.out.println("desactivado 4");
            stop();
        }
    }//GEN-LAST:event_rb4ItemStateChanged

    private void rb5ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_rb5ItemStateChanged
        // TODO add your handling code here:
        txt_Area.setText("");
                if(rb5.isSelected())
        {
            if(template5==null)
             {
                 start();
             }
             else
             {
                 cuadroDialogo("Huella ya capturada");
             }
        }
        else
        {
             System.out.println("desactivado 5");
            stop();
        }
    }//GEN-LAST:event_rb5ItemStateChanged

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
     //System.out.println("no campturo huella "+template1);
     if(validarCampos())
     {
         if(validarDoc())
        {
            
       
             try {
                 Thread t = new RegistroHuella.atUploading();
                 t.start();
                 t.join();
                 t = new RegistroHuella.atInsertAutorizados();
                 t.start();
             } catch (InterruptedException ex) {
                 Logger.getLogger(RegistroHuella.class.getName()).log(Level.SEVERE, null, ex);
             }
             
        }
        else
        {
            cuadroDialogo("Falta registrar huella ");
        } 
     }
    }//GEN-LAST:event_jButton1ActionPerformed
consultasBD consultas=new consultasBD();
    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed

     
            // TODO add your handling code here:
            //huella en server
            //pruebaGuardarSV();
            //apiautorizados.download();
            //subeArchivobinHuella();
    File file = new File("src/huellaserver.bin");
    byte[] bArray = readFileToByteArray(file);
    DPFPTemplate referenceTemplate = DPFPGlobal.getTemplateFactory().createTemplate(bArray);
DPFPVerification matcher = DPFPGlobal.getVerificationFactory().createVerification();
        matcher.setFARRequested(DPFPVerification.MEDIUM_SECURITY_FAR);
        DPFPVerificationResult result = matcher.verify(featureSetVerificacion, referenceTemplate);
        if (result.isVerified()) { 
    System.out.println("coinsiden");
}
else
{
    System.out.println("no coinside");
}
    }//GEN-LAST:event_jButton4ActionPerformed
  public void subeArchivobinHuella()
  {
         try (FileOutputStream fileOuputStream = new FileOutputStream("src/huellatmp.bin")){
    fileOuputStream.write(template1.serialize());
    File file = new File("src/huellatmp.bin");
    byte[] bArray = readFileToByteArray(file);
    DPFPTemplate referenceTemplate = DPFPGlobal.getTemplateFactory().createTemplate(bArray);
       System.out.println(template1.serialize());
       System.out.println(referenceTemplate.serialize());
       System.out.println(file.getAbsolutePath()+" "+file.getName());
       DPFPVerification matcher = DPFPGlobal.getVerificationFactory().createVerification();
        matcher.setFARRequested(DPFPVerification.MEDIUM_SECURITY_FAR);
        DPFPVerificationResult result = matcher.verify(featureSetVerificacion, referenceTemplate);
        if (result.isVerified()) { 
    System.out.println("coinsiden");
}
else
{
    System.out.println("no coinside");
}
    
    apiautorizados.subirImg(file);
 }      catch (FileNotFoundException ex) {
            Logger.getLogger(RegistroHuella.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(RegistroHuella.class.getName()).log(Level.SEVERE, null, ex);
        }
  }
    private static byte[] readFileToByteArray(File file){
    FileInputStream fis = null;
    // Creating a byte array using the length of the file
    // file.length returns long which is cast to int
    byte[] bArray = new byte[(int) file.length()];
    try{
      fis = new FileInputStream(file);
      fis.read(bArray);
      fis.close();                   
    }catch(IOException ioExp){
      ioExp.printStackTrace();
    }
    return bArray;
  }
    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        //pruebaBlobBD();
        pruebaBlobServer();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
          pruebaBlobBD();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        pruebaGuardarBD();
    }//GEN-LAST:event_jButton7ActionPerformed

    private void rb1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rb1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rb1ActionPerformed

    public void pruebaGuardarSV()
    {
        
          ByteArrayInputStream datosHuella = new ByteArrayInputStream(template1.serialize());
            System.out.println("array subido "+datosHuella);
            System.out.println("array subido "+template1.serialize());
            JSONObject objaux = new JSONObject();
            objaux.put("data", datosHuella);
            Integer tamañoHuella=template1.serialize().length;
            objaux.put("size",tamañoHuella);
            apiautorizados.pruebas(objaux); 
    }
    public void pruebaGuardarBD()
    {
            ByteArrayInputStream datosHuella = new ByteArrayInputStream(template1.serialize());
            Integer tamañoHuella=template1.serialize().length;
            consultas.insertHuella(datosHuella, tamañoHuella);
            System.out.println(template1.serialize());
            System.out.println(datosHuella);
    }
    public void pruebaBlobBD()
    {
        
        setTempAux(consultas.getHuella());
        System.out.println(tempAux.serialize());
        DPFPVerificationResult result = Verificador.verify(featureSetVerificacion, getTempAux());
        if (result.isVerified()){
            System.out.println("si coinside");
        }
        else
        {
            System.out.println("no coinside");
        }
    }

    public void pruebaBlobServer()
    {
                JSONArray info=new JSONArray();
        apiAutorizados apiautorizados=new apiAutorizados();
        //info=apiautorizados.pruebasget().getJSONArray("info");
        Integer tamaño=0;
         ByteArrayInputStream datosHuella=null;
        //ByteArrayInputStream datosHuella = new ByteArrayInputStream(template1.serialize());
        for (int i = 0; i < info.length(); i++) {   
            try {
                   JSONObject datos = info.getJSONObject(i);
                   tamaño=datos.getInt("id_autorizados");
                   //System.out.println(+i+" "+datos.get("datos"));
                   JSONObject data=datos.getJSONObject("datos");
                   System.out.println(data.getJSONArray("data"));
                   JSONArray jsonArray = data.getJSONArray("data");
                      
                   datosHuella = new ByteArrayInputStream(jsonArray.toString().getBytes());
                   System.out.println(datosHuella);
               
            } catch (JSONException e) {
                System.out.println(e.getMessage());
            }
   
        }
        System.out.println(tamaño);
        //consultas.insertHuella(datosHuella, tamaño);
        /*
        System.out.println("template "+template1.serialize());
        System.out.println("bd "+tempAux.serialize());
        DPFPVerification matcher = DPFPGlobal.getVerificationFactory().createVerification();
        matcher.setFARRequested(DPFPVerification.MEDIUM_SECURITY_FAR);
        DPFPVerificationResult result = matcher.verify(featureSetVerificacion, tempAux);
if (result.isVerified()) { 
    System.out.println("coinsiden");
}
else
{
    System.out.println("no coinside");
}
    
        System.out.println("res "+result);
    */
    }
    public DPFPTemplate getTempAux() {
        return tempAux;
    }

    public void setTempAux(DPFPTemplate tempAux) {
              DPFPTemplate antigua = this.tempAux;
  this.tempAux = tempAux;
  firePropertyChange(TEMPLATE_PROPERTY, antigua, tempAux);
    }
   public class atUploading extends Thread
     {
         public void run()
         {
             try {
                 apiautorizados.subirImg(fichero);
                 //apiautorizados.subirImg();
             } catch (IOException ex) {
                 Logger.getLogger(RegistroHuella.class.getName()).log(Level.SEVERE, null, ex);
             }
         }
     }
    public class atInsertAutorizados extends Thread
     {
         public void run()
         {
             apiautorizados.insertAutorizados(crearJson());
         }
     }
    public void guardandoHuella()
    {
         if(template1!=null)
        {
            //estatus 1=huella 2=documento
            JSONObject objaux = new JSONObject();
            try {
            objaux.put("documento",template1.serialize());
            objaux.put("estatus",1);
            objaux.put("descripcion","huella dactilar 1");
            //documentos.put(objaux);
            }
            catch (JSONException e)
            {
             System.err.println("error al crear JSON:"+e.getMessage());  
            }
        }
        if(template2!=null)
        {
            JSONObject objaux = new JSONObject();
            try {
            objaux.put("documento",template2.serialize());
            objaux.put("estatus",1);
            objaux.put("descripcion","huella dactilar 2");
            //documentos.put(objaux);
            }
            catch (JSONException e)
            {
             System.err.println("error al crear JSON:"+e.getMessage());  
            }
        }
        if(template3!=null)
        {
            JSONObject objaux = new JSONObject();
            try {
            objaux.put("documento",template3.serialize());
            objaux.put("estatus",1);
            objaux.put("descripcion","huella dactilar 3");
            //documentos.put(objaux);
            }
            catch (JSONException e)
            {
             System.err.println("error al crear JSON:"+e.getMessage());  
            }
        }
        if(template4!=null)
        {
            JSONObject objaux = new JSONObject();
            try {
            objaux.put("documento",template4.serialize());
            objaux.put("estatus",1);
            objaux.put("descripcion","huella dactilar 4");
            //documentos.put(objaux);
            }
            catch (JSONException e)
            {
             System.err.println("error al crear JSON:"+e.getMessage());  
            }
        }
        if(template5!=null)
        {
            JSONObject objaux = new JSONObject();
            try {
            objaux.put("documento",template5.serialize());
            objaux.put("estatus",1);
            objaux.put("descripcion","huella dactilar 5");
            //documentos.put(objaux);
            }
            catch (JSONException e)
            {
             System.err.println("error al crear JSON:"+e.getMessage());  
            }
        }
    }
    public JSONObject crearJson()
    {
        JSONObject general = new JSONObject();
        JSONArray documentos = new JSONArray();
           JSONObject autorizados = new JSONObject();
        try {
            autorizados.put("nombre",txt_nombre.getText().trim());
            autorizados.put("a_paterno", txt_paterno.getText().trim());
            autorizados.put("a_materno",txt_materno.getText().trim());
            autorizados.put("comentarios", txt_comentarios.getText().trim());
            autorizados.put("codigoMacro",param);
            }
        catch (JSONException e)
            {
             System.err.println("error al crear JSON:"+e.getMessage());  
            }
       
        if(fichero!=null)
        {
            
        
            try {
              
             
            JSONObject objaux = new JSONObject();
            objaux.put("nombre",fichero.getName());

            documentos.put(objaux);
            }
            catch (JSONException e)
            {
             System.err.println("error al crear JSON:"+e.getMessage());  
            }
              
        }
           general.put("autorizado",autorizados);
           general.put("documentos",documentos);
           System.out.println(general);
        return general;
    }
    public boolean validarCampos()
    {
        boolean val=false;
        if(txt_nombre.getText().equalsIgnoreCase(""))
        {
            cuadroDialogo("ingrese nombre");
            val=false;
        }
        else
        {
            if(txt_paterno.getText().equalsIgnoreCase(""))
            {
                cuadroDialogo("ingrese Apellido paterno");
                val=false;
            }
            else
            {
                if(txt_materno.getText().equalsIgnoreCase(""))
                {
                    cuadroDialogo("Ingrese Apellido Materno");
                    val=false;
                }
                else
                {
                    val=true;
                }
            }
        }
        
        return val;
        
    }
    public boolean validarDoc()
    {
        boolean val=false;
        if(template1!=null || template2!=null || template3!=null || template4!=null || template5!=null )
        {
            val=true;
        }
        else
        {
            val=false;
        }
        return val;
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
            java.util.logging.Logger.getLogger(RegistroHuella.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RegistroHuella.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RegistroHuella.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RegistroHuella.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                      new RegistroHuella().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lbl_foto;
    private javax.swing.JLabel lbl_huella1;
    private javax.swing.JLabel lbl_huella2;
    private javax.swing.JLabel lbl_huella3;
    private javax.swing.JLabel lbl_huella4;
    private javax.swing.JLabel lbl_huella5;
    private javax.swing.JLabel lbl_huellaimg1;
    private javax.swing.JLabel lbl_huellaimg2;
    private javax.swing.JLabel lbl_huellaimg3;
    private javax.swing.JLabel lbl_huellaimg4;
    private javax.swing.JLabel lbl_huellaimg5;
    private javax.swing.JRadioButton rb1;
    private javax.swing.JRadioButton rb2;
    private javax.swing.JRadioButton rb3;
    private javax.swing.JRadioButton rb4;
    private javax.swing.JRadioButton rb5;
    private javax.swing.ButtonGroup rb_group;
    private javax.swing.JTextArea txt_Area;
    private javax.swing.JTextArea txt_comentarios;
    private javax.swing.JTextField txt_materno;
    private javax.swing.JTextField txt_nombre;
    private javax.swing.JTextField txt_paterno;
    // End of variables declaration//GEN-END:variables
}
