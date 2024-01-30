<%-- 
    Document   : ajoutMatiere.jsp
    Created on : 14 déc. 2023, 10:30:10
    Author     : ASUS
--%>

<%@page import="modele.Employe"%>
<%@page import="java.util.ArrayList"%>
<%
   String message=(String) request.getAttribute("message");
   String erreur=(String) request.getAttribute("erreur");
   ArrayList<Employe> listeM=(ArrayList<Employe>) request.getAttribute("emps");
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="headerAdmin.jsp" />
    <div class="col-md-6 grid-margin stretch-card">
    <div class="card">
      <div class="card-body">
        <h4 class="card-title">Embaucher un employé</h4>
        <form class="forms-sample" action="<%= request.getContextPath() %>/add_date_embauche" method="post">
              
          <div class="form-group">
            <label for="exampleInputPassword1">Personne</label>
            <select class="form-control" name="emp">
                <% if(listeM!=null) { %>
                    <% for(int i=0;i<listeM.size();i++) { %>
                    <option value="<%= listeM.get(i).getId() %>"><%= listeM.get(i).getNom() %> <%= listeM.get(i).getPrenom() %></option>
                    <% } %>
                <% } %>
              </select>
          </div>
              
          <div class="form-group">
            <label for="exampleInputUsername1">Date d'embauche</label>
            <input type="date" class="form-control" id="exampleInputUsername1" name="date" />
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
