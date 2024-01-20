/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.matiere;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modele.Matiere;
import modele.Stock;

/**
 *
 * @author ITU
 */
public class MatiereStockController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try{
            Matiere matiere=new Matiere();
            ArrayList<Matiere> liste=matiere.getAllMatiere(null);
            request.setAttribute("matieres", liste);
        }catch(Exception e){
            request.setAttribute("erreur", e.getMessage());
        }finally{
            request.getRequestDispatcher("./pages/admin/ajoutStock.jsp").forward(request, response);
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
        String idMatiere=request.getParameter("matiere");
        int nombre=Integer.valueOf(request.getParameter("nombre"));
        try{
            Matiere matiere=new Matiere();
            matiere.setId(idMatiere);
            Stock stock=new Stock();
            stock.setEntrer(nombre);
            stock.setMatiere(matiere);
            stock.setSortie(0);
            stock.setDate(new Timestamp(System.currentTimeMillis()));
            stock.insertStock(null);
            request.setAttribute("message", "inserer avec succes");
        }catch(Exception e){
            request.setAttribute("erreur", e.getMessage());
        }finally{
            processRequest(request, response);
        }
    }
}
