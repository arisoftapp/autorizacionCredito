
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
import complementos.apiAutorizados;
import complementos.apiSubir;
import complementos.consultasBD;
import java.awt.Image;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class CapturaHuella extends javax.swing.JFrame {

    /**
     * Creates new form CapturaHuella
     */
     //declarando variables para huella
    public static String codigo;
    public static String nombre;
    public static Integer id_autorizados;
    consultasBD consultas=new consultasBD();
    Integer id_empresa=consultas.getIdEmpresa();
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
    boolean val1=false,val2=false,val3=false,val4=false,val5=false;
    public CapturaHuella() {
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
        Iniciar();
        rsscalelabel.RSScaleLabel.setScaleLabel(lbl_icono5, "src/images/huella-dactilar.png");
        rsscalelabel.RSScaleLabel.setScaleLabel(lbl_icono2, "src/images/huella-dactilar.png");
        rsscalelabel.RSScaleLabel.setScaleLabel(lbl_icono1, "src/images/huella-dactilar.png");
        rsscalelabel.RSScaleLabel.setScaleLabel(lbl_icono3, "src/images/huella-dactilar.png");
        rsscalelabel.RSScaleLabel.setScaleLabel(lbl_icono4, "src/images/huella-dactilar.png");
        rsscalelabel.RSScaleLabel.setScaleLabel(lbl_guardar, "src/images/save.png");
        System.out.println(codigo+" "+nombre);
        
    }
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
    lbl_huella1.setIcon(
    new ImageIcon(
      image.getScaledInstance(
        lbl_huella1.getWidth(),
        lbl_huella1.getHeight(),
        Image.SCALE_DEFAULT
      )
    )
  );

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
    
        }catch (DPFPImageQualityException ex) {
        System.err.println("Error: "+ex.getMessage());
        }

        finally {
        EstadoHuellas();
        // Comprueba si la plantilla se ha creado.
           switch(Reclutador.getTemplateStatus())
           {
               case TEMPLATE_STATUS_READY:	// informe de éxito y detiene  la captura de huellas
               setTemplate(Reclutador.getTemplate());
                // Dibuja la huella dactilar capturada.
                Image image=CrearImagenHuella(sample);
                DibujarHuella(image);
                Integer index=cambiarValidacion();
                   System.out.println("index: "+index);
                crearFichero(index,template);
                
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
 
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        grupoHuella = new javax.swing.ButtonGroup();
        jScrollPane1 = new javax.swing.JScrollPane();
        txt_Area = new javax.swing.JTextArea();
        lbl_icono5 = new javax.swing.JLabel();
        lbl_icono2 = new javax.swing.JLabel();
        lbl_icono1 = new javax.swing.JLabel();
        lbl_icono3 = new javax.swing.JLabel();
        lbl_icono4 = new javax.swing.JLabel();
        lbl_huella1 = new javax.swing.JLabel();
        rb1 = new javax.swing.JRadioButton();
        rb2 = new javax.swing.JRadioButton();
        rb3 = new javax.swing.JRadioButton();
        rb4 = new javax.swing.JRadioButton();
        rb5 = new javax.swing.JRadioButton();
        lbl_guardar = new javax.swing.JLabel();
        btn_guardar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(500, 520));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txt_Area.setEditable(false);
        txt_Area.setColumns(20);
        txt_Area.setRows(5);
        jScrollPane1.setViewportView(txt_Area);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 300, 461, 100));

        lbl_icono5.setPreferredSize(new java.awt.Dimension(50, 50));
        getContentPane().add(lbl_icono5, new org.netbeans.lib.awtextra.AbsoluteConstraints(352, 37, -1, -1));

        lbl_icono2.setPreferredSize(new java.awt.Dimension(50, 50));
        getContentPane().add(lbl_icono2, new org.netbeans.lib.awtextra.AbsoluteConstraints(148, 37, -1, -1));

        lbl_icono1.setPreferredSize(new java.awt.Dimension(50, 50));
        getContentPane().add(lbl_icono1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 37, -1, -1));

        lbl_icono3.setPreferredSize(new java.awt.Dimension(50, 50));
        getContentPane().add(lbl_icono3, new org.netbeans.lib.awtextra.AbsoluteConstraints(216, 37, -1, -1));

        lbl_icono4.setPreferredSize(new java.awt.Dimension(50, 50));
        getContentPane().add(lbl_icono4, new org.netbeans.lib.awtextra.AbsoluteConstraints(284, 37, -1, -1));

        lbl_huella1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        lbl_huella1.setPreferredSize(new java.awt.Dimension(50, 50));
        getContentPane().add(lbl_huella1, new org.netbeans.lib.awtextra.AbsoluteConstraints(148, 118, 167, 171));

        grupoHuella.add(rb1);
        rb1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                rb1ItemStateChanged(evt);
            }
        });
        getContentPane().add(rb1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 90, -1, -1));

        grupoHuella.add(rb2);
        rb2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                rb2ItemStateChanged(evt);
            }
        });
        getContentPane().add(rb2, new org.netbeans.lib.awtextra.AbsoluteConstraints(158, 90, -1, -1));

        grupoHuella.add(rb3);
        rb3.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                rb3ItemStateChanged(evt);
            }
        });
        getContentPane().add(rb3, new org.netbeans.lib.awtextra.AbsoluteConstraints(226, 90, -1, -1));

        grupoHuella.add(rb4);
        rb4.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                rb4ItemStateChanged(evt);
            }
        });
        getContentPane().add(rb4, new org.netbeans.lib.awtextra.AbsoluteConstraints(294, 90, -1, -1));

        grupoHuella.add(rb5);
        rb5.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                rb5ItemStateChanged(evt);
            }
        });
        getContentPane().add(rb5, new org.netbeans.lib.awtextra.AbsoluteConstraints(362, 89, -1, -1));
        getContentPane().add(lbl_guardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 430, 30, 30));

        btn_guardar.setText("jButton1");
        btn_guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_guardarActionPerformed(evt);
            }
        });
        getContentPane().add(btn_guardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 420, 50, 50));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void rb1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_rb1ItemStateChanged
        // TODO add your handling code here:
        if(rb1.isSelected())
        {
            System.out.println("selecciono 1");
            cambioImagen(1);
            if(Lector.isStarted())
            {
                Reclutador.clear();
                setTemplate(null);
                EstadoHuellas();
            }
            else
            {
                start();
            }
            
        }
        
    }//GEN-LAST:event_rb1ItemStateChanged

    private void rb2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_rb2ItemStateChanged
        // TODO add your handling code here:
        if(rb2.isSelected())
        {
           System.out.println("selecciono 2"); 
           cambioImagen(2);
            if(Lector.isStarted())
            {
                Reclutador.clear();
                setTemplate(null);
                EstadoHuellas();
            }
            else
            {
                start();
            }
        }
        
    }//GEN-LAST:event_rb2ItemStateChanged

    private void rb3ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_rb3ItemStateChanged
        // TODO add your handling code here:
        if(rb3.isSelected())
        {
            cambioImagen(3);
           System.out.println("selecciono 3"); 
            if(Lector.isStarted())
            {
                Reclutador.clear();
                setTemplate(null);
                EstadoHuellas();
            }
            else
            {
                start();
            }
        }
    }//GEN-LAST:event_rb3ItemStateChanged

    private void rb4ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_rb4ItemStateChanged
        // TODO add your handling code here:
        if(rb4.isSelected())
        {
            cambioImagen(4);
           System.out.println("selecciono 4"); 
            if(Lector.isStarted())
            {
                Reclutador.clear();
                setTemplate(null);
                EstadoHuellas();
            }
            else
            {
                start();
            }
        }
    }//GEN-LAST:event_rb4ItemStateChanged

    private void rb5ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_rb5ItemStateChanged
        // TODO add your handling code here:
        if(rb5.isSelected())
        {
            cambioImagen(5);
           System.out.println("selecciono 5"); 
            if(Lector.isStarted())
            {
                Reclutador.clear();
                setTemplate(null);
                EstadoHuellas();
            }
            else
            {
                start();
            }
        }
    }//GEN-LAST:event_rb5ItemStateChanged

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        // TODO add your handling code here:
        stop();
    }//GEN-LAST:event_formWindowClosed

    private void btn_guardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_guardarActionPerformed
        // TODO add your handling code here
        if(DirectorioVacio())
        {
            cuadroDialogo("No a registrado ninguna huella");
        }
        else
        {
            apiSubir apisubir=new apiSubir();     
        apiAutorizados apiautorizados=new apiAutorizados();
        try {
            JSONObject res=apisubir.subirHuella();
            if(res.getBoolean("success"))
            {
                //cuadroDialogo(res.getString("mensaje"));
                apiautorizados.insertRutaAHuella(crearJsonRutaHuella());
            }
            else
            {
                cuadroDialogo("Problemas al guardar huellas");
            }
            
        } catch (IOException ex) {
            Logger.getLogger(CapturaHuella.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
        
    }//GEN-LAST:event_btn_guardarActionPerformed

    public boolean DirectorioVacio()
    {
        boolean validar=false;
        File tmp =  new File("src/tmp");
        if(tmp.isDirectory() && tmp.list().length == 0)
        {
            System.out.println("directorio vacio");
            validar=true;
        }
        else
        {
            System.out.println("directorio con datos");
            validar=false;
        }
        return validar;
    }
    public void crearFichero(Integer i,DPFPTemplate templateAux)
    {
        Date date = new Date();
        DateFormat hourdateFormat = new SimpleDateFormat("HHmmssddMMyyyy");
        System.out.println("Hora y fecha: "+hourdateFormat.format(date));
        String ruta="src/tmp/"+hourdateFormat.format(date)+codigo+nombre+i+".bin";
        try(FileOutputStream fileOuputStream = new FileOutputStream(ruta)){
            fileOuputStream.write(templateAux.serialize());
            
        }catch(Exception e)
        {
            System.err.println(e);
        }
    }
    public Integer cambiarValidacion()
    {
        Integer i=0;
                if(rb1.isSelected())
               {
                   i=1;
                   val1=true;
                   validarHuellaCompleta();
               }
               if(rb2.isSelected())
               {
                   i=2;
                   val2=true;
                   validarHuellaCompleta();
               }
               if(rb3.isSelected())
               {
                   i=3;
                   val3=true;
                   validarHuellaCompleta();
               }
               if(rb4.isSelected())
               {
                   i=4;
                   val4=true;
                   validarHuellaCompleta();
               }
               if(rb5.isSelected())
               {
                   i=5;
                   val5=true;
                   validarHuellaCompleta();
               }
               return i;
    }
    public void validarHuellaCompleta()
    {
        if(val1==true)
        {
            rsscalelabel.RSScaleLabel.setScaleLabel(lbl_icono1, "src/images/fingerprint.png");
            rb1.setEnabled(false);
        }
        if(val2==true)
        {
            rsscalelabel.RSScaleLabel.setScaleLabel(lbl_icono2, "src/images/fingerprint.png");
            rb2.setEnabled(false);
        }
        if(val3==true)
        {
            rsscalelabel.RSScaleLabel.setScaleLabel(lbl_icono3, "src/images/fingerprint.png");
            rb3.setEnabled(false);
        }
        if(val4==true)
        {
            rsscalelabel.RSScaleLabel.setScaleLabel(lbl_icono4, "src/images/fingerprint.png");
            rb4.setEnabled(false);
        }
        if(val5==true)
        {
            rsscalelabel.RSScaleLabel.setScaleLabel(lbl_icono5, "src/images/fingerprint.png");
            rb5.setEnabled(false);
        }
        
    }
    public void cambioImagen(Integer i)
    {
        switch(i)
        {
            case 1: i=1;
            txt_Area.setText("");
            rsscalelabel.RSScaleLabel.setScaleLabel(lbl_icono1, "src/images/finger-scanner.png");
            rsscalelabel.RSScaleLabel.setScaleLabel(lbl_icono2, "src/images/huella-dactilar.png");
            rsscalelabel.RSScaleLabel.setScaleLabel(lbl_icono3, "src/images/huella-dactilar.png");
            rsscalelabel.RSScaleLabel.setScaleLabel(lbl_icono4, "src/images/huella-dactilar.png");
            rsscalelabel.RSScaleLabel.setScaleLabel(lbl_icono5, "src/images/huella-dactilar.png");
            validarHuellaCompleta();
            break;
            case 2: i=2;
            txt_Area.setText("");
            rsscalelabel.RSScaleLabel.setScaleLabel(lbl_icono2, "src/images/finger-scanner.png");
            rsscalelabel.RSScaleLabel.setScaleLabel(lbl_icono5, "src/images/huella-dactilar.png");
            rsscalelabel.RSScaleLabel.setScaleLabel(lbl_icono1, "src/images/huella-dactilar.png");
            rsscalelabel.RSScaleLabel.setScaleLabel(lbl_icono3, "src/images/huella-dactilar.png");
            rsscalelabel.RSScaleLabel.setScaleLabel(lbl_icono4, "src/images/huella-dactilar.png");
            validarHuellaCompleta();
            break;
            case 3: i=3;
            txt_Area.setText("");
            rsscalelabel.RSScaleLabel.setScaleLabel(lbl_icono3, "src/images/finger-scanner.png");
            rsscalelabel.RSScaleLabel.setScaleLabel(lbl_icono2, "src/images/huella-dactilar.png");
            rsscalelabel.RSScaleLabel.setScaleLabel(lbl_icono5, "src/images/huella-dactilar.png");
            rsscalelabel.RSScaleLabel.setScaleLabel(lbl_icono1, "src/images/huella-dactilar.png");
            rsscalelabel.RSScaleLabel.setScaleLabel(lbl_icono4, "src/images/huella-dactilar.png");
            validarHuellaCompleta();
            break;
            case 4: i=4;
            txt_Area.setText("");
            rsscalelabel.RSScaleLabel.setScaleLabel(lbl_icono4, "src/images/finger-scanner.png");
            rsscalelabel.RSScaleLabel.setScaleLabel(lbl_icono2, "src/images/huella-dactilar.png");
            rsscalelabel.RSScaleLabel.setScaleLabel(lbl_icono1, "src/images/huella-dactilar.png");
            rsscalelabel.RSScaleLabel.setScaleLabel(lbl_icono5, "src/images/huella-dactilar.png");
            rsscalelabel.RSScaleLabel.setScaleLabel(lbl_icono3, "src/images/huella-dactilar.png");
            validarHuellaCompleta();
            break;
            case 5: i=5;
            txt_Area.setText("");
            rsscalelabel.RSScaleLabel.setScaleLabel(lbl_icono5, "src/images/finger-scanner.png");
            rsscalelabel.RSScaleLabel.setScaleLabel(lbl_icono2, "src/images/huella-dactilar.png");
            rsscalelabel.RSScaleLabel.setScaleLabel(lbl_icono1, "src/images/huella-dactilar.png");
            rsscalelabel.RSScaleLabel.setScaleLabel(lbl_icono3, "src/images/huella-dactilar.png");
            rsscalelabel.RSScaleLabel.setScaleLabel(lbl_icono4, "src/images/huella-dactilar.png");
            validarHuellaCompleta();
            break;
            
        }
    }
    public JSONObject crearJsonRutaHuella()
    {   
        JSONObject json = new JSONObject();
        JSONArray data=new JSONArray();
        File carpeta = new File("src/tmp");
        String[] listado = carpeta.list();
        try {     
         for (int i=0; i< listado.length; i++) {
              JSONObject tmp=new JSONObject();
             tmp.put("ruta", listado[i]);
             tmp.put("id_autorizados", id_autorizados);
             tmp.put("codigoMacro", codigo);
             tmp.put("id_empresa", id_empresa);
             data.put(tmp);
        //System.out.println(listado[i]);
        }
         //System.out.println(data);
         json.put("data", data);
         System.out.println(data);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    
        return json;
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
            java.util.logging.Logger.getLogger(CapturaHuella.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CapturaHuella.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CapturaHuella.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CapturaHuella.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CapturaHuella().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_guardar;
    private javax.swing.ButtonGroup grupoHuella;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbl_guardar;
    private javax.swing.JLabel lbl_huella1;
    private javax.swing.JLabel lbl_icono1;
    private javax.swing.JLabel lbl_icono2;
    private javax.swing.JLabel lbl_icono3;
    private javax.swing.JLabel lbl_icono4;
    private javax.swing.JLabel lbl_icono5;
    private javax.swing.JRadioButton rb1;
    private javax.swing.JRadioButton rb2;
    private javax.swing.JRadioButton rb3;
    private javax.swing.JRadioButton rb4;
    private javax.swing.JRadioButton rb5;
    private javax.swing.JTextArea txt_Area;
    // End of variables declaration//GEN-END:variables
}
