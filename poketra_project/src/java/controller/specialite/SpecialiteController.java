/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.specialite;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modele.Specialite;

/**
 *
 * @author ITU
 */
public class SpecialiteController extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.getRequestDispatcher("./pages/admin/ajoutSpecialite.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String nom = request.getParameter("specialite");
            double salaire=Double.valueOf(request.getParameter("salaire"));
            Specialite specialite =new Specialite();
            specialite.setNom(nom);
            specialite.setSalaire(salaire);
            specialite.inserer(null);
            request.setAttribute("message", "Insertion réussi");
        } catch (Exception e) {
            request.setAttribute("erreur", e.getMessage());
        }
        finally{
            processRequest(request,response);
        }
    }
}
