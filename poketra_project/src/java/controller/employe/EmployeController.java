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
import modele.Genre;
import modele.Specialite;

/**
 *
 * @author ITU
 */
public class EmployeController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try{
            Genre genre=new Genre();
            Specialite specialite=new Specialite();
            ArrayList<Genre> liste=genre.getAll(null);
            ArrayList<Specialite> specs=specialite.getAll(null);
            request.setAttribute("genres", liste);
            request.setAttribute("specialites", specs);
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            request.getRequestDispatcher("./pages/admin/ajoutEmployer.jsp").forward(request, response);
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
            String specialite=request.getParameter("specialite");
            String nom=request.getParameter("nom");
            String prenom=request.getParameter("prenom");
            String dateNaissance=request.getParameter("dtn");
            System.out.println("controller.employe.EmployeController.doPost() "+ dateNaissance);
            String genre=request.getParameter("genre");
            Genre g=new Genre();
            g.setId(genre);
            Employe emp=new Employe();
            Specialite spec=new Specialite();
            spec.setId(specialite);
            emp.setDateNaissance(dateNaissance);
            emp.setGenre(g);
            emp.setSpecialite(spec);
            emp.setNom(nom);
            emp.setPrenom(prenom);
            emp.insererEmployer(null);
            request.setAttribute("message", "Insertion réussie");
        }catch(Exception e){
            request.setAttribute("erreur", e.getMessage());
        }finally{
            processRequest(request, response);
        }
    }

}
