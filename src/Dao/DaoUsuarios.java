
package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import modelo.usuarios;

public class DaoUsuarios {
    
    Connection con;
    conexion cn=new conexion();
    PreparedStatement ps;
    ResultSet rs;
    
    public usuarios login(String user,String pass){
        usuarios us=new usuarios();
        String sql="select * from usuarios where usuario='"+user+"' and password='"+pass+"'";
        try{
            con=cn.conectar();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                us.setIdUser(rs.getInt(1));
                us.setNombre(rs.getString(2));
                us.setUsuario(rs.getString(3));
                us.setPassword(rs.getString(4));
            }
        }catch(Exception e){
            JOptionPane.showConfirmDialog(null, e);
        }
        return us;
    }
    
    public boolean insertar(usuarios u){
        String sql="insert into usuarios (nombre,usuario,password,tipo) values (?,?,?,?)";
        try{
            con=cn.conectar();
            ps=con.prepareStatement(sql);
            ps.setString(1, u.getNombre());
            ps.setString(2, u.getUsuario());
            ps.setString(3, u.getPassword());
            ps.setString(4, u.getTipo());
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
}
