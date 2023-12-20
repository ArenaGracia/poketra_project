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
public class Type {
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
    public Type() {}
    
    public Type(String id, String nom) throws Exception {
        setId(id);
        setNom(nom);
    }
        
    public void insererType(Connection c) throws Exception{
         Statement s=null;
         boolean isValid=false;
         try {
             if (c==null) {
                c= Dbconnect.dbConnect();
                isValid=true;
             }
             String sql="INSERT INTO Type (nom) VALUES ('"+this.getNom()+"')";
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
    
    public Type getById(Connection c) throws Exception{
         Statement s=null;
         ResultSet res=null;
         boolean isValid=false;
         Type type=null;
         try {
             if (c==null) {
                c= Dbconnect.dbConnect();
                isValid=true;
             }
             String sql="SELECT * FROM type WHERE id_type='"+this.getId()+"'";
             s=c.createStatement();
             res=s.executeQuery(sql);
             while(res.next()){
                 type=new Type(res.getString("id_type"),res.getString("nom"));
             }
             
         } catch (Exception e) {
            e.printStackTrace();
         }
         finally{
             if (res !=null) res.close();
             if (s !=null) s.close();
             if (isValid) c.close();
         }
         return type;
     }
     
    
    public ArrayList<Type> getAllType(Connection c) throws Exception{
         Statement s=null;
         ResultSet res=null;
         boolean isValid=false;
         ArrayList<Type> liste=new ArrayList<Type>();
         try {
             if (c==null) {
                c= Dbconnect.dbConnect();
                isValid=true;
             }
             String sql="SELECT * FROM type";
             s=c.createStatement();
             res=s.executeQuery(sql);
             while(res.next()){
                 Type type=new Type(res.getString("id_type"),res.getString("nom"));
                 liste.add(type);
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
