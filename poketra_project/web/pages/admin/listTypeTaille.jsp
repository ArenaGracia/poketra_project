<%-- 
    Document   : listTypeTaille
    Created on : 19 déc. 2023, 14:30:43
    Author     : ASUS
--%>
<%@page import="java.util.ArrayList"%>
<%@page import="modele.Type"%>
<%@page import="modele.Look"%>
<%
   ArrayList<Type> listeType=(ArrayList<Type>) request.getAttribute("types");
   ArrayList<Look> listeLook=(ArrayList<Look>) request.getAttribute("looks");
   String message=(String) request.getAttribute("message");
   String erreur=(String) request.getAttribute("erreur");
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="headerAdmin.jsp" />
    <div class="col-md-6 grid-margin stretch-card">
    <div class="card">
      <div class="card-body">
            <h4 class="card-title">Liste des looks</h4>
        <form class="forms-sample" action="<%= request.getContextPath() %>/list_type_taille" method="get">
              
          <div class="form-group">
            <label for="exampleInputPassword1">Type</label>
            <select class="form-control" name="type">
                <% if(listeType!=null) { %>
                    <% for(int i=0;i<listeType.size();i++) { %>
                        <option value="<%= listeType.get(i).getId()%>"><%= listeType.get(i).getNom() %></option>
                    <% } %>
                <% } %>
              </select>
          </div>  
             <div class="form-group">
            <label for="exampleInputPassword1">Look</label>
            <select class="form-control" name="look">
                <% if(listeLook!=null) { %>
                    <% for(int i=0;i<listeLook.size();i++) { %>
                        <option value="<%= listeLook.get(i).getId()%>"><%= listeLook.get(i).getNom() %></option>
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
          <input type="submit" value="OK" class="btn btn-primary mr-2" />
        </form>
      </div>
    </div>
  </div>    
<jsp:include page="footerAdmin.jsp" />
