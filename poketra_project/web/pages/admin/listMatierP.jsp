<%-- 
    Document   : ajoutMatiere.jsp
    Created on : 14 déc. 2023, 10:30:10
    Author     : ASUS
--%>

<%@page import="modele.DetailModele"%>
<%@page import="java.util.ArrayList"%>
<%@page import="modele.Matiere"%>
<%
   ArrayList<Matiere> listeU=(ArrayList<Matiere>) request.getAttribute("matieres");
   ArrayList<DetailModele> liste=(ArrayList<DetailModele>) request.getAttribute("modeles");
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="headerAdmin.jsp" />
    <div class="col-md-6 grid-margin stretch-card">
    <div class="card">
      <div class="card-body">
            <h4 class="card-title">Liste des Modèles selon la matière</h4>
        <form class="form-inline" action="<%= request.getContextPath() %>/list_matiere_produit" method="get">
            <div class="input-group mb-2 mr-sm-2">
                <select class="form-control" name="matiere">
                <% if(listeU!=null) { %>
                    <% for(int i=0;i<listeU.size();i++) { %>
                        <option value="<%= listeU.get(i).getId()%>"><%= listeU.get(i).getNom() %></option>
                    <% } %>
                <% } %>
                </select>
            </div>
           
          <input type="submit" value="Valider" class="btn btn-primary mr-2" />
        </form>
        
        <br />
        
        <% if(liste!=null) { %>
            <div class="table-responsive">
              <table class="table">
                <thead>
                  <tr>
                    <th>Look</th>
                    <th>Type</th>
                    <th>Taille</th>
                    <th>Quantite</th>
                  </tr>
                </thead>
                <tbody>
                  <% for(int i=0;i<liste.size();i++) { %>
                      <tr>
                        <td><%= liste.get(i).getModele().getLook().getNom() %></td>
                        <td><%= liste.get(i).getModele().getType().getNom() %></td>
                        <td><%= liste.get(i).getModele().getTaille().getNom() %></td>
                        <td><%= liste.get(i).getQuantite() %></td>
                      </tr>
                  <% } %>
                </tbody>
              </table>
        <% } %>
      </div>
    </div>
  </div>    
<jsp:include page="footerAdmin.jsp" />
