
package modelo;
public class usuarios {
    
    int idUser;
    int idempleado;
    String usuario;
    String password;
    String tipo;

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public usuarios() {
    }

    public usuarios(int idUser, int idempleado, String usuario, String password, String tipo) {
        this.idUser = idUser;
        this.idempleado = idempleado;
        this.usuario = usuario;
        this.password = password;
        this.tipo = tipo;
    }

    

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdempleado() {
        return idempleado;
    }

    public void setIdempleado(int idempleado) {
        this.idempleado = idempleado;
    }

   

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    
}
