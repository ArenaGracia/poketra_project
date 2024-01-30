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
public class RechercheBeneficeController extends HttpServlet {

        protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
            request.getRequestDispatcher("./pages/admin/rechercheBenefice.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        boolean forwarded = false;
        try{
                double min=Double.valueOf(request.getParameter("min"));
                double max=Double.valueOf(request.getParameter("max"));
                Modele mod=new Modele();
                ArrayList<Modele> listMod= mod.getModeleBenefice(null,min,max);
                System.out.println(listMod.size());
                request.setAttribute("modeles",listMod);
        }
        catch(Exception e){
            request.setAttribute("erreur",e.getMessage());
            request.getRequestDispatcher("./pages/admin/rechercheBenefice.jsp").forward(request, response);
            forwarded=true;
        }
        finally{
            if(!forwarded) request.getRequestDispatcher("./pages/admin/listSacByBenefice.jsp").forward(request, response);                  
        }
    }
}
