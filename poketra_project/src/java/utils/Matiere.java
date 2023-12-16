/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

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

    //    Methods
    public void insererMatiere (Connection connection) throws Exception{
        PreparedStatement statement = null;
        Statement viewStatement = null;
        boolean isValid = false;
        ResultSet resultSet = null;
        try{
            if(connection == null){
                connection = Dbconnect.dbConnect();
                isValid = true;
            }
            connection.setAutoCommit(false);
            getUnite().insererUnite(connection);
            String query = "INSERT INTO Matiere (nom_matiere) VALUES " + 
                            " (\'" + getNom() + "\') " + 
                            "RETURNING id_matiere";
            statement = connection.prepareStatement(nom, Statement.RETURN_GENERATED_KEYS);
            statement.executeUpdate();
            resultSet = statement.getGeneratedKeys();
            String id = null;
            if(resultSet.next())id = resultSet.getString("id_matiere"); 
            viewStatement = connection.createStatement();
            query = "INSERT INTO Matiere_Unite (id_matiere, id_unite) VALUES" + 
                    " (\'" + id + "\', \'" + getUnite().getId() + "\') ";
            viewStatement.executeUpdate(query);
            connection.commit();
        }catch(Exception e){
           throw e;
        }finally{
            if(isValid)connection.close();
            if(statement != null)statement.close();
            if(viewStatement != null)statement.close();
            if(resultSet != null)resultSet.close();
        }
    }
    
    public ArrayList<Matiere> getAllMatiere(Connection connection) throws Exception{
        ArrayList<Matiere> result = new ArrayList<Matiere>();
        Statement statement = null;
        ResultSet resultSet = null;
        boolean isValid = false;
        try{
            if(connection == null){
                connection = Dbconnect.dbConnect();
                isValid = true;
            }
            statement = connection.createStatement();
            String query = "SELECT * FROM v_Matiere_Unite";
            resultSet = statement.executeQuery(query);
            while(resultSet.next()){
               Matiere matiere = new Matiere();
               Unite u = new Unite(resultSet.getString("id_unite"), resultSet.getString("nom_unite"));
               String nomMatiere = resultSet.getString("nom_matiere");
               String idMatiere = resultSet.getString("id_matiere");
               matiere.setId(idMatiere);
               matiere.setNom(nomMatiere);
               matiere.setUnite(u);
               result.add(matiere);
            }
        }catch(Exception e){
           throw e;
        }finally{
           if(isValid)connection.close();
           if(statement != null)statement.close();
           if(resultSet != null)resultSet.close();
        }
        return result;
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
