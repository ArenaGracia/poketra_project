package modele;

import dbconnect.Dbconnect;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;


public class DetailModele {
    int quantite;
    Matiere matiere;
    Modele modele;

    //Getters qnd setters
    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) throws Exception {
        if(quantite <= 0)throw new  Exception("La quantite ne peut pas être negatif ou null");
        this.quantite = quantite;
    }

    public Matiere getMatiere() {
        return matiere;
    }

    public void setMatiere(Matiere matiere) {
        this.matiere = matiere;
    }

    public Modele getModele() {
        return modele;
    }

    public void setModele(Modele modele) {
        this.modele = modele;
    }

    //Contructors
    public DetailModele(){}

    public DetailModele(int quantite, Matiere matiere, Modele modele) throws Exception {
        this.setQuantite(quantite);
        this.setModele(modele);
        this.setMatiere(matiere);
    }   
       
    public ArrayList<DetailModele> getDetailByMatiere(String idMatiere,Connection c) throws Exception{
        Statement s=null;
        ResultSet res=null;
        boolean isValid=false;
        ArrayList<DetailModele> liste=new ArrayList<DetailModele>();
        try {
            if (c==null) {
               c= Dbconnect.dbConnect();
               isValid=true;
            }
            String sql="SELECT * FROM v_modele WHERE id_matiere='"+idMatiere+"'";
            s=c.createStatement();
            res=s.executeQuery(sql);
            while(res.next()){
                Modele modele=new Modele();
                Matiere matiere=new Matiere();
                matiere.setNom(res.getString("nom_matiere"));
                matiere.setId(idMatiere);
                modele.setId(res.getString("id"));
                Taille taille=new Taille(res.getString("id_taille"),res.getString("nom_taille"));
                Type type=new Type(res.getString("id_type"),res.getString("nom_type"));
                Look look=new Look(res.getString("id_look"),res.getString("nom_look"));
                modele.setLook(look);
                modele.setTaille(taille);
                modele.setTaille(taille);
                modele.setType(type);
                DetailModele d=new DetailModele(res.getInt("qte"),matiere,modele);
                liste.add(d);
            }
        } catch (Exception e) {
           e.printStackTrace();
        }
        finally{
            if (res !=null) res.close();
            if (s !=null) s.close();
            if (isValid) c.close();
        }
        return liste;
    } 
}
