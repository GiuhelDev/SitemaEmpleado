
package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
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
    
    public DefaultTableModel listar(){
        DefaultTableModel modelo;
        
        String [] titulos={"ID_A","ID_E","Empleado","Fecha","Hora","estado","Tipo"};
        
        String [] registros=new String[7];
        modelo=new DefaultTableModel(null,titulos);
        
        String sql="SELECT a.id,e.id_empleado,concat(e.nombre,' ',e.apellido)as empleado,a.fecha,a.Hora,a.estado,a.tipo FROM asistencia a\n" +
                    "INNER JOIN empleados e\n" +
                    "on a.id_empleado=e.id_empleado order by a.id";
        
        try{
            con=cn.conectar();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                registros[0]=rs.getString("id");
                registros[1]=rs.getString("id_empleado");
                registros[2]=rs.getString("empleado");
                registros[3]=rs.getString("fecha");
                registros[4]=rs.getString("Hora");
                registros[5]=rs.getString("estado");
                registros[6]=rs.getString("tipo");
                modelo.addRow(registros);
            }
            return modelo;
        }catch(Exception e){
            JOptionPane.showConfirmDialog(null, e);
            return null;
        }
    }
    
    public int obtenerMaxID(){
       String sql="SELECT max(id)as maximo FROM asistencia";
       int id = 0;
       try{
            con=cn.conectar();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            if(rs.next()){
                id=rs.getInt(1);
                return id;
            }else{
                 return 0;
            }
        }catch(Exception e){
            JOptionPane.showConfirmDialog(null, e);
            return 0;
        }
   }
     public String obtenerHora(int id){
       String sql="SELECT Hora FROM asistencia where id="+id;
       try{
            con=cn.conectar();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            if(rs.next()){
                return "1";
            }else{
                return "0";
            }
        }catch(Exception e){
            JOptionPane.showConfirmDialog(null, e);
            return "0";
        }
   }
    public boolean editarEstado(asistencias as){
       String sql="update asistencia set estado=? where id=?";
       try{
            con=cn.conectar();
            ps=con.prepareStatement(sql);
            ps.setString(1, as.getEstado());
            ps.setInt(2, as.getId());
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
