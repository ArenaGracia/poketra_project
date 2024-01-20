/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.matiere;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modele.Matiere;
import modele.Stock;

/**
 *
 * @author ASUS
 */
public class StockMatiereController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
       try{
            Matiere matiere=new Matiere();
            ArrayList<Matiere> liste=matiere.getAllMatiere(null);
            request.setAttribute("matieres", liste);
            if(request.getAttribute("liste")==null){
                Stock stock=new Stock();
                Matiere mat=new Matiere();
                mat.setId("%");
                stock.setMatiere(mat);
                ArrayList<Stock> stocks=stock.getAllStock(null);
                request.setAttribute("liste", stocks);
            }
        }catch(Exception e){
            request.setAttribute("erreur", e.getMessage());
        }finally{
            request.getRequestDispatcher("./pages/admin/etatStock.jsp").forward(request, response);
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
        String idM=request.getParameter("matiere");
        System.out.println(idM);
        try{
            Stock stock=new Stock();
            Matiere mat=new Matiere();
            mat.setId(idM);
            stock.setMatiere(mat);
            ArrayList<Stock> liste=stock.getAllStock(null);
            request.setAttribute("liste", liste);
        }catch(Exception e){
            request.setAttribute("erreur", e.getMessage());
        }finally{
            processRequest(request, response);
        }   
    }
}