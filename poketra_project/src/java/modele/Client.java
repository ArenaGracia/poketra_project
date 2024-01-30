/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import dbconnect.Dbconnect;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Client {
    String id;
    String nom;
    Genre genre;

    public Client(String id, String nom, Genre genre) {
        this.id = id;
        this.nom = nom;
        this.genre = genre;
    }

    public Client() {
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id)throws Exception {
        if (id == null || "".equals(id))throw new IllegalArgumentException("L'ID ne peut pas être négatif.");
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom)throws Exception {
         if(nom.equalsIgnoreCase("") || nom.trim().length()==0 || nom==null) throw new Exception("nom invalide");
        this.nom = nom;
    }

     public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }
    public void insererClient(Connection con) throws Exception{
           boolean estValid=false;
        Statement stmt=null;
        try{
            if(con==null){
                con=Dbconnect.dbConnect();
                estValid=true;
            }
            con.setAutoCommit(false);
            String sql="INSERT INTO Client VALUES(default,'"+this.getNom()+"','"+this.getGenre().getId()+"')";
            System.out.println("modele.Employe.insererClient() "+sql);
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
    public ArrayList<Client> getAllClient(Connection con)throws Exception{
       Statement s=null;
         ResultSet res=null;
         boolean isValid=false;
         ArrayList<Client> liste=new ArrayList<Client>();
         try {
             if (con==null) {
                con= Dbconnect.dbConnect();
                isValid=true;
             }
             String sql="SELECT * FROM v_client";
             s=con.createStatement();
             res=s.executeQuery(sql);
             while(res.next()){
                 Genre genre=new Genre();
                 Client c=new Client();
                 String idC=res.getString("id_client");
                 String nom=res.getString("nom_client");
                genre.setId(res.getString("id_genre"));
                genre.setNom(res.getString("nom"));
                c.setId(idC);
                c.setNom(nom);
                c.setGenre(genre);
                 liste.add(c);
             }
             
         } catch (Exception e) {
            e.printStackTrace();
         }
         finally{
             if (res !=null) res.close();
             if (s !=null) s.close();
             if (isValid) con.close();
         }
         return liste;
        
    }

   
}
