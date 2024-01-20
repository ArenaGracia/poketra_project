/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.modele;

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
public class PrixVenteController extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try{
            Modele modele=new Modele();
            ArrayList<Modele> liste=modele.getModeles(null);
            request.setAttribute("modeles", liste);
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            request.getRequestDispatcher("./pages/admin/ajoutPrixVente.jsp").forward(request, response);
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
            double prix=Double.valueOf(request.getParameter("prix"));
            Modele modele=new Modele();
            modele.setId(idModele);
            modele.setPrixVente(prix);
            modele.insererPrixVenteModele(null);
            request.setAttribute("message", "Insertion réussie");
        }catch(Exception e){
            request.setAttribute("erreur", e.getMessage());
        }finally{
            processRequest(request, response);
        }
    }
}
