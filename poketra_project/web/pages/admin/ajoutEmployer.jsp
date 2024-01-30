<%-- 
    Document   : ajoutType
    Created on : 19 déc. 2023, 14:28:04
    Author     : ASUS
--%>
<%@page import="modele.Specialite"%>
<%@page import="modele.Genre"%>
<%@page import="java.util.ArrayList"%>
<%
  String message=(String) request.getAttribute("message");
   String erreur=(String) request.getAttribute("erreur");
   ArrayList<Genre> genres=(ArrayList<Genre>) request.getAttribute("genres");
   ArrayList<Specialite> specs=(ArrayList<Specialite>) request.getAttribute("specialites");
 %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="headerAdmin.jsp" />
<div class="col-md-6 grid-margin stretch-card">
    <div class="card">
      <div class="card-body">
        <h4 class="card-title">Ajout Employé</h4>
        <form class="forms-sample" action="<%= request.getContextPath() %>/add_employe" method="post">
          <div class="form-group">
            <label for="exampleInputUsername1">Nom</label>
            <input type="text" class="form-control" id="exampleInputUsername1" name="nom" placeholder="Nom">
          </div>
          <div class="form-group">
            <label for="exampleInputUsername1">Prénom</label>
            <input type="text" class="form-control" id="exampleInputUsername1" name="prenom" placeholder="Prénom">
          </div>
          
          <div class="form-group">
            <label for="exampleInputUsername1">Date de naissance</label>
            <input type="date" class="form-control" id="exampleInputUsername1" name="dtn">
          </div>
          
          <div class="form-group">
              <label for="exampleInputUsername1">Genre</label>
              <div class="input-group mb-2 mr-sm-2">
                <select class="form-control" name="genre">
                <% if(genres!=null) { %>
                    <% for(int i=0;i<genres.size();i++) { %>
                        <option value="<%= genres.get(i).getId()%>"><%= genres.get(i).getNom() %></option>
                    <% } %>
                <% } %>
                </select>
              </div>
          </div>
          
          <div class="form-group">
            <label for="exampleInputUsername1">Specialités</label>
            <div class="input-group mb-2 mr-sm-2">
<<<<<<< Updated upstream
              <% if(specs!=null) { %>
                  <% for(int i=0;i<specs.size();i++) { %>
                  <p><input type="checkbox" value="<%= specs.get(i).getId()%>" name="specialite" /><%= specs.get(i).getNom() %></p>
                  <% } %>
              <% } %>
=======
              <select class="form-control" name="specialite">
                <% if(specs!=null) { %>
                    <% for(int i=0;i<specs.size();i++) { %>
                        <option value="<%= specs.get(i).getId()%>"><%= specs.get(i).getNom() %></option>                        
                    <% } %>
                <% } %>
              </select>
>>>>>>> Stashed changes
            </div>
          </div>
                
          <%if(message != null){ %>
            <div class="alert alert-success" role="alert">
                <p><%= message %></p>
            </div>
          <%   } %>
          <%if(erreur != null){ %>
            <div class="alert alert-danger" role="alert">
                <p><%= erreur %></p>
            </div>
          <%   } %>
          <input type="submit" value="Valider" class="btn btn-primary mr-2" />
        </form>
      </div>
    </div>
  </div> 
<jsp:include page="footerAdmin.jsp" />