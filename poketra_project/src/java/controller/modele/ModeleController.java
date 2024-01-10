
package controller.modele;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modele.DetailModele;
import modele.Look;
import modele.Matiere;
import modele.Modele;
import modele.Taille;
import modele.Type;

public class ModeleController extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.getRequestDispatcher("./list_type_taille").forward(request, response);
    }

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String typ = request.getParameter("type");        
        String idLook = request.getParameter("look");
        String[] tailles = request.getParameterValues("taille");
        try{
            Type type=new Type();
            type.setId(typ);
            Look look=new Look();
            look.setId(idLook);
            double prix=0;
            
            for (String t : tailles) {
                Taille taille= new Taille();
                taille.setId(t);
                String[] listeM=request.getParameterValues(t);
                String[] liste=request.getParameterValues(t.concat("number"));
                ArrayList<DetailModele> listD=new ArrayList<DetailModele>();
                
                for (int i=0;i<listeM.length;i++) {
                    DetailModele detail=new DetailModele();
                    Matiere matiere=new Matiere();
                    matiere.setId(listeM[i]);
                    matiere=matiere.getMatiere(null);
                    detail.setMatiere(matiere);
                   
                    detail.setQuantite(Integer.valueOf(liste[i])); prix += matiere.getPrix()*detail.getQuantite();
                    listD.add(detail);
                }
                
                Modele p=new Modele();
                p.setTaille(taille);  
                p.setType(type);
                p.setLook(look);
                p.setDetails(listD);
                p.setPrixConfection(prix);
                p.insererModele(null);
                
                request.setAttribute("message", "Insérer avec succès");
            }
        }catch(Exception e){
            request.setAttribute("erreur", e.getMessage());
            response.getWriter().println(e.getMessage());
        }finally{
            processRequest(request,response);
        }
    }
}