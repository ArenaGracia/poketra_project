/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.fabrication;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modele.Fabrication;
import modele.Modele;

/**
 *
 * @author ITU
 */
public class FabricationController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try{
            Modele modele=new Modele();
            ArrayList<Modele> liste=modele.getModeles(null);
            request.setAttribute("modeles", liste);
        }catch(Exception e){
            
        }finally{
            request.getRequestDispatcher("./pages/admin/ajoutFabrication.jsp").forward(request, response);
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
        String idModele=request.getParameter("modele");
        double nbr=Double.valueOf(request.getParameter("nombre"));
        try{
            Modele modele=new Modele();
            modele.setId(idModele);
            modele=modele.getModele(null);
            System.out.println("controller.fabrication.FabricationController.doPost() "+modele.getId()+" controller");
            Fabrication fabrication=new Fabrication();
            fabrication.setDate(new Timestamp(System.currentTimeMillis()));
            fabrication.setModele(modele);
            fabrication.setNombre(nbr);
            fabrication.insertFabrication(null);
            request.setAttribute("message", "insertion reussie");
        }catch(Exception e){
            request.setAttribute("erreur", e.getMessage());
        }finally{
                processRequest(request, response);
            }
        
    }
}
