package controller.vente;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modele.*;

public class VenteController extends HttpServlet {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        boolean conf = false;
        String message = "Vente successful";
        String erreur = "";
        try{
            ArrayList<Modele> listeModele = new Modele().getModeles(null);
            ArrayList<Client> listeClient = new Client().getAllClient(null);
            request.setAttribute("modele", listeModele);
            request.setAttribute("client", listeClient);
        }catch(Exception e){
            erreur = e.getMessage();
            request.setAttribute("erreur", erreur);
            conf = false;
        }finally{
            if(conf){
                request.setAttribute("message", message);
            }
            RequestDispatcher dispatcher = request.getRequestDispatcher("./pages/admin/ajoutVente.jsp");
            dispatcher.forward(request, response);
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
        String message = "Vente successful";
        String erreur = "";
        boolean conf = true;
        try{
            String idModele = request.getParameter("modele");
            String idClient = request.getParameter("client");
            double nombre = Double.parseDouble(request.getParameter("nombre"));
            
            Modele modele = new Modele();
            Client client = new Client();
            modele.setId(idModele);
            client.setId(idClient);
            Date date = Date.valueOf(LocalDate.now());
            
            Vente vente = new Vente();
            vente.setClient(client);
            vente.setModele(modele);
            vente.setNombre(nombre);
            vente.setDate(date);
            vente.insererVente(null);
            
        }catch(Exception e){
            erreur = e.getMessage();
            request.setAttribute("erreur", erreur);
        }finally{
            processRequest(request, response);
        }
    }

}
