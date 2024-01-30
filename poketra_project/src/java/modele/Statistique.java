package modele;

public class Statistique {
    Genre genre;
    double pourcentage;
    double nombre;

    public Statistique() {
    }

    public Statistique(Genre genre, double pourcentage,double nombre) throws Exception {
        this.setGenre(genre);
        this.setPourcentage(pourcentage);
        this.setNombre(nombre);
    }
    
    
    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public double getPourcentage() {
        return pourcentage;
    }

    public void setPourcentage(double pourcentage) throws Exception {
        if(pourcentage<0) throw new Exception("Pourcentage non valide");
        this.pourcentage = pourcentage;
    }   

    public double getNombre() {
        return nombre;
    }

    public void setNombre(double nombre) {
        this.nombre = nombre;
    }
    
    
}
