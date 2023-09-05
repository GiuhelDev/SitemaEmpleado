
package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import modelo.pago;


public class DaoPago {
    Connection con;
    conexion cn=new conexion();
    PreparedStatement ps;
    ResultSet rs;
    
    public boolean insertar(pago p){
        String sql="insert into pago (idempleado,total,fecha1,fecha2) values (?,?,?,?)";
        try{
            con=cn.conectar();
            ps=con.prepareStatement(sql);
            ps.setInt(1, p.getIdempleado());
            ps.setDouble(2, p.getTotal());
            ps.setDate(3, p.getFecha1());
            ps.setDate(4, p.getFecha2());
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
