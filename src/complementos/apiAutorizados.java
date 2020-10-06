/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package complementos;

import java.awt.Cursor;
import static java.awt.Frame.DEFAULT_CURSOR;
import java.awt.List;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.UUID;
import javax.swing.JOptionPane;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.FileEntity;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.ContentBody;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;



import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Desarrollo
 */
public class apiAutorizados {
    consultasBD consultas=new consultasBD();
    String token=consultas.getToken();
    Integer id_empresa=consultas.getIdEmpresa();
    public JSONObject getAutorizados(String codigo)
    {
        String token=consultas.getToken();
       JSONObject data = null;
         try {
            
            URL url = new URL("http://wsar.homelinux.com:3100/getAutorizados/"+codigo+"/"+id_empresa);//your url i.e fetch data from .
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Accept","application/json");
            conn.setRequestProperty("access-token",token);
            conn.setConnectTimeout(10000);
            conn.setReadTimeout(10000);
            conn.addRequestProperty("User-Agent","Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.0)");
            conn.connect();
            System.out.print(conn.getResponseCode());
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null) {
                    sb.append(line).append("\n");
                }
                String finalJSON = sb.toString();
                JSONObject jObject = new JSONObject(finalJSON);
                System.out.print(jObject.getBoolean("success"));
               if(jObject.getBoolean("success"))
               {
                        data=jObject;
                 }
               else
               { 
      
                   data=jObject;
                   cuadroDialogo(jObject.getString("mensaje"));
               }
          
            conn.disconnect();

        } catch (IOException | JSONException e) {
            System.err.println("Exception in NetClientGet:- " + e);
          cuadroDialogo(e.getMessage());
      
        }
  
         return data;
    }
    
        public Integer insertAutorizados(JSONObject json)
    {
         Integer res=0;
         try {
            URL url = new URL("http://wsar.homelinux.com:3100/insertarAutorizado");//your url i.e fetch data from .
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Accept","application/json");
            conn.setRequestProperty("access-token",token);
            conn.setConnectTimeout(10000);
            conn.setReadTimeout(10000);
            conn.addRequestProperty("User-Agent","Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.0)");
            conn.setDoOutput(true);
            conn.connect();
            DataOutputStream os = new DataOutputStream(conn.getOutputStream());
            os.writeBytes(json.toString());
            os.flush();
            os.close();
            System.out.print(conn.getResponseCode());
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null) {
                    sb.append(line).append("\n");
                }
                String finalJSON = sb.toString();
                JSONObject jObject = new JSONObject(finalJSON);
                System.out.print(finalJSON);
               if(jObject.getBoolean("success"))
               {
                   System.out.println(jObject.getBoolean("success"));
                   res=jObject.getInt("respuesta");
                   cuadroDialogo(jObject.getString("mensaje"));
               }
               else
               {
                 cuadroDialogo(jObject.getString("mensaje"));
               }
          
            conn.disconnect();

        } catch (IOException | JSONException e) {
            System.err.println("Exception in NetClientGet:- " + e);
          
        }
         return res;
    }
        public boolean insertRutaAHuella(JSONObject json)
    {
        boolean res=false;
         try {
            
            URL url = new URL("http://wsar.homelinux.com:3100/insertarRutaAHuella");//your url i.e fetch data from .
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Accept","application/json");
            conn.setRequestProperty("access-token",token);
            conn.setConnectTimeout(10000);
            conn.setReadTimeout(10000);
            conn.addRequestProperty("User-Agent","Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.0)");
            conn.setDoOutput(true);
            conn.connect();
            DataOutputStream os = new DataOutputStream(conn.getOutputStream());
            os.writeBytes(json.toString());
            os.flush();
            os.close();
            System.out.print(conn.getResponseCode());
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null) {
                    sb.append(line).append("\n");
                }
                String finalJSON = sb.toString();
                JSONObject jObject = new JSONObject(finalJSON);
                System.out.print(finalJSON);
               if(jObject.getBoolean("success"))
               {
                   res=true;
                   System.out.println(jObject.getBoolean("success"));
                   cuadroDialogo(jObject.getString("mensaje"));
               }
               else
               {
                   res=false;
                 cuadroDialogo(jObject.getString("mensaje"));
               }
          
            conn.disconnect();

        } catch (IOException | JSONException e) {
            res=false;
            System.err.println("Exception in NetClientGet:- " + e);
          
        }
         return res;
    }
        public boolean updateAutorizado(JSONObject json)
    {
         boolean res=false;
         try {
            URL url = new URL("http://wsar.homelinux.com:3100/updateAutorizado");//your url i.e fetch data from .
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("PUT");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Accept","application/json");
            conn.setRequestProperty("access-token",token);
            conn.setConnectTimeout(10000);
            conn.setReadTimeout(10000);
            conn.addRequestProperty("User-Agent","Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.0)");
            conn.setDoOutput(true);
            conn.connect();
            DataOutputStream os = new DataOutputStream(conn.getOutputStream());
            os.writeBytes(json.toString());
            os.flush();
            os.close();
            System.out.print(conn.getResponseCode());
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null) {
                    sb.append(line).append("\n");
                }
                String finalJSON = sb.toString();
                JSONObject jObject = new JSONObject(finalJSON);
                System.out.print(finalJSON);
               if(jObject.getBoolean("success"))
               {
                   System.out.println(jObject.getBoolean("success"));
                   res=true;
                   cuadroDialogo(jObject.getString("mensaje"));
               }
               else
               {
                 cuadroDialogo(jObject.getString("mensaje"));
               }
          
            conn.disconnect();

        } catch (IOException | JSONException e) {
            System.err.println("Exception in NetClientGet:- " + e);
          
        }
         return res;
    }
        public boolean updateRutaHuellas(JSONObject json)
    {
         boolean res=false;
         try {
            URL url = new URL("http://wsar.homelinux.com:3100/updateHuella");//your url i.e fetch data from .
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("PUT");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Accept","application/json");
            conn.setRequestProperty("access-token",token);
            conn.setConnectTimeout(10000);
            conn.setReadTimeout(10000);
            conn.addRequestProperty("User-Agent","Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.0)");
            conn.setDoOutput(true);
            conn.connect();
            DataOutputStream os = new DataOutputStream(conn.getOutputStream());
            os.writeBytes(json.toString());
            os.flush();
            os.close();
            System.out.print(conn.getResponseCode());
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null) {
                    sb.append(line).append("\n");
                }
                String finalJSON = sb.toString();
                JSONObject jObject = new JSONObject(finalJSON);
                System.out.print(finalJSON);
               if(jObject.getBoolean("success"))
               {
                   System.out.println(jObject.getBoolean("success"));
                   res=true;
                   cuadroDialogo(jObject.getString("mensaje"));
               }
               else
               {
                 cuadroDialogo(jObject.getString("mensaje"));
               }
          
            conn.disconnect();

        } catch (IOException | JSONException e) {
            System.err.println("Exception in NetClientGet:- " + e);
          cuadroDialogo(e.getMessage());
        }
         return res;
    }
        public void pruebas2( byte[] content ) throws IOException
    {
        System.out.println(content);
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost httppost = new HttpPost("http://wsar.homelinux.com:3100/prueba");
        httppost.setEntity(new ByteArrayEntity(content));
        //httppost.setEntity(enti);
        CloseableHttpResponse response = httpclient.execute(httppost);
        HttpEntity entity = response.getEntity();
        response.close();
        String finalJSON=EntityUtils.toString(entity);
        System.out.println(finalJSON);
    }
               
            public void pruebas(JSONObject json)
    {
         try {
            URL url = new URL("http://wsar.homelinux.com:3100/prueba");//your url i.e fetch data from .
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Accept","application/json");
            //conn.setRequestProperty("access-token",token);
            conn.setConnectTimeout(10000);
            conn.setReadTimeout(10000);
            conn.addRequestProperty("User-Agent","Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.0)");
            conn.setDoOutput(true);
            conn.connect();
            DataOutputStream os = new DataOutputStream(conn.getOutputStream());
            os.writeBytes(json.toString());
            os.flush();
            os.close();
            System.out.print(conn.getResponseCode());
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null) {
                    sb.append(line).append("\n");
                }
                String finalJSON = sb.toString();
                JSONObject jObject = new JSONObject(finalJSON);
                System.out.print(finalJSON);
               if(jObject.getBoolean("success"))
               {
                   System.out.println(jObject.getBoolean("success"));
                   //cuadroDialogo(jObject.getString("mensaje"));
               }
               else
               {
                 
               }
          
            conn.disconnect();

        } catch (IOException | JSONException e) {
            System.err.println("Exception in NetClientGet:- " + e);
          
        }
         
    }
      public JSONObject getRutasHuella(String codigoMacro,Integer id_empresa)
    {
         JSONObject res = null;
         try {
            URL url = new URL("http://wsar.homelinux.com:3100/getRutaHuellas/"+codigoMacro+"/"+id_empresa);//your url i.e fetch data from .
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Accept","application/json");
            conn.setRequestProperty("access-token",token);
            conn.setConnectTimeout(10000);
            conn.setReadTimeout(10000);
            conn.addRequestProperty("User-Agent","Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.0)");
            conn.setDoOutput(true);
            conn.connect();
            System.out.print(conn.getResponseCode());
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null) {
                    sb.append(line).append("\n");
                }
                String finalJSON = sb.toString();
                JSONObject jObject = new JSONObject(finalJSON);
                //System.out.print(finalJSON);
               if(jObject.getBoolean("success"))
               {
                   System.out.println(jObject.getBoolean("success"));
                   //cuadroDialogo(jObject.getString("mensaje"));
                   res=new JSONObject(finalJSON);
               }
               else
               {
                 
               }
          
            conn.disconnect();

        } catch (IOException | JSONException e) {
            System.err.println("Exception in NetClientGet:- " + e.getMessage());
          JOptionPane.showMessageDialog(null, "Problemas al Descargar Foto: "+e.getMessage(),"",JOptionPane.WARNING_MESSAGE);
        }
         return res;
    }
             public JSONObject getAutorizadoId(Integer id,String codigo)
    {
         JSONObject res = null;
         try {
            URL url = new URL("http://wsar.homelinux.com:3100/getAutorizado/"+id+"/"+codigo+"/"+id_empresa);//your url i.e fetch data from .
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Accept","application/json");
            conn.setRequestProperty("access-token",token);
            conn.setConnectTimeout(10000);
            conn.setReadTimeout(10000);
            conn.addRequestProperty("User-Agent","Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.0)");
            conn.setDoOutput(true);
            conn.connect();
            System.out.print(conn.getResponseCode());
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null) {
                    sb.append(line).append("\n");
                }
                String finalJSON = sb.toString();
                JSONObject jObject = new JSONObject(finalJSON);
                //System.out.print(finalJSON);
               if(jObject.getBoolean("success"))
               {
                   System.out.println(jObject.getBoolean("success"));
                   //cuadroDialogo(jObject.getString("mensaje"));
                   res=new JSONObject(finalJSON);
               }
               else
               {
                 
               }
          
            conn.disconnect();

        } catch (IOException | JSONException e) {
            System.err.println("Exception in NetClientGet:- " + e);
             cuadroDialogo("Error al cargar Autorizado:"+e.getMessage());
          
        }
         return res;
    }  
    public void subirImg (File fichero) throws IOException 
    {
        
        String boundary = Long.toHexString(System.currentTimeMillis()); 
        FileEntity enti = new FileEntity(fichero, ContentType.create("multipart/form-data"));  
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost httppost = new HttpPost("http://wsar.homelinux.com:3100/uploading");
        MultipartEntity mpEntity = new MultipartEntity();
        String extension = "";
        int i = fichero.getName().lastIndexOf('.');
        if (i > 0) {
        extension = fichero.getName().substring(i+1);
        }
        ContentBody cbFile = new FileBody(fichero, "image/"+extension);
        mpEntity.addPart("file", cbFile);
        httppost.setEntity(mpEntity);
        //httppost.setEntity(enti);
        CloseableHttpResponse response = httpclient.execute(httppost);
        HttpEntity entity = response.getEntity();
        String finalJSON=EntityUtils.toString(entity);
        System.out.println(finalJSON);
        JSONObject jObject = new JSONObject(finalJSON);
        /*
             if(jObject.getBoolean("success"))
               {
                   //System.out.println(jObject.getBoolean("success"));
                   cuadroDialogo(jObject.getString("mensaje"));
               }
               else
               {
                 
               }
        */
        response.close();
        /*
        try {
            
            HttpEntity entity = response.getEntity();
            if (entity != null) {
            long len = entity.getContentLength();
            if (len != -1 && len < 2048) {
            System.out.println(EntityUtils.toString(entity));
   
        } else {
            // Stream content out
        }
    }
} finally {
    response.close();
}
*/
    }   
    public void download()
    {
        try {
            URL url = new URL("http://wsar.homelinux.com:3100/download/1");//your url i.e fetch data from .
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Accept","application/json");
            //conn.setRequestProperty("access-token",token);
            conn.setConnectTimeout(10000);
            conn.setReadTimeout(10000);
            conn.addRequestProperty("User-Agent","Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.0)");
            conn.setDoOutput(true);
            conn.connect();
            System.out.print(conn.getResponseCode());
                //BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            File file = new File("src/huellaserver.bin");
            copyInputStreamToFile(conn.getInputStream(), file);
            conn.disconnect();

        } catch (IOException | JSONException e) {
            System.err.println("Exception in NetClientGet:- " + e);
          
        }
    }
    public void downloadHuella(String nombre)
    {
        try {
            URL url = new URL("http://wsar.homelinux.com:3100/download_huellas/"+nombre);//your url i.e fetch data from .
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Accept","application/json");
            //conn.setRequestProperty("access-token",token);
            conn.setConnectTimeout(20000);
            conn.setReadTimeout(20000);
            conn.addRequestProperty("User-Agent","Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.0)");
            conn.setDoOutput(true);
            conn.connect();
            System.out.print(conn.getResponseCode());
                //BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            File file = new File("src/tmpDescargas/"+nombre);
            copyInputStreamToFile(conn.getInputStream(), file);
            conn.disconnect();

        } catch (IOException | JSONException e) {
            System.err.println("Exception in NetClientGet:- " + e);
          
        }
    }
    public void downloadFoto(String nombre)
    {
        try {
            URL url = new URL("http://wsar.homelinux.com:3100/download_fotos/"+nombre);//your url i.e fetch data from .
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Accept","application/json");
            //conn.setRequestProperty("access-token",token);
            conn.setConnectTimeout(20000);
            conn.setReadTimeout(20000);
            conn.addRequestProperty("User-Agent","Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.0)");
            conn.setDoOutput(true);
            conn.connect();
            System.out.print(conn.getResponseCode());
                //BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            File file = new File("src/tmpDescargas/"+nombre);
            copyInputStreamToFile(conn.getInputStream(), file);
            conn.disconnect();

        } catch (IOException | JSONException e) {
            System.err.println("Exception in NetClientGet:- " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Problemas al Descargar Foto: "+e.getMessage(),"",JOptionPane.WARNING_MESSAGE);
        }
    }
    public void downloadDoc(String nombre)
    {
        try {
            URL url = new URL("http://wsar.homelinux.com:3100/download_huellas/"+nombre);//your url i.e fetch data from .
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Accept","application/json");
            //conn.setRequestProperty("access-token",token);
            conn.setConnectTimeout(20000);
            conn.setReadTimeout(20000);
            conn.addRequestProperty("User-Agent","Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.0)");
            conn.setDoOutput(true);
            conn.connect();
            System.out.print(conn.getResponseCode());
                //BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            File file = new File("src/tmpDescargas/"+nombre);
            copyInputStreamToFile(conn.getInputStream(), file);
            conn.disconnect();

        } catch (IOException | JSONException e) {
            System.err.println("Exception in NetClientGet:- " + e);
          
        }
    }
      private static void copyInputStreamToFile(InputStream inputStream, File file)throws IOException {

        try (FileOutputStream outputStream = new FileOutputStream(file)) {

            int read;
            byte[] bytes = new byte[1024];

            while ((read = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, read);
            }

			// commons-io
            //IOUtils.copy(inputStream, outputStream);

        }

    }
     public void cuadroDialogo(String mensaje)
    {
          JOptionPane.showMessageDialog(null, mensaje);
    }

    
}
