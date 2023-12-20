package modele;

import dbconnect.Dbconnect;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;


public class DetailProduit {
    int quantite;
    Matiere matiere;
    Produit produit;
       
    public ArrayList<DetailProduit> getAllType(Connection c) throws Exception{
        Statement s=null;
        ResultSet res=null;
        boolean isValid=false;
        ArrayList<DetailProduit> liste=new ArrayList<DetailProduit>();
        try {
            if (c==null) {
               c= Dbconnect.dbConnect();
               isValid=true;
            }
            String sql="SELECT * FROM DetailProduit";
            s=c.createStatement();
            res=s.executeQuery(sql);
            while(res.next()){
                //DetailProduit type=new DetailProduit(res.getInt("quantite"), res.getString("id_produit"),res.getString("id_matiere"));
                //liste.add(type);
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

    //Contructors
    public DetailProduit(){}

    public DetailProduit(int quantite, Matiere matiere, Produit produit) throws Exception {
        this.setQuantite(quantite);
        this.setProduit(produit);
        this.setMatiere(matiere);
    }
    
    
    //Getters qnd setters
    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) throws Exception {
        if(quantite <= 0)throw new  Exception("La quantite ne peut pqs etre negatif ou null");
        this.quantite = quantite;
    }

    public Matiere getMatiere() {
        return matiere;
    }

    public void setMatiere(Matiere matiere) {
        this.matiere = matiere;
    }

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }
    
    
    
}
