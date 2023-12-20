<%-- 
    Document   : ajoutMatiere.jsp
    Created on : 14 déc. 2023, 10:30:10
    Author     : ASUS
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="modele.Matiere"%>
<%
   ArrayList<Matiere> listeU=(ArrayList<Matiere>) request.getAttribute("matieres");
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="headerAdmin.jsp" />
    <div class="col-md-6 grid-margin stretch-card">
    <div class="card">
      <div class="card-body">
            <h4 class="card-title">Liste des Matieres</h4>
        <form class="forms-sample" action="<%= request.getContextPath() %>/list_matiere_produit" method="get">
              
          <div class="form-group">
            <label for="exampleInputPassword1">Matiere</label>
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
      </div>
    </div>
  </div>    
<jsp:include page="footerAdmin.jsp" />
