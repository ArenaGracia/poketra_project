<%-- 
    Document   : listMatiereByLook
    Created on : 19 déc. 2023, 14:38:32
    Author     : ASUS
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="modele.Matiere"%>
<%@page import="modele.Look"%>
<%
    Look look=(Look) request.getAttribute("look");
    ArrayList<Matiere> listeU=(ArrayList<Matiere>) request.getAttribute("matieres");
    String message=(String) request.getAttribute("erreur");
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="headerAdmin.jsp" />
    <div class="col-md-6 grid-margin stretch-card">
    <div class="card">
      <div class="card-body">
        <h4 class="card-title">Liste des matières selon <%= look.getNom() %></h4>
        <% if(listeU!=null && listeU.size()>0) { %>
            <form class="forms-sample" action="<%= request.getContextPath() %>/add_quantite_matiere" method="post">
            <div class="form-group">
                <% for(int i=0;i<listeU.size();i++) { %>
                <p><input type="checkbox" name="matieres" value="<%= listeU.get(i).getId()%>"><%= listeU.get(i).getNom() %>quantite : <input type="number" name="quantite"></p>
                <% } %>
                    <input type="submit" value="Valider" class="btn btn-primary mr-2" />
            </div>             
          
            </form>
        <% } if(message!=null) { %>
            <p><%= message %></p>
        <% } %>
        
            
      </div>
    </div>
  </div>    
<jsp:include page="footerAdmin.jsp" />
