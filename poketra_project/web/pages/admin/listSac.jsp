<%-- 
    Document   : listSac
    Created on : 9 janv. 2024, 14:51:58
    Author     : ASUS
--%>
<%@page import="modele.Modele"%>
<%@page import="java.util.ArrayList"%>
<%
   ArrayList<Modele> liste=(ArrayList<Modele>) request.getAttribute("modeles");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="headerAdmin.jsp" />

<% if(liste!=null) { %>
            <div class="table-responsive">
              <table class="table">
                <thead>
                  <tr>
                    <th>Look</th>
                    <th>Type</th>
                    <th>Taille</th>
                    <th>Prix</th>
                  </tr>
                </thead>
                <tbody>
                  <% for(int i=0;i<liste.size();i++) { %>
                      <tr>
                        <td><%= liste.get(i).getLook().getNom() %></td>
                        <td><%= liste.get(i).getType().getNom() %></td>
                        <td><%= liste.get(i).getTaille().getNom() %></td>
                        <td><%= liste.get(i).getPrixConfection() %></td>
                      </tr>
                  <% } %>
                </tbody>
              </table>
        <% } %>
<jsp:include page="footerAdmin.jsp" />