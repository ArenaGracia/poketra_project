package modele;

import dbconnect.Dbconnect;
import java.sql.Connection;
import java.sql.Date;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;

public class Fabrication {
    String id_fabrication;
    Modele modele;
    double nombre;
    Timestamp date;

    
    public ArrayList<Stock> checkStock(Connection c)throws Exception{
        ArrayList<Stock> liste= new ArrayList<Stock>(); 
        for (DetailModele detail : this.getModele().getDetails()) {
            double nbr=this.getNombre()*detail.getQuantite();
            Stock stock=new Stock();
            stock.setMatiere(detail.getMatiere());
            stock=stock.getStock(c);
            System.out.println("modele.Fabrication.checkStock() "+ stock.getReste() +"-"+ nbr);
            double n=Math.abs(stock.getReste()-nbr);
            if(stock.getReste()<nbr) throw new Exception("Il manque "+n+" "+detail.getMatiere().getNom());
            Stock sortie=new Stock();
            sortie.setSortie(nbr);
            sortie.setMatiere(detail.getMatiere());
            sortie.setEntrer(0);
            liste.add(sortie);
        }
        return liste;
    }
    
    public void insertFabrication(Connection c)throws Exception{
        Statement s=null;
        boolean isValid=false;
        ArrayList<Stock> stocks=this.checkStock(c);
        try {
            if (c==null) {
               c= Dbconnect.dbConnect();
               isValid=true;
            }
            c.setAutoCommit(false);
            for(Stock stock : stocks ){
                stock.setDate(this.getDate());
                String sql= "INSERT INTO Stock (id_matiere, entrer, sortie , date) VALUES ( " + 
                        "  \'" + stock.getMatiere().getId()+ "\', " +                       
                        "  " + stock.getEntrer() + ", " +stock.getSortie()+", "+ 
                        "  \'" + stock.getDate() + "\') " ;
                System.out.println(sql);
                s=c.createStatement();
                s.executeUpdate(sql);
            }
            String sql= "INSERT INTO Fabrication (id_modele, nb, date) VALUES ( " + 
                       "  \'" + this.getModele().getId()+ "\', " +                       
                       "  " + this.getNombre() + ", " +  
                       "  \'" + this.getDate() + "\') " ;
            System.out.println(sql);
            s=c.createStatement();
            s.executeUpdate(sql);
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
    
    
//    Constructors
    public Fabrication(){}
    

//    Getters and setters
    public String getId_fabrication() {
        return id_fabrication;
    }

    public void setId_fabrication(String id_fabrication)throws Exception {
        if(id_fabrication == null || "".equals(id_fabrication))throw new Exception("Invalide id de fabrication dans fabrication");
        this.id_fabrication = id_fabrication;
    }

    public Modele getModele() {
        return modele;
    }

    public void setModele(Modele modele) {
        this.modele = modele;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }
    
    

    public double getNombre() {
        return nombre;
    }

    public void setNombre(double nombre) throws Exception{
        if(nombre < 0)throw new Exception ("Invalid nombre dans Fabrication");
        this.nombre = nombre;
    }
}
