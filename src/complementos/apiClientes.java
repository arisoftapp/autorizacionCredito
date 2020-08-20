/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package complementos;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.swing.JOptionPane;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Desarrollo
 */
public class apiClientes {
    consultasBD consultas=new consultasBD();
    public JSONObject getCliente(String codigo)
    {
        String token=consultas.getToken();
       JSONObject data = null;
         try {
            
            URL url = new URL("http://wsar.homelinux.com:3100/cliente/"+codigo);//your url i.e fetch data from .
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
                System.out.print(finalJSON);
               if(jObject.getBoolean("success"))
               {
                   JSONArray jArray = jObject.getJSONArray("respuesta");
                   System.out.println(jObject.getBoolean("success"));
                       for (int i=0;i<jArray.length();i++){
                        JSONObject objeto = jArray.getJSONObject(i);
                        data=objeto;
                        //System.out.println(objeto);
                }
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
  
         return data;
    }
            public void cuadroDialogo(String mensaje)
    {
          JOptionPane.showMessageDialog(null, mensaje);
    }
}
