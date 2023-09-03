
package Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

public class conexion {
    
    Connection con;
    String bd="bdempleados";
    String url="jdbc:mysql://127.0.0.1/"+bd;
    String user="root";
    String pass="";
    
    public Connection conectar(){
        try{
            Class.forName("org.gjt.mm.mysql.Driver");
            con=DriverManager.getConnection(url,user,pass);
        }catch(Exception e){
            JOptionPane.showConfirmDialog(null, e);
        }
        return con;
    }
}
