package complementos;


import com.digitalpersona.onetouch.DPFPGlobal;
import com.digitalpersona.onetouch.DPFPTemplate;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import java.io.ByteArrayInputStream;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

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
    conexionBD con=new conexionBD();
    Connection conexion=con.conectar();
      public void insertBD(String usuario,String token,boolean sesion,String ruta,Integer id_empresa,String nom_empresa)
    {
        conexion=con.conectar();
          try {
              
          //Connection conexion=(Connection) DriverManager.getConnection("jdbc:mysql://localhost/credito","root" ,"");
          Statement comando=(Statement) conexion.createStatement();
          comando.executeUpdate("insert into usuario(nom_usuario,token,sesion,ruta,id_empresa,nom_empresa) values ('"+usuario+"','"+token+"',"+sesion+",'"+ruta+"',"+id_empresa+",'"+nom_empresa+"')");
          conexion.close();
          System.out.println("se inserto");
        } catch(SQLException ex){
          System.out.println("error:"+ex);
        }
    }
           public void update(String usuario,String token,boolean sesion,String ruta,Integer id_empresa,String nom_empresa)
    {
        conexion=con.conectar();
          try {
          //Connection conexion=(Connection) DriverManager.getConnection("jdbc:mysql://localhost/credito","root" ,"");
          Statement comando=(Statement) conexion.createStatement();
          PreparedStatement stmt;
          stmt = conexion.prepareStatement("UPDATE usuario SET token='"+token+"',ruta='"+ruta+"',sesion='"+sesion+"',id_empresa="+id_empresa+",nom_empresa='"+nom_empresa+"' WHERE nom_usuario='"+usuario+"'");
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
          conexion=con.conectar();
          boolean validar=false;
              try {
          //Connection conexion=(Connection) DriverManager.getConnection("jdbc:mysql://localhost/credito","root" ,"");
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
          conexion=con.conectar();
          String res="";
                     try {
          //Connection conexion=(Connection) DriverManager.getConnection("jdbc:mysql://localhost/credito","root" ,"");
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
      public Integer getIdEmpresa()
      {
          conexion=con.conectar();
          Integer res=0;
                     try {
          //Connection conexion=(Connection) DriverManager.getConnection("jdbc:mysql://localhost/credito","root" ,"");
          Statement comando=(Statement) conexion.createStatement();
          ResultSet registro = comando.executeQuery("select * from usuario");
	  if (registro.next()==true) {
            res=registro.getInt("id_empresa");
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
      public String getNombreEmpresa()
      {
          conexion=con.conectar();
          String res="";
                     try {
          //Connection conexion=(Connection) DriverManager.getConnection("jdbc:mysql://localhost/credito","root" ,"");
          Statement comando=(Statement) conexion.createStatement();
          ResultSet registro = comando.executeQuery("select * from usuario");
	  if (registro.next()==true) {
            res=registro.getString("nom_empresa");
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
          conexion=con.conectar();
          String res="";
                     try {
          //Connection conexion=(Connection) DriverManager.getConnection("jdbc:mysql://localhost/credito","root" ,"");
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
          conexion=con.conectar();
                               try {
          //Connection conexion=(Connection) DriverManager.getConnection("jdbc:mysql://localhost/credito","root" ,"");
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
          conexion=con.conectar();
          boolean validar=false;
              try {
          //Connection conexion=(Connection) DriverManager.getConnection("jdbc:mysql://localhost/credito","root" ,"");
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
       
       public void insertHuella(ByteArrayInputStream datosHuella, Integer tamañoHuella)
       {
           conexion=con.conectar();
        try {
            //Connection c=con.conectar(); //establece la conexion con la BD
            PreparedStatement guardarStmt = conexion.prepareStatement("INSERT INTO huella(data)values(?)");
            guardarStmt.setBinaryStream(1, datosHuella,tamañoHuella);
              guardarStmt.execute();
             JOptionPane.showMessageDialog(null,"Huella Guardada Correctamente");
     con.desconectar();
     guardarStmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(consultasBD.class.getName()).log(Level.SEVERE, null, ex);
        }
       }
       public DPFPTemplate getHuella()
       {
            DPFPTemplate referenceTemplate=null;
        try {
            PreparedStatement identificarStmt = conexion.prepareStatement("SELECT data FROM huella");
               ResultSet rs = identificarStmt.executeQuery();
                    while(rs.next()){

            byte templateBuffer[] = rs.getBytes("data");
            referenceTemplate = DPFPGlobal.getTemplateFactory().createTemplate(templateBuffer);

            }
        } catch (SQLException ex) {
            Logger.getLogger(consultasBD.class.getName()).log(Level.SEVERE, null, ex);
        }
       return referenceTemplate;
       }
}
