/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package complementos;

import java.awt.List;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
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
    
        public void insertAutorizados(JSONObject json)
    {
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
                   cuadroDialogo(jObject.getString("mensaje"));
               }
               else
               {
                 
               }
          
            conn.disconnect();

        } catch (IOException | JSONException e) {
            System.err.println("Exception in NetClientGet:- " + e);
          
        }
         
    }
               public void subirImagen(File file)
    {
 
         
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
    }   
    
               
  
     public void cuadroDialogo(String mensaje)
    {
          JOptionPane.showMessageDialog(null, mensaje);
    }

    
}
