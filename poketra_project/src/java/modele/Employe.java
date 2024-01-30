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
import java.time.LocalDate;
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
    Date dateEmbauche;
    Status status;
    Specialite specialite;

    public String getId() {
        return id;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
    
    
    
    public void setId(String id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) throws Exception {
        if(nom.equalsIgnoreCase("") || nom.trim().length()==0 || nom==null) throw new Exception("Nom null");
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) throws Exception {
        if(prenom.equalsIgnoreCase("") || prenom.trim().length()==0 || prenom==null) throw new Exception("Prenom invalide");
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
    
    public void setDateNaissance(String dateNaissance) throws Exception{
        if(dateNaissance==null || dateNaissance.equals("")) throw new Exception("Date de naissance invalide");
        setDateNaissance(Date.valueOf(dateNaissance));
    }

    public void setDateNaissance(Date dateNaissance) throws Exception{
        Date today=new Date(System.currentTimeMillis());
        if(today.getYear()-dateNaissance.getYear()<18) throw new Exception("Il faut être majeur");
        this.dateNaissance = dateNaissance;
    }

    public Date getDateEmbauche() {
        return dateEmbauche;
    }

    public void setDateEmbauche(Date dateEmbauche) {
        this.dateEmbauche = dateEmbauche;
    }

    public Specialite getSpecialite() {
        return specialite;
    }

    public void setSpecialite(Specialite specialite) {
        this.specialite = specialite;
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
            String sql="INSERT INTO Employe VALUES(default,'"+this.getNom()+"','"+this.getPrenom()+"','"+this.getGenre().getId()+"','"+this.getDateNaissance()+"','"+this.getSpecialite().getId()+"')";
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
    
    public void insererDateEmbauche(Connection con) throws Exception{
        boolean estValid=false;
        Statement stmt=null;
        ResultSet res=null;
        try{
            if(con==null){
                con=Dbconnect.dbConnect();
                estValid=true;
            }
            con.setAutoCommit(false);
            String sql1="UPDATE Employe set is_embaucher=10 WHERE id='"+this.getId()+"'";
            System.out.println("modele.Employe.insererDateEmbauche() "+sql1);
            String sql="INSERT INTO embauche_employe VALUES(default,'"+this.getId()+"','"+this.getDateEmbauche()+"')";
            System.out.println("modele.Employe.insererDateEmbauche()"+sql);
            stmt=con.createStatement();
            stmt.executeUpdate(sql1);
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
    
    public ArrayList<Employe> getEmployeStatus(Date date,Connection con) throws Exception{
        boolean estValid=false;
        ResultSet res=null;
        Statement stmt=null;
        ArrayList<Employe> liste=new ArrayList<Employe>();
        try{
            if(con==null){
                con=Dbconnect.dbConnect();
                estValid=true;
            }
            String sql="SELECT*FROM v_employe WHERE is_embaucher=10";
            System.out.println("modele.Employe.getAll() "+sql);
            
            stmt=con.createStatement();
            res=stmt.executeQuery(sql); 
            while(res.next()){
                Employe emp=new Employe();
                Specialite s=new Specialite();
                Genre g=new Genre();
                Status status=new Status();
                
                
                s.setId(res.getString("id_specialite"));
                s.setNom(res.getString("specialite"));
                s.setSalaire(res.getDouble("salaire_heure"));
                
                g.setId(res.getString("id_genre"));
                g.setNom(res.getString("nom_genre"));
                
                emp.setId(res.getString("id"));
                emp.setNom(res.getString("nom"));
                emp.setPrenom(res.getString("prenom"));
                emp.setDateEmbauche(res.getDate("date_embauche"));
                emp.setGenre(g);
                emp.setSpecialite(s);
                
                double year=date.getYear()-emp.getDateEmbauche().getYear();
                status=status.getStatusByAnciennete(year, con);
                emp.setStatus(status);
                emp.getSpecialite().setSalaire(emp.getSpecialite().getSalaire()*emp.getStatus().getTaux());
                liste.add(emp);
            }
            
            
            
        }catch(Exception e){
            throw e;
        }finally{
            if(res!=null) res.close();
            if(stmt!=null) stmt.close();
            if(estValid) con.close();            
        }
        return liste;
    }
    
    public ArrayList<Employe> getAll(Connection con) throws Exception{
        boolean estValid=false;
        ResultSet res=null;
        Statement stmt=null;
        ArrayList<Employe> liste=new ArrayList<Employe>();
        try{
            if(con==null){
                con=Dbconnect.dbConnect();
                estValid=true;
            }
            String sql="SELECT*FROM v_employe WHERE is_embaucher=1";
            System.out.println("modele.Employe.getAll() "+sql);
            
            stmt=con.createStatement();
            res=stmt.executeQuery(sql); 
            while(res.next()){
                Employe emp=new Employe();
                Specialite s=new Specialite();
                Genre g=new Genre();
                
                s.setId(res.getString("id_specialite"));
                s.setNom(res.getString("specialite"));
                s.setSalaire(res.getDouble("salaire_heure"));
                
                g.setId(res.getString("id_genre"));
                g.setNom(res.getString("nom_genre"));
                
                emp.setId(res.getString("id"));
                emp.setNom(res.getString("nom"));
                emp.setPrenom(res.getString("prenom"));
                emp.setGenre(g);
                emp.setSpecialite(s);
                
                liste.add(emp);
            }
        }catch(Exception e){
            throw e;
        }finally{
            if(res!=null) res.close();
            if(stmt!=null) stmt.close();
            if(estValid) con.close();            
        }
        return liste;
    }
}
