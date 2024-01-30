<%-- 
    Document   : ajoutClient
    Created on : 25 janv. 2024, 14:49:12
    Author     : ASUS
--%>

<%@page import="modele.Genre"%>
<%@page import="java.util.ArrayList"%>
<%
  String message=(String) request.getAttribute("message");
   String erreur=(String) request.getAttribute("erreur");
   ArrayList<Genre> genres=(ArrayList<Genre>) request.getAttribute("genres");
 %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="headerAdmin.jsp" />
<div class="col-md-6 grid-margin stretch-card">
    <div class="card">
      <div class="card-body">
        <h4 class="card-title">Ajout Client</h4>
        <form class="forms-sample" action="<%= request.getContextPath() %>/add_client" method="post">
          <div class="form-group">
            <label for="exampleInputUsername1">Nom</label>
            <input type="text" class="form-control" id="exampleInputUsername1" name="nom" placeholder="Nom">
          </div>
           <div class="form-group">
            <label for="exampleInputUsername1">Genre</label>
            <select class="form-control" name="genre">
                <% if(genres!=null) { %>
                    <% for(int i=0;i<genres.size();i++) { %>
                        <option value="<%= genres.get(i).getId()%>"><%= genres.get(i).getNom() %></option>
                    <% } %>
                <% } %>
            </select>
          </div>
           <input type="submit" value="Valider" class="btn btn-primary mr-2" />
        </form>
     </div>
    </div>
  </div>
   <jsp:include page="footerAdmin.jsp" />
