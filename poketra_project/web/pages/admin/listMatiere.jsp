<%-- 
    Document   : ajoutMatiere.jsp
    Created on : 14 déc. 2023, 10:30:10
    Author     : ASUS
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="modele.Matiere"%>
<%@page import="modele.Look"%>
<%
    Look look=(Look) request.getAttribute("look");
    ArrayList<Matiere> listeU=look.getMatieres();
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="headerAdmin.jsp" />
    <div class="col-md-6 grid-margin stretch-card">
    <div class="card">
      <div class="card-body">
        <h4 class="card-title">Look <%= look.getNom() %></h4> 
            <ul>
            <% if(listeU!=null) { %>
                <% for(int i=0;i<listeU.size();i++) { %>
                    <li><%= listeU.get(i).getNom() %></li>
                <% } %>
            <% } if(listeU.size()==0) { %>
                <p>Aucune matière pour le moment</p>
            <% } %>
            </ul>
      </div>
    </div>
  </div>    
<jsp:include page="footerAdmin.jsp" />