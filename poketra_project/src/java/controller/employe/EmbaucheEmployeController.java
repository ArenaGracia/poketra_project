/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.employe;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modele.Employe;

/**
 *
 * @author ITU
 */
public class EmbaucheEmployeController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try{
            Employe emp=new Employe();
            ArrayList<Employe> listes=emp.getAll(null);
            request.setAttribute("emps", listes);
        }catch(Exception e){
            
        }finally{
            request.getRequestDispatcher("./pages/admin/ajoutEmployeEmbauche.jsp").forward(request, response);
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
        processRequest(request, response);
        try{
            Date embauche=Date.valueOf(request.getParameter("date"));
            String id=request.getParameter("emp");
            System.out.println("controller.employe.EmbaucheEmployeController.doPost()"+id);
            Employe emp=new Employe();
            emp.setDateEmbauche(embauche);
            emp.setId(id);
            emp.insererDateEmbauche(null);
            request.setAttribute("message","Succes");
        }catch(Exception e){
            request.setAttribute("erreur", e.getMessage());
        }finally{
            processRequest(request, response);
        }
    }

}
