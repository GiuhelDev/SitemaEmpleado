
package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import modelo.usuarios;

public class DaoUsuarios {
    
    Connection con;
    conexion cn=new conexion();
    PreparedStatement ps;
    ResultSet rs;
    
    public usuarios login(String user,String pass){
        usuarios us=new usuarios();
        String sql="select * from usuarios where usuario='"+user+"' and pass=aes_encrypt('"+pass+"','clave')";
        try{
            con=cn.conectar();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                us.setIdUser(rs.getInt(1));
                us.setNombre(rs.getString(2));
                us.setUsuario(rs.getString(3));
                us.setPassword(rs.getString(4));
                us.setTipo(rs.getString(5));
            }
        }catch(Exception e){
            JOptionPane.showConfirmDialog(null, e);
        }
        return us;
    }
    
    public boolean insertar(String nombre,String user,String pas,String tipo){
        String sql="INSERT into usuarios (nombre,usuario,pass,tipo) VALUES \n" +
                "('"+nombre+"','"+user+"',aes_encrypt('"+pas+"','clave'),'"+tipo+"');";
        try{
            con=cn.conectar();
            ps=con.prepareStatement(sql);
            int n=ps.executeUpdate();
            if(n!=0){
                return true;
            }else{
                return false;
            }
        }catch(Exception e){
            JOptionPane.showConfirmDialog(null, e);
            return false;
        }
    }
    
    public List Listar(){
        List<usuarios> lista=new ArrayList<>();
        String sql="select id_user,nombre,usuario,pass,tipo from usuarios";
        try{
            con=cn.conectar();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                usuarios u=new usuarios();
                u.setIdUser(rs.getInt(1));
                u.setNombre(rs.getString(2));
                u.setUsuario(rs.getString(3));
                u.setPassword(rs.getString(4));
                u.setTipo(rs.getString(5));
                lista.add(u);
            }
        }catch(Exception e){
            JOptionPane.showConfirmDialog(null, e);
        }
        return lista;
    }
    
    public boolean editar(int id,String nombre,String user,String pas,String tipo){
       String sql="update usuarios set nombre='"+nombre+"',usuario='"+user+"',"
               + "pass=aes_encrypt('"+pas+"','clave'), tipo='"+tipo+"' where id_user="+id;
       try{
            con=cn.conectar();
            ps=con.prepareStatement(sql);
            int n=ps.executeUpdate();
            if(n!=0){
                return true;
            }else{
                return false;
            }
        }catch(Exception e){
            JOptionPane.showConfirmDialog(null, e);
            return false;
        }
   }
    
    public boolean elimiar(usuarios usu){
       String sql="delete from usuarios where id_user=?";
       try{
            con=cn.conectar();
            ps=con.prepareStatement(sql); 
            ps.setInt(1, usu.getIdUser());
            int n=ps.executeUpdate();
            if(n!=0){
                return true;
            }else{
                return false;
            }
        }catch(Exception e){
            JOptionPane.showConfirmDialog(null, e);
            return false;
        }
   }
    
    public boolean Buscar(usuarios usu){
       String sql="SELECT id_user,nombre,usuario,aes_decrypt(usuarios.pass,'clave')as pass,tipo from usuarios where id_user=?";
       try{
            con=cn.conectar();
            ps=con.prepareStatement(sql);
            ps.setInt(1, usu.getIdUser());
            rs=ps.executeQuery();
            if(rs.next()){
                usu.setIdUser(rs.getInt(1));
                usu.setNombre(rs.getString(2));
                usu.setUsuario(rs.getString(3));
                usu.setPassword(rs.getString(4));
                usu.setTipo(rs.getString(5));
                return true;
            }else{
                 return false;
            }
        }catch(Exception e){
            JOptionPane.showConfirmDialog(null, e);
            return false;
        }
   }
    public int CantUsuarios(){
       String sql="SELECT COUNT(id_user)as cant from usuarios";
       int cant = 0;
       try{
            con=cn.conectar();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            if(rs.next()){
                cant=rs.getInt(1);
                return cant;
            }else{
                 return 0;
            }
        }catch(Exception e){
            JOptionPane.showConfirmDialog(null, e);
            return 0;
        }
   }   
}
