
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
import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
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
public class VerificarHuella extends javax.swing.JFrame {

    /**
     * Creates new form VerificarHuella
     */
    //variables
    JSONObject json=new JSONObject();
    JSONObject autorizado=new JSONObject();
    apiAutorizados apiautorizados=new apiAutorizados();
    //Nos sirve para identificar al dispositivo
    private DPFPCapture Lector = DPFPGlobal.getCaptureFactory().createCapture();
    //Nos sirve para leer a modo de enrrolar, y crear una plantilla nueva, a base de 4 huellas.
    private DPFPEnrollment Reclutador = DPFPGlobal.getEnrollmentFactory().createEnrollment();
    //Nos sirve para leer a modo de verificar o comparar, a base de una plantilla creada anteriormente
    private DPFPVerification Verificador = DPFPGlobal.getVerificationFactory().createVerification();
    //La plantilla, nueva o rescatada
    private DPFPTemplate template;
    //A modo de CONSTANTE para crear plantillas
    public String TEMPLATE_PROPERTY = "template";
    //Para leer la huella, y definirla como un enrrolamiento
    public DPFPFeatureSet featureSetInscripcion;
    //Para leer la huella, y definirla como una verificación
    public DPFPFeatureSet featureSetVerificacion;
         protected void Iniciar(){
   Lector.addDataListener(new DPFPDataAdapter() {
    @Override public void dataAcquired(final DPFPDataEvent e) {
    SwingUtilities.invokeLater(new Runnable() {	public void run() {
    EnviarTexto("La Huella Digital ha sido Capturada");
    if(txt_codigo.getText().equalsIgnoreCase(""))
    {
        cuadroDialogo("Ingrese codigo MacroPro");
    }
    else
    {
        ProcesarCaptura(e.getSample());
    }
    
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
        return template;
    }

    public void setTemplate(DPFPTemplate template) {
         DPFPTemplate antigua = this.template;
  this.template = template;
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
lbl_huella.setIcon(
    new ImageIcon(
      image.getScaledInstance(
        lbl_huella.getWidth(),
        lbl_huella.getHeight(),
        Image.SCALE_DEFAULT
      )
    )
  );
}

    public  void ProcesarCaptura(DPFPSample sample)
{

    // Procesar la muestra de la huella y crear un conjunto de características con el propósito de verificacion.
    featureSetVerificacion = extraerCaracteristicas(sample, DPFPDataPurpose.DATA_PURPOSE_VERIFICATION);
         boolean validarHuella=false;
    // Comprobar la calidad de la muestra de la huella y lo añade a su reclutador si es bueno
    if (featureSetVerificacion != null)

        try{
          
        System.out.println("Las Caracteristicas de la Huella han sido creada");
        Reclutador.addFeatures(featureSetVerificacion);// Agregar las caracteristicas de la huella a la plantilla a crear
        setTemplate(Reclutador.getTemplate());
        Reclutador.clear();
        Image image=CrearImagenHuella(sample);
        DibujarHuella(image);
        json=apiautorizados.getRutasHuella(txt_codigo.getText().toString().trim());
            System.out.println(json);
            if(json!=null)
            {
                   JSONArray jArray = json.getJSONArray("respuesta");
            for(int i=0;i<jArray.length();i++)
            {
                JSONObject objeto = jArray.getJSONObject(i);
                System.out.println(objeto.getString("ruta"));
                apiautorizados.downloadHuella(objeto.getString("ruta"));
                
                File file = new File("src/tmpDescargas/"+objeto.getString("ruta"));
                byte[] bArray = readFileToByteArray(file);
                DPFPTemplate referenceTemplate = DPFPGlobal.getTemplateFactory().createTemplate(bArray);
                DPFPVerification matcher = DPFPGlobal.getVerificationFactory().createVerification();
                matcher.setFARRequested(DPFPVerification.MEDIUM_SECURITY_FAR);
                DPFPVerificationResult result = matcher.verify(featureSetVerificacion, referenceTemplate);
                if (result.isVerified()) { 
                    i=jArray.length();
                    System.out.println("coinsiden");
                    //System.out.println(obj.getString("nombre"));
                    String nomAuto=objeto.getString("nombre")+" "+objeto.getString("a_paterno")+" "+objeto.getString("a_materno");
                    txt_autorizado.setText(nomAuto);
                    if(objeto.getInt("valFoto")==1)
                    {
                        apiautorizados.downloadFoto(objeto.getString("foto"));
                        rsscalelabel.RSScaleLabel.setScaleLabel(lbl_foto, "src/tmpDescargas/"+objeto.getString("foto"));
                        
                    }
                    validarHuella=true;
                }
                else
                {
                    validarHuella=false;
                    System.out.println("no coinside");
                }
            }
            }
         
            
        }catch (DPFPImageQualityException ex) {
        System.err.println("Error: "+ex.getMessage());
        }

        finally {
            if(validarHuella)
            {
                cuadroDialogo("Autorizado");
                limpiarDirectorio();
            }
            else
            {
                cuadroDialogo("No autorizado");
            }
   
                }
}

    public VerificarHuella() {
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
        this.setLocationRelativeTo(null);
        rsscalelabel.RSScaleLabel.setScaleLabel(lbl_foto, "src/images/persona.png");
        rsscalelabel.RSScaleLabel.setScaleLabel(lbl_huella, "src/images/huella-dactilar.png");
        limpiarDirectorio();
        Iniciar();
        start();
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
    public void cuadroDialogo(String mensaje)
    {
          JOptionPane.showMessageDialog(null, mensaje);
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

        lbl_foto = new javax.swing.JLabel();
        lbl_huella = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txt_codigo = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        txt_autorizado = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        txt_Area = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(700, 350));
        setResizable(false);

        lbl_foto.setText("jLabel1");
        lbl_foto.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lbl_huella.setText("jLabel1");
        lbl_huella.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setText("Codigo MacroPro:");

        jLabel2.setText("Nombre Cliente:");

        jLabel3.setText("Nombre Autorizado:");

        jTextField1.setEnabled(false);

        txt_autorizado.setEnabled(false);

        txt_Area.setEditable(false);
        txt_Area.setColumns(20);
        txt_Area.setRows(5);
        jScrollPane1.setViewportView(txt_Area);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(lbl_foto, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_codigo, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 51, Short.MAX_VALUE)
                        .addComponent(lbl_huella, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_autorizado, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 394, javax.swing.GroupLayout.PREFERRED_SIZE)))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbl_foto, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbl_huella, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txt_codigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txt_autorizado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(69, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(VerificarHuella.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VerificarHuella.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VerificarHuella.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VerificarHuella.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VerificarHuella().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel lbl_foto;
    private javax.swing.JLabel lbl_huella;
    private javax.swing.JTextArea txt_Area;
    private javax.swing.JTextField txt_autorizado;
    private javax.swing.JTextField txt_codigo;
    // End of variables declaration//GEN-END:variables
}
