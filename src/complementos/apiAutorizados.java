/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package complementos;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.swing.JOptionPane;
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
        
     public void cuadroDialogo(String mensaje)
    {
          JOptionPane.showMessageDialog(null, mensaje);
    }

    
}
