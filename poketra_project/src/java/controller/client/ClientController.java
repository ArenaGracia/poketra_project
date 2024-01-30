/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.client;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modele.Client;
import modele.Genre;
import modele.Specialite;

/**
 *
 * @author ASUS
 */
public class ClientController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
          try{
            Genre genre=new Genre();
            ArrayList<Genre> liste=genre.getAll(null);
            request.setAttribute("genres", liste);
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            request.getRequestDispatcher("./pages/admin/ajoutClient.jsp").forward(request, response);
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
            String nom=request.getParameter("nom");
            String genre=request.getParameter("genre");
            Genre g=new Genre();
            g.setId(genre);
           Client c=new Client();
            c.setNom(nom);
            c.setGenre(g);
            c.insererClient(null);
            request.setAttribute("message", "Insertion réussie");
        }catch(Exception e){
            request.setAttribute("erreur", e.getMessage());
        }finally{
            processRequest(request, response);
        }
        
    }
}