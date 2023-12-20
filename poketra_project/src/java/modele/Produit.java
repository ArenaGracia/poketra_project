package modele;

import dbconnect.Dbconnect;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;


public class Produit {
    String id; 
    Type type;
    Taille taille;
    ArrayList<DetailProduit> details;

    public ArrayList<DetailProduit> getDetails() {
        return details;
    }

    public void setDetails(ArrayList<DetailProduit> details) {
        this.details = details;
    }
   

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Taille getTaille() {
        return taille;
    }

    public void setTaille(Taille taille) {
        this.taille = taille;
    }
    
    
    
     //Contructors
    public Produit(){}
    public Produit(String id, Type type, Taille taille)throws Exception{
        setId(id);
        setTaille(taille);
        setType(type);
    }
    
    
    
    //Methods
     public void insererProduit(Connection c) throws Exception{
        Statement s=null;
        ResultSet res=null;
        boolean isValid=false;
        try {
            if (c==null) {
               c= Dbconnect.dbConnect();
               isValid=true;
            }
            c.setAutoCommit(false);
            String sql="INSERT INTO Produit (id_type, id_taille) VALUES (" +
                        "'" + this.getType().getId() + "'," +
                        "'" + this.getTaille().getId()+ "') returning id" ;
            System.out.println(sql);
            s=c.createStatement();
            res=s.executeQuery(sql);
            if(res.next()) this.setId(res.getString("id"));
            this.insererDetail(c);
            c.commit();
        } catch (Exception e) {
            c.rollback();
           e.printStackTrace();
        }
        finally{
            if (s !=null) s.close();
            if (isValid) c.close();
        }
     }
     
    public void insererDetail(Connection c) throws Exception{
        Statement s=null;
        boolean isValid=false;
        try {
            if (c==null) {
               c= Dbconnect.dbConnect();
               isValid=true;
            }
            
            for(DetailProduit dp:this.getDetails()){
                String sql="INSERT INTO Detail_Produit (id_produit,id_matiere,qte) VALUES (" +
                        "'" + this.getId() + "','"+dp.getMatiere().getId()+"',"+dp.getQuantite()+")" ;
                System.out.println(sql);
                s=c.createStatement();
                s.executeUpdate(sql);    
            }
            
        } catch (Exception e) {
           e.printStackTrace();
        }
        finally{
            if (s !=null) s.close();
            if (isValid) c.close();
        }        
    }
    
    
}
