<%-- 
    Document   : ajoutMatierePrix
    Created on : 9 janv. 2024, 14:16:29
    Author     : ASUS
--%>
<%@page import="modele.Matiere"%>
<%@page import="java.util.ArrayList"%>
<%
    ArrayList<Matiere> listeU=(ArrayList<Matiere>) request.getAttribute("matieres");
    String message=(String) request.getAttribute("message");
    String erreur=(String) request.getAttribute("erreur");
 %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="headerAdmin.jsp" />
    <div class="col-md-6 grid-margin stretch-card">
    <div class="card">
      <div class="card-body">
        <h4 class="card-title">Inserer un prix pour une matiere  </h4>
        <form class="forms-sample" action="<%= request.getContextPath() %>/add_matiere_prix" method="post">
            <div class="input-group mb-2 mr-sm-2">
                <select class="form-control" name="matiere">
                <% if(listeU!=null) { %>
                    <% for(int i=0;i<listeU.size();i++) { %>
                        <option value="<%= listeU.get(i).getId()%>"><%= listeU.get(i).getNom() %></option>
                    <% } %>
                <% } %>
                </select>
            </div>
          <div class="form-group">
            <label for="exampleInputPassword1">Prix</label>
            <input type="text" class="form-control" name="prix">
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

