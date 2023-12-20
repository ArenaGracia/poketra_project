<%-- 
    Document   : listTaille
    Created on : 19 déc. 2023, 14:50:11
    Author     : ASUS
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="modele.Matiere"%>
<%@page import="modele.Taille"%>
<%
    String idLook=(String) request.getAttribute("idLook");
    String idType=(String) request.getAttribute("idType");
    ArrayList<Taille> listeT=(ArrayList<Taille>) request.getAttribute("tailles");
    ArrayList<Matiere> listeU=(ArrayList<Matiere>) request.getAttribute("matieres");
   String message=(String) request.getAttribute("message");
   String erreur=(String) request.getAttribute("erreur");
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="headerAdmin.jsp" />
    <div class="col-md-6 grid-margin stretch-card">
    <div class="card">
      <div class="card-body">
        <h4 class="card-title">Liste taille</h4>
            <form class="forms-sample" action="<%= request.getContextPath() %>/list_matiere_produit" method="post">
              <div class="form-group">
                  <input type="hidden" class="form-control" name="look" value="<%= idLook %>">
                  <input type="hidden" class="form-control" name="type" value="<%= idType %>">
              </div>
          
                <% for(int j=0;j<listeT.size();j++) { %>
                   <div class="form-group">
                       <p><input type="hidden" class="form-control" name="taille" value="<%= listeT.get(j).getId() %>"><%= listeT.get(j).getNom() %></p>
                       <ul>
                        <% for(int i=0;i<listeU.size();i++) { %>
                        <li><input type="hidden" name="<%= listeT.get(j).getId() %>" value="<%= listeU.get(i).getId()%>"><%= listeU.get(i).getNom() %><input type="number" class="form-control" name="<%= listeT.get(j).getId().concat("number") %>"></li>
                        <% } %>
                       </ul>
                   </div>       
                <% } %> 
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