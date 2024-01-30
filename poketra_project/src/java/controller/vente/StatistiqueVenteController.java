/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.vente;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modele.Modele;

/**
 *
 * @author ITU
 */
public class StatistiqueVenteController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try{
            Modele modele=new Modele();
            ArrayList<Modele> liste=modele.getModeles(null);
            request.setAttribute("modeles", liste);
        }catch(Exception e){
            System.out.println("controller.vente.StatistiqueVenteController.processRequest()"+e.getMessage());
        }finally{
            request.getRequestDispatcher("./pages/admin/statistique.jsp").forward(request, response);
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
            String idModele=request.getParameter("modele");
            Modele m=new Modele();
            m.setId(idModele);
            m=m.getModeleParChoix(null);
            request.setAttribute("modele", m);
        }catch(Exception e){
            System.out.println("controller.vente.StatistiqueVenteController.doPost()"+e.getMessage());
        }finally{
            request.getRequestDispatcher("./pages/admin/statVente.jsp").forward(request, response);
        }
    }
}
