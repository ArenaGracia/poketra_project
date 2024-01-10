/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import dbconnect.Dbconnect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Toky
 */
public class Matiere {
    String id;
    String nom;
    Unite unite;
    double prix;

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) throws Exception {
        if(prix<0) throw new Exception("La valeur du prix ne peut etre negative");
        this.prix = prix;
    }
    

    //    Methods
    public void insererMatiere (Connection connection) throws Exception{
        Statement statement = null;
        boolean isValid = false;
        ResultSet resultSet = null;
        try{
            if(connection == null){
                connection = Dbconnect.dbConnect();
                isValid = true;
            }
            connection.setAutoCommit(false);
            String query = "INSERT INTO Matiere (nom) VALUES('" + getNom() + "') RETURNING id_matiere";
            statement = connection.createStatement();
            resultSet=statement.executeQuery(query);
            String id = null;
            if(resultSet.next())id = resultSet.getString("id_matiere");
            String query1 = "INSERT INTO Matiere_Unite (id_matiere,id_unite) VALUES('" + id + "', '" + getUnite().getId() + "')";
            statement.executeUpdate(query1);
            connection.commit();
        }catch(Exception e){
           throw e;
        }finally{
            if(resultSet != null)resultSet.close();
            if(statement != null)statement.close();
            if(isValid)connection.close();
        }
    }
    
    public void insererPrix (Connection connection) throws Exception{
        Statement statement = null;
        boolean isValid = false;
        ResultSet resultSet = null;
        try{
            if(connection == null){
                connection = Dbconnect.dbConnect();
                isValid = true;
            }
            connection.setAutoCommit(false);
            String query = "INSERT INTO Matiere_prix VALUES(default, '" + this.getId() + "',"+this.getPrix()+")";
            statement = connection.createStatement();
            statement.executeUpdate(query);
            connection.commit();
        }catch(Exception e){
           throw e;
        }finally{
            if(resultSet != null)resultSet.close();
            if(statement != null)statement.close();
            if(isValid)connection.close();
        }
    }
    public Matiere getMatiere(Connection c) throws Exception{
         Statement s=null;
         ResultSet res=null;
         boolean isValid=false;
         Matiere matiere=new Matiere();
         try {
             if (c==null) {
                c= Dbconnect.dbConnect();
                isValid=true;
             }
             String sql="SELECT * FROM v_Matiere_prix WHERE id_matiere='"+this.getId()+"'";
             s=c.createStatement();
             res=s.executeQuery(sql);
             while(res.next()){
                 Unite unite=new Unite(res.getString("id_unite"),res.getString("nom_unite"));
                 matiere=new Matiere(res.getString("id_matiere"),res.getString("nom_matiere"),unite);
                 matiere.setPrix(res.getDouble("prix"));
             }
             
         } catch (Exception e) {
            e.printStackTrace();
         }
         finally{
             if (res !=null) res.close();
             if (s !=null) s.close();
             if (isValid) c.close();
         }
         return matiere;
     }
    
    public ArrayList<Matiere> getAllMatiere(Connection c) throws Exception{
         Statement s=null;
         ResultSet res=null;
         boolean isValid=false;
         ArrayList<Matiere> liste=new ArrayList<Matiere>();
         try {
             if (c==null) {
                c= Dbconnect.dbConnect();
                isValid=true;
             }
             String sql="SELECT * FROM v_Matiere_unite";
             s=c.createStatement();
             res=s.executeQuery(sql);
             while(res.next()){
                 Unite unite=new Unite(res.getString("id_unite"),res.getString("nom_unite"));
                 Matiere matiere=new Matiere(res.getString("id_matiere"),res.getString("nom_matiere"),unite);
                 liste.add(matiere);
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
    public Matiere() {}
    public Matiere(String id, String nom, Unite unite) throws IllegalArgumentException{
        setId(id);
        setNom(nom);
        setUnite(unite);
    }

    // Getters and setters
    public Unite getUnite() {
        return unite;
    }
    public void setUnite(Unite unite) throws IllegalArgumentException{
        if (unite == null || "".equals(unite)) throw new IllegalArgumentException("L'ID est invalide");
        this.unite = unite;
    } 
    public String getId() {
        return id;
    }
    public void setId(String id) throws IllegalArgumentException{
        if (id == null || "".equals(id))throw new IllegalArgumentException("L'ID est invalide");
        this.id = id;
    }
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) throws IllegalArgumentException{
        if (nom == null || nom.trim().isEmpty())throw new IllegalArgumentException("Le nom ne peut pas être vide ou nul.");
        this.nom = nom;
    }
    
}
