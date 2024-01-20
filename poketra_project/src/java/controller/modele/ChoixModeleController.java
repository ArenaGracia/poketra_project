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
import modele.Look;
import modele.Modele;
import modele.Specialite;
import modele.Type;

/**
 *
 * @author ITU
 */
public class ChoixModeleController extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try{
            Modele modele=new Modele();
            ArrayList<Modele> liste=modele.getModeleSansTaille(null);
            request.setAttribute("modeles", liste);
        }catch(Exception e){
            
        }finally{
            request.getRequestDispatcher("./pages/admin/choixModel.jsp").forward(request, response);
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
            String modele=request.getParameter("modele");
            request.setAttribute("modele", modele);
            Specialite specialite=new Specialite();
            ArrayList<Specialite> liste=specialite.getAll(null);
            request.setAttribute("specs", liste);
        }catch(Exception e){
            e.printStackTrace();
            processRequest(request, response);
        }finally{
            request.getRequestDispatcher("./pages/admin/ajoutModeleSpecialite.jsp").forward(request, response);
        }
    }
}
