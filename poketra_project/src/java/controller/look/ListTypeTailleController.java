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
import modele.Taille;
import modele.Type;
import modele.Unite;

/**
 *
 * @author Toky
 */
public class ListTypeTailleController extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try{
            Look look=new Look();
            Type type=new Type();
            ArrayList<Type> listeT=type.getAllType(null);
            ArrayList<Look> listes=look.getAllLook(null);
            request.setAttribute("types", listeT);
            request.setAttribute("looks", listes);
            
        }catch(Exception e){
            request.setAttribute("erreur", e.getMessage());
        }finally{
            request.getRequestDispatcher("./pages/admin/listTypeTaille.jsp").forward(request, response);
        }
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

            if(request.getParameter("type")!=null && request.getParameter("look")!=null){
               try{
                    Taille taille=new Taille();
                    Look look=new Look();
                    String idType=request.getParameter("type");
                    String idLook=request.getParameter("look");
                    look.setId(idLook);
                    ArrayList<Taille> tailles= taille.getAllTaille(null);
                    ArrayList<Matiere> matieres= look.getAllMatiere(null);
                    request.setAttribute("idLook", idLook);
                    request.setAttribute("idType", idType);
                    request.setAttribute("tailles", tailles);
                    request.setAttribute("matieres", matieres);
                }catch(Exception e){
                    request.setAttribute("erreur", e.getMessage());
                }finally{
                    request.getRequestDispatcher("./pages/admin/listTaille.jsp").forward(request, response);
                }
            }           
            processRequest(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       try {
            String nom = (String) request.getParameter("matiere");
            Matiere matiere =new Matiere();
            Unite unite=new Unite();
            ArrayList<Unite> liste=unite.getAllUnite(null);
            request.setAttribute("unite", liste);
            unite.setId(request.getParameter("unite"));

            matiere.setNom(nom);
            matiere.setUnite(unite);
            matiere.insererMatiere(null);
            request.setAttribute("message", "Insertion de matiere  réussie");
        } catch (Exception e) {
            request.setAttribute("erreur", e.getMessage());
            response.getWriter().print(e.getMessage());
        }
        finally{
            processRequest(request,response);
        }
    }

}
