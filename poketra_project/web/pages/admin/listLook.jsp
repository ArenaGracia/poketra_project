<%-- 
    Document   : ajoutMatiere.jsp
    Created on : 14 déc. 2023, 10:30:10
    Author     : ASUS
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="modele.Look"%>
<%
   ArrayList<Look> listeU=(ArrayList<Look>) request.getAttribute("looks");
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="headerAdmin.jsp" />
    <div class="col-md-6 grid-margin stretch-card">
    <div class="card">
      <div class="card-body">
            <h4 class="card-title">Liste des looks</h4>
        <form class="forms-sample" action="<%= request.getContextPath() %>/list_matiere" method="post">
              
          <div class="form-group">
            <label for="exampleInputPassword1">Look</label>
            <select class="form-control" name="look">
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
