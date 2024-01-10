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
public class Look {
    String id;
    String nom;
    ArrayList<Matiere> matieres;

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

    public ArrayList<Matiere> getMatieres() {
        return matieres;
    }

    public void setMatieres(ArrayList<Matiere> matieres) throws Exception{
        if(matieres==null) throw new Exception("Liste null");
        this.matieres = matieres;
    }
     
     
    public Look() {}
    
    public Look(String id, String nom) throws Exception {
        setId(id);
        setNom(nom);
    }
    
    public Look(String id, String nom,ArrayList<Matiere> matieres) throws Exception {
        setId(id);
        setNom(nom);
        setMatieres(matieres);
    }
        
    public void insererLook(Connection c) throws Exception{
         Statement s=null;
         boolean isValid=false;
         try {
             if (c==null) {
                c= Dbconnect.dbConnect();
                isValid=true;
             }
             String sql="INSERT INTO Look (nom) VALUES ('"+this.getNom()+"')";
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
    
    public ArrayList<Look> getAllLook(Connection c) throws Exception{
         Statement s=null;
         ResultSet res=null;
         boolean isValid=false;
         ArrayList<Look> liste=new ArrayList<Look>();
         try {
             if (c==null) {
                c= Dbconnect.dbConnect();
                isValid=true;
             }
             String sql="SELECT * FROM look";
             s=c.createStatement();
             res=s.executeQuery(sql);
             while(res.next()){
                 Look look=new Look(res.getString("id_look"),res.getString("nom"));
                 liste.add(look);
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
    
    public Look getById(String idLook,Connection c) throws Exception{
         Statement s=null;
         ResultSet res=null;
         boolean isValid=false;
         Look look=null;
         ArrayList<Matiere> liste=new ArrayList<Matiere>();
         try {
             if (c==null) {
                c= Dbconnect.dbConnect();
                isValid=true;
             }
             String sql="SELECT * FROM v_matiere_look WHERE id_look='"+idLook+"'";
             System.out.println(sql);
             s=c.createStatement();
             res=s.executeQuery(sql);
             while(res.next()){
                look=new Look(res.getString("id_look"),res.getString("nom"));
                Unite unite=new Unite(res.getString("id_unite"),res.getString("nom_unite"));
                Matiere matiere=new Matiere(res.getString("id_matiere"),res.getString("nom_matiere"),unite);
                liste.add(matiere);
             }
             System.out.println(liste.size()+" lll");
            look.setMatieres(liste);
             
         } catch (Exception e) {
            e.printStackTrace();
         }
         finally{
             if (res !=null) res.close();
             if (s !=null) s.close();
             if (isValid) c.close();
         }
         return look;
     }
    
    public void insererListe(Connection c) throws Exception{
        Statement s=null;
        ResultSet res=null;
        boolean isValid=false;
        try{
            if (c==null) {
                c= Dbconnect.dbConnect();
                isValid=true;
            }
            c.setAutoCommit(false);
            s=c.createStatement();
            for(Matiere m: this.getMatieres()){
                String sql="INSERT INTO Look_matiere VALUES(DEFAULT,'"+this.getId()+"','"+m.getId()+"')";
                s.executeUpdate(sql);
            }
            c.commit();
        }catch(Exception e){
            c.rollback();
            throw e;
        }finally{
             if (s !=null) s.close();
             if (isValid) c.close();         
        }
    }
    
    public ArrayList<Matiere> getExterneMatiere(Connection c) throws Exception{
        boolean isValid=false;
        ArrayList<Matiere> liste=new ArrayList<Matiere>();
        Matiere m=new Matiere();
        
        try{
            if (c==null) {
                c= Dbconnect.dbConnect();
                isValid=true;
            }
            ArrayList<Matiere> in=this.getMatieres();
            ArrayList<Matiere> all=m.getAllMatiere(c);
            ArrayList<Matiere> intersect=new ArrayList<Matiere>();

            liste=all;
            if(liste.size()==0) throw new Exception("Toutes les matières sont déjà dans ce look");
        }catch(Exception e){
            throw e;
        }finally{
            if (isValid) c.close();
        }
        return liste;
    }
}
