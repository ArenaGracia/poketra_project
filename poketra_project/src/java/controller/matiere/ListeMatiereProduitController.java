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
import modele.DetailModele;
import modele.Look;
import modele.Matiere;
import modele.Modele;
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
        try{
            if(request.getParameter("matiere")!=null){
                String idMatiere=request.getParameter("matiere");
                DetailModele d=new DetailModele();
                ArrayList<DetailModele> liste=d.getDetailByMatiere(idMatiere, null);
                request.setAttribute("modeles", liste); 
            }           
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            processRequest(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

}
