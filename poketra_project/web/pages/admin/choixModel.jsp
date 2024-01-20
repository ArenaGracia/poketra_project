<%-- 
    Document   : ajoutMatiere.jsp
    Created on : 14 déc. 2023, 10:30:10
    Author     : ASUS
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="modele.Modele"%>
<%
   String message=(String) request.getAttribute("message");
   String erreur=(String) request.getAttribute("erreur");
   ArrayList<Modele> listeM=(ArrayList<Modele>) request.getAttribute("modeles");
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="headerAdmin.jsp" />
    <div class="col-md-6 grid-margin stretch-card">
    <div class="card">
      <div class="card-body">
        <h4 class="card-title">Choix Modele</h4>
        <form class="forms-sample" action="<%= request.getContextPath() %>/add_specialite_modele" method="post">
              
          <div class="form-group">
            <label for="exampleInputPassword1">Modele</label>
            <select class="form-control" name="modele">
                <% if(listeM!=null) { %>
                    <% for(int i=0;i<listeM.size();i++) { %>
                    <option value="<%= listeM.get(i).getLook().getId()+"_"+listeM.get(i).getType().getId() %>"><%= listeM.get(i).getType().getNom() %> <%= listeM.get(i).getLook().getNom() %></option>
                    <% } %>
                <% } %>
              </select>
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
