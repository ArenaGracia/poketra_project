package controller.modele;

import dbconnect.Dbconnect;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modele.DetailModele;
import modele.Look;
import modele.Matiere;
import modele.Modele;
import modele.ModeleSpecialite;
import modele.Specialite;
import modele.Taille;
import modele.Type;

public class ModeleSpecialiteController extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
    }

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    Connection conn = null;
        try {
            String mod=request.getParameter("modele");
            String idType=mod.split("_")[1];
            String idLook=mod.split("_")[0];
            
            Look look=new Look();
            look.setId(idLook);
            Type type=new Type();
            type.setId(idType);
            
            conn = Dbconnect.dbConnect();
            conn.setAutoCommit(false);

            Specialite specialite = new Specialite();
            Modele modele = new Modele();
            modele.setLook(look);
            modele.setType(type);

            String[] nombres = request.getParameterValues("nb");
            String[] durees = request.getParameterValues("duree");

            ArrayList<Specialite> listeSpe = specialite.getAll(conn);
            ArrayList<Modele> listeMode = modele.getModeleByLookAndType(conn);

            int nb = 1;

            for (Modele modele2 : listeMode) {
                ArrayList<ModeleSpecialite> listModeleSpecialite = new ArrayList<>();
                for (int i = 0; i < listeSpe.size(); i++) {
                    int nombre = nb * Integer.valueOf(nombres[i]);
                    double duree = nb * Double.valueOf(durees[i]);
                    ModeleSpecialite m = new ModeleSpecialite(modele2, listeSpe.get(i), nombre, duree);
                    listModeleSpecialite.add(m);
                }
                nb=nb*2;
                modele2.setModeleSpecialites(listModeleSpecialite);
                modele2.insererModeleSpecialite(conn);
            }

            conn.commit();
            request.setAttribute("message", "Insertion réussie");
            } catch (Exception e) {
               try { if(conn!=null) conn.rollback(); }
               catch(Exception e1) { e1.printStackTrace(); }
               request.setAttribute("erreur", e.getMessage());
            }      
            finally {
                try { if(conn!=null) conn.rollback(); }
                catch(Exception e1) { e1.printStackTrace(); }
                response.sendRedirect("./add_specialite_modele");
            }
    }
}