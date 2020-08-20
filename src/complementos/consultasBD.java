package complementos;


import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Desarrollo
 */
public class consultasBD {
      public void insertBD(String usuario,String token,boolean sesion,String ruta)
    {
          try {
          Connection conexion=(Connection) DriverManager.getConnection("jdbc:mysql://localhost/credito","root" ,"");
          Statement comando=(Statement) conexion.createStatement();
          comando.executeUpdate("insert into usuario(nom_usuario,token,sesion,ruta) values ('"+usuario+"','"+token+"',"+sesion+",'"+ruta+"')");
          conexion.close();
          System.out.println("se inserto");
        } catch(SQLException ex){
          System.out.println("error:"+ex);
        }
    }
           public void update(String usuario,String token,boolean sesion,String ruta)
    {
          try {
          Connection conexion=(Connection) DriverManager.getConnection("jdbc:mysql://localhost/credito","root" ,"");
          Statement comando=(Statement) conexion.createStatement();
          PreparedStatement stmt;
          stmt = conexion.prepareStatement("UPDATE usuario SET token='"+token+"',ruta='"+ruta+"',sesion='"+sesion+"' WHERE nom_usuario='"+usuario+"'");
          int retorno = stmt.executeUpdate();
          if(retorno==1)
          {
              System.out.println("se modifico");
          }
          else
          {
               System.out.println("no se pudo modifico");
          }
          conexion.close();
          
        } catch(SQLException ex){
          System.out.println("error:"+ex);
        }
    }
      public boolean buscarPorUsuario(String usuario)
      {
          boolean validar=false;
              try {
          Connection conexion=(Connection) DriverManager.getConnection("jdbc:mysql://localhost/credito","root" ,"");
          Statement comando=(Statement) conexion.createStatement();
          ResultSet registro = comando.executeQuery("select * from usuario where nom_usuario='"+usuario+"'");
	  if (registro.next()==true) {
            System.out.println(registro.getString("nom_usuario"));
            validar=true;
             System.out.println("encontro");
	  } else {
	  validar=false;
          System.out.println("no encontro");
	  }
          conexion.close();
         
        } catch(SQLException ex){
          System.out.println("error:"+ex);
        }
              return validar;
      }
      public String getUsuario()
      {
          String res="";
                     try {
          Connection conexion=(Connection) DriverManager.getConnection("jdbc:mysql://localhost/credito","root" ,"");
          Statement comando=(Statement) conexion.createStatement();
          ResultSet registro = comando.executeQuery("select * from usuario");
	  if (registro.next()==true) {
            res=registro.getString("nom_usuario");
            System.out.println(res);
             System.out.println("encontro");
	  } else {
	
          System.out.println("no encontro");
	  }
          conexion.close();
         
        } catch(SQLException ex){
          System.out.println("error:"+ex);
        }
          return res;
      }
            public String getToken()
      {
          String res="";
                     try {
          Connection conexion=(Connection) DriverManager.getConnection("jdbc:mysql://localhost/credito","root" ,"");
          Statement comando=(Statement) conexion.createStatement();
          ResultSet registro = comando.executeQuery("select * from usuario");
	  if (registro.next()==true) {
            res=registro.getString("token");
            System.out.println(res);
             System.out.println("encontro");
	  } else {
	
          System.out.println("no encontro");
	  }
          conexion.close();
         
        } catch(SQLException ex){
          System.out.println("error:"+ex);
        }
          return res;
      }
      public void deleteUserLogout()
      {
                               try {
          Connection conexion=(Connection) DriverManager.getConnection("jdbc:mysql://localhost/credito","root" ,"");
          PreparedStatement st = conexion.prepareStatement("DELETE FROM usuario");
          st.executeUpdate();
          conexion.close();
         System.out.println("se elimino ");
        } catch(SQLException ex){
          System.out.println("error:"+ex);
        }
      }
       public boolean tablaVacia()
      {
          boolean validar=false;
              try {
          Connection conexion=(Connection) DriverManager.getConnection("jdbc:mysql://localhost/credito","root" ,"");
          Statement comando=(Statement) conexion.createStatement();
          ResultSet registro = comando.executeQuery("select * from usuario");
	  if (registro.next()==true) {
            validar=false;
             System.out.println("encontro");
	  } else {
	  validar=true;
          System.out.println("no encontro");
	  }
          conexion.close();
         
        } catch(SQLException ex){
             validar=true;
          System.out.println("error:"+ex);
        }
              return validar;
      }
    
}
