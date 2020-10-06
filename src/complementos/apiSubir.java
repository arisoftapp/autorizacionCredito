/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package complementos;

import java.io.File;
import java.io.IOException;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.FileEntity;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.ContentBody;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Desarrollo
 */
public class apiSubir {
        public JSONObject subirImg (File fichero) throws IOException 
    {
        JSONObject jObject=null;
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost httppost = new HttpPost("http://wsar.homelinux.com:3100/uploading_fotos");
        FileBody fileBody = new FileBody(fichero);
        MultipartEntityBuilder builder = MultipartEntityBuilder.create()
                         .setMode(HttpMultipartMode.BROWSER_COMPATIBLE)
                         .addPart("file", fileBody);
       HttpEntity multiPartEntity = builder.build();
        httppost.setEntity(multiPartEntity);
        CloseableHttpResponse response = httpclient.execute(httppost);
        HttpEntity entity = response.getEntity();
        String finalJSON=EntityUtils.toString(entity);
        jObject = new JSONObject(finalJSON);
        System.out.println(jObject.getString("nombre"));
        response.close();
        return jObject;
    }   
         public JSONObject subirHuella ( ) throws IOException 
    {
        JSONObject jObject=null;
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost httppost = new HttpPost("http://wsar.homelinux.com:3100/uploading_huellas");
        MultipartEntityBuilder builder=null;
 
            try{
            File carpeta = new File("src/tmp");
            File huella1=null,huella2=null,huella3=null,huella4=null,huella5=null;
            String[] listado = carpeta.list();
             System.out.println(listado.length);
             
            if (listado == null || listado.length == 0) {
            System.out.println("No hay elementos dentro de la carpeta actual");
            }
            else {
                if(listado.length==1)
                {
                    System.out.println(listado[0]);
                    huella1=new File("src/tmp/"+listado[0]);
                    FileBody fileBody1 = new FileBody(huella1);
                         builder = MultipartEntityBuilder.create()
                         .setMode(HttpMultipartMode.BROWSER_COMPATIBLE)
                         .addPart("file", fileBody1);
                }
                if(listado.length==2)
                {
                    huella1=new File("src/tmp/"+listado[0]);
                    huella2=new File("src/tmp/"+listado[1]);
                    FileBody fileBody1 = new FileBody(huella1);
                    FileBody fileBody2 = new FileBody(huella2);
                         builder = MultipartEntityBuilder.create()
                         .setMode(HttpMultipartMode.BROWSER_COMPATIBLE)
                         .addPart("file", fileBody1) 
                         .addPart("file", fileBody2);
                }
                if(listado.length==3)
                {
                    huella1=new File("src/tmp/"+listado[0]);
                    huella2=new File("src/tmp/"+listado[1]);
                    huella3=new File("src/tmp/"+listado[2]);
                    FileBody fileBody1 = new FileBody(huella1);
                    FileBody fileBody2 = new FileBody(huella2);
                    FileBody fileBody3 = new FileBody(huella3);
                         builder = MultipartEntityBuilder.create()
                         .setMode(HttpMultipartMode.BROWSER_COMPATIBLE)
                         .addPart("file", fileBody1) 
                         .addPart("file", fileBody2)
                         .addPart("file", fileBody3);
                }
                if(listado.length==4)
                {
                    huella1=new File("src/tmp/"+listado[0]);
                    huella2=new File("src/tmp/"+listado[1]);
                    huella3=new File("src/tmp/"+listado[2]);
                    huella4=new File("src/tmp/"+listado[3]);
                    FileBody fileBody1 = new FileBody(huella1);
                    FileBody fileBody2 = new FileBody(huella2);
                    FileBody fileBody3 = new FileBody(huella3);
                    FileBody fileBody4 = new FileBody(huella4);
                         builder = MultipartEntityBuilder.create()
                         .setMode(HttpMultipartMode.BROWSER_COMPATIBLE)
                         .addPart("file", fileBody1) 
                         .addPart("file", fileBody2)
                         .addPart("file", fileBody3)
                         .addPart("file", fileBody4);
                }
                if(listado.length==5)
                {
                    huella1=new File("src/tmp/"+listado[0]);
                    huella2=new File("src/tmp/"+listado[1]);
                    huella3=new File("src/tmp/"+listado[2]);
                    huella4=new File("src/tmp/"+listado[3]);
                    huella5=new File("src/tmp/"+listado[4]);
                    FileBody fileBody1 = new FileBody(huella1);
                    FileBody fileBody2 = new FileBody(huella2);
                    FileBody fileBody3 = new FileBody(huella3);
                    FileBody fileBody4 = new FileBody(huella4);
                    FileBody fileBody5 = new FileBody(huella5);
                         builder = MultipartEntityBuilder.create()
                         .setMode(HttpMultipartMode.BROWSER_COMPATIBLE)
                         .addPart("file", fileBody1) 
                         .addPart("file", fileBody2)
                         .addPart("file", fileBody3)
                         .addPart("file", fileBody4)
                         .addPart("file", fileBody5);
                }
                
                
                
            }
          
            }catch(Exception e)
            {
                System.err.println(e.getMessage());
            }
            
        
    
        HttpEntity multiPartEntity = builder.build();
        httppost.setEntity(multiPartEntity);
        CloseableHttpResponse response = httpclient.execute(httppost);
        HttpEntity entity = response.getEntity();
        String finalJSON=EntityUtils.toString(entity);
        jObject = new JSONObject(finalJSON);
        System.out.println(jObject.getString("mensaje"));
        response.close();
        return jObject;
    }   
    public JSONObject subirDocumentos ( JSONArray jarray) throws IOException 
    {
        
        JSONObject jObject=null;
        File fichero1=null,fichero2=null,fichero3=null,fichero4=null,fichero5=null;
        
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost httppost = new HttpPost("http://wsar.homelinux.com:3100/uploading_documentos");
        MultipartEntityBuilder builder=null;
 
        try{
            System.out.println("array length:"+jarray.length());
            if(jarray.length()==1)
            {
                fichero1=new File(jarray.getJSONObject(0).getString("ruta"));
                FileBody fileBody1 = new FileBody(fichero1);
                    builder = MultipartEntityBuilder.create()
                    .setMode(HttpMultipartMode.BROWSER_COMPATIBLE)
                    .addPart("file", fileBody1);
            }
            if(jarray.length()==2)
            {
                fichero1=new File(jarray.getJSONObject(0).getString("ruta"));
                fichero2=new File(jarray.getJSONObject(1).getString("ruta"));
                FileBody fileBody1 = new FileBody(fichero1);
                FileBody fileBody2 = new FileBody(fichero2);
                    builder = MultipartEntityBuilder.create()
                    .setMode(HttpMultipartMode.BROWSER_COMPATIBLE)
                    .addPart("file", fileBody1) 
                    .addPart("file", fileBody2);
            }
            if(jarray.length()==3)
            {
                fichero1=new File(jarray.getJSONObject(0).getString("ruta"));
                fichero2=new File(jarray.getJSONObject(1).getString("ruta"));
                fichero3=new File(jarray.getJSONObject(2).getString("ruta"));
                FileBody fileBody1 = new FileBody(fichero1);
                FileBody fileBody2 = new FileBody(fichero2);
                FileBody fileBody3 = new FileBody(fichero3);
                    builder = MultipartEntityBuilder.create()
                    .setMode(HttpMultipartMode.BROWSER_COMPATIBLE)
                    .addPart("file", fileBody1) 
                    .addPart("file", fileBody2)
                    .addPart("file", fileBody3);
            }
            if(jarray.length()==4)
            {
                fichero1=new File(jarray.getJSONObject(0).getString("ruta"));
                fichero2=new File(jarray.getJSONObject(1).getString("ruta"));
                fichero3=new File(jarray.getJSONObject(2).getString("ruta"));
                fichero4=new File(jarray.getJSONObject(3).getString("ruta"));
                FileBody fileBody1 = new FileBody(fichero1);
                FileBody fileBody2 = new FileBody(fichero2);
                FileBody fileBody3 = new FileBody(fichero3);
                FileBody fileBody4 = new FileBody(fichero4);
                    builder = MultipartEntityBuilder.create()
                    .setMode(HttpMultipartMode.BROWSER_COMPATIBLE)
                    .addPart("file", fileBody1) 
                    .addPart("file", fileBody2)
                    .addPart("file", fileBody3)
                    .addPart("file", fileBody4);
            }
            if(jarray.length()==5)
            {
                fichero1=new File(jarray.getJSONObject(0).getString("ruta"));
                fichero2=new File(jarray.getJSONObject(1).getString("ruta"));
                fichero3=new File(jarray.getJSONObject(2).getString("ruta"));
                fichero4=new File(jarray.getJSONObject(3).getString("ruta"));
                fichero5=new File(jarray.getJSONObject(4).getString("ruta"));
                FileBody fileBody1 = new FileBody(fichero1);
                FileBody fileBody2 = new FileBody(fichero2);
                FileBody fileBody3 = new FileBody(fichero3);
                FileBody fileBody4 = new FileBody(fichero4);
                FileBody fileBody5 = new FileBody(fichero5);
                    builder = MultipartEntityBuilder.create()
                    .setMode(HttpMultipartMode.BROWSER_COMPATIBLE)
                    .addPart("file", fileBody1) 
                    .addPart("file", fileBody2)
                    .addPart("file", fileBody3)
                    .addPart("file", fileBody4)
                    .addPart("file", fileBody5);
            }
            
        }catch(Exception e)
        {
            System.err.println(e.getMessage());
        }
        try{
            HttpEntity multiPartEntity = builder.build();
        httppost.setEntity(multiPartEntity);
        CloseableHttpResponse response = httpclient.execute(httppost);
        HttpEntity entity = response.getEntity();
        String finalJSON=EntityUtils.toString(entity);
        jObject = new JSONObject(finalJSON);
        System.out.println(jObject.getString("mensaje"));
        response.close();
        }catch(Exception e)
        {
            System.err.println(e.getMessage());
        }
        

        return jObject;
    } 
}
