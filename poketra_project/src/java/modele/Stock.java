package modele;

import dbconnect.Dbconnect;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;

public class Stock {
    Matiere matiere;
    double entrer;
    double sortie;
    Timestamp date;

    public double getEntrer() {
        return entrer;
    }

    public void setEntrer(double entrer) throws Exception{
        if(entrer < 0 )throw new Exception("Nombre d'entrer invalide dans stock");
        this.entrer = entrer;
    }

    public double getSortie() {
        return sortie;
    }
    
    public double getReste(){
        return getEntrer()-getSortie();
    }

    public void setSortie(double sortie) throws Exception{
        if(sortie < 0 )throw new Exception("Nombre de sortie invalide dans stock");
        this.sortie = sortie;
    }

    
    
    public Matiere getMatiere() {
        return matiere;
    }

    public void setMatiere(Matiere matiere) {
        this.matiere = matiere;
    }
    
//    Class methods
    public Stock getStock(Connection c) throws Exception{
        Stock result = null;
        Statement s=null;
        ResultSet res=null;
        boolean isValid=false;
        try {
            if (c==null) {
                c= Dbconnect.dbConnect();
                isValid=true;
             }
             String sql= "SELECT * FROM v_stock " + 
                        " WHERE id_matiere = \'" + this.getMatiere().getId()+ "\'";
             System.out.println(sql);
             s=c.createStatement();
             res=s.executeQuery(sql);
             while(res.next()){
                result=new Stock();
                result.setEntrer(res.getDouble("entrer"));
                result.setSortie(res.getDouble("sortie"));
                Matiere matiere=new Matiere();
                matiere.setId(res.getString("id_matiere"));
                matiere.setNom(res.getString("nom"));
                result.setMatiere(matiere);
             }
         } catch (Exception e) {
            e.printStackTrace();
         }
         finally{
             if (res !=null) res.close();
             if (s !=null) s.close();
             if (isValid) c.close();
         }
        return result;
    }
    
    public ArrayList<Stock> getAllStock(Connection c) throws Exception{
        Statement s=null;
        ResultSet res=null;
        boolean isValid=false;
        ArrayList<Stock> liste=new ArrayList<Stock>();
        try {
            if (c==null) {
                c= Dbconnect.dbConnect();
                isValid=true;
             }
             String sql= "SELECT * FROM v_stock " + 
                        " WHERE id_matiere LIKE \'" + this.getMatiere().getId()+ "\'";
             
             s=c.createStatement();
             res=s.executeQuery(sql);
             System.out.println(sql);
             while(res.next()){
                Stock result=new Stock();
                result.setEntrer(res.getDouble("entrer"));
                result.setSortie(res.getDouble("sortie"));
                Matiere matiere2=new Matiere();
                matiere2.setId(res.getString("id_matiere"));
                matiere2.setNom(res.getString("nom"));
                result.setMatiere(matiere2);
                liste.add(result);
             }
             
         } catch (Exception e) {
            e.printStackTrace();
         }
         finally{
             if (res !=null) res.close();
             if (s !=null) s.close();
             if (isValid) c.close();
         }
        System.out.println(liste.size());
        return liste;
    }
    
    public void insertStock(Connection c)throws Exception{
        Statement s=null;
         boolean isValid=false;
         try {
             if (c==null) {
                c= Dbconnect.dbConnect();
                isValid=true;
             }
             String sql= "INSERT INTO Stock (id_matiere, entrer, sortie , date) VALUES ( " + 
                        "  \'" + this.getMatiere().getId()+ "\', " +                       
                        "  " + this.getEntrer() + ", " +this.getSortie()+", "+ 
                        "  \'" + this.getDate() + "\') " ;
             System.out.println(sql);
             s=c.createStatement();
             s.executeUpdate(sql);
         } catch (Exception e) {
            e.printStackTrace();
         }
         finally{
             if (s !=null) s.close();
             if (isValid) c.close();
         }
    }
    
    
//    Constructors  
    public Stock(){}
    
    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) throws Exception{
        if(date == null) throw new Exception("Date invalide dans stock");
        this.date = date;
    }
}
