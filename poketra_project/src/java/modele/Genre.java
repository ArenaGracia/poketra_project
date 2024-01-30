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
 * @author ITU
 */
public class Genre {
    String id;
    String nom;
<<<<<<< Updated upstream
=======
    String couleur;
>>>>>>> Stashed changes

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }
<<<<<<< Updated upstream

=======
    
    public String getCouleur(){
        if(getNom().equals("Femme")) return "rgba(255, 99, 132, 0.5)";
        if(getNom().equals("Homme")) return "rgba(54, 162, 235, 0.5)";
        return null;
    }

    public Genre(String id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    public Genre() {
    }

    
>>>>>>> Stashed changes
    public void setNom(String nom) throws Exception {
        if(nom.equalsIgnoreCase("") || nom.trim().length()==0 || nom==null) throw new Exception("Nom du genre null");
        this.nom = nom;
    }
    
    public ArrayList<Genre> getAll(Connection con) throws Exception{
        ArrayList<Genre> lg=new ArrayList<Genre>();
        Statement stmt=null;  
        ResultSet res=null;
        boolean estValid=false;
        
        try{
            if(con==null){
                con=Dbconnect.dbConnect();
                estValid=true;
            }
            stmt=con.createStatement();
            String sql="SELECT*FROM Genre";   
            res=stmt.executeQuery(sql);
            while(res.next()){
                Genre g=new Genre();
                g.setId(res.getString("id"));
                g.setNom(res.getString("nom"));
                lg.add(g);
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }finally{
            if(res!=null) res.close();
            if(stmt!=null) stmt.close();
            if(estValid) con.close();
        }    
        return lg;
    }
    
    public void inserer(Connection con) throws Exception{
        Statement stmt=null;
        boolean estValid=false; 
        try{
            if(con==null){
                con=Dbconnect.dbConnect();
                estValid=true;
            }
            String sql="INSERT INTO Genre VALUES(DEFAULT,'"+this.getNom()+"')";
            stmt=con.createStatement();
            stmt.executeUpdate(sql);
        }catch(Exception e){
            throw e;
        }finally{
            if(stmt!=null) stmt.close();
            if(estValid) con.close();
        }
    }
}
