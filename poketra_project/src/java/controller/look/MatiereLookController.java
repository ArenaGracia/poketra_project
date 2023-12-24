/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.look;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modele.Look;
import modele.Matiere;

/**
 *
 * @author ITU
 */
public class MatiereLookController extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try{
            Look look=new Look();
            ArrayList<Look> liste=look.getAllLook(null);
            request.setAttribute("looks", liste);
        }catch(Exception e){
            request.setAttribute("erreur", e.getMessage());
        }finally{
            request.getRequestDispatcher("./pages/admin/ajoutMatiereLook.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if(request.getParameter("look")!=null){
            try{
                String idLook=request.getParameter("look");
                Look look=new Look();
                look=look.getById(idLook,null);
                request.setAttribute("look", look);
                ArrayList<Matiere> liste=look.getExterneMatiere(null);
                request.setAttribute("matieres", liste);
            }catch(Exception e){
                request.setAttribute("erreur", e.getMessage());
            }finally{
                request.getRequestDispatcher("./pages/admin/ajoutMatiereLook1.jsp").forward(request, response);
            }   
        }
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try{
            String idLook=request.getParameter("look");
            String[] idMatiere=request.getParameterValues("matieres");
            ArrayList<Matiere> liste=new ArrayList<Matiere>();
            for(String id:idMatiere){
                Matiere m=new Matiere();
                m.setId(id);
                liste.add(m);
            }
            Look look=new Look();
            look.setId(idLook);
            look.setMatieres(liste);
            look.insererListe(null);
            request.setAttribute("message", "Insertion réussie");
        }catch(Exception e){
            request.setAttribute("erreur", e.getMessage());
            response.getWriter().print(e.getMessage());
        }finally{
            processRequest(request,response);
        }
    }
}
