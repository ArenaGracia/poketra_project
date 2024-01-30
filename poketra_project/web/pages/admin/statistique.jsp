 <%-- 
    Document   : statVente
    Created on : 25 janv. 2024, 15:15:54
    Author     : ITU
--%>

<%@page import="modele.Statistique"%>
<%@page import="modele.Modele"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
  ArrayList<Modele> listeM=(ArrayList<Modele>) request.getAttribute("modeles");
%>

<jsp:include page="headerAdmin.jsp" />
    <div class="col-md-6 grid-margin stretch-card">
    <div class="card">
      <div class="card-body">
        <h4 class="card-title">Statistiques par genre par modèles</h4>
        <form class="forms-sample" action="<%= request.getContextPath() %>/stat_vente" method="post">
              
          <div class="form-group">
            <label for="exampleInputPassword1">Modèles</label>
            <select class="form-control" name="modele">
                <% if(listeM!=null) { %>
                    <option value="all">Tout</option>
                    <% for(int i=0;i<listeM.size();i++) { %>
                        <option value="<%= listeM.get(i).getId()%>"><%= listeM.get(i).getType().getNom() %> <%= listeM.get(i).getLook().getNom() %> <%= listeM.get(i).getTaille().getNom() %> </option>
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
