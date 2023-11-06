
package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import modelo.asistencias;

public class DaoAsistencia {
    
    Connection con;
    conexion cn=new conexion();
    PreparedStatement ps;
    ResultSet rs;
    
    public boolean insertar(asistencias a){
        String sql="insert into asistencia (id_empleado,tipo) values (?,?)";
        try{
            con=cn.conectar();
            ps=con.prepareStatement(sql);
            ps.setInt(1, a.getIdempleado());
            ps.setString(2, a.getTipo());
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
