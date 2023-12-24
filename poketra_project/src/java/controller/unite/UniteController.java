/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.unite;

import dbconnect.Dbconnect;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.sql.Connection;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modele.Unite;

/**
 *
 * @author Toky
 */
public class UniteController extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
       request.getRequestDispatcher("./pages/admin/ajoutUnite.jsp").forward(request, response);
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
            String nom = request.getParameter("unite");
            Unite unite =new Unite();
            unite.setNom(nom);
            unite.insererUnite(null);
            request.setAttribute("message", "Insertion réussi");
        } catch (Exception e) {
            request.setAttribute("erreur", e.getMessage());
        }
        finally{
            processRequest(request,response);
        }
    }

}
