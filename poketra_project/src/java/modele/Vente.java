package modele;

import dbconnect.Dbconnect;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;

public class Vente {
    String idVente;
    Modele modele;
    Client client;
    Date date;
    double nombre;
    
//    Class methods
    public boolean checkFabricationModele(Connection c) throws Exception{
        Statement s=null;
        ResultSet resultSet = null;
        boolean isValid=false;
        int nombre = 0;
        boolean result = true;
        try {
            if (c==null) {
               c= Dbconnect.dbConnect();
               isValid=true;
            }
            String sql= "SELECT SUM(nb) nombre FROM Fabrication WHERE id_modele = \'" + this.getModele().getId() + "\'" ;
            System.out.println(sql);
            s=c.createStatement();
            resultSet = s.executeQuery(sql);
            if(resultSet.next()){
                nombre = resultSet.getInt("nombre");
                if(nombre < this.getNombre()){
                    result = false;
                }
            }else{
                result = false;
            }
        } catch (Exception e) {
            c.rollback();
           e.printStackTrace();
        }
        finally{
            if (s !=null) s.close();
            if (resultSet != null)resultSet.close();
            if (isValid) c.close();
        }
        return result;
    }
    public void insererVente(Connection c) throws Exception{
        Statement s=null;
        ResultSet res=null;
        boolean isValid=false;
        try {
            if (c==null) {
               c= Dbconnect.dbConnect();
               isValid=true;
            }
            c.setAutoCommit(false);
            if(!this.checkFabricationModele(c))throw new Exception("Nombre inferieur au stock");   
            String sql = "INSERT INTO Vente (id_modele, id_client, nombre) VALUES (" +
                        "'" + this.getModele().getId() + "', " +
                        "'" + this.getClient().getId() + "', " +
                        "" + this.getNombre()+ ")" ;
            System.out.println(sql);
            s=c.createStatement();
            s.execute(sql);
            c.commit();
        } catch (Exception e) {
            c.rollback();
            throw e;
        }
        finally{
            if (res !=null) res.close();
            if (s !=null) s.close();
            if (isValid) c.close();
        }
    }
    
    
//    Constructors
    public Vente(){}
    public Vente(String idVente, Modele modele, Client client, Date date, double nombre) throws Exception{
        this.setIdVente(idVente);
        this.setModele(modele);
        this.setClient(client);
        this.setDate(date);
        this.setNombre(nombre);
    }
    
//  Getters and setters
    public String getIdVente() {
        return idVente;
    }

    public void setIdVente(String idVente) throws Exception{
        if(idVente == null || "".equals(idVente))throw new Exception("Invalide id vente");
        this.idVente = idVente;
    }

    public Modele getModele() {
        return modele;
    }

    public void setModele(Modele modele) throws Exception {
        if(modele == null)throw new Exception("INvalide modele dans Vente");
        this.modele = modele;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) throws Exception{
        if(client == null)throw new Exception("INvalide client dans Vente");
        this.client = client;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date)throws Exception {
        if(date == null)throw new Exception("INvalide date dans Vente");
        this.date = date;
    }
    
    public double getNombre() {
        return nombre;
    }

    public void setNombre(double nombre) throws Exception{
        if(nombre <= 0)throw new Exception("Invalide nombre dans Vente");
        this.nombre = nombre;
    }
}
