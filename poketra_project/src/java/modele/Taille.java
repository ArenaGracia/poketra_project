/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import dbconnect.Dbconnect;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Toky
 */
public class Taille {
    String id;
    String nom;

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
    public Taille() {}
    
    public Taille(String id, String nom) throws Exception {
        setId(id);
        setNom(nom);
    }
        
    public void insererTaille(Connection c) throws Exception{
        Statement s=null;
        boolean isValid=false;
        try {
            if (c==null) {
               c= Dbconnect.dbConnect();
               isValid=true;
            }
            String sql="INSERT INTO Taille (nom) VALUES ('"+this.getNom()+"')";
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
    
    public Taille getById(Connection c) throws Exception{
        Statement s=null;
        ResultSet res=null;
        boolean isValid=false;
        Taille taille=null;
        try {
            if (c==null) {
               c= Dbconnect.dbConnect();
               isValid=true;
            }
            String sql="SELECT * FROM taille WHERE id_taille='"+this.getId()+"'";
            s=c.createStatement();
            res=s.executeQuery(sql);
            while(res.next()){
                taille=new Taille(res.getString("id_taille"),res.getString("nom"));
            }

        } catch (Exception e) {
           e.printStackTrace();
        }
        finally{
            if (res !=null) res.close();
            if (s !=null) s.close();
            if (isValid) c.close();
        }
        return taille;
    }
     
    
    public ArrayList<Taille> getAllTaille(Connection c) throws Exception{
        Statement s=null;
        ResultSet res=null;
        boolean isValid=false;
        ArrayList<Taille> liste=new ArrayList<Taille>();
        try {
            if (c==null) {
               c= Dbconnect.dbConnect();
               isValid=true;
            }
            String sql="SELECT * FROM taille";
            s=c.createStatement();
            res=s.executeQuery(sql);
            while(res.next()){
                Taille taille=new Taille(res.getString("id_taille"),res.getString("nom"));
                liste.add(taille);
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
