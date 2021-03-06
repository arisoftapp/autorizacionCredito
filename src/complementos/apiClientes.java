/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package complementos;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
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
        String token=consultas.getToken();
        Integer id_empresa=consultas.getIdEmpresa();
    public JSONObject getCliente(String codigo)
    {
        String token=consultas.getToken();
       JSONObject data = null;
         try {
            
            URL url = new URL("http://wsar.homelinux.com:3100/cliente/"+codigo+"/"+id_empresa);//your url i.e fetch data from .
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
    public JSONObject getClienteVH(String codigo,String id_empresa)
    {
        
       JSONObject data = null;
         try {
            
            URL url = new URL("http://wsar.homelinux.com:3100/cliente/"+codigo+"/"+id_empresa);//your url i.e fetch data from .
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Accept","application/json");
    
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
    public JSONObject getClientes()
    {
        String token=consultas.getToken();
        Integer id_empresa=consultas.getIdEmpresa();
       JSONObject data = null;
         try {
            
            URL url = new URL("http://wsar.homelinux.com:3100/clientes/"+id_empresa);//your url i.e fetch data from .
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
                        data=jObject;
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
    public boolean modificarCliente(JSONObject json)
    {
         boolean res=false;
         try {
            URL url = new URL("http://wsar.homelinux.com:3100/modificarcliente");//your url i.e fetch data from .
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
     public boolean insertarRutaDoc(JSONObject json)
    {
        boolean res=false;
         try {
            
            URL url = new URL("http://wsar.homelinux.com:3100/insertarRutaDocumentos");//your url i.e fetch data from .
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
     public JSONObject getRutasDoc(String codigoMacro)
    {
         JSONObject res = null;
         try {
            URL url = new URL("http://wsar.homelinux.com:3100/rutaDocumentos/"+codigoMacro+"/"+id_empresa);//your url i.e fetch data from .
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
                   //System.out.println(jObject.getBoolean("success"));
                   //cuadroDialogo(jObject.getString("mensaje"));
                   res=new JSONObject(finalJSON);
               }
               else
               {
                 
               }
          
            conn.disconnect();

        } catch (Exception e) {
            System.err.println("Exception in NetClientGet:- getRutasDoc" + e.getMessage());
             cuadroDialogo("Error"+e.getMessage());
        }
         return res;
    }
     public boolean eliminarRutaDoc(String id)
    {
        boolean res=false;
         try {
            
            URL url = new URL("http://wsar.homelinux.com:3100/eliminarDoc/"+id);//your url i.e fetch data from .
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("DELETE");
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
     public void downloadDoc(String nombre,String ruta)
    {
        try {
            URL url = new URL("http://wsar.homelinux.com:3100/download_documentos/"+nombre);//your url i.e fetch data from .
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
             System.out.println("ruta a guardar:"+ruta);
            File file = new File(ruta+"/"+nombre);
            copyInputStreamToFile(conn.getInputStream(), file);
            conn.disconnect();
            cuadroDialogo("Se descargo archivo en la ruta:"+ruta);

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
