/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import dbconnect.Dbconnect;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author ITU
 */
public class Employe {
    String id;
    String nom;
    String prenom;
    Genre genre;
    Date dateNaissance;
    ArrayList<Specialite> specialites;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) throws Exception{
        Date today=new Date(System.currentTimeMillis());
        if(today.getYear()-dateNaissance.getYear()<18) throw new Exception("Il faut être majeur");
        this.dateNaissance = dateNaissance;
    }

    public ArrayList<Specialite> getSpecialites() {
        return specialites;
    }

    public void setSpecialites(ArrayList<Specialite> specialites) {
        this.specialites = specialites;
    }
    
    public void insererEmployer(Connection con) throws Exception{
        boolean estValid=false;
        Statement stmt=null;
        ResultSet res=null;
        try{
            if(con==null){
                con=Dbconnect.dbConnect();
                estValid=true;
            }
            con.setAutoCommit(false);
            String sql="INSERT INTO Employe VALUES(default,'"+this.getNom()+"','"+this.getPrenom()+"','"+this.getGenre().getId()+"','"+this.getDateNaissance()+"') returning id";
            stmt=con.createStatement();
            res=stmt.executeQuery(sql);
            if(res.next()) this.setId(res.getString("id"));
            this.insererSpecialite(stmt);
        }catch(Exception e){
            throw e;
        }finally{
            if(stmt!=null) stmt.close();
            if(estValid) con.close();
        }
    }
    
    public void insererSpecialite(Statement stmt) throws Exception{
        for (Specialite specialite : this.getSpecialites()) {
            String sql1="INSERT INTO Employe_specialite VALUES(DEFAULT,'"+this.getId()+"','"+specialite.getId()+"')";
            stmt.executeUpdate(sql1);
        }
    }
}
