package modele;

import dbconnect.Dbconnect;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;


public class Modele {
    String id; 
    Look look;
    Type type;
    Taille taille;
    double prixConfection;
    double prixVente;
    double prixRevient;
    double benefice;
    ArrayList<DetailModele> details;
    ArrayList<ModeleSpecialite> modeleSpecialites;

    public double getPrixRevient() {
        return prixRevient;
    }

    public void setPrixRevient(double prixRevient) {
        this.prixRevient = prixRevient;
    }

    
    public double getBenefice() {
        return benefice;
    }

    public void setBenefice(double benefice) {
        this.benefice = benefice;
    }

    
    public ArrayList<ModeleSpecialite> getModeleSpecialites() {
        return modeleSpecialites;
    }

    public void setModeleSpecialites(ArrayList<ModeleSpecialite> modeleSpecialites) {
        this.modeleSpecialites = modeleSpecialites;
    }

    public double getPrixVente() {
        return prixVente;
    }

    public void setPrixVente(double prixVente) throws Exception {
        if(prixVente<0) throw new Exception("Prix de vente non valide");
        this.prixVente = prixVente;
    }
    
    

    public Look getLook() {
        return look;
    }

    public void setLook(Look look) {
        this.look = look;
    }

    public ArrayList<DetailModele> getDetails() {
        return details;
    }

    public void setDetails(ArrayList<DetailModele> details) {
        this.details = details;
    }
  
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Taille getTaille() {
        return taille;
    }

    public void setTaille(Taille taille) {
        this.taille = taille;
    }

    public double getPrixConfection() {
        return prixConfection;
    }

    public void setPrixConfection(double prixConfection) {
        this.prixConfection = prixConfection;
    }
    
    
    
     //Contructors
    public Modele(){}
    public Modele(String id, Type type, Taille taille,Look look)throws Exception{
        setId(id);
        setTaille(taille);
        setType(type);
        setLook(look);
    }
      
    
    //Methods
     public void insererModele(Connection c) throws Exception{
        Statement s=null;
        ResultSet res=null;
        boolean isValid=false;
        try {
            if (c==null) {
               c= Dbconnect.dbConnect();
               isValid=true;
            }
            c.setAutoCommit(false);
            String sql="INSERT INTO Modele (id_look, id_type, id_taille) VALUES (" +
                        "'" + this.getLook().getId() + "'," +
                        "'" + this.getType().getId() + "'," +
                        "'" + this.getTaille().getId()+ "') returning id" ;
            System.out.println(sql);
            s=c.createStatement();
            res=s.executeQuery(sql);
            if(res.next()) this.setId(res.getString("id"));
            this.insererDetail(c);
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
    
    public void insererPrixVenteModele(Connection connection) throws Exception{
        Statement s=null;
        boolean isValid=false;
        try {
            if (connection==null) {
               connection= Dbconnect.dbConnect();
               isValid=true;
            }
            this.getPrixRevient(connection);
            if(this.getPrixRevient()>this.getPrixVente()) throw new Exception("Voulez-vous entrer dans une perte ou quoi? Vous devez entrer au moins "+this.getPrixRevient());
            String sql="INSERT INTO modele_prix_vente VALUES (DEFAULT,'"+this.getId()+"',"+this.getPrixVente()+",DEFAULT)" ;
            s=connection.createStatement();
            System.out.println(sql);
            s.executeUpdate(sql);
        } catch (Exception e) {
           throw e;
        }
        finally{
            if (s !=null) s.close();
            if (isValid) connection.close();
        }       
    }
    
    public void getPrixRevient(Connection con) throws Exception{
        ResultSet res=null;
        Statement stmt=null;
        boolean estValid=false;
        try{
            if(con==null){
                estValid=true;
                con=Dbconnect.dbConnect();
            }
            String sql="SELECT*FROM v_prix_revient WHERE id='"+this.getId()+"'";
            stmt=con.createStatement();
            res=stmt.executeQuery(sql);
            while(res.next()){
                this.setPrixRevient(res.getDouble("prix_revient"));
            }
        }catch(Exception e){
            throw e;
        }finally{
            if(res!=null) res.close();
            if(stmt!=null) stmt.close();
            if(estValid) con.close();
        }
    }
    
    public ArrayList<Modele> getModelePrix(Connection connection,double min,double max) throws Exception{
        if(min<0 || max<min) throw new Exception("L'intervallle de valeur est fausse");
        Statement s=null;
        ResultSet res=null;
        boolean isValid=false;
        ArrayList<Modele> liste=new ArrayList<Modele>();
         try {
             if (connection==null) {
                connection= Dbconnect.dbConnect();
                isValid=true;
             }
             String sql="SELECT * FROM v_prix_modele WHERE prix_confection BETWEEN "+min+" AND "+max+"";
             System.out.println(sql);
             s=connection.createStatement();
             res=s.executeQuery(sql);
             while(res.next()){
                Modele modele=new Modele();
                Matiere matiere=new Matiere();
                modele.setId(res.getString("id"));
                Taille taille=new Taille(res.getString("id_taille"),res.getString("nom_taille"));
                Type type=new Type(res.getString("id_type"),res.getString("nom_type"));
                Look look=new Look(res.getString("id_look"),res.getString("nom_look"));
                modele.setLook(look);
                modele.setPrixConfection(res.getDouble("prix_confection"));
                modele.setTaille(taille);
                modele.setTaille(taille);
                modele.setType(type);
             
                liste.add(modele);
             }
         } catch (Exception e) {
            throw e;
         }
         finally{
             if (res !=null) res.close();
             if (s !=null) s.close();
             if (isValid) connection.close();
         }
         return liste;
    }

    public ArrayList<Modele> getModeleBenefice(Connection connection,double min,double max) throws Exception{
<<<<<<< Updated upstream
        if(max<min) throw new Exception("L'intervallle de valeur est fausse");
=======
        if(max<min || min<0) throw new Exception("L'intervallle de valeur est fausse");
>>>>>>> Stashed changes
        Statement s=null;
        ResultSet res=null;
        boolean isValid=false;
        ArrayList<Modele> liste=new ArrayList<Modele>();
         try {
             if (connection==null) {
                connection= Dbconnect.dbConnect();
                isValid=true;
             }
             String sql="SELECT * FROM v_modele_benefice WHERE benefice BETWEEN "+min+" AND "+max+"";
             System.out.println(sql);
             s=connection.createStatement();
             res=s.executeQuery(sql);
             while(res.next()){
                Modele modele=new Modele();
                modele.setId(res.getString("id"));
                Taille taille=new Taille(res.getString("id_taille"),res.getString("nom_taille"));
                Type type=new Type(res.getString("id_type"),res.getString("nom_type"));
                Look look=new Look(res.getString("id_look"),res.getString("nom_look"));
                modele.setLook(look);
                modele.setBenefice(res.getDouble("benefice"));
                modele.setTaille(taille);
                modele.setTaille(taille);
                modele.setType(type);
             
                liste.add(modele);
             }
         } catch (Exception e) {
            throw e;
         }
         finally{
             if (res !=null) res.close();
             if (s !=null) s.close();
             if (isValid) connection.close();
         }
         return liste;
    }
    
    public ArrayList<Modele> getModeles(Connection connection) throws Exception{
        Statement s=null;
        ResultSet res=null;
        boolean isValid=false;
        ArrayList<Modele> liste=new ArrayList<Modele>();
         try {
             if (connection==null) {
                connection= Dbconnect.dbConnect();
                isValid=true;
             }
<<<<<<< Updated upstream
             String sql="SELECT * FROM v_prix_modele";
=======
             String sql="SELECT * FROM v_modele_sans_matiere";
>>>>>>> Stashed changes
             System.out.println(sql);
             s=connection.createStatement();
             res=s.executeQuery(sql);
             while(res.next()){
                Modele modele=new Modele();
                modele.setId(res.getString("id"));
                Taille taille=new Taille(res.getString("id_taille"),res.getString("nom_taille"));
                Type type=new Type(res.getString("id_type"),res.getString("nom_type"));
                Look look=new Look(res.getString("id_look"),res.getString("nom_look"));
                modele.setLook(look);
<<<<<<< Updated upstream
                modele.setPrixConfection(res.getDouble("prix_confection"));
=======
>>>>>>> Stashed changes
                modele.setTaille(taille);
                modele.setTaille(taille);
                modele.setType(type);
             
                liste.add(modele);
             }
         } catch (Exception e) {
            throw e;
         }
         finally{
             if (res !=null) res.close();
             if (s !=null) s.close();
             if (isValid) connection.close();
         }
         return liste;
    }

    public void insererDetail(Connection c) throws Exception{
        Statement s=null;
        boolean isValid=false;
        try {
            if (c==null) {
               c= Dbconnect.dbConnect();
               isValid=true;
            }
            
            for(DetailModele dp:this.getDetails()){
                String sql="INSERT INTO Detail_Modele (id_modele,id_matiere,qte) VALUES (" +
                        "'" + this.getId() + "','"+dp.getMatiere().getId()+"',"+dp.getQuantite()+")" ;
                System.out.println(sql);
                s=c.createStatement();
                s.executeUpdate(sql);    
            }
            
        } catch (Exception e) {
           throw e;
        }
        finally{
            if (s !=null) s.close();
            if (isValid) c.close();
        }        
    }
    
    public Modele getModele(Connection c) throws Exception{
        Statement s=null;
        ResultSet res=null;
        boolean isValid=false;
        Type type=null;
        Look look=null;
        Taille taille=null;
        ArrayList<DetailModele> liste=new ArrayList<DetailModele>();
        Modele modele=null;
        try {
            if (c==null) {
               c= Dbconnect.dbConnect();
               isValid=true;
            }
            String sql="SELECT * FROM v_modele WHERE id='"+this.getId()+"'";
            System.out.println(sql);
            s=c.createStatement();
            res=s.executeQuery(sql);
            while(res.next()){
                modele=new Modele();
                Matiere matiere=new Matiere();
                matiere.setNom(res.getString("nom_matiere"));
                matiere.setId(res.getString("id_matiere"));
                modele.setId(id);
                taille=new Taille(res.getString("id_taille"),res.getString("nom_taille"));
                type=new Type(res.getString("id_type"),res.getString("nom_type"));
                look=new Look(res.getString("id_look"),res.getString("nom_look"));
                modele.setLook(look);
                modele.setTaille(taille);
                modele.setTaille(taille);
                modele.setType(type);
                DetailModele d=new DetailModele(res.getInt("qte"),matiere,modele);
                liste.add(d);
            }            
        } catch (Exception e) {
           throw e;
        }
        finally{
            if (res !=null) res.close();
            if (s !=null) s.close();
            if (isValid) c.close();
        }
        modele.setDetails(liste);
        return modele;
    } 

    
    public void insererModeleSpecialite(Connection connection) throws Exception{
        Statement s=null;
        ResultSet res=null;
        boolean isValid=false;
        try {
            if (connection==null) {
               connection= Dbconnect.dbConnect();
               isValid=true;
            }
            s=connection.createStatement();
            for (ModeleSpecialite modeleSpecialite : this.getModeleSpecialites()) {
                String sql="INSERT INTO Modele_specialite VALUES (DEFAULT,'"+modeleSpecialite.getModele().getId()+"','"+modeleSpecialite.getSpecialite().getId()+"',"+modeleSpecialite.getNombre()+","+modeleSpecialite.getDuree()+",default)" ;
                System.out.println(sql);
                s.executeUpdate(sql);                
            }
            
        } catch (Exception e) {
           throw e;
        }
        finally{
            if (s !=null) s.close();
            if (isValid) connection.close();
        }       
    }
 
    
    public ArrayList<Modele> getModeleByLookAndType(Connection connection) throws Exception{
        ArrayList<Modele> list =new ArrayList<Modele>();
        Statement s=null;
        ResultSet res=null;
        Boolean isValid=false;
        try{
            if (connection == null) {
                connection=Dbconnect.dbConnect();
                isValid=true;
            }
            String sql="SELECT * FROM v_modele_sans_detail WHERE id_look = '"+this.getLook().getId()+"' AND id_type = '"+this.getType().getId()+"'";
            s=connection.createStatement();
            res=s.executeQuery(sql);
            while(res.next()){
               Look look1=new Look(res.getString("id_look"), res.getString("nom_look"));
               Type type1=new Type(res.getString("id_type"), res.getString("nom_type"));
               Taille taille1=new Taille(res.getString("id_taille"), res.getString("nom_taille"));
               
               Modele modele =new Modele(res.getString("id"), type1, taille1, look1);
               
               list.add(modele);
             }
        }
        catch(Exception ex){
             throw ex;
        }
          finally{
             if (res !=null) res.close();
             if (s !=null) s.close();
             if (isValid) connection.close();
         }
        return list;
    }
 
    public ArrayList<Modele> getModeleSansTaille(Connection connection) throws Exception{
        ArrayList<Modele> list =new ArrayList<Modele>();
        Statement s=null;
        ResultSet res=null;
        Boolean isValid=false;
        try{
            if (connection == null) {
                connection=Dbconnect.dbConnect();
                isValid=true;
            }
            String sql="SELECT * FROM v_modele_sans_taille ";
            s=connection.createStatement();
            res=s.executeQuery(sql);
            while(res.next()){
               Look look1=new Look(res.getString("id_look"), res.getString("nom_look"));
               Type type1=new Type(res.getString("id_type"), res.getString("nom_type"));
               Modele modele=new Modele();
               modele.setLook(look1);
               modele.setType(type1);
               list.add(modele);
             }
        }
        catch(Exception ex){
              throw ex;
        }
          finally{
             if (res !=null) res.close();
             if (s !=null) s.close();
             if (isValid) connection.close();
         }
        return list;
    }
<<<<<<< Updated upstream
}
=======
    
   public ArrayList<Statistique> getStatistique(Connection connection) throws Exception {
    ArrayList<Statistique> listStats = new ArrayList<>();
    Statement s = null;
    ResultSet r = null;
    Boolean isValid = false;
    
    try {
        if (connection == null) {
            connection = Dbconnect.dbConnect();
            isValid = true;
        }

        String sql = "SELECT * FROM v_statistique_modele WHERE id_modele='" + this.getId() + "'";
        if(this.getId().equals("all")) sql="SELECT * FROM v_statistique_modele_general";
        System.out.println("modele.Modele.getStatistique()"+sql);
        s = connection.createStatement();
        r = s.executeQuery(sql);

        while (r.next()) {
            Genre genre=new Genre(r.getString("id_genre"),r.getString("nom"));
            Statistique stat = new Statistique(genre,r.getDouble("statistique"),r.getDouble("valeur"));
            listStats.add(stat);
        }

    } catch (Exception e) {
        System.err.println(e.getMessage());
    } finally {
        if (r != null) r.close();
        if (s != null) s.close();
        if (isValid) connection.close();
    }

    return listStats;
    }
    
       public Modele getModeleParChoix(Connection connection) throws Exception{
        Statement s=null;
        ResultSet res=null;
        boolean isValid=false;
        Modele modele=null;
         try {
             if (connection==null) {
                connection= Dbconnect.dbConnect();
                isValid=true;
             }
             String sql="SELECT * FROM v_modele_sans_matiere WHERE id LIKE '"+this.getId()+"'";
             if(this.getId().equals("all")) return this;
             System.out.println(sql);
             s=connection.createStatement();
             res=s.executeQuery(sql);
             while(res.next()){
                modele=new Modele();
                modele.setId(res.getString("id"));
                Taille taille=new Taille(res.getString("id_taille"),res.getString("nom_taille"));
                Type type=new Type(res.getString("id_type"),res.getString("nom_type"));
                Look look=new Look(res.getString("id_look"),res.getString("nom_look"));
                modele.setLook(look);
                modele.setTaille(taille);
                modele.setTaille(taille);
                modele.setType(type);
             }
         } catch (Exception e) {
            throw e;
         }
         finally{
             if (res !=null) res.close();
             if (s !=null) s.close();
             if (isValid) connection.close();
         }
         return modele;
    }

}
>>>>>>> Stashed changes
