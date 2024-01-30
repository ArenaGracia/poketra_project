/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.status;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modele.Status;

/**
 *
 * @author ASUS
 */
public class StatusController extends HttpServlet {

   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
         request.getRequestDispatcher("./pages/admin/ajoutStatus.jsp").forward(request, response);
       
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        try{
            String status=request.getParameter("status");
            int anc=Integer.valueOf(request.getParameter("anc"));
            double taux=Double.valueOf(request.getParameter("horaire"));
            Status stat=new Status();
            stat.setNom(status);
            stat.setNb_annee(anc);
            stat.setTaux(taux);
            stat.insererStatus(null);
            request.setAttribute("message", "Insertion réussie");
        }catch(Exception ex){
            request.setAttribute("erreur", ex.getMessage());
        }
        finally{
             processRequest(request, response);
        }
    }
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
