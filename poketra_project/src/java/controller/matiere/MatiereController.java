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
import modele.Matiere;
import modele.Unite;

/**
 *
 * @author Toky
 */
public class MatiereController extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try{
            Unite unite=new Unite();
            ArrayList<Unite> listes=unite.getAllUnite(null);
            request.setAttribute("unite", listes);
        }catch(Exception e){
            request.setAttribute("erreur", e.getMessage());
        }finally{
            request.getRequestDispatcher("./pages/admin/ajoutMatiere.jsp").forward(request, response);
        }
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       try {
            String nom = (String) request.getParameter("matiere");
            Matiere matiere =new Matiere();
            Unite unite=new Unite();
            ArrayList<Unite> liste=unite.getAllUnite(null);
            request.setAttribute("unite", liste);
            unite.setId(request.getParameter("unite"));

            matiere.setNom(nom);
            matiere.setUnite(unite);
            matiere.insererMatiere(null);
            request.setAttribute("message", "Insertion de matiere  réussie");
        } catch (Exception e) {
            request.setAttribute("erreur", e.getMessage());
            response.getWriter().print(e.getMessage());
        }
        finally{
            processRequest(request,response);
        }
    }

}
