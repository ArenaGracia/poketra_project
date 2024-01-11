package modele;

import dbconnect.Dbconnect;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;


public class Modele {
    String id; 
    Look look;
    Type type;
    Taille taille;
    double prixConfection;
    ArrayList<DetailModele> details;

    public Look getLook() {
        return look;
    }

    public void setLook(Look look) {
        this.look = look;
    }

    public ArrayList<DetailModele> getDetails() {
        return details;
    }

    public void setDetails(ArrayList<DetailModele> details) {
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

    public double getPrixConfection() {
        return prixConfection;
    }

    public void setPrixConfection(double prixConfection) {
        this.prixConfection = prixConfection;
    }
    
    
    
     //Contructors
    public Modele(){}
    public Modele(String id, Type type, Taille taille,Look look)throws Exception{
        setId(id);
        setTaille(taille);
        setType(type);
        setLook(look);
    }
    
    
    
    //Methods
     public void insererModele(Connection c) throws Exception{
        Statement s=null;
        ResultSet res=null;
        boolean isValid=false;
        try {
            if (c==null) {
               c= Dbconnect.dbConnect();
               isValid=true;
            }
            c.setAutoCommit(false);
            String sql="INSERT INTO Modele (id_look, id_type, id_taille) VALUES (" +
                        "'" + this.getLook().getId() + "'," +
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
            if (res !=null) res.close();
            if (s !=null) s.close();
            if (isValid) c.close();
        }
    }
    
    public void insererPrixModele(Connection connection) throws Exception{
        Statement s=null;
        ResultSet res=null;
        boolean isValid=false;
        try {
            if (connection==null) {
               connection= Dbconnect.dbConnect();
               isValid=true;
            }
            String sql="INSERT INTO modele_prix VALUES (DEFAULT,'"+this.getId()+"',"+this.getPrixConfection()+")" ;
            System.out.println(sql);
            s.executeUpdate(sql);
        } catch (Exception e) {
           e.printStackTrace();
        }
        finally{
            if (s !=null) s.close();
            if (isValid) connection.close();
        }       
    }
    
    public ArrayList<Modele> getModelePrix(Connection connection,double min,double max) throws Exception{
        if(min<0 || max<min) throw new Exception("L'intervallle de valeur est fausse");
        Statement s=null;
        ResultSet res=null;
        boolean isValid=false;
        ArrayList<Modele> liste=new ArrayList<Modele>();
         try {
             if (connection==null) {
                connection= Dbconnect.dbConnect();
                isValid=true;
             }
             String sql="SELECT * FROM v_prix_modele WHERE prix_confection BETWEEN "+min+" AND "+max+"";
             System.out.println(sql);
             s=connection.createStatement();
             res=s.executeQuery(sql);
             while(res.next()){
                Modele modele=new Modele();
                Matiere matiere=new Matiere();
                modele.setId(res.getString("id"));
                Taille taille=new Taille(res.getString("id_taille"),res.getString("nom_taille"));
                Type type=new Type(res.getString("id_type"),res.getString("nom_type"));
                Look look=new Look(res.getString("id_look"),res.getString("nom_look"));
                modele.setLook(look);
                modele.setPrixConfection(res.getDouble("prix_confection"));
                modele.setTaille(taille);
                modele.setTaille(taille);
                modele.setType(type);
             
                liste.add(modele);
             }
         } catch (Exception e) {
            e.printStackTrace();
         }
         finally{
             if (res !=null) res.close();
             if (s !=null) s.close();
             if (isValid) connection.close();
         }
         return liste;
    }
    public void insererDetail(Connection c) throws Exception{
        Statement s=null;
        boolean isValid=false;
        try {
            if (c==null) {
               c= Dbconnect.dbConnect();
               isValid=true;
            }
            
            for(DetailModele dp:this.getDetails()){
                String sql="INSERT INTO Detail_Modele (id_modele,id_matiere,qte) VALUES (" +
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
