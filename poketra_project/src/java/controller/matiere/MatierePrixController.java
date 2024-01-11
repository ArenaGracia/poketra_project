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

/**
 *
 * @author ASUS
 */
public class MatierePrixController extends HttpServlet {
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
            request.getRequestDispatcher("./pages/admin/ajoutMatierePrix.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
     try{
        double price = Double.valueOf(request.getParameter("prix"));
        String idM= request.getParameter("matiere");
        Matiere mat=new Matiere();
        mat.setId(idM);
        mat.setPrix(price);
        mat.insererPrix(null);
        request.setAttribute("message","succes");
     }  catch(Exception e){
         request.setAttribute("erreur",e.getMessage());
     }
     finally{
         processRequest(request,response);
     }
    }
}
