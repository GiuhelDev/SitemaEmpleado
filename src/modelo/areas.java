
package modelo;

public class areas {
    
    int idarea;
    String nomArea;

    public areas() {
    }

    public areas(int idarea, String nomArea) {
        this.idarea = idarea;
        this.nomArea = nomArea;
    }

    public int getIdarea() {
        return idarea;
    }

    public void setIdarea(int idarea) {
        this.idarea = idarea;
    }

    public String getNomArea() {
        return nomArea;
    }

    public void setNomArea(String nomArea) {
        this.nomArea = nomArea;
    }
    
    
}
