
package modelo;

public class empleado {
    
    int id;
    String nombre;
    String apellido;
    String tipodoc;
    String doc;
    int idArea;
    int idcargo;
    String telefono;
    String correo;

    public empleado() {
    }

    public empleado(int id, String nombre, String apellido, String tipodoc, String doc, int idArea, int idcargo, String telefono, String correo) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.tipodoc = tipodoc;
        this.doc = doc;
        this.idArea = idArea;
        this.idcargo = idcargo;
        this.telefono = telefono;
        this.correo = correo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTipodoc() {
        return tipodoc;
    }

    public void setTipodoc(String tipodoc) {
        this.tipodoc = tipodoc;
    }

    public String getDoc() {
        return doc;
    }

    public void setDoc(String doc) {
        this.doc = doc;
    }

    public int getIdArea() {
        return idArea;
    }

    public void setIdArea(int idArea) {
        this.idArea = idArea;
    }

    public int getIdcargo() {
        return idcargo;
    }

    public void setIdcargo(int idcargo) {
        this.idcargo = idcargo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
    
    
}
