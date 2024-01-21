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
public class Specialite {
    String id;
    String nom;
    double salaire;
    
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

    public double getSalaire() {
        return salaire;
    }

    public void setSalaire(double salaire) {
        this.salaire = salaire;
    }  
    
    public ArrayList<Specialite> getAll(Connection con) throws Exception{
        ArrayList<Specialite> lg=new ArrayList<Specialite>();
        Statement stmt=null;  
        ResultSet res=null;
        boolean estValid=false;
        
        try{
            if(con==null){
                con=Dbconnect.dbConnect();
                estValid=true;
            }
            stmt=con.createStatement();
            String sql="SELECT*FROM Specialite";   
            res=stmt.executeQuery(sql);
            while(res.next()){
                Specialite s=new Specialite();
                s.setId(res.getString("id_specialite"));
                s.setNom(res.getString("nom"));
                s.setSalaire(res.getDouble("salaire"));
                lg.add(s);
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
            String sql="INSERT INTO Specialite VALUES(DEFAULT,'"+this.getNom()+"')";
            stmt=con.createStatement();
            stmt.executeUpdate(sql);
        }catch(Exception e){
            throw e;
        }finally{
            if(stmt!=null) stmt.close();
            if(estValid) con.close();
        }
    }
    
        public void insererSalaire(Connection con) throws Exception{
        Statement stmt=null;
        boolean estValid=false; 
        try{
            if(con==null){
                con=Dbconnect.dbConnect();
                estValid=true;
            }
            String sql="INSERT INTO Specialite_salaire VALUES(DEFAULT,'"+this.getNom()+"',"+this.getSalaire()+",DEFAULT)";
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
