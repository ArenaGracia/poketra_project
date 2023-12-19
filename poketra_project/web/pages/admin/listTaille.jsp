<%-- 
    Document   : listTaille
    Created on : 19 déc. 2023, 14:50:11
    Author     : ASUS
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="modele.Matiere"%>
<%@page import="modele.Taille"%>
<%
     Taille taille=(Taille) request.getAttribute("taille");
    ArrayList<Taille> listeT=(ArrayList<Taille>) request.getAttribute("tailles");
    ArrayList<Matiere> listeU=(ArrayList<Matiere>) request.getAttribute("matieres");
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="headerAdmin.jsp" />
    <div class="col-md-6 grid-margin stretch-card">
    <div class="card">
      <div class="card-body">
        <h4 class="card-title">Liste taille</h4>
        <% if(listeT!=null && listeT.size()>0) { %>
            <form class="forms-sample" action="<%= request.getContextPath() %>/add_qantite_matiere" method="post">
              <div class="form-group">
                  <input type="hidden" class="form-control" name="taille" value="<%= look.getId() %>">
              </div>
          
            <div class="form-group">
                <% for(int i=0;i<listeU.size();i++) { %>
                <p><input type="checkbox" name="matieres" value="<%= listeU.get(i).getId()%>"><%= listeU.get(i).getNom() %>Quantite :<input type="number" name="quantite"></p>
                <% } %>
                    <input type="submit" value="Valider" class="btn btn-primary mr-2" />
            </div>             
          
            </form>      
      </div>
    </div>
  </div>    
<jsp:include page="footerAdmin.jsp" />