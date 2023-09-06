
package Dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import modelo.nomina;
import modelo.pago;


public class DaoPago {
    Connection con;
    conexion cn=new conexion();
    PreparedStatement ps;
    ResultSet rs;
    
    public boolean insertar(pago p){
        String sql="insert into pagos (idempleado,total,fecha1,fecha2) values (?,?,?,?)";
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
    
    public pago Total(int ide,String f1,String f2){
        pago pa=new pago();
        String sql="select sum(total) from nomina WHERE idempleado="+ide+" and fecha BETWEEN '"+f1+"' and '"+f2+"'";
        try{
            con=cn.conectar();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                pa.setTotal(rs.getDouble(1));
            }
        }catch(Exception e){
            JOptionPane.showConfirmDialog(null, e);
        }
        return pa;
    }
   
    public boolean editar(int ide,String f1,String f2){
       String sql="UPDATE nomina set estado='pagado' WHERE idempleado="+ide+" and fecha BETWEEN  '"+f1+"' and '"+f2+"'";
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
}
