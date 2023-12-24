<%-- 
    Document   : ajoutMatiere.jsp
    Created on : 14 déc. 2023, 10:30:10
    Author     : ASUS
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="modele.Unite"%>
<%
   String message=(String) request.getAttribute("message");
   String erreur=(String) request.getAttribute("erreur");
   ArrayList<Unite> listeU=(ArrayList<Unite>) request.getAttribute("unite");
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="headerAdmin.jsp" />
    <div class="col-md-6 grid-margin stretch-card">
    <div class="card">
      <div class="card-body">
        <h4 class="card-title">Inserer une matiere</h4>
        <form class="forms-sample" action="<%= request.getContextPath() %>/add_matiere" method="post">
          <div class="form-group">
            <label for="exampleInputPassword1">Matiere</label>
            <input type="text" class="form-control" name="matiere">
          </div>
              
          <div class="form-group">
            <label for="exampleInputPassword1">Unite</label>
            <select class="form-control" name="unite">
                <% if(listeU!=null) { %>
                    <% for(int i=0;i<listeU.size();i++) { %>
                        <option value="<%= listeU.get(i).getId()%>"><%= listeU.get(i).getNom() %></option>
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
