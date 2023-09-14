
package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import modelo.Datos;

public class DaoDatos {
    
    Connection con;
    conexion cn=new conexion();
    PreparedStatement ps;
    ResultSet rs;
    
    public void Agregar(Datos d){
        String sql="insert into datos (empresa,ruc,razonS,telefono,direccion,correo,imagen) values (?,?,?,?,?,?,?)";
        try {
            con=cn.conectar();
            ps=con.prepareStatement(sql);
            ps.setString(1, d.getNombre());
            ps.setString(2, d.getRUC());
            ps.setString(3, d.getRasonS());
            ps.setString(4, d.getTelefono());
            ps.setString(5, d.getDireccion());
            ps.setString(6, d.getCorreo());
            ps.setBytes(7, d.getImagen());
            ps.executeUpdate();
        } catch (SQLException ex) {
            
        }finally {
            try {
                ps.close();
            } catch (SQLException ex) {
            }
        }
    }
    
}
