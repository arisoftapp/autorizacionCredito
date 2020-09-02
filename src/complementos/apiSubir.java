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
         public JSONObject subirHuella (File huella1,File huella2,File huella3,File huella4,File huella5) throws IOException 
    {
        JSONObject jObject=null;
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost httppost = new HttpPost("http://wsar.homelinux.com:3100/uploading_huellas");
        FileBody fileBody1 = new FileBody(huella1);
        FileBody fileBody2 = new FileBody(huella2);
        FileBody fileBody3 = new FileBody(huella3);
        FileBody fileBody4 = new FileBody(huella4);
        FileBody fileBody5 = new FileBody(huella5);
        MultipartEntityBuilder builder = MultipartEntityBuilder.create()
                         .setMode(HttpMultipartMode.BROWSER_COMPATIBLE)
                         .addPart("file", fileBody1)
                         .addPart("file", fileBody2)
                         .addPart("file", fileBody3)
                         .addPart("file", fileBody4)
                         .addPart("file", fileBody5);
        HttpEntity multiPartEntity = builder.build();
        httppost.setEntity(multiPartEntity);
        CloseableHttpResponse response = httpclient.execute(httppost);
        HttpEntity entity = response.getEntity();
        String finalJSON=EntityUtils.toString(entity);
        jObject = new JSONObject(finalJSON);
        response.close();
        return jObject;
    }   
    
}
