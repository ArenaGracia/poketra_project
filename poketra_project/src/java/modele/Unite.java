/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import dbconnect.*;
import java.util.ArrayList;

/**
 *
 * @author Toky
 */
public class Unite {
    String id;
    String nom;

    public Unite(String id, String nom) {
        setId(id);
        setNom(nom);
    }

    public Unite() {
    }

    public String getId() {
        return id;
    }

   public void setId(String id) throws IllegalArgumentException{
        if (id == null || "".equals(id))throw new IllegalArgumentException("L'ID ne peut pas être négatif.");
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

     public void setNom(String nom) {
        if (nom == null || nom.trim().isEmpty()) {
            throw new IllegalArgumentException("Le nom ne peut pas être vide ou nul.");
        }
        this.nom = nom;
    }
    
    public void insererUnite(Connection c) throws Exception{
         Statement s=null;
         boolean isValid=false;
         try {
             if (c==null) {
                c= Dbconnect.dbConnect();
                isValid=true;
             }
             String sql="INSERT INTO unite (nom) VALUES ('"+this.getNom()+"')";
             System.out.println(sql);
             s=c.createStatement();
             s.executeUpdate(sql);
         } catch (Exception e) {
            e.printStackTrace();
         }
         finally{
             if (s !=null) {
                 s.close();
             }
             if (isValid) {
                 c.close();
             }
         }
     }
    
    public ArrayList<Unite> getAllUnite(Connection c) throws Exception{
         Statement s=null;
         ResultSet res=null;
         boolean isValid=false;
         ArrayList<Unite> liste=new ArrayList<Unite>();
         try {
             if (c==null) {
                c= Dbconnect.dbConnect();
                isValid=true;
             }
             String sql="SELECT * FROM Unite";
             s=c.createStatement();
             res=s.executeQuery(sql);
             while(res.next()){
                 Unite unite=new Unite(res.getString("id_unite"),res.getString("nom"));
                 liste.add(unite);
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
