package modele;

public class ModeleSpecialite {
    String idModeleSpecialite;
    Modele modele;
    Specialite specialite;
    int nombre;
    double duree;
    
//    Class methods
    
//    Constructors
    public ModeleSpecialite(){}

    public ModeleSpecialite(Modele modele, Specialite specialite, int nombre, double duree) throws Exception {
        this.setDuree(duree);
        this.setNombre(nombre);
        this.setSpecialite(specialite);
        this.setModele(modele);
    }
   
//    Getters and setters
    public Specialite getSpecialite() {
        return specialite;
    }

    public void setSpecialite(Specialite specialite) throws Exception{
        if(specialite == null) throw new Exception("La specialite dans modele specialite est invalide");
        this.specialite = specialite;
    }

    public int getNombre() {
        return nombre;
    }

    public void setNombre(int nombre) throws Exception{
        if(nombre <= 0)throw new Exception("Le nombre dans Modele specialite est invalide");
        this.nombre = nombre;
    }
    
    public double getDuree() {
        return duree;
    }

    public void setDuree(double duree) throws Exception{
        if(duree < 0)throw new Exception("Duree dans modele specialite invalide");
        this.duree = duree;
    }

    public String getIdModeleSpecialite() {
        return idModeleSpecialite;
    }

    public void setIdModeleSpecialite(String idModeleSpecialite) {
        this.idModeleSpecialite = idModeleSpecialite;
    }

    public Modele getModele() {
        return modele;
    }

    public void setModele(Modele modele) {
        this.modele = modele;
    }
    
    
}
