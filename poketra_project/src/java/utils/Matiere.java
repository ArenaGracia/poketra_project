/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import dbconnect.Dbconnect;
import java.sql.Connection;
import java.sql.Statement;

/**
 *
 * @author Toky
 */
public class Matiere {
    int id;
    String nom;

    public Matiere() {
    }

    public Matiere(int id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (id < 0) {
            throw new IllegalArgumentException("L'ID ne peut pas être négatif.");
        }
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
    
    public void insererMatiere(Connection c) throws Exception{
         Statement s=null;
         boolean isValid=false;
         try {
             if (c==null) {
                c= Dbconnect.dbConnect();
                isValid=true;
             }
         String sql="INSERT INTO matiere (nom) VALUES ('"+this.getNom()+"')";
         s.executeUpdate(sql);
         } catch (Exception e) {
            e.printStackTrace();
         }
         finally{
             if (isValid) {
                 c.close();
             }
             if (s !=null) {
                 s.close();
             }
         }
     }
    
    
}
