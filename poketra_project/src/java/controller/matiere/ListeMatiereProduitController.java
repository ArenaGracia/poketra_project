/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.matiere;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modele.DetailProduit;
import modele.Look;
import modele.Matiere;
import modele.Produit;
import modele.Taille;
import modele.Type;

/**
 *
 * @author ASUS
 */
public class ListeMatiereProduitController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try{
            Matiere matiere=new Matiere();
            ArrayList<Matiere> liste=matiere.getAllMatiere(null);
            request.setAttribute("matieres", liste);
        }catch(Exception e){
            request.setAttribute("erreur", e.getMessage());
        }finally{
            request.getRequestDispatcher("./pages/admin/listMatierP.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if(request.getParameter(("matiere"))!=null){
            
        }
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String typ = request.getParameter("type");        
        String look = request.getParameter("look");
        String[] tailles = request.getParameterValues("taille");
        try{
            Type type=new Type();
            type.setId(typ);
            
            for (String t : tailles) {
                Taille taille= new Taille();
                taille.setId(t);
                String[] listeM=request.getParameterValues(t);
                String[] liste=request.getParameterValues(t.concat("number"));
                ArrayList<DetailProduit> listD=new ArrayList<DetailProduit>();
                
                for (int i=0;i<listeM.length;i++) {
                    DetailProduit detail=new DetailProduit();
                    Matiere matiere=new Matiere();
                    matiere.setId(listeM[i]);
                    detail.setMatiere(matiere);
                    detail.setQuantite(Integer.valueOf(liste[i]));
                    listD.add(detail);
                }
              
                Produit p=new Produit();
                p.setTaille(taille);  
                p.setType(type);
                p.setDetails(listD);
                p.insererProduit(null);
            }
            
        }catch(Exception e){
            request.setAttribute("erreur", e.getMessage());
            response.getWriter().println(e.getMessage());
        }finally{
            processRequest(request,response);
        }
    }

}
