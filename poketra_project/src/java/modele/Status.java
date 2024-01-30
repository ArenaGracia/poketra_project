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

/**
 *
 * @author ASUS
 */
public class Status {
    String id;
    String nom;
    int nb_annee;
    double taux;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom)throws Exception {
         if(nom.equalsIgnoreCase("") || nom.trim().length()==0 || nom==null) throw new Exception("Nom null");
        this.nom = nom;
    }

    public int getNb_annee() {
        return nb_annee;
    }

    public void setNb_annee(int nb_annee) {
        this.nb_annee = nb_annee;
    }

    public double getTaux() {
        return taux;
    }

    public void setTaux(double taux) {
        this.taux = taux;
    }
    public void insererStatus(Connection con)throws Exception
    {
         boolean estValid=false;
        Statement stmt=null;
        ResultSet res=null;
        try{
            if(con==null){
                con=Dbconnect.dbConnect();
                estValid=true;
            }
            con.setAutoCommit(false);
            String sql="INSERT INTO Status VALUES(default,'"+this.getNom()+"',"+this.getNb_annee()+","+this.getTaux()+")";
            System.out.println("modele.Employe.insererEmployer() "+sql);
            
            stmt=con.createStatement();
            stmt.executeUpdate(sql);
            con.commit();
        }catch(Exception e){
            con.rollback();
            throw e;
        }finally{
            if(stmt!=null) stmt.close();
            if(estValid) con.close();
        }
    }
    
    public Status getStatusByAnciennete(double diff,Connection con) throws Exception{
        boolean estValid=false;
        Statement stmt=null;
        ResultSet res = null;
        Status s=null;
        try{
            if(con==null){
                estValid=true;
                con=Dbconnect.dbConnect();
            }
            String sql="SELECT*FROM Status WHERE nb_annee<="+diff+" ORDER BY nb_annee DESC LIMIT 1";
            System.out.println("modele.Status.getStatusByAnciennete() "+sql );
            stmt=con.createStatement();
            res=stmt.executeQuery(sql);
            while(res.next()){
                s=new Status();
                s.setId(res.getString("id_status"));
                s.setNom(res.getString("nom"));
                s.setNb_annee(res.getInt("nb_annee"));
                s.setTaux(res.getDouble("taux_horaire"));
            }
        }catch(Exception e){
            throw e;
        }finally{
            if(res!=null) res.close();
            if(stmt!=null) stmt.close();
            if(estValid) con.close();
        }
        return s;
    }
  }

