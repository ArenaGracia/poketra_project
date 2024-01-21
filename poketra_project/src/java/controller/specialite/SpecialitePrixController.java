/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.specialite;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modele.Specialite;

/**
 *
 * @author ASUS
 */
public class SpecialitePrixController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try{
            Specialite spec = new Specialite();
            ArrayList<Specialite> liste=spec.getAll(null);
            request.setAttribute("specialites", liste);
        }catch(Exception e){
            request.setAttribute("erreur", e.getMessage());
        }finally{
            request.getRequestDispatcher("./pages/admin/ajoutPrixSpecialite.jsp").forward(request, response);
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
        double price = Double.valueOf(request.getParameter("salaire"));
        String idSpec= request.getParameter("specialite");
        Specialite spec=new Specialite();
        spec.setId(idSpec);
        spec.setSalaire(price);
        spec.insererSalaire(null);
        request.setAttribute("message","insertion reussi");
     }  catch(Exception e){
         request.setAttribute("erreur",e.getMessage());
     }
     finally{
         processRequest(request,response);
     }
   }
}
